package com.gdunivo.es.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gdunivo.es.model.Clases;
import com.gdunivo.es.model.Materias;
import com.gdunivo.es.model.Personal;
import com.gdunivo.es.repository.ClasesRepository;
import com.gdunivo.es.repository.MateriasRepository;
import com.gdunivo.es.repository.PersonalRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@ManagedBean(name = "clases")
@ViewScoped
public class ClasesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Clases clase = new Clases();
	private Clases claseSelect = new Clases();
	private List<Clases> clases = new ArrayList<>();
	private ClasesRepository pClase = (ClasesRepository) RepositoryFactory.getRepository("Clases");
	private boolean claseS = false;

	private Personal personal = new Personal();
	private Personal personalSelect = new Personal();
	private List<Personal> personalList = new ArrayList<>();
	private PersonalRepository pPersonal = (PersonalRepository) RepositoryFactory.getRepository("Personal");
	private boolean personalS = false;

	private Materias materia = new Materias();
	private Materias materiaSelect = new Materias();
	private List<Materias> materias = new ArrayList<>();
	private MateriasRepository pMateria = (MateriasRepository) RepositoryFactory.getRepository("Materias");
	private boolean materiaS = false;

	public ClasesBean() {

	}

	public Clases getClase() {
		return clase;
	}

	public void setClase(Clases clase) {
		this.clase = clase;
	}

	public Clases getClaseSelect() {
		return claseSelect;
	}

	public void setClaseSelect(Clases claseSelect) {
		this.claseSelect = claseSelect;
	}

	public List<Clases> getClases() {
		return clases;
	}

	public void setClases(List<Clases> clases) {
		this.clases = clases;
	}

	public boolean isClaseS() {
		return claseS;
	}

	public void setClaseS(boolean claseS) {
		this.claseS = claseS;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Personal getPersonalSelect() {
		return personalSelect;
	}

	public void setPersonalSelect(Personal personalSelect) {
		this.personalSelect = personalSelect;
	}

	public List<Personal> getPersonalList() {
		return personalList;
	}

	public void setPersonalList(List<Personal> personalList) {
		this.personalList = personalList;
	}

	public boolean isPersonalS() {
		return personalS;
	}

	public void setPersonalS(boolean personalS) {
		this.personalS = personalS;
	}

	public Materias getMateria() {
		return materia;
	}

	public void setMateria(Materias materia) {
		this.materia = materia;
	}

	public Materias getMateriaSelect() {
		return materiaSelect;
	}

	public void setMateriaSelect(Materias materiaSelect) {
		this.materiaSelect = materiaSelect;
	}

	public List<Materias> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materias> materias) {
		this.materias = materias;
	}

	public boolean isMateriaS() {
		return materiaS;
	}

	public void setMateriaS(boolean materiaS) {
		this.materiaS = materiaS;
	}
	
	public void limpiar() {
		this.clase = new Clases();
	}
	

}
