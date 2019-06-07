package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Responsables;
import com.gdunivo.es.model.RolResponsable;
import com.gdunivo.es.model.Roles;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.ResponsableRepository;
import com.gdunivo.es.repository.RolResponsableRepository;
import com.gdunivo.es.repository.RolesRepository;

public class RolResponsableRepositoryImp implements RolResponsableRepository {
	private RolResponsable rResponsable;
	private List<RolResponsable> rResponsablesList;
	private ResponsableRepository responsable = (ResponsableRepository) RepositoryFactory.getRepository("Responsables");
	private RolesRepository roles = (RolesRepository) RepositoryFactory.getRepository("Roles");

	@Override
	public RolResponsable guardar(RolResponsable ra) {
		ConexionFactory.createdConection();
		String query = "INSERT INTO Rol_Responsable(Id_Rol, Cod_Responsable,Contraseña)"
				+ "VALUES(?,?,?); SELECT * FROM Rol_Responsable WHERE Id_Rol_Responsable = (SELECT MAX(Id_Rol_Responsable) FROM Rol_Responsable)";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, ra.getRole().getId_Role());
			sql.setString(2, ra.getResponsable().getCodResponsable());
			sql.setString(3, ra.getContraseña());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rResponsable = new RolResponsable();
				this.rResponsable.setIdRoleResponsable(result.getInt("Id_Rol_Responsable"));
				this.rResponsable.setRole(roles.rolePorId(result.getInt("Id_Rol")));
				this.rResponsable.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));
				this.rResponsable.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rResponsable;

		} catch (Exception e) {
			System.out.println("Error guardando Role responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolResponsable actualizar(RolResponsable ra) {
		ConexionFactory.createdConection();
		String query = "UPDATE Rol_Responsable SET Id_Rol=?, Cod_Responsable=?, Contraseña=? WHERE Id_Rol_Responsable=?;"
				+ "SELECT * FROM Rol_Responsable WHERE Id_Rol_Responsable=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, ra.getRole().getId_Role());
			sql.setString(2, ra.getResponsable().getCodResponsable());
			sql.setString(3, ra.getContraseña());
			sql.setInt(4, ra.getIdRoleResponsable());
			sql.setInt(4, ra.getIdRoleResponsable());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rResponsable = new RolResponsable();
				this.rResponsable.setIdRoleResponsable(result.getInt("Id_Rol_Responsable"));
				this.rResponsable.setRole(roles.rolePorId(result.getInt("Id_Rol")));
				this.rResponsable.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));
				this.rResponsable.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rResponsable;

		} catch (Exception e) {
			System.out.println("Error guardando Role responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminar(RolResponsable ra) {
		ConexionFactory.createdConection();
		String query = "DELETE FROM Rol_Responsable WHERE Id_Rol_Responsable=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setInt(1, ra.getIdRoleResponsable());
			boolean value = sql.execute();
			sql.close();
			return value;

		} catch (Exception e) {
			System.out.println("Error eliminando Role responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RolResponsable> lista() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Responsable";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			ResultSet result = sql.executeQuery();
			this.rResponsablesList = new ArrayList<>();
			while (result.next()) {
				this.rResponsable = new RolResponsable();
				this.rResponsable.setIdRoleResponsable(result.getInt("Id_Rol_Responsable"));
				this.rResponsable.setRole(roles.rolePorId(result.getInt("Id_Rol")));
				this.rResponsable.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));
				this.rResponsable.setContraseña(result.getString("Contraseña"));
				this.rResponsablesList.add(this.rResponsable);
			}
			result.close();
			sql.close();
			return this.rResponsablesList;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista de Role responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolResponsable busquedaPoId(int id) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Responsable WHERE Id_Rol_Responsable=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, id);
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rResponsable = new RolResponsable();
				this.rResponsable.setIdRoleResponsable(result.getInt("Id_Rol_Responsable"));
				this.rResponsable.setRole(roles.rolePorId(result.getInt("Id_Rol")));
				this.rResponsable.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));
				this.rResponsable.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rResponsable;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista de Role responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RolResponsable> listaPorRol(Roles r) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Responsable WHERE Id_Rol = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, r.getId_Role());
			ResultSet result = sql.executeQuery();
			this.rResponsablesList = new ArrayList<>();
			while (result.next()) {
				this.rResponsable = new RolResponsable();
				this.rResponsable.setIdRoleResponsable(result.getInt("Id_Rol_Responsable"));
				this.rResponsable.setRole(roles.rolePorId(result.getInt("Id_Rol")));
				this.rResponsable.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));
				this.rResponsable.setContraseña(result.getString("Contraseña"));
				this.rResponsablesList.add(this.rResponsable);
			}
			result.close();
			sql.close();
			return this.rResponsablesList;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista de Role responsable Por Role::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RolResponsable busquedaPorResponsable(Responsables a) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Rol_Responsable WHERE Cod_Responsable = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, a.getCodResponsable());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.rResponsable = new RolResponsable();
				this.rResponsable.setIdRoleResponsable(result.getInt("Id_Rol_Responsable"));
				this.rResponsable.setRole(roles.rolePorId(result.getInt("Id_Rol")));
				this.rResponsable.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));
				this.rResponsable.setContraseña(result.getString("Contraseña"));
			}
			result.close();
			sql.close();
			return this.rResponsable;

		} catch (Exception e) {
			System.out.println("Error obteniendo  Role de responsable Por responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
