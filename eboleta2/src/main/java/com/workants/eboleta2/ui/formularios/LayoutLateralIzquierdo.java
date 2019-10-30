package com.workants.eboleta2.ui.formularios;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LayoutLateralIzquierdo extends VerticalLayout{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnOperaciones;
	private Button btnAbmUsuario;
	private Button btnActualizarEmision;
	private Button btnExportarInscripciones;
	private Button btnImportarInscripciones;
	private Button btnCuerpoMail;
	private Button btnSuscripciones;
	
	

	public LayoutLateralIzquierdo(){
		
		setMargin(true);
		setSpacing(true);
		addComponent(generarBotonera());
		setSizeFull();
	}

	
	private Component generarBotonera() {
		
		VerticalLayout botonera = new VerticalLayout();
		botonera.setSpacing(true);	
		this.setBtnAbmUsuario(new Button("ABM Usuarios"));
		this.setBtnActualizarEmision(new Button("Actualizar Emision"));
		this.setBtnExportarInscripciones(new Button("Exportar Inscriptos"));
		this.setBtnImportarInscripciones(new Button("Importar Inscriptos"));
		this.setBtnOperaciones(new Button("Operaciones"));
		this.setBtnCuerpoMail(new Button("Cuerpo de email"));
		this.setBtnSuscripciones(new Button("Suscripciones"));
		
		
//		this.getBtnAbmUsuario().setWidth("150px");
//		this.getBtnSuscripciones().setWidth("150px");
//		this.getBtnOperaciones().setWidth("150px");
//		this.getBtnCuerpoMail().setWidth("150px");
//		this.getBtnExportar().setWidth("150px");
//		this.getBtnLogout().setWidth("150px");
//		
		this.getBtnAbmUsuario().addStyleName(ValoTheme.BUTTON_LINK);
		this.getBtnSuscripciones().addStyleName(ValoTheme.BUTTON_LINK);
		this.getBtnExportarInscripciones().addStyleName(ValoTheme.BUTTON_LINK);
		this.getBtnImportarInscripciones().addStyleName(ValoTheme.BUTTON_LINK);
		this.getBtnOperaciones().addStyleName(ValoTheme.BUTTON_LINK);
		this.getBtnCuerpoMail().addStyleName(ValoTheme.BUTTON_LINK);
		this.getBtnActualizarEmision().addStyleName(ValoTheme.BUTTON_LINK);
		
		
		
		
		botonera.addComponent(this.getBtnAbmUsuario());
		botonera.addComponent(this.getBtnOperaciones());
		botonera.addComponent(this.getBtnSuscripciones());
		botonera.addComponent(this.getBtnExportarInscripciones());
		botonera.addComponent(this.getBtnImportarInscripciones());
		//botonera.addComponent(this.getBtnExportar());
		botonera.addComponent(this.getBtnActualizarEmision());
		botonera.addComponent(this.getBtnCuerpoMail());
			
		return botonera;
	}


	public Button getBtnOperaciones() {
		return btnOperaciones;
	}


	public void setBtnOperaciones(Button btnOperaciones) {
		this.btnOperaciones = btnOperaciones;
	}


	public Button getBtnAbmUsuario() {
		return btnAbmUsuario;
	}


	public void setBtnAbmUsuario(Button btnAbmUsuario) {
		this.btnAbmUsuario = btnAbmUsuario;
	}

	public Button getBtnCuerpoMail() {
		return btnCuerpoMail;
	}


	public void setBtnCuerpoMail(Button btnCuerpoMail) {
		this.btnCuerpoMail = btnCuerpoMail;
	}


	public Button getBtnSuscripciones() {
		return btnSuscripciones;
	}


	public void setBtnSuscripciones(Button btnSuscripciones) {
		this.btnSuscripciones = btnSuscripciones;
	}


	public Button getBtnActualizarEmision() {
		return btnActualizarEmision;
	}


	public void setBtnActualizarEmision(Button btnActualizarEmision) {
		this.btnActualizarEmision = btnActualizarEmision;
	}


	public Button getBtnExportarInscripciones() {
		return btnExportarInscripciones;
	}


	public void setBtnExportarInscripciones(Button btnExportarInscripciones) {
		this.btnExportarInscripciones = btnExportarInscripciones;
	}


	public Button getBtnImportarInscripciones() {
		return btnImportarInscripciones;
	}


	public void setBtnImportarInscripciones(Button btnImportarInscripciones) {
		this.btnImportarInscripciones = btnImportarInscripciones;
	}
}
