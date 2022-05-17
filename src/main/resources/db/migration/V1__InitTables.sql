CREATE TABLE `tbl_departments`
(
    `dpID` BIGINT AUTO_INCREMENT UNIQUE,
    `dpName` varchar(255),
    PRIMARY KEY(`dpID`)
);

CREATE TABLE `tbl_employees`
(
    `empID` BIGINT AUTO_INCREMENT UNIQUE,
    `empName` varchar(255),
    `empActive` BOOLEAN DEFAULT false,
    `emp_dpID` BIGINT,
    PRIMARY KEY(`empID`),
    FOREIGN KEY(`emp_dpID`) REFERENCES tbl_departments(`dpID`)
);

CREATE TABLE `tbl_users`
(
    `userID` BIGINT AUTO_INCREMENT UNIQUE,
    `userName` varchar(255),
    `userPassword` varchar(255),
    PRIMARY KEY(`userID`)
);

INSERT INTO tbl_departments(dpName) VALUES('HR');
INSERT INTO tbl_departments(dpName) VALUES('Tech');
INSERT INTO tbl_departments(dpName) VALUES('Finance');
INSERT INTO tbl_departments(dpName) VALUES('Marketing');
INSERT INTO tbl_departments(dpName) VALUES('Management');

INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Lisa',1,1);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Erik',1,2);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Don',1,3);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Peter',0,2);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Katherine',1,1);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Michael',0,4);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('James',1,3);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Joseph',0,2);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Jack',1,5);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Josephine',1,2);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Jacob',1,1);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('John',1,4);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Emma',0,2);
INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES('Spencer',1,5);

INSERT INTO tbl_users(userName, userPassword) VALUES('test', '$2a$10$GVLDkpoL4Q07wD2/hip0eu6ukq3yLEPtt1FU2EL/Kn4SkOqodHUT2');



