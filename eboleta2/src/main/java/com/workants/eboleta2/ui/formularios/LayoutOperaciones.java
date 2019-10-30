package com.workants.eboleta2.ui.formularios;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LayoutOperaciones extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label lblDescripcion;
	private Button btnGenerarEnvio;
	
	
	
	public LayoutOperaciones(){
		
		setSpacing(true);
		setMargin(true);
		
		
		this.setLblDescripcion(new Label("Desde aquí se genera el proceso de envio masivo de mails para los registrados \n Una vez iniciado el"
				+ " proceso no podra detenerse."));
		this.getLblDescripcion().addStyleName("my-labeloperaciones");
		
		this.setBtnGenerarEnvio(new Button("Generar proceso"));
		
		addComponent(this.getLblDescripcion());
		addComponent(this.getBtnGenerarEnvio());
		setComponentAlignment(this.getLblDescripcion(), Alignment.MIDDLE_CENTER);
		setComponentAlignment(this.getBtnGenerarEnvio(), Alignment.MIDDLE_CENTER);
		
		
	}



	public Label getLblDescripcion() {
		return lblDescripcion;
	}



	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}



	public Button getBtnGenerarEnvio() {
		return btnGenerarEnvio;
	}



	public void setBtnGenerarEnvio(Button btnGenerarEnvio) {
		this.btnGenerarEnvio = btnGenerarEnvio;
	}

}
