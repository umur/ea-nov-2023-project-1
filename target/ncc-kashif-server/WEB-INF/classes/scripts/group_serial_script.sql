-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

-------------------------------------------------
CREATE SEQUENCE  "SERIAL_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
-------------------------------------------------
CREATE TABLE "HC_JOB_LOG" (
    "TS"        TIMESTAMP(6) DEFAULT systimestamp,
    "MESSAGE"   VARCHAR2(255 BYTE),
    PRIMARY KEY ( "TS" )
);
-------------------------------------------------
CREATE OR REPLACE PROCEDURE HC_JOB_LOGGER (
    V_MESSAGE VARCHAR2
) IS
BEGIN
    INSERT INTO HC_JOB_LOG ( MESSAGE ) VALUES ( V_MESSAGE );

    COMMIT;
END;
-------------------------------------------------
CREATE OR REPLACE PROCEDURE SERIAL_SEQ_RESET_PROCEDURE
    AUTHID CURRENT_USER
AS
    V_CODE   NUMBER;
    V_ERRM   VARCHAR2(64);
BEGIN
    HC_JOB_LOGGER('Starting job for procedure SERIAL_SEQ_RESET_PROCEDURE');
    EXECUTE IMMEDIATE 'DROP SEQUENCE SERIAL_SEQ';
    EXECUTE IMMEDIATE 'CREATE SEQUENCE SERIAL_SEQ'
                      || ' MINVALUE 1 '
                      || ' MAXVALUE 9999999999999999999999999999 '
                      || ' INCREMENT BY 1 '
                      || ' START WITH 1 '
                      || ' NOCACHE';

    HC_JOB_LOGGER('Job success for procedure SERIAL_SEQ_RESET_PROCEDURE.');
EXCEPTION
    WHEN OTHERS THEN
        V_CODE := SQLCODE;
        V_ERRM := SUBSTR(SQLERRM, 1, 64);
        HC_JOB_LOGGER(V_CODE
                      || ' '
                      || V_ERRM);
END SERIAL_SEQ_RESET_PROCEDURE;
-------------------------------------------------
BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
            JOB_NAME => '"SERIAL_SEQ_RESET_JOB"',
            JOB_TYPE => 'STORED_PROCEDURE',
            JOB_ACTION => 'SERIAL_SEQ_RESET_PROCEDURE',
            NUMBER_OF_ARGUMENTS => 0,
            START_DATE => TO_TIMESTAMP_TZ('2020-04-20 00:00:00.000000000 ASIA/QATAR','YYYY-MM-DD HH24:MI:SS.FF TZR'),
            REPEAT_INTERVAL => 'FREQ=DAILY;BYHOUR=23;BYMINUTE=59',
            END_DATE => NULL,
            ENABLED => FALSE,
            AUTO_DROP => FALSE,
            COMMENTS => 'Serial sequence daily reset job.');

    DBMS_SCHEDULER.SET_ATTRIBUTE(
             NAME => '"SERIAL_SEQ_RESET_JOB"',
             ATTRIBUTE => 'store_output', VALUE => TRUE);
    DBMS_SCHEDULER.SET_ATTRIBUTE(
             NAME => '"SERIAL_SEQ_RESET_JOB"',
             ATTRIBUTE => 'logging_level', VALUE => DBMS_SCHEDULER.LOGGING_OFF);

    DBMS_SCHEDULER.ENABLE(
             NAME => '"SERIAL_SEQ_RESET_JOB"');
END;
-------------------------------------------------
