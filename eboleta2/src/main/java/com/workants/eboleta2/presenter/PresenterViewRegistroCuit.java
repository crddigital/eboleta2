package com.workants.eboleta2.presenter;

import java.io.Serializable;

import com.workants.eboleta2.handler.IViewRegistroCuitHandler;
import com.workants.eboleta2.servicio.ServicioRegistro;
import com.workants.eboleta2.ui.view.ViewRegistroCuit.ViewRegistroCuit;

public class PresenterViewRegistroCuit implements Serializable, IViewRegistroCuitHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewRegistroCuit view;
	private ServicioRegistro service;
	
	
	public PresenterViewRegistroCuit(ViewRegistroCuit view, ServicioRegistro service) {
		
		this.view = view;
		this.service = service;
		
	}
	
	
	
	
	
	
	

}
