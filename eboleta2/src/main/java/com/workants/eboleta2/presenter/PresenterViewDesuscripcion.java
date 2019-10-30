package com.workants.eboleta2.presenter;

import java.io.Serializable;

import com.workants.eboleta2.handler.IViewDesuscripcionHandler;
import com.workants.eboleta2.servicio.ServicioValidar;
import com.workants.eboleta2.ui.view.ViewDesuscripcion.ViewDesuscripcion;

public class PresenterViewDesuscripcion implements Serializable, IViewDesuscripcionHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewDesuscripcion view;
	private ServicioValidar service;
	
	public PresenterViewDesuscripcion(ViewDesuscripcion view, ServicioValidar service) {
	
		this.view = view;
		this.service = service;
			
	}

	@Override
	public void buscarImpuestoBaja() {
	
		//String tipoDeDocumento = (String)view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getCmbTipoDeDocumento().getValue();
		String tipoDeContribuyente = (String)view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getCmbTipoDeDocumento().getValue();
		//String numeroDeDocumento = view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getTxtNumeroDeDocumento().getValue();
		String datosContribuyente = view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getTxtNumeroDeDocumento().getValue();
		String codigoPagoElectronico = view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getTxtCodigoPagoElectronico().getValue();
		
//		System.out.println(tipoDeDocumento);
//		System.out.println(numeroDeDocumento);
//		System.out.println(codigoPagoElectronico);
		System.out.println(tipoDeContribuyente);
		System.out.println(datosContribuyente);
		System.out.println(codigoPagoElectronico);
		
		if(!codigoPagoElectronico.isEmpty()){
			//String rta = service.buscarImpuestoBaja(tipoDeDocumento, numeroDeDocumento, codigoPagoElectronico);
			String rta = service.buscarImpuestoBaja(tipoDeContribuyente, datosContribuyente, codigoPagoElectronico);
			view.cargarResultado(rta);
		}
	}

	@Override
	public void generarMailBaja() {
		
		String codigoPagoElectronico = view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getTxtCodigoPagoElectronico().getValue();
		String tipoDeContribuyente = (String)view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getCmbTipoDeDocumento().getValue();
		//String tipoDeDocumento =  (String) view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getCmbTipoDeDocumento().getValue();
		String datosContribuyente = view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getTxtNumeroDeDocumento().getValue();
		//String numeroDeDocumento =  view.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getTxtNumeroDeDocumento().getValue();
		String cuerpo = view.getViewDesuscripcionCuerpo().getLayoutRespuesta().getLblRespuesta().getValue();
	
		
		//if( service.generarMailBaja(codigoPagoElectronico, tipoDeDocumento, numeroDeDocumento, cuerpo) !=0){
		if( service.generarMailBaja(codigoPagoElectronico, tipoDeContribuyente, datosContribuyente, cuerpo) !=0){
			view.generarMailBajaOK();
		}else {
			view.generarMailBajaError();
		}
				
		
		
	}


}
