-- DataMagus Depot for Oracle

-- The core stored objects


create table Meta_Class
(
    Class_Id number(4) not null
        constraint Meta_Class_pk
            primary key,
    Base_Class_Code
        constraint Meta_Class_Base_fk
            references Meta_Class,
    Class_Java_Package varchar(160) not null,
    Class_Java_Name varchar(60) not null,
    constraint Meta_Class_Java_ak
        unique (Class_Java_Name, Class_Java_Package)
);


create table Meta_Property
(
    Property_Id number(4) not null
        constraint Meta_Property_pk
            primary key,
    Property_Type char(1) not null,
    Class_Id not null
        constraint Meta_Property_Class_fk
            references Meta_Class
                on delete cascade,
    Property_Name varchar(26) not null,
    constraint Meta_Property_ak
        unique (Class_Id, Property_Name)
);


create table Text
(
    Text_Hash char(40) not null
        constraint Text_pk
            primary key,
    Text_Body nclob
);


create table Model
(
    Mod_Code varchar(8) not null
        constraint Model_pk primary key,
    Mod_Name nvarchar2(160) not null
        constraint Model_Name_ak unique
) organization index;


create table Model_Version
(
    Mod_Code not null
        constraint Model_Version_Model_fk
            references Model
                on delete cascade,
    Mod_Ver number(6) not null,
    Timestamp date default sysdate not null,
    constraint Model_Version_pk
        primary key (Mod_Code, Mod_ver)
) organization index;

create index Model_Version_Time_i
    on Model_Version (Mod_Code, Timestamp);


create table Word
(
    Mod_Code,
    Mod_Ver,
    constraint Word_Model_fk
        foreign key (Mod_Code, Mod_Ver)
            references Model_Version
                on delete cascade,
    Word nvarchar2(60),
    constraint Word_pk
        primary key (Mod_Code, Mod_Ver, Word)
) organization index;


create table Node
(
    Mod_Code,
    Mod_Ver,
    constraint Node_Model_fk
        foreign key (Mod_Code, Mod_Ver)
            references Model_Version
                on delete cascade,
    Node_Id number(10) not null
        constraint Node_Nid_positive_ch
            check (Node_Id > 0),
    constraint Node_pk
        primary key (Mod_Code, Mod_Ver, Node_Id),
    Node_Name nvarchar2(160),  -- not mandatory
    Class_Id not null
        constraint Node_Class_fk
            references Meta_Class
) organization index;

create index Node_Name_i
    on Node (Mod_Code, Mod_Ver, Node_Name);


create table Node_Property_Int
(
    Mod_Code,
    Mod_Ver,
    Node_Id,
    constraint Node_Property_Int_Node_fk
        foreign key (Mod_Code, Mod_Ver, Node_Id)
            references Node,
    Property_Id not null
        constraint Node_Property_Int_Property_fk
            references Meta_Property,
    constraint Node_Property_Int_pk
        primary key (Mod_Code, Mod_Ver, Node_Id, Property_Id),
    Property_Value number(19) not null
) organization index;


create table Node_Property_Str
(
    Mod_Code,
    Mod_Ver,
    Node_Id,
    constraint Node_Property_Str_Node_fk
        foreign key (Mod_Code, Mod_Ver, Node_Id)
            references Node
                on delete cascade,
    Property_Id not null
        constraint Node_Property_Str_Property_fk
            references Meta_Property,
    constraint Node_Property_Str_pk
        primary key (Mod_Code, Mod_Ver, Node_Id, Property_Id),
    Property_Hash
        constraint Node_Property_Str_Text_fk
            references Text,
    Property_Value nvarchar2(2000)
) organization index overflow tablespace Users;


create table Node_Property_Ref
(
    Mod_Code,
    Mod_Ver,
    Node_Id,
        constraint Node_Property_Ref_Node_fk
            foreign key (Mod_Code, Mod_Ver, Node_Id)
                references Node
                    on delete cascade,
    Property_Id not null
        constraint Node_Property_Ref_Property_fk
            references Meta_Property,
    constraint Node_Property_Ref_pk
        primary key (Mod_Code, Mod_Ver, Node_Id, Property_Id),
    Refer_Index number(4) default 0 not null,
    Refer_Node_Id not null,
    constraint Node_Property_Ref_Ref_Node_fk
        foreign key (Mod_Code, Mod_Ver, Refer_Node_Id)
            references Node
) organization index;

create index Node_Property_Ref_Ref_Node_i
    on Node_Property_Ref (Mod_Code, Mod_Ver, Refer_Node_Id);



