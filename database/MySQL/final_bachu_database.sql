-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 08, 2024 at 05:44 PM
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
(4, 'user3', 'user3@example.com', 'user3password', 1, NULL, NULL, 2),
(5, 'user4', 'user4@example.com', 'user4password', 1, NULL, NULL, 2);

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
(5, 2, 5, '2024-05-03');

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
(1, 'Album One', '2023-01-01', 'https://example.com/album/album1.jpg', 2),
(2, 'Album Two', '2023-02-01', 'https://example.com/album/album2.jpg', 3),
(3, 'Album Three', '2023-03-01', 'https://example.com/album/album3.jpg', 4),
(4, 'Album Four', '2023-04-01', 'https://example.com/album/album4.jpg', 5),
(5, 'Album Five', '2023-05-01', 'https://example.com/album/album5.jpg', 2);

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
(2, 'Author Two');

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
(5, 'Country'),
(4, 'Hip Hop'),
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
(1, 'Singer One', 'https://example.com/avatar/singer1.jpg', 'Description of Singer One'),
(2, 'Singer Two', 'https://example.com/avatar/singer2.jpg', 'Description of Singer Two'),
(3, 'Singer Three', 'https://example.com/avatar/singer3.jpg', 'Description of Singer Three'),
(4, 'Singer Four', 'https://example.com/avatar/singer4.jpg', 'Description of Singer Four'),
(5, 'Singer Five', 'https://example.com/avatar/singer5.jpg', 'Description of Singer Five');

-- --------------------------------------------------------

--
-- Table structure for table `song`
--

CREATE TABLE `song` (
  `song_id` int(11) NOT NULL,
  `song_detail_id` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `singer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `song`
--

INSERT INTO `song` (`song_id`, `song_detail_id`, `author_id`, `album_id`, `singer_id`) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 1, 2),
(3, 3, 3, 1, 3),
(4, 4, 4, 1, 4),
(5, 5, 5, 1, 5);

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
  `song_cover_url` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `songdetail`
--

INSERT INTO `songdetail` (`song_detail_id`, `title`, `lyric`, `song_time`, `release_date`, `file_url`, `listen_count`, `status`, `song_cover_url`, `category_id`) VALUES
(1, 'Song One', 'Lyrics of Song One', '00:04:30', '2023-01-01', 'https://example.com/song/song1.mp3', 100, 1, 'https://example.com/songcover/song1.jpg', 1),
(2, 'Song Two', 'Lyrics of Song Two', '00:03:45', '2023-01-01', 'https://example.com/song/song2.mp3', 150, 1, 'https://example.com/songcover/song2.jpg', 2),
(3, 'Song Three', 'Lyrics of Song Three', '00:04:15', '2023-01-01', 'https://example.com/song/song3.mp3', 200, 1, 'https://example.com/songcover/song3.jpg', 3),
(4, 'Song Four', 'Lyrics of Song Four', '00:03:50', '2023-01-01', 'https://example.com/song/song4.mp3', 120, 1, 'https://example.com/songcover/song4.jpg', 4),
(5, 'Song Five', 'Lyrics of Song Five', '00:05:00', '2023-01-01', 'https://example.com/song/song5.mp3', 180, 1, 'https://example.com/songcover/song5.jpg', 5);

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
(1, 1, 'Admin User', 'https://example.com/avatar/admin.jpg', '123 Admin Street, Admin City', '+123456789'),
(2, 2, 'User One', 'https://example.com/avatar/user1.jpg', '123 User1 Street, User1 City', '+123456789'),
(3, 3, 'User Two', 'https://example.com/avatar/user2.jpg', '123 User2 Street, User2 City', '+123456789'),
(4, 4, 'User Three', 'https://example.com/avatar/user3.jpg', '123 User3 Street, User3 City', '+123456789'),
(5, 5, 'User Four', 'https://example.com/avatar/user4.jpg', '123 User4 Street, User4 City', '+123456789');

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
  ADD KEY `FK_song_singer` (`singer_id`);

--
-- Indexes for table `songdetail`
--
ALTER TABLE `songdetail`
  ADD PRIMARY KEY (`song_detail_id`),
  ADD KEY `PK_SongDetail_Category` (`category_id`);

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
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `accountmembership`
--
ALTER TABLE `accountmembership`
  MODIFY `account_membership_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `account_song`
--
ALTER TABLE `account_song`
  MODIFY `account_song_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `album`
--
ALTER TABLE `album`
  MODIFY `album_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `author_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
  MODIFY `singer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `song`
--
ALTER TABLE `song`
  MODIFY `song_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `songdetail`
--
ALTER TABLE `songdetail`
  MODIFY `song_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `userprofile`
--
ALTER TABLE `userprofile`
  MODIFY `profile_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
  ADD CONSTRAINT `FK_song_singer` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`singer_id`),
  ADD CONSTRAINT `song_ibfk_1` FOREIGN KEY (`song_detail_id`) REFERENCES `songdetail` (`song_detail_id`),
  ADD CONSTRAINT `song_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  ADD CONSTRAINT `song_ibfk_3` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`);

--
-- Constraints for table `songdetail`
--
ALTER TABLE `songdetail`
  ADD CONSTRAINT `PK_SongDetail_Category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  ADD CONSTRAINT `transactionhistory_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
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
