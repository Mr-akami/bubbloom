CREATE DATABASE IF NOT EXISTS event_service_db;

USE event_service_db;

CREATE TABLE IF NOT EXISTS event (
    id INT NULL,
    title VARCHAR(30) NOT NULL
);