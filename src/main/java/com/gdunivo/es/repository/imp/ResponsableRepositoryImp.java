package com.gdunivo.es.repository.imp;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Responsables;
import com.gdunivo.es.repository.ResponsableRepository;

public class ResponsableRepositoryImp implements ResponsableRepository, Serializable {

	private static final long serialVersionUID = 1L;
	private Responsables respo;
	private List<Responsables> listaRespo;

	@Override
	public Responsables guardar(Responsables resp) {
		ConexionFactory.createdConection();
		String query = "EXEC stpInsertarResponsable @Nombre=?," + " @Apellido=?,@Direccion=?,@Telefono =?, @DUI=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, resp.getNombre());
			sql.setString(2, resp.getApellido());
			sql.setString(3, resp.getDireccion());
			sql.setString(4, resp.getTelefono());
			sql.setString(5, resp.getDUI());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.respo = new Responsables();
				this.respo.setCodResponsable(result.getString("Cod_Responsable"));
				this.respo.setNombre(result.getString("Nombre"));
				this.respo.setApellido(result.getString("Apellido"));
				this.respo.setDireccion(result.getString("Direccion"));
				this.respo.setTelefono(result.getString("Telefono"));
				this.respo.setDUI(result.getString("DUI"));

			}
			result.close();
			sql.close();
			return this.respo;

		} catch (Exception e) {
			System.out.println("Error guardando responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Responsables actualizar(Responsables resp) {
		ConexionFactory.createdConection();
		String query = "EXEC stpActualizarResponsable @Cod_Responsable= ?, @Nombre=?, @Apellido=?,@Direccion=?,"
				+ "@Telefono =?, @DUI=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, resp.getCodResponsable());
			sql.setString(2, resp.getNombre());
			sql.setString(3, resp.getApellido());
			sql.setString(4, resp.getDireccion());
			sql.setString(5, resp.getTelefono());
			sql.setString(6, resp.getDUI());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.respo = new Responsables();
				this.respo.setCodResponsable(result.getString("Cod_Responsable"));
				this.respo.setNombre(result.getString("Nombre"));
				this.respo.setApellido(result.getString("Apellido"));
				this.respo.setDireccion(result.getString("Direccion"));
				this.respo.setTelefono(result.getString("Telefono"));
				this.respo.setDUI(result.getString("DUI"));

			}
			result.close();
			sql.close();
			return this.respo;

		} catch (Exception e) {
			System.out.println("Error actualizandos responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminar(Responsables resp) {
		ConexionFactory.createdConection();
		String query = "EXEC stpEliminarResponsable @COD=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, resp.getCodResponsable());
			boolean value = sql.execute();
			return value;
		} catch (Exception e) {
			System.out.println("Error eliminando responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Responsables> obtenerListado() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Responsables WHERE Activo = 1";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			this.listaRespo = new ArrayList<>();
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				Responsables r = new Responsables();
				r.setCodResponsable(result.getString("Cod_Responsable"));
				r.setNombre(result.getString("Nombre"));
				r.setApellido(result.getString("Apellido"));
				r.setDireccion(result.getString("Direccion"));
				r.setTelefono(result.getString("Telefono"));
				r.setDUI(result.getString("DUI"));
				this.listaRespo.add(r);

			}

			result.close();
			sql.close();
			return this.listaRespo;

		} catch (Exception e) {
			System.out.println("Error obteniedno listado responsable::" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Responsables buscarPorCodigo(String cod) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Responsables WHERE Activo = 1 AND Cod_Responsable = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, cod);

			ResultSet result = sql.executeQuery();
			while (result.next()) {
				Responsables r = new Responsables();
				r.setCodResponsable(result.getString("Cod_Responsable"));
				r.setNombre(result.getString("Nombre"));
				r.setApellido(result.getString("Apellido"));
				r.setDireccion(result.getString("Direccion"));
				r.setTelefono(result.getString("Telefono"));
				r.setDUI(result.getString("DUI"));
				this.respo = r;

			}

			result.close();
			sql.close();
			return this.respo;

		} catch (Exception e) {
			System.out.println("Error obteniendo responsable por Codigo::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Responsables> buscarPorNombre(String nombre) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Responsables WHERE Activo = 1 AND Nombre LIKE ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, "%" + nombre + "%");
			ResultSet result = sql.executeQuery();
			this.listaRespo = new ArrayList<>();
			while (result.next()) {

				Responsables r = new Responsables();
				r.setCodResponsable(result.getString("Cod_Responsable"));
				r.setNombre(result.getString("Nombre"));
				r.setApellido(result.getString("Apellido"));
				r.setDireccion(result.getString("Direccion"));
				r.setTelefono(result.getString("Telefono"));
				r.setDUI(result.getString("DUI"));
				this.listaRespo.add(r);

			}

			result.close();
			sql.close();
			return this.listaRespo;

		} catch (Exception e) {
			System.out.println("Error obteniendo responsable por nombre::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Responsables> buscarPorApellido(String apellido) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Responsables WHERE Activo = 1 AND Apellido LIKE ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, "%" + apellido + "%");

			ResultSet result = sql.executeQuery();
			this.listaRespo = new ArrayList<>();
			while (result.next()) {
				Responsables r = new Responsables();
				r.setCodResponsable(result.getString("Cod_Responsable"));
				r.setNombre(result.getString("Nombre"));
				r.setApellido(result.getString("Apellido"));
				r.setDireccion(result.getString("Direccion"));
				r.setTelefono(result.getString("Telefono"));
				r.setDUI(result.getString("DUI"));
				this.listaRespo.add(r);

			}

			result.close();
			sql.close();
			return this.listaRespo;

		} catch (Exception e) {
			System.out.println("Error obteniendo responsable por apellido::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
