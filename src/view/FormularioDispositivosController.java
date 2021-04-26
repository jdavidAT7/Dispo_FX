package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.Conexion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Dispositivo;

public class FormularioDispositivosController implements Initializable {

	@FXML private ComboBox<Dispositivo> cmbDispositivo;
	// Columnas
	@FXML private TableColumn<Dispositivo, String> clmTipoDispositivo;
	@FXML private TableColumn<Dispositivo, Number> clmNumeroTelefono;
	@FXML private TableColumn<Dispositivo, String> clmDispositivoAsociado;
	@FXML private TableColumn<Dispositivo, String> clmCorreoElectronico;
	@FXML private TableColumn<Dispositivo, String> clmNombreDispositivo;
	@FXML private TableColumn<Dispositivo, String> clmVisible;
	@FXML private TableColumn<Dispositivo, String> clmEstado;

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
	
	
	private Conexion conexion;
	Stage dialogStage = new Stage();
	Scene scene;

	// Colecciones
	private ObservableList<Dispositivo> listaDipositivos;
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	void vistaDispositivo(ActionEvent event) {
		conexion = new Conexion();
		Conexion.getConnection();

			// Inicializar listas
			listaDipositivos = FXCollections.observableArrayList();

			// Llenar listas
			Dispositivo.llenarInformacionDispositivos(Conexion.getConnection(), listaDipositivos);

			// Enlazar listas con comboBox y TableView
			tblViewDispositivos.setItems(listaDipositivos);

			// Enlazar columnas con atributos de Dispositivo
			clmTipoDispositivo.setCellValueFactory
				(new PropertyValueFactory<Dispositivo, String>("tipoDispositivo"));
			clmNumeroTelefono.setCellValueFactory
				(new PropertyValueFactory<Dispositivo, Number>("numeroTelefono"));
			clmDispositivoAsociado
					.setCellValueFactory
					(new PropertyValueFactory<Dispositivo, String>("dispositivoAsociado"));
			clmCorreoElectronico.setCellValueFactory
				(new PropertyValueFactory<Dispositivo, String>("correoElectronico"));
			clmNombreDispositivo.setCellValueFactory
				(new PropertyValueFactory<Dispositivo, String>("nombreDispositivo"));
			clmVisible.setCellValueFactory
				(new PropertyValueFactory<Dispositivo, String>("visible"));
			clmEstado.setCellValueFactory
			(new PropertyValueFactory<Dispositivo, String>("estado"));
			
			gestionarEventos();

			conexion.desconectar();;
	}
	
	@FXML
	void dispositivos(ActionEvent event) {
		
		try {
			//Node node = (Node) event.getSource();
			//dialogStage = (Stage) node.getScene().getWindow();
			//dialogStage.close();
			scene = new Scene(FXMLLoader.load(getClass().getResource("VistaDispositivos.fxml")));
			dialogStage.setScene(scene);
			dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dialogStage.setScene(scene);
		dialogStage.show();

	}
	
	@FXML
	void crearDispositivo(ActionEvent event) {
		
		try {
			//Node node = (Node) event.getSource();
			//dialogStage = (Stage) node.getScene().getWindow();
			//dialogStage.close();
			scene = new Scene(FXMLLoader.load(getClass().getResource("CrearDispositivo1-4.fxml")));
			dialogStage.setScene(scene);
			dialogStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialogStage.setScene(scene);
		dialogStage.show();

	}
	
	@FXML 
	public void eliminarRegistroDispositivo(){
		
		int resultado =  tblViewDispositivos.getSelectionModel().getSelectedItem().eliminarRegistro(Conexion.getConnection());
		
		if (resultado == 1) {
			listaDipositivos.remove(tblViewDispositivos.getSelectionModel().getSelectedIndex());
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
				txtTipoDispositivo.setText(valorSeleccionado.getTipoDispositivo());
				txtNumeroTel.setText(String.valueOf(valorSeleccionado.getNumeroTelefono()));
				txtDispositivoAso.setText(valorSeleccionado.getDispositivoAsociado());
				txtCorreo.setText(valorSeleccionado.getCorreoElectronico());
				txtNombreDispo.setText(valorSeleccionado.getNombreDispositivo());
				if(valorSeleccionado.getVisible().equals("SI")) {
					rbtSi.setSelected(true);
					rbtNo.setSelected(false);
				} else if(valorSeleccionado.getVisible().equals("NO")) {
					rbtSi.setSelected(false);
					rbtNo.setSelected(true);
				}
				
				if(valorSeleccionado.getEstado().equals("ENCENDIDO")) {
					rbtEncendido.setSelected(true);
					rbtApagado.setSelected(false);
				} else if(valorSeleccionado.getEstado().equals("APAGADO")) {
					rbtEncendido.setSelected(false);
					rbtApagado.setSelected(true);
				}
				
				btnGuardar.setDisable(true);
				btnEliminar.setDisable(false);
				btnActualizar.setDisable(false);
				

			}
		});
	}
	

	@FXML
	public void limpiarComponentes() {
		txtTipoDispositivo.setText(null);
		txtNumeroTel.setText(null);
		txtDispositivoAso.setText(null);
		txtCorreo.setText(null);
		rbtSi.setSelected(false);
		rbtNo.setSelected(false);
		rbtEncendido.setSelected(false);
		rbtApagado.setSelected(false);
		
		btnGuardar.setDisable(false);
		btnEliminar.setDisable(true);
		btnActualizar.setDisable(true);
	}
}
