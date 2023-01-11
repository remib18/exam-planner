-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 08 jan. 2023 à 12:53
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
    time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DELETE
FROM _SubjectToMockUp;
DELETE
FROM _SlotToRoom;
DELETE
FROM _GroupToGroup;
DELETE
FROM _ExamToSlot;
DELETE
FROM _ExamToRoom;
DELETE
FROM _ExamToManager;
DELETE
FROM _ExamToGroup;
DELETE
FROM _ExamToExam;
DELETE
FROM _ExamToDepartment;
DELETE
FROM User;
DELETE
FROM Exam;
DELETE
FROM Subject;
DELETE
FROM Slot;
DELETE
FROM Room;
DELETE
FROM MockUp;
DELETE
FROM Manager;
DELETE
FROM `Group`;
DELETE
FROM Department;

--
-- Base de données : `exam_planner`
--

-- --------------------------------------------------------

--
-- Déchargement des données de la table `subject`
--

INSERT INTO `subject` (`name`)
VALUES ('Codage'),
       ('Derivée'),
       ('Dissection'),
       ('Integrale');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `department`
--

INSERT INTO `department` (`name`)
VALUES ('Anglais'),
       ('Biologique'),
       ('Droit'),
       ('Geographie'),
       ('Histoire'),
       ('Informatique'),
       ('Mathematique'),
       ('Orthophonie'),
       ('Physique'),
       ('Sociologie');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `exam`
--

INSERT INTO `exam` (`id`, `name`, `duration`, `type`, `subject`)
VALUES ('035ab38c-69bd-4ea4-aadb-d91e120d7db7', 'CC1', 2, 'Final', 'Codage'),
       ('0a88554a-6fdc-4c66-a040-1386285af669', 'CC2', 1, 'Final', 'Derivée'),
       ('0b180354-811a-4a7c-867c-361320f040f0', 'CC1', 4, 'Continuous', 'Dissection'),
       ('163b97a6-1e13-4a93-bb01-92a7ccd4cbeb', 'CC2', 2, 'Final', 'Integrale'),
       ('1ab1e709-ab90-47f4-9969-fd2d7fb55243', 'CC2', 2, 'Final', 'Integrale'),
       ('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', 'CC2', 4, 'Final', 'Dissection'),
       ('240b771f-594b-4043-abd4-5e53e889aa5d', 'Partiel', 1, 'Continuous', 'Codage'),
       ('25bcbf69-4f0a-4264-af9f-27a22eec1713', 'CC1', 2, 'Final', 'Dissection'),
       ('28afe4f4-d995-4f1e-8ecf-e7378e54891a', 'CC2', 1, 'Final', 'Derivée'),
       ('29e45b8c-dc9f-497c-a51d-da4862c2a25d', 'CC1', 2, 'Final', 'Codage'),
       ('2bb42f42-0038-43a6-9c38-ba27d27ecd67', 'Partiel', 2, 'Final', 'Codage'),
       ('363f04d1-afc7-4a4b-ab44-1103a4f9147f', 'CC1', 1, 'Continuous', 'Derivée'),
       ('38c5bda4-556d-438d-bcac-5ca86d5e8837', 'CC1', 3, 'Final', 'Integrale'),
       ('40c062b6-0d36-47c2-ae2a-6e30e18a03b3', 'Partiel', 4, 'Final', 'Dissection'),
       ('40dc0e1e-2543-4e7b-8da8-c7169952db89', 'CC2', 2, 'Final', 'Codage'),
       ('449bac15-a5bc-4a72-acf1-c3ee6a793331', 'Partiel', 3, 'Continuous', 'Integrale'),
       ('450bf82d-71df-48cd-8e08-e0b62c892c56', 'CC1', 3, 'Final', 'Integrale'),
       ('470011bd-82f5-437b-8b5f-b73261e13ddf', 'Partiel', 1, 'Continuous', 'Derivée'),
       ('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', 'CC2', 3, 'Final', 'Derivée'),
       ('58bfea21-4e61-4ce6-94ef-a6fea2889727', 'CC2', 3, 'Final', 'Integrale'),
       ('59c2131f-396f-4515-98d9-5590fff6a52b', 'CC2', 1, 'Final', 'Integrale'),
       ('607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1', 'CC1', 1, 'Final', 'Derivée'),
       ('66e16c78-db95-4481-84db-0f0c9d3e2aba', 'CC1', 1, 'Final', 'Codage'),
       ('6b413a5d-3281-4beb-82fd-02c2018634ea', 'Partiel', 1, 'Final', 'Integrale'),
       ('785c16e2-9922-4ab5-8d43-8545e7cca4c4', 'CC1', 4, 'Final', 'Dissection'),
       ('78fca392-05fe-4055-a520-1174a527bf31', 'CC2', 3, 'Continuous', 'Codage'),
       ('7ccab381-c326-49e9-a9b3-3718d5c3955a', 'Partiel', 2, 'Final', 'Codage'),
       ('801a9863-62e9-4d5c-ba94-0c1004f72e12', 'CC2', 4, 'Continuous', 'Integrale'),
       ('8c90032f-ff5d-46f2-8735-acba6d24f976', 'CC1', 2, 'Continuous', 'Integrale'),
       ('917629fb-ee6c-46bd-97b4-df599b18c15f', 'CC1', 3, 'Final', 'Dissection'),
       ('9b8ef61a-1a1e-4502-b338-eacac647bc4e', 'Partiel', 4, 'Final', 'Derivée'),
       ('a0d135ff-2e65-413c-8f20-c8be82f63ab6', 'CC1', 1, 'Final', 'Integrale'),
       ('a2fed69f-d54c-4525-bf57-31b34197c105', 'CC1', 1, 'Final', 'Dissection'),
       ('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'Partiel', 3, 'Final', 'Derivée'),
       ('a79be83e-96cd-433e-a2d4-b18bb6d2866f', 'Partiel', 3, 'Continuous', 'Codage'),
       ('a9caf0de-034a-499f-8d55-4d97f0eafad4', 'Partiel', 2, 'Final', 'Codage'),
       ('abf7e80b-b66e-4c94-8289-9cf0cb450780', 'CC1', 2, 'Continuous', 'Dissection'),
       ('c592004a-7405-49f1-974f-cd51de7f179e', 'Partiel', 2, 'Final', 'Derivée'),
       ('d8f9cbe5-02ad-4888-a5e0-db22ee0f82eb', 'Partiel', 1, 'Final', 'Codage'),
       ('e037f5cf-12ce-4fbe-936d-67daff40f207', 'CC1', 1, 'Final', 'Dissection'),
       ('e48fba02-73b0-4809-bf19-1183c7b2dc90', 'CC1', 2, 'Continuous', 'Dissection'),
       ('e58808bf-1bb1-4146-9253-90bfa43d08dd', 'Partiel', 4, 'Final', 'Codage'),
       ('edcbc1b5-877c-4b51-b62a-e93b975581d8', 'Partiel', 3, 'Continuous', 'Dissection'),
       ('ee125391-a841-43cc-bcaf-6b9d977563f7', 'Partiel', 4, 'Final', 'Integrale'),
       ('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', 'Partiel', 1, 'Final', 'Derivée'),
       ('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'Partiel', 1, 'Final', 'Dissection'),
       ('f8a8fc13-36cf-4d93-9f66-f8b7ef7c3f6f', 'Partiel', 4, 'Continuous', 'Codage'),
       ('fa3b20a4-c2de-4b3f-b993-7a71ffe12616', 'CC2', 2, 'Continuous', 'Codage'),
       ('fe2edefe-066c-430a-af48-8f773f8e262a', 'CC2', 4, 'Final', 'Integrale');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `group`
--

INSERT INTO `group` (`id`, `name`, `containReducedMobilityPerson`, `numberOfStudentsWithoutAdjustment`,
                     `numberOfStudentsWithWritingNeeds`, `numberOfStudentsWithIsolatedRooms`,
                     `numberOfStudentsWithPartTime`)
VALUES ('058be0fb-675a-4baf-b1ee-ccbbbd15c7ae', '1B', 0, 13, 13, 1, 4),
       ('0a24e134-0a42-4bf3-bfed-5021b37751fc', '1B', 0, 2, 16, 16, 19),
       ('0bcc4f06-7e72-4524-bab4-a34c3db5711e', '2B', 0, 16, 11, 15, 19),
       ('0c27e066-64f6-4e25-9181-27b9f6ae0f86', '1A', 0, 1, 19, 19, 19),
       ('0deb6098-a6be-4733-876e-caff1d34261b', '2A', 0, 16, 19, 12, 7),
       ('0f3653da-7ab4-41ea-9eac-c2fa043e0998', '3A', 0, 19, 6, 4, 3),
       ('108f0388-770d-479b-bf67-1d9f3679299d', '2C', 0, 15, 1, 12, 8),
       ('12d3ef10-2a6b-4ab6-a78c-b5973157ff27', '1B', 0, 19, 9, 1, 10),
       ('148b1196-3817-4d31-ab69-630a2e6155fa', '2A', 0, 8, 19, 11, 15),
       ('153ffa07-8ab5-4a3e-b805-eace49ffc671', '1C', 0, 13, 4, 2, 15),
       ('1c2408bc-ed86-4099-a4fd-c316124ea8ed', '3C', 0, 8, 7, 3, 7),
       ('20d845f8-96f4-4952-a6e3-15d84d56e8e5', '3B', 0, 10, 0, 19, 17),
       ('23fb2452-d036-44a1-bc12-007bd45ef65b', '1C', 0, 5, 1, 17, 16),
       ('2b73c9db-c7fe-4f25-b88f-12e6255b4f2d', '1A', 0, 5, 3, 13, 3),
       ('2b8e9fae-6231-46f0-87ae-d91f552b6624', '3A', 0, 4, 3, 3, 10),
       ('33070b97-0fea-4e53-8122-b086bc2e133e', '3C', 0, 16, 13, 19, 15),
       ('3491240a-e2f9-429e-80ad-9832e57f8d92', '3B', 0, 11, 13, 6, 2),
       ('3536bc20-f674-4a5f-beaa-4a885b6a489f', '3C', 0, 10, 12, 10, 1),
       ('37549213-ea9a-4a85-80d2-6c8385fb1115', '3A', 0, 7, 3, 14, 7),
       ('4e0a38ee-8006-459c-a7d3-1c3227da04af', '2A', 0, 14, 17, 15, 4),
       ('4e948497-65be-443f-bd92-016aa6b52899', '1A', 0, 3, 11, 8, 0),
       ('5ec9b9ec-1095-4b5f-a9cd-f0199d22be52', '2A', 0, 13, 5, 14, 12),
       ('6011aa92-5915-407b-969d-8f7e1f2793ec', '3A', 0, 10, 19, 7, 14),
       ('623d01b0-02a0-4c43-a1ec-63e5d5979a06', '3B', 0, 5, 13, 13, 0),
       ('6ba1df71-8855-49d8-80dd-5f4e38844b0a', '1B', 0, 18, 16, 12, 15),
       ('7042d1c9-c265-446c-b155-88f031bb53c9', '1A', 0, 8, 9, 17, 12),
       ('7159ef43-3468-4560-91d2-f58af5147d8e', '1C', 0, 16, 16, 16, 7),
       ('782bf663-2ce1-48ce-8315-4bf6d336f71e', '2B', 0, 12, 1, 7, 15),
       ('7b9277fa-2244-466d-9d60-2888ce4d66cf', '3B', 0, 1, 2, 3, 0),
       ('7eb54a65-9737-4ce4-8461-daf8659cba60', '2C', 0, 14, 11, 9, 15),
       ('7f94f2ed-272b-4bd2-8cef-c2c0437ec038', '1C', 0, 4, 0, 8, 1),
       ('84720fdc-d4a4-4e91-9298-1fc3175aebe8', '1B', 0, 12, 3, 4, 8),
       ('8659f388-8207-4ddf-9da0-70ac8521a23f', '3A', 0, 6, 19, 6, 1),
       ('8846b0bf-d76a-446e-9cf7-cf4aa3ad4436', '2B', 0, 2, 12, 9, 7),
       ('90c2ce81-cb82-4c5e-8ab1-2921d8302642', '2A', 0, 18, 6, 16, 16),
       ('951fd0c7-6383-4e9a-b429-f1251a179b45', '2B', 0, 6, 8, 5, 2),
       ('9d4cd589-f941-48c0-afad-0f42d7086362', '1A', 0, 18, 3, 6, 14),
       ('a9a2a6f7-4cf7-4b43-abaa-c561ebf0c6b9', '2B', 0, 3, 10, 18, 4),
       ('a9fdf3bf-3fe3-4121-bced-5b97e3cfe83e', '2B', 0, 6, 8, 8, 6),
       ('acbfae4b-3483-499b-98d0-8165c81de0bb', '3B', 0, 9, 11, 8, 5),
       ('ad95a910-5fb6-4678-807e-e6722983d006', '3C', 0, 5, 13, 9, 6),
       ('be97d018-684c-4572-8ddc-55986e052327', '1C', 0, 17, 4, 18, 18),
       ('c284f4f3-afff-4be6-9871-92f93b4e7134', '2A', 0, 2, 10, 8, 11),
       ('d3b24d46-ae4a-4aa9-afc7-105cfb3ae8d7', '2C', 0, 7, 6, 7, 6),
       ('d655eabb-da7c-41b9-b595-bc41bcb91c16', '1A', 0, 0, 15, 3, 18),
       ('f5de3c99-0732-41e5-84a0-081125c87afe', '3C', 0, 19, 12, 5, 16),
       ('f7d24941-4a40-4ba7-8424-75ba0dad3b79', '2C', 0, 16, 17, 8, 7),
       ('f9e147ea-91f5-4392-8644-dae46ed5b8a0', '2C', 0, 9, 4, 19, 16),
       ('fe20df25-8188-4cf7-a395-4833585af8fc', '1B', 0, 16, 7, 10, 5),
       ('fe60a9fd-9d79-4a6b-8eeb-4f34522b4fa0', '1C', 0, 6, 18, 17, 10);

-- --------------------------------------------------------

--
-- Déchargement des données de la table `manager`
--

INSERT INTO `manager` (`id`, `civility`, `lastname`, `firstname`)
VALUES ('0176df6e-3fa9-4008-98a9-e1f812aaefbd', 'Man', 'Théberge', 'René'),
       ('0b16454f-c73c-4bcd-9418-e3fd8948103a', 'Woman', 'Dupont', 'Joséphine'),
       ('0cb2674d-b2ec-4ffc-b7c0-991fdcc2bb0d', 'Woman', 'Guédry', 'Astrid'),
       ('0d1400d8-559c-41e1-8872-017a46baccb7', 'Man', 'Melanson', 'Pascal'),
       ('17dce27e-862b-495c-8ca7-ffeb47b4ccd6', 'Woman', 'Vaillancourt', 'Mariette'),
       ('197a35f7-6b2d-4d00-8d2a-09dfd64ae45c', 'Man', 'Coudert', 'Philippe'),
       ('1e0c39cd-8e97-46bb-b419-0c1fb97a6fff', 'Woman', 'Poissonnier', 'Georgette'),
       ('22dc9102-532e-4448-8f72-cd345b85cc8f', 'Man', 'Faubert', 'Tristan'),
       ('2a30d2ce-cdd1-4d0d-aaa3-16d6a4ef81be', 'Woman', 'Auger', 'Harriette'),
       ('3b0d53ce-9390-4b2b-9293-d3e6ccaa9bd2', 'Man', 'Deserres', 'Magalie'),
       ('5bb41d27-27f3-4919-bb55-215840f2f724', 'Man', 'Gaulin', 'Nicolas'),
       ('5e23ed53-4544-4e99-9689-e0108b0d1bc4', 'Man', 'Bérubé', 'Gérard'),
       ('5e63df9b-90b7-406c-9955-9f77f777b248', 'Woman', 'Gauthier', 'Aceline'),
       ('5eccdb81-0014-4b88-ac27-f2449b67daa1', 'Man', 'Faubert', 'Frontino'),
       ('6365cfee-f7ca-4729-9a96-4bd68455fb69', 'Woman', 'Metivier', 'Eléonore'),
       ('68fb85af-683e-477c-9ed6-e0e2d64db1bc', 'Man', 'Etoile', 'Ferrau'),
       ('699bd9a8-0c9f-4656-8693-d580b3f5d5e9', 'Man', 'Louis', 'Mallory'),
       ('6cac9753-b308-408a-8607-be94daaac758', 'Man', 'Forest', 'Raymond'),
       ('73bb5b42-ca6b-4c3a-8ffe-db754a1d4e28', 'Woman', 'Asselin', 'Iva'),
       ('74fc3c54-a3dc-4e7c-bd8f-72d196ecd6c6', 'Man', 'Routhier', 'Seymour'),
       ('7672e84c-0877-4058-8d26-bdd9e01a8c5b', 'Woman', 'Pitre', 'Heloise'),
       ('7c334929-a33a-4ff4-8703-2b22dec3adc0', 'Man', 'Austin', 'Carolos'),
       ('7c7f0fd2-8e3d-415c-b9f4-be3cfcb321a0', 'Man', 'Arnoux', 'Talbot'),
       ('81dc1197-8c09-4f0b-ac16-cda770b16b9a', 'Man', 'Bienvenue', 'Fabien'),
       ('83446c63-4e77-4cf3-a3cd-736bf9913a0b', 'Woman', 'Faure', 'Faustine'),
       ('85a5ddda-d321-4442-bf49-e331378ce073', 'Man', 'Paulet', 'Frontino'),
       ('8847cfb3-232f-4cb2-85ce-dbea9e23c82d', 'Woman', 'Patenaude', 'Tilly'),
       ('8fc47990-41ca-4c05-abb5-905f9e46ac7f', 'Woman', 'Desrosiers', 'Lirienne'),
       ('a2f9a6d4-b1f1-4994-b747-907d9d8ca980', 'Woman', 'Gaudreau', 'Océane'),
       ('a754b1b6-2a01-4624-83b9-4a242d58f3c2', 'Man', 'Chauvin', 'Burrell'),
       ('a7eb779e-4591-44a0-98f1-49f84a788b34', 'Woman', 'Pelletier', 'Annot'),
       ('af6c79bc-31e8-4bdd-9396-b6583e868742', 'Woman', 'Lebel', 'Clothilde'),
       ('b964a502-6ec1-4a00-a843-f779c14f9d10', 'Man', 'Pomerleau', 'Felicien'),
       ('bc4bf926-e654-43b5-a5aa-3aa959bf26db', 'Woman', 'Lefèbvre', 'Christiane'),
       ('bd8a60f7-6bd4-4427-9794-c6040ee4d32f', 'Man', 'Deslauriers', 'Lothair'),
       ('bf8bd6d2-2e74-47ca-867d-6735e6009f38', 'Woman', 'Brisette', 'Marie'),
       ('c1f5078c-764d-4a15-a868-bbf6508d6eb8', 'Man', 'Sirois', 'Aubrey'),
       ('c65d7db3-915c-4d83-8361-d5f2b45b86e9', 'Man', 'Jobin', 'Agrican'),
       ('c87bbe95-21bf-478f-bfff-8a7386bbcbba', 'Woman', 'Daigle', 'Calandre'),
       ('d84e4d30-88b5-4815-a5bd-88747b52893b', 'Woman', 'Deniger', 'Phillipa'),
       ('de0b0b23-32f6-485a-bd25-0468e1139366', 'Woman', 'Mercure', 'Hélène'),
       ('de301420-2c8d-4bab-82f4-682649247521', 'Man', 'Dennis', 'Christian'),
       ('de939cfc-c277-49c5-afcb-d0df41ff521c', 'Woman', 'Reault', 'Amarante'),
       ('df2807c0-cf64-4a2f-8db8-ca07c850d32d', 'Man', 'L\'Anglais', 'Bellamy'),
('df41cc18-4833-4c40-86f8-a78d772d2d90', 'Man', 'Salmons', 'Philip'),
('e1e07b19-b617-46cb-b931-89b59611ef1e', 'Woman', 'Lacasse', 'Oriel'),
('e7aa82a8-cc1d-450c-a385-fae7914357ef', 'Man', 'Langlais', 'Sumner'),
('ed5b67da-3ccb-472c-a01b-a02c15305da6', 'Woman', 'Bourgeau', 'Céline'),
('fc4e1754-de3c-40ae-979c-2b208db24d1e', 'Woman', 'Deserres', 'Arianne'),
('ffaf176f-038a-4909-81c7-80594a25e1e5', 'Woman', 'Marceau', 'Jolie');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `mockup`
--

INSERT INTO `mockup` (`name`, `degree`, `year`, `semester`, `department`) VALUES
('BachelorOne_Droit', 'Bachelor', 'One', 'One', 'Droit'),
('BachelorOne_Geographie', 'Bachelor', 'One', 'One', 'Geographie'),
('BachelorOne_Histoire', 'Bachelor', 'One', 'One', 'Histoire'),
('BachelorOne_Informatique', 'Bachelor', 'One', 'Two', 'Informatique'),
('BachelorOne_Mathematique', 'Bachelor', 'One', 'One', 'Mathematique'),
('BachelorOne_Physique', 'Bachelor', 'One', 'One', 'Physique'),
('BachelorOne_Sociologie', 'Bachelor', 'One', 'Two', 'Sociologie'),
('BachelorThree_Anglais', 'Bachelor', 'Three', 'One', 'Anglais'),
('BachelorThree_Droit', 'Bachelor', 'Three', 'Two', 'Droit'),
('BachelorThree_Histoire', 'Bachelor', 'Three', 'Two', 'Histoire'),
('BachelorThree_Informatique', 'Bachelor', 'Three', 'One', 'Informatique'),
('BachelorThree_Orthophonie', 'Bachelor', 'Three', 'Two', 'Orthophonie'),
('BachelorThree_Physique', 'Bachelor', 'Three', 'One', 'Physique'),
('BachelorTwo_Anglais', 'Bachelor', 'Two', 'One', 'Anglais'),
('BachelorTwo_Droit', 'Bachelor', 'Two', 'Two', 'Droit'),
('BachelorTwo_Histoire', 'Bachelor', 'Two', 'One', 'Histoire'),
('BachelorTwo_Mathematique', 'Bachelor', 'Two', 'Two', 'Mathematique'),
('BachelorTwo_Orthophonie', 'Bachelor', 'Two', 'Two', 'Orthophonie'),
('BachelorTwo_Physique', 'Bachelor', 'Two', 'Two', 'Physique'),
('MasterOne_Anglais', 'Master', 'One', 'Two', 'Anglais'),
('MasterOne_Droit', 'Master', 'One', 'Two', 'Droit'),
('MasterOne_Geographie', 'Master', 'One', 'One', 'Geographie'),
('MasterOne_Histoire', 'Master', 'One', 'One', 'Histoire'),
('MasterOne_Informatique', 'Master', 'One', 'One', 'Informatique'),
('MasterOne_Orthophonie', 'Master', 'One', 'Two', 'Orthophonie'),
('MasterOne_Physique', 'Master', 'One', 'Two', 'Physique'),
('MasterThree_Anglais', 'Master', 'Three', 'Two', 'Anglais'),
('MasterThree_Droit', 'Master', 'Three', 'One', 'Droit'),
('MasterThree_Geographie', 'Master', 'Three', 'One', 'Geographie'),
('MasterThree_Histoire', 'Master', 'Three', 'Two', 'Histoire'),
('MasterThree_Informatique', 'Master', 'Three', 'Two', 'Informatique'),
('MasterThree_Mathematique', 'Master', 'Three', 'One', 'Mathematique'),
('MasterThree_Physique', 'Master', 'Three', 'One', 'Physique'),
('MasterTwo_Geographie', 'Master', 'Two', 'One', 'Geographie'),
('MasterTwo_Informatique', 'Master', 'Two', 'One', 'Informatique'),
('MasterTwo_Mathematique', 'Master', 'Two', 'Two', 'Mathematique'),
('MasterTwo_Orthophonie', 'Master', 'Two', 'Two', 'Orthophonie'),
('MasterTwo_Physique', 'Master', 'Two', 'Two', 'Physique'),
('MasterTwo_Sociologie', 'Master', 'Two', 'One', 'Sociologie');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `room`
--

INSERT INTO `room` (`name`, `places`, `types`, `equipments`, `computerEnvironment`) VALUES
('A0020', 146, 'Laboratory', 'Speaker, Board', 'LinuxEnvironment, WindowsEnvironment, MacOsEnvironment'),
('A0030', 156, 'Library', 'Speaker, Board, Webcam', 'MacOsEnvironment'),
('A0040', 23, 'Amphitheater', 'Speaker, Board, Webcam', 'InternetAccess'),
('A0060', 171, 'ComputerRoom', 'Projector, Board', 'LinuxEnvironment'),
('A050', 64, 'Amphitheater', 'Projector, Webcam', 'ProgrammingApplication, PhysicsApplication'),
('A1010', 73, 'Amphitheater', 'Projector, Board, Webcam', 'OfficeApplication, ProgrammingApplication,
        PhysicsApplication, ChemistryApplication'),
('A1020', 198, 'ComputerRoom', 'Speaker', 'InternetAccess, WindowsEnvironment, PhysicsApplication'),
('A1030', 132, 'ComputerRoom', 'Webcam', 'InternetAccess, WindowsEnvironment, MathApplication'),
('A1040', 189, 'Office', 'Speaker, Board, Webcam', 'OfficeApplication, WindowsEnvironment'),
('A1050', 20, 'Office', 'Speaker, Board', 'LinuxEnvironment, MacOsEnvironment, ProgrammingApplication'),
('A1060', 249, 'Amphitheater', 'Board, Webcam', 'InternetAccess, WindowsEnvironment, MacOsEnvironment,
        PhysicsApplication'),
('E00050', 93, 'ComputerRoom', 'Speaker, Board', 'OfficeApplication, WindowsEnvironment, PhysicsApplication'),
('E0020', 71, 'ComputerRoom', 'Projector, Speaker', 'WindowsEnvironment, ProgrammingApplication, MathApplication'),
('E0030', 66, 'ComputerRoom', 'Speaker, Board', 'OfficeApplication, InternetAccess, LinuxEnvironment, PhysicsApplication'),
('E0040', 164, 'Laboratory', 'Projector, Speaker, Webcam', 'WindowsEnvironment, PhysicsApplication, ChemistryApplication'),
('E0060', 236, 'Amphitheater', 'Projector, Speaker, Board, Webcam', 'LinuxEnvironment, WindowsEnvironment,
        ProgrammingApplication'),
('E0070', 38, 'Library', 'Speaker, Webcam', 'InternetAccess, MacOsEnvironment, MathApplication'),
('E0080', 30, 'ComputerRoom', 'Speaker, Webcam', 'InternetAccess, MacOsEnvironment, ChemistryApplication'),
('E0090', 31, 'Laboratory', 'Projector, Speaker, Board', 'LinuxEnvironment, ProgrammingApplication, MathApplication'),
('E0100', 171, 'Laboratory', 'Projector, Webcam', 'OfficeApplication, LinuxEnvironment, PhysicsApplication,
        MathApplication'),
('E1000', 168, 'Laboratory', 'Projector, Board', 'PhysicsApplication, MathApplication, ChemistryApplication'),
('E1010', 150, 'Office', 'Projector, Speaker, Board, Webcam', 'ProgrammingApplication, MathApplication,
        ChemistryApplication'),
('E1020', 58, 'Office', 'Projector, Speaker', 'MacOsEnvironment, ProgrammingApplication, PhysicsApplication,
        MathApplication, ChemistryApplication'),
('F020', 86, 'Laboratory', 'Projector, Board', 'MacOsEnvironment, ProgrammingApplication, PhysicsApplication,
        MathApplication, ChemistryApplication'),
('F021', 108, 'Laboratory', 'Speaker', 'OfficeApplication, InternetAccess, LinuxEnvironment, WindowsEnvironment'),
('F022', 143, 'Amphitheater', 'Webcam', 'LinuxEnvironment, WindowsEnvironment, MacOsEnvironment, ProgrammingApplication'),
('F023', 49, 'Amphitheater', 'Board', 'WindowsEnvironment, MacOsEnvironment, ProgrammingApplication'),
('F110', 105, 'Laboratory', 'Webcam', 'InternetAccess, LinuxEnvironment'),
('F120', 80, 'ComputerRoom', 'Projector', 'WindowsEnvironment'),
('F130', 77, 'ComputerRoom', 'Speaker', 'WindowsEnvironment'),
('F140', 246, 'Amphitheater', 'Projector', 'ChemistryApplication'),
('F150', 225, 'Office', 'Webcam', 'MacOsEnvironment'),
('L0020', 212, 'Amphitheater', 'Speaker', 'OfficeApplication'),
('L0030', 72, 'Library', 'Speaker', 'InternetAccess'),
('L0040', 59, 'Amphitheater', 'Speaker', 'LinuxEnvironment, WindowsEnvironment'),
('L0050', 51, 'Amphitheater', 'Projector', 'OfficeApplication, InternetAccess, LinuxEnvironment, WindowsEnvironment,
        MacOsEnvironment, ProgrammingApplication, PhysicsApplication, MathApplication, ChemistryApplication'),
('L0060', 242, 'Office', 'Speaker', 'InternetAccess, WindowsEnvironment'),
('L0070', 150, 'Library', 'Projector', 'LinuxEnvironment, ProgrammingApplication'),
('L0080', 30, 'Library', 'Speaker', 'MacOsEnvironment, MathApplication, ChemistryApplication'),
('L0090', 69, 'Laboratory', 'Speaker', 'ProgrammingApplication, PhysicsApplication'),
('L0100', 92, 'Amphitheater', 'Webcam', 'OfficeApplication, InternetAccess, MacOsEnvironment, MathApplication'),
('L0110', 89, 'Office', 'Projector', 'LinuxEnvironment, WindowsEnvironment, MacOsEnvironment'),
('L0120', 230, 'Library', 'Speaker', 'MacOsEnvironment, ProgrammingApplication, ChemistryApplication'),
('L0130', 159, 'ComputerRoom', 'Projector', 'ProgrammingApplication'),
('L0140', 49, 'Laboratory', 'Board', 'InternetAccess, ProgrammingApplication'),
('L0150', 202, 'Library', 'Board', 'LinuxEnvironment, PhysicsApplication'),
('L0160', 20, 'Amphitheater', 'Projector', 'InternetAccess, ProgrammingApplication'),
('L0170', 221, 'Office', 'Board', 'OfficeApplication, MacOsEnvironment'),
('L0180', 161, 'Library', 'Speaker', 'OfficeApplication, ProgrammingApplication, PhysicsApplication, MathApplication'),
('L0190', 37, 'Laboratory', 'Webcam', 'WindowsEnvironment, ProgrammingApplication, PhysicsApplication ');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `slot`
--

INSERT INTO `slot` (`id`, `day`, `hour`, `duration`)VALUES ('049340e2-a174-4806-8e66-227ca9a9bcc0', '2045-06-08', 10,
                                                            2),
                                                           ('05a8b4ed-3953-46cd-b4b3-0fd3c46d993a', '2045-06-04', 13,
                                                            1),
                                                           ('078ac405-8685-4bf7-9a70-d02caddcefdd', '2045-06-01', 8, 1),
                                                           ('0932e5db-cdae-43be-bcb9-72f3ad074e97', '2045-06-02', 15,
                                                            1),
                                                           ('0a4a90f2-9cea-41f9-b341-c1edac414ef5', '2045-06-08', 16,
                                                            2),
                                                           ('0a5f370b-5234-472c-8cfe-e2fc1316a24c', '2045-05-08', 15,
                                                            1),
                                                           ('0dde3ca0-e5b4-46ca-93d4-f8eac5501c90', '2045-05-05', 7, 1),
                                                           ('0e83c8f6-d495-41da-bded-962017ce8bd8', '2045-06-08', 17,
                                                            2),
                                                           ('0ee84d18-4859-4ef8-a08f-93ace9e44fae', '2045-05-06', 9, 2),
                                                           ('0f1e87af-4138-48b4-a79b-5549c7df2e39', '2045-05-07', 18,
                                                            1),
                                                           ('140d9e43-896a-4d08-bd9b-76941a9bee2d', '2045-06-05', 10,
                                                            2),
                                                           ('17ede819-68f6-40d1-9de6-a0f0aa164250', '2045-06-01', 12,
                                                            1),
                                                           ('17f8c40c-0949-4b43-b5b1-6a66a28a32ec', '2045-05-08', 17,
                                                            1),
                                                           ('18892e71-5527-489e-af59-dc020ecea390', '2045-05-05', 10,
                                                            2),
                                                           ('1b40c900-7b65-42f0-a4f0-e1b71af921f7', '2045-05-08', 15,
                                                            1),
                                                           ('1bb9e37b-8a25-4248-8576-80d60c56602c', '2045-05-08', 18,
                                                            1),
                                                           ('246379ed-9cee-4ff3-bd10-761194e144a9', '2045-06-05', 16,
                                                            2),
                                                           ('2dcb5227-c929-401e-b5da-ffa6d659d5c7', '2045-06-03', 18,
                                                            1),
                                                           ('2e7d53ca-4be2-4f16-b3a7-c517de54c9c7', '2045-05-06', 13,
                                                            2),
                                                           ('2f06f008-5383-446b-b651-d009099c2702', '2045-06-04', 10,
                                                            2),
                                                           ('32f02fd0-d8c8-437f-b5cf-f7fd19e27195', '2045-06-01', 16,
                                                            1),
                                                           ('33096acb-0b0e-4990-95d1-5e13d99df585', '2045-05-06', 11,
                                                            1),
                                                           ('34c4b802-6dda-42c3-81e8-e48b4154c1c6', '2045-06-08', 15,
                                                            2),
                                                           ('36243e44-3389-41bb-a82b-65a312aee0ae', '2045-05-05', 10,
                                                            1),
                                                           ('3664169f-c295-49d5-89d8-c8e8f4c6184f', '2045-06-08', 18,
                                                            2),
                                                           ('3bc809aa-6d04-48da-895a-d4a58518f2e8', '2045-06-04', 9, 2),
                                                           ('3ec6fa32-6fb4-4b6a-9db8-b4e06254bb6d', '2045-06-01', 15,
                                                            1),
                                                           ('40026353-4310-4039-8cc0-a52d149ad21b', '2045-05-06', 18,
                                                            2),
                                                           ('4292c9be-25fb-4907-8504-34dbdd6d064c', '2045-06-05', 7, 2),
                                                           ('470afc5d-af4d-4613-907f-55d6b13bb055', '2045-06-01', 10,
                                                            1),
                                                           ('47f8039c-4642-4652-af15-baf83a49c8d3', '2045-05-08', 18,
                                                            1),
                                                           ('4d3e56a7-5e05-4ac5-bd0b-98025e131995', '2045-06-03', 18,
                                                            2),
                                                           ('4efda2c5-022d-44d8-a362-b3e773c4484e', '2045-06-05', 16,
                                                            1),
                                                           ('512cbfdd-b0c4-40cf-819d-19763067dcdc', '2045-05-07', 18,
                                                            1),
                                                           ('5613658f-c03f-4130-8c37-52d7e8d901bc', '2045-06-03', 16,
                                                            2),
                                                           ('6429f863-6daa-4aa2-a265-dbe1c3328b66', '2045-06-02', 16,
                                                            1),
                                                           ('6474630d-a378-45b6-a7ab-84e8139b1a11', '2045-06-03', 11,
                                                            1),
                                                           ('66fa9123-e6e3-4a40-8b2b-1ae1d37f3c48', '2045-06-04', 18,
                                                            1),
                                                           ('67666f92-92d5-4d27-9877-c3b652d3886e', '2045-05-07', 14,
                                                            2),
                                                           ('6bb19260-f320-41b9-9c09-dc61143e7327', '2045-06-02', 7, 2),
                                                           ('70cb82b1-1b99-477a-828a-ce1fab837388', '2045-06-01', 16,
                                                            1),
                                                           ('718eaba4-21d5-4656-a3e5-0ddd4eef73a0', '2045-05-07', 8, 2),
                                                           ('727cfcea-3b85-45d0-8f6a-89110fa55375', '2045-06-05', 7, 2),
                                                           ('7474d00d-3fb3-49e4-83d9-cd32c59e89cd', '2045-05-07', 17,
                                                            2),
                                                           ('75a7bba2-809b-43a8-8352-78f478b8fd44', '2045-06-03', 9, 2),
                                                           ('769007eb-2500-4116-9083-61648ee36a70', '2045-06-04', 7, 2),
                                                           ('789187e5-4be1-4d02-a478-e90fd704f8a0', '2045-06-02', 15,
                                                            2),
                                                           ('7a508570-71d8-419e-ae74-565c9dcfe61a', '2045-05-05', 14,
                                                            1),
                                                           ('7c4ba636-8247-4c86-abe9-968698f349e7', '2045-06-08', 13,
                                                            1),
                                                           ('7e8a7778-a640-4ed3-be9d-e098157263f0', '2045-06-01', 8, 1),
                                                           ('8167dbf6-5f31-4d8e-b029-48858c635409', '2045-05-05', 14,
                                                            1),
                                                           ('841b527d-0549-4ab8-a2d5-909dfa651081', '2045-05-05', 9, 1),
                                                           ('86843a15-643a-4927-a438-907dba0d7a8c', '2045-05-07', 10,
                                                            1),
                                                           ('8795075e-547d-479c-8066-8b04eb0a1139', '2045-06-05', 16,
                                                            1),
                                                           ('8c52e344-f78f-4427-9340-1c4efecd0806', '2045-05-06', 10,
                                                            2),
                                                           ('8e7f3147-846d-474b-ae6d-7f169c6510b7', '2045-06-03', 15,
                                                            1),
                                                           ('911200cd-44c3-4e24-ab16-a79a14bc679a', '2045-06-02', 8, 2),
                                                           ('98a6b989-cdda-4f11-a96c-0ebad6d026b2', '2045-06-04', 11,
                                                            1),
                                                           ('9905d1fe-d93b-4985-a90f-1a1fe7bdfee4', '2045-06-04', 18,
                                                            2),
                                                           ('9efa49da-514f-4044-8210-f87df3101087', '2045-06-01', 18,
                                                            1),
                                                           ('9f4a3188-0b52-4abb-a150-fb7ea6d01e3c', '2045-06-03', 8, 2),
                                                           ('9fe89647-ff69-4991-8ccd-571fbcf6068b', '2045-06-05', 10,
                                                            1),
                                                           ('a36fb62d-4a52-4e43-aec0-3edddeaaa641', '2045-05-08', 8, 2),
                                                           ('a61122a7-16ff-48e0-88d9-b8eefa9edee8', '2045-06-01', 13,
                                                            1),
                                                           ('ab825e6a-eee6-43f8-9d82-7b6353d360cb', '2045-06-03', 8, 1),
                                                           ('ac173af0-0266-4d69-ace7-3984b10f827d', '2045-06-04', 10,
                                                            2),
                                                           ('acbc1dc8-d617-4a58-bbb9-c6ecd74f8e64', '2045-06-05', 9, 1),
                                                           ('aff53af0-618c-4ea0-aa94-6cfe95a1c39c', '2045-06-04', 13,
                                                            1),
                                                           ('b24a760d-5fcd-41e8-958f-6cc71347e931', '2045-06-08', 14,
                                                            2),
                                                           ('b3c95223-6d46-4781-8cfa-fc2935cc18c2', '2045-06-08', 10,
                                                            1),
                                                           ('b4b4764c-f113-41a3-b98f-e3a65b907889', '2045-06-03', 7, 1),
                                                           ('b4f76fab-82aa-47a7-9723-36e94b93fd9e', '2045-06-04', 11,
                                                            2),
                                                           ('baf94a8b-fdaf-48ed-b539-9e50d70f0980', '2045-06-02', 9, 2),
                                                           ('bb089d19-d51a-4dcf-87d9-a1908b1dfec4', '2045-05-07', 9, 2),
                                                           ('bb5cf15d-6908-4329-a7bd-f9af47b45faa', '2045-05-05', 7, 1),
                                                           ('bf5d634f-5c10-4952-8572-a989514ed1d2', '2045-06-03', 13,
                                                            2),
                                                           ('c0fb54fc-c76e-4f40-8759-eb0866a6d33f', '2045-05-08', 13,
                                                            1),
                                                           ('c50fcf4f-e345-41e3-a7ab-45bbc580c407', '2045-05-07', 8, 2),
                                                           ('cac2fc91-f2ae-43c7-8cab-4b3decb04030', '2045-05-05', 17,
                                                            2),
                                                           ('cc20006f-4a5d-4afb-9618-23b9633eae97', '2045-06-05', 17,
                                                            1),
                                                           ('ceb85fde-f78e-4c8c-a086-086e4e4d1e0d', '2045-06-01', 15,
                                                            2),
                                                           ('d17e4c81-7bf0-46e7-b874-851b2bee4756', '2045-06-08', 10,
                                                            1),
                                                           ('d1db361f-5985-4ebd-94fe-7a5743fb790e', '2045-05-08', 17,
                                                            2),
                                                           ('d24260cd-22ba-40e5-b6c0-76835bb394ea', '2045-06-02', 16,
                                                            1),
                                                           ('d423c7ad-3f66-4fe3-8bfa-e911dd30a799', '2045-05-08', 10,
                                                            2),
                                                           ('d5a5e686-05e3-443c-b2f5-bf419e9054e9', '2045-05-06', 13,
                                                            1),
                                                           ('d85fab44-b039-43d3-8232-e871f38629dd', '2045-05-05', 8, 1),
                                                           ('de5f8194-4013-428a-bed4-c99bcaf5233e', '2045-05-06', 9, 1),
                                                           ('ded7f7c7-07c8-4b24-a86b-2c3091f23516', '2045-06-05', 12,
                                                            2),
                                                           ('e2e5e963-51e1-4e7c-9d6c-b0773fb9042a', '2045-05-06', 15,
                                                            1),
                                                           ('e587327e-805d-486b-b6a4-6d0a3d3f54e1', '2045-06-08', 14,
                                                            2),
                                                           ('e5bf7fe2-2c30-4553-b496-b7d46b5366de', '2045-05-06', 13,
                                                            2),
                                                           ('e8480095-a0e8-47b6-8a7f-0275c6a18bac', '2045-06-08', 7, 1),
                                                           ('e8a03451-4b11-432a-b527-eb92e54f9436', '2045-05-06', 9, 2),
                                                           ('e9abd39f-a6e4-44d7-b3e0-cfd3bbb1fcfb', '2045-06-02', 13,
                                                            2),
                                                           ('ed34eda4-8029-4080-a664-8d8b1f8a179e', '2045-06-02', 13,
                                                            1),
                                                           ('f5a81af6-de85-4388-a359-40c6b3ae17bb', '2045-05-08', 12,
                                                            1),
                                                           ('f7e2558f-e452-4d6f-a793-8e109bfd9d38', '2045-05-07', 13,
                                                            2),
                                                           ('fb78141f-20bd-4e57-97a2-b5002389bc61', '2045-05-07', 17,
                                                            2),
                                                           ('fb812eee-38a0-4030-ad67-548881800a5d', '2045-06-02', 13,
                                                            2);

-- --------------------------------------------------------

--
-- Déchargement des données de la table `user`
--
INSERT INTO `User` (id, mail, department, manager, password)
VALUES ('aaaaaaaa-aee0-4795-9655-bd6909bd690a', 'schooling', null, null,
        '22fa799b871efc06156b7b793920643a8711bdb9d147723151d7f48e70bd2df9'),
       ('aaaaaaaa-aee0-4795-9655-bd6909bd690b', 'department', 'Anglais', null,
        '2ad29f65743a0524d916bfb3e24f5034c970b8daa7749699a88bd7096129fa09'),
       ('aaaaaaaa-aee0-4795-9655-bd6909bd690c', 'manager', null, '197a35f7-6b2d-4d00-8d2a-09dfd64ae45c',
        '6ee4a469cd4e91053847f5d3fcb61dbcc91e8f0ef10be7748da4c4a1ba382d17');

INSERT INTO `user` (`id`, `mail`, `department`, `manager`, `password`)
VALUES ('13317305-aee0-4795-9655-bd6909bd690c', 'FelicienPomerleau@gustr.com', 'Anglais',
        'e1e07b19-b617-46cb-b931-89b59611ef1e', 'Xiek9aewah'),
       ('1a5ad923-a457-47db-9180-48480f269c73', 'CarolosAustin@superrito.com', 'Anglais',
        'bd8a60f7-6bd4-4427-9794-c6040ee4d32f', 'iiyooP4S'),
       ('34a5a691-086c-4b45-b580-c42f00b06fd6', 'PhillipaDeniger@cuvox.de', 'Geographie',
        'a7eb779e-4591-44a0-98f1-49f84a788b34', 'zait4ua9Ae'),
       ('6b839c2f-0e8a-4215-b496-56d05dcb411d', 'JosephineDupont@gustr.com', 'Mathematique',
        '7c334929-a33a-4ff4-8703-2b22dec3adc0', 'ieHie5eesae'),
       ('867bf487-1774-4954-ad1f-75ffcf10b2fb', 'AgricanJobin@rhyta.com', 'Biologique',
        '22dc9102-532e-4448-8f72-cd345b85cc8f', 'iNgaibu5Ji'),
       ('af6e002d-a7c2-4504-bdbe-9cf79ad109a5', 'BurrellChauvin@gustr.com', 'Physique',
        '5e23ed53-4544-4e99-9689-e0108b0d1bc4', 'roo6aiYoh'),
       ('b4526d8a-33b6-425c-ae65-7b90f680a7c7', 'HeloisePitre@jourrapide.com', 'Droit',
        'e7aa82a8-cc1d-450c-a385-fae7914357ef', 'deophohn2Ie'),
       ('b4b3850f-f990-485e-872a-75c42b8f0de9', 'TristanFaubert@armyspy.com', 'Sociologie',
        'bf8bd6d2-2e74-47ca-867d-6735e6009f38', 'cat6gaeJ6ei'),
       ('ce057798-3c03-47f6-a17a-e6cce685c4d0', 'HeleneMercure@teleworm.us', 'Anglais',
        'e1e07b19-b617-46cb-b931-89b59611ef1e', 'Boam5ike'),
       ('d545dbdb-01c0-43a1-a677-dee1df166885', 'ReneTheberge@superrito.com', 'Biologique',
        'a7eb779e-4591-44a0-98f1-49f84a788b34', 'Easie2ebaet');
-- --------------------------------------------------------

--
-- Déchargement des données de la table `_examtodepartment`
--

INSERT INTO `_examtodepartment` (`exam`, `department`)
VALUES ('035ab38c-69bd-4ea4-aadb-d91e120d7db7', 'Anglais'),
       ('035ab38c-69bd-4ea4-aadb-d91e120d7db7', 'Sociologie'),
('0b180354-811a-4a7c-867c-361320f040f0', 'Biologique'),
('163b97a6-1e13-4a93-bb01-92a7ccd4cbeb', 'Informatique'),
('1ab1e709-ab90-47f4-9969-fd2d7fb55243', 'Orthophonie'),
('1ab1e709-ab90-47f4-9969-fd2d7fb55243', 'Sociologie'),
('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', 'Droit'),
('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', 'Geographie'),
('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', 'Histoire'),
('29e45b8c-dc9f-497c-a51d-da4862c2a25d', 'Geographie'),
('29e45b8c-dc9f-497c-a51d-da4862c2a25d', 'Physique'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', 'Droit'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', 'Geographie'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', 'Physique'),
('363f04d1-afc7-4a4b-ab44-1103a4f9147f', 'Droit'),
('363f04d1-afc7-4a4b-ab44-1103a4f9147f', 'Informatique'),
('40c062b6-0d36-47c2-ae2a-6e30e18a03b3', 'Geographie'),
('40c062b6-0d36-47c2-ae2a-6e30e18a03b3', 'Informatique'),
('449bac15-a5bc-4a72-acf1-c3ee6a793331', 'Histoire'),
('450bf82d-71df-48cd-8e08-e0b62c892c56', 'Orthophonie'),
('470011bd-82f5-437b-8b5f-b73261e13ddf', 'Anglais'),
('470011bd-82f5-437b-8b5f-b73261e13ddf', 'Biologique'),
('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', 'Physique'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', 'Histoire'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', 'Physique'),
('66e16c78-db95-4481-84db-0f0c9d3e2aba', 'Informatique'),
('78fca392-05fe-4055-a520-1174a527bf31', 'Sociologie'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', 'Anglais'),
('801a9863-62e9-4d5c-ba94-0c1004f72e12', 'Biologique'),
('917629fb-ee6c-46bd-97b4-df599b18c15f', 'Informatique'),
('a0d135ff-2e65-413c-8f20-c8be82f63ab6', 'Physique'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'Droit'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'Histoire'),
('a79be83e-96cd-433e-a2d4-b18bb6d2866f', 'Informatique'),
('a79be83e-96cd-433e-a2d4-b18bb6d2866f', 'Mathematique'),
('a9caf0de-034a-499f-8d55-4d97f0eafad4', 'Anglais'),
('a9caf0de-034a-499f-8d55-4d97f0eafad4', 'Mathematique'),
('a9caf0de-034a-499f-8d55-4d97f0eafad4', 'Orthophonie'),
('c592004a-7405-49f1-974f-cd51de7f179e', 'Droit'),
('d8f9cbe5-02ad-4888-a5e0-db22ee0f82eb', 'Informatique'),
('e037f5cf-12ce-4fbe-936d-67daff40f207', 'Mathematique'),
('e48fba02-73b0-4809-bf19-1183c7b2dc90', 'Geographie'),
('e48fba02-73b0-4809-bf19-1183c7b2dc90', 'Informatique'),
('e48fba02-73b0-4809-bf19-1183c7b2dc90', 'Mathematique'),
('edcbc1b5-877c-4b51-b62a-e93b975581d8', 'Biologique'),
('edcbc1b5-877c-4b51-b62a-e93b975581d8', 'Histoire'),
('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', 'Geographie'),
('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', 'Histoire'),
('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', 'Physique'),
('fe2edefe-066c-430a-af48-8f773f8e262a', 'Physique');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `_examtoexam`
--

INSERT INTO `_examtoexam` (`parent`, `child`) VALUES
('0b180354-811a-4a7c-867c-361320f040f0', '8c90032f-ff5d-46f2-8735-acba6d24f976'),
('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', 'edcbc1b5-877c-4b51-b62a-e93b975581d8'),
('25bcbf69-4f0a-4264-af9f-27a22eec1713', '8c90032f-ff5d-46f2-8735-acba6d24f976'),
('28afe4f4-d995-4f1e-8ecf-e7378e54891a', '0b180354-811a-4a7c-867c-361320f040f0'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', 'f5c1af53-ca47-4a10-ad0d-b1ee63b7b738'),
('363f04d1-afc7-4a4b-ab44-1103a4f9147f', 'a2fed69f-d54c-4525-bf57-31b34197c105'),
('40c062b6-0d36-47c2-ae2a-6e30e18a03b3', 'a9caf0de-034a-499f-8d55-4d97f0eafad4'),
('40dc0e1e-2543-4e7b-8da8-c7169952db89', '0b180354-811a-4a7c-867c-361320f040f0'),
('450bf82d-71df-48cd-8e08-e0b62c892c56', 'f8a8fc13-36cf-4d93-9f66-f8b7ef7c3f6f'),
('470011bd-82f5-437b-8b5f-b73261e13ddf', '240b771f-594b-4043-abd4-5e53e889aa5d'),
('470011bd-82f5-437b-8b5f-b73261e13ddf', '801a9863-62e9-4d5c-ba94-0c1004f72e12'),
('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', '363f04d1-afc7-4a4b-ab44-1103a4f9147f'),
('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', 'fe2edefe-066c-430a-af48-8f773f8e262a'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', '785c16e2-9922-4ab5-8d43-8545e7cca4c4'),
('59c2131f-396f-4515-98d9-5590fff6a52b', 'a79be83e-96cd-433e-a2d4-b18bb6d2866f'),
('66e16c78-db95-4481-84db-0f0c9d3e2aba', 'a2fed69f-d54c-4525-bf57-31b34197c105'),
('66e16c78-db95-4481-84db-0f0c9d3e2aba', 'edcbc1b5-877c-4b51-b62a-e93b975581d8'),
('66e16c78-db95-4481-84db-0f0c9d3e2aba', 'ee125391-a841-43cc-bcaf-6b9d977563f7'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', '40dc0e1e-2543-4e7b-8da8-c7169952db89'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', '607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1'),
('801a9863-62e9-4d5c-ba94-0c1004f72e12', '58bfea21-4e61-4ce6-94ef-a6fea2889727'),
('9b8ef61a-1a1e-4502-b338-eacac647bc4e', 'c592004a-7405-49f1-974f-cd51de7f179e'),
('a0d135ff-2e65-413c-8f20-c8be82f63ab6', '449bac15-a5bc-4a72-acf1-c3ee6a793331'),
('a0d135ff-2e65-413c-8f20-c8be82f63ab6', '6b413a5d-3281-4beb-82fd-02c2018634ea'),
('a2fed69f-d54c-4525-bf57-31b34197c105', '035ab38c-69bd-4ea4-aadb-d91e120d7db7'),
('a2fed69f-d54c-4525-bf57-31b34197c105', '801a9863-62e9-4d5c-ba94-0c1004f72e12'),
('a2fed69f-d54c-4525-bf57-31b34197c105', 'ee125391-a841-43cc-bcaf-6b9d977563f7'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'd8f9cbe5-02ad-4888-a5e0-db22ee0f82eb'),
('a79be83e-96cd-433e-a2d4-b18bb6d2866f', '59c2131f-396f-4515-98d9-5590fff6a52b'),
('a79be83e-96cd-433e-a2d4-b18bb6d2866f', 'e037f5cf-12ce-4fbe-936d-67daff40f207'),
('a9caf0de-034a-499f-8d55-4d97f0eafad4', '785c16e2-9922-4ab5-8d43-8545e7cca4c4'),
('a9caf0de-034a-499f-8d55-4d97f0eafad4', 'fa3b20a4-c2de-4b3f-b993-7a71ffe12616'),
('abf7e80b-b66e-4c94-8289-9cf0cb450780', 'ee125391-a841-43cc-bcaf-6b9d977563f7'),
('c592004a-7405-49f1-974f-cd51de7f179e', '470011bd-82f5-437b-8b5f-b73261e13ddf'),
('e037f5cf-12ce-4fbe-936d-67daff40f207', '78fca392-05fe-4055-a520-1174a527bf31'),
('e48fba02-73b0-4809-bf19-1183c7b2dc90', '035ab38c-69bd-4ea4-aadb-d91e120d7db7'),
('edcbc1b5-877c-4b51-b62a-e93b975581d8', '29e45b8c-dc9f-497c-a51d-da4862c2a25d'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', 'e037f5cf-12ce-4fbe-936d-67daff40f207'),
('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', '363f04d1-afc7-4a4b-ab44-1103a4f9147f'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', '917629fb-ee6c-46bd-97b4-df599b18c15f'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'abf7e80b-b66e-4c94-8289-9cf0cb450780'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'd8f9cbe5-02ad-4888-a5e0-db22ee0f82eb'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'edcbc1b5-877c-4b51-b62a-e93b975581d8'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'f8a8fc13-36cf-4d93-9f66-f8b7ef7c3f6f');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `_examtogroup`
--

INSERT INTO `_examtogroup` (`exam`, `group`) VALUES
('035ab38c-69bd-4ea4-aadb-d91e120d7db7', '3491240a-e2f9-429e-80ad-9832e57f8d92'),
('0a88554a-6fdc-4c66-a040-1386285af669', '9d4cd589-f941-48c0-afad-0f42d7086362'),
('0b180354-811a-4a7c-867c-361320f040f0', '33070b97-0fea-4e53-8122-b086bc2e133e'),
('0b180354-811a-4a7c-867c-361320f040f0', '4e0a38ee-8006-459c-a7d3-1c3227da04af'),
('163b97a6-1e13-4a93-bb01-92a7ccd4cbeb', '3536bc20-f674-4a5f-beaa-4a885b6a489f'),
('1ab1e709-ab90-47f4-9969-fd2d7fb55243', 'd655eabb-da7c-41b9-b595-bc41bcb91c16'),
('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', 'd655eabb-da7c-41b9-b595-bc41bcb91c16'),
('28afe4f4-d995-4f1e-8ecf-e7378e54891a', '6011aa92-5915-407b-969d-8f7e1f2793ec'),
('29e45b8c-dc9f-497c-a51d-da4862c2a25d', '7159ef43-3468-4560-91d2-f58af5147d8e'),
('29e45b8c-dc9f-497c-a51d-da4862c2a25d', 'fe20df25-8188-4cf7-a395-4833585af8fc'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', '7b9277fa-2244-466d-9d60-2888ce4d66cf'),
('363f04d1-afc7-4a4b-ab44-1103a4f9147f', '90c2ce81-cb82-4c5e-8ab1-2921d8302642'),
('363f04d1-afc7-4a4b-ab44-1103a4f9147f', '951fd0c7-6383-4e9a-b429-f1251a179b45'),
('38c5bda4-556d-438d-bcac-5ca86d5e8837', '2b73c9db-c7fe-4f25-b88f-12e6255b4f2d'),
('40dc0e1e-2543-4e7b-8da8-c7169952db89', '782bf663-2ce1-48ce-8315-4bf6d336f71e'),
('40dc0e1e-2543-4e7b-8da8-c7169952db89', '9d4cd589-f941-48c0-afad-0f42d7086362'),
('40dc0e1e-2543-4e7b-8da8-c7169952db89', 'f7d24941-4a40-4ba7-8424-75ba0dad3b79'),
('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', '0a24e134-0a42-4bf3-bfed-5021b37751fc'),
('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', '5ec9b9ec-1095-4b5f-a9cd-f0199d22be52'),
('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', '623d01b0-02a0-4c43-a1ec-63e5d5979a06'),
('59c2131f-396f-4515-98d9-5590fff6a52b', '782bf663-2ce1-48ce-8315-4bf6d336f71e'),
('607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1', '6ba1df71-8855-49d8-80dd-5f4e38844b0a'),
('66e16c78-db95-4481-84db-0f0c9d3e2aba', '4e0a38ee-8006-459c-a7d3-1c3227da04af'),
('6b413a5d-3281-4beb-82fd-02c2018634ea', 'f5de3c99-0732-41e5-84a0-081125c87afe'),
('785c16e2-9922-4ab5-8d43-8545e7cca4c4', '6ba1df71-8855-49d8-80dd-5f4e38844b0a'),
('785c16e2-9922-4ab5-8d43-8545e7cca4c4', '90c2ce81-cb82-4c5e-8ab1-2921d8302642'),
('78fca392-05fe-4055-a520-1174a527bf31', '7042d1c9-c265-446c-b155-88f031bb53c9'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', '37549213-ea9a-4a85-80d2-6c8385fb1115'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', '84720fdc-d4a4-4e91-9298-1fc3175aebe8'),
('801a9863-62e9-4d5c-ba94-0c1004f72e12', '7042d1c9-c265-446c-b155-88f031bb53c9'),
('917629fb-ee6c-46bd-97b4-df599b18c15f', '7f94f2ed-272b-4bd2-8cef-c2c0437ec038'),
('917629fb-ee6c-46bd-97b4-df599b18c15f', '90c2ce81-cb82-4c5e-8ab1-2921d8302642'),
('a0d135ff-2e65-413c-8f20-c8be82f63ab6', 'd3b24d46-ae4a-4aa9-afc7-105cfb3ae8d7'),
('a2fed69f-d54c-4525-bf57-31b34197c105', '2b8e9fae-6231-46f0-87ae-d91f552b6624'),
('a2fed69f-d54c-4525-bf57-31b34197c105', '7f94f2ed-272b-4bd2-8cef-c2c0437ec038'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', '6011aa92-5915-407b-969d-8f7e1f2793ec'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'd3b24d46-ae4a-4aa9-afc7-105cfb3ae8d7'),
('a79be83e-96cd-433e-a2d4-b18bb6d2866f', '148b1196-3817-4d31-ab69-630a2e6155fa'),
('a9caf0de-034a-499f-8d55-4d97f0eafad4', '0f3653da-7ab4-41ea-9eac-c2fa043e0998'),
('abf7e80b-b66e-4c94-8289-9cf0cb450780', '7159ef43-3468-4560-91d2-f58af5147d8e'),
('c592004a-7405-49f1-974f-cd51de7f179e', '0bcc4f06-7e72-4524-bab4-a34c3db5711e'),
('d8f9cbe5-02ad-4888-a5e0-db22ee0f82eb', '7f94f2ed-272b-4bd2-8cef-c2c0437ec038'),
('d8f9cbe5-02ad-4888-a5e0-db22ee0f82eb', '9d4cd589-f941-48c0-afad-0f42d7086362'),
('edcbc1b5-877c-4b51-b62a-e93b975581d8', 'd655eabb-da7c-41b9-b595-bc41bcb91c16'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', '5ec9b9ec-1095-4b5f-a9cd-f0199d22be52'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', 'd655eabb-da7c-41b9-b595-bc41bcb91c16'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', 'f7d24941-4a40-4ba7-8424-75ba0dad3b79'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', '7f94f2ed-272b-4bd2-8cef-c2c0437ec038'),
('f8a8fc13-36cf-4d93-9f66-f8b7ef7c3f6f', '153ffa07-8ab5-4a3e-b805-eace49ffc671'),
('fa3b20a4-c2de-4b3f-b993-7a71ffe12616', '6ba1df71-8855-49d8-80dd-5f4e38844b0a');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `_examtomanager`
--

INSERT INTO `_examtomanager` (`exam`, `manager`) VALUES
('163b97a6-1e13-4a93-bb01-92a7ccd4cbeb', 'bf8bd6d2-2e74-47ca-867d-6735e6009f38'),
('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', '6cac9753-b308-408a-8607-be94daaac758'),
('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', 'c87bbe95-21bf-478f-bfff-8a7386bbcbba'),
('25bcbf69-4f0a-4264-af9f-27a22eec1713', '0cb2674d-b2ec-4ffc-b7c0-991fdcc2bb0d'),
('28afe4f4-d995-4f1e-8ecf-e7378e54891a', '7672e84c-0877-4058-8d26-bdd9e01a8c5b'),
('28afe4f4-d995-4f1e-8ecf-e7378e54891a', '8fc47990-41ca-4c05-abb5-905f9e46ac7f'),
('28afe4f4-d995-4f1e-8ecf-e7378e54891a', 'e7aa82a8-cc1d-450c-a385-fae7914357ef'),
('29e45b8c-dc9f-497c-a51d-da4862c2a25d', 'ffaf176f-038a-4909-81c7-80594a25e1e5'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', '3b0d53ce-9390-4b2b-9293-d3e6ccaa9bd2'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', '8fc47990-41ca-4c05-abb5-905f9e46ac7f'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', 'e7aa82a8-cc1d-450c-a385-fae7914357ef'),
('38c5bda4-556d-438d-bcac-5ca86d5e8837', '5e23ed53-4544-4e99-9689-e0108b0d1bc4'),
('40c062b6-0d36-47c2-ae2a-6e30e18a03b3', '74fc3c54-a3dc-4e7c-bd8f-72d196ecd6c6'),
('40dc0e1e-2543-4e7b-8da8-c7169952db89', '5e23ed53-4544-4e99-9689-e0108b0d1bc4'),
('40dc0e1e-2543-4e7b-8da8-c7169952db89', '85a5ddda-d321-4442-bf49-e331378ce073'),
('40dc0e1e-2543-4e7b-8da8-c7169952db89', 'a754b1b6-2a01-4624-83b9-4a242d58f3c2'),
('450bf82d-71df-48cd-8e08-e0b62c892c56', '1e0c39cd-8e97-46bb-b419-0c1fb97a6fff'),
('450bf82d-71df-48cd-8e08-e0b62c892c56', 'c87bbe95-21bf-478f-bfff-8a7386bbcbba'),
('470011bd-82f5-437b-8b5f-b73261e13ddf', '81dc1197-8c09-4f0b-ac16-cda770b16b9a'),
('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', '7c334929-a33a-4ff4-8703-2b22dec3adc0'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', '0b16454f-c73c-4bcd-9418-e3fd8948103a'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', 'b964a502-6ec1-4a00-a843-f779c14f9d10'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', 'fc4e1754-de3c-40ae-979c-2b208db24d1e'),
('59c2131f-396f-4515-98d9-5590fff6a52b', 'fc4e1754-de3c-40ae-979c-2b208db24d1e'),
('607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1', 'df41cc18-4833-4c40-86f8-a78d772d2d90'),
('66e16c78-db95-4481-84db-0f0c9d3e2aba', 'c1f5078c-764d-4a15-a868-bbf6508d6eb8'),
('6b413a5d-3281-4beb-82fd-02c2018634ea', 'a2f9a6d4-b1f1-4994-b747-907d9d8ca980'),
('78fca392-05fe-4055-a520-1174a527bf31', '0b16454f-c73c-4bcd-9418-e3fd8948103a'),
('78fca392-05fe-4055-a520-1174a527bf31', '85a5ddda-d321-4442-bf49-e331378ce073'),
('78fca392-05fe-4055-a520-1174a527bf31', 'de0b0b23-32f6-485a-bd25-0468e1139366'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', 'e1e07b19-b617-46cb-b931-89b59611ef1e'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', 'ffaf176f-038a-4909-81c7-80594a25e1e5'),
('801a9863-62e9-4d5c-ba94-0c1004f72e12', '22dc9102-532e-4448-8f72-cd345b85cc8f'),
('801a9863-62e9-4d5c-ba94-0c1004f72e12', 'de939cfc-c277-49c5-afcb-d0df41ff521c'),
('917629fb-ee6c-46bd-97b4-df599b18c15f', 'af6c79bc-31e8-4bdd-9396-b6583e868742'),
('917629fb-ee6c-46bd-97b4-df599b18c15f', 'de939cfc-c277-49c5-afcb-d0df41ff521c'),
('9b8ef61a-1a1e-4502-b338-eacac647bc4e', '7672e84c-0877-4058-8d26-bdd9e01a8c5b'),
('a0d135ff-2e65-413c-8f20-c8be82f63ab6', 'c87bbe95-21bf-478f-bfff-8a7386bbcbba'),
('e037f5cf-12ce-4fbe-936d-67daff40f207', 'a7eb779e-4591-44a0-98f1-49f84a788b34'),
('e58808bf-1bb1-4146-9253-90bfa43d08dd', '73bb5b42-ca6b-4c3a-8ffe-db754a1d4e28'),
('e58808bf-1bb1-4146-9253-90bfa43d08dd', 'ffaf176f-038a-4909-81c7-80594a25e1e5'),
('edcbc1b5-877c-4b51-b62a-e93b975581d8', '1e0c39cd-8e97-46bb-b419-0c1fb97a6fff'),
('edcbc1b5-877c-4b51-b62a-e93b975581d8', '8fc47990-41ca-4c05-abb5-905f9e46ac7f'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', 'b964a502-6ec1-4a00-a843-f779c14f9d10'),
('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', '699bd9a8-0c9f-4656-8693-d580b3f5d5e9'),
('f8a8fc13-36cf-4d93-9f66-f8b7ef7c3f6f', 'c1f5078c-764d-4a15-a868-bbf6508d6eb8'),
('fa3b20a4-c2de-4b3f-b993-7a71ffe12616', '699bd9a8-0c9f-4656-8693-d580b3f5d5e9'),
('fe2edefe-066c-430a-af48-8f773f8e262a', '6365cfee-f7ca-4729-9a96-4bd68455fb69'),
('fe2edefe-066c-430a-af48-8f773f8e262a', '81dc1197-8c09-4f0b-ac16-cda770b16b9a');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `_examtoroom`
--

INSERT INTO `_examtoroom` (`exam`, `room`) VALUES
('035ab38c-69bd-4ea4-aadb-d91e120d7db7', 'A1030'),
('163b97a6-1e13-4a93-bb01-92a7ccd4cbeb', 'A1010'),
('163b97a6-1e13-4a93-bb01-92a7ccd4cbeb', 'A1050'),
('163b97a6-1e13-4a93-bb01-92a7ccd4cbeb', 'E0040'),
('1ab1e709-ab90-47f4-9969-fd2d7fb55243', 'A1010'),
('1ab1e709-ab90-47f4-9969-fd2d7fb55243', 'E0080'),
('1ab1e709-ab90-47f4-9969-fd2d7fb55243', 'F020'),
('29e45b8c-dc9f-497c-a51d-da4862c2a25d', 'F021'),
('29e45b8c-dc9f-497c-a51d-da4862c2a25d', 'L0060'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', 'F120'),
('2bb42f42-0038-43a6-9c38-ba27d27ecd67', 'F130'),
('363f04d1-afc7-4a4b-ab44-1103a4f9147f', 'E0030'),
('449bac15-a5bc-4a72-acf1-c3ee6a793331', 'A0040'),
('449bac15-a5bc-4a72-acf1-c3ee6a793331', 'E0040'),
('449bac15-a5bc-4a72-acf1-c3ee6a793331', 'E0090'),
('450bf82d-71df-48cd-8e08-e0b62c892c56', 'E0040'),
('470011bd-82f5-437b-8b5f-b73261e13ddf', 'A050'),
('4eeb80e7-dfcb-4180-baa7-e03caa2c430b', 'A0020'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', 'F110'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', 'L0110'),
('607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1', 'A1050'),
('607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1', 'L0070'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', 'A1040'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', 'E0090'),
('917629fb-ee6c-46bd-97b4-df599b18c15f', 'E0060'),
('917629fb-ee6c-46bd-97b4-df599b18c15f', 'L0190'),
('a0d135ff-2e65-413c-8f20-c8be82f63ab6', 'E0090'),
('a2fed69f-d54c-4525-bf57-31b34197c105', 'A050'),
('a2fed69f-d54c-4525-bf57-31b34197c105', 'E0040'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'F020'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'F120'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'L0040'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', 'L0130'),
('a79be83e-96cd-433e-a2d4-b18bb6d2866f', 'L0030'),
('a9caf0de-034a-499f-8d55-4d97f0eafad4', 'E00050'),
('c592004a-7405-49f1-974f-cd51de7f179e', 'F150'),
('c592004a-7405-49f1-974f-cd51de7f179e', 'L0060'),
('d8f9cbe5-02ad-4888-a5e0-db22ee0f82eb', 'F140'),
('e037f5cf-12ce-4fbe-936d-67daff40f207', 'L0050'),
('edcbc1b5-877c-4b51-b62a-e93b975581d8', 'L0030'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', 'F110'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', 'F150'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', 'L0060'),
('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', 'E0040'),
('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', 'L0050'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'A1010'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'E00050'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'F140'),
('fe2edefe-066c-430a-af48-8f773f8e262a', 'A050'),
('fe2edefe-066c-430a-af48-8f773f8e262a', 'E0080');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `_examtoslot`
--

INSERT INTO `_examtoslot` (`exam`, `slot`) VALUES
('035ab38c-69bd-4ea4-aadb-d91e120d7db7', '6474630d-a378-45b6-a7ab-84e8139b1a11'),
('035ab38c-69bd-4ea4-aadb-d91e120d7db7', '7474d00d-3fb3-49e4-83d9-cd32c59e89cd'),
('0b180354-811a-4a7c-867c-361320f040f0', 'aff53af0-618c-4ea0-aa94-6cfe95a1c39c'),
('163b97a6-1e13-4a93-bb01-92a7ccd4cbeb', '3bc809aa-6d04-48da-895a-d4a58518f2e8'),
('1e67d5b7-7cd5-4ab7-980b-f2d57091beb4', 'd1db361f-5985-4ebd-94fe-7a5743fb790e'),
('240b771f-594b-4043-abd4-5e53e889aa5d', '4efda2c5-022d-44d8-a362-b3e773c4484e'),
('240b771f-594b-4043-abd4-5e53e889aa5d', 'acbc1dc8-d617-4a58-bbb9-c6ecd74f8e64'),
('38c5bda4-556d-438d-bcac-5ca86d5e8837', '33096acb-0b0e-4990-95d1-5e13d99df585'),
('38c5bda4-556d-438d-bcac-5ca86d5e8837', '512cbfdd-b0c4-40cf-819d-19763067dcdc'),
('38c5bda4-556d-438d-bcac-5ca86d5e8837', '7c4ba636-8247-4c86-abe9-968698f349e7'),
('38c5bda4-556d-438d-bcac-5ca86d5e8837', 'd24260cd-22ba-40e5-b6c0-76835bb394ea'),
('40c062b6-0d36-47c2-ae2a-6e30e18a03b3', '140d9e43-896a-4d08-bd9b-76941a9bee2d'),
('40c062b6-0d36-47c2-ae2a-6e30e18a03b3', '47f8039c-4642-4652-af15-baf83a49c8d3'),
('40dc0e1e-2543-4e7b-8da8-c7169952db89', '3bc809aa-6d04-48da-895a-d4a58518f2e8'),
('449bac15-a5bc-4a72-acf1-c3ee6a793331', 'bb089d19-d51a-4dcf-87d9-a1908b1dfec4'),
('450bf82d-71df-48cd-8e08-e0b62c892c56', '75a7bba2-809b-43a8-8352-78f478b8fd44'),
('450bf82d-71df-48cd-8e08-e0b62c892c56', '9f4a3188-0b52-4abb-a150-fb7ea6d01e3c'),
('470011bd-82f5-437b-8b5f-b73261e13ddf', '911200cd-44c3-4e24-ab16-a79a14bc679a'),
('470011bd-82f5-437b-8b5f-b73261e13ddf', 'e8a03451-4b11-432a-b527-eb92e54f9436'),
('58bfea21-4e61-4ce6-94ef-a6fea2889727', 'd24260cd-22ba-40e5-b6c0-76835bb394ea'),
('607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1', '0dde3ca0-e5b4-46ca-93d4-f8eac5501c90'),
('607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1', '9efa49da-514f-4044-8210-f87df3101087'),
('607fff8b-7f8c-4a8c-8bbe-5c54fc2e35c1', 'bb089d19-d51a-4dcf-87d9-a1908b1dfec4'),
('66e16c78-db95-4481-84db-0f0c9d3e2aba', '0dde3ca0-e5b4-46ca-93d4-f8eac5501c90'),
('66e16c78-db95-4481-84db-0f0c9d3e2aba', '36243e44-3389-41bb-a82b-65a312aee0ae'),
('7ccab381-c326-49e9-a9b3-3718d5c3955a', 'baf94a8b-fdaf-48ed-b539-9e50d70f0980'),
('801a9863-62e9-4d5c-ba94-0c1004f72e12', '078ac405-8685-4bf7-9a70-d02caddcefdd'),
('801a9863-62e9-4d5c-ba94-0c1004f72e12', '7c4ba636-8247-4c86-abe9-968698f349e7'),
('8c90032f-ff5d-46f2-8735-acba6d24f976', 'e587327e-805d-486b-b6a4-6d0a3d3f54e1'),
('9b8ef61a-1a1e-4502-b338-eacac647bc4e', '8167dbf6-5f31-4d8e-b029-48858c635409'),
('9b8ef61a-1a1e-4502-b338-eacac647bc4e', '98a6b989-cdda-4f11-a96c-0ebad6d026b2'),
('a502f374-192f-4a54-b0a7-513b21ddf8ab', '1bb9e37b-8a25-4248-8576-80d60c56602c'),
('c592004a-7405-49f1-974f-cd51de7f179e', 'bb5cf15d-6908-4329-a7bd-f9af47b45faa'),
('d8f9cbe5-02ad-4888-a5e0-db22ee0f82eb', '7e8a7778-a640-4ed3-be9d-e098157263f0'),
('e037f5cf-12ce-4fbe-936d-67daff40f207', '727cfcea-3b85-45d0-8f6a-89110fa55375'),
('e48fba02-73b0-4809-bf19-1183c7b2dc90', '3664169f-c295-49d5-89d8-c8e8f4c6184f'),
('e48fba02-73b0-4809-bf19-1183c7b2dc90', 'ab825e6a-eee6-43f8-9d82-7b6353d360cb'),
('e48fba02-73b0-4809-bf19-1183c7b2dc90', 'c0fb54fc-c76e-4f40-8759-eb0866a6d33f'),
('e58808bf-1bb1-4146-9253-90bfa43d08dd', '0932e5db-cdae-43be-bcb9-72f3ad074e97'),
('e58808bf-1bb1-4146-9253-90bfa43d08dd', 'e5bf7fe2-2c30-4553-b496-b7d46b5366de'),
('e58808bf-1bb1-4146-9253-90bfa43d08dd', 'fb78141f-20bd-4e57-97a2-b5002389bc61'),
('ee125391-a841-43cc-bcaf-6b9d977563f7', '7a508570-71d8-419e-ae74-565c9dcfe61a'),
('f5c1af53-ca47-4a10-ad0d-b1ee63b7b738', 'b3c95223-6d46-4781-8cfa-fc2935cc18c2'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', '246379ed-9cee-4ff3-bd10-761194e144a9'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'e5bf7fe2-2c30-4553-b496-b7d46b5366de'),
('f605bcd5-7ae9-478b-b2eb-6793683e20f2', 'e9abd39f-a6e4-44d7-b3e0-cfd3bbb1fcfb'),
('fa3b20a4-c2de-4b3f-b993-7a71ffe12616', '7a508570-71d8-419e-ae74-565c9dcfe61a'),
('fa3b20a4-c2de-4b3f-b993-7a71ffe12616', 'd85fab44-b039-43d3-8232-e871f38629dd'),
('fe2edefe-066c-430a-af48-8f773f8e262a', 'd423c7ad-3f66-4fe3-8bfa-e911dd30a799'),
('fe2edefe-066c-430a-af48-8f773f8e262a', 'de5f8194-4013-428a-bed4-c99bcaf5233e');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `_grouptogroup`
--

INSERT INTO `_grouptogroup` (`parent`, `child`) VALUES
('058be0fb-675a-4baf-b1ee-ccbbbd15c7ae', '3536bc20-f674-4a5f-beaa-4a885b6a489f'),
('0a24e134-0a42-4bf3-bfed-5021b37751fc', '6ba1df71-8855-49d8-80dd-5f4e38844b0a'),
('0bcc4f06-7e72-4524-bab4-a34c3db5711e', 'fe20df25-8188-4cf7-a395-4833585af8fc'),
('108f0388-770d-479b-bf67-1d9f3679299d', '3491240a-e2f9-429e-80ad-9832e57f8d92'),
('108f0388-770d-479b-bf67-1d9f3679299d', '7f94f2ed-272b-4bd2-8cef-c2c0437ec038'),
('1c2408bc-ed86-4099-a4fd-c316124ea8ed', '6ba1df71-8855-49d8-80dd-5f4e38844b0a'),
('20d845f8-96f4-4952-a6e3-15d84d56e8e5', '0deb6098-a6be-4733-876e-caff1d34261b'),
('20d845f8-96f4-4952-a6e3-15d84d56e8e5', 'f7d24941-4a40-4ba7-8424-75ba0dad3b79'),
('23fb2452-d036-44a1-bc12-007bd45ef65b', '148b1196-3817-4d31-ab69-630a2e6155fa'),
('23fb2452-d036-44a1-bc12-007bd45ef65b', '4e948497-65be-443f-bd92-016aa6b52899'),
('2b73c9db-c7fe-4f25-b88f-12e6255b4f2d', '0deb6098-a6be-4733-876e-caff1d34261b'),
('2b73c9db-c7fe-4f25-b88f-12e6255b4f2d', '623d01b0-02a0-4c43-a1ec-63e5d5979a06'),
('2b73c9db-c7fe-4f25-b88f-12e6255b4f2d', '7b9277fa-2244-466d-9d60-2888ce4d66cf'),
('2b8e9fae-6231-46f0-87ae-d91f552b6624', 'f7d24941-4a40-4ba7-8424-75ba0dad3b79'),
('33070b97-0fea-4e53-8122-b086bc2e133e', '6011aa92-5915-407b-969d-8f7e1f2793ec'),
('33070b97-0fea-4e53-8122-b086bc2e133e', '8846b0bf-d76a-446e-9cf7-cf4aa3ad4436'),
('33070b97-0fea-4e53-8122-b086bc2e133e', 'a9fdf3bf-3fe3-4121-bced-5b97e3cfe83e'),
('33070b97-0fea-4e53-8122-b086bc2e133e', 'acbfae4b-3483-499b-98d0-8165c81de0bb'),
('3491240a-e2f9-429e-80ad-9832e57f8d92', 'be97d018-684c-4572-8ddc-55986e052327'),
('3536bc20-f674-4a5f-beaa-4a885b6a489f', 'fe60a9fd-9d79-4a6b-8eeb-4f34522b4fa0'),
('37549213-ea9a-4a85-80d2-6c8385fb1115', '4e948497-65be-443f-bd92-016aa6b52899'),
('37549213-ea9a-4a85-80d2-6c8385fb1115', 'a9fdf3bf-3fe3-4121-bced-5b97e3cfe83e'),
('4e0a38ee-8006-459c-a7d3-1c3227da04af', '2b8e9fae-6231-46f0-87ae-d91f552b6624'),
('4e948497-65be-443f-bd92-016aa6b52899', 'ad95a910-5fb6-4678-807e-e6722983d006'),
('6011aa92-5915-407b-969d-8f7e1f2793ec', '4e948497-65be-443f-bd92-016aa6b52899'),
('623d01b0-02a0-4c43-a1ec-63e5d5979a06', '6ba1df71-8855-49d8-80dd-5f4e38844b0a'),
('623d01b0-02a0-4c43-a1ec-63e5d5979a06', '7eb54a65-9737-4ce4-8461-daf8659cba60'),
('6ba1df71-8855-49d8-80dd-5f4e38844b0a', 'f5de3c99-0732-41e5-84a0-081125c87afe'),
('7159ef43-3468-4560-91d2-f58af5147d8e', '4e0a38ee-8006-459c-a7d3-1c3227da04af'),
('7159ef43-3468-4560-91d2-f58af5147d8e', '6ba1df71-8855-49d8-80dd-5f4e38844b0a'),
('7159ef43-3468-4560-91d2-f58af5147d8e', 'fe20df25-8188-4cf7-a395-4833585af8fc'),
('782bf663-2ce1-48ce-8315-4bf6d336f71e', '058be0fb-675a-4baf-b1ee-ccbbbd15c7ae'),
('7eb54a65-9737-4ce4-8461-daf8659cba60', 'c284f4f3-afff-4be6-9871-92f93b4e7134'),
('7f94f2ed-272b-4bd2-8cef-c2c0437ec038', 'c284f4f3-afff-4be6-9871-92f93b4e7134'),
('84720fdc-d4a4-4e91-9298-1fc3175aebe8', '20d845f8-96f4-4952-a6e3-15d84d56e8e5'),
('8659f388-8207-4ddf-9da0-70ac8521a23f', '951fd0c7-6383-4e9a-b429-f1251a179b45'),
('8846b0bf-d76a-446e-9cf7-cf4aa3ad4436', 'f5de3c99-0732-41e5-84a0-081125c87afe'),
('90c2ce81-cb82-4c5e-8ab1-2921d8302642', '153ffa07-8ab5-4a3e-b805-eace49ffc671'),
('90c2ce81-cb82-4c5e-8ab1-2921d8302642', 'a9a2a6f7-4cf7-4b43-abaa-c561ebf0c6b9'),
('951fd0c7-6383-4e9a-b429-f1251a179b45', '6011aa92-5915-407b-969d-8f7e1f2793ec'),
('951fd0c7-6383-4e9a-b429-f1251a179b45', '90c2ce81-cb82-4c5e-8ab1-2921d8302642'),
('a9a2a6f7-4cf7-4b43-abaa-c561ebf0c6b9', 'acbfae4b-3483-499b-98d0-8165c81de0bb'),
('c284f4f3-afff-4be6-9871-92f93b4e7134', '951fd0c7-6383-4e9a-b429-f1251a179b45'),
('d3b24d46-ae4a-4aa9-afc7-105cfb3ae8d7', '108f0388-770d-479b-bf67-1d9f3679299d'),
('d3b24d46-ae4a-4aa9-afc7-105cfb3ae8d7', 'f9e147ea-91f5-4392-8644-dae46ed5b8a0'),
('f5de3c99-0732-41e5-84a0-081125c87afe', '058be0fb-675a-4baf-b1ee-ccbbbd15c7ae'),
('f5de3c99-0732-41e5-84a0-081125c87afe', '6011aa92-5915-407b-969d-8f7e1f2793ec'),
('f9e147ea-91f5-4392-8644-dae46ed5b8a0', '951fd0c7-6383-4e9a-b429-f1251a179b45'),
('f9e147ea-91f5-4392-8644-dae46ed5b8a0', 'c284f4f3-afff-4be6-9871-92f93b4e7134'),
('fe60a9fd-9d79-4a6b-8eeb-4f34522b4fa0', '5ec9b9ec-1095-4b5f-a9cd-f0199d22be52');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `_slottoroom`
--

INSERT INTO `_slottoroom` (`slot`, `room`) VALUES
('05a8b4ed-3953-46cd-b4b3-0fd3c46d993a', 'E0030'),
('078ac405-8685-4bf7-9a70-d02caddcefdd', 'A1050'),
('0a5f370b-5234-472c-8cfe-e2fc1316a24c', 'A1060'),
('0a5f370b-5234-472c-8cfe-e2fc1316a24c', 'E0040'),
('0e83c8f6-d495-41da-bded-962017ce8bd8', 'E0070'),
('0ee84d18-4859-4ef8-a08f-93ace9e44fae', 'L0140'),
('0f1e87af-4138-48b4-a79b-5549c7df2e39', 'E0060'),
('17ede819-68f6-40d1-9de6-a0f0aa164250', 'L0040'),
('17f8c40c-0949-4b43-b5b1-6a66a28a32ec', 'F150'),
('1b40c900-7b65-42f0-a4f0-e1b71af921f7', 'A1060'),
('2e7d53ca-4be2-4f16-b3a7-c517de54c9c7', 'L0110'),
('2f06f008-5383-446b-b651-d009099c2702', 'A1040'),
('34c4b802-6dda-42c3-81e8-e48b4154c1c6', 'E0080'),
('34c4b802-6dda-42c3-81e8-e48b4154c1c6', 'F021'),
('34c4b802-6dda-42c3-81e8-e48b4154c1c6', 'F023'),
('3ec6fa32-6fb4-4b6a-9db8-b4e06254bb6d', 'E1010'),
('40026353-4310-4039-8cc0-a52d149ad21b', 'F120'),
('470afc5d-af4d-4613-907f-55d6b13bb055', 'L0050'),
('4d3e56a7-5e05-4ac5-bd0b-98025e131995', 'E0040'),
('4efda2c5-022d-44d8-a362-b3e773c4484e', 'L0060'),
('6429f863-6daa-4aa2-a265-dbe1c3328b66', 'L0040'),
('67666f92-92d5-4d27-9877-c3b652d3886e', 'L0170'),
('718eaba4-21d5-4656-a3e5-0ddd4eef73a0', 'A0020'),
('718eaba4-21d5-4656-a3e5-0ddd4eef73a0', 'A1040'),
('718eaba4-21d5-4656-a3e5-0ddd4eef73a0', 'L0150'),
('727cfcea-3b85-45d0-8f6a-89110fa55375', 'F120'),
('7474d00d-3fb3-49e4-83d9-cd32c59e89cd', 'A1050'),
('7a508570-71d8-419e-ae74-565c9dcfe61a', 'E0060'),
('841b527d-0549-4ab8-a2d5-909dfa651081', 'E0040'),
('841b527d-0549-4ab8-a2d5-909dfa651081', 'F140'),
('9905d1fe-d93b-4985-a90f-1a1fe7bdfee4', 'L0050'),
('9f4a3188-0b52-4abb-a150-fb7ea6d01e3c', 'L0070'),
('9fe89647-ff69-4991-8ccd-571fbcf6068b', 'A1050'),
('a36fb62d-4a52-4e43-aec0-3edddeaaa641', 'A0030'),
('a36fb62d-4a52-4e43-aec0-3edddeaaa641', 'L0180'),
('a61122a7-16ff-48e0-88d9-b8eefa9edee8', 'L0190'),
('ab825e6a-eee6-43f8-9d82-7b6353d360cb', 'A1020'),
('ab825e6a-eee6-43f8-9d82-7b6353d360cb', 'F120'),
('acbc1dc8-d617-4a58-bbb9-c6ecd74f8e64', 'E0070'),
('baf94a8b-fdaf-48ed-b539-9e50d70f0980', 'A1010'),
('bf5d634f-5c10-4952-8572-a989514ed1d2', 'F022'),
('cc20006f-4a5d-4afb-9618-23b9633eae97', 'F120'),
('d17e4c81-7bf0-46e7-b874-851b2bee4756', 'F020'),
('d1db361f-5985-4ebd-94fe-7a5743fb790e', 'F021'),
('d85fab44-b039-43d3-8232-e871f38629dd', 'E0080'),
('d85fab44-b039-43d3-8232-e871f38629dd', 'F022'),
('d85fab44-b039-43d3-8232-e871f38629dd', 'F120'),
('e2e5e963-51e1-4e7c-9d6c-b0773fb9042a', 'E0090'),
('e587327e-805d-486b-b6a4-6d0a3d3f54e1', 'A1010'),
('e5bf7fe2-2c30-4553-b496-b7d46b5366de', 'E00050');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `_subjecttomockup`
--

INSERT INTO `_subjecttomockup` (`subject`, `mockUp`) VALUES
('Codage', 'BachelorOne_Informatique'),
('Codage', 'BachelorThree_Informatique'),
('Codage', 'MasterOne_Informatique'),
('Codage', 'MasterThree_Informatique'),
('Codage', 'MasterTwo_Informatique'),
('Derivée', 'BachelorOne_Mathematique'),
('Derivée', 'BachelorOne_Physique'),
('Derivée', 'BachelorTwo_Mathematique'),
('Derivée', 'MasterThree_Droit'),
('Dissection', 'BachelorOne_Droit'),
('Dissection', 'BachelorOne_Sociologie'),
('Dissection', 'BachelorThree_Anglais'),
('Dissection', 'BachelorTwo_Anglais'),
('Dissection', 'BachelorTwo_Histoire'),
('Dissection', 'BachelorTwo_Orthophonie'),
('Dissection', 'MasterOne_Anglais'),
('Dissection', 'MasterOne_Orthophonie'),
('Dissection', 'MasterTwo_Sociologie'),
('Integrale', 'BachelorOne_Physique'),
('Integrale', 'BachelorThree_Informatique'),
('Integrale', 'BachelorThree_Orthophonie'),
('Integrale', 'BachelorTwo_Anglais'),
('Integrale', 'BachelorTwo_Mathematique'),
('Integrale', 'MasterOne_Anglais'),
('Integrale', 'MasterOne_Physique'),
('Integrale', 'MasterThree_Histoire'),
('Integrale', 'MasterThree_Physique'),
('Integrale', 'MasterTwo_Mathematique');


COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
