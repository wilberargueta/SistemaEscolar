package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.repository.AlumnosRepository;

public class AlumnosRepositoryImp implements AlumnosRepository {

	private Alumnos alumno;
	private List<Alumnos> listaAlumnos;

	@Override
	public Alumnos guardar(Alumnos alumno) {

		ConexionFactory.createdConection();
		String query = "EXEC stpInsertarAlumno @Nombre=?, @Apellido=?, @Direccion=?," + "@Fecha_Nacimiento=?, @Sexo=?";

		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, alumno.getNombre());
			sql.setString(2, alumno.getApellido());
			sql.setString(3, alumno.getDireccion());
			sql.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
			sql.setString(5, alumno.getSexo());
			ResultSet result = sql.executeQuery();

			this.alumno = new Alumnos();
			while (result.next()) {
				this.alumno.setCodAlumno(result.getString("Cod_Alumno"));
				this.alumno.setNombre(result.getString("Nombre"));
				this.alumno.setApellido(result.getString("Apellido"));
				this.alumno.setDireccion(result.getString("Direccion"));
				this.alumno.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				this.alumno.setSexo(result.getString("Sexo"));
			}
			return this.alumno;

		} catch (SQLException e) {
			System.out.println("Error ingresando Alumno::" + e.getMessage());
			e.printStackTrace();
			ConexionFactory.closeConection();
		} finally {
			ConexionFactory.closeConection();
		}
		return null;
	}

	@Override
	public Alumnos actualizar(Alumnos alumno) {
		ConexionFactory.createdConection();
		String query = "EXEC stpActualizarAlumno @Cod_Alumno=?, @Nombre=?, @Apellido=?, @Direccion=?,"
				+ "@Fecha_Nacimiento=?, @Sexo=?";

		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, alumno.getCodAlumno());
			sql.setString(2, alumno.getNombre());
			sql.setString(3, alumno.getApellido());
			sql.setString(4, alumno.getDireccion());
			sql.setDate(5, Date.valueOf(alumno.getFechaNacimiento()));
			sql.setString(6, alumno.getSexo());
			ResultSet result = sql.executeQuery();

			this.alumno = new Alumnos();
			while (result.next()) {
				this.alumno.setCodAlumno(result.getString("Cod_Alumno"));
				this.alumno.setNombre(result.getString("Nombre"));
				this.alumno.setApellido(result.getString("Apellido"));
				this.alumno.setDireccion(result.getString("Direccion"));
				this.alumno.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				this.alumno.setSexo(result.getString("Sexo"));
			}
			return this.alumno;

		} catch (SQLException e) {
			System.out.println("Error actualizando Alumno::" + e.getMessage());
			e.printStackTrace();
			ConexionFactory.closeConection();
		} finally {
			ConexionFactory.closeConection();
		}
		return null;
	}

	@Override
	public boolean eliminar(Alumnos alumno) {
		ConexionFactory.createdConection();
		String query = "EXEC stpEliminarAlumno @COD=?";

		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, alumno.getCodAlumno());

			return sql.execute();
		} catch (SQLException e) {
			System.out.println("Error eliminando Alumno::" + e.getMessage());
			e.printStackTrace();
			ConexionFactory.closeConection();
		} finally {
			ConexionFactory.closeConection();
		}

		return false;

	}

	@Override
	public List<Alumnos> obtenerLista() {
		String query = "SELECT * FROM Alumnos WHERE Activo = 1";
		ConexionFactory.createdConection();
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			ResultSet result = sql.executeQuery();
			this.listaAlumnos = new ArrayList<>();
			while (result.next()) {
				Alumnos m = new Alumnos();
				m.setCodAlumno(result.getString("Cod_Alumno"));
				m.setNombre(result.getString("Nombre"));
				m.setApellido(result.getString("Apellido"));
				m.setDireccion(result.getString("Direccion"));
				m.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				m.setSexo(result.getString("Sexo"));
				this.listaAlumnos.add(m);
			}
			result.close();
			sql.close();
			return this.listaAlumnos;

		} catch (Exception e) {
			System.out.println("Error obteniendo la lista de alumnos::" + e.getMessage());

		} finally {

		}
		return null;
	}

	@Override
	public Alumnos buscarPorCodigo(String codAlumno) {
		String query = "SELECT * FROM Alumnos WHERE Activo = 1 AND Cod_Alumno = ?";
		ConexionFactory.createdConection();
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, codAlumno);
			ResultSet result = sql.executeQuery();

			this.alumno = new Alumnos();
			while (result.next()) {

				this.alumno.setCodAlumno(result.getString("Cod_Alumno"));
				this.alumno.setNombre(result.getString("Nombre"));
				this.alumno.setApellido(result.getString("Apellido"));
				this.alumno.setDireccion(result.getString("Direccion"));
				this.alumno.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				this.alumno.setSexo(result.getString("Sexo"));

			}
			return this.alumno;

		} catch (Exception e) {
			System.out.println("Error obteniendo la lista de alumnos::" + e.getMessage());
			ConexionFactory.closeConection();
		} finally {
			ConexionFactory.closeConection();
		}
		return null;
	}

	@Override
	public List<Alumnos> busquedaPorNombre(String parametro) {
		String query = "SELECT * FROM Alumnos WHERE Activo = 1 AND Nombre like ?";
		ConexionFactory.createdConection();
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, "%" + parametro + "%");

			ResultSet result = sql.executeQuery();

			this.listaAlumnos = new ArrayList<>();
			while (result.next()) {
				Alumnos m = new Alumnos();
				m.setCodAlumno(result.getString("Cod_Alumno"));
				m.setNombre(result.getString("Nombre"));
				m.setApellido(result.getString("Apellido"));
				m.setDireccion(result.getString("Direccion"));
				m.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				m.setSexo(result.getString("Sexo"));
				this.listaAlumnos.add(m);

			}
			return this.listaAlumnos;

		} catch (Exception e) {
			System.out.println("Error obteniendo la lista de alumnos::" + e.getMessage());
			ConexionFactory.closeConection();
		} finally {
			ConexionFactory.closeConection();
		}
		return null;
	}

	@Override
	public List<Alumnos> busquedaPorApellido(String apelldido) {
		String query = "SELECT * FROM Alumnos WHERE Activo = 1 AND Apellido like ?";
		ConexionFactory.createdConection();
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, "%" + apelldido + "%");

			ResultSet result = sql.executeQuery();

			this.listaAlumnos = new ArrayList<>();
			while (result.next()) {
				Alumnos m = new Alumnos();
				m.setCodAlumno(result.getString("Cod_Alumno"));
				m.setNombre(result.getString("Nombre"));
				m.setApellido(result.getString("Apellido"));
				m.setDireccion(result.getString("Direccion"));
				m.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				m.setSexo(result.getString("Sexo"));
				this.listaAlumnos.add(m);

			}
			return this.listaAlumnos;

		} catch (Exception e) {
			System.out.println("Error obteniendo la lista de alumnos::" + e.getMessage());
			ConexionFactory.closeConection();
		} finally {
			ConexionFactory.closeConection();
		}
		return null;
	}

}
