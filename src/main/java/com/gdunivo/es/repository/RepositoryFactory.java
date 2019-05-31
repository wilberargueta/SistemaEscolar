package com.gdunivo.es.repository;

import com.gdunivo.es.repository.imp.AlumnosRepositoryImp;
import com.gdunivo.es.repository.imp.MateriaRepositoryImp;

public class RepositoryFactory {
	
	
	public static <T> Object getRepository(String name){
		
		switch (name) {
		case "Alumnos":
			return new AlumnosRepositoryImp();
		case "Materias":
			return new MateriaRepositoryImp();
		default:
			System.out.println("Repository no encontrado");
			return null;
		}
		
	}

}
