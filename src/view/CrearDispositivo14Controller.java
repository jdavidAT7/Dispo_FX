package view;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import dao.Conexion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Dispositivo;

public class CrearDispositivo14Controller implements Initializable {

	// Componentes GUI
	@FXML private TextField txtTipoDispositivo;
	@FXML private TextField txtNumeroTel;
	@FXML private TextField txtDispositivoAso;
	@FXML private TextField txtCorreo;
	@FXML private TextField txtNombreDispo;
	@FXML private RadioButton rbtSi;
	@FXML private RadioButton rbtNo;
	@FXML private RadioButton rbtEncendido;
	@FXML private RadioButton rbtApagado;
	@FXML private Button btnGuardar;
	@FXML private Button btnCrearDispositivo;
	@FXML private Button btnEliminar;
	@FXML private Button btnActualizar;
	
	@FXML private TableView<Dispositivo> tblViewDispositivos;
	@FXML private ComboBox<String> cmbDispositivos; //lista los cinco dispositivos 

	private ObservableList<Dispositivo> listaDipositivos;
	
	Stage dialogStage = new Stage();
	Scene scene;


	
	//se une con combobox cmbDispositivos
	ObservableList<String> list = FXCollections.observableArrayList("Computadora portatil", "Tablet", "Smartwatch",
			"Smarthphone", "Auriculares inalambricos");
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cmbDispositivos.setPromptText("Seleccione...");
		cmbDispositivos.setItems(list);	

	}

	
	// Guarda los registros a la BD de Oracle en tabla Dispositivo
	@FXML
	public void guardarRegistroDispositivo() {		

		Dispositivo a = new Dispositivo(0, cmbDispositivos.getSelectionModel().getSelectedItem(),
				Integer.valueOf(txtNumeroTel.getText()), null, txtCorreo.getText(), txtNombreDispo.getText(),
				rbtSi.isSelected() ? "si" : "no", rbtEncendido.isSelected() ? "encendido" : "apagado");
		
		// llamar al metodo guardarRegistro de la clase dispositivo
		int resultado = a.guardarDispositivo(Conexion.getConnection());
		

		if (resultado == 1) {
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro agregado");
			mensaje.setContentText("El registro ha sido agregado exitosamente");
			mensaje.setHeaderText("Resultado:");
			mensaje.show();
		} else {
			Alert mensaje1 = new Alert(AlertType.ERROR);
			mensaje1.setContentText("saber que fue");
		}
	}
	
	
	
	
	
	public void gestionarEventos() {
		tblViewDispositivos.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<Dispositivo>() {

			@Override
			public void changed(ObservableValue<? extends Dispositivo> arg0, 
					Dispositivo valorAnterior, Dispositivo valorSeleccionado) {

				if(valorSeleccionado.getVisible().equals("Auriculares inalambricos")) {
					txtNumeroTel.setEditable(false);
					txtNumeroTel.setEditable(true);

				} else if(valorSeleccionado.getEstado().equals("APAGADO")) {
					rbtEncendido.setSelected(false);
					rbtApagado.setSelected(true);
				}
				
				btnGuardar.setDisable(true);

				

			}
		});
	}
}
