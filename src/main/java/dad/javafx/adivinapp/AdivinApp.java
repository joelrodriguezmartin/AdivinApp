package dad.javafx.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	private TextField numeroText;
	private Button comprobarButton;
	private Label comprobarLabel;
	private Alert comprobarAlert;
	private int numeroAdivinar;
	private int cuentaIntentos = 1;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		numeroText = new TextField();
		numeroText.setPromptText("Introduce un numero del 1 al 100: ");
		numeroText.setMaxWidth(150);
		
		comprobarLabel = new Label("Introduce un numero del 1 al 100");
		
		comprobarButton = new Button("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setOnAction(e -> onComprobarButtonAction(e));
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(comprobarLabel, numeroText, comprobarButton);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		numeroAdivinar = (int) (Math.random() * 100 + 1);
		
		
	}
	private void onComprobarButtonAction(ActionEvent e) {
		
		try {
			int intento = Integer.parseInt(numeroText.getText());
			if(intento != numeroAdivinar){
				comprobarAlert = new Alert(AlertType.WARNING);
				comprobarAlert.setHeaderText("¡Has fallado!");
				if(intento < numeroAdivinar) {
					comprobarAlert.setContentText("El número a adivinar es mayor que "+intento+" , vuelve a intentarlo");
				}
				else {
					comprobarAlert.setContentText("El número a adivinar es menor que "+intento+" , vuelve a intentarlo");
				}
				cuentaIntentos++;
				comprobarAlert.showAndWait();
			}
			else {
				comprobarAlert = new Alert(AlertType.INFORMATION);
				comprobarAlert.setHeaderText("Has ganado");
				comprobarAlert.setContentText("Has necesitado "+cuentaIntentos+" intentos, intentalo de nuevo y hazlo mejor");
				numeroAdivinar = (int) (Math.random() * 100 + 1);
				comprobarAlert.showAndWait();
				
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			comprobarAlert = new Alert(AlertType.ERROR);
			comprobarAlert.setHeaderText("Error");
			comprobarAlert.setContentText("El numero introducido no es válido");
			comprobarAlert.showAndWait();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
