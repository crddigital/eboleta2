package com.workants.eboleta2.ui.view.ViewAdministrador;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.presenter.PresenterVentanaCuerpoMail;
import com.workants.eboleta2.servicio.ServicioEnvio;
import com.workants.eboleta2.ui.formularios.LayoutABMUsuarios;
import com.workants.eboleta2.ui.formularios.LayoutActualizarEmision;
import com.workants.eboleta2.ui.formularios.LayoutExportarAdheridos;
import com.workants.eboleta2.ui.formularios.LayoutImportarAdheridos;
import com.workants.eboleta2.ui.formularios.LayoutLateralIzquierdo;
import com.workants.eboleta2.ui.formularios.LayoutOperaciones;
import com.workants.eboleta2.ui.formularios.LayoutSuscripciones;

public class ViewAdministradorCuerpo extends VerticalLayout{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LayoutABMUsuarios layoutABMUsuarios;	
	private LayoutOperaciones layoutOperaciones;
	private LayoutSuscripciones layoutSuscripciones; 
	private LayoutActualizarEmision layoutActualizarEmision;
	private LayoutExportarAdheridos layoutExportarAdheridos;
	private LayoutImportarAdheridos layoutImportarAdheridos;
	private LayoutLateralIzquierdo layoutLateralIzquierdo;	
	private HorizontalSplitPanel splitter;
	

	public ViewAdministradorCuerpo(){
		
		
		addComponent(generarSplitter());	
		setSizeFull();
		this.setLayoutABMUsuarios(new LayoutABMUsuarios());
		this.setLayoutOperaciones(new LayoutOperaciones());
		this.setLayoutSuscripciones(new LayoutSuscripciones());
		this.setLayoutActualizarEmision(new LayoutActualizarEmision());
		this.setLayoutExportarAdheridos(new LayoutExportarAdheridos());
		this.setLayoutImportarAdheridos(new LayoutImportarAdheridos());
		
	}
	
	
	

	private Component generarSplitter() {
		
		this.setSplitter(new HorizontalSplitPanel());
		this.getSplitter().setFirstComponent(generarLayoutLateralIzquierdo());
		this.getSplitter().setSplitPosition(200, Unit.PIXELS);
		this.getSplitter().setLocked(true);
		this.getSplitter().setSizeFull();
		return this.getSplitter();
	}




	private Component generarLayoutLateralIzquierdo() {
		
		this.setLayoutLateralIzquierdo(new LayoutLateralIzquierdo());
		return this.getLayoutLateralIzquierdo();
	}




	public Component generarLayoutABMUsuarios() {
		
		//this.setLayoutABMUsuarios(new LayoutABMUsuarios());
		return this.getLayoutABMUsuarios();
	}

	public void cargarLayoutABMUsuarios() {
		// TODO Auto-generated method stub
		
	}




	public LayoutABMUsuarios getLayoutABMUsuarios() {
		return layoutABMUsuarios;
	}




	public void setLayoutABMUsuarios(LayoutABMUsuarios layoutABMUsuarios) {
		this.layoutABMUsuarios = layoutABMUsuarios;
	}




	public LayoutLateralIzquierdo getLayoutLateralIzquierdo() {
		return layoutLateralIzquierdo;
	}




	public void setLayoutLateralIzquierdo(
			LayoutLateralIzquierdo layoutLateralIzquierdo) {
		this.layoutLateralIzquierdo = layoutLateralIzquierdo;
	}




	public HorizontalSplitPanel getSplitter() {
		return splitter;
	}




	public void setSplitter(HorizontalSplitPanel splitter) {
		this.splitter = splitter;
	}




	public Component generarLayoutOperaciones() {
		
		//this.setLayoutOperaciones(new LayoutOperaciones());
		return this.getLayoutOperaciones();
		
	}
	
	public Component generarLayoutSuscripciones() {
		
		//this.setLayoutOperaciones(new LayoutOperaciones());
		return this.getLayoutSuscripciones();
		
	}


	public LayoutOperaciones getLayoutOperaciones() {
		return layoutOperaciones;
	}




	public void setLayoutOperaciones(LayoutOperaciones layoutOperaciones) {
		this.layoutOperaciones = layoutOperaciones;
	}




	public void mostrarCuerpoMail(String cuerpoMail) {
		
		
		VentanaCuerpoMail ventanaCuerpoMail = new VentanaCuerpoMail(cuerpoMail);
		PresenterVentanaCuerpoMail presenterVentanaCuerpoMail = new PresenterVentanaCuerpoMail(ventanaCuerpoMail, ServicioEnvio.getIntance());
		ventanaCuerpoMail.setHandler(presenterVentanaCuerpoMail);
		UI.getCurrent().addWindow(ventanaCuerpoMail);
		
	}




	public LayoutSuscripciones getLayoutSuscripciones() {
		return layoutSuscripciones;
	}




	public void setLayoutSuscripciones(LayoutSuscripciones layoutSuscripciones) {
		this.layoutSuscripciones = layoutSuscripciones;
	}




	public Component generarLayoutActualizarEmision() {
		
		return layoutActualizarEmision;
	}
	
	public Component generarLayoutExportarAdheridos(){
		
		return layoutExportarAdheridos;
	}
	
	public Component generarLayoutImportarAdheridos(){
		
		return layoutImportarAdheridos;
	}




	public LayoutActualizarEmision getLayoutActualizarEmision() {
		return layoutActualizarEmision;
	}




	public void setLayoutActualizarEmision(LayoutActualizarEmision layoutActualizarEmision) {
		this.layoutActualizarEmision = layoutActualizarEmision;
	}




	public LayoutExportarAdheridos getLayoutExportarAdheridos() {
		return layoutExportarAdheridos;
	}




	public void setLayoutExportarAdheridos(LayoutExportarAdheridos layoutExportarAdheridos) {
		this.layoutExportarAdheridos = layoutExportarAdheridos;
	}




	public LayoutImportarAdheridos getLayoutImportarAdheridos() {
		return layoutImportarAdheridos;
	}




	public void setLayoutImportarAdheridos(LayoutImportarAdheridos layoutImportarAdheridos) {
		this.layoutImportarAdheridos = layoutImportarAdheridos;
	}
	
	




}
