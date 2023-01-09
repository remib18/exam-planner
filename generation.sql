#------------------------------------------------------------
#               Script MySQL. EXAM-PLANNER
#------------------------------------------------------------

CREATE DATABASE IF NOT EXISTS exam_planner;

use exam_planner;

#------------------------------------------------------------
# Table: Department
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Department
(
    `name` Varchar(63) NOT NULL PRIMARY KEY
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Subject
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Subject
(
    `name` Varchar(63) NOT NULL PRIMARY KEY
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: MockUp
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS MockUp
(
    `name`     Varchar(63)                 NOT NULL PRIMARY KEY,
    degree     Enum ('Bachelor', 'Master') NOT NULL,
    `year`     Enum ('One','Two','Three')  NOT NULL,
    semester   Enum ('One','Two')          NOT NULL,
    department Varchar(63)                 NOT NULL,

    CONSTRAINT MockUp_Department_FK FOREIGN KEY (department) REFERENCES Department (`name`)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Slot
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Slot
(
    id       CHAR(36) NOT NULL PRIMARY KEY,
    `day`    Date     NOT NULL,
    `hour`   Float    NOT NULL,
    duration Float    NOT NULL
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Exam
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Exam
(
    id       CHAR(36)                     NOT NULL PRIMARY KEY,
    `name`   Varchar(50)                  NOT NULL,
    duration Float                        NOT NULL,
    type     Enum ('Final', 'Continuous') NOT NULL,
    subject  Varchar(50)                  NOT NULL,

    CONSTRAINT Exam_Subject_FK FOREIGN KEY (subject) REFERENCES Subject (name)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Manager
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Manager
(
    id        CHAR(36)                     NOT NULL PRIMARY KEY,
    civility  Enum ('Man','Woman','Other') NOT NULL,
    lastname  Varchar(63)                  NOT NULL,
    firstname Varchar(63)                  NOT NULL

) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Room
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Room
(
    `name`              Varchar(63) NOT NULL PRIMARY KEY,
    places              Int         NOT NULL,
    types               SET (
        'Amphitheater',
        'ComputerRoom',
        'Laboratory',
        'Library',
        'Office')                   NOT NULL,
    equipments          SET (
        'Projector',
        'Speaker',
        'Board',
        'Webcam'),
    computerEnvironment SET (
        'OfficeApplication',
        'InternetAccess',
        'LinuxEnvironment',
        'WindowsEnvironment',
        'MacOsEnvironment',
        'ProgrammingApplication',
        'PhysicsApplication',
        'MathApplication',
        'ChemistryApplication')
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `User`
(
    id         CHAR(36)     NOT NULL PRIMARY KEY,
    mail       Varchar(100) NOT NULL,
    department Varchar(63) DEFAULT NULL,
    manager    CHAR(36)    DEFAULT NULL,
    `password` Varchar(128) NOT NULL,

    CONSTRAINT User_Department_FK FOREIGN KEY (department) REFERENCES Department (`name`),
    CONSTRAINT User_Manager_FK FOREIGN KEY (manager) REFERENCES Manager (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: _Group
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `Group`
(
    id                                CHAR(36)    NOT NULL PRIMARY KEY,
    `name`                            Varchar(63) NOT NULL,
    containReducedMobilityPerson      Bool        NOT NULL DEFAULT false,
    numberOfStudentsWithoutAdjustment Int         NOT NULL DEFAULT 0,
    numberOfStudentsWithWritingNeeds  Int         NOT NULL DEFAULT 0,
    numberOfStudentsWithIsolatedRooms Int         NOT NULL DEFAULT 0,
    numberOfStudentsWithPartTime      Int         NOT NULL DEFAULT 0
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: SubjectToMockUp
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _SubjectToMockUp
(
    `subject` Varchar(50) NOT NULL,
    mockUp    Varchar(50) NOT NULL,

    CONSTRAINT SubjectToMockUp_PK PRIMARY KEY (`subject`, mockUp),
    CONSTRAINT SubjectToMockUp_Subject_FK FOREIGN KEY (`subject`) REFERENCES Subject (name),
    CONSTRAINT SubjectToMockUp_MockUp_FK FOREIGN KEY (mockUp) REFERENCES MockUp (name)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: ExamToManager
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _ExamToManager
(
    exam    CHAR(36) NOT NULL,
    manager CHAR(36) NOT NULL,

    CONSTRAINT _ExamToManager_PK PRIMARY KEY (exam, manager),
    CONSTRAINT _ExamToManager_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id),
    CONSTRAINT _ExamToManager_Manager_FK FOREIGN KEY (manager) REFERENCES Manager (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: _ExamToExam
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _ExamToExam
(
    parent CHAR(36) NOT NULL,
    child  CHAR(36) NOT NULL,

    CONSTRAINT _ExamToExam_PK PRIMARY KEY (parent, child),
    CONSTRAINT _ExamToExam_Exam_parent_FK FOREIGN KEY (parent) REFERENCES Exam (id),
    CONSTRAINT _ExamToExam_Exam_child_FK FOREIGN KEY (child) REFERENCES Exam (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: _GroupToGroup
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _GroupToGroup
(
    parent CHAR(36) NOT NULL,
    child  CHAR(36) NOT NULL,

    CONSTRAINT __GroupToGroup_PK PRIMARY KEY (parent, child),
    CONSTRAINT __GroupToGroup_Group_parent_FK FOREIGN KEY (parent) REFERENCES `Group` (id),
    CONSTRAINT __GroupToGroup_Group_child_FK FOREIGN KEY (child) REFERENCES `Group` (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: _ExamToDepartment
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _ExamToDepartment
(
    exam       CHAR(36)    NOT NULL,
    department Varchar(50) NOT NULL,

    CONSTRAINT _ExamToDepartment_PK PRIMARY KEY (exam, department),
    CONSTRAINT _ExamToDepartment_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id),
    CONSTRAINT _ExamToDepartment_Department_FK FOREIGN KEY (department) REFERENCES Department (name)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _ExamToRoom
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _ExamToRoom
(
    exam CHAR(36)    NOT NULL,
    room Varchar(63) NOT NULL,

    CONSTRAINT _ExamToRoom_PK PRIMARY KEY (exam, room),
    CONSTRAINT _ExamToRoom_Room_FK FOREIGN KEY (room) REFERENCES Room (name),
    CONSTRAINT _ExamToRoom_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _SlotToRoom
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _SlotToRoom
(
    slot CHAR(36)    NOT NULL,
    room Varchar(63) NOT NULL,

    CONSTRAINT _SlotToRoom_PK PRIMARY KEY (slot, room),
    CONSTRAINT _SlotToRoom_Room_FK FOREIGN KEY (room) REFERENCES Room (name),
    CONSTRAINT _SlotToRoom_Exam_FK FOREIGN KEY (slot) REFERENCES Slot (id)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _ExamToGroup
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _ExamToGroup
(
    exam    CHAR(36) NOT NULL,
    `group` CHAR(36) NOT NULL,

    CONSTRAINT _ExamToGroup_PK PRIMARY KEY (exam, `group`),
    CONSTRAINT _ExamToGroup_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id),
    CONSTRAINT _ExamToGroup_Group_FK FOREIGN KEY (`group`) REFERENCES `Group` (id)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _ExamToSlot
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS _ExamToSlot
(
    exam CHAR(36) NOT NULL,
    slot CHAR(36) NOT NULL,

    CONSTRAINT _ExamToSlot_PK PRIMARY KEY (exam, slot),
    CONSTRAINT _ExamToSlot_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id),
    CONSTRAINT _ExamToSlot_Group_FK FOREIGN KEY (slot) REFERENCES Slot (id)
) ENGINE = InnoDB;