CREATE TABLE sensors(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    model VARCHAR(15) NOT NULL,
    range_from INT NOT NULL,
    range_to INT NOT NULL,
    type VARCHAR(11) NOT NULL,
    unit VARCHAR(7) NOT NULL,
    location VARCHAR(40) NOT NULL,
    description VARCHAR(200) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(15) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL,
    status VARCHAR(25) NOT NULL,
    password VARCHAR(200) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE roles(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE user_roles(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

ALTER TABLE user_roles
    ADD CONSTRAINT user_role_user_fk
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_roles
    ADD CONSTRAINT user_role_role_fk
        FOREIGN KEY (role_id) REFERENCES roles (id);

INSERT INTO users(username, first_name, last_name, email, status, password) VALUES
    ('mveyhf', 'Matvey', 'Androsyuk', 'matveyhf@gmail.com', 'ACTIVE', '$2a$04$aXXwX7Wff0pvGz9JdIwIDOyHS.33y8vHbZOL6ugH.95uywxMG3LUi'),
    ('ivanhf', 'Ivan', 'Shostak', 'ivanhf@gmail.com', 'ACTIVE', '$2a$04$JT9Mm1OPId.jW2ZsAow.4OY1ylf.uFGh3buhO5jqrAq1TV1Zm9jre');

INSERT INTO roles(name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

INSERT INTO user_roles(user_id, role_id) VALUES
    (1, 1),
    (1, 2),
    (2, 1);

INSERT INTO sensors(name, model, range_from, range_to, type, unit, location, description) VALUES
    ('Sensor 1', 'PC33-56', 0, 16, 'Pressure', 'bar', 'Room1', 'This sensor has number one'),
    ('Sensor 2', 'EH-567', -25, 25, 'Voltage', 'voltage', 'Room1', 'This sensor has number two'),
    ('Sensor 3', 'TER-21', -70, 50, 'Temperature', '°С', 'Room2', 'This sensor has number three'),
    ('Sensor 4', 'H94', 0, 100, 'Humidity', '%', 'Room3', 'This sensor has number four'),
    ('Sensor 5', 'GD-23', 0, 30, 'Temperature', '°С', 'Room4', 'This sensor has number five'),
    ('Sensor 6', 'LC-11', 5, 10, 'Voltage', 'voltage', 'Room3', 'This sensor has number six'),
    ('Sensor 7', 'RFD-84', 15, 75, 'Pressure', 'bar', 'Room2', 'This sensor has number seven'),
    ('Sensor 8', 'QQ-12', 40, 80, 'Temperature', '°С', 'Room1', 'This sensor has number eight'),
    ('Sensor 9', 'LL_3', -3, 10, 'Temperature', '°С', 'Room1', 'This sensor has number nine'),
    ('Sensor 10', 'PC33-56', -5, 50, 'Voltage', 'voltage', 'Room2', 'This sensor has number ten'),
    ('Sensor 11', 'H94', 5, 10, 'Pressure', 'bar', 'Room4', 'This sensor has number eleven'),
    ('Sensor 12', 'EH-567', 0, 50, 'Humidity', '%', 'Room4', 'This sensor has number twelve'),
    ('Sensor 13', 'PC33-56', 3, 10, 'Humidity', '%', 'Room4', 'This sensor has number thirteen'),
    ('Sensor 14', 'GD-23', 0, 100, 'Voltage', 'voltage', 'Room1', 'This sensor has number fourteen'),
    ('Sensor 15', 'TER-21', 0, 30, 'Pressure', 'bar', 'Room1', 'This sensor has number fifteen'),
    ('Sensor 16', 'LL_3', 3, 10, 'Humidity', '%', 'Room2', 'This sensor has number sixteen'),
    ('Sensor 17', 'EH-567', 0, 30, 'Humidity', '%', 'Room3', 'This sensor has number seventeen'),
    ('Sensor 18', 'H94', -25, 25, 'Temperature', '°С', 'Room4', 'This sensor has number eighteen'),
    ('Sensor 19', 'GD-23', -70, 50, 'Pressure', 'bar', 'Room1', 'This sensor has number nineteen'),
    ('Sensor 20', 'EH-567', 0, 100, 'Voltage', 'voltage', 'Room2', 'This sensor has number twenty'),
    ('Sensor 21', 'LL_3', 3, 10, 'Humidity', '%', 'Room4', 'This sensor has number twenty one'),
    ('Sensor 22', 'TER-21', -25, 25, 'Pressure', 'bar', 'Room2', 'This sensor has number twenty two'),
    ('Sensor 23', 'PC33-56', -70, 50, 'Temperature', '°С', 'Room3', 'This sensor has number twenty three'),
    ('Sensor 24', 'H94', -25, 25, 'Voltage', 'voltage', 'Room1', 'This sensor has number twenty four'),
    ('Sensor 25', 'QQ-12',0, 30, 'Pressure', 'bar', 'Room4', 'This sensor has number twenty five'),
    ('Sensor 26', 'EH-567', 0, 100, 'Humidity', '%', 'Room4', 'This sensor has number twenty six');

