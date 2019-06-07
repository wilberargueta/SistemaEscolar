package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.exception.RecursoEliminadoException;
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
			res.close();
			sql.close();
			return this.materia;

		} catch (SQLException e) {
			
			if (e.getSQLState() == "23000" && e.getErrorCode() == 2627) {
				throw new RecursoEliminadoException("El recurso fue eliminado, "
						+ "pero se encuentra en los resgistros. Desea reactivarlo?");
			}else {
				System.out.println("Error guardando la materia::" + e.getMessage().toString());
				e.printStackTrace();
			}
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
			res.close();
			sql.close();
			return this.materia;

		} catch (SQLException e) {
			System.out.println("Error actualizando la materia::" + e.getMessage().toString());
			e.printStackTrace();
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
			boolean value = sql.execute();
			sql.close();
			return value;

		} catch (SQLException e) {
			System.out.println("Error actualizando la materia::" + e.getMessage().toString());
			e.printStackTrace();
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
			result.close();
			sql.close();
			return this.listaMaterias;

		} catch (SQLException e) {
			System.out.println("Error extrallendo los datos de materias::" + e.getMessage().toString());
			e.printStackTrace();
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

			while (result.next()) {

				this.materia.setCodMateria(result.getString("Cod_Materia"));
				this.materia.setMateria(result.getString("Materia"));

			}
			result.close();
			sql.close();
			return this.materia;

		} catch (SQLException e) {
			System.out.println("Error extrallendo los datos de materias::" + e.getMessage().toString());
			e.printStackTrace();
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
			result.close();
			sql.close();
			return this.listaMaterias;

		} catch (SQLException e) {
			System.out.println("Error extrallendo los datos de materias::" + e.getMessage().toString());
			e.printStackTrace();
		}

		return null;
	}

}
