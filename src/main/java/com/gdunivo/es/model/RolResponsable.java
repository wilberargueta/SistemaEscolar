package com.gdunivo.es.model;

public class RolResponsable {
	private int idRoleResponsable;
	private Roles role;
	private Responsables responsable;
	private String contraseña;

	public RolResponsable() {

	}

	public RolResponsable(int idRoleResponsable, Roles role, Responsables responsable, String contraseña) {
		this.idRoleResponsable = idRoleResponsable;
		this.role = role;
		this.responsable = responsable;
		this.contraseña = contraseña;
	}

	public int getIdRoleResponsable() {
		return idRoleResponsable;
	}

	public void setIdRoleResponsable(int idRoleResponsable) {
		this.idRoleResponsable = idRoleResponsable;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Responsables getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsables responsable) {
		this.responsable = responsable;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "RolResponsable [idRoleResponsable=" + idRoleResponsable + ", role=" + role + ", responsable="
				+ responsable + ", contraseña=" + contraseña + "]";
	}

}
