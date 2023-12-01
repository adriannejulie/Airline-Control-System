DROP DATABASE IF EXISTS TP480;

CREATE DATABASE TP480;
USE TP480;

ALTER USER 'root'@'127.0.0.1' IDENTIFIED BY 'password';


DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE EMPLOYEE(
	USERNAME			INT,
    PASSWORD			VARCHAR(30),
    PRIMARY KEY(USERNAME)
); 

INSERT INTO EMPLOYEE(USERNAME, PASSWORD)
VALUE
(1000, 'agent'), (1001, 'agent'), (1002, 'agent'), (1003, 'agent'), (1004, 'agent'), (1005, 'agent'), /* ID_NOs for Airline Agents*/
(2000, 'crew'), (2001, 'crew'), (2002, 'crew'), (2003, 'crew'), (2004, 'crew'), (2005, 'crew'), 
(2006, 'crew'), (2007, 'crew'), (2008, 'crew'), (2009, 'crew'), (2010, 'crew'), (2011, 'crew'),

(3000, 'crew'), (3001, 'crew'), (3002, 'crew'), (3003, 'crew'), (3004, 'crew'),
(3005, 'crew'), (3006, 'crew'), (3007, 'crew'), (3008, 'crew'), (3009, 'crew'),
(3010, 'crew'), (3011, 'crew'), (3012, 'crew'), (3013, 'crew'), (3014, 'crew'),

(3015, 'crew'), (3016, 'crew'), (3017, 'crew'), (3018, 'crew'), (3019, 'crew'),
(3020, 'crew'), (3021, 'crew'), (3022, 'crew'), (3023, 'crew'), (3024, 'crew'), 
(3025, 'crew'), (3026, 'crew'), (3027, 'crew'), (3028, 'crew'), (3029, 'crew'), 

(3030, 'crew'), (3031, 'crew'), (3032, 'crew'), (3033, 'crew'), (3034, 'crew'),
(3035, 'crew'), (3036, 'crew'), (3037, 'crew'), (3038, 'crew'), (3039, 'crew'),
(3040, 'crew'), (3041, 'crew'), (3042, 'crew'), (3043, 'crew'), (3044, 'crew');

DROP TABLE IF EXISTS AIRCRAFT;
CREATE TABLE AIRCRAFT(
    AIRCFRAFT_ID        INT,
    PRIMARY KEY(AIRCFRAFT_ID)
);

INSERT INTO AIRCRAFT(AIRCFRAFT_ID) /* Assume all aircrafts have 80 seats*/
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
    FLIGHT_NUMBER       VARCHAR(10),
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
    FOREIGN KEY(AIRCRAFT_TYPE) REFERENCES AIRCRAFT(AIRCFRAFT_ID),
    FOREIGN KEY(DESTINATION) REFERENCES AIRPORT(AIRPORT_CODE),
    FOREIGN KEY(PILOT1) REFERENCES EMPLOYEE(USERNAME),
    FOREIGN KEY(FLIGHT_ATTENDANT1) REFERENCES EMPLOYEE(USERNAME),
    FOREIGN KEY(FLIGHT_ATTENDANT2) REFERENCES EMPLOYEE(USERNAME),
    FOREIGN KEY(FLIGHT_ATTENDANT3) REFERENCES EMPLOYEE(USERNAME),
    FOREIGN KEY(FLIGHT_ATTENDANT4) REFERENCES EMPLOYEE(USERNAME)
);

INSERT INTO FLIGHT(FLIGHT_NUMBER, DESTINATION, ORIGIN, TIME_DEPARTURE, AIRCRAFT_TYPE, PILOT1, FLIGHT_ATTENDANT1, FLIGHT_ATTENDANT2, FLIGHT_ATTENDANT3, FLIGHT_ATTENDANT4)
VALUES
('ABC123', 'CYYZ','CYVR','2023-12-20 00:00:00', 01, 2000, 3000, 3001, 3002, 3003), /*Flights from Toronto to Vancouver*/
('BCD234', 'CYYZ','CYVR','2023-12-20 12:00:00', 02, 2001, 3004, 3005, 3006, 3007),
('CDE345', 'CYYZ','CYVR','2023-12-23 00:00:00', 03, 2002, 3008, 3009, 3010, 3011),
('SDR456', 'CYYZ','CYVR','2023-12-23 12:00:00', 04, 2003, 3012, 3013, 3014, 3015),
('KFB688', 'CYUL','CYYC','2023-12-25 00:00:00', 05, 2000, 3000, 3001, 3002, 3003), /*Flights from Montreal to Calgary*/
('PSD980', 'CYUL','CYYC','2023-12-25 12:00:00', 06, 2001, 3004, 3005, 3006, 3007),
('PEF234', 'CYUL','CYYC','2023-12-27 00:00:00', 07, 2002, 3008, 3009, 3010, 3011),
('SMO023', 'CYUL','CYYC','2023-12-27 12:00:00', 08, 2003, 3012, 3013, 3014, 3015),
('UBR632', 'CYOW','CYTZ','2023-12-29 00:00:00', 09, 2000, 3000, 3001, 3002, 3003), /*Flights from Ottowa to Toronto*/
('POI098', 'CYOW','CYTZ','2023-12-29 12:00:00', 10, 2001, 3004, 3005, 3006, 3007),
('PEW690', 'CYOW','CYTZ','2023-12-31 00:00:00', 11, 2002, 3008, 3009, 3010, 3011),
('WEW123', 'CYOW','CYTZ','2023-12-31 12:00:00', 12, 2003, 3012, 3013, 3014, 3015),
('UWU765', 'CYEG','CYWG','2023-12-20 00:00:00', 13, 2004, 3016, 3017, 3018, 3019), /*Flights from Edmonton to Winnepeg*/
('UYT709', 'CYEG','CYWG','2023-12-20 12:00:00', 14, 2005, 3020, 3021, 3022, 3023),
('YUI987', 'CYEG','CYWG','2023-12-23 00:00:00', 15, 2006, 3024, 3025, 3026, 3027),
('NMB098', 'CYEG','CYWG','2023-12-23 12:00:00', 16, 2007, 3028, 3029, 3030, 3031),
('RTY890', 'CYYZ','CYYC','2023-12-25 00:00:00', 17, 2004, 3016, 3017, 3018, 3019), /*Flights from Toronto to Calgary*/
('UWU456', 'CYYZ','CYYC','2023-12-25 12:00:00', 18, 2005, 3020, 3021, 3022, 3023),
('UWU098', 'CYYZ','CYYC','2023-12-27 00:00:00', 19, 2006, 3024, 3025, 3026, 3027),
('TYU894', 'CYYZ','CYYC','2023-12-27 12:00:00', 20, 2007, 3028, 3029, 3030, 3031),
('OIU875', 'CYYC','CYVR','2023-12-29 00:00:00', 21, 2004, 3016, 3017, 3018, 3019), /*Flights from Calgary to Vancouver*/
('WER276', 'CYYC','CYVR','2023-12-29 12:00:00', 22, 2005, 3020, 3021, 3022, 3023),
('GFH456', 'CYYC','CYVR','2023-12-31 00:00:00', 23, 2006, 3024, 3025, 3026, 3027),
('UYT679', 'CYYC','CYVR','2023-12-31 12:00:00', 24, 2007, 3028, 3029, 3030, 3031);


DROP TABLE  IF EXISTS AIRLINE_AGENT;
CREATE TABLE AIRLINE_AGENT(
    USERNAME            INT,
    NAME				VARCHAR(50),
    PRIMARY KEY(USERNAME),
    FOREIGN KEY(USERNAME) REFERENCES EMPLOYEE(USERNAME)
);

INSERT INTO AIRLINE_AGENT(USERNAME, NAME)
VALUES
(1000, 'John Doe'), (1001, 'Jane Smith'), (1002, 'Michael Johnson'), (1003, 'Emily Davis'), (1004, 'David Brown'), (1005, 'Sarah Miller');


DROP TABLE IF EXISTS CREW;
CREATE TABLE CREW(
    USERNAME             INT,
    NAME				VARCHAR(50),
    PRIMARY KEY(USERNAME),
    FOREIGN KEY(USERNAME) REFERENCES EMPLOYEE(USERNAME)
);

INSERT INTO CREW(USERNAME, NAME)
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

CREATE TABLE USERS(
    USERNAME			VARCHAR(40),
    PASSWORD			VARCHAR(40),
    NAME                VARCHAR(40),
    BIRTH               DATE,
    PHONE_NUMBER        VARCHAR(15),
    EMAIL               VARCHAR(30),
    MEMBERSHIP_ID       INT, /* MEMBERSHIP_ID == 0 if a non-member*/
    PRIMARY KEY(USERNAME)
    
);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, BIRTH, PHONE_NUMBER, EMAIL, MEMBERSHIP_ID)
VALUES
( 'GeorgeUsername', 'GeorgePassword', 'George Gower', '2000-12-13', '403-123-1234', 'GeorgeGower@example.com',0001),
( 'MichielUsername', 'MichielPassword', 'Michiel Sittow', '2001-11-03', '467-321-2345', 'MichielSittow@example.com', 0000),
( 'LucasUsername', 'LucasPassword', 'Lucas de Heere', '1999-09-11', '403-678-3875', 'LucasdeHeere@example.com',0002),
( 'PabloUsername', 'PabloPassword', 'Pablo Picasso', '1984-02-01', '403-098-0683', 'PabloPicasso@example.com', 0000),
( 'GuillaumeUsername', 'GuillaumePassword', 'Guillaume Coustou', '2003-06-22', '587-123-2356','GuillaumeCoustou@example.com', 0003),
( 'BobbyUsername', 'BobbyPassword', 'Bobby Brown', '1999-12-22', '403-753-2759','BobbyBrown@example.com', 0004),
( 'YukiUsername', 'YukiPassword', 'Yuki Ishikawa', '1995-12-11', '467-192-1284','YukiIshikawa@example.com',0005),
( 'HersheyUsername', 'HersheyPassword', 'Hershey Chips', '2003-05-22', '416-746-0298',  'HersheyChips@example.com',0006),
( 'TimUsername', 'TimPassword', 'Tim Hortons', '1965-04-21', '587-953-9923',  'TimHortons@example.com',0000),
( 'CalUsername', 'CalPassword', 'Cal Garyian', '1974-06-23', '403-908-0085', 'CalGaryian@example.com',0007),
( 'EdUsername', 'EdPassword', 'Ed Montonian', '1982-08-20', '587-002-9352','EdMontonian@example.com', 0000),
( 'CareyUsername', 'CareyPassword', 'Carey Beary', '2000-09-18', '403-034-8105',  'CareyBeary@example.com', 0000),
( 'NickUsername', 'NickPassword', 'Nick Ey', '1965-10-16', '416-652-8234',  'NickEy@example.com', 0008),
( 'StephanoUsername', 'StephanoPassword', 'Stephano Montoya', '1945-10-15', '467-564-8723',  'StephanoMontoya@example.com', 0000),
( 'SueUsername', 'SuePassword', 'Sue Perstore', '1987-12-01', '467-079-7352', 'SuePerstore@example.com',  0009),
( 'JohnUsername', 'JohnPassword', 'John Wick', '1995-04-15', '416-938-8374', 'JohnWick@example.com',0000),
( 'TessUsername', 'TessPassword', 'Tess La', '1988-09-05', '403-124-0933',  'TessLa@example.com',  0010),
( 'MercyUsername', 'MercyPassword', 'Mercy Des', '2000-08-18', '587-934-8245',  'MercyDes@example.com',  0011),
( 'PorchaUsername', 'PorchaPassword', 'Porcha Lux', '1992-12-10', '587-283-9084',  'PorchaLux@example.com',  0012),
( 'BasUsername', 'BasPassword', 'Bas Ketbal', '1985-03-30', '416-824-1872',  'BasKetbal@example.com', 0000),
( 'EmilyUsername', 'EmilyPassword', 'Emily Thompson', '2002-07-12', '467-234-8753', 'EmilyThompson@example.com',  0000),
( 'OliviaUsername', 'OliviaPassword', 'Olivia Chang', '1998-11-03', '403-935-9823',  'OliviaChang@example.com',  0000),
( 'SophiaUsername', 'SophiaPassword', 'Sophia Patel', '1989-09-20', '416-024-8872',  'SophiaPatel@example.com', 0013),
( 'AvaUsername', 'AvaPassword', 'Ava Khan', '2001-04-22', '467-464-2445', 'AvaKhan@example.com',0014);

DROP TABLE IF EXISTS SEAT; /* Number of seats: 48*/
/*Seat Numbers - Business: 0A - 1D, Comfort: 2A - 5D, Ordinary: 6A - 11D*/
CREATE TABLE SEAT(
    SEAT_TYPE           VARCHAR(40),
    NUMBER_OF_SEATS		INT,
    PRIMARY KEY(SEAT_TYPE)
);

INSERT INTO SEAT(SEAT_TYPE, NUMBER_OF_SEATS)
VALUES
('Business', 8 ),
('Comfort', 16),
('Ordinary', 24);

DROP TABLE IF EXISTS BOOKED;
CREATE TABLE BOOKED(
	USERNAME			VARCHAR(50),
	FLIGHT_NUMBER		VARCHAR(10),
    SEAT_TYPE			VARCHAR(20),
    SEAT_NUMBER			VARCHAR(5),
    INSURANCE_STATUS    INT /* INSURANCE_STATUS == 1 if the customer has paid for insurance, otehrwise 0*/
);

INSERT INTO BOOKED(USERNAME, FLIGHT_NUMBER, SEAT_TYPE, SEAT_NUMBER, INSURANCE_STATUS)
VALUES

('GeorgeUsername', 'ABC123' , 'Comfort', '3A', 1), 
('MichielUsername','BCD234', 'Ordinary', '7A', 0),
('LucasUsername',  'CDE345', 'Business', '1A', 1),
('PabloUsername',  'SDR456', 'Comfort', '3A', 0),
('GuillaumeUsername','KFB688', 'Ordinary', '6A', 1),
('BobbyUsername','PSD980', 'Ordinary', '7A', 1),
('YukiUsername',  'PEF234', 'Comfort', '3A',  0),
('HersheyUsername','SMO023', 'Ordinary', '7A', 0),
('TimUsername','UBR632', 'Business', '1A', 1),
('CalUsername','POI098', 'Comfort', '3A', 0),
('EdUsername','PEW690', 'Business', '1A', 1),
('CareyUsername','WEW123', 'Ordinary', '7A', 0),
('NickUsername','UWU765', 'Comfort', '3A', 1),
('StephanoUsername','UYT709', 'Business', '1A', 1),
('SueUsername','YUI987', 'Business', '1A', 0),
('JohnUsername','NMB098', 'Ordinary', '7A', 1),
('TessUsername', 'RTY890', 'Ordinary', '7A', 0),
('MercyUsername', 'UWU456', 'Comfort', '7A', 0),
('PorchaUsername','UWU098', 'Business', '1A', 1),
('BasUsername','TYU894', 'Ordinary', '7A', 1),
('EmilyUsername', 'OIU875', 'Ordinary', '7A', 0),
('OliviaUsername','WER276', 'Ordinary', '7A', 1),
('SophiaUsername', 'GFH456', 'Comfort', '3A', 1),
('AvaUsername', 'UYT679', 'Business', '1A', 1);


SELECT * FROM USERS;
