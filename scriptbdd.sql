#------------------------------------------------------------
#               Script MySQL. EXAM-PLANNEUR
#------------------------------------------------------------

#------------------------------------------------------------
# Table: Department
#------------------------------------------------------------

CREATE TABLE Department(
    name Varchar(50) NOT NULL PRIMARY KEY
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Subject
#------------------------------------------------------------

CREATE TABLE Subject(
    name Varchar(50) NOT NULL PRIMARY KEY
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: MockUp
#------------------------------------------------------------

CREATE TABLE MockUp
(
    name           Varchar(50)                NOT NULL PRIMARY KEY,
    degree         Enum ('Master')            NOT NULL,
    year           Enum ('One','Two','Three') NOT NULL,
    semester       Enum ('One','Two')         NOT NULL,
    DepartmentName Varchar(50)                NOT NULL,

    CONSTRAINT MockUp_Department_FK FOREIGN KEY (name) REFERENCES Department (name)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Slot
#------------------------------------------------------------

CREATE TABLE Slot
(
    idSlot   Char(36) NOT NULL PRIMARY KEY,
    day      Date     NOT NULL,
    hour     Float    NOT NULL,
    duration Float    NOT NULL

)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Exam
#------------------------------------------------------------

CREATE TABLE Exam
(
    idExam   Char(36)           NOT NULL PRIMARY KEY,
    name     Varchar(50)        NOT NULL,
    duration Float              NOT NULL,
    type     Enum ('FinalExam') NOT NULL,
    idSlot   Char(36),

    CONSTRAINT Exam_Slot_FK FOREIGN KEY (idSlot) REFERENCES Slot (idSlot)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Manager
#------------------------------------------------------------

CREATE TABLE Manager
(
    id        Char(36)                     NOT NULL PRIMARY KEY,
    civility  Enum ('Men','Women','Other') NOT NULL,
    lastname  Varchar(50)                  NOT NULL,
    firstname Varchar(50)                  NOT NULL,
    idExam    Char(36),

    CONSTRAINT Manager_Exam_FK FOREIGN KEY (idExam) REFERENCES Exam (idExam)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Room
#------------------------------------------------------------

CREATE TABLE Room
(
    name   Varchar(50)    NOT NULL PRIMARY KEY,
    places Int            NOT NULL,
    type   Enum ('Salle') NOT NULL,
    idSlot Char(36),
    idExam Char(36),

    CONSTRAINT Room_Slot_FK FOREIGN KEY (idSlot) REFERENCES Slot (idSlot),
    CONSTRAINT Room_Exam0_FK FOREIGN KEY (idExam) REFERENCES Exam (idExam)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE User
(
    id         Char(36)     NOT NULL PRIMARY KEY,
    mail       Varchar(100) NOT NULL,
    name       Varchar(50),
    id_Manager Char(36),


    CONSTRAINT User_Department_FK FOREIGN KEY (name) REFERENCES Department (name),
    CONSTRAINT User_Manager0_FK FOREIGN KEY (id_Manager) REFERENCES Manager (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: _Group
#------------------------------------------------------------

CREATE TABLE _Group
(
    idGroup                           Char(36)    NOT NULL PRIMARY KEY,
    name                              Varchar(50) NOT NULL,
    containReductedMobilityPerson     Bool        NOT NULL,
    personsWithoutAdjustmentNumber    Int         NOT NULL,
    numberOfStudentsWithWritingNeeds  Int         NOT NULL,
    numberOfStudentsWithIsolatedRooms Int         NOT NULL,
    numberOfStudentsWithPartTime      Int         NOT NULL
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SubjectToMockUp
#------------------------------------------------------------

CREATE TABLE SubjectToMockUp
(
    nameSubject Varchar(50) NOT NULL,
    nameMockUp  Varchar(50) NOT NULL,
    CONSTRAINT SubjectToMockUp_PK PRIMARY KEY (nameSubject, nameMockUp),

    CONSTRAINT SubjectToMockUp_Subject_FK FOREIGN KEY (nameSubject) REFERENCES Subject (name),
    CONSTRAINT SubjectToMockUp_MockUp0_FK FOREIGN KEY (nameMockUp) REFERENCES MockUp (name)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: previousExam
#------------------------------------------------------------

CREATE TABLE previousExam
(
    idExam              Char(36) NOT NULL,
    idExam_previousExam Char(36) NOT NULL,
    CONSTRAINT previousExam_PK PRIMARY KEY (idExam, idExam_previousExam),

    CONSTRAINT previousExam_Exam_FK FOREIGN KEY (idExam) REFERENCES Exam (idExam),
    CONSTRAINT previousExam_Exam0_FK FOREIGN KEY (idExam_previousExam) REFERENCES Exam (idExam)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ExamToDepartment
#------------------------------------------------------------

CREATE TABLE ExamToDepartment
(
    idExam         Char(36)    NOT NULL,
    DepartmentName Varchar(50) NOT NULL,
    CONSTRAINT ExamToDepartment_PK PRIMARY KEY (idExam, DepartmentName),

    CONSTRAINT ExamToDepartment_Exam_FK FOREIGN KEY (idExam) REFERENCES Exam (idExam),
    CONSTRAINT ExamToDepartment_Department0_FK FOREIGN KEY (DepartmentName) REFERENCES Department (name)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ExamToGroup
#------------------------------------------------------------

CREATE TABLE ExamToGroup
(
    idExam  Char(36) NOT NULL,
    idGroup Char(36) NOT NULL,
    CONSTRAINT ExamToGroup_PK PRIMARY KEY (idExam, idGroup),

    CONSTRAINT ExamToGroup_Exam_FK FOREIGN KEY (idExam) REFERENCES Exam (idExam),
    CONSTRAINT ExamToGroup__Group0_FK FOREIGN KEY (idGroup) REFERENCES _Group (idGroup)
)ENGINE=InnoDB;

