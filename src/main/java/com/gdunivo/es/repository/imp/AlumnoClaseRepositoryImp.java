package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.AlumnoClase;
import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.Clases;
import com.gdunivo.es.repository.AlumnoClaseRepository;
import com.gdunivo.es.repository.AlumnosRepository;
import com.gdunivo.es.repository.ClasesRepository;
import com.gdunivo.es.repository.RepositoryFactory;

public class AlumnoClaseRepositoryImp implements AlumnoClaseRepository {

	private AlumnoClase aclase;
	private List<AlumnoClase> listaAClase;
	private AlumnosRepository alumno = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
	private ClasesRepository clase = (ClasesRepository) RepositoryFactory.getRepository("Clases");

	@Override
	public AlumnoClase guardar(AlumnoClase ac) {
		ConexionFactory.createdConection();
		String query = "INSERT INTO Alumno_Clase(Cod_Alumno, Id_Clases) VALUES(?,?)" + "SELECT * FROM Alumno_Clase "
				+ "WHERE Id_Alumno_Clase = (SELECT MAX(Id_Alumno_Clase) FROM Alumno_Clase)";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setString(1, ac.getAlumno().getCodAlumno());
			sql.setInt(2, ac.getClase().getIdClase());

			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.aclase = new AlumnoClase();
				this.aclase.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aclase.setClase(this.clase.obtenerPorId(result.getInt("Id_Clases")));
			}

			result.close();
			sql.close();
			return this.aclase;

		} catch (Exception e) {
			System.out.println("Error Guardando AlumnoClases::" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public AlumnoClase actualizar(AlumnoClase ac) {
		ConexionFactory.createdConection();
		String query = "UPDATE Alumno_Clase SET Id_Clases=?,Cod_Alumno=?,Nota_PP=?,Nota_SP=?,Nota_TP=?,Nota_Final=?"
				+ " WHERE Id_Alumno_Clase =?; SELECT * FROM Alumno_Clase WHERE Id_Alumno_Clase =?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, ac.getClase().getIdClase());
			sql.setString(2, ac.getAlumno().getCodAlumno());
			sql.setFloat(3, ac.getNotaPP());
			sql.setFloat(4, ac.getNotaSP());
			sql.setFloat(5, ac.getNotaTP());
			sql.setFloat(6, ac.getNotaFinal());
			sql.setInt(7, ac.getIdAlumnoClase());
			sql.setInt(8, ac.getIdAlumnoClase());

			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.aclase = new AlumnoClase();
				this.aclase.setIdAlumnoClase(result.getInt("Id_Alumno_Clase"));
				this.aclase.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aclase.setClase(this.clase.obtenerPorId(result.getInt("Id_Clases")));
				this.aclase.setNotaPP(result.getFloat("Nota_PP"));
				this.aclase.setNotaSP(result.getFloat("Nota_SP"));
				this.aclase.setNotaTP(result.getFloat("Nota_TP"));
				this.aclase.setNotaFinal(result.getFloat("Nota_Final"));

			}

			result.close();
			sql.close();
			return this.aclase;

		} catch (Exception e) {
			System.out.println("Error actualizando AlumnoClases::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminar(AlumnoClase ac) {
		ConexionFactory.createdConection();
		String query = "DELETE FROM Alumno_Clase WHERE Id_Alumno_Clase = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setFloat(1, ac.getIdAlumnoClase());

			boolean value = sql.execute();
			sql.close();
			return value;

		} catch (Exception e) {
			System.out.println("Error eliminando AlumnoClases::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AlumnoClase> listaAlumnosPorClase(Clases c) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Alumno_Clase WHERE Id_Clases = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, c.getIdClase());
			ResultSet result = sql.executeQuery();
			this.listaAClase = new ArrayList<>();
			while (result.next()) {
				this.aclase = new AlumnoClase();
				this.aclase.setIdAlumnoClase(result.getInt("Id_Alumno_Clase"));
				this.aclase.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aclase.setClase(this.clase.obtenerPorId(result.getInt("Id_Clases")));
				this.aclase.setNotaPP(result.getFloat("Nota_PP"));
				this.aclase.setNotaSP(result.getFloat("Nota_SP"));
				this.aclase.setNotaTP(result.getFloat("Nota_TP"));
				this.aclase.setNotaFinal(result.getFloat("Nota_Final"));
				this.listaAClase.add(this.aclase);

			}

			result.close();
			sql.close();
			return this.listaAClase;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista AlumnoClases::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AlumnoClase> clasesPorAlumno(Alumnos a) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Alumno_Clase WHERE Cod_Alumno = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, a.getCodAlumno());
			ResultSet result = sql.executeQuery();
			this.listaAClase = new ArrayList<>();
			while (result.next()) {
				this.aclase = new AlumnoClase();
				this.aclase.setIdAlumnoClase(result.getInt("Id_Alumno_Clase"));
				this.aclase.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aclase.setClase(this.clase.obtenerPorId(result.getInt("Id_Clases")));
				this.aclase.setNotaPP(result.getFloat("Nota_PP"));
				this.aclase.setNotaSP(result.getFloat("Nota_SP"));
				this.aclase.setNotaTP(result.getFloat("Nota_TP"));
				this.aclase.setNotaFinal(result.getFloat("Nota_Final"));
				this.listaAClase.add(this.aclase);

			}

			result.close();
			sql.close();
			return this.listaAClase;

		} catch (Exception e) {
			System.out.println("Error obteniendo lista AlumnoClases::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AlumnoClase primerNota(AlumnoClase pNota) {
		ConexionFactory.createdConection();
		String query = "UPDATE Alumno_Clase SET Nota_PP = ? , Nota_Final = ? WHERE Id_Alumno_Clase = ? "
				+ "SELECT * FROM Alumno_Clase WHERE Id_Alumno_Clase = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setFloat(1, pNota.getNotaPP());
			sql.setFloat(2, pNota.getNotaFinal());
			sql.setInt(3, pNota.getIdAlumnoClase());
			sql.setInt(4, pNota.getIdAlumnoClase());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.aclase = new AlumnoClase();
				this.aclase.setIdAlumnoClase(result.getInt("Id_Alumno_Clase"));
				this.aclase.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aclase.setClase(this.clase.obtenerPorId(result.getInt("Id_Clases")));
				this.aclase.setNotaPP(result.getFloat("Nota_PP"));
				this.aclase.setNotaSP(result.getFloat("Nota_SP"));
				this.aclase.setNotaTP(result.getFloat("Nota_TP"));
				this.aclase.setNotaFinal(result.getFloat("Nota_Final"));
			}

			result.close();
			sql.close();
			return this.aclase;

		} catch (Exception e) {
			System.out.println("Error agregando Primer nota az AlumnoClases::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AlumnoClase segundaNota(AlumnoClase sNota) {
		ConexionFactory.createdConection();
		String query = "UPDATE Alumno_Clase SET Nota_SP = ? , Nota_Final = ? WHERE Id_Alumno_Clase = ? "
				+ "SELECT * FROM Alumno_Clase WHERE Id_Alumno_Clase = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setFloat(1, sNota.getNotaSP());
			sql.setFloat(2, sNota.getNotaFinal());
			sql.setInt(3, sNota.getIdAlumnoClase());
			sql.setInt(4, sNota.getIdAlumnoClase());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.aclase = new AlumnoClase();
				this.aclase.setIdAlumnoClase(result.getInt("Id_Alumno_Clase"));
				this.aclase.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aclase.setClase(this.clase.obtenerPorId(result.getInt("Id_Clases")));
				this.aclase.setNotaPP(result.getFloat("Nota_PP"));
				this.aclase.setNotaSP(result.getFloat("Nota_SP"));
				this.aclase.setNotaTP(result.getFloat("Nota_TP"));
				this.aclase.setNotaFinal(result.getFloat("Nota_Final"));
			}

			result.close();
			sql.close();
			return this.aclase;

		} catch (Exception e) {
			System.out.println("Error agregando segunda nota a AlumnoClases::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AlumnoClase tercerNota(AlumnoClase tNota) {
		ConexionFactory.createdConection();
		String query = "UPDATE Alumno_Clase SET Nota_TP= ? , Nota_Final = ? WHERE Id_Alumno_Clase = ? "
				+ "SELECT * FROM Alumno_Clase WHERE Id_Alumno_Clase = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {

			sql.setFloat(1, tNota.getNotaTP());
			sql.setFloat(2, tNota.getNotaFinal());
			sql.setInt(3, tNota.getIdAlumnoClase());
			sql.setInt(4, tNota.getIdAlumnoClase());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.aclase = new AlumnoClase();
				this.aclase.setIdAlumnoClase(result.getInt("Id_Alumno_Clase"));
				this.aclase.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aclase.setClase(this.clase.obtenerPorId(result.getInt("Id_Clases")));
				this.aclase.setNotaPP(result.getFloat("Nota_PP"));
				this.aclase.setNotaSP(result.getFloat("Nota_SP"));
				this.aclase.setNotaTP(result.getFloat("Nota_TP"));
				this.aclase.setNotaFinal(result.getFloat("Nota_Final"));
			}

			result.close();
			sql.close();
			return this.aclase;

		} catch (Exception e) {
			System.out.println("Error agregando tercer nota a AlumnoClases::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
