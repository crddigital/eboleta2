package com.workants.eboleta2.ui.view.ViewDesuscripcion;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class FormLayoutRespuesta extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label lblRespuesta;
	private Button btnSolicitarBaja;
	private Button btnVolver;
	
	
	
	public FormLayoutRespuesta() {
		
		addComponent(generarLblRespuesta());
		addComponent(generarComandos());
		setHeight("300px");		
		setComponentAlignment(getComponent(1), Alignment.BOTTOM_LEFT);
	}



	private Component generarComandos() {
		
		HorizontalLayout layoutComandos = new HorizontalLayout();
		layoutComandos.setSpacing(true);
		
		this.setBtnSolicitarBaja(new Button("Solicitar Baja"));
		this.setBtnVolver(new Button("Volver"));
		layoutComandos.addComponent(this.getBtnVolver());
		layoutComandos.addComponent(this.getBtnSolicitarBaja());
		return layoutComandos;
	}



	private Component generarLblRespuesta() {
	
		this.setLblRespuesta(new Label("",ContentMode.HTML));	
		return this.getLblRespuesta();
	}



	public Label getLblRespuesta() {
		return lblRespuesta;
	}



	public void setLblRespuesta(Label lblRespuesta) {
		this.lblRespuesta = lblRespuesta;
	}



	public Button getBtnSolicitarBaja() {
		return btnSolicitarBaja;
	}



	public void setBtnSolicitarBaja(Button btnSolicitarBaja) {
		this.btnSolicitarBaja = btnSolicitarBaja;
	}



	public Button getBtnVolver() {
		return btnVolver;
	}



	public void setBtnVolver(Button btnVolver) {
		this.btnVolver = btnVolver;
	}
	

}
