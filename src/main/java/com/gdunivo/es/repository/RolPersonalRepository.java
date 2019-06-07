package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Personal;
import com.gdunivo.es.model.RolPersonal;
import com.gdunivo.es.model.Roles;

public interface RolPersonalRepository {

	public RolPersonal guardar(RolPersonal ra);

	public RolPersonal actualizar(RolPersonal ra);

	public boolean eliminar(RolPersonal ra);

	public List<RolPersonal> lista();

	public RolPersonal busquedaPoId(int id);

	public List<RolPersonal> listaPorRol(Roles r);

	public RolPersonal busquedaPorPersonal(Personal a);
}
