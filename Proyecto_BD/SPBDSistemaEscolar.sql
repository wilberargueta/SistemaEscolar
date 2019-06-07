
--- Procesos Almacenados para Materias
CREATE PROCEDURE stpInsertarMateria @Materia VARCHAR(20)
AS 
BEGIN
	DECLARE @COD VARCHAR(10) = (SELECT CONCAT('M', RIGHT('000000000'+CAST(REPLACE(MAX(Cod_Materia), 'M', '')+ 1 AS VARCHAR(9)),9) ) FROM Materias)
BEGIN
	DECLARE @Rows INT = (SELECT COUNT(*) FROM Materias);
	IF(@Rows = 0)
		BEGIN
			INSERT INTO Materias(Cod_Materia, Materia, Activo, AU_CREADO, AU_ACTUALIZADO)
			VALUES('M000000001', @Materia, 1, GETDATE(), GETDATE())
			 SELECT * FROM Materias WHERE Cod_Materia = 'M000000001'
		END
	ELSE
		BEGIN
			INSERT INTO Materias(Cod_Materia, Materia, Activo, AU_CREADO, AU_ACTUALIZADO)
			VALUES(@COD, @Materia, 1, GETDATE(), GETDATE())
			SELECT * FROM Materias WHERE Cod_Materia = @COD
		END
		
END
END
GO


CREATE PROCEDURE stpActualizarMateria @COD VARCHAR(10), @Materia VARCHAR(20)
AS
BEGIN 
	UPDATE Materias SET Materia = @Materia, AU_ACTUALIZADO = GETDATE() 
		WHERE Cod_Materia = @COD;
		SELECT * FROM Materias WHERE Cod_Materia = @COD;
END
GO 


CREATE PROCEDURE stpEliminarMateria @COD VARCHAR(10)
AS
BEGIN
	UPDATE Materias SET Activo = 0, AU_ELIMINADO = GETDATE() 
		WHERE Cod_Materia = @COD;
END
GO

--- Procesos Almacenados para Alumno
CREATE PROCEDURE stpInsertarAlumno 
	@Nombre VARCHAR(20),
	@Apellido VARCHAR(20),
	@Direccion VARCHAR(50),
	@Fecha_Nacimiento DATE,
	@Sexo CHAR(1)
AS 
BEGIN
	DECLARE @COD VARCHAR(10) = (SELECT CONCAT('A', RIGHT('000000000'+CAST(REPLACE(MAX(Cod_Alumno), 'A', '')+ 1 AS VARCHAR(9)),9) ) FROM Alumnos)
BEGIN
	DECLARE @Rows INT = (SELECT COUNT(*) FROM Alumnos);
	IF(@Rows = 0)
		BEGIN 
			INSERT INTO Alumnos(Cod_Alumno, Nombre, Apellido, Direccion, Fecha_Nacimiento, Sexo, Activo, AU_CREADO, AU_ACTUALIZADO)
			VALUES('A000000001', @Nombre, @Apellido, @Direccion, @Fecha_Nacimiento,@Sexo, 1, GETDATE(), GETDATE());
			SELECT * FROM Alumnos WHERE Cod_Alumno = 'A000000001'
		END
	ELSE
		BEGIN 
			INSERT INTO Alumnos(Cod_Alumno, Nombre, Apellido, Direccion, Fecha_Nacimiento, Sexo, Activo, AU_CREADO, AU_ACTUALIZADO)
			VALUES(@COD, @Nombre, @Apellido, @Direccion, @Fecha_Nacimiento,@Sexo, 1, GETDATE(), GETDATE());
			SELECT * FROM Alumnos WHERE Cod_Alumno = @COD
		END
END
END
GO


CREATE PROCEDURE stpActualizarAlumno
	@Cod_Alumno CHAR(10), 
	@Nombre VARCHAR(20),
	@Apellido VARCHAR(20),
	@Direccion VARCHAR(50),
	@Fecha_Nacimiento DATE,
	@Sexo CHAR(1)
AS 
BEGIN
	UPDATE Alumnos SET Nombre = @Nombre,  Apellido = @Apellido, Direccion = @Direccion, 
	Fecha_Nacimiento = @Fecha_Nacimiento, Sexo = @Sexo, AU_ACTUALIZADO = GETDATE()
	WHERE Cod_Alumno =  @Cod_Alumno

	SELECT * FROM Alumnos WHERE Cod_Alumno = @Cod_Alumno; 
END
GO


CREATE PROCEDURE stpEliminarAlumno @COD VARCHAR(10)
AS
BEGIN
	UPDATE Alumnos SET Activo = 0, AU_ELIMINADO = GETDATE()
		WHERE Cod_Alumno = @COD
	 SELECT 1 ;
END
GO

--- Procesos Almacenados para Personal
CREATE PROCEDURE stpInsertarPersonal
	@Nombre VARCHAR(20),
	@Apellido VARCHAR(20),
	@Direccion VARCHAR(50),
	@Telefono CHAR(15),
	@DUI CHAR(11),
	@NIT CHAR(17),
	@Fecha_Nacimiento DATE
AS 
BEGIN
	DECLARE @COD VARCHAR(10) = (SELECT CONCAT('P', RIGHT('000000000'+CAST(REPLACE(MAX(Cod_Personal), 'P', '')+ 1 AS VARCHAR(9)),9) ) FROM Personal)
BEGIN
	DECLARE @Rows INT = (SELECT COUNT(*) FROM Personal);
	IF(@Rows = 0)
		BEGIN 
			INSERT INTO Personal(Cod_Personal, Nombre, Apellido, Direccion,Telefono,DUI, NIT, Fecha_Nacimiento, Activo, AU_CREADO, AU_ACTUALIZADO)
			VALUES('P000000001', @Nombre, @Apellido, @Direccion,@Telefono,@DUI, @NIT, @Fecha_Nacimiento, 1, GETDATE(), GETDATE())
			SELECT * FROM Personal WHERE Cod_Personal = 'P000000001';
		END
	ELSE
		BEGIN 
			INSERT INTO Personal(Cod_Personal, Nombre, Apellido, Direccion,Telefono,DUI, NIT, Fecha_Nacimiento, Activo, AU_CREADO, AU_ACTUALIZADO)
			VALUES(@COD, @Nombre, @Apellido, @Direccion,@Telefono,@DUI, @NIT, @Fecha_Nacimiento, 1, GETDATE(), GETDATE());
			SELECT * FROM Personal WHERE Cod_Personal = @COD;
		END
END
END
GO

CREATE PROCEDURE stpActualizarPersonal
	@Cod_Personal CHAR(10),
	@Nombre VARCHAR(20),
	@Apellido VARCHAR(20),
	@Direccion VARCHAR(50),
	@Telefono CHAR(15),
	@DUI CHAR(11),
	@NIT CHAR(17),
	@Fecha_Nacimiento DATE
AS 
BEGIN	
	UPDATE Personal SET Nombre = @Nombre, Apellido = @Apellido ,Direccion = @Direccion, Telefono = @Telefono,
	DUI = @DUI,NIT = @NIT, Fecha_Nacimiento = @Fecha_Nacimiento, AU_ACTUALIZADO = GETDATE()
	WHERE Cod_Personal = @Cod_Personal
	SELECT * FROM Personal WHERE  Cod_Personal = @Cod_Personal;
END
GO

CREATE PROCEDURE stpEliminarPersonal @COD VARCHAR(10)
AS
BEGIN
	UPDATE Personal SET Activo = 0, AU_ELIMINADO = GETDATE()
		WHERE Cod_Personal = @COD;
END
GO
--- Procesos Almacenados para Responsable
CREATE PROCEDURE stpInsertarResponsable
	@Nombre VARCHAR(20),
	@Apellido VARCHAR(20),
	@Direccion VARCHAR(50),
	@Telefono CHAR(15),
	@DUI CHAR(11)
AS 
BEGIN
	DECLARE @COD VARCHAR(10) = (SELECT CONCAT('R', RIGHT('000000000'+CAST(REPLACE(MAX(Cod_Responsable), 'R', '')+ 1 AS VARCHAR(9)),9) ) FROM Responsables)
BEGIN
	DECLARE @Rows INT = (SELECT COUNT(*) FROM Responsables);
	IF(@Rows = 0)
		BEGIN 
			INSERT INTO Responsables(Cod_Responsable, Nombre, Apellido, Direccion,Telefono,DUI, Activo, AU_CREADO, AU_ACTUALIZADO)
			VALUES('R000000001', @Nombre, @Apellido, @Direccion,@Telefono,@DUI,1, GETDATE(), GETDATE())
			SELECT * FROM Responsables WHERE Cod_Responsable = 'R000000001';
		END
	ELSE
		BEGIN 
			INSERT INTO Responsables(Cod_Responsable, Nombre, Apellido, Direccion,Telefono,DUI, Activo, AU_CREADO, AU_ACTUALIZADO)
			VALUES(@COD, @Nombre, @Apellido, @Direccion,@Telefono,@DUI,1, GETDATE(), GETDATE());
			SELECT * FROM Responsables WHERE Cod_Responsable = @COD;
		END
END
END
GO


CREATE PROCEDURE stpActualizarResponsable
	@Cod_Responsable CHAR(10),
	@Nombre VARCHAR(20),
	@Apellido VARCHAR(20),
	@Direccion VARCHAR(50),
	@Telefono CHAR(15),
	@DUI CHAR(11)
AS 
BEGIN
	UPDATE Responsables SET Nombre = @Nombre, Apellido = @Apellido, Direccion = @Direccion, Telefono = @Telefono, DUI = @DUI,
							AU_ACTUALIZADO = GETDATE() WHERE Cod_Responsable = @Cod_Responsable
							SELECT * FROM Responsables WHERE Cod_Responsable = @Cod_Responsable;
END
GO

CREATE PROCEDURE stpEliminarResponsable @COD VARCHAR(10)
AS
BEGIN
	UPDATE Responsables SET Activo = 0, AU_ELIMINADO = GETDATE()
		WHERE Cod_Responsable = @COD;
END
GO

CREATE PROCEDURE stpInsertarClase 
@Cod_Personal CHAR(10),
@Cod_Materia CHAR(10),
@Dia VARCHAR(10),
@Hora VARCHAR(14),
@Seccion CHAR(1),
@Turno VARCHAR(10)
AS
BEGIN
	INSERT INTO Clases(Cod_Personal, Cod_Materia, Dia, Hora, Seccion, Turno)
	VALUES(@Cod_Personal, @Cod_Materia, @Dia, @Hora,@Seccion, @Turno)
	SELECT * FROM Clases WHERE Id_Clases = (SELECT MAX(Id_Clases) FROM Clases);
END
GO

CREATE PROCEDURE stpActualizarClase 
@Id_Clases INT,
@Cod_Personal CHAR(10),
@Cod_Materia CHAR(10),
@Dia VARCHAR(10),
@Hora VARCHAR(14),
@Seccion CHAR(1),
@Turno VARCHAR(10)
AS
BEGIN
	UPDATE Clases SET Cod_Personal = @Cod_Personal, Cod_Materia = @Cod_Materia, Dia=@Dia,Hora=@Hora,Seccion=@Seccion,Turno=@Turno
	WHERE Id_Clases= @Id_Clases
	SELECT * FROM Clases WHERE Id_Clases = @Id_Clases;
END
GO

