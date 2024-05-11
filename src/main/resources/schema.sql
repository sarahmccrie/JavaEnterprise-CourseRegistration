CREATE TABLE `User` (
    userId BIGINT PRIMARY KEY,
    email VARCHAR(45) UNIQUE NOT NULL,
    encryptedPassword VARCHAR(95) NOT NULL,
    enabled BIT NOT NULL
);


CREATE TABLE Role (
    roleId BIGINT PRIMARY KEY,
    roleName VARCHAR(45) UNIQUE NOT NULL
);


CREATE TABLE User_Role (
    userId BIGINT NOT NULL,
    roleId BIGINT NOT NULL,
    CONSTRAINT FK_UserRole_User FOREIGN KEY (userId) REFERENCES `User`(userId),
    CONSTRAINT FK_UserRole_Role FOREIGN KEY (roleId) REFERENCES Role(roleId),
    PRIMARY KEY (userId, roleId)
);


CREATE TABLE Student (
    studentId BIGINT PRIMARY KEY,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    userId BIGINT NOT NULL,
    CONSTRAINT FK_Student_User FOREIGN KEY (userId) REFERENCES `User`(userId)
);


CREATE TABLE Course (
    courseId BIGINT PRIMARY KEY,
    courseName VARCHAR(45) NOT NULL
);


CREATE TABLE Enrollment (
    studentId BIGINT NOT NULL,
    courseId BIGINT NOT NULL,
    termName VARCHAR(45) NOT NULL,
    grade VARCHAR(45),
    enrollDate DATE NOT NULL,
    CONSTRAINT FK_Enrollment_Student FOREIGN KEY (studentId) REFERENCES Student(studentId),
    CONSTRAINT FK_Enrollment_Course FOREIGN KEY (courseId) REFERENCES Course(courseId),
    PRIMARY KEY (studentId, courseId, termName)
);
