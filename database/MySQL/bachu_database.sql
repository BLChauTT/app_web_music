-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 16, 2024 at 07:06 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bachu_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `token` varchar(255) DEFAULT NULL,
  `security_code` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_id`, `username`, `email`, `password`, `status`, `token`, `security_code`, `role_id`) VALUES
(1, 'admin', 'admin@example.com', 'adminpassword', 1, NULL, NULL, 1),
(2, 'user1', 'user1@example.com', 'user1password', 1, NULL, NULL, 2),
(3, 'user2', 'user2@example.com', 'user2password', 1, NULL, NULL, 2),
(4, 'user3', 'user3@example.com', '123', 1, NULL, NULL, 2),
(5, 'user4', 'user4@example.com', 'user4password', 1, NULL, NULL, 2),
(6, 'Toan Nguyen', 'zz09ndt02zz@gmail.com', '123456789', 1, NULL, 'f8224eb8687c4471bfac7e9b1847dc68', 2);

-- --------------------------------------------------------

--
-- Table structure for table `accountmembership`
--

CREATE TABLE `accountmembership` (
  `account_membership_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `package_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `account_song`
--

CREATE TABLE `account_song` (
  `account_song_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `song_id` int(11) DEFAULT NULL,
  `post_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account_song`
--

INSERT INTO `account_song` (`account_song_id`, `account_id`, `song_id`, `post_date`) VALUES
(1, 2, 1, '2024-05-03'),
(2, 3, 2, '2024-05-03'),
(3, 4, 3, '2024-05-03'),
(4, 5, 4, '2024-05-03'),
(5, 2, 5, '2024-05-03'),
(9, 1, 22, '2024-11-05'),
(10, 1, 23, '2024-11-05'),
(11, 4, 24, '2024-11-05'),
(12, 4, 25, '2025-01-05'),
(13, 4, 27, '2024-05-15');

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE `album` (
  `album_id` int(11) NOT NULL,
  `album_name` varchar(255) NOT NULL,
  `release_date` date DEFAULT NULL,
  `album_cover_url` varchar(255) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`album_id`, `album_name`, `release_date`, `album_cover_url`, `account_id`) VALUES
(1, 'Love', '2023-01-01', 'c1.jpg', 2),
(2, 'Sad', '2023-02-01', 'c2.jpg', 3),
(3, 'Enjoy', '2023-03-01', 'c3.jpg', 4),
(4, 'You', '2023-04-01', 'c4.jpg', 5),
(5, 'The Rain', '2023-05-01', 'c5.jpg', 2),
(6, 'Album Charlie Puth', '2024-05-15', '24131d1c701749db8caf612a92158a22.jpg', 4);

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `author_id` int(11) NOT NULL,
  `author_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`author_id`, `author_name`) VALUES
(5, 'Author Five'),
(4, 'Author Four'),
(1, 'Author One'),
(3, 'Author Three'),
(2, 'Author Two'),
(10, 'bai so 6'),
(13, 'Bonnie Leigh McKee'),
(11, 'Nguyễn Văn Chung'),
(12, 'Sơn Tùng MTP');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_name`) VALUES
(6, 'Another'),
(7, 'Bolero'),
(5, 'Country'),
(4, 'Hip Hop'),
(8, 'Nhạc Xưa'),
(1, 'Pop'),
(3, 'R&B'),
(2, 'Rock');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `account_song_id` int(11) DEFAULT NULL,
  `comment_text` varchar(255) DEFAULT NULL,
  `comment_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `account_song_id`, `comment_text`, `comment_date`) VALUES
(1, 1, 'Great song!', '2023-01-02'),
(2, 2, 'Awesome track!', '2023-01-03'),
(3, 3, 'Love it!', '2023-01-04'),
(4, 4, 'Amazing!', '2023-01-05'),
(5, 5, 'Nice work!', '2023-01-06');

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE `favorite` (
  `favorite_id` int(11) NOT NULL,
  `account_song_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`favorite_id`, `account_song_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `membershippackage`
--

CREATE TABLE `membershippackage` (
  `package_id` int(11) NOT NULL,
  `package_name` varchar(100) NOT NULL,
  `duration` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `membershippackage`
--

INSERT INTO `membershippackage` (`package_id`, `package_name`, `duration`, `price`) VALUES
(1, 'Basic', 30, 9.99),
(2, 'Standard', 90, 19.99),
(3, 'Premium', 180, 29.99),
(4, 'VIP', 365, 49.99),
(5, 'Trial', 7, 0);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `created_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`notification_id`, `account_id`, `message`, `created_at`) VALUES
(1, 2, 'New notification for user 2', '2022-04-01'),
(2, 3, 'New notification for user 3', '2022-04-01');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `payment_method` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL,
  `account_song_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `rating_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`rating_id`, `account_song_id`, `rating`, `rating_date`) VALUES
(1, 1, 4, '2023-01-02'),
(2, 2, 5, '2023-01-03'),
(3, 3, 4, '2023-01-04'),
(4, 4, 5, '2023-01-05'),
(5, 5, 4, '2023-01-06');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'Admin'),
(2, 'User');

-- --------------------------------------------------------

--
-- Table structure for table `singer`
--

CREATE TABLE `singer` (
  `singer_id` int(11) NOT NULL,
  `singer_name` varchar(255) NOT NULL,
  `singer_avatar_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `singer`
--

INSERT INTO `singer` (`singer_id`, `singer_name`, `singer_avatar_url`, `description`) VALUES
(1, 'Charlie Puth', 'charlie.jpg', 'Charlie Puth is a Singer'),
(2, 'Son Tung MTP', 'sontung.jpg', 'Song Tung is a singer'),
(3, 'Katy Perry', 'katy.jpg', 'Katy Perry is a singer'),
(4, 'Taylor Swift', 'taylor.jpg', 'Taylor is a singer'),
(5, 'Ed Sheeran', 'ed.jpg', 'Ed Sheeran is a Singer ...'),
(6, 'My Tam', 'mytam.jpg', 'My Tam is a singer ...'),
(7, 'Justin Bierber', 'bcb4bcba78c4498e8f5594dce56a96fc.jpg', 'Justin Drew Bieber là một nam ca sĩ kiêm sáng tác nhạc người Canada.');

-- --------------------------------------------------------

--
-- Table structure for table `song`
--

CREATE TABLE `song` (
  `song_id` int(11) NOT NULL,
  `song_detail_id` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `song`
--

INSERT INTO `song` (`song_id`, `song_detail_id`, `author_id`, `album_id`, `category_id`) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 1, 2),
(3, 3, 3, 1, 3),
(4, 4, 4, 1, 4),
(5, 5, 5, 1, 5),
(10, 13, 10, NULL, 4),
(11, 14, 2, NULL, 5),
(12, 15, 2, NULL, 5),
(13, 16, 2, NULL, 4),
(14, 17, 1, NULL, 5),
(15, 18, 4, NULL, 5),
(16, 19, 2, NULL, 5),
(17, 20, 2, 3, 5),
(18, 22, 10, 4, 4),
(19, 23, 2, 3, 4),
(20, 24, 1, 3, 4),
(21, 25, 2, 3, 5),
(22, 26, 2, 3, 4),
(23, 27, 2, 2, 5),
(24, 28, 11, 2, 4),
(25, 29, 12, 1, 5),
(26, 30, 13, 3, 1),
(27, 31, 12, 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `songdetail`
--

CREATE TABLE `songdetail` (
  `song_detail_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `lyric` varchar(255) DEFAULT NULL,
  `song_time` varchar(255) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `listen_count` int(11) DEFAULT 0,
  `status` int(11) DEFAULT 1,
  `song_cover_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `songdetail`
--

INSERT INTO `songdetail` (`song_detail_id`, `title`, `lyric`, `song_time`, `release_date`, `file_url`, `listen_count`, `status`, `song_cover_url`) VALUES
(1, 'See You Again', 'See You Again ...', '03:49', '2023-01-01', 'seeyouagain.mp3', 100, 1, 'c15.jpg'),
(2, 'Attention', 'Attention', '05:23', '2023-01-01', 'attention.mp3', 150, 1, 'c11.jpg'),
(3, 'Low', 'Lyrics of Song Three', '04:15', '2023-01-01', 'low.mp3', 200, 1, 'c3.jpg'),
(4, 'Liar', 'Lyrics of Song Four', '03:50', '2023-01-01', 'liar.mp3', 120, 1, 'c4.jpg'),
(5, 'Perfect', 'Lyrics of Song Five', '05:00', '2023-01-01', 'attention.mp3', 180, 1, 'c5.jpg'),
(7, 'Shap Of You', 'Shap Of You ...', '06:23', '2024-09-05', 'shape.mp3', 1, 1, 'c6.jpg'),
(13, 'Let\'s me Lowly', 'Let\'s me Lowly', '05:04', '2024-10-05', 'slowly.mp3', 1, 1, 'c7.jpg'),
(14, 'Alone', 'Alone ...', '05:04', '2024-10-05', 'Alone.mp3', 1, 1, 'c8.jpg'),
(15, 'Wolves', 'Wolves', '05:04', '2024-10-05', 'wolves.mp3', 1, 1, 'c9.jpg'),
(16, 'Believer', 'Believer', '05:04', '2024-10-05', 'believer.mp3', 1, 1, 'c10.jpg'),
(17, 'Faded', 'Faded', '05:04', '2024-10-05', 'faded.mp3', 1, 1, 'c11.jpg'),
(18, 'Dreams', 'Dreams ...', '05:04', '2024-10-05', 'dreams.mp3', 1, 1, 'c12.jpg'),
(19, 'Working', 'Working', '05:04', '2024-10-05', 'wdtam.mp3', 1, 1, 'c13.jpg'),
(20, 'I Can Do It With a Broken Heart', 'I Can Do It With a Broken Heart ...', '05:04', '2024-10-05', 'broken.mp3', 1, 1, 'c14.jpg'),
(21, 'Blank Space', 'Blank Space ..', '05:04', '2024-11-05', 'blank.mp3', 1, 1, 'c15.jpg'),
(22, 'Shiver', 'Shiver ...', '05:04', '2024-11-05', 'shiver.mp3', 1, 1, 'c16.jpg'),
(23, 'Hot N Cold', 'Hot N Cold ...', '05:04', '2024-11-05', 'hot.mp3', 1, 1, 'c17.jpg'),
(24, 'FireWork', 'FireWork ...', '05:04', '2024-11-05', 'fire.mp3', 1, 1, 'c18.jpg'),
(25, 'Wide Awake', 'Wide Awake ...', '05:04', '2024-11-05', 'wide.mp3', 1, 1, 'c19.jpg'),
(26, 'Diary', 'Nhật Ký Của Mẹ ...', '05:04', '2024-11-05', 'nhatkycuame.mp3', 1, 1, 'c20.jpg'),
(27, 'Later', 'Muộn Rồi Sao Mà Con', '05:04', '2024-11-05', 'muonroisaomacon.mp3', 1, 1, 'a3.jpg'),
(28, 'I\'m here', 'I\'m here ...', '08:15', '2024-11-05', 'noinaycoanh.mp3', 1, 1, 'a4.jpg'),
(29, 'Now', 'Now ...', '06:27', '2025-01-05', 'chungtacuahientai.mp3', 1, 1, 'a2.jpg'),
(30, 'Roar', 'Roar ..', '03:45', '2024-05-14', 'roar.mp3', 1, 1, 'roar.jpg'),
(31, 'Follow You', 'Follow You', '05:26', '2024-05-15', '3a33bbe6fefe422fbe9b0bd8932da66c.mp3', 1, 1, 'feca0e4327d6475fafc15df772d3e4c2.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `song_singer`
--

CREATE TABLE `song_singer` (
  `song_id` int(11) NOT NULL,
  `singer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `song_singer`
--

INSERT INTO `song_singer` (`song_id`, `singer_id`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 4),
(5, 5),
(18, 1),
(19, 3),
(20, 3),
(21, 3),
(22, 3),
(23, 1),
(24, 2),
(25, 1),
(26, 3),
(27, 2);

-- --------------------------------------------------------

--
-- Table structure for table `transactionhistory`
--

CREATE TABLE `transactionhistory` (
  `transaction_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `package_id` int(11) DEFAULT NULL,
  `transaction_date` date DEFAULT NULL,
  `amount` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userprofile`
--

CREATE TABLE `userprofile` (
  `profile_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userprofile`
--

INSERT INTO `userprofile` (`profile_id`, `account_id`, `full_name`, `avatar_url`, `address`, `phone_number`) VALUES
(1, 1, 'Admin User', 'a0.jpg', '123 Admin Street, Admin City', '+123456789'),
(2, 2, 'User One', 'a4.jpg', '123 User1 Street, User1 City', '+123456789'),
(3, 3, 'User Two', 'a19.jpg', '123 User2 Street, User2 City', '+123456789'),
(4, 4, 'Toan Nguyen', 'abdd1580e2204d8cbc7ef935fe075db4.jpg', 'Ho Chi Minh', '0906420345'),
(5, 5, 'User Four', 'a18.jpg', '123 User4 Street, User4 City', '+123456789'),
(6, 6, 'Toan Nguyen ', 'noimage.jpg', 'HCM', '0906420348');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `role_id` (`role_id`);

--
-- Indexes for table `accountmembership`
--
ALTER TABLE `accountmembership`
  ADD PRIMARY KEY (`account_membership_id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `package_id` (`package_id`);

--
-- Indexes for table `account_song`
--
ALTER TABLE `account_song`
  ADD PRIMARY KEY (`account_song_id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `song_id` (`song_id`);

--
-- Indexes for table `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`album_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`author_id`),
  ADD UNIQUE KEY `author_name` (`author_name`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `category_name` (`category_name`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `account_song_id` (`account_song_id`);

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`favorite_id`),
  ADD KEY `account_song_id` (`account_song_id`);

--
-- Indexes for table `membershippackage`
--
ALTER TABLE `membershippackage`
  ADD PRIMARY KEY (`package_id`),
  ADD UNIQUE KEY `package_name` (`package_name`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`notification_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `transaction_id` (`transaction_id`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`rating_id`),
  ADD KEY `account_song_id` (`account_song_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `role_name` (`role_name`);

--
-- Indexes for table `singer`
--
ALTER TABLE `singer`
  ADD PRIMARY KEY (`singer_id`),
  ADD UNIQUE KEY `singer_name` (`singer_name`);

--
-- Indexes for table `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`song_id`),
  ADD KEY `song_detail_id` (`song_detail_id`),
  ADD KEY `author_id` (`author_id`),
  ADD KEY `album_id` (`album_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `songdetail`
--
ALTER TABLE `songdetail`
  ADD PRIMARY KEY (`song_detail_id`);

--
-- Indexes for table `song_singer`
--
ALTER TABLE `song_singer`
  ADD PRIMARY KEY (`song_id`,`singer_id`),
  ADD KEY `singer_id` (`singer_id`);

--
-- Indexes for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `package_id` (`package_id`);

--
-- Indexes for table `userprofile`
--
ALTER TABLE `userprofile`
  ADD PRIMARY KEY (`profile_id`),
  ADD UNIQUE KEY `account_id` (`account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `accountmembership`
--
ALTER TABLE `accountmembership`
  MODIFY `account_membership_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `account_song`
--
ALTER TABLE `account_song`
  MODIFY `account_song_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `album`
--
ALTER TABLE `album`
  MODIFY `album_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `author_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `favorite`
--
ALTER TABLE `favorite`
  MODIFY `favorite_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `membershippackage`
--
ALTER TABLE `membershippackage`
  MODIFY `package_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `rating_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `singer`
--
ALTER TABLE `singer`
  MODIFY `singer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `song`
--
ALTER TABLE `song`
  MODIFY `song_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `songdetail`
--
ALTER TABLE `songdetail`
  MODIFY `song_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `userprofile`
--
ALTER TABLE `userprofile`
  MODIFY `profile_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

--
-- Constraints for table `accountmembership`
--
ALTER TABLE `accountmembership`
  ADD CONSTRAINT `accountmembership_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  ADD CONSTRAINT `accountmembership_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `membershippackage` (`package_id`);

--
-- Constraints for table `account_song`
--
ALTER TABLE `account_song`
  ADD CONSTRAINT `account_song_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  ADD CONSTRAINT `account_song_ibfk_2` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`);

--
-- Constraints for table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`account_song_id`) REFERENCES `account_song` (`account_song_id`);

--
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`account_song_id`) REFERENCES `account_song` (`account_song_id`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transactionhistory` (`transaction_id`);

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`account_song_id`) REFERENCES `account_song` (`account_song_id`);

--
-- Constraints for table `song`
--
ALTER TABLE `song`
  ADD CONSTRAINT `song_ibfk_1` FOREIGN KEY (`song_detail_id`) REFERENCES `songdetail` (`song_detail_id`),
  ADD CONSTRAINT `song_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  ADD CONSTRAINT `song_ibfk_3` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`),
  ADD CONSTRAINT `song_ibfk_4` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `song_singer`
--
ALTER TABLE `song_singer`
  ADD CONSTRAINT `song_singer_ibfk_1` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`),
  ADD CONSTRAINT `song_singer_ibfk_2` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`singer_id`);

--
-- Constraints for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  ADD CONSTRAINT `transactionhistory_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `membershippackage` (`package_id`);

--
-- Constraints for table `userprofile`
--
ALTER TABLE `userprofile`
  ADD CONSTRAINT `userprofile_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
