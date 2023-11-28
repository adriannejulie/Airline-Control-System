DROP DATABASE IF EXISTS TP480;
CREATE DATABASE TP480;
USE TP480;

ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';

DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE EMPLOYEE(
    ID_NO               INT,
    PRIMARY KEY(ID_NO)
);

DROP TABLE IF EXISTS AIRCRAFT;
CREATE TABLE AIRCRAFT(
    AIRCRAFT_ID         INT,
    PRIMARY KEY(AIRCRAFT_ID)
);

DROP TABLE IF EXISTS AIRPORT;
CREATE TABLE AIRPORT(
    AIRPORT_CODE        VARCHAR(10),
    AIRPORT_NAME        VARCHAR(100),
    PRIMARY KEY(AIRPORT_CODE)
);

DROP TABLE IF EXISTS FLIGHT;
CREATE TABLE FLIGHT(
    FLIGHT_NUMBER       INT,
    DESTINATION         VARCHAR(40),
    ORIGIN              VARCHAR(40),
    TIME_DEPARTURE      DATETIME,
    AIRCRAFT_TYPE       INT,
    PILOT1              INT,
    PILOT2              INT,
    FLIGHT_ATTENDANT1   INT,
    FLIGHT_ATTENDANT2   INT,
    FLIGHT_ATTENDANT3   INT,
    FLIGHT_ATTENDANT4   INT,

    PRIMARY KEY(FLIGHT_NUMBER),
    FOREIGN KEY(AIRCRAFT_TYPE) REFERENCES AIRCRAFT(AIRCRAFT_ID),
    FOREIGN KEY(DESTINATION) REFERENCES AIRPORT(AIRPORT_CODE),
    FOREIGN KEY(PILOT1) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(PILOT2) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(FLIGHT_ATTENDANT1) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(FLIGHT_ATTENDANT2) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(FLIGHT_ATTENDANT3) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(FLIGHT_ATTENDANT4) REFERENCES EMPLOYEE(ID_NO)
);

DROP TABLE IF EXISTS AIRLINE_AGENT;
CREATE TABLE AIRLINE_AGENT(
    AGENT_ID            INT,
    NAME                VARCHAR(50), 
    PRIMARY KEY(AGENT_ID),
    FOREIGN KEY(AGENT_ID) REFERENCES EMPLOYEE(ID_NO)
);

DROP TABLE IF EXISTS CREW;
CREATE TABLE CREW(
    CREW_ID             INT,
    NAME                VARCHAR(50),
    PRIMARY KEY(CREW_ID),
    FOREIGN KEY(CREW_ID) REFERENCES EMPLOYEE(ID_NO)
);

DROP TABLE IF EXISTS ADMIN_STAFF;
CREATE TABLE ADMIN_STAFF(
    ADMIN_ID            INT,
    NAME                VARCHAR(50),
    PRIMARY KEY(ADMIN_ID),
    FOREIGN KEY(ADMIN_ID) REFERENCES EMPLOYEE(ID_NO)
);

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS(
    ID_NO               INT,
    USERNAME			VARCHAR(40),
    PASSWORD			VARCHAR(40),
    NAME                VARCHAR(40),
    BIRTH               DATE,
    PHONE_NUMBER        VARCHAR(15),
    CARD_NUMBER         VARCHAR(16),
    EMAIL               VARCHAR(30),
    MEMBERSHIP_ID       INT, /* MEMBERSHIP_ID == 0 if a non-member*/
    PRIMARY KEY(ID_NO)
);

DROP TABLE IF EXISTS CUSTOMERS;
CREATE TABLE CUSTOMERS(
    CUSTOMER_ID         INT,
    PRIMARY KEY(CUSTOMER_ID),
    FOREIGN KEY(CUSTOMER_ID) REFERENCES USERS(ID_NO)
);


DROP TABLE IF EXISTS SEAT; /* Number of seats: 80*/
/*Seat Numbers - Business: 1 - 10, Comfort: 11 - 25, Ordinary: 26 - 80*/
CREATE TABLE SEAT(
    SEAT_TYPE           VARCHAR(40),
    COST                INT,
    PRIMARY KEY(SEAT_TYPE)
);

DROP TABLE IF EXISTS BOOKED;
CREATE TABLE BOOKED(
	USER_ID				INT,
	FLIGHT_NUMBER		INT,
    SEAT_TYPE			VARCHAR(20),
    SEAT_NUMBER			INT,
    INSURANCE_STATUS    INT, /* INSURANCE_STATUS == 1 if the customer has paid for insurance, otehrwise 0*/
    PRIMARY KEY(USER_ID)
);
