package com.workants.eboleta2.presenter;

import java.io.Serializable;

import com.vaadin.ui.UI;
import com.workants.eboleta2.handler.IViewABMSuscripcionesHandler;
import com.workants.eboleta2.servicio.ServicioValidar;
import com.workants.eboleta2.ui.view.ViewBaja.ViewBaja;
import com.workants.eboleta2.ui.view.ViewSuscripcion.ViewSuscripcion;

public class PresenterViewABMSuscripciones implements Serializable, IViewABMSuscripcionesHandler{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewBaja view;
	private ViewSuscripcion viewSuscripcion;
	private ServicioValidar service;
	
	public PresenterViewABMSuscripciones(ViewBaja view, ServicioValidar service) {
	
		this.view = view;
		this.service = service;
	}
	public PresenterViewABMSuscripciones(ViewSuscripcion viewSuscripcion,
			ServicioValidar service) {
		
		this.viewSuscripcion = viewSuscripcion;
		this.service = service;
	}
	@Override
	public void verificarCpe() {
		
		String cpe = view.getCodigoPagoElectronico();
		
		
		if(service.verificarCpe(cpe) != 0){
			view.generarLayoutBajaOK();
		} else {
			view.generarLayoutBajaError();
		}
		
	}
	
	
	
	
	@Override
	public void ejecutarBaja() {
		
		String cpe = view.getCodigoPagoElectronico();
		String codigoKeyBaja = view.getTxtCodogoKeyBaja().getValue();		
		if(service.ejecutarBaja(cpe, codigoKeyBaja) != 0){
			view.bajaOk();
		}else{
			view.bajaError();
		}
		
	}
	@Override
	public void redirigir() {
		
		UI.getCurrent().getSession().close();
		UI.getCurrent().getUI().getPage().setLocation("https://www.comodoro.gov.ar");
		  
		
	}
	@Override
	public void verificarCpeSuscripcion() {
		
		String cpe = viewSuscripcion.getCodigoPagoElectronico();
		int rta = service.verificarCpeSuscripcion(cpe);
		if(rta == 0){
			viewSuscripcion.generarLayoutSuscripcionError();			
		} else if(rta == 2){			
			viewSuscripcion.generarLayoutSuscripcionError2();
		}else{
			viewSuscripcion.generarLayoutSuscripcionOK();
		}
		
		
		
	}

	
	
}
