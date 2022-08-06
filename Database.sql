SCHOOL MANAGEMENT SYSTEM

#mysql -h localhost -p 1234 -u root
DROP DATABASE IF EXISTS school_management;
CREATE DATABASE IF NOT EXISTS school_management;
SHOW DATABASES ;
USE school_management;

STUDENT
DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(
	stuId VARCHAR(10),
	parId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    address TEXT,
    contact VARCHAR(10),
    CONSTRAINT PRIMARY KEY(stuId),
    CONSTRAINT FOREIGN KEY(parId) REFERENCES Parent (parId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Student;
#-----------------
PARENT
DROP TABLE IF EXISTS Parent;
CREATE TABLE IF NOT EXISTS Parent(
	parId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    address TEXT,
    contact INT,
    CONSTRAINT PRIMARY KEY(parId)
    );
SHOW TABLES;
DESCRIBE Parent;
#----------------
Reader
DROP TABLE IF EXISTS Reader;
CREATE TABLE IF NOT EXISTS Reader(
	reaId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    address TEXT,
    contact VARCHAR(10),
    CONSTRAINT PRIMARY KEY(reaId)
    );
SHOW TABLES;
DESCRIBE Reader;
#---------------
Library_Reader
DROP TABLE IF EXISTS Library_Reader;
CREATE TABLE IF NOT EXISTS Library_Reader(
	libId VARCHAR(10),
    reaId VARCHAR(10),
    CONSTRAINT PRIMARY KEY(libId,reaId)

    );
SHOW TABLES;
DESCRIBE Library_Reader;
#----------------------
Library
DROP TABLE IF EXISTS Library;
CREATE TABLE IF NOT EXISTS Library(
	libId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    schId VARCHAR(10),
    CONSTRAINT PRIMARY KEY(libId),
    CONSTRAINT FOREIGN KEY(schId) REFERENCES School (schId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Library;
#------------------
Book
DROP TABLE IF EXISTS Book;
CREATE TABLE IF NOT EXISTS Book(
	bookId VARCHAR(10),
 	reaId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    category VARCHAR(45),
    issueDate DATE,
    CONSTRAINT PRIMARY KEY(bookId),
    CONSTRAINT FOREIGN KEY(reaId) REFERENCES Reader (reaId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Book;
#-----------------

School
DROP TABLE IF EXISTS School;
CREATE TABLE IF NOT EXISTS School(
	schId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	address TEXT,
	CONSTRAINT PRIMARY KEY(schId)
    );
SHOW TABLES;
DESCRIBE School;
#----------------
Student_Attendance
DROP TABLE IF EXISTS Student_Attendance;
CREATE TABLE IF NOT EXISTS Student_Attendance(
    stuId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    date DATE,
    reMark TEXT,
    CONSTRAINT PRIMARY KEY(stuId)
    );
SHOW TABLES;
DESCRIBE Student_Attendance;
#----------------------------------
Class
DROP TABLE IF EXISTS Class;
CREATE TABLE IF NOT EXISTS Class(
	classId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    teaId VARCHAR(10),
	schId VARCHAR(10),
	CONSTRAINT PRIMARY KEY(classId),
	CONSTRAINT FOREIGN KEY(teaId) REFERENCES Teacher (teaId) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(schId) REFERENCES School (schId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Class;
#------------------
Class_Teacher
DROP TABLE IF EXISTS Class_Teacher;
CREATE TABLE IF NOT EXISTS Class_Teacher(
	classId VARCHAR(10),
	teaId VARCHAR(10),
	CONSTRAINT PRIMARY KEY(classId,teaId)
    );
SHOW TABLES;
DESCRIBE Class_Teacher;
#---------------------------
Teacher
DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher(
	teaId VARCHAR(10),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	address TEXT,
    contact VARCHAR(10),
	CONSTRAINT PRIMARY KEY(teaId)
    );
SHOW TABLES;
DESCRIBE Teacher;
#-----------------
Teacher_Attendance
DROP TABLE IF EXISTS Teacher_Attendance;
CREATE TABLE IF NOT EXISTS Teacher_Attendance(
	teaAttId VARCHAR(10),
	date DATE,
	teaId VARCHAR(10),
	name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    reMark TEXT,
	CONSTRAINT PRIMARY KEY(teaAttId),
    CONSTRAINT FOREIGN KEY(teaId) REFERENCES Teacher (teaId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Teacher_Attendance;
#----------------------------
Subject
DROP TABLE IF EXISTS Subject;
CREATE TABLE IF NOT EXISTS Subject(
	subId VARCHAR(10),
	name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
 	schId VARCHAR(10),
 	CONSTRAINT PRIMARY KEY(subId)
    );
SHOW TABLES;
DESCRIBE Subject;
#-----------------
Subject_Time_Table
DROP TABLE IF EXISTS Subject_Time_Table;
CREATE TABLE IF NOT EXISTS Subject_Time_Table(
	subId VARCHAR(10),
	tableId VARCHAR(10),
 	CONSTRAINT PRIMARY KEY(subId,tableId)

    );
SHOW TABLES;
DESCRIBE Subject_Time_Table;
#-----------------------------
Time_Table
DROP TABLE IF EXISTS Time_Table;
CREATE TABLE IF NOT EXISTS Time_Table(
	tableId VARCHAR(10),
	classId VARCHAR(10),
	subName1 VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	subName2 VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	subName3 VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	subName4 VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	subName5 VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	subName6 VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	subName7 VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	subName8 VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	CONSTRAINT PRIMARY KEY(tableId),
    CONSTRAINT FOREIGN KEY(classId) REFERENCES Class (classId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Time_Table;
#---------------------
Exam_Type
DROP TABLE IF EXISTS Exam_Type;
CREATE TABLE IF NOT EXISTS Exam_Type(
	examTypeId VARCHAR(10),
	name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	CONSTRAINT PRIMARY KEY(examTypeId)
    );
SHOW TABLES;
DESCRIBE Exam_Type;
#-------------------
Exam
DROP TABLE IF EXISTS Exam;
CREATE TABLE IF NOT EXISTS Exam(
	examId VARCHAR(10),
	examTypeId VARCHAR(10),
	name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    startDate DATE,
    CONSTRAINT PRIMARY KEY(examId),
    CONSTRAINT FOREIGN KEY(examTypeId) REFERENCES Exam_Type (examTypeId) ON DELETE CASCADE ON UPDATE CASCADE

    );
SHOW TABLES;
DESCRIBE Exam;
#-----------------
Exam_Result
DROP TABLE IF EXISTS Exam_Result;
CREATE TABLE IF NOT EXISTS Exam_Result(
	reId VARCHAR(10),
	examId VARCHAR(10),
    stuId VARCHAR(10),
    subject VARCHAR(10)  NOT NULL DEFAULT 'Unknown',
	marks VARCHAR(30),
    grade VARCHAR(5),
    CONSTRAINT PRIMARY KEY(reId),
    CONSTRAINT FOREIGN KEY(examId) REFERENCES Exam (examId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(stuId) REFERENCES Student (stuId) ON DELETE CASCADE ON UPDATE CASCADE

    );
SHOW TABLES;
DESCRIBE Exam_Result;

#========================

DROP TABLE IF EXISTS UserLogin;
CREATE TABLE IF NOT EXISTS UserLogin(
    id VARCHAR(10) ,
    name VARCHAR(45)not null,
    password VARCHAR(10)not null,
    CONSTRAINT PRIMARY KEY(id)
);
SHOW TABLES;
DESCRIBE UserLogin;

INSERT INTO UserLogin VALUES("A001","Admin","1234A");
INSERT INTO UserLogin VALUES("T001","user1","12345T");
INSERT INTO UserLogin VALUES("L001","user2","123456L");

#---------------------------------
DROP TABLE IF EXISTS School;
CREATE TABLE IF NOT EXISTS School(
    schId VARCHAR(10) ,
    name VARCHAR(45)not null,
    address VARCHAR(10)not null,
    CONSTRAINT PRIMARY KEY(schId)
);
SHOW TABLES;
DESCRIBE School;

INSERT INTO School VALUES("SH01","Rajarata Central College","Anuradhapura");

#----------------------------------

INSERT INTO Library VALUES("L01","Kannangara Library","SH01");
INSERT INTO Library VALUES("L02","Science Library","SH01");

#-------------------------------------------

INSERT INTO Reader VALUES("R00001","Anuththara Gimhan","Kandy","0707261528");
INSERT INTO Reader VALUES("R00002","Kumara Silva","Kandy","0706374628");
INSERT INTO Reader VALUES("R00003","Pavan Kumara","Kandy","0708362734");
INSERT INTO Reader VALUES("R00004","Nuwan Kumara","Colombo","0702738265");
INSERT INTO Reader VALUES("R00005","Dilip Milantha","Colombo","0706372837");

#---------------------------------------------------

INSERT INTO Book VALUES("B00001","R00001","Sapu Mal","Navel","2021-09-25");