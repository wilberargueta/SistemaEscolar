package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.RolAlumno;
import com.gdunivo.es.model.Roles;
import com.gdunivo.es.repository.AlumnosRepository;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.RolAlumnoRepository;
import com.gdunivo.es.repository.RolesRepository;

public class RolAlumnoRepositoryImp implements RolAlumnoRepository {
	private RolAlumno rAlumno;
	private List<RolAlumno> rAlumnoList;
	private AlumnosRepository alumno = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
	private RolesRepository roles = (RolesRepository) RepositoryFactory.getRepository("Roles");

	@Override
	public RolAlumno guardar(RolAlumno ra) {
		ConexionFactory.createdConection();
		String query = "INSERT INTO Rol_Alumno(Id_Rol, Cod_Alumno,Contraseña)"
				+ "VALUES(?,?,?); SELECT * FROM Rol_Alumno WHERE Id_Rol_Alumno = (SELECT MAX(Id_Rol_Alumno) FROM Rol_Alumno)";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, ra.getRol().getId_Role());
			sql.setString(2, ra.getAlumno().getCodAlumno());
			sql.setString(3, ra.getContraseña());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rAlumno = new RolAlumno();
				this.rAlumno.setRolAlumno(result.getInt("Id_Rol_Alumno"));
				this.rAlumno.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rAlumno.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.rAlumno.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rAlumno;

		} catch (Exception e) {
			System.out.println("Error guardando Role Alumno::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolAlumno actualizar(RolAlumno ra) {
		ConexionFactory.createdConection();
		String query = "UPDATE Rol_Alumno SET Id_Rol=?, Cod_Alumno=?, Contraseña=? WHERE Id_Rol_Alumno=?;"
				+ "SELECT * FROM Rol_Alumno WHERE Id_Rol_Alumno=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setInt(1, ra.getRol().getId_Role());
			sql.setString(2, ra.getAlumno().getCodAlumno());
			sql.setString(3, ra.getContraseña());
			sql.setInt(4, ra.getRolAlumno());
			sql.setInt(5, ra.getRolAlumno());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rAlumno = new RolAlumno();
				this.rAlumno.setRolAlumno(result.getInt("Id_Rol_Alumno"));
				this.rAlumno.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rAlumno.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.rAlumno.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rAlumno;

		} catch (Exception e) {
			System.out.println("Error actualizando Role Alumno::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminar(RolAlumno ra) {
		ConexionFactory.createdConection();
		String query = "DELETE FROM Rol_Alumno WHERE Id_Rol_Alumno=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setInt(1, ra.getRolAlumno());
			boolean value = sql.execute();
			sql.close();
			return value;

		} catch (Exception e) {
			System.out.println("Error eliminando Role Alumno::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RolAlumno> lista() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Alumno";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			ResultSet result = sql.executeQuery();
			this.rAlumnoList = new ArrayList<>();
			while (result.next()) {
				this.rAlumno = new RolAlumno();
				this.rAlumno.setRolAlumno(result.getInt("Id_Rol_Alumno"));
				this.rAlumno.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rAlumno.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.rAlumno.setContraseña(result.getString("Contraseña"));
				this.rAlumnoList.add(rAlumno);
			}
			result.close();
			sql.close();
			return this.rAlumnoList;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista Role Alumno::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolAlumno busquedaPoId(int id) {

		ConexionFactory.createdConection();

		String query = "SELECT * FROM Rol_Alumno WHERE Id_Rol_Alumno = ?";

		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {

			sql.setInt(1, id);
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rAlumno = new RolAlumno();
				this.rAlumno.setRolAlumno(result.getInt("Id_Rol_Alumno"));
				this.rAlumno.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rAlumno.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.rAlumno.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rAlumno;

		} catch (Exception e) {
			System.out.println("Error obteniendo  Role Alumno por ID::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RolAlumno> listaPorRol(Roles r) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Alumno WHERE Id_Rol = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, r.getId_Role());
			ResultSet result = sql.executeQuery();
			this.rAlumnoList = new ArrayList<>();
			while (result.next()) {
				this.rAlumno = new RolAlumno();
				this.rAlumno.setRolAlumno(result.getInt("Id_Rol_Alumno"));
				this.rAlumno.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rAlumno.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.rAlumno.setContraseña(result.getString("Contraseña"));
				this.rAlumnoList.add(rAlumno);
			}
			result.close();
			sql.close();
			return this.rAlumnoList;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista Role Alumno Por ROLE::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolAlumno busquedaPorAlumno(Alumnos a) {
		ConexionFactory.createdConection();

		String query = "SELECT * FROM Rol_Alumno WHERE Cod_Alumno = ?";

		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {

			sql.setString(1, a.getCodAlumno());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rAlumno = new RolAlumno();
				this.rAlumno.setRolAlumno(result.getInt("Id_Rol_Alumno"));
				this.rAlumno.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rAlumno.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.rAlumno.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rAlumno;

		} catch (Exception e) {
			System.out.println("Error obteniendo  Role Alumno por Alumno::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
