package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.exception.NoDatosException;
import com.gdunivo.es.model.Materias;
import com.gdunivo.es.repository.MateriasRepository;

public class MateriaRepositoryImp implements MateriasRepository {

	private Materias materia;
	private List<Materias> listaMaterias;

	@Override
	public Materias guardar(Materias materia) {
		ConexionFactory.createdConection();
		String query = "EXEC stpInsertarMateria @Materia=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, materia.getMateria());
			ResultSet res = sql.executeQuery();
			while (res.next()) {
				this.materia = new Materias();
				this.materia.setCodMateria(res.getString(1));
				this.materia.setMateria(res.getString(2));

			}

			return this.materia;

		} catch (SQLException e) {
			ConexionFactory.closeConection();
			System.out.println("Error guardando la materia::" + e.getMessage().toString());
		} finally {

			ConexionFactory.closeConection();
		}

		return null;
	}

	@Override
	public Materias actualizar(Materias materia) {
		ConexionFactory.createdConection();
		String query = "EXEC stpActualizarMateria @COD=?, @Materia=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, materia.getCodMateria());
			sql.setString(2, materia.getMateria());
			ResultSet res = sql.executeQuery();
			while (res.next()) {
				this.materia = new Materias();
				this.materia.setCodMateria(res.getString(1));
				this.materia.setMateria(res.getString(2));

			}

			return this.materia;

		} catch (SQLException e) {
			ConexionFactory.closeConection();
			System.out.println("Error actualizando la materia::" + e.getMessage().toString());
			e.printStackTrace();
		} finally {

			ConexionFactory.closeConection();
		}

		return null;
	}

	@Override
	public boolean eliminar(Materias materia) {

		ConexionFactory.createdConection();
		String query = "EXEC stpEliminarMateria @COD=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, materia.getCodMateria());
			return sql.execute();

		} catch (SQLException e) {
			ConexionFactory.closeConection();
			System.out.println("Error actualizando la materia::" + e.getMessage().toString());
			e.printStackTrace();
		} finally {

			ConexionFactory.closeConection();
		}
		return false;
	}

	@Override
	public List<Materias> obtenerLista() {

		this.listaMaterias = new ArrayList<>();
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Materias WHERE Activo = 1";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			ResultSet result = sql.executeQuery();

			while (result.next()) {
				Materias m = new Materias();
				m.setCodMateria(result.getString("Cod_Materia"));
				m.setMateria(result.getString("Materia"));
				this.listaMaterias.add(m);
			}
			return this.listaMaterias;

		} catch (SQLException e) {
			ConexionFactory.closeConection();
			System.out.println("Error extrallendo los datos de materias::" + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			ConexionFactory.closeConection();
		}

		return null;
	}

	@Override
	public Materias obtenerMateriaPorId(String codMateria) {

		this.materia = new Materias();
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Materias WHERE Activo = 1 AND Cod_Materia = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, codMateria);
			ResultSet result = sql.executeQuery();

			if (result.getRow() > 0) {
				while (result.next()) {

					this.materia.setCodMateria(result.getString("Cod_Materia"));
					this.materia.setMateria(result.getString("Materia"));

				}
				return this.materia;
			} else {
				throw new NoDatosException("El Codigo no conside con ningun registro");
			}

		} catch (SQLException e) {
			ConexionFactory.closeConection();
			System.out.println("Error extrallendo los datos de materias::" + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			ConexionFactory.closeConection();
		}

		return null;
	}

	@Override
	public List<Materias> busquedaPorMateria(String parametro) {
		this.listaMaterias = new ArrayList<>();
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Materias WHERE Activo = 1 AND Materia like ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, "%" + parametro + "%");
			ResultSet result = sql.executeQuery();

			while (result.next()) {
				Materias m = new Materias();
				m.setCodMateria(result.getString("Cod_Materia"));
				m.setMateria(result.getString("Materia"));
				this.listaMaterias.add(m);
			}
			return this.listaMaterias;

		} catch (SQLException e) {
			ConexionFactory.closeConection();
			System.out.println("Error extrallendo los datos de materias::" + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			ConexionFactory.closeConection();
		}

		return null;
	}

}
