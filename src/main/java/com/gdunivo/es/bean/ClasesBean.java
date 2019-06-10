package com.gdunivo.es.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.gdunivo.es.model.AlumnoClase;
import com.gdunivo.es.model.Clases;
import com.gdunivo.es.model.Materias;
import com.gdunivo.es.model.Personal;
import com.gdunivo.es.repository.AlumnoClaseRepository;
import com.gdunivo.es.repository.ClasesRepository;
import com.gdunivo.es.repository.MateriasRepository;
import com.gdunivo.es.repository.PersonalRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@ManagedBean(name = "clases")
@ViewScoped
public class ClasesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Clases clase = new Clases();
	private List<Clases> clases = new ArrayList<>();
	private ClasesRepository pClase = (ClasesRepository) RepositoryFactory.getRepository("Clases");
	private boolean claseS = false;

	private Personal personal = new Personal();
	private PersonalRepository pPersonal = (PersonalRepository) RepositoryFactory.getRepository("Personal");
	private MateriasRepository pMateria = (MateriasRepository) RepositoryFactory.getRepository("Materias");
	private AlumnoClaseRepository pAClase = (AlumnoClaseRepository) RepositoryFactory.getRepository("AlumnoClase");
	private List<AlumnoClase> listaAClases = new ArrayList<>();
	private Date hora1, hora2;

	private String[] dias;

	public ClasesBean() {
		this.actualizarTabla();
		// this.dias = this.clase.getDia().split(",");
	}

	public Clases getClase() {
		return clase;
	}

	public void setClase(Clases clase) {
		this.clase = clase;
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

	public Date getHora1() {
		return hora1;
	}

	public void setHora1(Date hora1) {
		this.hora1 = hora1;
	}

	public Date getHora2() {
		return hora2;
	}

	public void setHora2(Date hora2) {
		this.hora2 = hora2;
	}

	public String[] getDias() {
		return dias;
	}

	public void setDias(String[] dias) {
		this.dias = dias;
	}

	public List<AlumnoClase> getListaAClases() {
		return listaAClases;
	}

	public void setListaAClases(List<AlumnoClase> listaAClases) {
		this.listaAClases = listaAClases;
	}

	public void splitHora(String hora) {
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
		String horas[] = hora.split("-");

		try {
			this.hora2 = formato.parse(horas[1]);
			this.hora1 = formato.parse(horas[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void seleccionarClase(SelectEvent event) {

		this.clase = (Clases) event.getObject();
		this.claseS = true;
		this.dias = this.clase.getDia().split(",");
		splitHora(this.clase.getHora());
		this.personal = this.clase.getPersonal();
	}

	public void actualizarTabla() {
		this.clases = this.pClase.listadoClases();
	}

	public void limpiar() {

		this.clase = new Clases();
		this.claseS = false;
		this.hora1 = null;
		this.hora2 = null;
		this.dias = null;

	}

	public List<Personal> busquedaPersonal(String query) {

		return this.pPersonal.buscarPorNombre(query);
	}

	public List<Materias> busquedaMateria(String query) {

		return this.pMateria.busquedaPorMateria(query);
	}

	public void convertArrayToString() {

		List<String> diasL = Arrays.asList(this.dias);

		if (this.clase.getDia() != null) {

			this.clase.setDia(null);
			diasL.forEach(d1 -> {
				String d = this.clase.getDia();
				if (d == null) {

					this.clase.setDia(d1);
				} else {
					this.clase.setDia(d + "," + d1);
				}

			});

		} else {

			diasL.forEach(d1 -> {
				String d = this.clase.getDia();
				if (d == null) {

					this.clase.setDia(d1);
				} else {
					this.clase.setDia(d + "," + d1);
				}

			});
		}

	}

	public void convertHorToString() {
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
		String h1 = formato.format(hora1);
		String h2 = formato.format(hora2);

		this.clase.setHora(h1 + "-" + h2);
	}

	public void guardar() {
		this.convertArrayToString();
		this.convertHorToString();
		this.clase = this.pClase.guardar(this.clase);
		this.actualizarTabla();
		this.limpiar();

	}

	public void actualizar() {
		this.convertArrayToString();
		this.convertHorToString();
		if (this.clase.getPersonal() == null)
			this.clase.setPersonal(this.personal);

		this.clase = this.pClase.actualizar(this.clase);
		this.actualizarTabla();
		this.limpiar();
	}

	public void eliminar() {
		this.pClase.eliminar(this.clase);
		this.actualizarTabla();
		this.limpiar();
	}

	public void actualizarAlumnoClase() {
		this.listaAClases = this.pAClase.listaAlumnosPorClase(clase);
	}

}
