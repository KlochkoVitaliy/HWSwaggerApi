-- liquibase formatted sql

-- changeset vKlochko:1
CREATE INDEX student_name_index ON student (name);

--changeset vKlochko:2
CREATE INDEX faculty_nc_index ON faculty (name, color);