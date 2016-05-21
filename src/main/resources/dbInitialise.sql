-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2016 at 11:19 PM
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=123 ;

-- --------------------------------------------------------

--
-- Table structure for table `reset_token`
--

CREATE TABLE IF NOT EXISTS `reset_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `token` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;


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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;


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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

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
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `address`, `adminFlag`, `email`, `name`, `phone`, `username`, `password`, `enabled`, `role`) VALUES
  (14, 'Tesanjska 24', 0, 'mmujic1@etf.unsa.ba', 'Muhamed Mujic', '062355108', 'mmujic', '$2a$10$n9n46VqPvbEvTBaAmeJQuO/wbf6TSHVpk3pyzzX5Iqcn/llqcwYiq', 1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `verification_token`
--

CREATE TABLE IF NOT EXISTS `verification_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `token` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

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
