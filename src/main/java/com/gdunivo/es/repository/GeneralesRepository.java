package com.gdunivo.es.repository;

import com.gdunivo.es.model.Generales;

public interface GeneralesRepository {

	public Generales guardar(Generales g);

	public Generales modificar(Generales g);
	
	public Generales obtenerGenerales();

}
