package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Materias;


public interface MateriasRepository {

	public Materias guardar(Materias materia);
	public Materias actualizar(Materias materia);
	public boolean eliminar(Materias materia);
	public List<Materias> obtenerLista();
	public Materias obtenerMateriaPorId(String codMateria);
	public List<Materias> busquedaPorMateria(String parametro);
}
