#------------------------------------------------------------
#               Script MySQL. EXAM-PLANEUR
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
    idSlot   Char(36) NOT NULL PRIMARY KEY,
    `day`    Date     NOT NULL,
    `hour`   Float    NOT NULL,
    duration Float    NOT NULL

) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Exam
#------------------------------------------------------------

CREATE TABLE Exam
(
    idExam   Char(36)                     NOT NULL PRIMARY KEY,
    `name`   Varchar(50)                  NOT NULL,
    duration Float                        NOT NULL,
    type     Enum ('Final', 'Continuous') NOT NULL,
    idSlot   Char(36),

    CONSTRAINT Exam_Slot_FK FOREIGN KEY (idSlot) REFERENCES Slot (idSlot)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Manager
#------------------------------------------------------------

CREATE TABLE Manager
(
    id        Char(36)                     NOT NULL PRIMARY KEY,
    civility  Enum ('Men','Women','Other') NOT NULL,
    lastname  Varchar(63)                  NOT NULL,
    firstname Varchar(63)                  NOT NULL,
    idExam    Char(36),

    CONSTRAINT Manager_Exam_FK FOREIGN KEY (idExam) REFERENCES Exam (idExam)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Room
#------------------------------------------------------------

CREATE TABLE Room
(
    `name` Varchar(63)    NOT NULL PRIMARY KEY,
    places Int            NOT NULL,
    type   Enum ('Salle') NOT NULL,
    idSlot Char(36),
    idExam Char(36),

    CONSTRAINT Room_Slot_FK FOREIGN KEY (idSlot) REFERENCES Slot (idSlot),
    CONSTRAINT Room_Exam0_FK FOREIGN KEY (idExam) REFERENCES Exam (idExam)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE User
(
    id         Char(36)     NOT NULL PRIMARY KEY,
    mail       Varchar(100) NOT NULL,
    department Varchar(63),
    id_Manager Char(36),


    CONSTRAINT User_Department_FK FOREIGN KEY (department) REFERENCES Department (`name`),
    CONSTRAINT User_Manager0_FK FOREIGN KEY (id_Manager) REFERENCES Manager (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: Group
#----------------------------------------------x--------------

CREATE TABLE `Group`
(
    idGroup                           Char(36)    NOT NULL PRIMARY KEY,
    `name`                            Varchar(63) NOT NULL,
    containReducedMobilityPerson      Bool        NOT NULL,
    personsWithoutAdjustmentNumber    Int         NOT NULL,
    numberOfStudentsWithWritingNeeds  Int         NOT NULL,
    numberOfStudentsWithIsolatedRooms Int         NOT NULL,
    numberOfStudentsWithPartTime      Int         NOT NULL
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
# Table: previousExam
#------------------------------------------------------------

CREATE TABLE _ExamToExam
(
    parent Char(36) NOT NULL,
    child  Char(36) NOT NULL,

    CONSTRAINT previousExam_PK PRIMARY KEY (parent, child),

    CONSTRAINT previousExam_Exam_FK FOREIGN KEY (parent) REFERENCES Exam (idExam),
    CONSTRAINT previousExam_Exam0_FK FOREIGN KEY (child) REFERENCES Exam (idExam)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: ExamToDepartment
#------------------------------------------------------------

CREATE TABLE _ExamToDepartment
(
    exam       Char(36)    NOT NULL,
    department Varchar(50) NOT NULL,

    CONSTRAINT ExamToDepartment_PK PRIMARY KEY (exam, department),

    CONSTRAINT ExamToDepartment_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (idExam),
    CONSTRAINT ExamToDepartment_Department0_FK FOREIGN KEY (department) REFERENCES Department (name)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: ExamToGroup
#------------------------------------------------------------

CREATE TABLE _ExamToGroup
(
    exam    Char(36) NOT NULL,
    `group` Char(36) NOT NULL,

    CONSTRAINT ExamToGroup_PK PRIMARY KEY (exam, `group`),

    CONSTRAINT ExamToGroup_Exam_FK FOREIGN KEY (exam) REFERENCES Exam (idExam),
    CONSTRAINT ExamToGroup__Group0_FK FOREIGN KEY (`group`) REFERENCES `Group` (idGroup)
) ENGINE = InnoDB;

