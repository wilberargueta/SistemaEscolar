package com.gdunivo.es.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConexionFactory {

	private static Connection conn;
	private static final String USER = "sa";
	private static final String PASS = "admin";
	private static final String DB = "Escuela";
	private static final String URL = String.format(
			"jdbc:sqlserver://DESKTOP-83KH9G2\\SQLEXPRESS:1434;" + "databaseName=%s;" + "user=%s;" + "password=%s", DB,
			USER, PASS);

	public static void createdConection() {

		if (conn == null) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				conn = DriverManager.getConnection(URL);
				System.out.println("Conexion exitosa");

			} catch (Exception e) {

				System.out.println("Erro: En la conexion:::" + e.getMessage().toString());
				e.printStackTrace();
			}

		}
	}

	public static PreparedStatement getStatement(String query) {
		try {
			return ConexionFactory.conn.prepareStatement(query);
		} catch (SQLException e) {
			System.out.println("Error Creando el Statement::" + e.getMessage().toString());
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConection() {
		try {
			ConexionFactory.conn.close();
			System.out.println("Conexion cerrada correctamente");
		} catch (SQLException e) {
			System.out
					.println("Error no se pudo cerrar la conexion con la base de datos::" + e.getMessage().toString());
			e.printStackTrace();
		}
	}

}
