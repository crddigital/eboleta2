package com.workants.eboleta2.ui.view.responsive.ViewRegistro;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;


public class ViewRegistroEncabezadoResponsive extends HorizontalLayout {

	/**
	 * 
	 */
	private Label lblEncabezado;
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	public ViewRegistroEncabezadoResponsive(){
	
		setMargin(true);		
		addComponent(generarLabel());	
		
	}
	
	private Component generarLabel() {
				
		this.setLblEncabezado(new Label("",ContentMode.HTML));
		this.getLblEncabezado().addStyleName("my-style");
		return this.getLblEncabezado();
	}

	public Label getLblEncabezado() {
		return lblEncabezado;
	}


	public void setLblEncabezado(Label lblEncabezado) {
		this.lblEncabezado = lblEncabezado;
	}



}
