package com.gdunivo.es.repository;

import com.gdunivo.es.repository.imp.AlumnoClaseRepositoryImp;
import com.gdunivo.es.repository.imp.AlumnoResponsableRepositoryImp;
import com.gdunivo.es.repository.imp.AlumnosRepositoryImp;
import com.gdunivo.es.repository.imp.ClasesRespositoryImp;
import com.gdunivo.es.repository.imp.GeneralesRepositoryImp;
import com.gdunivo.es.repository.imp.MateriaRepositoryImp;
import com.gdunivo.es.repository.imp.PersonalRepositoryImp;
import com.gdunivo.es.repository.imp.ResponsableRepositoryImp;
import com.gdunivo.es.repository.imp.RolAlumnoRepositoryImp;
import com.gdunivo.es.repository.imp.RolPersonalRepositoryImp;
import com.gdunivo.es.repository.imp.RolResponsableRepositoryImp;
import com.gdunivo.es.repository.imp.RolesRepositoryImp;

public class RepositoryFactory {

	public static <T> Object getRepository(String name) {

		switch (name) {
		case "Alumnos":
			return new AlumnosRepositoryImp();
		case "Materias":
			return new MateriaRepositoryImp();
		case "Responsables":
			return new ResponsableRepositoryImp();
		case "Personal":
			return new PersonalRepositoryImp();
		case "Generales":
			return new GeneralesRepositoryImp();
		case "Clases":
			return new ClasesRespositoryImp();
		case "AlumnoClase":
			return new AlumnoClaseRepositoryImp();
		case "AlumnoResponsable":
			return new AlumnoResponsableRepositoryImp();
		case "Roles":
			return new RolesRepositoryImp();
		case "RolAlumno":
			return new RolAlumnoRepositoryImp();
		case "RolPersonal":
			return new RolPersonalRepositoryImp();
		case "RolResponsable":
			return new RolResponsableRepositoryImp();
		default:
			System.out.println("Repository no encontrado");
			return null;
		}

	}

}
