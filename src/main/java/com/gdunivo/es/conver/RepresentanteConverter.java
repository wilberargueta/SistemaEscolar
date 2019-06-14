package com.gdunivo.es.conver;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.ResponsableRepository;

@FacesConverter(value = "RepreConvert")
public class RepresentanteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ResponsableRepository persis = (ResponsableRepository) RepositoryFactory.getRepository("Responsables");
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
