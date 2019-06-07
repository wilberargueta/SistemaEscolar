package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Generales;
import com.gdunivo.es.repository.GeneralesRepository;

public class GeneralesRepositoryImp implements GeneralesRepository {

	private Generales gene;

	@Override
	public Generales guardar(Generales g) {
		ConexionFactory.createdConection();
		String query = "INSERT INTO Generales(Nombre_Institucion, Direccion, Telefono, Codigo) "
				+ "VALUES(?,?,?,?); SELECT * FROM Generales";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, g.getNombreInstitucion());
			sql.setString(2, g.getDireccion());
			sql.setString(3, g.getTelefono());
			sql.setString(4, g.getCodigo());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.gene = new Generales();
				this.gene.setIdGenerales(result.getInt("Id_Generales"));
				this.gene.setNombreInstitucion(result.getString("Nombre_Institucion"));
				this.gene.setDireccion(result.getString("Direccion"));
				this.gene.setTelefono(result.getString("Telefono"));
				this.gene.setCodigo(result.getString("Codigo"));

			}
			return this.gene;
		} catch (Exception e) {
			System.out.println("Error guardando las generalidades" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Generales modificar(Generales g) {
		ConexionFactory.createdConection();
		String query = "UPDATE Generales SET Nombre_Institucion = ?, Direccion= ?, Telefono= ?, Codigo= ? WHERE Id_Generales = ? ;SELECT * FROM Generales";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, g.getNombreInstitucion());
			sql.setString(2, g.getDireccion());
			sql.setString(3, g.getTelefono());
			sql.setString(4, g.getCodigo());
			sql.setInt(5, g.getIdGenerales());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.gene = new Generales();
				this.gene.setIdGenerales(result.getInt("Id_Generales"));
				this.gene.setNombreInstitucion(result.getString("Nombre_Institucion"));
				this.gene.setDireccion(result.getString("Direccion"));
				this.gene.setTelefono(result.getString("Telefono"));
				this.gene.setCodigo(result.getString("Codigo"));

			}
			return this.gene;
		} catch (Exception e) {
			System.out.println("Error actualizando las generalidades" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Generales obtenerGenerales() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Generales";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try (ResultSet result = sql.executeQuery()) {
			while (result.next()) {
				this.gene = new Generales();
				this.gene.setIdGenerales(result.getInt("Id_Generales"));
				this.gene.setNombreInstitucion(result.getString("Nombre_Institucion"));
				this.gene.setDireccion(result.getString("Direccion"));
				this.gene.setTelefono(result.getString("Telefono"));
				this.gene.setCodigo(result.getString("Codigo"));

			}
			return this.gene;
		} catch (Exception e) {
			System.out.println("Error obteniendo las generalidades" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

}
