package com.gdunivo.es.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.Personal;
import com.gdunivo.es.model.Responsables;
import com.gdunivo.es.model.RolAlumno;
import com.gdunivo.es.model.RolPersonal;
import com.gdunivo.es.model.RolResponsable;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.RolAlumnoRepository;
import com.gdunivo.es.repository.RolPersonalRepository;
import com.gdunivo.es.repository.RolResponsableRepository;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {

	private String rol;
	private String user;
	private String pass;
	private String nombre;
	private String msg;
	private RolPersonalRepository pPersonal = (RolPersonalRepository) RepositoryFactory.getRepository("RolPersonal");
	private RolAlumnoRepository pAlumno = (RolAlumnoRepository) RepositoryFactory.getRepository("RolAlumno");
	private RolResponsableRepository pResponsable = (RolResponsableRepository) RepositoryFactory
			.getRepository("RolResponsable");

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String ingresar() {

		switch (this.rol) {
		case "admin":
			Personal p = new Personal();
			p.setCodPersonal(user);
			RolPersonal rp = this.pPersonal.busquedaPorPersonal(p);
			if (rp != null) {
				if (rp.getContraseña().equals(pass) && rp.getRol().getId_Role() == 1) {
					this.msg = "";
					this.nombre = rp.getPersonal().getNombre() + " " + rp.getPersonal().getApellido();
					HttpSession session = SessionUtils.getSession();
					session.setAttribute("user", user);
					session.setAttribute("rol", rol);
				
					return "home.xhtml";
				} else {
					this.msg = "Usuario o contraseña incorrectos";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario o contraseña incorrectos", "Por favor ingresa un usuario o contraseña correcta"));
					return "index.xhtml";
				}

			} else {
				this.msg = "Usuario o contraseña incorrectos";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Usuario o contraseña incorrectos", "Por favor ingresa un usuario o contraseña correcta"));
				return "index.xhtml";
			}
		case "perso":
			Personal p2 = new Personal();
			p2.setCodPersonal(user);
			RolPersonal rp2 = this.pPersonal.busquedaPorPersonal(p2);
			if (rp2 != null) {
				if (rp2.getContraseña().equals(pass) && rp2.getRol().getId_Role() == 2) {
					this.msg = "";
					this.nombre = rp2.getPersonal().getNombre() + " " + rp2.getPersonal().getApellido();
					HttpSession session = SessionUtils.getSession();
					session.setAttribute("user", user);
					session.setAttribute("rol", rol);
					return "home.xhtml";
				} else {
					this.msg = "Usuario o contraseña incorrectos";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario o contraseña incorrectos", "Por favor ingresa un usuario o contraseña correcta"));
					return "index.xhtml";
				}

			} else {
				this.msg = "Usuario o contraseña incorrectos";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Usuario o contraseña incorrectos", "Por favor ingresa un usuario o contraseña correcta"));
				return "index.xhtml";
			}

		case "repre":
			Responsables r = new Responsables();
			r.setCodResponsable(user);
			RolResponsable rr = this.pResponsable.busquedaPorResponsable(r);

			if (rr != null) {
				if (rr.getContraseña().equals(pass)) {
					this.msg = "";
					this.nombre = rr.getResponsable().getNombre() + " " + rr.getResponsable().getApellido();
					HttpSession session = SessionUtils.getSession();
					session.setAttribute("user", user);
					session.setAttribute("rol", rol);
					
					return "home.xhtml";
				} else {
					this.msg = "Usuario o contraseña incorrectos";

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario o contraseña incorrectos", "Por favor ingresa un usuario o contraseña correcta"));
					return "index.xhtml";
				}

			} else {
				this.msg = "Usuario o contraseña incorrectos";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Usuario o contraseña incorrectos", "Por favor ingresa un usuario o contraseña correcta"));
				return "index.xhtml";
			}

		case "alum":
			Alumnos a = new Alumnos();
			a.setCodAlumno(user);
			RolAlumno ra = this.pAlumno.busquedaPorAlumno(a);

			if (ra != null) {

				if (ra.getContraseña().equals(pass)) {
					this.msg = "";
					this.nombre = ra.getAlumno().getNombre() + " " + ra.getAlumno().getApellido();
					HttpSession session = SessionUtils.getSession();
					session.setAttribute("user", user);
					session.setAttribute("rol", rol);
					return "home.xhtml";
				} else {
					this.msg = "Usuario o contraseña incorrectos";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario o contraseña incorrectos", "Por favor ingresa un usuario o contraseña correcta"));
					return "index.xhtml";
				}

			} else {
				this.msg = "Usuario o contraseña incorrectos";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Usuario o contraseña incorrectos", "Por favor ingresa un usuario o contraseña correcta"));
				return "index.xhtml";
			}

		default:
			this.msg = "Usuario o contraseña incorrectos";
			return "index";

		}

	}

	public String salir() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index";
	}

}
