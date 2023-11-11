-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

-------------------------------------------------
-- Create general sequence
-------------------------------------------------

CREATE SEQUENCE  "HIBERNATE_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

-------------------------------------------------
-- Insert settings records
-------------------------------------------------

Insert into HC_SETTINGS (ID,CREATED_AT,MODIFIED_AT,RETIRED,RETIRED_AT,RETIRED_BY,KEY,VALUE) values (1,null,null,0,null,null,'lastHmcIntegrationDate','05/07/2020 05:00:00');
Insert into HC_SETTINGS (ID,CREATED_AT,MODIFIED_AT,RETIRED,RETIRED_AT,RETIRED_BY,KEY,VALUE) values (2,null,null,0,null,null,'lastMophIntegrationDate','05/07/2020 05:00:00');
