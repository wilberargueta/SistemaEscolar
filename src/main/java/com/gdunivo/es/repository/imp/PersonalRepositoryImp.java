package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Personal;
import com.gdunivo.es.repository.PersonalRepository;

public class PersonalRepositoryImp implements PersonalRepository {

	private Personal pers;
	private List<Personal> listaPers;

	@Override
	public Personal guardar(Personal pers) {
		ConexionFactory.createdConection();
		String query = "EXEC stpInsertarPersonal @Nombre=?, @Apellido=?,@Direccion=?, @Telefono =?, @DUI=?,"
				+ " @NIT=?, @Fecha_Nacimiento=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, pers.getNombre());
			sql.setString(2, pers.getApellido());
			sql.setString(3, pers.getDireccion());
			sql.setString(4, pers.getTelefono());
			sql.setString(5, pers.getDUI());
			sql.setString(6, pers.getNIT());
			sql.setDate(7, Date.valueOf(pers.getFechaNacimiento()));
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.pers = new Personal();
				this.pers.setCodPersonal(result.getString("Cod_Personal"));
				this.pers.setNombre(result.getString("Nombre"));
				this.pers.setApellido(result.getString("Apellido"));
				this.pers.setDireccion(result.getString("Direccion"));
				this.pers.setTelefono(result.getString("Telefono"));
				this.pers.setDUI(result.getString("DUI"));
				this.pers.setNIT(result.getString("NIT"));
				this.pers.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());

			}
			result.close();
			sql.close();
			return this.pers;

		} catch (Exception e) {
			System.out.println("Error guardando personal::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Personal actualizar(Personal pers) {
		ConexionFactory.createdConection();
		String query = "EXEC stpActualizarPersonal @Cod_Personal=?, @Nombre=?, @Apellido=?,@Direccion=?, "
				+ "@Telefono =?, @DUI=?, @NIT=?, @Fecha_Nacimiento=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, pers.getCodPersonal());
			sql.setString(2, pers.getNombre());
			sql.setString(3, pers.getApellido());
			sql.setString(4, pers.getDireccion());
			sql.setString(5, pers.getTelefono());
			sql.setString(6, pers.getDUI());
			sql.setString(7, pers.getNIT());
			sql.setDate(8, Date.valueOf(pers.getFechaNacimiento()));
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.pers = new Personal();
				this.pers.setCodPersonal(result.getString("Cod_Personal"));
				this.pers.setNombre(result.getString("Nombre"));
				this.pers.setApellido(result.getString("Apellido"));
				this.pers.setDireccion(result.getString("Direccion"));
				this.pers.setTelefono(result.getString("Telefono"));
				this.pers.setDUI(result.getString("DUI"));
				this.pers.setNIT(result.getString("NIT"));
				this.pers.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());

			}
			result.close();
			sql.close();
			return this.pers;

		} catch (Exception e) {
			System.out.println("Error actualizando personal::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminar(Personal pers) {
		ConexionFactory.createdConection();
		String query = "EXEC stpEliminarPersonal @COD=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, pers.getCodPersonal());
			boolean value = sql.execute();
			return value;
		} catch (Exception e) {
			System.out.println("Error eliminando personal::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Personal> obtenerListado() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Personal WHERE Activo = 1";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			this.listaPers = new ArrayList<>();
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				Personal p = new Personal();
				p.setCodPersonal(result.getString("Cod_Personal"));
				p.setNombre(result.getString("Nombre"));
				p.setApellido(result.getString("Apellido"));
				p.setDireccion(result.getString("Direccion"));
				p.setTelefono(result.getString("Telefono"));
				p.setDUI(result.getString("DUI"));
				p.setNIT(result.getString("NIT"));
				p.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				this.listaPers.add(p);

			}

			result.close();
			sql.close();
			return this.listaPers;

		} catch (Exception e) {
			System.out.println("Error obteniendo listado de personal::" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Personal buscarPorCodigo(String cod) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Personal WHERE Activo = 1 AND Cod_Personal = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, cod);

			ResultSet result = sql.executeQuery();
			while (result.next()) {
				Personal p = new Personal();
				p.setCodPersonal(result.getString("Cod_Personal"));
				p.setNombre(result.getString("Nombre"));
				p.setApellido(result.getString("Apellido"));
				p.setDireccion(result.getString("Direccion"));
				p.setTelefono(result.getString("Telefono"));
				p.setDUI(result.getString("DUI"));
				p.setNIT(result.getString("NIT"));
				p.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				this.pers = p;
			}

			result.close();
			sql.close();
			return this.pers;

		} catch (Exception e) {
			System.out.println("Error obteniendo responsable por Codigo::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personal> buscarPorNombre(String nombre) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Personal WHERE Activo = 1 AND Nombre LIKE ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, "%" + nombre + "%");
			ResultSet result = sql.executeQuery();
			this.listaPers = new ArrayList<>();
			while (result.next()) {

				Personal p = new Personal();
				p.setCodPersonal(result.getString("Cod_Personal"));
				p.setNombre(result.getString("Nombre"));
				p.setApellido(result.getString("Apellido"));
				p.setDireccion(result.getString("Direccion"));
				p.setTelefono(result.getString("Telefono"));
				p.setDUI(result.getString("DUI"));
				p.setNIT(result.getString("NIT"));
				p.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				this.listaPers.add(p);

			}

			result.close();
			sql.close();
			return this.listaPers;

		} catch (Exception e) {
			System.out.println("Error obteniendo personal por nombre::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personal> buscarPorApellido(String apellido) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Personal WHERE Activo = 1 AND Apellido LIKE ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, "%" + apellido + "%");
			ResultSet result = sql.executeQuery();
			this.listaPers = new ArrayList<>();
			while (result.next()) {

				Personal p = new Personal();
				p.setCodPersonal(result.getString("Cod_Personal"));
				p.setNombre(result.getString("Nombre"));
				p.setApellido(result.getString("Apellido"));
				p.setDireccion(result.getString("Direccion"));
				p.setTelefono(result.getString("Telefono"));
				p.setDUI(result.getString("DUI"));
				p.setNIT(result.getString("NIT"));
				p.setFechaNacimiento(new Date(result.getDate("Fecha_Nacimiento").getTime()).toLocalDate());
				this.listaPers.add(p);

			}

			result.close();
			sql.close();
			return this.listaPers;

		} catch (Exception e) {
			System.out.println("Error obteniendo personal por apellido::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
