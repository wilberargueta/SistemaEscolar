package com.gdunivo.es.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;

import com.gdunivo.es.model.AlumnoClase;
import com.gdunivo.es.model.AlumnoResponsable;
import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.Responsables;
import com.gdunivo.es.repository.AlumnoClaseRepository;
import com.gdunivo.es.repository.AlumnoResponsableRepository;
import com.gdunivo.es.repository.AlumnosRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@ManagedBean(name = "reporte")
@SessionScoped
public class ReporteBean implements Serializable{


	private static final long serialVersionUID = 1647791952526265913L;
	
	private List<AlumnoResponsable> listaA;
	private Alumnos a = new Alumnos();
	private AlumnoResponsableRepository pAResponsable = (AlumnoResponsableRepository) RepositoryFactory
			.getRepository("AlumnoResponsable");
	private AlumnosRepository pAlumno = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
	private AlumnoClaseRepository pAClase = (AlumnoClaseRepository) RepositoryFactory.getRepository("AlumnoClase");
	private AlumnoClase ac = new AlumnoClase();
	private AlumnoResponsable ar = new AlumnoResponsable();
	private List<AlumnoClase> listaAC;
	private boolean tipo = false;
	private boolean selecionado = false;

	public ReporteBean() {
		String rol = SessionUtils.getRol();
		String user = SessionUtils.getUsuario();
		if (rol.equals("alum")) {
			this.a = this.pAlumno.buscarPorCodigo(user);
			this.listaAC = this.pAClase.clasesPorAlumno(a);
			this.tipo = false;
		} else {
			Responsables r = new Responsables();
			r.setCodResponsable(user);
			this.listaA = this.pAResponsable.listadoPorResponsable(r);
			this.tipo = true;
		}
	}

	public List<AlumnoResponsable> getListaA() {
		return listaA;
	}

	public void setListaA(List<AlumnoResponsable> listaA) {
		this.listaA = listaA;
	}

	public AlumnoClase getAc() {
		return ac;
	}

	public void setAc(AlumnoClase ac) {
		this.ac = ac;
	}

	public List<AlumnoClase> getListaAC() {
		return listaAC;
	}

	public void setListaAC(List<AlumnoClase> listaAC) {
		this.listaAC = listaAC;
	}

	public Alumnos getA() {
		return a;
	}

	public void setA(Alumnos a) {
		this.a = a;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public AlumnoResponsable getAr() {
		return ar;
	}

	public void setAr(AlumnoResponsable ar) {
		this.ar = ar;
	}

	public void seleccionarAlumno(SelectEvent event) {
		AlumnoResponsable aar = (AlumnoResponsable) event.getObject();
		this.a = aar.getAlumno();
		this.listaAC = this.pAClase.clasesPorAlumno(a);
		this.selecionado = true;
		System.out.println("sdsd"+this.listaAC);
	}

	public void actualizar() {
		this.listaAC = this.pAClase.clasesPorAlumno(a);
		System.out.println(this.listaAC);
		this.selecionado = true;
	}

}
