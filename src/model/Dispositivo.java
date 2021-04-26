package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Conexion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import sun.security.krb5.internal.APRep;

public class Dispositivo {

	private IntegerProperty dispositivoId;
	private StringProperty tipoDispositivo;
	private IntegerProperty numeroTelefono;
	private StringProperty dispositivoAsociado;
	private StringProperty correoElectronico;
	private StringProperty nombreDispositivo;
	private StringProperty visible;
	private StringProperty estado;
	
	
	Connection conn = null;

	public Dispositivo(Integer dispositivoId, String tipoDispositivo, Integer numeroTelefono,
			String dispositivoAsociado, String correoElectronico, 
			String nombreDispositivo, String visible,
			String estado) {
		this.dispositivoId = new SimpleIntegerProperty(dispositivoId);
		this.tipoDispositivo = new SimpleStringProperty(tipoDispositivo);
		this.numeroTelefono = new SimpleIntegerProperty(numeroTelefono);
		this.dispositivoAsociado = new SimpleStringProperty(dispositivoAsociado);
		this.correoElectronico = new SimpleStringProperty(correoElectronico);
		this.nombreDispositivo = new SimpleStringProperty(nombreDispositivo);
		this.visible = new SimpleStringProperty(visible);
		this.estado = new SimpleStringProperty(estado);
	}

	public final IntegerProperty dispositivoIdProperty() {
		return this.dispositivoId;
	}

	public final int getDispositivoId() {
		return this.dispositivoIdProperty().get();
	}

	public final void setDispositivoId(final int dispositivoId) {
		this.dispositivoIdProperty().set(dispositivoId);
	}

	public final StringProperty tipoDispositivoProperty() {
		return this.tipoDispositivo;
	}

	public final String getTipoDispositivo() {
		return this.tipoDispositivoProperty().get();
	}

	public final void setTipoDispositivo(final String tipoDispositivo) {
		this.tipoDispositivoProperty().set(tipoDispositivo);
	}

	public final IntegerProperty numeroTelefonoProperty() {
		return this.numeroTelefono;
	}

	public final int getNumeroTelefono() {
		return this.numeroTelefonoProperty().get();
	}

	public final void setNumeroTelefono(final int numeroTelefono) {
		this.numeroTelefonoProperty().set(numeroTelefono);
	}

	public final StringProperty dispositivoAsociadoProperty() {
		return this.dispositivoAsociado;
	}

	public final String getDispositivoAsociado() {
		return this.dispositivoAsociadoProperty().get();
	}

	public final void setDispositivoAsociado(final String dispositivoAsociado) {
		this.dispositivoAsociadoProperty().set(dispositivoAsociado);
	}

	public final StringProperty correoElectronicoProperty() {
		return this.correoElectronico;
	}

	public final String getCorreoElectronico() {
		return this.correoElectronicoProperty().get();
	}

	public final void setCorreoElectronico(final String correoElectronico) {
		this.correoElectronicoProperty().set(correoElectronico);
	}

	public final StringProperty nombreDispositivoProperty() {
		return this.nombreDispositivo;
	}

	public final String getNombreDispositivo() {
		return this.nombreDispositivoProperty().get();
	}

	public final void setNombreDispositivo(final String nombreDispositivo) {
		this.nombreDispositivoProperty().set(nombreDispositivo);
	}

	public final StringProperty visibleProperty() {
		return this.visible;
	}

	public final String getVisible() {
		return this.visibleProperty().get();
	}

	public final void setVisible(final String visible) {
		this.visibleProperty().set(visible);
	}

	public final StringProperty estadoProperty() {
		return this.estado;
	}

	public final String getEstado() {
		return this.estadoProperty().get();
	}

	public final void setEstado(final String estado) {
		this.estadoProperty().set(estado);
	}

	public int guardarDispositivo(Connection connection) {
		String sql = "INSERT INTO DISPOSITIVO (TIPO_DISPOSITIVO, NUMERO_TELEFONO, EMAIL, NOMBRE, VISIBLE, ESTADO)  VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement instruccion = connection
					.prepareStatement(sql);
			
			instruccion.setString(1, tipoDispositivo.get());
			instruccion.setInt(2, numeroTelefono.get());
			instruccion.setString(3, correoElectronico.get());
			instruccion.setString(4, nombreDispositivo.get());
			instruccion.setString(5, visible.get());
			instruccion.setString(6, estado.get());
			
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} 
	
	}

	public void actualizarDispositivo() {

	}

	public int eliminarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement("DELETE FROM DISPOSITIVO WHERE dispositivo_id = ?");
			instruccion.setInt(1,dispositivoId.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	public static void llenarInformacionDispositivos(Connection connection, 
			ObservableList<Dispositivo> lista) {
		String sql = "SELECT * FROM DISPOSITIVO";
		try {
			Statement instruccion = connection.createStatement();
			ResultSet resultado = instruccion.executeQuery(sql);

			while (resultado.next()) {
				lista.add(new Dispositivo(
						resultado.getInt("dispositivo_id"), 
						resultado.getString("tipo_dispositivo"), 
						resultado.getInt("numero_telefono"), 
						resultado.getString("dispositivo_asociado"), 
						resultado.getString("email"), 
						resultado.getString("nombre"), 
						resultado.getString("visible"),
						resultado.getString("estado")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
