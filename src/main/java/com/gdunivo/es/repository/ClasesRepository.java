package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Clases;
import com.gdunivo.es.model.Materias;
import com.gdunivo.es.model.Personal;

public interface ClasesRepository {

	public Clases guardar(Clases c);

	public Clases actualizar(Clases c);

	public boolean eliminar(Clases c);

	public List<Clases> listadoClases();

	public Clases obtenerPorId(int id);

	public List<Clases> listadoClasesPorPersonal(Personal p);

	public List<Clases> listadoClasesPorMateria(Materias m);

}
