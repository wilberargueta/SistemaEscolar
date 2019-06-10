package com.gdunivo.es.conver;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.gdunivo.es.model.Materias;
import com.gdunivo.es.repository.MateriasRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@FacesConverter("materiaConver")
public class MateriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		MateriasRepository persis = (MateriasRepository) RepositoryFactory.getRepository("Materias");
		if (value != null && value.trim().length() > 0) {
			
			return persis.busquedaPorMateria(value).get(0);
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return String.valueOf(((Materias) value).getMateria());
		} else {
			return null;
		}

	}

}
