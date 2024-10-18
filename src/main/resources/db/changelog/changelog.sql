--liquibase formatted sql

--changeset apejkovic:1
CREATE TABLE candidates (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    jmbg VARCHAR(13) NOT NULL UNIQUE,
    birth_year INT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(15),
    note TEXT,
    hired BOOLEAN DEFAULT FALSE,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset apejkovic:2
INSERT INTO candidates (first_name, last_name, jmbg, birth_year, email, phone, note, hired, last_modified)
VALUES
('John', 'Doe', '1234567890123', 1990, 'john.doe@example.com', '0641234567', 'Java developer with 5 years experience', FALSE, '2024-10-01'),
('Jane', 'Smith', '2234567890123', 1985, 'jane.smith@example.com', '0642234567', 'Senior software engineer', TRUE, '2024-09-29'),
('Emily', 'Brown', '3234567890123', 1992, 'emily.brown@example.com', '0643234567', 'Recently graduated, looking for opportunities', FALSE, '2024-10-05'),
('Michael', 'Johnson', '4234567890123', 1988, 'michael.johnson@example.com', '0644234567', 'Worked on various projects using Java and Spring', TRUE, '2024-10-10'),
('Olivia', 'Davis', '5234567890123', 1993, 'olivia.davis@example.com', '0645234567', 'Junior developer with a strong Java background', FALSE, '2024-10-12'),
('David', 'Wilson', '6234567890123', 1990, 'david.wilson@example.com', '0646234567', 'Looking for mid-level Java developer position', TRUE, '2024-10-08'),
('Chris', 'Taylor', '7234567890123', 1987, 'chris.taylor@example.com', '0647234567', 'Experienced in Java EE and microservices', TRUE, '2024-10-11'),
('Sophia', 'Anderson', '8234567890123', 1991, 'sophia.anderson@example.com', '0648234567', 'Strong experience with Java and cloud platforms', FALSE, '2024-10-03'),
('Liam', 'Thomas', '9234567890123', 1989, 'liam.thomas@example.com', '0649234567', 'Interested in full-stack development', TRUE, '2024-10-07'),
('Ava', 'Jackson', '1034567890123', 1995, 'ava.jackson@example.com', '0641034567', 'Passionate about software development', FALSE, '2024-10-06'),
('Ethan', 'White', '1134567890123', 1984, 'ethan.white@example.com', '0641134567', '10+ years experience in software engineering', TRUE, '2024-10-04'),
('Mia', 'Harris', '1234567891123', 1996, 'mia.harris@example.com', '0641234568', 'Looking for junior Java developer roles', FALSE, '2024-10-13'),
('Noah', 'Martin', '1334567890123', 1986, 'noah.martin@example.com', '0641334567', 'Mid-level developer, experience with Java Spring', TRUE, '2024-09-28'),
('Ella', 'Clark', '1434567890123', 1994, 'ella.clark@example.com', '0641434567', 'Experience in backend development', FALSE, '2024-10-14'),
('James', 'Lewis', '1534567890123', 1991, 'james.lewis@example.com', '0641534567', 'Currently working as a freelance Java developer', TRUE, '2024-10-02');
