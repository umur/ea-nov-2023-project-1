-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

-------------------------------------------------
-- Close opened groups
-------------------------------------------------

SELECT *
FROM HC_GROUPS
WHERE
    CREATED_AT <= '05-JUN-2020 12:00:00AM'
AND
    CLOSED=0
ORDER BY CREATED_AT DESC
;

--

UPDATE HC_GROUPS
SET CLOSED=1
WHERE
    CREATED_AT <= '05-JUN-2020 12:00:00AM'
;

-------------------------------------------------
-- Make username and qid unique
-------------------------------------------------

-- ALTER TABLE HC_USERS ADD CONSTRAINT HC_USERS_UNIQUES UNIQUE (USERNAME, QID);

-------------------------------------------------
-- Change DOB column type of person to Date
-------------------------------------------------

ALTER TABLE HC_PERSONS RENAME COLUMN DATE_OF_BIRTH TO DATE_OF_BIRTH_OLD;
ALTER TABLE HC_PERSONS ADD DATE_OF_BIRTH TIMESTAMP;

UPDATE HC_PERSONS
SET DATE_OF_BIRTH = TO_TIMESTAMP(TO_CHAR(TO_TIMESTAMP(DATE_OF_BIRTH_OLD, 'YYYY-MM-DD'), 'DD/MM/RRRR HH12:MI:SSXFF PM'), 'DD/MM/RRRR HH12:MI:SSXFF PM')
;

ALTER TABLE HC_PERSONS DROP (DATE_OF_BIRTH_OLD);

-- Rollback the fix script

------ START ------
ALTER TABLE HC_PERSONS DROP (DATE_OF_BIRTH);
ALTER TABLE HC_PERSONS RENAME COLUMN DATE_OF_BIRTH_OLD TO DATE_OF_BIRTH;
------ END ------

-------------------------------------------------
-- Fix checkup lat and long that have Optional prefix
-------------------------------------------------

UPDATE HC_CHECKUPS
SET
    LATITUDE=SUBSTR(LATITUDE, 10, INSTR(LATITUDE,')')-10),
    LONGITUDE=SUBSTR(LONGITUDE, 10, INSTR(LONGITUDE,')')-10)
WHERE LATITUDE IS NOT NULL AND LONGITUDE IS NOT NULL
;

-- Rollback the fix script

------ START ------
UPDATE HC_CHECKUPS
SET
    LATITUDE=CONCAT('Optional(',CONCAT(LATITUDE,')')),
    LONGITUDE=CONCAT('Optional(',CONCAT(LONGITUDE,')'))
WHERE LATITUDE IS NOT NULL AND LONGITUDE IS NOT NULL
;
------ END ------

-------------------------------------------------
-- Drop primary and secondary mobile numbers
-------------------------------------------------

ALTER TABLE HC_CHECKUPS DROP (MOBILE_NUMBER_PRIMARY, MOBILE_NUMBER_SECONDARY);

-------------------------------------------------
-- Drop static sub-types columns
-------------------------------------------------

ALTER TABLE HC_CHECKUPS DROP (RANDOM_CLIENT_ID, QUARANTINE_FACILITY_ID, LOCKDOWN_ZONE_ID);

-------------------------------------------------
-- Change lookup type name from 'humanGender' to 'humanGenders'
-------------------------------------------------

------ START ------
UPDATE HC_LOOKUPS
SET LOOKUP_TYPE='humanGenders'
WHERE LOOKUP_TYPE='humanGender'
;
------ END ------

-- Rollback the fix script

------ START ------
UPDATE HC_LOOKUPS
SET LOOKUP_TYPE='humanGender'
WHERE LOOKUP_TYPE='humanGenders'
;
------ END ------

-------------------------------------------------
-- Use one column subtype (COLLECTION_SUBTYPE_ID) instead of using column for each subtype (RANDOM_CLIENT_ID, QUARANTINE_FACILITY_ID, LOCKDOWN_ZONE_ID)
-------------------------------------------------

/*
SELECT count(SERIAL,COLLECTION_TYPE_ID,COLLECTION_SUBTYPE_ID,RANDOM_CLIENT_ID,QUARANTINE_FACILITY_ID,LOCKDOWN_ZONE_ID)
FROM HC_GROUPS
ORDER BY CREATED_AT DESC
;
*/

-- Before run the fix script the script (2) should return value same as the script (1)
-- Before run the fix script the script (3) should return zero value
-- After run the fix script the script (2) should return zero value, others wise means there are some sample collection not set correctly
-- After run the fix script the script (3) should return value same as the script (1), others wise means there are some sample collection not set correctly
-- 1)
SELECT count(*)
FROM HC_GROUPS
WHERE COLLECTION_TYPE_ID IN (1,3,4)
;
-- 2)
SELECT count(*)
FROM HC_GROUPS
WHERE COLLECTION_TYPE_ID IN (1,3,4) AND COLLECTION_SUBTYPE_ID IS NULL
;
-- 2) with columns
SELECT ORIGINATING_POSITIVE_PERSON_ID,COLLECTION_TYPE_ID,COLLECTION_SUBTYPE_ID,LOCKDOWN_ZONE_ID,QUARANTINE_FACILITY_ID,RANDOM_CLIENT_ID
FROM HC_GROUPS
WHERE COLLECTION_TYPE_ID IN (1,3,4) AND COLLECTION_SUBTYPE_ID IS NULL
;
-- 3)
SELECT count(*)
FROM HC_GROUPS
WHERE COLLECTION_TYPE_ID IN (1,3,4) AND COLLECTION_SUBTYPE_ID IS NOT NULL
;

--

------ START ------
UPDATE HC_LOOKUPS
SET LOOKUP_SUBTYPE =
    CASE
        WHEN ID=1 THEN 'randomClients'
        WHEN ID=3 THEN 'quarantineFacilities'
        WHEN ID=4 THEN 'lockdownZones'
    END
WHERE LOOKUP_TYPE='collectionTypes'
;
------ END ------

------ START ------
UPDATE HC_LOOKUPS
SET LOOKUP_SUBTYPE = LOOKUP_TYPE, LOOKUP_TYPE = 'collectionSubtypes'
WHERE LOOKUP_TYPE IN ('randomClients','quarantineFacilities','lockdownZones')
;
------ END ------

-- The number of updated rows count should be same as the script (1)
------ START ------
UPDATE HC_GROUPS
SET COLLECTION_SUBTYPE_ID =
    CASE
        WHEN COLLECTION_TYPE_ID=1 THEN RANDOM_CLIENT_ID
        WHEN COLLECTION_TYPE_ID=3 THEN QUARANTINE_FACILITY_ID
        WHEN COLLECTION_TYPE_ID=4 THEN LOCKDOWN_ZONE_ID
    END
WHERE COLLECTION_TYPE_ID IN (1,3,4)
;
------ END ------

-- Rollback the fix script

------ START ------
UPDATE HC_LOOKUPS
SET LOOKUP_SUBTYPE=NULL
WHERE LOOKUP_TYPE='collectionTypes'
;
------ END ------

------ START ------
UPDATE HC_LOOKUPS
SET LOOKUP_TYPE=LOOKUP_SUBTYPE, LOOKUP_SUBTYPE = NULL
WHERE LOOKUP_TYPE='collectionSubtypes'
;
------ END ------

------ START ------
UPDATE HC_GROUPS
SET RANDOM_CLIENT_ID=COLLECTION_SUBTYPE_ID
WHERE COLLECTION_TYPE_ID=1

UPDATE HC_GROUPS
SET QUARANTINE_FACILITY_ID=COLLECTION_SUBTYPE_ID
WHERE COLLECTION_TYPE_ID=3

UPDATE HC_GROUPS
SET LOCKDOWN_ZONE_ID=COLLECTION_SUBTYPE_ID
WHERE COLLECTION_TYPE_ID=4

UPDATE HC_GROUPS
SET COLLECTION_SUBTYPE_ID = NULL
;
------ END ------

-------------------------------------------------
-- Fix of making originating positive person global on all collections
-------------------------------------------------

/*
-- Get total count for records before fix or after rollback
SELECT count(*)
FROM HC_GROUPS
WHERE COLLECTION_TYPE_ID=2 AND COLLECTION_SUBTYPE_ID IS NULL
-- Get total count for records after fix
SELECT count(*)
FROM HC_GROUPS
WHERE COLLECTION_TYPE_ID=1 AND COLLECTION_SUBTYPE_ID=2

------ START ------
-- 1) Prepare the temp subtype lookup solution
UPDATE HC_LOOKUPS
SET LOOKUP_TYPE='randomClients', LABEL_EN='Others - Originating Positive Patient Fix', LABEL_AR='Others - Originating Positive Patient Fix', CODE='12', RETIRED=1
WHERE ID=2
;
-- 2) Run the update script: set any collection type with value 'Originating Positive Patient' to value 'Random Client' and subtype value to the temp 'Others'
UPDATE HC_GROUPS
SET
    COLLECTION_TYPE_ID=1,
    COLLECTION_SUBTYPE_ID=2
WHERE COLLECTION_TYPE_ID=2
;
------ END ------

------ START ------
-- Rollback the fix script
UPDATE HC_LOOKUPS
SET LOOKUP_TYPE='collectionTypes', LABEL_EN='Originating Positive Patient', LABEL_AR='Originating Positive Patient', CODE=NULL, RETIRED=0
WHERE ID=2
;
UPDATE HC_GROUPS
SET
    COLLECTION_TYPE_ID=2,
    COLLECTION_SUBTYPE_ID=NULL
WHERE COLLECTION_TYPE_ID=1 AND COLLECTION_SUBTYPE_ID=2
;
------ END ------

------ START ------
-- 1) Prepare the temp subtype lookup solution
UPDATE HC_LOOKUPS
SET LOOKUP_TYPE='randomClients', LABEL_EN='Others - Originating Positive Patient Fix', LABEL_AR='Others - Originating Positive Patient Fix', CODE='12', RETIRED=1
WHERE ID=2
;
-- 2) Run the update script: set any collection type with value 'Originating Positive Patient' to value 'Random Client' and subtype value to the temp 'Others'
UPDATE HC_GROUPS
SET
    COLLECTION_TYPE_ID=1,
    COLLECTION_SUBTYPE_ID=2
WHERE COLLECTION_TYPE_ID=2
;
------ END ------

------ START ------
-- Rollback the fix script
UPDATE HC_LOOKUPS
SET LOOKUP_TYPE='collectionTypes', LABEL_EN='Originating Positive Patient', LABEL_AR='Originating Positive Patient', CODE=NULL, RETIRED=0
WHERE ID=2
;
UPDATE HC_GROUPS
SET
    COLLECTION_TYPE_ID=2,
    COLLECTION_SUBTYPE_ID=NULL
WHERE COLLECTION_TYPE_ID=1 AND COLLECTION_SUBTYPE_ID=2
;
------ END ------
*/

-- Get total count for records before fix or after rollback
SELECT count(*)
FROM HC_GROUPS, HC_LOOKUPS
WHERE
    HC_GROUPS.COLLECTION_TYPE_ID=2
AND
    HC_GROUPS.COLLECTION_SUBTYPE_ID IS NULL
AND
    HC_GROUPS.ORIGINATING_POSITIVE_PERSON_ID IS NOT NULL
AND
    HC_GROUPS.COLLECTION_TYPE_ID=HC_LOOKUPS.ID
AND
    HC_LOOKUPS.LABEL_EN='Originating Positive Patient'
;
-- Get total count for records after fix
SELECT count(*)
FROM HC_GROUPS, HC_LOOKUPS
WHERE
    HC_GROUPS.COLLECTION_TYPE_ID=2
AND
    HC_GROUPS.COLLECTION_SUBTYPE_ID IS NULL
AND
    HC_GROUPS.ORIGINATING_POSITIVE_PERSON_ID IS NOT NULL
AND
    HC_GROUPS.COLLECTION_TYPE_ID=HC_LOOKUPS.ID
AND
    HC_LOOKUPS.LABEL_EN='Others'
;

------ START ------
UPDATE HC_LOOKUPS
SET LABEL_EN='Others', LABEL_AR='Others'
WHERE ID=2
;
------ END ------

-- Rollback the fix script
------ START ------
UPDATE HC_LOOKUPS
SET LABEL_EN='Originating Positive Patient', LABEL_AR='Originating Positive Patient'
WHERE ID=2
;
------ END ------

-------------------------------------------------
-- Fix the issue that was happened by Izhar code on iOS App => by selecting the collection-type using fixed index way (Assuming that order never change). After we changed the lookups order the system start sending wrong values!
-------------------------------------------------

SELECT *
FROM HC_GROUPS
WHERE
    ORIGINATING_POSITIVE_PERSON_ID IS NOT NULL
AND
    COLLECTION_TYPE_ID=2
AND
    (RANDOM_CLIENT_ID IS NOT NULL OR QUARANTINE_FACILITY_ID IS NOT NULL OR LOCKDOWN_ZONE_ID IS NOT NULL)
;

SELECT SERIAL,ORIGINATING_POSITIVE_PERSON_ID,COLLECTION_TYPE_ID,COLLECTION_SUBTYPE_ID,LOCKDOWN_ZONE_ID,QUARANTINE_FACILITY_ID,RANDOM_CLIENT_ID
FROM HC_GROUPS
WHERE COLLECTION_TYPE_ID IN (1,3,4) AND ORIGINATING_POSITIVE_PERSON_ID IS NOT NULL AND COLLECTION_SUBTYPE_ID IS NULL
;

SELECT SERIAL,ORIGINATING_POSITIVE_PERSON_ID,COLLECTION_TYPE_ID,COLLECTION_SUBTYPE_ID,LOCKDOWN_ZONE_ID,QUARANTINE_FACILITY_ID,RANDOM_CLIENT_ID
FROM HC_GROUPS
WHERE COLLECTION_TYPE_ID IN (1,3,4) AND COLLECTION_SUBTYPE_ID IS NULL
;

-- Run in the below scripts order, otherwise will do wrong fix

------ START ------
-- (1)
UPDATE HC_GROUPS
SET RANDOM_CLIENT_ID=NULL, QUARANTINE_FACILITY_ID=NULL, LOCKDOWN_ZONE_ID=NULL
WHERE
    ORIGINATING_POSITIVE_PERSON_ID IS NOT NULL
AND
    COLLECTION_TYPE_ID=2
AND
    (RANDOM_CLIENT_ID IS NOT NULL OR QUARANTINE_FACILITY_ID IS NOT NULL OR LOCKDOWN_ZONE_ID IS NOT NULL)
;
------ END ------

------ START ------
-- (2)
UPDATE HC_GROUPS
SET ORIGINATING_POSITIVE_PERSON_ID=NULL, QUARANTINE_FACILITY_ID=NULL, LOCKDOWN_ZONE_ID=NULL
WHERE
    COLLECTION_TYPE_ID=1 AND RANDOM_CLIENT_ID IS NULL
AND
    COLLECTION_SUBTYPE_ID IS NULL
;
------ END ------

------ START ------
-- (3)
UPDATE HC_GROUPS
SET ORIGINATING_POSITIVE_PERSON_ID=NULL, RANDOM_CLIENT_ID=NULL, LOCKDOWN_ZONE_ID=NULL
WHERE
    COLLECTION_TYPE_ID=3 AND QUARANTINE_FACILITY_ID IS NULL
AND
    COLLECTION_SUBTYPE_ID IS NULL
;
------ END ------

------ START ------
-- (4)
UPDATE HC_GROUPS
SET ORIGINATING_POSITIVE_PERSON_ID=NULL, RANDOM_CLIENT_ID=NULL, QUARANTINE_FACILITY_ID=NULL
WHERE
    COLLECTION_TYPE_ID=4 AND LOCKDOWN_ZONE_ID IS NULL
AND
    COLLECTION_SUBTYPE_ID IS NULL
;
------ END ------

------ START ------
-- (5)
UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=2, RANDOM_CLIENT_ID=NULL, QUARANTINE_FACILITY_ID=NULL, LOCKDOWN_ZONE_ID=NULL
WHERE
    ORIGINATING_POSITIVE_PERSON_ID IS NOT NULL
AND
    COLLECTION_SUBTYPE_ID IS NULL
;
------ END ------

UPDATE HC_GROUPS
SET
    COLLECTION_TYPE_ID=2,
    RANDOM_CLIENT_ID=NULL,
    QUARANTINE_FACILITY_ID=NULL,
    LOCKDOWN_ZONE_ID=NULL
WHERE COLLECTION_TYPE_ID IN (1,3,4) AND COLLECTION_SUBTYPE_ID IS NULL AND ORIGINATING_POSITIVE_PERSON_ID IS NOT NULL


-------------------------------------------------
-- Set all PREGNANCY_CASE_ID in checkups table to Unknown for all females persons and No for Age less than or equal 6
-------------------------------------------------

SELECT HC_CHECKUPS.ID, HC_CHECKUPS.GROUP_SERIAL, HC_PERSONS.NAME_EN, HC_CHECKUPS.PREGNANCY_CASE_ID, HC_LOOKUPS.LABEL_EN
FROM HC_CHECKUPS, HC_PERSONS, HC_LOOKUPS
WHERE HC_CHECKUPS.PERSON_ID=HC_PERSONS.ID AND HC_PERSONS.GENDER_ID=HC_LOOKUPS.ID AND HC_LOOKUPS.ID=343
ORDER BY HC_CHECKUPS.GROUP_SERIAL ASC
;

SELECT COUNT(*)
FROM HC_CHECKUPS, HC_PERSONS, HC_LOOKUPS
WHERE HC_CHECKUPS.PERSON_ID=HC_PERSONS.ID AND HC_PERSONS.GENDER_ID=HC_LOOKUPS.ID AND HC_LOOKUPS.ID=343
;

SELECT count(*) FROM HC_CHECKUPS;
SELECT count(*) FROM HC_CHECKUPS WHERE PREGNANCY_CASE_ID IS NOT NULL;
SELECT count(*) FROM HC_CHECKUPS WHERE PREGNANCY_CASE_ID IS NULL;
;

SELECT COUNT(*)
FROM HC_PERSONS
WHERE HC_PERSONS.GENDER_ID IS NULL
;

SELECT ID,NAME1AR,DATE_OF_BIRTH,(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DATE_OF_BIRTH)) AS FEMALE_AGE
FROM HC_PERSONS
WHERE GENDER_ID=343
AND (EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DATE_OF_BIRTH)) <= 6
ORDER BY FEMALE_AGE

SELECT HC_PERSONS.NAME1AR AS NAME, HC_CHECKUPS.PREGNANCY_CASE_ID AS PREGNANCY_CASE_ID, HC_PERSONS.DATE_OF_BIRTH AS DATE_OF_BIRTH, (EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DATE_OF_BIRTH)) AS FEMALE_AGE
FROM HC_CHECKUPS, HC_PERSONS, HC_LOOKUPS
WHERE HC_CHECKUPS.PERSON_ID=HC_PERSONS.ID AND HC_PERSONS.GENDER_ID=HC_LOOKUPS.ID AND HC_LOOKUPS.ID=343
ORDER BY FEMALE_AGE

------ START ------
-- UPDATE (
--         SELECT HC_CHECKUPS.PREGNANCY_CASE_ID AS PREGNANCY_CASE_ID
--         FROM HC_CHECKUPS, HC_PERSONS, HC_LOOKUPS
--         WHERE HC_CHECKUPS.PERSON_ID=HC_PERSONS.ID AND HC_PERSONS.GENDER_ID=HC_LOOKUPS.ID AND HC_LOOKUPS.ID=343
--         ) FEMALE_CHECKUPS
-- SET FEMALE_CHECKUPS.PREGNANCY_CASE_ID=404
-- ;

UPDATE (
        SELECT HC_CHECKUPS.PREGNANCY_CASE_ID AS PREGNANCY_CASE_ID, (EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DATE_OF_BIRTH)) AS FEMALE_AGE
        FROM HC_CHECKUPS, HC_PERSONS, HC_LOOKUPS
        WHERE HC_CHECKUPS.PERSON_ID=HC_PERSONS.ID AND HC_PERSONS.GENDER_ID=HC_LOOKUPS.ID AND HC_LOOKUPS.ID=343
        ) FEMALE_CHECKUPS
SET FEMALE_CHECKUPS.PREGNANCY_CASE_ID =
    CASE
        WHEN FEMALE_AGE <= 6 THEN 405
        ELSE 404
    END
;
------ END ------

-- Rollback the fix script

------ START ------
UPDATE HC_CHECKUPS
SET PREGNANCY_CASE_ID=NULL
;
------ END ------

-------------------------------------------------
-- Set all MARITAL_STATUS_ID in persons table to Not Applicable for all persons, with age <= 15
-------------------------------------------------

SELECT ID,NAME1AR,MARITAL_STATUS_ID,DATE_OF_BIRTH,(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DATE_OF_BIRTH)) AS AGE
FROM HC_PERSONS
ORDER BY AGE

------ START ------
UPDATE HC_PERSONS
SET MARITAL_STATUS_ID = 423
WHERE (EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DATE_OF_BIRTH)) <= 15
;
------ END ------

-------------------------------------------------
-- Set Accessible Collection Types
-------------------------------------------------

SELECT ID, LABEL_EN, ACCESSIBLE
FROM HC_LOOKUPS
WHERE LOOKUP_TYPE='endUserTypes'
;

------ START ------
-- UPDATE HC_LOOKUPS
-- SET ACCESSIBLE = '1;2;3;4;5;73'
-- WHERE
--     LOOKUP_TYPE='endUserTypes'
-- AND
--     ID IN (74,75,340,362,363,364,365,411,412,415)
-- ;
------ END ------

-------------------------------------------------
-- Fix types and subtypes codes
-------------------------------------------------

-- SELECT *
-- FROM HC_LOOKUPS
-- WHERE
--     LOOKUP_TYPE='collectionTypes'
-- OR
--     LOOKUP_TYPE='collectionSubtypes'
-- ORDER BY LOOKUP_TYPE, CODE, ID
-- ;

-- SELECT *
-- FROM HC_LOOKUPS
-- WHERE
--     LOOKUP_TYPE='collectionTypes'
-- OR
--     LOOKUP_TYPE='collectionSubtypes'
-- ORDER BY CODE
-- ;

-- -- Rollback the fix script
--
-- ------ START ------
-- UPDATE HC_LOOKUPS
-- SET CODE=NULL
-- WHERE
--     LOOKUP_TYPE='collectionTypes'
-- OR
--     (LOOKUP_TYPE='collectionSubtypes' AND LOOKUP_SUBTYPE <> 'randomClients')
-- ;

-- UPDATE HC_LOOKUPS
-- SET CODE=SUBSTR(CODE, 3)
-- WHERE
--     LOOKUP_TYPE='collectionSubtypes'
-- AND
--     LOOKUP_SUBTYPE = 'randomClients'
-- ;
-- ------ END ------

-------------------------------------------------
-- Build new types and subtypes
-------------------------------------------------

SELECT *
FROM HC_LOOKUPS
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'PHCC - %'

SELECT *
FROM HC_LOOKUPS
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'ED - %'

-----------------

-----------------
UPDATE HC_LOOKUPS
SET
    LOOKUP_TYPE='collectionTypes',
    LOOKUP_SUBTYPE=NULL,
    LABEL_EN='Seroepidemiology',
    LABEL_AR='Seroepidemiology'
WHERE ID=413
-----------------

-----------------
UPDATE HC_LOOKUPS
SET
    LOOKUP_TYPE='collectionTypes',
    LOOKUP_SUBTYPE='phcc'
WHERE ID=20
;

UPDATE HC_LOOKUPS
SET
    LOOKUP_SUBTYPE='phcc'
--    ,
--    LABEL_EN=SUBSTR(LABEL_EN, 8),
--    LABEL_AR=SUBSTR(LABEL_AR, 8)
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'PHCC - %'
;
-----------------

-----------------
UPDATE HC_LOOKUPS
SET
    LOOKUP_TYPE='collectionTypes',
    LOOKUP_SUBTYPE='emergency',
    LABEL_EN='Emergency Departments',
    LABEL_AR='Emergency Departments'
WHERE ID=21
;

UPDATE HC_LOOKUPS
SET
    LOOKUP_SUBTYPE='emergency'
--    ,
--    LABEL_EN=SUBSTR(LABEL_EN, 6),
--    LABEL_AR=SUBSTR(LABEL_AR, 6)
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'ED - %'
;
-----------------

-----------------
Insert into HC_LOOKUPS (ID,LOOKUP_TYPE,LOOKUP_SUBTYPE,SELECTABLE,SORT_ORDER,CODE,LABEL_AR,LABEL_EN,RETIRED,RETIRED_AT,RETIRED_BY,MODIFIED_AT,CREATED_AT) values (407,'collectionTypes','sports',1,0,null,'Sports','Sports',0,null,null,null,null);

UPDATE HC_LOOKUPS
SET
    LOOKUP_SUBTYPE='sports'
--    ,
--    LABEL_EN=SUBSTR(LABEL_EN, 10),
--    LABEL_AR=SUBSTR(LABEL_AR, 10)
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'Sports - %'
;
-----------------

-----------------
Insert into HC_LOOKUPS (ID,LOOKUP_TYPE,LOOKUP_SUBTYPE,SELECTABLE,SORT_ORDER,CODE,LABEL_AR,LABEL_EN,RETIRED,RETIRED_AT,RETIRED_BY,MODIFIED_AT,CREATED_AT) values (427,'collectionTypes','qrc',1,0,null,'QRC','QRC',0,null,null,null,null);

UPDATE HC_LOOKUPS
SET
    LOOKUP_SUBTYPE='qrc'
--    ,
--    LABEL_EN=SUBSTR(LABEL_EN, 7),
--    LABEL_AR=SUBSTR(LABEL_AR, 7)
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'QRC - %'
;
-----------------

-----------------
Insert into HC_LOOKUPS (ID,LOOKUP_TYPE,LOOKUP_SUBTYPE,SELECTABLE,SORT_ORDER,CODE,LABEL_AR,LABEL_EN,RETIRED,RETIRED_AT,RETIRED_BY,MODIFIED_AT,CREATED_AT) values (428,'collectionTypes','survey',1,0,null,'Survey','Survey',0,null,null,null,null);

UPDATE HC_LOOKUPS
SET
    LOOKUP_SUBTYPE='survey'
--    ,
--    LABEL_EN=SUBSTR(LABEL_EN, 10),
--    LABEL_AR=SUBSTR(LABEL_AR, 10)
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'Survey - %'
;

UPDATE HC_LOOKUPS
SET
    LOOKUP_SUBTYPE='survey'
--    ,
--    LABEL_EN='Workplace',
--    LABEL_AR='Workplace'
WHERE ID=347
-----------------

-----------------
Insert into HC_LOOKUPS (ID,LOOKUP_TYPE,LOOKUP_SUBTYPE,SELECTABLE,SORT_ORDER,CODE,LABEL_AR,LABEL_EN,RETIRED,RETIRED_AT,RETIRED_BY,MODIFIED_AT,CREATED_AT) values (429,'collectionTypes','driveThru',1,0,null,'Drive Thru','Drive Thru',0,null,null,null,null);

UPDATE HC_LOOKUPS
SET
    LOOKUP_SUBTYPE='driveThru'
--    ,
--    LABEL_EN=SUBSTR(LABEL_EN, 14),
--    LABEL_AR=SUBSTR(LABEL_AR, 14)
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'Drive Thru - %'
;
-----------------

-----------------
Insert into HC_LOOKUPS (ID,LOOKUP_TYPE,LOOKUP_SUBTYPE,SELECTABLE,SORT_ORDER,CODE,LABEL_AR,LABEL_EN,RETIRED,RETIRED_AT,RETIRED_BY,MODIFIED_AT,CREATED_AT) values (430,'collectionTypes','hgh',1,0,null,'HGH','HGH',0,null,null,null,null);

UPDATE HC_LOOKUPS
SET
    LOOKUP_SUBTYPE='hgh'
--    ,
--    LABEL_EN=SUBSTR(LABEL_EN, 7),
--    LABEL_AR=SUBSTR(LABEL_AR, 7)
WHERE LOOKUP_TYPE='collectionSubtypes'
AND LABEL_EN LIKE 'HGH - %'
;
-----------------

----------------------------------

-----------------
SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (358, 359, 357)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=427
WHERE COLLECTION_SUBTYPE_ID in (358, 359, 357)
-----------------

-----------------
SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (346, 345, 344)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=429
WHERE COLLECTION_SUBTYPE_ID in (346, 345, 344)
-----------------

-----------------
SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (395,391,392,396,393,397,398,394,399,400,401,402,403)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=407
WHERE COLLECTION_SUBTYPE_ID in (395,391,392,396,393,397,398,394,399,400,401,402,403)
-----------------

-----------------
SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (425, 426, 347)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=428
WHERE COLLECTION_SUBTYPE_ID in (425, 426, 347)
-----------------

-----------------
SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (21)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=21, COLLECTION_SUBTYPE_ID=353
WHERE COLLECTION_SUBTYPE_ID in (21)

---

SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (354,355,353,356)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=21
WHERE COLLECTION_SUBTYPE_ID in (354,355,353,356)
-----------------

-----------------
SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (20)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=20, COLLECTION_SUBTYPE_ID=379
WHERE COLLECTION_SUBTYPE_ID in (20)

---

SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (371,384,373,386,390,385,388,380,376,382,378,383,381,369,349,368,389,377,370,351,374,387,350,372,379,348,375)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=20
WHERE COLLECTION_SUBTYPE_ID in (371,384,373,386,390,385,388,380,376,382,378,383,381,369,349,368,389,377,370,351,374,387,350,372,379,348,375)
-----------------

-----------------
SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (410,409)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=430
WHERE COLLECTION_SUBTYPE_ID in (410,409)
-----------------

-----------------
SELECT COLLECTION_TYPE_ID, COLLECTION_SUBTYPE_ID
FROM HC_GROUPS
WHERE COLLECTION_SUBTYPE_ID in (413)

UPDATE HC_GROUPS
SET COLLECTION_TYPE_ID=413, COLLECTION_SUBTYPE_ID=NULL
WHERE COLLECTION_SUBTYPE_ID in (413)
-----------------

-------------------------------------------------
-- Fill all mandatory new columns with default values
-------------------------------------------------

SELECT REASON_ID
FROM HC_GROUPS
;

UPDATE HC_GROUPS
SET REASON_ID=420
;

SELECT count(*)
from hc_checkups
;

UPDATE HC_CHECKUPS
SET PATIENT_MEDICAL_VITAL_SIGNS='CoronaSymptoms=Yes'

-------------------------------------------------
--
-------------------------------------------------
