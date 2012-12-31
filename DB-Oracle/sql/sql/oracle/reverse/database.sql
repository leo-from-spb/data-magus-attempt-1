----# Env_Names
select regexp_substr(Property_Value, '^[^\.]+') as Database_Name,
       sys_context('userenv','current_schema') as Schema_Name,
       user as User_Name
from sys.Database_Properties
where Property_Name = 'GLOBAL_DB_NAME';
