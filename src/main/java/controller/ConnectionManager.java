package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionManager {

	private static Connection conexion = null;
	
	
	public static Connection getConexion () throws SQLException {
		// Si es la primera vez que accedemos a la conexi�n, debemos instanciarla
		if (conexion == null) {
			conectar();
		}
		// Compruebo si la conexi�n sigue estando activa
		while (!conexion.isValid(5)) {
			conectar();
		}
		
		return conexion;
	}
	
	
	
	private static void conectar () throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String user = "java";
		String password = "Abcdefgh.1";
		String host = "localhost";
		String schema = "profesoresymaterias";
		String properties = "?autoReconnect=true&serverTimezone=Europe/Madrid&useSSL=False&allowPublicKeyRetrieval=TRUE";

		
		try {
			Class.forName(driver);
		   
			conexion = (Connection) DriverManager.getConnection ("jdbc:mysql://" + host + "/" + schema + properties, user, password);			   
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Imposible acceder al driver Mysql");
		}
	}
}
