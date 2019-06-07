package com.gdunivo.es.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.gdunivo.es.model.Generales;
import com.gdunivo.es.repository.GeneralesRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@ManagedBean(name = "generalesBean", eager = true)
@SessionScoped
public class GeneralesBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Generales generales = new Generales();
	private GeneralesRepository persis = (GeneralesRepository) RepositoryFactory.getRepository("Generales");

	public GeneralesBean() {
		this.extraer();
	}

	public Generales getGenerales() {
		return generales;
	}

	public void setGenerales(Generales generales) {
		this.generales = generales;
	}

	public void extraer() {
		this.generales = this.persis.obtenerGenerales();
	}

}
