DROP DATABASE IF EXISTS TP480;
CREATE DATABASE TP480;
USE TP480;

ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';


DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE EMPLOYEE(
    ID_NO               INT,
    PASSWORD			VARCHAR(30),
    PRIMARY KEY(ID_NO)
); 

INSERT INTO EMPLOYEE(ID_NO, PASSWORD)
VALUE
(1000, 'agent'), (1001, 'agent'), (1002, 'agent'), (1003, 'agent'), (1004, 'agent'), (1005, 'agent'), /* ID_NOs for Airline Agents*/
(2000, 'crew'), (2001, 'crew'), (2002, 'crew'), (2003, 'crew'), (2004, 'crew'), (2005, 'crew'), /* ID_NOs for Pilots*/
(2006, 'crew'), (2007, 'crew'), (2008, 'crew'), (2009, 'crew'), (2010, 'crew'), (2011, 'crew'), /* ID_NOs for Pilots*/

(3000, 'crew'), (3001, 'crew'), (3002, 'crew'), (3003, 'crew'), (3004, 'crew'), /* ID_NOs for Flight Attendants*/
(3005, 'crew'), (3006, 'crew'), (3007, 'crew'), (3008, 'crew'), (3009, 'crew'), /* ID_NOs for Flight Attendants*/
(3010, 'crew'), (3011, 'crew'), (3012, 'crew'), (3013, 'crew'), (3014, 'crew'), /* ID_NOs for Flight Attendants*/

(3015, 'crew'), (3016, 'crew'), (3017, 'crew'), (3018, 'crew'), (3019, 'crew'), /* ID_NOs for Flight Attendants*/
(3020, 'crew'), (3021, 'crew'), (3022, 'crew'), (3023, 'crew'), (3024, 'crew'), /* ID_NOs for Flight Attendants*/
(3025, 'crew'), (3026, 'crew'), (3027, 'crew'), (3028, 'crew'), (3029, 'crew'), /* ID_NOs for Flight Attendants*/

(3030, 'crew'), (3031, 'crew'), (3032, 'crew'), (3033, 'crew'), (3034, 'crew'), /* ID_NOs for Flight Attendants*/
(3035, 'crew'), (3036, 'crew'), (3037, 'crew'), (3038, 'crew'), (3039, 'crew'), /* ID_NOs for Flight Attendants*/
(3040, 'crew'), (3041, 'crew'), (3042, 'crew'), (3043, 'crew'), (3044, 'crew'), /* ID_NOs for Flight Attendants*/

(4000, 'admin'), (4001, 'admin'), (4002, 'admin'), (4003, 'admin'), (4004, 'admin'), (4005, 'admin'); /* ID_NOs for System Admins*/

DROP TABLE IF EXISTS AIRCRAFT;
CREATE TABLE AIRCRAFT(
    AIRCRAFT_ID        INT,
    PRIMARY KEY(AIRCRAFT_ID)
);

INSERT INTO AIRCRAFT(AIRCRAFT_ID) /* Assume all aircrafts have 80 seats*/
VALUES
(01), (02), (03), (04), (05), (06), (07), (08), (09), (10), (11), (12),
(13), (14), (15), (16), (17), (18), (19), (20), (21), (22), (23), (24);

DROP TABLE IF EXISTS AIRPORT;
CREATE TABLE AIRPORT(
    AIRPORT_CODE        VARCHAR(10),
    AIRPORT_NAME        VARCHAR(100),
    PRIMARY KEY(AIRPORT_CODE)
);

INSERT INTO AIRPORT(AIRPORT_CODE, AIRPORT_NAME)
VALUES
('CYYZ', 'Toronto Lester B. Pearson International Airport'),
('CYVR', 'Vancouver International Airport'),
('CYUL', 'Montreal / Pierre Elliott Trudeau International Airport'),
('CYYC', 'Calgary International Airport'),
('CYOW', 'Ottawa Macdonald-Cartier International Airport'),
('CYTZ', 'Billy Bishop Toronto City Centre Airport'),
('CYEG', 'Edmonton International Airport'),
('CYWG', 'Winnipeg / James Armstrong Richardson International Airport');

DROP TABLE IF EXISTS FLIGHT;
CREATE TABLE FLIGHT(
    FLIGHT_NUMBER       INT,
    DESTINATION         VARCHAR(40),
    ORIGIN              VARCHAR(40),
    TIME_DEPARTURE      DATETIME,
    AIRCRAFT_TYPE       INT,
    PILOT1              INT,
    FLIGHT_ATTENDANT1   INT,
    FLIGHT_ATTENDANT2   INT,
    FLIGHT_ATTENDANT3   INT,
    FLIGHT_ATTENDANT4   INT,

    PRIMARY KEY(FLIGHT_NUMBER),
    FOREIGN KEY(AIRCRAFT_TYPE) REFERENCES AIRCRAFT(AIRCRAFT_ID),
    FOREIGN KEY(DESTINATION) REFERENCES AIRPORT(AIRPORT_CODE),
    FOREIGN KEY(PILOT1) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(FLIGHT_ATTENDANT1) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(FLIGHT_ATTENDANT2) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(FLIGHT_ATTENDANT3) REFERENCES EMPLOYEE(ID_NO),
    FOREIGN KEY(FLIGHT_ATTENDANT4) REFERENCES EMPLOYEE(ID_NO)
);

INSERT INTO FLIGHT(FLIGHT_NUMBER, ORIGIN, DESTINATION, TIME_DEPARTURE, AIRCRAFT_TYPE, PILOT1, FLIGHT_ATTENDANT1, FLIGHT_ATTENDANT2, FLIGHT_ATTENDANT3, FLIGHT_ATTENDANT4)
VALUES
(01, 'CYYZ','CYVR','2023-12-20 00:00:05', 01, 2000, 3000, 3001, 3002, 3003), /*Flights from Toronto to Vancouver*/
(02, 'CYYZ','CYVR','2023-12-20 12:00:05', 02, 2001, 3004, 3005, 3006, 3007),
(03, 'CYYZ','CYVR','2023-12-23 00:00:05', 03, 2002, 3008, 3009, 3010, 3011),
(04, 'CYYZ','CYVR','2023-12-23 12:00:05', 04, 2003, 3012, 3013, 3014, 3015),

(05, 'CYUL','CYYC','2023-12-25 00:00:05', 05, 2000, 3000, 3001, 3002, 3003), /*Flights from Montreal to Calgary*/
(06, 'CYUL','CYYC','2023-12-25 12:00:05', 06, 2001, 3004, 3005, 3006, 3007),
(07, 'CYUL','CYYC','2023-12-27 00:00:05', 07, 2002, 3008, 3009, 3010, 3011),
(08, 'CYUL','CYYC','2023-12-27 12:00:05', 08, 2003, 3012, 3013, 3014, 3015),

(09, 'CYOW','CYTZ','2023-12-29 00:00:05', 09, 2000, 3000, 3001, 3002, 3003), /*Flights from Ottowa to Toronto*/
(10, 'CYOW','CYTZ','2023-12-29 12:00:05', 10, 2001, 3004, 3005, 3006, 3007),
(11, 'CYOW','CYTZ','2023-12-31 00:00:05', 11, 2002, 3008, 3009, 3010, 3011),
(12, 'CYOW','CYTZ','2023-12-31 12:00:05', 12, 2003, 3012, 3013, 3014, 3015),

(13, 'CYEG','CYWG','2023-12-20 00:00:05', 13, 2004, 3016, 3017, 3018, 3019), /*Flights from Edmonton to Winnepeg*/
(14, 'CYEG','CYWG','2023-12-20 12:00:05', 14, 2005, 3020, 3021, 3022, 3023),
(15, 'CYEG','CYWG','2023-12-23 00:00:05', 15, 2006, 3024, 3025, 3026, 3027),
(16, 'CYEG','CYWG','2023-12-23 12:00:05', 16, 2007, 3028, 3029, 3030, 3031),

(17, 'CYYZ','CYYC','2023-12-25 00:00:05', 17, 2004, 3016, 3017, 3018, 3019), /*Flights from Toronto to Calgary*/
(18, 'CYYZ','CYYC','2023-12-25 12:00:05', 18, 2005, 3020, 3021, 3022, 3023),
(19, 'CYYZ','CYYC','2023-12-27 00:00:05', 19, 2006, 3024, 3025, 3026, 3027),
(20, 'CYYZ','CYYC','2023-12-27 12:00:05', 20, 2007, 3028, 3029, 3030, 3031),

(21, 'CYYC','CYVR','2023-12-29 00:00:05', 21, 2004, 3016, 3017, 3018, 3019), /*Flights from Calgary to Vancouver*/
(22, 'CYYC','CYVR','2023-12-29 12:00:05', 22, 2005, 3020, 3021, 3022, 3023),
(23, 'CYYC','CYVR','2023-12-31 00:00:05', 23, 2006, 3024, 3025, 3026, 3027),
(24, 'CYYC','CYVR','2023-12-31 12:00:05', 24, 2007, 3028, 3029, 3030, 3031);


DROP TABLE  IF EXISTS AIRLINE_AGENT;
CREATE TABLE AIRLINE_AGENT(
    AGENT_ID            INT,
    NAME				VARCHAR(50),
    PRIMARY KEY(AGENT_ID),
    FOREIGN KEY(AGENT_ID) REFERENCES EMPLOYEE(ID_NO)
);

INSERT INTO AIRLINE_AGENT(AGENT_ID, NAME)
VALUES
(1000, 'John Doe'), (1001, 'Jane Smith'), (1002, 'Michael Johnson'), (1003, 'Emily Davis'), (1004, 'David Brown'), (1005, 'Sarah Miller');


DROP TABLE IF EXISTS CREW;
CREATE TABLE CREW(
    CREW_ID             INT,
    NAME				VARCHAR(50),
    PRIMARY KEY(CREW_ID),
    FOREIGN KEY(CREW_ID) REFERENCES EMPLOYEE(ID_NO)
);

INSERT INTO CREW(CREW_ID, NAME)
VALUES
(2000, 'John Smith'), (2001, 'Emily Johnson'), (2002, 'Michael Davis'), (2003, 'Sarah Wilson'), (2004, 'Christopher Taylor'), (2005, 'Jessica Brown'), /* ID_NOs for Pilots*/
(2006, 'David Miller'), (2007, 'Amanda Garcia'), (2008, 'Brian Robinson'), (2009, 'Megan Martinez'), (2010, 'Matthew Anderson'), (2011, 'Olivia Hernandez'), /* ID_NOs for Pilots*/

(3000, 'Daniel White'), (3001, 'Lauren Moore'), (3002, 'Kevin Carter'), (3003, 'Ashley Clark'), (3004, 'Jonathan Hall'), /* ID_NOs for Flight Attendants*/
(3005, 'Jennifer Lee'), (3006, 'Ryan Rodriguez'), (3007, 'Stephanie Turner'), (3008, 'Brandon Mitchell'), (3009, 'Taylor Adams'), /* ID_NOs for Flight Attendants*/
(3010, 'Nicole Lewis'), (3011, 'Jacob Scott'), (3012, 'Kayla King'), (3013, 'Tyler Wright'), (3014, 'Haley Hill'), /* ID_NOs for Flight Attendants*/

(3015, 'Cameron Baker'), (3016, 'Lily Nelson'), (3017, 'Jordan Walker'), (3018, 'Emma Evans'), (3019, 'Alexis Martinez'), /* ID_NOs for Flight Attendants*/
(3020, 'Ethan Garcia'), (3021, 'Natalie Moore'), (3022, 'Logan Davis'), (3023, 'Madison Wilson'), (3024, 'Caleb Thompson'), /* ID_NOs for Flight Attendants*/
(3025, 'Grace White'), (3026, 'Austin Harris'), (3027, 'Victoria Allen'), (3028, 'Dylan Baker'), (3029, 'Sophia Carter'), /* ID_NOs for Flight Attendants*/

(3030, 'Connor Robinson'), (3031, 'Avery Turner'), (3032, 'Mia Mitchell'), (3033, 'Noah Smith'), (3034, 'Ava Johnson'), /* ID_NOs for Flight Attendants*/
(3035, 'Evan Anderson'), (3036, 'Zoe Hall'), (3037, 'Christopher Taylor'), (3038, 'Ella Hernandez'), (3039, 'Jack Adams'), /* ID_NOs for Flight Attendants*/
(3040, 'Mason King'), (3041, 'Sydney Scott'), (3042, 'Harrison Wright'), (3043, 'Alyssa Hill'), (3044, 'Luke Clark'); /* ID_NOs for Flight Attendants*/



DROP TABLE IF EXISTS ADMIN_STAFF;
CREATE TABLE ADMIN_STAFF(
    ADMIN_ID            INT,
    NAME				VARCHAR(50),
    PRIMARY KEY(ADMIN_ID),
    FOREIGN KEY(ADMIN_ID) REFERENCES EMPLOYEE(ID_NO)
);

INSERT INTO ADMIN_STAFF(ADMIN_ID, NAME)
VALUES
(4000, 'Matthew King'), (4001, 'Abigail Scott'), (4002, 'Ethan Adams'), (4003, 'Chloe Lewis'), (4004, 'Jackson Turner'), (4005, 'Lily Bennett');


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
INSERT INTO USERS(ID_NO, USERNAME, PASSWORD, NAME, BIRTH, PHONE_NUMBER, CARD_NUMBER, EMAIL, MEMBERSHIP_ID)
VALUES
(0001, 'GeorgeUsername', 'GeorgePassword', 'George Gower', '2000-12-13', '403-123-1234', '4111111111111111', 'GeorgeGower@example.com',0001),
(0002, 'MichielUsername', 'MichielPassword', 'Michiel Sittow', '2001-11-03', '467-321-2345', '5431111111111111', 'MichielSittow@example.com', 0000),
(0003, 'LucasUsername', 'LucasPassword', 'Lucas de Heere', '1999-09-11', '403-678-3875', '371111111111114', 'LucasdeHeere@example.com',0002),
(0004, 'PabloUsername', 'PabloPassword', 'Pablo Picasso', '1984-02-01', '403-098-0683', '36000000000008', 'PabloPicasso@example.com', 0000),
(0005, 'GuillaumeUsername', 'GuillaumePassword', 'Guillaume Coustou', '2003-06-22', '587-123-2356', '123455678901234', 'GuillaumeCoustou@example.com', 0003),
(0006, 'BobbyUsername', 'BobbyPassword', 'Bobby Brown', '1999-12-22', '403-753-2759', '6574839201928374', 'BobbyBrown@example.com', 0004),
(0007, 'YukiUsername', 'YukiPassword', 'Yuki Ishikawa', '1995-12-11', '467-192-1284', '1223344556677889', 'YukiIshikawa@example.com',0005),
(0008, 'HersheyUsername', 'HersheyPassword', 'Hershey Chips', '2003-05-22', '416-746-0298', '1019181716151413', 'HersheyChips@example.com',0006),
(0009, 'TimUsername', 'TimPassword', 'Tim Hortons', '1965-04-21', '587-953-9923', '6564747384950628', 'TimHortons@example.com',0000),
(0010, 'CalUsername', 'CalPassword', 'Cal Garyian', '1974-06-23', '403-908-0085', '3436373829938465', 'CalGaryian@example.com',0007),
(0011, 'EdUsername', 'EdPassword', 'Ed Montonian', '1982-08-20', '587-002-9352', '2349765492738456', 'EdMontonian@example.com', 0000),
(0012, 'CareyUsername', 'CareyPassword', 'Carey Beary', '2000-09-18', '403-034-8105', '0182736475839284', 'CareyBeary@example.com', 0000),
(0013, 'NickUsername', 'NickPassword', 'Nick Ey', '1965-10-16', '416-652-8234', '6193956244768703', 'NickEy@example.com', 0008),
(0014, 'StephanoUsername', 'StephanoPassword', 'Stephano Montoya', '1945-10-15', '467-564-8723', '1253680986384572', 'StephanoMontoya@example.com', 0000),
(0015, 'SueUsername', 'SuePassword', 'Sue Perstore', '1987-12-01', '467-079-7352', '9837483927463718', 'SuePerstore@example.com',  0009),
(0016, 'JohnUsername', 'JohnPassword', 'John Wick', '1995-04-15', '416-938-8374', '4829175630986245', 'JohnWick@example.com',0000),
(0017, 'TessUsername', 'TessPassword', 'Tess La', '1988-09-05', '403-124-0933', '7632891456723401', 'TessLa@example.com',  0010),
(0018, 'MercyUsername', 'MercyPassword', 'Mercy Des', '2000-08-18', '587-934-8245', '2156984710328765', 'MercyDes@example.com',  0011),
(0019, 'PorchaUsername', 'PorchaPassword', 'Porcha Lux', '1992-12-10', '587-283-9084', '5974820136481093', 'PorchaLux@example.com',  0012),
(0020, 'BasUsername', 'BasPassword', 'Bas Ketbal', '1985-03-30', '416-824-1872', '8310246570981537', 'BasKetbal@example.com', 0000),
(0021, 'EmilyUsername', 'EmilyPassword', 'Emily Thompson', '2002-07-12', '467-234-8753', '4567890123456789', 'EmilyThompson@example.com',  0000),
(0022, 'OliviaUsername', 'OliviaPassword', 'Olivia Chang', '1998-11-03', '403-935-9823', '1023745638905162', 'OliviaChang@example.com',  0000),
(0023, 'SophiaUsername', 'SophiaPassword', 'Sophia Patel', '1989-09-20', '416-024-8872', '8743210956328901', 'SophiaPatel@example.com', 0013),
(0024, 'AvaUsername', 'AvaPassword', 'Ava Khan', '2001-04-22', '467-464-2445', '3456789012345678', 'AvaKhan@example.com',0014);

DROP TABLE IF EXISTS CUSTOMERS;
CREATE TABLE CUSTOMERS(
    CUSTOMER_ID         INT,
    PRIMARY KEY(CUSTOMER_ID),
    FOREIGN KEY(CUSTOMER_ID) REFERENCES USERS(ID_NO)
);

INSERT INTO CUSTOMERS(CUSTOMER_ID)
VALUES
(0001), (0003), (0004), (0005), (0006), (0007), (0008), (0009), (0010), (0011),
(0012), (0013), (0014), (0015), (0016), (0017), (0018), (0019), (0020), (0021),
(0022), (0023), (0024);

DROP TABLE IF EXISTS SEAT; /* Number of seats: 80*/
/*Seat Numbers - Business: 1 - 10, Comfort: 11 - 25, Ordinary: 26 - 80*/
CREATE TABLE SEAT(
    SEAT_TYPE           VARCHAR(40),
    COST                INT,
    PRIMARY KEY(SEAT_TYPE)
);

INSERT INTO SEAT(SEAT_TYPE, COST)
VALUES
('Business', 1000),
('Comfort', 700),
('Ordinary', 500);

DROP TABLE IF EXISTS BOOKED;
CREATE TABLE BOOKED(
	USER_ID				INT,
	FLIGHT_NUMBER		INT,
    SEAT_TYPE			VARCHAR(20),
    SEAT_NUMBER			INT,
    INSURANCE_STATUS    INT, /* INSURANCE_STATUS == 1 if the customer has paid for insurance, otehrwise 0*/
    PRIMARY KEY(USER_ID)
);

INSERT INTO BOOKED(USER_ID, FLIGHT_NUMBER, SEAT_TYPE, SEAT_NUMBER, INSURANCE_STATUS)
VALUES
(0001,  01, 'Comfort', 11, 1),
(0002,  02, 'Ordinary', 26, 0),
(0003,  03, 'Business', 01, 1),
(0004,  04, 'Comfort', 11, 0),
(0005,  05, 'Ordinary', 26, 1),
(0006,  06, 'Ordinary', 26, 1),
(0007,  07, 'Comfort', 11,  0),
(0008,  08, 'Ordinary', 26, 0),
(0009,  09, 'Business', 01, 1),
(0010,  10, 'Comfort', 11, 0),
(0011,  11, 'Business', 01, 1),
(0012,  12, 'Ordinary', 26, 0),
(0013, 13, 'Comfort', 11, 1),
(0014,  14, 'Business', 01, 1),
(0015, 15, 'Business', 01, 0),
(0016, 16, 'Ordinary', 26, 1),
(0017,  17, 'Ordinary', 26, 0),
(0018,  18, 'Comfort', 11, 0),
(0019,  19, 'Business', 01, 1),
(0020, 20, 'Ordinary', 26, 1),
(0021, 21, 'Ordinary', 26, 0),
(0022,  22, 'Ordinary', 26, 1),
(0023,  23, 'Comfort', 11, 1),
(0024, 24, 'Business', 01, 1);

	
