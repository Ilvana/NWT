-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2016 at 09:26 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kino`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `event` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_comment_user` (`user`),
  KEY `FK_comment_event` (`event`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `content`, `time`, `event`, `user`) VALUES
(1, 'Bilo je ludo i nezaboravno.', '2016-04-19 21:19:39', 3, 6);

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `timeBegin` datetime DEFAULT NULL,
  `timeEnd` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`id`, `description`, `name`, `timeBegin`, `timeEnd`) VALUES
(1, 'wds', 'asdasd', '2016-03-02 00:00:00', '2016-03-04 00:00:00'),
(2, 'ghvg', 'jaksb', NULL, NULL),
(3, 'Loodilo, oduzimanje, mnozenje, dijeljene, rastavljanje na proste faktore.', 'Brucosijada', '2016-04-19 21:19:39', '2016-04-19 21:19:39');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE IF NOT EXISTS `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id`, `description`, `duration`, `name`) VALUES
(3, 'ksajbjab', 10, 'Ilvana'),
(4, NULL, NULL, 'Ilvana'),
(5, 'ksajbjab', 10, 'Ilvana'),
(6, 'Leonardo di Caprio. Enough said.', 120, 'Catch me if you can');

-- --------------------------------------------------------

--
-- Table structure for table `screening`
--

CREATE TABLE IF NOT EXISTS `screening` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timeBegin` datetime DEFAULT NULL,
  `timeEnd` datetime DEFAULT NULL,
  `movie` int(11) DEFAULT NULL,
  `theater` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_screening_theater` (`theater`),
  KEY `FK_screening_movie` (`movie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `screening`
--

INSERT INTO `screening` (`id`, `timeBegin`, `timeEnd`, `movie`, `theater`) VALUES
(1, '2016-04-19 21:19:39', '2016-04-19 21:19:39', 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `theater`
--

CREATE TABLE IF NOT EXISTS `theater` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sizeX` int(11) DEFAULT NULL,
  `sizeY` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `theater`
--

INSERT INTO `theater` (`id`, `name`, `sizeX`, `sizeY`) VALUES
(1, 'Cinema city', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE IF NOT EXISTS `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seatX` int(11) DEFAULT NULL,
  `seatY` int(11) DEFAULT NULL,
  `screening` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ticket_user` (`user`),
  KEY `FK_ticket_screening` (`screening`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`id`, `seatX`, `seatY`, `screening`, `user`) VALUES
(1, 2, 1, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `adminFlag` tinyint(1) DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `address`, `adminFlag`, `email`, `name`, `phone`, `username`, `password`, `enabled`, `role`) VALUES
(1, 'dsfsdfasd', 0, 'sdfs', 'ilvana', '54465', 'benjo', 'benjo', 1, 'ROLE_USER'),
(5, NULL, 0, NULL, 'Ilvana', NULL, '', '', 0, ''),
(6, 'Zmaja od Bosne bb', 0, 'cizi@live.com', 'Narcis Behlilovic', '033-225-883', 'jhbjhsd', 'hjsdbdjs', 1, 'konj');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_comment_event` FOREIGN KEY (`event`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `FK_comment_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`);

--
-- Constraints for table `screening`
--
ALTER TABLE `screening`
  ADD CONSTRAINT `FK_screening_movie` FOREIGN KEY (`movie`) REFERENCES `movie` (`id`),
  ADD CONSTRAINT `FK_screening_theater` FOREIGN KEY (`theater`) REFERENCES `theater` (`id`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK_ticket_screening` FOREIGN KEY (`screening`) REFERENCES `screening` (`id`),
  ADD CONSTRAINT `FK_ticket_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
