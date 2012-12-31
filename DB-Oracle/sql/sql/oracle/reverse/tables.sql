----# Tables
select Owner as Schema_Name,
       Table_Name,
       decode(IOT_Type, null, 'F', 'T') as Struct_Kind,
       decode(Temporary, 'Y', 1, 0) as Is_Temporary
from sys.All_Tables
where Secondary = 'N' and Nested = 'NO';

----# Table_Columns
select Owner as Schema_Name,
       Table_Name,
       Column_Id as Column_Order_Nr,
       Column_Name,
       Data_Type,
       nvl(Data_Precision, Data_Length) as Data_Size,
       Data_Scale,
       decode(Nullable,'N',0,1) as Mandatory,
       Data_Default
from sys.All_Tab_Columns
where (Owner,Table_Name) in (select Owner,Table_Name
                             from sys.All_Tables
                             where Secondary = 'N'
                               and Nested = 'NO');
