CREATE DATABASE dream_journal;

USE dream_journal;

CREATE TABLE dreams (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    theme VARCHAR(50),
    date DATE
);
