
INSERT INTO `User` (userId, email, encryptedPassword, enabled) VALUES (1, 'mccries@sheridancollege.ca', '$2a$10$vzq5ZOJIlVYK0x.Ww9C7zeYkWSIAAnuVEqAgr1wij5.WnFdtZ3CiW', 1);
INSERT INTO `User` (userId, email, encryptedPassword, enabled) VALUES (2, 'john@sheridancollege.ca', '$2a$10$vzq5ZOJIlVYK0x.Ww9C7zeYkWSIAAnuVEqAgr1wij5.WnFdtZ3CiW', 1);
INSERT INTO `User` (userId, email, encryptedPassword, enabled) VALUES (3, 'eric@gmail.com', '$2a$10$vzq5ZOJIlVYK0x.Ww9C7zeYkWSIAAnuVEqAgr1wij5.WnFdtZ3CiW', 1);
INSERT INTO `User` (userId, email, encryptedPassword, enabled) VALUES (4, 'georgia@live.ca', '$2a$10$vzq5ZOJIlVYK0x.Ww9C7zeYkWSIAAnuVEqAgr1wij5.WnFdtZ3CiW', 1);


INSERT INTO `Role` (roleName)
VALUES ('ROLE_USER');
INSERT INTO `Role` (roleName)
VALUES ('ROLE_ADMIN');


INSERT INTO User_Role (userId, roleId) VALUES (1, 2);
INSERT INTO User_Role (userId, roleId) VALUES (2, 2);
INSERT INTO User_Role (userId, roleId) VALUES (3, 1);
INSERT INTO User_Role (userId, roleId) VALUES (4, 1);


INSERT INTO Student (studentId, firstName, lastName, userId) VALUES (1, 'Sarah', 'McCrie', 1);
INSERT INTO Student (studentId, firstName, lastName, userId) VALUES (2, 'John', 'Smith', 2);
INSERT INTO Student (studentId, firstName, lastName, userId) VALUES (3, 'Eric', 'Johnstone', 3);
INSERT INTO Student (studentId, firstName, lastName, userId) VALUES (4, 'Georgia', 'Peach', 4);


INSERT INTO Course (courseId, courseName) VALUES (1, 'PROG301');
INSERT INTO Course (courseId, courseName) VALUES (2, 'COMM202');
INSERT INTO Course (courseId, courseName) VALUES (3, 'SYST303');
INSERT INTO Course (courseId, courseName) VALUES (4, 'INFO201');


INSERT INTO Enrollment (studentId, courseId, termName, grade, enrollDate) VALUES (1, 1, 'Fall 2022', 'A', '2023-09-01');
INSERT INTO Enrollment (studentId, courseId, termName, grade, enrollDate) VALUES (2, 2, 'Spring 2020', 'B+', '2023-09-01');
INSERT INTO Enrollment (studentId, courseId, termName, grade, enrollDate) VALUES (3, 3, 'Winter 2023', 'D', '2023-09-01');
INSERT INTO Enrollment (studentId, courseId, termName, grade, enrollDate) VALUES (4, 4, 'Fall 2023', 'A-', '2023-09-01');
