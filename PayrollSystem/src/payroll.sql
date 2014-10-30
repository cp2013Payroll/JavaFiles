-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 30, 2014 at 01:04 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `payroll`
--

-- --------------------------------------------------------

--
-- Table structure for table `affiliation`
--

CREATE TABLE IF NOT EXISTS `affiliation` (
  `affId` int(11) NOT NULL AUTO_INCREMENT,
  `affUnionName` varchar(50) NOT NULL,
  `affUnionDues` double NOT NULL,
  `empId` int(11) NOT NULL,
  PRIMARY KEY (`affId`),
  UNIQUE KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `commissionclass`
--

CREATE TABLE IF NOT EXISTS `commissionclass` (
  `commClassId` int(11) NOT NULL AUTO_INCREMENT,
  `commClassRate` double NOT NULL,
  `commClassSalary` double NOT NULL,
  `empId` int(11) NOT NULL,
  PRIMARY KEY (`commClassId`),
  UNIQUE KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `directmethod`
--

CREATE TABLE IF NOT EXISTS `directmethod` (
  `directPayId` int(11) NOT NULL AUTO_INCREMENT,
  `directPayBankName` varchar(25) NOT NULL,
  `directPayBSB` varchar(8) NOT NULL,
  `directPayAcctNum` varchar(25) NOT NULL,
  `empId` int(11) NOT NULL,
  PRIMARY KEY (`directPayId`),
  UNIQUE KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `empId` int(11) NOT NULL AUTO_INCREMENT,
  `empFname` varchar(25) NOT NULL,
  `empLname` varchar(25) NOT NULL,
  `empAddress` varchar(50) NOT NULL,
  `empType` varchar(1) NOT NULL,
  PRIMARY KEY (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `holdmethod`
--

CREATE TABLE IF NOT EXISTS `holdmethod` (
  `holdPayId` int(11) NOT NULL AUTO_INCREMENT,
  `empId` int(11) NOT NULL,
  PRIMARY KEY (`holdPayId`),
  UNIQUE KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `hourlyclass`
--

CREATE TABLE IF NOT EXISTS `hourlyclass` (
  `hourlyClassId` int(11) NOT NULL AUTO_INCREMENT,
  `hourlyClassRate` double NOT NULL,
  `empId` int(11) NOT NULL,
  PRIMARY KEY (`hourlyClassId`),
  UNIQUE KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mailmethod`
--

CREATE TABLE IF NOT EXISTS `mailmethod` (
  `mailPayId` int(11) NOT NULL AUTO_INCREMENT,
  `mailPayAddress` varchar(50) NOT NULL,
  `empId` int(11) NOT NULL,
  PRIMARY KEY (`mailPayId`),
  UNIQUE KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `paycheck`
--

CREATE TABLE IF NOT EXISTS `paycheck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empId` int(11) NOT NULL,
  `payDate` varchar(40) NOT NULL,
  `grossPay` double NOT NULL,
  `netPay` double NOT NULL,
  `deductions` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `salaryclass`
--

CREATE TABLE IF NOT EXISTS `salaryclass` (
  `salClassId` int(11) NOT NULL AUTO_INCREMENT,
  `salClassSalary` double NOT NULL,
  `empId` int(11) NOT NULL,
  PRIMARY KEY (`salClassId`),
  UNIQUE KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `salesrecipt`
--

CREATE TABLE IF NOT EXISTS `salesrecipt` (
  `salesReciptId` int(11) NOT NULL AUTO_INCREMENT,
  `salesReciptAmount` double NOT NULL,
  `salesReciptDate` varchar(40) NOT NULL,
  `empId` int(11) NOT NULL,
  `paid` varchar(1) NOT NULL DEFAULT 'F',
  PRIMARY KEY (`salesReciptId`),
  KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `timesheet`
--

CREATE TABLE IF NOT EXISTS `timesheet` (
  `timeSheetId` int(11) NOT NULL AUTO_INCREMENT,
  `timeSheetDate` varchar(40) NOT NULL,
  `timeSheetHours` int(11) NOT NULL,
  `empId` int(11) NOT NULL,
  `paid` varchar(1) NOT NULL DEFAULT 'F',
  PRIMARY KEY (`timeSheetId`),
  KEY `empId` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `affiliation`
--
ALTER TABLE `affiliation`
  ADD CONSTRAINT `affiliation_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `commissionclass`
--
ALTER TABLE `commissionclass`
  ADD CONSTRAINT `commissionclass_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `directmethod`
--
ALTER TABLE `directmethod`
  ADD CONSTRAINT `directmethod_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `holdmethod`
--
ALTER TABLE `holdmethod`
  ADD CONSTRAINT `holdmethod_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hourlyclass`
--
ALTER TABLE `hourlyclass`
  ADD CONSTRAINT `hourlyclass_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mailmethod`
--
ALTER TABLE `mailmethod`
  ADD CONSTRAINT `mailmethod_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `paycheck`
--
ALTER TABLE `paycheck`
  ADD CONSTRAINT `paycheck_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salaryclass`
--
ALTER TABLE `salaryclass`
  ADD CONSTRAINT `salaryclass_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salesrecipt`
--
ALTER TABLE `salesrecipt`
  ADD CONSTRAINT `salesrecipt_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `timesheet`
--
ALTER TABLE `timesheet`
  ADD CONSTRAINT `timesheet_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
