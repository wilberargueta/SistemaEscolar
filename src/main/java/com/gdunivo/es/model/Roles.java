package com.gdunivo.es.model;

public class Roles {

	private int Id_Role;
	private String role;

	public Roles() {

	}

	public Roles(int id_Role, String role) {
		Id_Role = id_Role;
		this.role = role;
	}

	public int getId_Role() {
		return Id_Role;
	}

	public void setId_Role(int id_Role) {
		Id_Role = id_Role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Roles [Id_Role=" + Id_Role + ", role=" + role + "]";
	}

}
