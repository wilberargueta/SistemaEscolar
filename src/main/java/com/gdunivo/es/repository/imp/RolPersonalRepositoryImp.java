package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Personal;
import com.gdunivo.es.model.RolPersonal;
import com.gdunivo.es.model.Roles;
import com.gdunivo.es.repository.PersonalRepository;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.RolPersonalRepository;
import com.gdunivo.es.repository.RolesRepository;

public class RolPersonalRepositoryImp implements RolPersonalRepository {
	private RolPersonal rPersonal;
	private List<RolPersonal> rPersonalList;
	private PersonalRepository personal = (PersonalRepository) RepositoryFactory.getRepository("Personal");
	private RolesRepository roles = (RolesRepository) RepositoryFactory.getRepository("Roles");

	@Override
	public RolPersonal guardar(RolPersonal ra) {
		ConexionFactory.createdConection();
		String query = "INSERT INTO Rol_Personal(Id_Rol, Cod_Personal,Contraseña)"
				+ "VALUES(?,?,?); SELECT * FROM Rol_Personal WHERE Id_Rol_Personal = (SELECT MAX(Id_Rol_Personal) FROM Rol_Personal)";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, ra.getRol().getId_Role());
			sql.setString(2, ra.getPersonal().getCodPersonal());
			sql.setString(3, ra.getContraseña());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rPersonal = new RolPersonal();
				this.rPersonal.setIdRolePersonal(result.getInt("Id_Rol_Personal"));
				this.rPersonal.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rPersonal.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.rPersonal.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rPersonal;

		} catch (Exception e) {
			System.out.println("Error guardando Role Personal::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolPersonal actualizar(RolPersonal ra) {
		ConexionFactory.createdConection();
		String query = "UPDATE Rol_Personal SET Id_Rol=?, Cod_Personal=?, Contraseña=? WHERE Id_Rol_Alumno=?;"
				+ "SELECT * FROM Rol_Personal WHERE Id_Rol_Personal=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, ra.getRol().getId_Role());
			sql.setString(2, ra.getPersonal().getCodPersonal());
			sql.setString(3, ra.getContraseña());
			sql.setInt(4, ra.getIdRolePersonal());
			sql.setInt(5, ra.getIdRolePersonal());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rPersonal = new RolPersonal();
				this.rPersonal.setIdRolePersonal(result.getInt("Id_Rol_Personal"));
				this.rPersonal.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rPersonal.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.rPersonal.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rPersonal;

		} catch (Exception e) {
			System.out.println("Error actualizando Role Personal::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminar(RolPersonal ra) {
		ConexionFactory.createdConection();
		String query = "DELETE FROM Rol_Personal WHERE Id_Rol_Personal=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setInt(1, ra.getIdRolePersonal());
			boolean value = sql.execute();
			sql.close();
			return value;

		} catch (Exception e) {
			System.out.println("Error eliminando Role Personal::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RolPersonal> lista() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Personal";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			ResultSet result = sql.executeQuery();
			this.rPersonalList = new ArrayList<>();
			while (result.next()) {
				this.rPersonal = new RolPersonal();
				this.rPersonal.setIdRolePersonal(result.getInt("Id_Rol_Personal"));
				this.rPersonal.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rPersonal.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.rPersonal.setContraseña(result.getString("Contraseña"));
				this.rPersonalList.add(rPersonal);
			}
			result.close();
			sql.close();
			return this.rPersonalList;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista de Role Personal::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolPersonal busquedaPoId(int id) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Personal WHERE Id_Rol_Personal = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, id);
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rPersonal = new RolPersonal();
				this.rPersonal.setIdRolePersonal(result.getInt("Id_Rol_Personal"));
				this.rPersonal.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rPersonal.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.rPersonal.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rPersonal;

		} catch (Exception e) {
			System.out.println("Error obteniendo de Role Personal por ID::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RolPersonal> listaPorRol(Roles r) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Personal WHERE Id_Rol = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, r.getId_Role());
			ResultSet result = sql.executeQuery();
			this.rPersonalList = new ArrayList<>();
			while (result.next()) {
				this.rPersonal = new RolPersonal();
				this.rPersonal.setIdRolePersonal(result.getInt("Id_Rol_Personal"));
				this.rPersonal.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rPersonal.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.rPersonal.setContraseña(result.getString("Contraseña"));
				this.rPersonalList.add(rPersonal);
			}
			result.close();
			sql.close();
			return this.rPersonalList;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista de Role Personal::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolPersonal busquedaPorPersonal(Personal a) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Personal WHERE Cod_Personal = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, a.getCodPersonal());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rPersonal = new RolPersonal();
				this.rPersonal.setIdRolePersonal(result.getInt("Id_Rol_Personal"));
				this.rPersonal.setRol(roles.rolePorId(result.getInt("Id_Rol")));
				this.rPersonal.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.rPersonal.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rPersonal;

		} catch (Exception e) {
			System.out.println("Error obteniendo Role Personal por ID::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
