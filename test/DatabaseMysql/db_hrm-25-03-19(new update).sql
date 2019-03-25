/*
 Navicat Premium Data Transfer

 Source Server         : wamp
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : db_hrm

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 25/03/2019 13:15:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actions
-- ----------------------------
DROP TABLE IF EXISTS `actions`;
CREATE TABLE `actions`  (
  `Actionid` int(10) NOT NULL AUTO_INCREMENT,
  `ActionType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Actionid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attendancedetails
-- ----------------------------
DROP TABLE IF EXISTS `attendancedetails`;
CREATE TABLE `attendancedetails`  (
  `AttendanceDetailId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `AttId` int(11) NULL DEFAULT NULL,
  `DayIn` date NULL DEFAULT NULL,
  `TimeIn` time(0) NULL DEFAULT NULL,
  `DayOut` date NULL DEFAULT NULL,
  `TimeOut` time(0) NULL DEFAULT NULL,
  `Status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ForEmpId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`AttendanceDetailId`) USING BTREE,
  INDEX `ForEmpId`(`ForEmpId`) USING BTREE,
  INDEX `AttId`(`AttId`) USING BTREE,
  CONSTRAINT `attendancedetails_ibfk_1` FOREIGN KEY (`ForEmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attendancedetails_ibfk_2` FOREIGN KEY (`AttId`) REFERENCES `attendances` (`AttId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attendances
-- ----------------------------
DROP TABLE IF EXISTS `attendances`;
CREATE TABLE `attendances`  (
  `AttId` int(10) NOT NULL AUTO_INCREMENT,
  `DateCreated` date NULL DEFAULT NULL,
  `Empid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`AttId`) USING BTREE,
  INDEX `Empid`(`Empid`) USING BTREE,
  CONSTRAINT `attendances_ibfk_2` FOREIGN KEY (`Empid`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for categorys
-- ----------------------------
DROP TABLE IF EXISTS `categorys`;
CREATE TABLE `categorys`  (
  `CatID` int(11) NOT NULL AUTO_INCREMENT,
  `CatName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CatID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `CustomerId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`CustomerId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for days
-- ----------------------------
DROP TABLE IF EXISTS `days`;
CREATE TABLE `days`  (
  `DayID` int(10) NOT NULL AUTO_INCREMENT,
  `Day` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DayID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments`  (
  `DepartmentId` int(10) NOT NULL AUTO_INCREMENT,
  `Department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`DepartmentId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees`  (
  `EmpId` int(10) NOT NULL AUTO_INCREMENT,
  `HiredDate` datetime(0) NULL DEFAULT NULL,
  `BasicSalary` decimal(10, 2) NULL DEFAULT NULL,
  `BlockSalary` decimal(10, 2) NULL DEFAULT NULL,
  `Status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Children` int(11) NULL DEFAULT 0,
  `FiredDate` date NULL DEFAULT NULL,
  `WorkDayId` int(11) NULL DEFAULT NULL,
  `UserId` int(11) NULL DEFAULT NULL,
  `DepartmentId` int(11) NULL DEFAULT NULL,
  `EmpTypeId` int(11) NULL DEFAULT NULL,
  `PositionID` int(11) NULL DEFAULT NULL,
  `ManagerId` int(11) NULL DEFAULT NULL,
  `IntervieweeId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`EmpId`) USING BTREE,
  INDEX `EmpTypeId`(`EmpTypeId`) USING BTREE,
  INDEX `PositionID`(`PositionID`) USING BTREE,
  INDEX `ManagerId`(`ManagerId`) USING BTREE,
  INDEX `DepartmentId`(`DepartmentId`) USING BTREE,
  INDEX `UserId`(`UserId`) USING BTREE,
  INDEX `IntervieweeId`(`IntervieweeId`) USING BTREE,
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`ManagerId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`DepartmentId`) REFERENCES `departments` (`DepartmentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employees_ibfk_3` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `p` FOREIGN KEY (`PositionID`) REFERENCES `positions` (`PositionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `s` FOREIGN KEY (`EmpTypeId`) REFERENCES `emptype` (`EmpTypeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employees_ibfk_4` FOREIGN KEY (`IntervieweeId`) REFERENCES `interviewees` (`IntervieweeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for emptype
-- ----------------------------
DROP TABLE IF EXISTS `emptype`;
CREATE TABLE `emptype`  (
  `EmpTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `EmpType` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`EmpTypeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for events
-- ----------------------------
DROP TABLE IF EXISTS `events`;
CREATE TABLE `events`  (
  `EventId` int(10) NOT NULL AUTO_INCREMENT,
  `Event` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`EventId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for holidays
-- ----------------------------
DROP TABLE IF EXISTS `holidays`;
CREATE TABLE `holidays`  (
  `HolidayId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Date` date NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`HolidayId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for interviewdetails
-- ----------------------------
DROP TABLE IF EXISTS `interviewdetails`;
CREATE TABLE `interviewdetails`  (
  `InterviewDetailId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `InterviewID` int(11) NULL DEFAULT NULL,
  `IntervieweeId` int(11) NULL DEFAULT NULL,
  `StatusCome` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `StatusOfReinter` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`InterviewDetailId`) USING BTREE,
  INDEX `InterviewID`(`InterviewID`) USING BTREE,
  INDEX `IntervieweeId`(`IntervieweeId`) USING BTREE,
  CONSTRAINT `interviewdetails_ibfk_1` FOREIGN KEY (`InterviewID`) REFERENCES `interviews` (`InterviewID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `interviewdetails_ibfk_2` FOREIGN KEY (`IntervieweeId`) REFERENCES `interviewees` (`IntervieweeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for interviewees
-- ----------------------------
DROP TABLE IF EXISTS `interviewees`;
CREATE TABLE `interviewees`  (
  `IntervieweeId` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Gender` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Tel` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Image` blob NULL,
  `Status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Blocked` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SkillId` int(11) NULL DEFAULT NULL,
  `Dob` date NULL DEFAULT NULL,
  `Email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`IntervieweeId`) USING BTREE,
  INDEX `SkillId`(`SkillId`) USING BTREE,
  CONSTRAINT `interviewees_ibfk_1` FOREIGN KEY (`SkillId`) REFERENCES `skills` (`SkillId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interviewees
-- ----------------------------
INSERT INTO `interviewees` VALUES (1, 'roth', 'female', 'pp', '099887788', NULL, NULL, NULL, 'k', 'khmer , english', NULL, NULL, NULL);
INSERT INTO `interviewees` VALUES (2, 'sokthea', 'male', 'tk', '077889933', NULL, NULL, NULL, 'e', 'khmer,english,chinese', NULL, NULL, NULL);
INSERT INTO `interviewees` VALUES (3, 'vireak', 'male', 'ck', '010223399', NULL, NULL, NULL, 'k', 'chinese,khmer', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for interviews
-- ----------------------------
DROP TABLE IF EXISTS `interviews`;
CREATE TABLE `interviews`  (
  `InterviewID` int(10) NOT NULL AUTO_INCREMENT,
  `RecruitID` int(11) NULL DEFAULT NULL,
  `TotalNumberOfInterview` int(11) NULL DEFAULT NULL,
  `DateOn` datetime(0) NULL DEFAULT NULL,
  `EmID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`InterviewID`) USING BTREE,
  INDEX `EmID`(`EmID`) USING BTREE,
  CONSTRAINT `interviews_ibfk_1` FOREIGN KEY (`EmID`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for majors
-- ----------------------------
DROP TABLE IF EXISTS `majors`;
CREATE TABLE `majors`  (
  `MajorId` int(10) NOT NULL AUTO_INCREMENT,
  `Major` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`MajorId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opportunitydetails
-- ----------------------------
DROP TABLE IF EXISTS `opportunitydetails`;
CREATE TABLE `opportunitydetails`  (
  `OpportunityDetailId` int(11) NOT NULL,
  `OpportunityId` int(11) NULL DEFAULT NULL,
  `dateStart` datetime(0) NULL DEFAULT NULL,
  `dateEnd` datetime(0) NULL DEFAULT NULL,
  `descrption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Actionid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`OpportunityDetailId`) USING BTREE,
  INDEX `OpportunityId`(`OpportunityId`) USING BTREE,
  INDEX `Actionid`(`Actionid`) USING BTREE,
  CONSTRAINT `opportunitydetails_ibfk_1` FOREIGN KEY (`OpportunityId`) REFERENCES `opportunitys` (`OpportunityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `opportunitydetails_ibfk_2` FOREIGN KEY (`Actionid`) REFERENCES `actions` (`Actionid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opportunitys
-- ----------------------------
DROP TABLE IF EXISTS `opportunitys`;
CREATE TABLE `opportunitys`  (
  `OpportunityId` int(10) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Priority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Done` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DateCreated` date NULL DEFAULT NULL,
  PRIMARY KEY (`OpportunityId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for overtimedetails
-- ----------------------------
DROP TABLE IF EXISTS `overtimedetails`;
CREATE TABLE `overtimedetails`  (
  `OvertimeDetailId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `OvertimeId` int(11) NULL DEFAULT NULL,
  `DayStart` date NULL DEFAULT NULL,
  `TimeStart` time(0) NULL DEFAULT NULL,
  `DayEnd` date NULL DEFAULT NULL,
  `TimeEnd` time(0) NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ForEmpId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`OvertimeDetailId`) USING BTREE,
  INDEX `ForEmpId`(`ForEmpId`) USING BTREE,
  INDEX `OvertimeId`(`OvertimeId`) USING BTREE,
  CONSTRAINT `overtimedetails_ibfk_1` FOREIGN KEY (`ForEmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `overtimedetails_ibfk_2` FOREIGN KEY (`OvertimeId`) REFERENCES `overtimes` (`OvertimeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for overtimes
-- ----------------------------
DROP TABLE IF EXISTS `overtimes`;
CREATE TABLE `overtimes`  (
  `OvertimeId` int(11) NOT NULL AUTO_INCREMENT,
  `DateCreated` datetime(0) NULL DEFAULT NULL,
  `EmpId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`OvertimeId`) USING BTREE,
  INDEX `EmpId`(`EmpId`) USING BTREE,
  CONSTRAINT `overtimes_ibfk_1` FOREIGN KEY (`EmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for payrolldetails
-- ----------------------------
DROP TABLE IF EXISTS `payrolldetails`;
CREATE TABLE `payrolldetails`  (
  `PayrollDetailId` int(10) NOT NULL AUTO_INCREMENT,
  `PayrollId` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tNormalHour` int(11) NULL DEFAULT NULL,
  `tDayOffOTHour` int(11) NULL DEFAULT NULL,
  `tOTHour` int(11) NULL DEFAULT NULL,
  `tNormalSalary` decimal(10, 2) NULL DEFAULT NULL,
  `tDayOffOTSalary` decimal(10, 2) NULL DEFAULT NULL,
  `tOTSalary` decimal(10, 2) NULL DEFAULT NULL,
  `bonus` float NULL DEFAULT NULL,
  `penalty` float NULL DEFAULT NULL,
  `BlockSalary` float NULL DEFAULT NULL,
  `ForEmpId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`PayrollDetailId`) USING BTREE,
  INDEX `PayrollId`(`PayrollId`) USING BTREE,
  INDEX `ForEmpId`(`ForEmpId`) USING BTREE,
  CONSTRAINT `payrolldetails_ibfk_1` FOREIGN KEY (`PayrollId`) REFERENCES `payrolls` (`PayrollId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payrolldetails_ibfk_2` FOREIGN KEY (`ForEmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for payrolls
-- ----------------------------
DROP TABLE IF EXISTS `payrolls`;
CREATE TABLE `payrolls`  (
  `PayrollId` int(10) NOT NULL AUTO_INCREMENT,
  `DateCreated` datetime(0) NULL DEFAULT NULL,
  `ForDate` date NULL DEFAULT NULL,
  `EmpId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`PayrollId`) USING BTREE,
  INDEX `EmpId`(`EmpId`) USING BTREE,
  CONSTRAINT `payrolls_ibfk_1` FOREIGN KEY (`EmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for positions
-- ----------------------------
DROP TABLE IF EXISTS `positions`;
CREATE TABLE `positions`  (
  `PositionID` int(11) NOT NULL AUTO_INCREMENT,
  `Position` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`PositionID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of positions
-- ----------------------------
INSERT INTO `positions` VALUES (1, 'ceo');
INSERT INTO `positions` VALUES (2, 'manager');
INSERT INTO `positions` VALUES (3, 'to');

-- ----------------------------
-- Table structure for privilages
-- ----------------------------
DROP TABLE IF EXISTS `privilages`;
CREATE TABLE `privilages`  (
  `PrivilageId` int(10) NOT NULL AUTO_INCREMENT,
  `RoleId` int(11) NULL DEFAULT NULL,
  `EventId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`PrivilageId`) USING BTREE,
  INDEX `EventId`(`EventId`) USING BTREE,
  INDEX `RoleId`(`RoleId`) USING BTREE,
  CONSTRAINT `privilages_ibfk_1` FOREIGN KEY (`EventId`) REFERENCES `events` (`EventId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `privilages_ibfk_2` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`RoleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for recruitdetails
-- ----------------------------
DROP TABLE IF EXISTS `recruitdetails`;
CREATE TABLE `recruitdetails`  (
  `RecruitDetailId` int(10) NOT NULL AUTO_INCREMENT,
  `RecruitId` int(10) NOT NULL,
  `NumberNeeded` int(10) UNSIGNED NOT NULL,
  `PositionId` int(10) NOT NULL,
  `TimeShiftId` int(10) NOT NULL,
  PRIMARY KEY (`RecruitDetailId`) USING BTREE,
  INDEX `PositionId`(`PositionId`) USING BTREE,
  INDEX `RecruitId`(`RecruitId`) USING BTREE,
  INDEX `TimeShiftId`(`TimeShiftId`) USING BTREE,
  CONSTRAINT `recruitdetails_ibfk_1` FOREIGN KEY (`PositionId`) REFERENCES `positions` (`PositionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `recruitdetails_ibfk_3` FOREIGN KEY (`RecruitId`) REFERENCES `recruits` (`RecruitId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `recruitdetails_ibfk_4` FOREIGN KEY (`TimeShiftId`) REFERENCES `timeshifts` (`TimeShiftId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for recruits
-- ----------------------------
DROP TABLE IF EXISTS `recruits`;
CREATE TABLE `recruits`  (
  `RecruitId` int(10) NOT NULL AUTO_INCREMENT,
  `DateCreated` date NOT NULL,
  `EndDate` date NOT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifiedDate` datetime(0) NULL DEFAULT NULL,
  `cEmpId` int(10) UNSIGNED NULL DEFAULT NULL,
  `mEmpId` int(10) UNSIGNED NULL DEFAULT NULL,
  `ListModify` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`RecruitId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reinters
-- ----------------------------
DROP TABLE IF EXISTS `reinters`;
CREATE TABLE `reinters`  (
  `ReInterId` int(10) NOT NULL,
  `RecruitDetailId` int(11) NOT NULL AUTO_INCREMENT,
  `InterviewOn` date NULL DEFAULT NULL,
  `Status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `result` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IntervieweeId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`RecruitDetailId`) USING BTREE,
  INDEX `ReInterId`(`ReInterId`) USING BTREE,
  INDEX `IntervieweeId`(`IntervieweeId`) USING BTREE,
  CONSTRAINT `reinters_ibfk_1` FOREIGN KEY (`ReInterId`) REFERENCES `reinterstaffs` (`ReInterID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reinters_ibfk_2` FOREIGN KEY (`IntervieweeId`) REFERENCES `interviewees` (`IntervieweeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reinterstaffs
-- ----------------------------
DROP TABLE IF EXISTS `reinterstaffs`;
CREATE TABLE `reinterstaffs`  (
  `ReInterStaffID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ReInterID` int(11) NULL DEFAULT NULL,
  `EmpID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ReInterStaffID`) USING BTREE,
  INDEX `EmpID`(`EmpID`) USING BTREE,
  INDEX `ReInterID`(`ReInterID`) USING BTREE,
  CONSTRAINT `reinterstaffs_ibfk_1` FOREIGN KEY (`EmpID`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `RoleId` int(10) NOT NULL AUTO_INCREMENT,
  `Role` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`RoleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sceheduledayoffs
-- ----------------------------
DROP TABLE IF EXISTS `sceheduledayoffs`;
CREATE TABLE `sceheduledayoffs`  (
  `SceheduleDayOff` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DayOffId` int(11) NULL DEFAULT NULL,
  `StartDate` date NULL DEFAULT NULL,
  `EndDate` date NULL DEFAULT NULL,
  `DayID` int(11) NULL DEFAULT NULL,
  `EmpId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`SceheduleDayOff`) USING BTREE,
  INDEX `EmpId`(`EmpId`) USING BTREE,
  INDEX `DayID`(`DayID`) USING BTREE,
  CONSTRAINT `sceheduledayoffs_ibfk_1` FOREIGN KEY (`EmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sceheduledayoffs_ibfk_2` FOREIGN KEY (`DayID`) REFERENCES `days` (`DayID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for servicedetails
-- ----------------------------
DROP TABLE IF EXISTS `servicedetails`;
CREATE TABLE `servicedetails`  (
  `ServiceDetailId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ServiceId` int(11) NULL DEFAULT NULL,
  `Price` decimal(10, 2) NULL DEFAULT NULL,
  `ServiceTypeId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ServiceDetailId`) USING BTREE,
  INDEX `ServiceTypeId`(`ServiceTypeId`) USING BTREE,
  INDEX `ServiceId`(`ServiceId`) USING BTREE,
  CONSTRAINT `servicedetails_ibfk_1` FOREIGN KEY (`ServiceTypeId`) REFERENCES `servicetypes` (`ServiceTypeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `servicedetails_ibfk_2` FOREIGN KEY (`ServiceId`) REFERENCES `services` (`ServiceId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for services
-- ----------------------------
DROP TABLE IF EXISTS `services`;
CREATE TABLE `services`  (
  `ServiceId` int(10) NOT NULL AUTO_INCREMENT,
  `Total` decimal(10, 2) NULL DEFAULT NULL,
  `EmpId` int(11) NULL DEFAULT NULL,
  `CustomerId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ServiceId`) USING BTREE,
  INDEX `CustomerId`(`CustomerId`) USING BTREE,
  INDEX `EmpId`(`EmpId`) USING BTREE,
  CONSTRAINT `services_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`CustomerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `services_ibfk_2` FOREIGN KEY (`EmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for servicetypes
-- ----------------------------
DROP TABLE IF EXISTS `servicetypes`;
CREATE TABLE `servicetypes`  (
  `ServiceTypeId` int(10) NOT NULL AUTO_INCREMENT,
  `ServiceType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ServiceTypeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for skills
-- ----------------------------
DROP TABLE IF EXISTS `skills`;
CREATE TABLE `skills`  (
  `SkillId` int(10) NOT NULL AUTO_INCREMENT,
  `Skill` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SkillId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of skills
-- ----------------------------
INSERT INTO `skills` VALUES (1, 'IT');
INSERT INTO `skills` VALUES (2, 'network');
INSERT INTO `skills` VALUES (3, 'web');

-- ----------------------------
-- Table structure for studyrecords
-- ----------------------------
DROP TABLE IF EXISTS `studyrecords`;
CREATE TABLE `studyrecords`  (
  `StudyRecordId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `StudyRecordTypeId` int(11) NULL DEFAULT NULL,
  `MajorId` int(11) NULL DEFAULT NULL,
  `CatID` int(11) NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SkillId` int(11) NULL DEFAULT NULL,
  `EndYear` date NULL DEFAULT NULL,
  `IntervieweeId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`StudyRecordId`) USING BTREE,
  INDEX `SkillId`(`SkillId`) USING BTREE,
  INDEX `StudyRecordTypeId`(`StudyRecordTypeId`) USING BTREE,
  INDEX `IntervieweeId`(`IntervieweeId`) USING BTREE,
  INDEX `MajorId`(`MajorId`) USING BTREE,
  INDEX `CatID`(`CatID`) USING BTREE,
  CONSTRAINT `studyrecords_ibfk_1` FOREIGN KEY (`SkillId`) REFERENCES `skills` (`SkillId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studyrecords_ibfk_2` FOREIGN KEY (`StudyRecordTypeId`) REFERENCES `studyrecordtypes` (`StudyRecordTypeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studyrecords_ibfk_3` FOREIGN KEY (`IntervieweeId`) REFERENCES `interviewees` (`IntervieweeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studyrecords_ibfk_4` FOREIGN KEY (`MajorId`) REFERENCES `majors` (`MajorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studyrecords_ibfk_5` FOREIGN KEY (`CatID`) REFERENCES `categorys` (`CatID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studyrecords_ibfk_6` FOREIGN KEY (`SkillId`) REFERENCES `skills` (`SkillId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for studyrecordtypes
-- ----------------------------
DROP TABLE IF EXISTS `studyrecordtypes`;
CREATE TABLE `studyrecordtypes`  (
  `StudyRecordTypeId` int(10) NOT NULL AUTO_INCREMENT,
  `StudyRecordType` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`StudyRecordTypeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for takeleavedetails
-- ----------------------------
DROP TABLE IF EXISTS `takeleavedetails`;
CREATE TABLE `takeleavedetails`  (
  `TakeLeaveDetailId` int(10) NOT NULL AUTO_INCREMENT,
  `TakeLeaveId` int(11) NULL DEFAULT NULL,
  `EmpID` int(11) NULL DEFAULT NULL,
  `StartDate` date NULL DEFAULT NULL,
  `EndDate` date NULL DEFAULT NULL,
  `StartTime` time(0) NULL DEFAULT NULL,
  `EndTime` time(0) NULL DEFAULT NULL,
  `Reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`TakeLeaveDetailId`) USING BTREE,
  INDEX `TakeLeaveId`(`TakeLeaveId`) USING BTREE,
  CONSTRAINT `takeleavedetails_ibfk_1` FOREIGN KEY (`TakeLeaveId`) REFERENCES `takeleaves` (`TakeLeaveId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for takeleaves
-- ----------------------------
DROP TABLE IF EXISTS `takeleaves`;
CREATE TABLE `takeleaves`  (
  `TakeLeaveId` int(10) NOT NULL AUTO_INCREMENT,
  `EmpId` int(11) NULL DEFAULT NULL,
  `Approval` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ApproveDate` datetime(0) NULL DEFAULT NULL,
  `Status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`TakeLeaveId`) USING BTREE,
  INDEX `EmpId`(`EmpId`) USING BTREE,
  CONSTRAINT `takeleaves_ibfk_1` FOREIGN KEY (`EmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for timeshifts
-- ----------------------------
DROP TABLE IF EXISTS `timeshifts`;
CREATE TABLE `timeshifts`  (
  `TimeShiftId` int(10) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`TimeShiftId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trainingcourserecords
-- ----------------------------
DROP TABLE IF EXISTS `trainingcourserecords`;
CREATE TABLE `trainingcourserecords`  (
  `TrainingCourseId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TrainingCourseTypeId` int(11) NULL DEFAULT NULL,
  `SkillId` int(11) NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DateStart` date NULL DEFAULT NULL,
  `DateEnd` date NULL DEFAULT NULL,
  `IntervieweeId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`TrainingCourseId`) USING BTREE,
  INDEX `TrainingCourseTypeId`(`TrainingCourseTypeId`) USING BTREE,
  INDEX `SkillId`(`SkillId`) USING BTREE,
  INDEX `IntervieweeId`(`IntervieweeId`) USING BTREE,
  CONSTRAINT `trainingcourserecords_ibfk_1` FOREIGN KEY (`TrainingCourseTypeId`) REFERENCES `trainingcoursetypes` (`TrainingCourseTypeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trainingcourserecords_ibfk_2` FOREIGN KEY (`SkillId`) REFERENCES `skills` (`SkillId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trainingcourserecords_ibfk_3` FOREIGN KEY (`IntervieweeId`) REFERENCES `interviewees` (`IntervieweeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trainingcoursetypes
-- ----------------------------
DROP TABLE IF EXISTS `trainingcoursetypes`;
CREATE TABLE `trainingcoursetypes`  (
  `TrainingCourseTypeId` int(10) NOT NULL AUTO_INCREMENT,
  `TrainingCourseType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`TrainingCourseTypeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trainingtypes
-- ----------------------------
DROP TABLE IF EXISTS `trainingtypes`;
CREATE TABLE `trainingtypes`  (
  `TrainingTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `TrainingType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Duration` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`TrainingTypeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trainningdetails
-- ----------------------------
DROP TABLE IF EXISTS `trainningdetails`;
CREATE TABLE `trainningdetails`  (
  `TrainingDetailId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TrainingId` int(11) NULL DEFAULT NULL,
  `DateStart` date NULL DEFAULT NULL,
  `DateEnd` date NULL DEFAULT NULL,
  `EmpId` int(11) NULL DEFAULT NULL,
  `TrainingTypeID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`TrainingDetailId`) USING BTREE,
  INDEX `EmpId`(`EmpId`) USING BTREE,
  INDEX `TrainingId`(`TrainingId`) USING BTREE,
  INDEX `TrainingTypeID`(`TrainingTypeID`) USING BTREE,
  CONSTRAINT `trainningdetails_ibfk_1` FOREIGN KEY (`EmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trainningdetails_ibfk_3` FOREIGN KEY (`TrainingId`) REFERENCES `trainnings` (`TrainingId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trainningdetails_ibfk_4` FOREIGN KEY (`TrainingTypeID`) REFERENCES `trainingtypes` (`TrainingTypeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trainnings
-- ----------------------------
DROP TABLE IF EXISTS `trainnings`;
CREATE TABLE `trainnings`  (
  `TrainingId` int(10) NOT NULL AUTO_INCREMENT,
  `DateCreated` date NULL DEFAULT NULL,
  `EmpId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`TrainingId`) USING BTREE,
  INDEX `EmpId`(`EmpId`) USING BTREE,
  CONSTRAINT `trainnings_ibfk_1` FOREIGN KEY (`EmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `UserId` int(10) NOT NULL AUTO_INCREMENT,
  `Username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `active` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RoleId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`UserId`) USING BTREE,
  INDEX `RoleId`(`RoleId`) USING BTREE,
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`RoleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vehicles
-- ----------------------------
DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE `vehicles`  (
  `VehicleId` int(11) NOT NULL,
  `VehicleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Cost` decimal(10, 2) NULL DEFAULT NULL,
  `SalvageValue` decimal(10, 2) NULL DEFAULT NULL,
  `SellPrice` decimal(10, 2) NULL DEFAULT NULL,
  `EmpId` int(11) NULL DEFAULT NULL,
  `LifeSpan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BuyDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`VehicleId`) USING BTREE,
  INDEX `EmpId`(`EmpId`) USING BTREE,
  CONSTRAINT `vehicles_ibfk_1` FOREIGN KEY (`EmpId`) REFERENCES `employees` (`EmpId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for workdays
-- ----------------------------
DROP TABLE IF EXISTS `workdays`;
CREATE TABLE `workdays`  (
  `WorkDayId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DayStart` date NULL DEFAULT NULL,
  `DayEnd` date NULL DEFAULT NULL,
  `TimeStart` time(0) NULL DEFAULT NULL,
  `TimeEnd` time(0) NULL DEFAULT NULL,
  `WorkHour` int(11) NULL DEFAULT NULL,
  `TimeShiftId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`WorkDayId`) USING BTREE,
  INDEX `TimeShiftId`(`TimeShiftId`) USING BTREE,
  CONSTRAINT `workdays_ibfk_1` FOREIGN KEY (`TimeShiftId`) REFERENCES `timeshifts` (`TimeShiftId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for workexperiences
-- ----------------------------
DROP TABLE IF EXISTS `workexperiences`;
CREATE TABLE `workexperiences`  (
  `WorkExperienceId` int(10) NOT NULL AUTO_INCREMENT,
  `PositionId` int(11) NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SkillId` int(11) NULL DEFAULT NULL,
  `DateStart` date NULL DEFAULT NULL,
  `DateEnd` date NULL DEFAULT NULL,
  `IntervieweeId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`WorkExperienceId`) USING BTREE,
  INDEX `PositionId`(`PositionId`) USING BTREE,
  INDEX `IntervieweeId`(`IntervieweeId`) USING BTREE,
  CONSTRAINT `workexperiences_ibfk_1` FOREIGN KEY (`PositionId`) REFERENCES `positions` (`PositionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workexperiences_ibfk_2` FOREIGN KEY (`IntervieweeId`) REFERENCES `interviewees` (`IntervieweeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workexperiences
-- ----------------------------
INSERT INTO `workexperiences` VALUES (1, 1, 'something', NULL, '2019-03-01', '2019-03-31', 1);
INSERT INTO `workexperiences` VALUES (2, 3, 'saysomething', NULL, '2019-02-01', '2019-07-31', 3);

SET FOREIGN_KEY_CHECKS = 1;
