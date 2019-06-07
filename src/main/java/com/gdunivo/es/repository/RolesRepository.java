package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Roles;

public interface RolesRepository {

	public List<Roles> listaRoles();

	public Roles rolePorId(int id);

}
