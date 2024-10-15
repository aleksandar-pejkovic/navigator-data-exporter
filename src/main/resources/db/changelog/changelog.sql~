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
    is_hired BOOLEAN DEFAULT FALSE,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset apejkovic:2
INSERT INTO candidates (first_name, last_name, jmbg, birth_year, email, phone, note, is_hired, last_modified)
VALUES
('Marko', 'Petrovic', '1234567890123', 1990, 'marko.petrovic@example.com', '0641234567', 'Java developer with 5 years experience', FALSE, '2024-10-01'),
('Jelena', 'Markovic', '2234567890123', 1985, 'jelena.markovic@example.com', '0642234567', 'Senior software engineer', TRUE, '2024-09-29'),
('Ana', 'Jovanovic', '3234567890123', 1992, 'ana.jovanovic@example.com', '0643234567', 'Recently graduated, looking for opportunities', FALSE, '2024-10-05'),
('Nikola', 'Nikolic', '4234567890123', 1988, 'nikola.nikolic@example.com', '0644234567', 'Worked on various projects using Java and Spring', TRUE, '2024-10-10'),
('Milica', 'Stojanovic', '5234567890123', 1993, 'milica.stojanovic@example.com', '0645234567', 'Junior developer with a strong Java background', FALSE, '2024-10-12'),
('Ivan', 'Milosevic', '6234567890123', 1990, 'ivan.milosevic@example.com', '0646234567', 'Looking for mid-level Java developer position', TRUE, '2024-10-08'),
('Petar', 'Ilic', '7234567890123', 1987, 'petar.ilic@example.com', '0647234567', 'Experienced in Java EE and microservices', TRUE, '2024-10-11'),
('Dragana', 'Kostic', '8234567890123', 1991, 'dragana.kostic@example.com', '0648234567', 'Strong experience with Java and cloud platforms', FALSE, '2024-10-03'),
('Nemanja', 'Vasic', '9234567890123', 1989, 'nemanja.vasic@example.com', '0649234567', 'Interested in full-stack development', TRUE, '2024-10-07'),
('Maja', 'Todorovic', '1034567890123', 1995, 'maja.todorovic@example.com', '0641034567', 'Passionate about software development', FALSE, '2024-10-06'),
('Vladimir', 'Lazic', '1134567890123', 1984, 'vladimir.lazic@example.com', '0641134567', '10+ years experience in software engineering', TRUE, '2024-10-04'),
('Sanja', 'Milenkovic', '1234567891123', 1996, 'sanja.milenkovic@example.com', '0641234568', 'Looking for junior Java developer roles', FALSE, '2024-10-13'),
('Darko', 'Zivkovic', '1334567890123', 1986, 'darko.zivkovic@example.com', '0641334567', 'Mid-level developer, experience with Java Spring', TRUE, '2024-09-28'),
('Tamara', 'Popovic', '1434567890123', 1994, 'tamara.popovic@example.com', '0641434567', 'Experience in backend development', FALSE, '2024-10-14'),
('Stefan', 'Ristic', '1534567890123', 1991, 'stefan.ristic@example.com', '0641534567', 'Currently working as a freelance Java developer', TRUE, '2024-10-02');
