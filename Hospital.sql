-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 05, 2011 at 06:45 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hospitalms`
--

-- --------------------------------------------------------

--
-- Table structure for table `check_up`
--

CREATE TABLE IF NOT EXISTS `check_up` (
  `check_up_ID` int(11) NOT NULL AUTO_INCREMENT,
  `pressure` varchar(10) NOT NULL,
  `weight` decimal(10,2) NOT NULL,
  `temperature` decimal(10,2) NOT NULL,
  `pulse` decimal(10,2) NOT NULL,
  `patient_ID` int(11) NOT NULL,
  PRIMARY KEY (`check_up_ID`),
  UNIQUE KEY `check_up_ID_UNIQUE` (`check_up_ID`),
  KEY `Patient_ID` (`patient_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=43 ;

--
-- Dumping data for table `check_up`
--

INSERT INTO `check_up` (`check_up_ID`, `pressure`, `weight`, `temperature`, `pulse`, `patient_ID`) VALUES
(2, '140/90', '78.00', '36.00', '71.00', 4),
(8, '36/47', '57.00', '35.00', '80.00', 1),
(7, '140/70', '72.00', '36.00', '78.00', 6),
(6, '140/49', '82.00', '36.00', '77.00', 3),
(9, '36/47', '57.00', '35.00', '80.00', 1),
(10, '36/56', '67.00', '36.00', '87.00', 12),
(11, '149/90', '81.00', '36.00', '77.00', 4),
(12, '140/88', '77.00', '38.00', '72.00', 4),
(13, '140/90', '81.00', '37.00', '77.00', 4),
(14, '140/85', '83.00', '37.00', '70.00', 4),
(15, '140/90', '76.00', '37.00', '73.00', 10),
(16, '140/90', '65.00', '37.00', '78.00', 20),
(17, '32/56', '72.00', '37.00', '56.00', 2),
(18, '36/56', '56.00', '36.00', '56.00', 11),
(19, '35/87', '100.00', '41.00', '47.00', 5),
(20, '45/56', '87.00', '37.00', '56.00', 8),
(21, '140/90', '85.00', '38.00', '74.00', 3),
(22, '150/87', '56.00', '38.00', '73.00', 11),
(23, '140/90', '79.00', '37.00', '71.00', 1),
(24, '140/90', '81.00', '37.00', '81.00', 10),
(25, '142/89', '56.00', '38.00', '70.00', 2),
(26, '140/90', '81.00', '37.00', '78.00', 6),
(27, '140/90', '83.00', '37.00', '78.00', 14),
(28, '140/90', '91.00', '37.00', '78.00', 8),
(29, '140/90', '89.00', '37.00', '78.00', 3),
(30, '140/90', '69.00', '37.00', '77.00', 17),
(31, '140/90', '79.00', '37.00', '72.00', 4),
(32, '140/90', '89.00', '36.00', '71.00', 11),
(33, '150/90', '88.00', '38.00', '79.00', 3),
(34, '140/90', '67.00', '38.00', '70.00', 14),
(35, '140/90', '57.00', '38.00', '70.00', 22),
(36, '146/92', '80.00', '38.00', '67.00', 1),
(37, '140/87', '87.00', '38.00', '67.00', 21),
(38, '147/89', '76.00', '41.00', '78.00', 20),
(39, '147/88', '98.00', '38.00', '69.00', 12),
(40, '138/97', '45.00', '32.00', '56.00', 3),
(41, '140/100', '90.00', '45.00', '80.00', 13),
(42, '120/90', '60.00', '36.00', '72.00', 23);

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis`
--

CREATE TABLE IF NOT EXISTS `diagnosis` (
  `Diagnosis_ID` int(11) NOT NULL AUTO_INCREMENT,
  `patient_ID` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  `Date_Of_Diagnosis` varchar(15) NOT NULL,
  `Diagnosis` mediumtext NOT NULL,
  `Prescription` mediumtext NOT NULL,
  `Total` decimal(10,2) NOT NULL,
  `Approved` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`Diagnosis_ID`),
  UNIQUE KEY `Diagnosis_ID_UNIQUE` (`Diagnosis_ID`),
  KEY `patient_ID` (`patient_ID`),
  KEY `user_ID` (`user_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `diagnosis`
--

INSERT INTO `diagnosis` (`Diagnosis_ID`, `patient_ID`, `user_ID`, `Date_Of_Diagnosis`, `Diagnosis`, `Prescription`, `Total`, `Approved`) VALUES
(4, 3, 4, 'March 31, 2011', '-Kichwa kibovu\n-Anahara\n-Kojoa kila mahali', '-Aczone Gel(Dapsone) 3 times a day\n-Amoxicillin(Amoxil) 3 times a day for 10 days\n', '0.00', b'0'),
(5, 4, 4, 'March 31, 2011', 'kakskas', '-Aspirin(Bayer) 4 times\n-Aspirin(Bayer) zjhxkz\n', '0.00', b'1'),
(6, 1, 4, 'April 1, 2011', '-A lot of saliva\n-Loss of appetite\n-Yellow eyes\n-Bad beath', '-Augmentin(Amoxicillin Clavulanate) once a day for 5 days\n-Panadol Extra once a day until finished\n-Sonadol thrice a day for 3 days\n', '0.00', b'0'),
(7, 4, 4, 'April 1, 2011', '-Heavy breathing\n-Dry cough\n-Red eyes\n-Swollen lips', '-Action 3 times a day for 3 days\n-Zedex Cough Syrup 2 teaspoonfuls twice a day\n', '0.00', b'1'),
(8, 4, 4, 'April 1, 2011', '-Swollen  right ankle\n', '-Hedex 3 times a day for 3 days\n-Aczone Gel(Dapsone) 1\n', '0.00', b'1'),
(9, 6, 4, 'April 1, 2011', '-Sore throat\n-running nose\n-Dry cough', '-Zedex Cough Syrup twice a day for 5 days\n-Panadol twice 3 times a day\n', '0.00', b'1'),
(10, 4, 4, 'April 1, 2011', '-Balding\n-Loss of memory', '-Aspirin(Bayer) 2 times a day\n\n-Calpol once a day\n\n', '0.00', b'1'),
(11, 12, 4, 'April 1, 2011', '-Loss of appetite\n-Vomiting\n-Blood-shot eyes', '-Dawanol 3 times a day for 5 days\n\n-Amoxicillin(Amoxil) once a day for 7 days\n\n', '0.00', b'1'),
(12, 10, 4, 'April 1, 2011', '-Balding\n-Greying of hair', 'NA', '0.00', b'0'),
(13, 20, 4, 'April 1, 2011', '-Kichwa\n-mgongo\n-mdomo', '-Aspirin(Bayer) 5 times a day\n\n-Panadol 7 times a day\n\n', '0.00', b'1'),
(14, 10, 4, 'April 1, 2011', '-back pains', '-Deep Heat Ointment twice daily\n\n', '0.00', b'1'),
(15, 1, 4, 'April 1, 2011', 'back pains\nheadache', '-Deep Heat Spray apply twice a day\n\n-Panadol 3 times a day for 3 days\n\n', '436.00', b'1'),
(16, 2, 4, 'April 1, 2011', 'Coughing.', '-Zedex Cough Syrup 2 table spoons 3 times a day\n\n', '396.00', b'1'),
(17, 6, 4, 'April 4, 2011', 'Headache\nHeartburn\nKushutshuta', '-Action 3 times a day\n\n-Scotts Emulsion 2 teaspoons for 10 days\n\n', '442.00', b'1'),
(18, 14, 4, 'April 4, 2011', 'Headache\nStomachache', '-Action 10\n\n-Calpol 4times a day\n\n-Panadol Extra 3 times a day\n\n', '376.00', b'1'),
(19, 8, 4, 'April 4, 2011', '-Backache\n-headache\n-Lack of appetite', '-Deep Heat Spray 1\n\n-Panadol Extra 3times a day\n\n-Calpol once a day\n\n', '596.00', b'1'),
(20, 3, 4, 'April 4, 2011', 'Back ache\nLoss of hair\njoint aches\nFlu', '-Deep Freeze Spray 1\n\n-Aczone Gel(Dapsone) 1\n\n-Panadol Extra 3 times a day\n\n-Zedex Cough Syrup twice a  day\n\n', '828.00', b'1'),
(21, 4, 4, 'April 4, 2011', 'Sweaty palms\nweek knees\nheavy arms\nVomiting', '-Amoxicillin(Amoxil) 3times a day for 3 days\n\n-Aspirin(Bayer) twice a day for 5 days\n\n-Seven Seas once a day for 14 days\n\n-Panadol 3 times a day for 4 days\n\n', '966.00', b'1'),
(22, 14, 4, 'April 5, 2011', 'Coughing.', '-Calpol 3 teaspoons 3 times a day\n\n', '96.00', b'1'),
(23, 11, 4, 'April 5, 2011', 'Head aches.', '-Panadol Extra 2 tablets a day\n\n', '190.00', b'1'),
(24, 3, 4, 'April 5, 2011', 'Knee injury.', '-Deep Heat Spray apply twice daily\n\n', '4340.00', b'1'),
(25, 17, 4, 'April 5, 2011', 'Coughing.', '-Calpol 3 teaspoons 3 times a day\n\n', '96.00', b'1'),
(26, 22, 4, 'April 5, 2011', 'Back pains', '-Deep Freeze Spray Apply twice daily\n\n', '320.00', b'1'),
(27, 1, 4, 'April 5, 2011', 'Back ache.', '-Deep Heat Spray Aplly 2 times daily\n\n', '310.00', b'1'),
(28, 20, 4, 'April 5, 2011', 'Vomitting.', '-Dawanol 2 tablets 3  times a day\n\n', '300.00', b'1'),
(29, 3, 12, 'April 5, 2011', 'Back pains with selective amnesia', '-Hedex 1 tab 3 times a day for 10 days\n\n-Deep Heat Spray apply once daily\n\n', '640.00', b'1'),
(30, 12, 1, 'April 5, 2011', 'Malaria.', '-Augmentin(Amoxicillin Clavulanate) 2 tablets 3 times day\n\n', '0.00', b'0'),
(31, 21, 4, 'April 5, 2011', 'Malaria', '-Aspirin(Bayer) 2\n\n-Deep Freeze Spray 3\n\n', '0.00', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis_medication`
--

CREATE TABLE IF NOT EXISTS `diagnosis_medication` (
  `Diagnosis_Medication_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Diagnosis_ID` int(11) NOT NULL,
  `Drug_ID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `SubTotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Diagnosis_Medication_ID`),
  UNIQUE KEY `Diagnosis_Medication_ID_UNIQUE` (`Diagnosis_Medication_ID`),
  KEY `Diagnosis_ID` (`Diagnosis_ID`),
  KEY `Drug_ID` (`Drug_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=48 ;

--
-- Dumping data for table `diagnosis_medication`
--

INSERT INTO `diagnosis_medication` (`Diagnosis_Medication_ID`, `Diagnosis_ID`, `Drug_ID`, `Quantity`, `SubTotal`) VALUES
(1, 5, 5, 2, '380.00'),
(2, 7, 15, 9, '81.00'),
(3, 7, 14, 1, '198.00'),
(4, 8, 12, 9, '99.00'),
(5, 8, 3, 1, '120.00'),
(6, 6, 6, 1, '670.00'),
(7, 6, 17, 6, '114.00'),
(8, 6, 18, 9, '72.00'),
(9, 4, 3, 1, '120.00'),
(10, 4, 4, 1, '440.00'),
(11, 10, 10, 1, '96.00'),
(12, 10, 5, 5, '950.00'),
(13, 11, 11, 15, '150.00'),
(14, 11, 4, 1, '440.00'),
(15, 13, 5, 4, '760.00'),
(16, 13, 9, 3, '42.00'),
(17, 0, 20, 1, '310.00'),
(18, 0, 10, 1, '96.00'),
(19, 14, 19, 1, '280.00'),
(20, 15, 20, 1, '310.00'),
(21, 15, 9, 9, '126.00'),
(22, 16, 14, 2, '396.00'),
(23, 17, 15, 10, '90.00'),
(24, 17, 16, 2, '352.00'),
(25, 18, 15, 10, '90.00'),
(26, 18, 10, 1, '96.00'),
(27, 18, 17, 10, '190.00'),
(28, 19, 20, 1, '310.00'),
(29, 19, 17, 10, '190.00'),
(30, 19, 10, 1, '96.00'),
(31, 20, 21, 1, '320.00'),
(32, 20, 3, 1, '120.00'),
(33, 20, 17, 10, '190.00'),
(34, 20, 14, 1, '198.00'),
(35, 21, 4, 1, '440.00'),
(36, 21, 5, 1, '190.00'),
(37, 21, 13, 1, '126.00'),
(38, 21, 9, 15, '210.00'),
(39, 22, 10, 1, '96.00'),
(40, 24, 20, 14, '4340.00'),
(41, 25, 10, 1, '96.00'),
(42, 23, 17, 10, '190.00'),
(43, 27, 20, 1, '310.00'),
(44, 28, 11, 30, '300.00'),
(45, 29, 12, 30, '330.00'),
(46, 29, 20, 1, '310.00'),
(47, 26, 21, 1, '320.00');

-- --------------------------------------------------------

--
-- Table structure for table `drug`
--

CREATE TABLE IF NOT EXISTS `drug` (
  `DrugID` int(11) NOT NULL AUTO_INCREMENT,
  `Drug_name` varchar(50) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`DrugID`),
  UNIQUE KEY `DrugID_UNIQUE` (`DrugID`),
  UNIQUE KEY `Drug_name_UNIQUE` (`Drug_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `drug`
--

INSERT INTO `drug` (`DrugID`, `Drug_name`, `Quantity`, `price`) VALUES
(3, 'Aczone Gel(Dapsone)', 320, '120.00'),
(4, 'Amoxicillin(Amoxil)', 230, '440.00'),
(5, 'Aspirin(Bayer)', 490, '190.00'),
(6, 'Augmentin(Amoxicillin Clavulanate)', 190, '670.00'),
(7, 'Augmentin ES(Amoxicillin Clavulanate Potassium)', 176, '780.00'),
(18, 'Sonadol', 320, '8.00'),
(9, 'Panadol', 300, '14.00'),
(10, 'Calpol', 190, '96.00'),
(11, 'Dawanol', 290, '10.00'),
(12, 'Hedex', 320, '11.00'),
(13, 'Seven Seas', 110, '126.00'),
(14, 'Zedex Cough Syrup', 225, '198.00'),
(15, 'Action', 200, '9.00'),
(16, 'Scotts Emulsion', 180, '176.00'),
(17, 'Panadol Extra', 300, '19.00'),
(19, 'Deep Heat Ointment', 110, '280.00'),
(20, 'Deep Heat Spray', 130, '310.00'),
(21, 'Deep Freeze Spray', 90, '320.00');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `Patient_ID` int(11) NOT NULL AUTO_INCREMENT,
  `fName` varchar(15) NOT NULL,
  `sName` varchar(15) NOT NULL,
  `OtherNames` varchar(25) DEFAULT NULL,
  `national_ID` int(11) DEFAULT NULL,
  `Date_Of_Birth` varchar(15) NOT NULL,
  `address` varchar(25) NOT NULL,
  `gender` varchar(7) NOT NULL,
  `blood_type` varchar(7) NOT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Patient_ID`),
  UNIQUE KEY `Patient_ID_UNIQUE` (`Patient_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`Patient_ID`, `fName`, `sName`, `OtherNames`, `national_ID`, `Date_Of_Birth`, `address`, `gender`, `blood_type`, `phoneNumber`) VALUES
(1, 'Paul', 'Tergat', 'Mkale', 2930192, '16/12/70', 'P.O. Box 9021', 'Male', 'O +ve', '0721897623'),
(2, 'Dennis', 'Oliech', 'Janateng', 9821901, '3/29/80', 'P.O. Box 903221', 'Male', 'AB +ve', '219099078'),
(3, 'Uhuru', 'Kenyatta', 'Muigai', 1290876, '7/19/61', 'P.O. Box 89239', 'Male', 'B -ve', '291678621'),
(4, 'Anthony', 'Jaramba', 'Odhiambo', 2789078, '7/14/89', 'P.O. Box 4568', 'Male', 'O +ve', '899239017'),
(5, 'Albert', 'Ndolo', 'Mulwa', 2901840, '2/11/88', 'P.O. Box 909832', 'Male', 'AB -ve', '8013990'),
(6, 'Irene', 'Mutua', 'Kilonz', 2902823, '5/16/91', 'P.O. Box 90234', 'Female', 'B -ve', '291732892'),
(8, 'Janeth', 'Jespkosgei', 'Sang', 2093423, '1/6/88', 'P.O. Box 73291', 'Female', 'O +ve', '27238621'),
(11, 'Ian ', 'Mutuku', 'Maweu', 345465767, '3/14/91', 'P. O. Box 59527', 'Male', 'B -ve', '712206482'),
(12, 'Oscar ', 'Otindo', 'Mavado', 123456, '3/16/88', 'P. O. Box 12345', 'Male', 'O +ve', '1234567'),
(10, 'Stanley', 'Kariuki', 'Kamau', 921818, '3/13/85', 'P.O. Box 89329', 'Male', 'AB +ve', '093328918'),
(13, 'Ronald', 'Kitali', 'Mutuku', 87237832, '4/19/89', 'P.O. Box 8293', 'Male', 'B -ve', '032892362'),
(14, 'Charlene', 'Nyambura', 'Mungich', 9138931, '2/9/88', 'P.O. Box 829392', 'Female', 'O -ve', '92988249'),
(15, 'Ann', 'Kiki', '', 983218, '5/5/89', 'P.O. Box 9283', 'Female', 'A +ve', '09138931'),
(16, 'Elizabeth', 'Nyamu', '', 7317318, '8/15/91', 'P.O. Box 83813', 'Female', 'A -ve', '61841748'),
(17, 'Robert', 'Tipis', 'Laibon', NULL, '4/15/09', 'P.O. Box 8218712', 'Male', 'A +ve', '728382'),
(18, 'Joan', 'Chepkemoi', 'Rono', NULL, '6/21/07', 'P.O. Box 9138931', 'Female', 'B -ve', '871731'),
(19, 'Kevin', 'Mwangi', 'Nganga', 9813931, '6/10/86', 'P.O. Box 892398', 'Male', 'B -ve', '7893871'),
(20, 'Tito', 'Kaminja', '', NULL, '4/23/09', 'P.O. Box 892392', 'Male', 'B +ve', '8919821'),
(21, 'Cherlene', 'Nyambura', 'Migwe', 3456565, '4/11/90', '59527-00200', 'Female', 'O +ve', '0723456789f'),
(22, 'Mary', 'Sheila', 'Kabutha', 87762344, '4/10/88', '595321-00100', 'Female', 'B +ve', '0720657087'),
(23, 'Rono', 'Gilbert', 'Kiprop', 34, '5/13/21', '56 Nairobi', 'Male', 'A +ve', '564747');

-- --------------------------------------------------------

--
-- Table structure for table `queue`
--

CREATE TABLE IF NOT EXISTS `queue` (
  `queue_ID` int(11) NOT NULL AUTO_INCREMENT,
  `patient_ID` int(11) NOT NULL,
  `seenNurse` bit(1) NOT NULL DEFAULT b'0',
  `seenDoctor` bit(1) NOT NULL DEFAULT b'0',
  `seenPharmacy` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`queue_ID`),
  UNIQUE KEY `queue_ID_UNIQUE` (`queue_ID`),
  KEY `patient_ID` (`patient_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=58 ;

--
-- Dumping data for table `queue`
--

INSERT INTO `queue` (`queue_ID`, `patient_ID`, `seenNurse`, `seenDoctor`, `seenPharmacy`) VALUES
(57, 15, b'0', b'0', b'0'),
(56, 23, b'1', b'0', b'0'),
(55, 5, b'0', b'0', b'0'),
(47, 12, b'1', b'1', b'0'),
(52, 4, b'0', b'0', b'0'),
(51, 13, b'1', b'0', b'0'),
(54, 16, b'0', b'0', b'0'),
(48, 21, b'1', b'1', b'0'),
(53, 10, b'0', b'0', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `Roles_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Role_name` varchar(25) NOT NULL,
  PRIMARY KEY (`Roles_ID`),
  UNIQUE KEY `Roles_ID_UNIQUE` (`Roles_ID`),
  UNIQUE KEY `Role_name_UNIQUE` (`Role_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`Roles_ID`, `Role_name`) VALUES
(1, 'Administrator'),
(2, 'Doctor'),
(3, 'Nurse'),
(4, 'Pharmacist'),
(5, 'Receptionist');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_ID` int(11) NOT NULL AUTO_INCREMENT,
  `fName` varchar(15) NOT NULL,
  `sName` varchar(15) NOT NULL,
  `otherNames` varchar(25) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `role_ID` int(11) NOT NULL,
  PRIMARY KEY (`user_ID`),
  UNIQUE KEY `user_ID_UNIQUE` (`user_ID`),
  KEY `role_ID` (`role_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_ID`, `fName`, `sName`, `otherNames`, `password`, `role_ID`) VALUES
(1, 'Arnold', 'Mwana', 'Williams', 'aa', 2),
(15, 'Cannibal', 'Dogo', 'Nyaga', '123456', 2),
(3, 'Brian', 'Omondi', 'Okello', 'aa', 3),
(4, 'Albert', 'Rono', 'Kiplagat', '12', 1),
(5, 'El Hadji', 'Idd', 'Toya', '123456', 2),
(6, 'Daniel', 'Kihara', 'Kipara', '123456', 3),
(7, 'William', 'Ruto', 'Samoei', '123456', 4),
(8, 'Macdonald', 'Mariga', 'Wanyama', '123456', 2),
(9, 'Duke', 'Opiyo', 'Onyango', '123456', 4),
(10, 'Anthony', 'Shikokoti', 'Wefwe', '123456', 4),
(11, 'Cristobal', 'Wepukhulu', 'Mwasio', '123456', 3),
(12, 'Mike', 'Rua', 'Mungai', '123456', 2),
(13, 'Jamal', 'Abdi', 'Mohammed', '123456', 3),
(16, 'Diana', 'Ross', 'Kemp', '123456', 5),
(17, 'Brian', 'Kip', 'KIpchoge', '123456', 5),
(18, 'Brian', 'Onyango', 'Odhiambo', '123456', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
