package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
	
	private IntegerProperty usuarioId;
	private StringProperty nombreUsuario;
	private StringProperty username;
	private StringProperty password;	
	
	private static Usuario usuario;

	public Usuario(int usuarioId, String nombreUsuario, String username, String password) {
		
		this.usuarioId = new SimpleIntegerProperty(usuarioId);
		this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
	}

	public static Usuario getInstanceUser(int id, String nombreUsuario, 
			String user, String pass) {
		
		if(usuario == null) {
			usuario = new Usuario(id, nombreUsuario, user, pass);
		}
		return usuario;
	}

	public final IntegerProperty usuarioIdProperty() {
		return this.usuarioId;
	}
	


	public final int getUsuarioId() {
		return this.usuarioIdProperty().get();
	}
	


	public final void setUsuarioId(final int usuarioId) {
		this.usuarioIdProperty().set(usuarioId);
	}
	


	public final StringProperty nombreUsuarioProperty() {
		return this.nombreUsuario;
	}
	


	public final String getNombreUsuario() {
		return this.nombreUsuarioProperty().get();
	}
	


	public final void setNombreUsuario(final String nombreUsuario) {
		this.nombreUsuarioProperty().set(nombreUsuario);
	}
	


	public final StringProperty usernameProperty() {
		return this.username;
	}
	


	public final String getUsername() {
		return this.usernameProperty().get();
	}
	


	public final void setUsername(final String username) {
		this.usernameProperty().set(username);
	}
	


	public final StringProperty passwordProperty() {
		return this.password;
	}
	


	public final String getPassword() {
		return this.passwordProperty().get();
	}
	


	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
	
	

}
