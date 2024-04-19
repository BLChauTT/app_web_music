-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2024 at 12:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `app_web_musics_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `displayName`, `email`, `password`, `gender`, `dob`, `region`, `status`) VALUES
(1, 'Tan Chau', 'abc@gmail.com', '123', 'Male', '2023-01-01', 'VietNam', 1),
(2, 'Duc Toan', 'abc1@gmail.com', '123', 'Male', '2023-01-02', 'VietNam', 1),
(3, 'Huy Pham', 'abc2@gmail.com', '123', 'Male', '2023-01-03', 'VietNam', 1),
(4, 'Tran Duong', 'abc3@gmail.com', '123', 'Male', '2023-01-04', 'VietNam', 1);

-- --------------------------------------------------------

--
-- Table structure for table `accountrole`
--

CREATE TABLE `accountrole` (
  `id` int(11) NOT NULL,
  `accountId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accountrole`
--

INSERT INTO `accountrole` (`id`, `accountId`, `roleId`) VALUES
(1, 1, 3),
(2, 2, 3),
(3, 3, 3),
(4, 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL DEFAULT 'no_image.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `name`, `image`) VALUES
(1, 'Author 1', 'no_image.jpg'),
(2, 'Author 2', 'no_image.jpg'),
(3, 'Author 3', 'no_image.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `lovelist`
--

CREATE TABLE `lovelist` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `musicId` int(11) NOT NULL,
  `accountId` int(11) NOT NULL,
  `image` varchar(255) NOT NULL DEFAULT 'no_image.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lovelist`
--

INSERT INTO `lovelist` (`id`, `name`, `musicId`, `accountId`, `image`) VALUES
(1, 'Love List 1', 1, 1, 'no_image.jpg'),
(2, 'Love List 1', 2, 1, 'no_image.jpg'),
(3, 'Love List 1', 3, 1, 'no_image.jpg'),
(4, 'Love List 2', 1, 2, 'no_image.jpg'),
(5, 'Love List 2', 2, 2, 'no_image.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `music`
--

CREATE TABLE `music` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `authorId` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `album` varchar(255) NOT NULL,
  `postingDate` date NOT NULL DEFAULT current_timestamp(),
  `modifyDate` date DEFAULT NULL,
  `time` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `music`
--

INSERT INTO `music` (`id`, `name`, `authorId`, `image`, `file`, `album`, `postingDate`, `modifyDate`, `time`) VALUES
(1, 'Music 1', 1, 'abc.jpg', 'music1.mp3', 'Album 1', '2024-03-20', NULL, '2:30'),
(2, 'Music 2', 2, 'abc.jpg', 'music1.mp3', 'Album 2', '2024-03-20', NULL, '2:45'),
(3, 'Music 3', 3, 'abc.jpg', 'music1.mp3', 'Album 1', '2024-03-20', NULL, '2:33');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(2, 'admin'),
(1, 'normal'),
(3, 'sa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `accountrole`
--
ALTER TABLE `accountrole`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lovelist`
--
ALTER TABLE `lovelist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `music`
--
ALTER TABLE `music`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `accountrole`
--
ALTER TABLE `accountrole`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `lovelist`
--
ALTER TABLE `lovelist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `music`
--
ALTER TABLE `music`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

-- Bổ sung table Album
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `releaseDate` date NOT NULL,
  PRIMARY KEY (`id`)
);

-- Bổ sung cột Status cho table Music
ALTER TABLE `music`
  ADD COLUMN `status` tinyint(1) NOT NULL DEFAULT 1;

-- Thêm table Rating
CREATE TABLE `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `musicId` int(11) NOT NULL,
  `accountId` int(11) NOT NULL,
  `ratingValue` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_rating_music` FOREIGN KEY (`musicId`) REFERENCES `music` (`id`),
  CONSTRAINT `fk_rating_account` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`)
);

-- Thêm table Comment
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `musicId` int(11) NOT NULL,
  `accountId` int(11) NOT NULL,
  `content` text NOT NULL,
  `commentDate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comment_music` FOREIGN KEY (`musicId`) REFERENCES `music` (`id`),
  CONSTRAINT `fk_comment_account` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`)
);


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
