-- <----------------USE CASE 1----------->
CREATE DATABASE address_book_service;
USE address_book_service;
SHOW databases;
SELECT DATABASE();

-- <----------------USE CASE 2----------->
CREATE TABLE AddressBook (
    ContactID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(255),
    City VARCHAR(50),
    State VARCHAR(50),
    ZipCode VARCHAR(10),
    PhoneNumber VARCHAR(15),
    Email VARCHAR(100)
);

-- <----------------USE CASE 3----------->
INSERT INTO AddressBook (FirstName, LastName, Address, City, State, ZipCode, PhoneNumber, Email)
VALUES
    ('vivek', 'tangudu', 'pnr', 'pathapatnam', 'AP', '12345', '98765432', 'xyz@email.com'),
    ('Bob', 'Williams', '321 Pine St', 'Nowhere', 'AZ', '54321', '555-5432', 'bob.williams@email.com'),
    ('Charlie', 'Davis', '555 Maple St', 'Anywhere', 'FL', '98765', '555-2345', 'charlie.davis@email.com'),
    ('Diana', 'Miller', '876 Oak St', 'Noway', 'WA', '12345', '555-8765', 'diana.miller@email.com');
SELECT * FROM AddressBook;

-- <----------------USE CASE 4----------->
SET SQL_SAFE_UPDATES = 0;
UPDATE AddressBook
SET Address="del" ,City="delhi",State="UP",ZipCode="2345",PhoneNumber="6335839823",Email="ria@gmail.com"
WHERE FirstName="Diana" AND LastName="Miller";

-- <----------------USE CASE 5----------->
DELETE FROM AddressBook
WHERE FirstName = 'Charlie' AND LastName = 'Davis';

-- <----------------USE CASE 6----------->
SELECT * FROM AddressBook
WHERE city="pathapatnam" OR state="AP";

-- <----------------USE CASE 7------------------->
SELECT City,State,COUNT(*) AS size
FROM AddressBook
GROUP BY city,state;

-- <----------------USE CASE 8------------------->
SELECT * FROM AddressBook
WHERE state="AP"
ORDER BY FirstName;

-- <----------------USE CASE 9------------------->
ALTER TABLE AddressBook
ADD COLUMN Name VARCHAR(100),
ADD COLUMN Type VARCHAR(50);
UPDATE AddressBook
SET Name = CONCAT(FirstName, ' ', LastName), Type = 'Family'
WHERE ContactID = 1;
UPDATE AddressBook
SET Name = CONCAT(FirstName, ' ', LastName), Type = 'Friends'
WHERE ContactID = 4;

-- <----------------USE CASE 10------------------->
SELECT type,COUNT(*)
FROM AddressBook
GROUP BY type;

-- <----------------USE CASE 11------------------->
UPDATE AddressBook
SET Type = 'Friends'
WHERE ContactID = 1;
INSERT INTO AddressBook (FirstName, LastName, Address, City, State, ZipCode, PhoneNumber, Email, Name, Type)
SELECT FirstName, LastName, Address, City, State, ZipCode, PhoneNumber, Email, Name, 'Family'
FROM AddressBook
WHERE ContactID = 1;

-- <----------------USE CASE 12------------------->
-- Create Person table
CREATE TABLE Person (
    ContactID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Address VARCHAR(255),
    City VARCHAR(50),
    State VARCHAR(50),
    ZipCode VARCHAR(10),
    PhoneNumber VARCHAR(15),
    Email VARCHAR(100)
);

-- Create ContactType table
CREATE TABLE ContactType (
    TypeID INT PRIMARY KEY AUTO_INCREMENT,
    TypeName VARCHAR(50)
);

-- Create AddressBook table
CREATE TABLE AddressBooks (
    AddressBookID INT PRIMARY KEY AUTO_INCREMENT,
    ContactID INT,
    TypeID INT,
    Name VARCHAR(100),
    FOREIGN KEY (ContactID) REFERENCES Person(ContactID),
    FOREIGN KEY (TypeID) REFERENCES ContactType(TypeID)
);


INSERT INTO Person (FirstName, LastName, Address, City, State, ZipCode, PhoneNumber, Email)
VALUES ('Diana', 'Miller', 'del', 'delhi', 'UP', '2345', '6335839823', 'ria@gmail.com'),
       ('vivek', 'tangudu', 'pnr', 'pathapatnam', 'AP', '12345', '98765432', 'xyz@email.com');
INSERT INTO ContactType (TypeName)
VALUES ('Friends'), ('Family');
INSERT INTO AddressBooks (ContactID, TypeID, Name)
VALUES (1, 1, 'Diana Miller'),
       (2, 1, 'vivek tangudu'),
       (2, 2, 'vivek tangudu');
SELECT * FROM Person;
SELECT * FROM ContactType;
SELECT * FROM AddressBooks;

-- <----------------USE CASE 13------------------->
SELECT *
FROM Person
WHERE City = 'pathaptnam' OR State = 'AP';

SELECT City, State, COUNT(*) AS AddressBookSize
FROM Person
GROUP BY City, State
ORDER BY City, State;

SELECT *
FROM Person
WHERE State = 'AP'
ORDER BY FirstName, LastName;

SELECT AddressBooks.TypeID, ContactType.TypeName, COUNT(*) AS ContactPersonCount
FROM AddressBooks
JOIN ContactType ON AddressBooks.TypeID = ContactType.TypeID
GROUP BY AddressBooks.TypeID, ContactType.TypeName;