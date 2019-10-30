package com.workants.eboleta2.presenter;


import java.io.Serializable;
import java.util.Date;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.UI;
import com.workants.eboleta2.IService.IServiceEnvio;
import com.workants.eboleta2.handler.IViewAdministradorHandler;
import com.workants.eboleta2.model.PreSolicitudConfirmar;
import com.workants.eboleta2.model.Procesados;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.ui.view.ViewAdministrador.ViewAdministrador;

public class PresenterViewAdministrador implements IViewAdministradorHandler, Serializable {


	private static final long serialVersionUID = 1L;
	private ViewAdministrador view;
	private IServiceEnvio services;
	
	
	public PresenterViewAdministrador(ViewAdministrador viewAdministrador, IServiceEnvio services) {
		
		this.view = viewAdministrador;
		this.services = services;
		
	}
	
	
	

	@Override
	public void generarProceso() {
		
		
		if (services.generarProceso() !=0 ){
			view.procesoOk();
		}else {
			view.procesoError();
		}
		//automotor
		//https://www.comodoro.gov.ar/impuestos/html/autoPdfU.php?clave=FGV999&imputacion=%203
		
		
		//thu
		//https://www.comodoro.gov.ar/impuestos/html/thuPdfU.php?clave=14703&imputacion=%2097
		
		
		//inmobiliario
		//https://www.comodoro.gov.ar/impuestos/html/pdfinmoU.php?clave=14703&imputacion=%201
		
		//ocupante
		//https://www.comodoro.gov.ar/impuestos/html/pdfOcu.php?clave=11310&imputacion=%2017
		
		//ocupantethu
		//https://www.comodoro.gov.ar/impuestos/html/PdfOcuThu.php?clave=18664&imputacion=%2097
		
		
	}




	@Override
	public void obtenerCuerpoMail() {
		
		String cuerpoMail = services.obtenerCuerpoMail();
		view.getViewAdministradorCuerpo().mostrarCuerpoMail(cuerpoMail);
	}




	@Override
	public void presuscripcionesJuridicas() {
		
		BeanItemContainer<PreSolicitudConfirmar> preSolicitudesConfirmar;
		preSolicitudesConfirmar = services.presuscripcionesJuridicas(view.getViewAdministradorCuerpo().getLayoutSuscripciones().getDtfFechaSuscripcionDesde().getValue(),
				view.getViewAdministradorCuerpo().getLayoutSuscripciones().getDtfFechaSuscripcionHasta().getValue());
		if(preSolicitudesConfirmar.size() != 0){
			
			System.out.println("llego:" + preSolicitudesConfirmar.size());
			view.getViewAdministradorCuerpo().getLayoutSuscripciones().setearTabla(preSolicitudesConfirmar);
		}
				
				
	}




	@Override
	public void presuscripcionesFisicas() {

		BeanItemContainer<PreSolicitudConfirmar> preSolicitudesConfirmar;
		preSolicitudesConfirmar = services.presuscripcionesFisicas(view.getViewAdministradorCuerpo().getLayoutSuscripciones().getDtfFechaSuscripcionDesde().getValue(),
				view.getViewAdministradorCuerpo().getLayoutSuscripciones().getDtfFechaSuscripcionHasta().getValue());
		if(preSolicitudesConfirmar.size() != 0){		
			view.getViewAdministradorCuerpo().getLayoutSuscripciones().setearTabla(preSolicitudesConfirmar);
		}
		
	}




	@Override
	public void cancelarPresuscripcion() {
		
		PreSolicitudConfirmar presolicitud = (PreSolicitudConfirmar) view.getViewAdministradorCuerpo().getLayoutSuscripciones().getTablaSuscripciones().getValue();
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");		
		if(services.cancelarPresuscripcion(presolicitud, usuario) !=0){
			view.cancelarPresuscripcionOk();
			view.getViewAdministradorCuerpo().getLayoutSuscripciones().getTablaSuscripciones().removeItem(view.getViewAdministradorCuerpo().getLayoutSuscripciones().getTablaSuscripciones().getValue());
		}else view.cancelarPresuscripcionError();
		
		
		
		
	}




	@Override
	public void cargarProcesados() {
		
		String tipoImpuesto = view.getViewAdministradorCuerpo().getLayoutActualizarEmision().getCmbTipoImpuesto().getValue().toString();
		System.out.println("este es el impuesto seleccionado: " + tipoImpuesto);
		BeanItemContainer<Procesados> containter = services.cargarProcesados(tipoImpuesto);
		
		if(containter.size() != 0){		
			view.getViewAdministradorCuerpo().getLayoutActualizarEmision().cargarProcesosOK(containter);
		}else{
			view.getViewAdministradorCuerpo().getLayoutActualizarEmision().cargarProcesosError();
		}
			
	}




	@Override
	public void eliminarEmision() {
		
		String tipoImpuesto = view.getViewAdministradorCuerpo().getLayoutActualizarEmision().getCmbTipoImpuesto().getValue().toString();		
		if(services.eliminarEmision(tipoImpuesto) !=0){
			view.getViewAdministradorCuerpo().getLayoutActualizarEmision().eliminarEmisionOK();
		}else{
			view.getViewAdministradorCuerpo().getLayoutActualizarEmision().eliminarEmisionError();
		}
		
		
	}




	@Override
	public void generarExportarAdheridos() {
		
		Date fechaDesde = view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getDtfFechaDesde().getValue();
		Date fechaHasta = view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getDtfFechaHasta().getValue();
		
		if (services.exportarAdheridos(fechaDesde,fechaHasta) !=0){			
			view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getBtnGenerarExportacionAdheridos().setEnabled(false);
			view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getBtnExportar().setEnabled(true);			
		}else{			
			view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getBtnGenerarExportacionAdheridos().setEnabled(true);
			view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getBtnExportar().setEnabled(false);
			view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getDtfFechaDesde().clear();
			view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getDtfFechaHasta().clear();
			view.getViewAdministradorCuerpo().getLayoutExportarAdheridos().generarImportacionAdheridosVacio();
		}
		
	}




	@Override
	public void generarImportarAdheridos() {
		
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
		
		if(services.importarAdheridos(usuario) != 0){			
			view.importarAdheridosOK();
		}else{
			view.importarAdheridosError();
		}
		
	}
	
}
