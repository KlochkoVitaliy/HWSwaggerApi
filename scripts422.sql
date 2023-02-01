CREATE TABLE humans
(
    id      INTEGER PRIMARY KEY,
    name    TEXT,
    age     INTEGER,
    license BOOLEAN,
    cars_id INTEGER REFERENCES cars (id)
);
CREATE TABLE cars
(
    id    INTEGER PRIMARY KEY,
    brand TEXT,
    model VARCHAR(15),
    cost  MONEY
);

