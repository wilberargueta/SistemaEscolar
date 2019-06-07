package com.gdunivo.es.repository.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdunivo.es.config.ConexionFactory;
import com.gdunivo.es.model.AlumnoResponsable;
import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.Responsables;
import com.gdunivo.es.repository.AlumnoResponsableRepository;
import com.gdunivo.es.repository.AlumnosRepository;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.ResponsableRepository;

public class AlumnoResponsableRepositoryImp implements AlumnoResponsableRepository {
	private AlumnoResponsable aRespon;
	private List<AlumnoResponsable> aResponLista;
	private AlumnosRepository alumno = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
	private ResponsableRepository responsable = (ResponsableRepository) RepositoryFactory.getRepository("Responsables");

	@Override
	public AlumnoResponsable guardar(AlumnoResponsable ar) {
		ConexionFactory.createdConection();
		String query = "INSERT INTO Alumno_Responsable(Cod_Alumno, Cod_Responsable)VALUES(?,?)"
				+ "SELECT * FROM Alumno_Responsable "
				+ "WHERE Id_Alumno_Responsable = (SELECT MAX(Id_Alumno_Responsable) FROM Alumno_Responsable)";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, ar.getAlumno().getCodAlumno());
			sql.setString(2, ar.getResponsable().getCodResponsable());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.aRespon = new AlumnoResponsable();
				this.aRespon.setIdAlumnoResponsable(result.getInt("Id_Alumno_Responsable"));
				this.aRespon.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aRespon.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));

			}
			result.close();
			sql.close();
			return this.aRespon;

		} catch (Exception e) {
			System.out.println("Error guardando alumno responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AlumnoResponsable actualizar(AlumnoResponsable ar) {
		ConexionFactory.createdConection();
		String query = "UPDATE Alumno_Responsable SET Cod_Alumno =?, Cod_Responsable =? WHERE Id_Alumno_Responsable =?;"
				+ "SELECT * FROM Alumno_Responsable WHERE Id_Alumno_Responsable=?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, ar.getAlumno().getCodAlumno());
			sql.setString(2, ar.getResponsable().getCodResponsable());
			sql.setInt(3, ar.getIdAlumnoResponsable());
			sql.setInt(4, ar.getIdAlumnoResponsable());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				this.aRespon = new AlumnoResponsable();
				this.aRespon.setIdAlumnoResponsable(result.getInt("Id_Alumno_Responsable"));
				this.aRespon.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aRespon.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));

			}
			result.close();
			sql.close();
			return this.aRespon;

		} catch (Exception e) {
			System.out.println("Error actualizando alumno responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminar(AlumnoResponsable ar) {
		ConexionFactory.createdConection();
		String query = "DELETE FROM Alumno_Responsable WHERE Id_Alumno_Responsable = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setInt(1, ar.getIdAlumnoResponsable());
			boolean value = sql.execute();
			sql.close();
			return value;

		} catch (Exception e) {
			System.out.println("Error eliminando alumno responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AlumnoResponsable> listado() {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Alumno_Responsable";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			ResultSet result = sql.executeQuery();
			this.aResponLista = new ArrayList<>();
			while (result.next()) {
				this.aRespon = new AlumnoResponsable();
				this.aRespon.setIdAlumnoResponsable(result.getInt("Id_Alumno_Responsable"));
				this.aRespon.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aRespon.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));
				this.aResponLista.add(aRespon);

			}
			result.close();
			sql.close();
			return this.aResponLista;
		} catch (Exception e) {
			System.out.println("Error obteniendo alumno responsable lista::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AlumnoResponsable> listadoPorResponsable(Responsables r) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Alumno_Responsable WHERE Cod_Responsable = ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, r.getCodResponsable());
			ResultSet result = sql.executeQuery();
			this.aResponLista = new ArrayList<>();
			while (result.next()) {
				this.aRespon = new AlumnoResponsable();
				this.aRespon.setIdAlumnoResponsable(result.getInt("Id_Alumno_Responsable"));
				this.aRespon.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aRespon.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));
				this.aResponLista.add(aRespon);

			}
			result.close();
			sql.close();
			return this.aResponLista;
		} catch (Exception e) {
			System.out.println("Error obteniendo alumno responsable lista por responsable::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AlumnoResponsable ResponsablePorAlumno(Alumnos a) {
		ConexionFactory.createdConection();
		String query = "SELECT * FROM Alumno_Responsable WHERE Cod_Alumno= ?";
		PreparedStatement sql = ConexionFactory.getStatement(query);
		try {
			sql.setString(1, a.getCodAlumno());
			ResultSet result = sql.executeQuery();

			while (result.next()) {
				this.aRespon = new AlumnoResponsable();
				this.aRespon.setIdAlumnoResponsable(result.getInt("Id_Alumno_Responsable"));
				this.aRespon.setAlumno(this.alumno.buscarPorCodigo(result.getString("Cod_Alumno")));
				this.aRespon.setResponsable(this.responsable.buscarPorCodigo(result.getString("Cod_Responsable")));

			}
			result.close();
			sql.close();
			return this.aRespon;
		} catch (Exception e) {
			System.out.println("Error obteniendo alumno responsable  por alumno::" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
