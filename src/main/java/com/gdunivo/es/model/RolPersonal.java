package com.gdunivo.es.model;

public class RolPersonal {

	private int idRolePersonal;
	private Roles rol;
	private Personal personal;
	private String contraseña;

	public RolPersonal() {
	
	}

	public RolPersonal(int idRolePersonal, Roles rol, Personal personal, String contraseña) {
		this.idRolePersonal = idRolePersonal;
		this.rol = rol;
		this.personal = personal;
		this.contraseña = contraseña;
	}

	public int getIdRolePersonal() {
		return idRolePersonal;
	}

	public void setIdRolePersonal(int idRolePersonal) {
		this.idRolePersonal = idRolePersonal;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "RolPersonal [idRolePersonal=" + idRolePersonal + ", rol=" + rol + ", personal=" + personal
				+ ", contraseña=" + contraseña + "]";
	}

}
