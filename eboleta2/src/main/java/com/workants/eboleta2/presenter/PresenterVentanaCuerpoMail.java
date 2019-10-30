package com.workants.eboleta2.presenter;

import java.io.Serializable;

import com.workants.eboleta2.IService.IServiceEnvio;
import com.workants.eboleta2.handler.IVentanaCuerpoMailHandler;
import com.workants.eboleta2.servicio.ServicioEnvio;
import com.workants.eboleta2.ui.view.ViewAdministrador.VentanaCuerpoMail;



public class PresenterVentanaCuerpoMail implements IVentanaCuerpoMailHandler, Serializable{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VentanaCuerpoMail view;
	private IServiceEnvio service;
	
	
	public PresenterVentanaCuerpoMail(VentanaCuerpoMail view, ServicioEnvio service){
		
		this.view = view;
		this.service = service;
	}

	@Override
	public void limpiar() {
		
		view.getTxtAreaCuerpoMail().setValue("");
		
	}

	@Override
	public void guardarCambios() {
		
		int rta = service.guardarCambios(view.getTxtAreaCuerpoMail().getValue());
		if(rta != 0){
			view.guardarCambiosOk();
		}else view.guardarCambiosError();
		
	}

	
}
