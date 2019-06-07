package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.Clases;
import com.gdunivo.es.model.Materias;
import com.gdunivo.es.model.Personal;
import com.gdunivo.es.repository.ClasesRepository;
import com.gdunivo.es.repository.MateriasRepository;
import com.gdunivo.es.repository.PersonalRepository;
import com.gdunivo.es.repository.RepositoryFactory;

public class ClasesRespositoryImp implements ClasesRepository {

	private Clases clase;
	private List<Clases> listadoClases;
	private PersonalRepository personal = (PersonalRepository) RepositoryFactory.getRepository("Personal");
	private MateriasRepository materia = (MateriasRepository) RepositoryFactory.getRepository("Materias");

	@Override
	public Clases guardar(Clases c) {
		ConexionFactory.createdConection();
		String query = "EXEC stpInsertarClase @Cod_Personal=?,@Cod_Materia=?,@Dia=?,@Hora=?,@Seccion=?, @Turno=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setString(1, c.getPersonal().getCodPersonal());
			sql.setString(2, c.getMateria().getCodMateria());
			sql.setString(3, c.getDia());
			sql.setString(4, c.getHora());
			sql.setString(5, c.getSeccion());
			sql.setString(6, c.getTurno());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.clase = new Clases();
				this.clase.setIdClase(result.getInt("Id_Clases"));
				this.clase.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.clase.setMateria(this.materia.obtenerMateriaPorId(result.getString("Cod_Materia")));
				this.clase.setDia(result.getString("Dia"));
				this.clase.setHora(result.getString("Hora"));
				this.clase.setSeccion(result.getString("Seccion"));
				this.clase.setTurno(result.getString("Turno"));

			}
			result.close();
			sql.close();
			return this.clase;

		} catch (Exception e) {
			System.out.println("Error guardando clase::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Clases actualizar(Clases c) {
		ConexionFactory.createdConection();
		String query = "EXEC stpActualizarClase @Id_Clases=?,@Cod_Personal=?,@Cod_Materia=?,@Dia=?,@Hora=?,@Seccion=?, @Turno=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);

		try {
			sql.setInt(1, c.getIdClase());
			sql.setString(2, c.getPersonal().getCodPersonal());
			sql.setString(3, c.getMateria().getCodMateria());
			sql.setString(4, c.getDia());
			sql.setString(5, c.getHora());
			sql.setString(6, c.getSeccion());
			sql.setString(7, c.getTurno());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.clase = new Clases();
				this.clase.setIdClase(result.getInt("Id_Clases"));
				this.clase.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.clase.setMateria(this.materia.obtenerMateriaPorId(result.getString("Cod_Materia")));
				this.clase.setDia(result.getString("Dia"));
				this.clase.setHora(result.getString("Hora"));
				this.clase.setSeccion(result.getString("Seccion"));
				this.clase.setTurno(result.getString("Turno"));

			}
			result.close();
			sql.close();
			return this.clase;

		} catch (Exception e) {
			System.out.println("Error actualizando clase::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminar(Clases c) {
		ConexionFactory.createdConection();
		String query = "DELETE FROM Clases WHERE Id_Clases = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, c.getIdClase());
			boolean value = sql.execute();
			sql.close();
			return value;

		} catch (Exception e) {
			System.out.println("Error eliminando clase::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Clases> listadoClases() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Clases";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			ResultSet result = sql.executeQuery();
			this.listadoClases = new ArrayList<>();
			while (result.next()) {
				this.clase = new Clases();
				this.clase.setIdClase(result.getInt("Id_Clases"));
				this.clase.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.clase.setMateria(this.materia.obtenerMateriaPorId(result.getString("Cod_Materia")));
				this.clase.setDia(result.getString("Dia"));
				this.clase.setHora(result.getString("Hora"));
				this.clase.setSeccion(result.getString("Seccion"));
				this.clase.setTurno(result.getString("Turno"));
				this.listadoClases.add(this.clase);
			}

			result.close();
			sql.close();
			return this.listadoClases;

		} catch (Exception e) {
			System.out.println("Error obteniendo listado de clases::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Clases obtenerPorId(int id) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Clases WHERE Id_Clases = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, id);
			ResultSet result = sql.executeQuery();

			while (result.next()) {
				this.clase = new Clases();
				this.clase.setIdClase(result.getInt("Id_Clases"));
				this.clase.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.clase.setMateria(this.materia.obtenerMateriaPorId(result.getString("Cod_Materia")));
				this.clase.setDia(result.getString("Dia"));
				this.clase.setHora(result.getString("Hora"));
				this.clase.setSeccion(result.getString("Seccion"));
				this.clase.setTurno(result.getString("Turno"));

			}

			result.close();
			sql.close();
			return this.clase;

		} catch (Exception e) {
			System.out.println("Error obteniendo listado de clases::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Clases> listadoClasesPorPersonal(Personal p) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Clases WHERE Cod_Personal = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, p.getCodPersonal());
			ResultSet result = sql.executeQuery();
			this.listadoClases = new ArrayList<>();
			while (result.next()) {
				this.clase = new Clases();
				this.clase.setIdClase(result.getInt("Id_Clases"));
				this.clase.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.clase.setMateria(this.materia.obtenerMateriaPorId(result.getString("Cod_Materia")));
				this.clase.setDia(result.getString("Dia"));
				this.clase.setHora(result.getString("Hora"));
				this.clase.setSeccion(result.getString("Seccion"));
				this.clase.setTurno(result.getString("Turno"));
				this.listadoClases.add(this.clase);
			}

			result.close();
			sql.close();
			return this.listadoClases;

		} catch (Exception e) {
			System.out.println("Error obteniendo listado de clases por personal::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Clases> listadoClasesPorMateria(Materias m) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Clases WHERE Cod_Materia = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, m.getCodMateria());
			ResultSet result = sql.executeQuery();
			this.listadoClases = new ArrayList<>();
			while (result.next()) {
				this.clase = new Clases();
				this.clase.setIdClase(result.getInt("Id_Clases"));
				this.clase.setPersonal(this.personal.buscarPorCodigo(result.getString("Cod_Personal")));
				this.clase.setMateria(this.materia.obtenerMateriaPorId(result.getString("Cod_Materia")));
				this.clase.setDia(result.getString("Dia"));
				this.clase.setHora(result.getString("Hora"));
				this.clase.setSeccion(result.getString("Seccion"));
				this.clase.setTurno(result.getString("Turno"));
				this.listadoClases.add(this.clase);
			}

			result.close();
			sql.close();
			return this.listadoClases;

		} catch (Exception e) {
			System.out.println("Error obteniendo listado de clases por materia::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
