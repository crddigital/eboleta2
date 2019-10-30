package com.workants.eboleta2.ui.view.ViewAdministrativo;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class ViewAdministrativoEncabezado extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label lblEncabezado;
	private Button btnSalir;
	


	public ViewAdministrativoEncabezado(){
		
		//setSpacing(true);
		//addComponent(generarLabelEncabezado());
		//addComponent(generarBtnSalir());
		
		setWidth("100%");
		setHeight("50px");
		
		HorizontalLayout label = new HorizontalLayout();
		label.addComponent(generarLabelEncabezado());
		addComponent(label);
		
		HorizontalLayout botonera = new HorizontalLayout();
		botonera.setWidth(null);
		botonera.setSpacing(true);	 
		botonera.addComponent(generarBtnSalir());			
		addComponent(botonera);	
		
		
		setComponentAlignment(label, Alignment.MIDDLE_LEFT);
		setComponentAlignment(botonera, Alignment.TOP_RIGHT);
		
		
		
		
	}
	
	private Component generarBtnSalir() {
		
		this.setBtnSalir(new Button("Salir"));
		this.getBtnSalir().addStyleName(ValoTheme.BUTTON_LINK);
		return this.getBtnSalir();
	}
	
	private Component generarLabelEncabezado() {
		
		this.setLblEncabezado(new Label());
		return this.getLblEncabezado();
	}

	public Label getLblEncabezado() {
		return lblEncabezado;
	}

	public void setLblEncabezado(Label lblEncabezado) {
		this.lblEncabezado = lblEncabezado;
	}

	public Button getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(Button btnSalir) {
		this.btnSalir = btnSalir;
	}

}
