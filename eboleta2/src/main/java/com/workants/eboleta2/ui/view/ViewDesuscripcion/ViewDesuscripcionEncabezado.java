package com.workants.eboleta2.ui.view.ViewDesuscripcion;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class ViewDesuscripcionEncabezado extends HorizontalLayout{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label lblBienvenida;
	private Button btnSalir;

	public ViewDesuscripcionEncabezado(){
		
		//setMargin(true);	
		setSizeFull();
		addComponent(generarLabelBienvenida());
		addComponent(generarBtnSalir());
		setComponentAlignment(this.getLblBienvenida(), Alignment.TOP_LEFT);
		setComponentAlignment(this.getBtnSalir(), Alignment.TOP_RIGHT);
		
	
		
	}

	private Component generarLabelBienvenida() {
		this.setLblBienvenida(new Label("Cancelacion de suscripcion Recibos Electronicos - MCR"));
		this.getLblBienvenida().setWidth(null); 
		return this.getLblBienvenida();
	}
	private Component generarBtnSalir() {
		
		this.setBtnSalir(new Button("Salir"));
		this.getBtnSalir().setStyleName(ValoTheme.BUTTON_LINK);
		return this.getBtnSalir();
	}

	public Label getLblBienvenida() {
		return lblBienvenida;
	}

	public void setLblBienvenida(Label lblBienvenida) {
		this.lblBienvenida = lblBienvenida;
	}

	public Button getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(Button btnSalir) {
		this.btnSalir = btnSalir;
	}

}
