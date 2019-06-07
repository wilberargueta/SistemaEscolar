package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Responsables;

public interface ResponsableRepository {

	public Responsables guardar(Responsables resp);

	public Responsables actualizar(Responsables resp);

	public boolean eliminar(Responsables resp);

	public List<Responsables> obtenerListado();

	public Responsables buscarPorCodigo(String cod);

	public List<Responsables> buscarPorNombre(String nombre);

	public List<Responsables> buscarPorApellido(String apellido);

}
