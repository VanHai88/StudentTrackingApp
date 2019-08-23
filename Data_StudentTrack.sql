-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema student_tracking
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema student_tracking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `student_tracking` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `student_tracking` ;

-- -----------------------------------------------------
-- Table `student_tracking`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`courses` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME_COURSES` VARCHAR(45) NULL DEFAULT NULL,
  `DETAIL` TEXT NULL DEFAULT NULL,
  `DURATION` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`classes` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME_CLASS` VARCHAR(45) NULL DEFAULT NULL,
  `TIMETABLE` TEXT NULL DEFAULT NULL,
  `FEE` DOUBLE NULL DEFAULT NULL,
  `DATE_BEGIN` DATE NULL DEFAULT NULL,
  `DATE_END` DATE NULL DEFAULT NULL,
  `MIN_QTY_STUDENTS` INT(11) NULL DEFAULT NULL,
  `MAX_QTY_STUDENTS` INT(11) NULL DEFAULT NULL,
  `COURSE_ID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `COURSE_ID` (`COURSE_ID` ASC) VISIBLE,
  CONSTRAINT `classes_ibfk_1`
    FOREIGN KEY (`COURSE_ID`)
    REFERENCES `student_tracking`.`courses` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`classes_has_students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`classes_has_students` (
  `STUDENT_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TYPE_STUDENT` VARCHAR(45) NOT NULL,
  `STATUS_STUDENT` VARCHAR(45) NULL DEFAULT NULL,
  `CLASSES_ID` INT(11) NOT NULL,
  PRIMARY KEY (`STUDENT_ID`, `TYPE_STUDENT`, `CLASSES_ID`),
  INDEX `fk_CLASSES_HAS_STUDENTS_CLASSES1_idx` (`CLASSES_ID` ASC) VISIBLE,
  CONSTRAINT `fk_CLASSES_HAS_STUDENTS_CLASSES1`
    FOREIGN KEY (`CLASSES_ID`)
    REFERENCES `student_tracking`.`classes` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`course_structure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`course_structure` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `CHAPTER_NAME` VARCHAR(200) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `COURSES_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`, `COURSES_ID`),
  INDEX `fk_COURSE_STRUCTURE_COURSES_idx` (`COURSES_ID` ASC) VISIBLE,
  CONSTRAINT `fk_COURSE_STRUCTURE_COURSES`
    FOREIGN KEY (`COURSES_ID`)
    REFERENCES `student_tracking`.`courses` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`lecturers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`lecturers` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME_LECTURERS` VARCHAR(45) NULL DEFAULT NULL,
  `JOB_DESCRIPTION` VARCHAR(45) NULL DEFAULT NULL,
  `WAGE` DOUBLE NULL DEFAULT NULL,
  `ADDRESS` TEXT NULL DEFAULT NULL,
  `DATE_OF_BIRTH` DATE NULL DEFAULT NULL,
  `STATUS_LECTURERS` VARCHAR(45) NULL DEFAULT NULL,
  `EMAIL` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`lecturers_has_classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`lecturers_has_classes` (
  `LECTURERS_ID` INT(11) NOT NULL,
  `CLASSES_ID` INT(11) NOT NULL,
  PRIMARY KEY (`LECTURERS_ID`, `CLASSES_ID`),
  INDEX `fk_LECTURERS_has_CLASSES_CLASSES1_idx` (`CLASSES_ID` ASC) VISIBLE,
  INDEX `fk_LECTURERS_has_CLASSES_LECTURERS1_idx` (`LECTURERS_ID` ASC) VISIBLE,
  CONSTRAINT `fk_LECTURERS_has_CLASSES_CLASSES1`
    FOREIGN KEY (`CLASSES_ID`)
    REFERENCES `student_tracking`.`classes` (`ID`),
  CONSTRAINT `fk_LECTURERS_has_CLASSES_LECTURERS1`
    FOREIGN KEY (`LECTURERS_ID`)
    REFERENCES `student_tracking`.`lecturers` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`skills`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`skills` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TECH` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`lecturers_has_skills`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`lecturers_has_skills` (
  `LECTURERS_ID` INT(11) NOT NULL,
  `SKILLS_ID` INT(11) NOT NULL,
  `EXPY` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`LECTURERS_ID`, `SKILLS_ID`),
  INDEX `fk_LECTURERS_has_SKILLS_SKILLS1_idx` (`SKILLS_ID` ASC) VISIBLE,
  INDEX `fk_LECTURERS_has_SKILLS_LECTURERS1_idx` (`LECTURERS_ID` ASC) VISIBLE,
  CONSTRAINT `fk_LECTURERS_has_SKILLS_LECTURERS1`
    FOREIGN KEY (`LECTURERS_ID`)
    REFERENCES `student_tracking`.`lecturers` (`ID`),
  CONSTRAINT `fk_LECTURERS_has_SKILLS_SKILLS1`
    FOREIGN KEY (`SKILLS_ID`)
    REFERENCES `student_tracking`.`skills` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`non_students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`non_students` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `FULLNAME` VARCHAR(45) NULL DEFAULT NULL,
  `PHONE` CHAR(11) NULL DEFAULT NULL,
  `ADDRESS` TEXT NULL DEFAULT NULL,
  `EMAIL` VARCHAR(45) NULL DEFAULT NULL,
  `DATE_OF_BIRTH` DATE NULL DEFAULT NULL,
  `FBLINK` VARCHAR(200) NULL DEFAULT NULL,
  `CURRENT_WORKING` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `student_tracking`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tracking`.`students` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `FULLNAME` VARCHAR(45) NULL DEFAULT NULL,
  `PHONE` CHAR(11) NULL DEFAULT NULL,
  `ADDRESS` TEXT NULL DEFAULT NULL,
  `EMAIL` VARCHAR(45) NULL DEFAULT NULL,
  `DATE_OF_BIRTH` DATE NULL DEFAULT NULL,
  `FBLINK` VARCHAR(200) NULL DEFAULT NULL,
  `YEARTH` INT(11) NULL DEFAULT NULL,
  `SCHOOL_NAME` VARCHAR(45) NULL DEFAULT NULL,
  `CURRENT_WORKING` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
