package com.gdunivo.es.conver;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.gdunivo.es.repository.PersonalRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@FacesConverter("personalConver")
public class PersonalConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PersonalRepository persis = (PersonalRepository) RepositoryFactory.getRepository("Personal");

		if (value != null && value.trim().length() > 0) {

			return persis.buscarPorCodigo(value);

		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return String.valueOf(value);
		} else {
			return null;
		}
	}

}
