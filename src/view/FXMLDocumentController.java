package view;

import dao.Conexion;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

	@FXML private TextField textUser;
	@FXML private PasswordField textPassword;

	Stage dialogStage = new Stage();
	Scene scene;

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public FXMLDocumentController() {
		connection = Conexion.getConnection();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}	
/*
	@FXML
	void loginAction(ActionEvent event) throws SQLException, IOException {
		
		if(textUser.getText().equals("admin") && textPassword.getText().equals("admin")) {
			infoBox("Login Successfull", null, "Success");
			Node node = (Node) event.getSource();
			dialogStage = (Stage) node.getScene().getWindow();
			dialogStage.close();
			scene = new Scene(FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml")));
			dialogStage.setTitle("Welcome to JavaFX!");
			dialogStage.setScene(scene);
			dialogStage.show();
		}else {
			
			infoBox("Please enter correct User and Password", null, "Failed");

		}
	}
	
*/	
	@FXML
	void dispositivos(ActionEvent event) {
		
		try {
			
			scene = new Scene(FXMLLoader.load(getClass().getResource("VistaDispositivos.fxml")));
			Node node = (Node) event.getSource();
			dialogStage = (Stage) node.getScene().getWindow();
			dialogStage.close();
			dialogStage.setScene(scene);
			dialogStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}
	
	@FXML
	 void loginAction1(ActionEvent event) throws SQLException {
		String usuario = textUser.getText().toString();
		String password = textPassword.getText().toString();
		connection = Conexion.connectdb();
		connection.createStatement();

		try {
			String sql = "select * from usuarios where UserName = ? and contrasena= ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet);
			if (!resultSet.next()) {
				infoBox("Please enter correct User and Password", null, "Failed");
			} else {
				infoBox("Login Successfull", null, "Success");
				Node node = (Node) event.getSource();
				dialogStage = (Stage) node.getScene().getWindow();
				dialogStage.close();
				scene = new Scene(FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml")));
				dialogStage.setScene(scene);
				dialogStage.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}