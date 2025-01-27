
/*********************************************************
SCRIPT EJERCICIO FINAL MODULO N°7 | UNIVERSIDAD ANDES
ALUMNO			: Francisco Javier Araya Hernandez
FECHA				: 17/01/2025
CURSO       : BOTIC-SOFOF-23-30-13-0064
SERVER			: LOCALHOST
PUERTO			: 5432
DATABASE		: POSTGRES
SCHEMA			: PUBLIC
ENGINE			: POSTGRESQL 16.4
USERNAME		: postgres
PASSWORD		: ******
*********************************************************/

/*********************************************************
SCRIPT CREACION DE ENTIDADES DE LA BASE DE DATOS
*********************************************************/

-- Creacion tabla de alumnos
CREATE TABLE alumno(
    id_alumno 					SERIAL PRIMARY KEY,
    rut 								VARCHAR(12) NOT NULL UNIQUE,
    nombre 							VARCHAR(100) NOT NULL,
    direccion 					VARCHAR(200),
    fecha_creacion 			TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Creacion tabla de materias
CREATE TABLE materia(
    id_materia 					SERIAL PRIMARY KEY,
    nombre 							VARCHAR(100) NOT NULL,
    alumno_id 					INT,
    fecha_creacion 			TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_alumno FOREIGN KEY(alumno_id) REFERENCES alumno(id_alumno) ON DELETE CASCADE
);

-- Creacion tabla usuario
CREATE TABLE usuario(
    id_usuario 					SERIAL PRIMARY KEY,
    nombre 							VARCHAR(100) NOT NULL,
    username 						VARCHAR(50) NOT NULL UNIQUE,
    email 							VARCHAR(100) NOT NULL UNIQUE,
    password 						VARCHAR(100) NOT NULL,
    fecha_creacion 			TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Creacion tabla de Roles
CREATE TABLE rol(
    id_rol 							SERIAL PRIMARY KEY,
    nombre_rol 					VARCHAR(50) NOT NULL UNIQUE
);

-- Creacion tabla usuario_rol (Para romper relacion N:M entre Usuario y Rol)
CREATE TABLE usuario_rol(
    usuario_id 					INT,
    rol_id 							INT,
    PRIMARY KEY (usuario_id, rol_id),
    
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES rol(id_rol) ON DELETE CASCADE
);

/*********************************************************
SCRIPT CON TRIGGERS PARA ACTUALIZAR REGISTROS AUTOMATICOS
*********************************************************/
-- Función para actualizar fecha_actualizacion
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.fecha_actualizacion = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para la tabla alumno
CREATE TRIGGER update_alumno_updated_at
BEFORE UPDATE ON alumno
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- Trigger para la tabla materia
CREATE TRIGGER update_materia_updated_at
BEFORE UPDATE ON materia
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- Trigger para la tabla usuario
CREATE TRIGGER update_usuario_updated_at
BEFORE UPDATE ON usuario
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

/*********************************************************
SCRIPT INSERTS PARA LAS ENTIDADES DE LA BASE DE DATOS
*********************************************************/
-- Insert en la tabla alumno
INSERT INTO alumno (rut, nombre, direccion) VALUES
('12345678-9', 'Juan Pérez', 'Calle Falsa 123, Santiago'),
('98765432-1', 'María González', 'Avenida Siempre Viva 456, Valparaíso'),
('19283746-5', 'Carlos Sánchez', 'Pasaje Sin Salida 789, Concepción'),
('56473829-0', 'Ana López', 'Callejón del Beso 101, La Serena'),
('11223344-5', 'Pedro Martínez', 'Avenida Libertad 202, Antofagasta');

-- Insert en la tabla materia
INSERT INTO materia (nombre, alumno_id) VALUES
('Matemáticas', 1),
('Física', 1),
('Química', 2),
('Historia', 3),
('Literatura', 4),
('Biología', 5);

-- Insert en la tabla usuario
INSERT INTO usuario (nombre, username, email, password) VALUES
('Admin User', 'admin', 'admin@example.com', '$2a$10$ExampleHashedPassword1'),
('Cliente User', 'cliente', 'cliente@example.com', '$2a$10$ExampleHashedPassword2'),
('Profesor User', 'profesor', 'profesor@example.com', '$2a$10$ExampleHashedPassword3'),
('Estudiante User', 'estudiante', 'estudiante@example.com', '$2a$10$ExampleHashedPassword4'),
('Invitado User', 'invitado', 'invitado@example.com', '$2a$10$ExampleHashedPassword5');

-- Passwords sin encriptar
INSERT INTO usuario (nombre, username, email, password) VALUES
('Admin User', 'admin', 'admin@example.com', 'admin123'),
('Cliente User', 'cliente', 'cliente@example.com', 'cliente123'),
('Profesor User', 'profesor', 'profesor@example.com', 'profesor123'),
('Estudiante User', 'estudiante', 'estudiante@example.com', 'estudiante123'),
('Invitado User', 'invitado', 'invitado@example.com', 'invitado123');

-- Insert en la tabla rol
INSERT INTO rol (nombre_rol) VALUES
('ROLE_ADMIN'),
('ROLE_CLIENT'),
('ROLE_PROFESOR'),
('ROLE_ESTUDIANTE'),
('ROLE_INVITADO');

-- Insert en la tabla usuario_rol
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

