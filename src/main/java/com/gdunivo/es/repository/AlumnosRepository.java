package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Alumnos;

public interface AlumnosRepository {

	public Alumnos guardar(Alumnos alumno);

	public Alumnos actualizar(Alumnos alumno);

	public boolean eliminar(Alumnos alumno);

	public List<Alumnos> obtenerLista();

	public Alumnos buscarPorCodigo(String codAlumno);

	public List<Alumnos> busquedaPorNombre(String nombre);
	
	public List<Alumnos> busquedaPorApellido(String apelldido);


}
