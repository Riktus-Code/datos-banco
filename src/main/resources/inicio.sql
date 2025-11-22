
create database banco;

CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      nombre VARCHAR(255) NOT NULL,
                      direccion VARCHAR(255),
                      telefono VARCHAR(50),
                      fecha_nac VARCHAR(255)
);

