CREATE DATABASE IF NOT EXISTS music_app;

USE music_app;

CREATE TABLE IF NOT EXISTS Role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
);

CREATE TABLE IF NOT EXISTS UserProfile (
    profile_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT UNIQUE,
    full_name VARCHAR(100),
    avatar_url VARCHAR(255),
    address TEXT,
    phone_number VARCHAR(20),
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE IF NOT EXISTS Song (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    artist VARCHAR(255),
    genre VARCHAR(50),
    release_date DATE,
    file_url VARCHAR(255),
    price DECIMAL(10,2),
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS TransactionHistory (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    song_id INT,
    transaction_date DATETIME,
    amount DECIMAL(10,2),
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);

CREATE TABLE IF NOT EXISTS Comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    song_id INT,
    comment_text TEXT,
    comment_date DATETIME,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);

CREATE TABLE IF NOT EXISTS Rating (
    rating_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    song_id INT,
    rating INT,
    rating_date DATETIME,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);

CREATE TABLE IF NOT EXISTS Favorite (
    favorite_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    song_id INT,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);

CREATE TABLE IF NOT EXISTS Notification (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    message TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE IF NOT EXISTS Category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS SongCategory (
    song_id INT,
    category_id INT,
    PRIMARY KEY (song_id, category_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id),
    FOREIGN KEY (category_id) REFERENCES Category(category_id)
);

CREATE TABLE IF NOT EXISTS Payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT,
    payment_date DATETIME,
    amount DECIMAL(10,2),
    payment_method VARCHAR(50),
    FOREIGN KEY (transaction_id) REFERENCES TransactionHistory(transaction_id)
);

CREATE TABLE IF NOT EXISTS AccountVerification (
    verification_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    verification_code VARCHAR(100),
    is_verified BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);
