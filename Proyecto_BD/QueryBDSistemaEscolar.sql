----CULERO MARICA
-----Gay
CREATE DATABASE Escuela

USE Escuela

CREATE TABLE Materias(
	Cod_Materia CHAR(10),
	Materia VARCHAR(20) NOT NULL UNIQUE,
	Activo BIT NOT NULL,
	AU_CREADO DATETIME NOT NULL,
	AU_ACTUALIZADO DATETIME NOT NULL,
	AU_ELIMINADO DATETIME,
	CONSTRAINT pk_materias PRIMARY KEY(Cod_Materia)
)

CREATE TABLE Generales(
	Id_Generales INT NOT NULL IDENTITY(1,1),
	Nombre_Institucion VARCHAR(30) NOT NULL,
	Direccion VARCHAR(50) NOT NULL,
	Telefono CHAR(11) NOT NULL,
	Codigo CHAR(5) NOT NULL,
	CONSTRAINT pk_generales PRIMARY KEY(Id_Generales)
)

CREATE TABLE Roles(
	Id_Rol INT NOT NULL IDENTITY(1,1),
	Rol VARCHAR(20) NOT NULL,
	CONSTRAINT pk_rol PRIMARY KEY(Id_Rol)
)

CREATE TABLE Personal(
	Cod_Personal CHAR(10) NOT NULL,
	Nombre VARCHAR(20) NOT NULL,
	Apellido VARCHAR(20) NOT NULL,
	Direccion VARCHAR(50) NOT NULL,
	Telefono CHAR(11) NOT NULL,
	DUI CHAR(11) NOT NULL,
	NIT CHAR(11) NOT NULL,
	Fecha_Nacimiento DATE NOT NULL,
	Activo BIT NOT NULL,
	AU_CREADO DATETIME NOT NULL,
	AU_ACTUALIZADO DATETIME NOT NULL,
	AU_ELIMINADO DATETIME ,
	CONSTRAINT pk_personal PRIMARY KEY(Cod_Personal)
)
CREATE TABLE Rol_Personal(
	Id_Rol_Personal INT NOT NULL IDENTITY(1,1),
	Id_Rol INT NOT NULL UNIQUE,
	Cod_Personal CHAR(10) NOT NULL UNIQUE,
	Contrase�a VARCHAR(12) NOT NULL,
	CONSTRAINT pk_rol_personal PRIMARY KEY(Id_Rol_Personal),
	CONSTRAINT fk_rol_personal_To_rol FOREIGN KEY(Id_Rol) REFERENCES Roles(Id_Rol) ON DELETE CASCADE,
	CONSTRAINT fk_rol_personal_To_personal FOREIGN KEY(Cod_Personal) REFERENCES Personal(Cod_Personal) ON DELETE CASCADE
)

CREATE TABLE Responsables(
	Cod_Responsable CHAR(10) NOT NULL,
	Nombre VARCHAR(20) NOT NULL,
	Apellido VARCHAR(20) NOT NULL,
	Direccion VARCHAR(50) NOT NULL,
	Telefono CHAR(11) NOT NULL,
	DUI CHAR(11) NOT NULL,
	Activo BIT NOT NULL,
	AU_CREADO DATETIME NOT NULL,
	AU_ACTUALIZADO DATETIME NOT NULL,
	AU_ELIMINADO DATETIME ,
	CONSTRAINT pk_responsable PRIMARY KEY(Cod_Responsable)
)
CREATE TABLE Rol_Responsable(
	Id_Rol_Responsable INT NOT NULL IDENTITY(1,1),
	Id_Rol INT NOT NULL UNIQUE,
	Cod_Responsable CHAR(10) NOT NULL UNIQUE,
	Contrase�a VARCHAR(12) NOT NULL,
	CONSTRAINT pk_rol_responsable PRIMARY KEY(Id_Rol_Responsable),
	CONSTRAINT fk_rol_responsable_To_rol FOREIGN KEY(Id_Rol) REFERENCES Roles(Id_Rol) ON DELETE CASCADE,
	CONSTRAINT fk_rol_responsable_To_responsable FOREIGN KEY(Cod_Responsable) REFERENCES Responsables(Cod_Responsable) ON DELETE CASCADE
)
CREATE TABLE Alumnos(
	Cod_Alumno CHAR(10) NOT NULL,
	Nombre VARCHAR(20) NOT NULL,
	Apellido VARCHAR(20) NOT NULL,
	Direccion VARCHAR(50) NOT NULL,
	Fecha_Nacimiento DATE NOT NULL,
	Sexo CHAR(1) NOT NULL,
	Activo BIT NOT NULL,
	AU_CREADO DATETIME NOT NULL,
	AU_ACTUALIZADO DATETIME NOT NULL,
	AU_ELIMINADO DATETIME ,
	CONSTRAINT pk_alumno PRIMARY KEY(Cod_Alumno)
)

CREATE TABLE Rol_Alumno(
	Id_Rol_Alumno INT NOT NULL IDENTITY(1,1),
	Id_Rol INT NOT NULL UNIQUE,
	Cod_Alumno CHAR(10) NOT NULL UNIQUE,
	Contrase�a VARCHAR(12) NOT NULL,
	CONSTRAINT pk_rol_alumno PRIMARY KEY(Id_Rol_Alumno),
	CONSTRAINT fk_rol_alumno_To_rol FOREIGN KEY(Id_Rol) REFERENCES Roles(Id_Rol) ON DELETE CASCADE,
	CONSTRAINT fk_rol_alumno_To_alumno FOREIGN KEY(Cod_Alumno) REFERENCES Alumnos(Cod_Alumno) ON DELETE CASCADE
)

CREATE TABLE  Clases(
	Id_Clases INT NOT NULL IDENTITY(1,1),
	Cod_Personal CHAR(10) NOT NULL,
	Cod_Materia CHAR(10) NOT NULL,
	Dia VARCHAR(10) NOT NULL,
	Hora VARCHAR(14) NOT NULL,
	Seccion CHAR(1) NOT NULL,
	CONSTRAINT pk_clases PRIMARY KEY(Id_Clases),
	CONSTRAINT fk_clases_personal FOREIGN KEY(Cod_Personal) REFERENCES Personal(Cod_Personal) ON UPDATE CASCADE,
	CONSTRAINT fk_clases_materia FOREIGN KEY(Cod_Materia) REFERENCES Materias(Cod_Materia)ON UPDATE CASCADE
)

CREATE TABLE Alumno_Clase(
	Id_Alumno_Clase INT NOT NULL IDENTITY(1,1),
	Cod_Alumno CHAR(10) NOT NULL,
	Id_Clases INT NOT NULL,
	Nota_PP FLOAT ,
	Nota_SP FLOAT ,
	Nota_TP FLOAT,
	Nota_Final FLOAT,
	CONSTRAINT pk_alumno_clase PRIMARY KEY(Id_Alumno_Clase),
	CONSTRAINT fk_alumno_clase_alumno FOREIGN KEY(Cod_Alumno) REFERENCES Alumnos(Cod_Alumno) ON DELETE CASCADE,
	CONSTRAINT fk_alumno_clase_clases FOREIGN KEY(Id_Clases) REFERENCES Clases(Id_Clases) ON DELETE CASCADE
)

CREATE TABLE Alumno_Responsable(
	Id_Alumno_Responsable INT NOT NULL IDENTITY(1,1),
	Cod_Alumno CHAR(10) NOT NULL UNIQUE,
	Cod_Responsable CHAR(10) NOT NULL ,
	CONSTRAINT pk_alumno_responsable PRIMARY KEY(Id_Alumno_Responsable),
	CONSTRAINT fk_alumno_responsable_alumno FOREIGN KEY(Cod_Alumno) REFERENCES Alumnos(Cod_Alumno) ON UPDATE CASCADE,
	CONSTRAINT fk_alumno_responsable_responsable FOREIGN KEY(Cod_Responsable) REFERENCES ResponsableS(Cod_Responsable) ON UPDATE CASCADE
)