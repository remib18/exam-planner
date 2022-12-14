#------------------------------------------------------------
#               Script MySQL. EXAM-PLANNER
#------------------------------------------------------------

#------------------------------------------------------------
# Table: Department
#------------------------------------------------------------

CREATE TABLE Department
(
    `name` Varchar(63) NOT NULL PRIMARY KEY
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Subject
#------------------------------------------------------------

CREATE TABLE Subject
(
    `name` Varchar(63) NOT NULL PRIMARY KEY
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: MockUp
#------------------------------------------------------------

CREATE TABLE MockUp
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

CREATE TABLE Slot
(
    id       Char(36) NOT NULL PRIMARY KEY DEFAULT UUID(),
    `day`    Date     NOT NULL,
    `hour`   Float    NOT NULL,
    duration Float    NOT NULL

) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Exam
#------------------------------------------------------------

CREATE TABLE Exam
(
    id       Char(36)                     NOT NULL PRIMARY KEY DEFAULT UUID(),
    `name`   Varchar(50)                  NOT NULL,
    duration Float                        NOT NULL,
    type     Enum ('Final', 'Continuous') NOT NULL,
    subject  Varchar(50)                  NOT NULL,

    CONSTRAINT Exam_Subject_FK FOREIGN KEY (subject) REFERENCES Subject (name)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Manager
#------------------------------------------------------------

CREATE TABLE Manager
(
    id        Char(36)                     NOT NULL PRIMARY KEY DEFAULT UUID(),
    civility  Enum ('Men','Women','Other') NOT NULL,
    lastname  Varchar(63)                  NOT NULL,
    firstname Varchar(63)                  NOT NULL

) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Room
#------------------------------------------------------------

CREATE TABLE Room
(
    `name`              Varchar(63) NOT NULL PRIMARY KEY DEFAULT UUID(),
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

CREATE TABLE `User`
(
    id         Char(36)     NOT NULL PRIMARY KEY DEFAULT UUID(),
    mail       Varchar(100) NOT NULL,
    department Varchar(63)                       DEFAULT NULL,
    manager    Char(36)                          DEFAULT NULL,
    password   Varchar(63)  NOT NULL,

    CONSTRAINT User_Department_FK FOREIGN KEY (department) REFERENCES Department (`name`),
    CONSTRAINT User_Manager0_FK FOREIGN KEY (manager) REFERENCES Manager (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: _Group
#------------------------------------------------------------

CREATE TABLE `Group`
(
    id                                Char(36)    NOT NULL PRIMARY KEY DEFAULT UUID(),
    `name`                            Varchar(63) NOT NULL,
    containReducedMobilityPerson      Bool        NOT NULL             DEFAULT false,
    personsWithoutAdjustmentNumber    Int         NOT NULL             DEFAULT 0,
    numberOfStudentsWithWritingNeeds  Int         NOT NULL             DEFAULT 0,
    numberOfStudentsWithIsolatedRooms Int         NOT NULL             DEFAULT 0,
    numberOfStudentsWithPartTime      Int         NOT NULL             DEFAULT 0
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: SubjectToMockUp
#------------------------------------------------------------

CREATE TABLE _SubjectToMockUp
(
    `subject` Varchar(50) NOT NULL,
    mockUp    Varchar(50) NOT NULL,

    CONSTRAINT SubjectToMockUp_PK PRIMARY KEY (`subject`, mockUp),

    CONSTRAINT SubjectToMockUp_Subject_FK FOREIGN KEY (`subject`) REFERENCES Subject (name),
    CONSTRAINT SubjectToMockUp_MockUp0_FK FOREIGN KEY (mockUp) REFERENCES MockUp (name)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: ExamToManager
#------------------------------------------------------------

CREATE TABLE _ExamToManager
(
    exam    Char(36) NOT NULL,
    manager Char(36) NOT NULL,

    CONSTRAINT manage_PK PRIMARY KEY (exam, manager),
    CONSTRAINT manage_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id),
    CONSTRAINT manage_Manager0_FK FOREIGN KEY (manager) REFERENCES Manager (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: _ExamToExam
#------------------------------------------------------------

CREATE TABLE _ExamToExam
(
    parent Char(36) NOT NULL,
    child  Char(36) NOT NULL,

    CONSTRAINT previousExam_PK PRIMARY KEY (parent, child),

    CONSTRAINT previousExam_Exam_FK FOREIGN KEY (parent) REFERENCES Exam (id),
    CONSTRAINT previousExam_Exam0_FK FOREIGN KEY (child) REFERENCES Exam (id)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _ExamToDepartment
#------------------------------------------------------------

CREATE TABLE _ExamToDepartment
(
    exam       Char(36)    NOT NULL,
    department Varchar(50) NOT NULL,

    CONSTRAINT ExamToDepartment_PK PRIMARY KEY (exam, department),

    CONSTRAINT ExamToDepartment_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id),
    CONSTRAINT ExamToDepartment_Department0_FK FOREIGN KEY (department) REFERENCES Department (name)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _ExamToRoom
#------------------------------------------------------------

CREATE TABLE _ExamToRoom
(
    exam Char(36)    NOT NULL,
    room Varchar(63) NOT NULL,

    CONSTRAINT require_PK PRIMARY KEY (exam, room),

    CONSTRAINT require_Room_FK FOREIGN KEY (room) REFERENCES Room (name),
    CONSTRAINT require_Exam0_FK FOREIGN KEY (exam) REFERENCES Exam (id)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _SlotToRoom
#------------------------------------------------------------

CREATE TABLE _SlotToRoom
(
    slot Char(36)    NOT NULL,
    room Varchar(63) NOT NULL,

    CONSTRAINT require_PK PRIMARY KEY (slot, room),

    CONSTRAINT require_Room_FK FOREIGN KEY (room) REFERENCES Room (name),
    CONSTRAINT require_Exam0_FK FOREIGN KEY (slot) REFERENCES Slot (id)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _ExamToGroup
#------------------------------------------------------------

CREATE TABLE _ExamToGroup
(
    exam    Char(36) NOT NULL,
    `group` Char(36) NOT NULL,

    CONSTRAINT ExamToGroup_PK PRIMARY KEY (exam, `group`),

    CONSTRAINT ExamToGroup_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id),
    CONSTRAINT ExamToGroup__Group0_FK FOREIGN KEY (`group`) REFERENCES `Group` (id)
) ENGINE = InnoDB;

#------------------------------------------------------------
# Table: _ExamToSlot
#------------------------------------------------------------

CREATE TABLE _ExamToSlot
(
    exam Char(36) NOT NULL,
    slot Char(36) NOT NULL,

    CONSTRAINT ExamToGroup_PK PRIMARY KEY (exam, slot),

    CONSTRAINT ExamToGroup_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (id),
    CONSTRAINT ExamToGroup__Group0_FK FOREIGN KEY (slot) REFERENCES Slot (id)
) ENGINE = InnoDB;