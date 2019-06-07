package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Roles;
import com.gdunivo.es.repository.RolesRepository;

public class RolesRepositoryImp implements RolesRepository {

	private Roles role;
	private List<Roles> listaR;

	@Override
	public List<Roles> listaRoles() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Roles";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			ResultSet result = sql.executeQuery();
			this.listaR = new ArrayList<>();
			while (result.next()) {
				this.role = new Roles();
				this.role.setId_Role(result.getInt("Id_Rol"));
				this.role.setRole(result.getString("Rol"));
				this.listaR.add(role);
			}
			result.close();
			sql.close();
			return this.listaR;
		} catch (Exception e) {
			System.out.println("Error obteniendo la lista de roles" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Roles rolePorId(int id) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Roles WHERE Id_Rol = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, id);
			ResultSet result = sql.executeQuery();

			while (result.next()) {
				this.role = new Roles();
				this.role.setId_Role(result.getInt("Id_Rol"));
				this.role.setRole(result.getString("Rol"));

			}
			result.close();
			sql.close();
			return this.role;
		} catch (Exception e) {
			System.out.println("Error obteniendo  role por id" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
