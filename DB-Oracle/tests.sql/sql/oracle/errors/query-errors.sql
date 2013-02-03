----# error 00942 UnknownTableError
select * from unexistent_table;

----# error 00904 UnknownIdentifierError
select unexistent_column from dual;
