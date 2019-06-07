package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Responsables;
import com.gdunivo.es.model.RolResponsable;
import com.gdunivo.es.model.Roles;

public interface RolResponsableRepository {

	public RolResponsable guardar(RolResponsable ra);

	public RolResponsable actualizar(RolResponsable ra);

	public boolean eliminar(RolResponsable ra);

	public List<RolResponsable> lista();

	public RolResponsable busquedaPoId(int id);

	public List<RolResponsable> listaPorRol(Roles r);

	public RolResponsable busquedaPorResponsable(Responsables a);
}
