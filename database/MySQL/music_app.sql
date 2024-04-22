-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS music_app;
USE music_app;

-- Tạo bảng Role
CREATE TABLE Role (
    role_id INT PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

-- Tạo bảng Account
CREATE TABLE Account (
    account_id INT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
);

-- Tạo bảng UserProfile
CREATE TABLE UserProfile (
    profile_id INT PRIMARY KEY,
    account_id INT UNIQUE,
    full_name VARCHAR(100),
    avatar_url VARCHAR(255),
    address TEXT,
    phone_number VARCHAR(20),
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

-- Tạo bảng Author
CREATE TABLE Author (
    author_id INT PRIMARY KEY,
    author_name VARCHAR(255) UNIQUE NOT NULL
);

-- Tạo bảng Singer
CREATE TABLE Singer (
    singer_id INT PRIMARY KEY,
    singer_name VARCHAR(255) UNIQUE NOT NULL
);

-- Tạo bảng Category
CREATE TABLE Category (
    category_id INT PRIMARY KEY,
    category_name VARCHAR(100) UNIQUE NOT NULL
);

-- Tạo bảng MembershipPackage
CREATE TABLE MembershipPackage (
    package_id INT PRIMARY KEY,
    package_name VARCHAR(100) UNIQUE NOT NULL,
    duration INT, -- Đơn vị là tháng
    price DECIMAL(10,2)
);

-- Tạo bảng SongDetail
CREATE TABLE SongDetail (
    song_detail_id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(50),
    release_date DATE,
    file_url VARCHAR(255),
    listen_count INT DEFAULT 0, -- Số lượt nghe của bài hát
    status INT DEFAULT 1
);

-- Tạo bảng Song
CREATE TABLE Song (
    song_id INT PRIMARY KEY,
    account_id INT, -- Khóa ngoại liên kết với bảng Account_Song
    song_detail_id INT, -- Khóa ngoại liên kết với bảng SongDetail
    author_id INT, -- Khóa ngoại liên kết với bảng Author
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_detail_id) REFERENCES SongDetail(song_detail_id),
    FOREIGN KEY (author_id) REFERENCES Author(author_id)
);

-- Tạo bảng Account_Song
CREATE TABLE Account_Song (
    account_song_id INT PRIMARY KEY,
    account_id INT,
    song_id INT,
    post_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);

-- Tạo bảng Rating
CREATE TABLE Rating (
    rating_id INT PRIMARY KEY,
    account_id INT,
    song_id INT,
    rating INT,
    rating_date DATETIME,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);

-- Tạo bảng Comment
CREATE TABLE Comment (
    comment_id INT PRIMARY KEY,
    account_id INT,
    song_id INT,
    comment_text TEXT,
    comment_date DATETIME,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);

-- Tạo bảng Favorite
CREATE TABLE Favorite (
    favorite_id INT PRIMARY KEY,
    account_id INT,
    song_id INT,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);

-- Tạo bảng Song_Singer
CREATE TABLE Song_Singer (
    song_id INT,
    singer_id INT,
    PRIMARY KEY (song_id, singer_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id),
    FOREIGN KEY (singer_id) REFERENCES Singer(singer_id)
);

-- Tạo bảng SongCategory
CREATE TABLE SongCategory (
    song_id INT,
    category_id INT,
    PRIMARY KEY (song_id, category_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id),
    FOREIGN KEY (category_id) REFERENCES Category(category_id)
);

-- Tạo bảng TransactionHistory
CREATE TABLE TransactionHistory (
    transaction_id INT PRIMARY KEY,
    account_id INT,
    package_id INT,
    transaction_date DATE,
    amount DECIMAL(10,2),
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (package_id) REFERENCES MembershipPackage(package_id)
);

-- Tạo bảng Payment
CREATE TABLE Payment (
    payment_id INT PRIMARY KEY,
    transaction_id INT,
    payment_date DATE,
    amount DECIMAL(10,2),
    payment_method VARCHAR(100),
    FOREIGN KEY (transaction_id) REFERENCES TransactionHistory(transaction_id)
);

-- Tạo bảng AccountMembership
CREATE TABLE AccountMembership (
    account_membership_id INT PRIMARY KEY,
    account_id INT,
    package_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (package_id) REFERENCES MembershipPackage(package_id)
);

-- Tạo bảng AccountVerification
CREATE TABLE AccountVerification (
    verification_id INT PRIMARY KEY,
    account_id INT,
    verification_code VARCHAR(255),
    is_verified INT,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

-- Tạo bảng Notification
CREATE TABLE Notification (
    notification_id INT PRIMARY KEY,
    account_id INT,
    message TEXT,
    created_at DATE,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

-- Dữ liệu mẫu cho bảng Role
INSERT INTO Role (role_id, role_name)
VALUES (1, 'Admin'),
       (2, 'User'),
       (3, 'Artist');

-- Dữ liệu mẫu cho bảng Account
INSERT INTO Account (account_id, username, email, password_hash, role_id)
VALUES (1, 'admin', 'admin@example.com', 'hashed_password', 1),
       (2, 'user1', 'user1@example.com', 'hashed_password', 2),
       (3, 'user2', 'user2@example.com', 'hashed_password', 2);

-- Dữ liệu mẫu cho bảng UserProfile
INSERT INTO UserProfile (profile_id, account_id, full_name, avatar_url, address, phone_number)
VALUES (1, 1, 'Admin', 'avatar_admin.jpg', '123 Admin Street, Admin City', '123456789'),
       (2, 2, 'User 1', 'avatar_user1.jpg', '456 User Street, User City', '987654321'),
       (3, 3, 'User 2', 'avatar_user2.jpg', '789 User Street, User City', '456123789');

-- Dữ liệu mẫu cho bảng Author
INSERT INTO Author (author_id, author_name)
VALUES (1, 'Author 1'),
       (2, 'Author 2'),
       (3, 'Author 3');

-- Dữ liệu mẫu cho bảng Singer
INSERT INTO Singer (singer_id, singer_name)
VALUES (1, 'Singer 1'),
       (2, 'Singer 2'),
       (3, 'Singer 3');

-- Dữ liệu mẫu cho bảng Category
INSERT INTO Category (category_id, category_name)
VALUES (1, 'Pop'),
       (2, 'Rock'),
       (3, 'Hip Hop');

-- Dữ liệu mẫu cho bảng MembershipPackage
INSERT INTO MembershipPackage (package_id, package_name, duration, price)
VALUES (1, '1 Month', 1, 120.00),
       (2, '6 Months', 6, 300.00),
       (3, '1 Year', 12, 1000.00);

-- Dữ liệu mẫu cho bảng SongDetail
INSERT INTO SongDetail (song_detail_id, title, genre, release_date, file_url, listen_count, status)
VALUES (1, 'Song 1', 'Pop', '2022-01-01', 'song1.mp3', 1000, 1),
       (2, 'Song 2', 'Rock', '2022-02-01', 'song2.mp3', 500, 1),
       (3, 'Song 3', 'Hip Hop', '2022-03-01', 'song3.mp3', 800, 1);

-- Dữ liệu mẫu cho bảng Song
INSERT INTO Song (song_id, account_id, song_detail_id, author_id)
VALUES (1, 2, 1, 1),
       (2, 2, 2, 2),
       (3, 3, 3, 3);

-- Dữ liệu mẫu cho bảng Account_Song
INSERT INTO Account_Song (account_song_id, account_id, song_id)
VALUES (1, 2, 1),
       (2, 2, 2),
       (3, 3, 3);

-- Dữ liệu mẫu cho bảng Rating
INSERT INTO Rating (rating_id, account_id, song_id, rating, rating_date)
VALUES (1, 2, 1, 4, '2022-04-01'),
       (2, 2, 2, 5, '2022-04-02'),
       (3, 3, 3, 4, '2022-04-03');

-- Dữ liệu mẫu cho bảng Comment
INSERT INTO Comment (comment_id, account_id, song_id, comment_text, comment_date)
VALUES (1, 2, 1, 'Great song!', '2022-04-01'),
       (2, 2, 2, 'Amazing!', '2022-04-02'),
       (3, 3, 3, 'Love it!', '2022-04-03');

-- Dữ liệu mẫu cho bảng Favorite
INSERT INTO Favorite (favorite_id, account_id, song_id)
VALUES (1, 2, 1),
       (2, 2, 2),
       (3, 3, 3);

-- Dữ liệu mẫu cho bảng Song_Singer
INSERT INTO Song_Singer (song_id, singer_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

-- Dữ liệu mẫu cho bảng SongCategory
INSERT INTO SongCategory (song_id, category_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

-- Dữ liệu mẫu cho bảng TransactionHistory
INSERT INTO TransactionHistory (transaction_id, account_id, package_id, transaction_date, amount)
VALUES (1, 2, 1, '2022-04-01', 120.00),
       (2, 2, 2, '2022-04-01', 300.00),
       (3, 3, 3, '2022-04-01', 1000.00);

-- Dữ liệu mẫu cho bảng Payment
INSERT INTO Payment (payment_id, transaction_id, payment_date, amount, payment_method)
VALUES (1, 1, '2022-04-01', 120.00, 'Credit Card'),
       (2, 2, '2022-04-01', 300.00, 'PayPal'),
       (3, 3, '2022-04-01', 1000.00, 'Bank Transfer');

-- Dữ liệu mẫu cho bảng AccountMembership
INSERT INTO AccountMembership (account_membership_id, account_id, package_id, start_date, end_date)
VALUES (1, 2, 1, '2022-04-01', '2022-05-01'),
       (2, 2, 2, '2022-04-01', '2022-10-01'),
       (3, 3, 3, '2022-04-01', '2023-04-01');

-- Dữ liệu mẫu cho bảng AccountVerification
INSERT INTO AccountVerification (verification_id, account_id, verification_code, is_verified)
VALUES (1, 2, 'verification_code_1', 1),
       (2, 3, 'verification_code_2', 1);

-- Dữ liệu mẫu cho bảng Notification
INSERT INTO Notification (notification_id, account_id, message, created_at)
VALUES (1, 2, 'New notification for user 2', '2022-04-01'),
       (2, 3, 'New notification for user 3', '2022-04-01');
