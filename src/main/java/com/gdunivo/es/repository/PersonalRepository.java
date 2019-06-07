package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Personal;

public interface PersonalRepository {

	public Personal guardar(Personal resp);

	public Personal actualizar(Personal resp);

	public boolean eliminar(Personal resp);

	public List<Personal> obtenerListado();

	public Personal buscarPorCodigo(String cod);

	public List<Personal> buscarPorNombre(String nombre);

	public List<Personal> buscarPorApellido(String apellido);

}
