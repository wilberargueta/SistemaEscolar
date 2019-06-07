package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.RolAlumno;
import com.gdunivo.es.model.Roles;

public interface RolAlumnoRepository {

	public RolAlumno guardar(RolAlumno ra);

	public RolAlumno actualizar(RolAlumno ra);

	public boolean eliminar(RolAlumno ra);

	public List<RolAlumno> lista();

	public RolAlumno busquedaPoId(int id);

	public List<RolAlumno> listaPorRol(Roles r);

	public RolAlumno busquedaPorAlumno(Alumnos a);

}
