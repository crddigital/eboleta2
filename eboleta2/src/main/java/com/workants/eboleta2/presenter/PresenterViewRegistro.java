package com.workants.eboleta2.presenter;

import java.io.Serializable;


import com.vaadin.ui.UI;
import com.workants.eboleta2.handler.IViewRegistroHandler;
import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.servicio.ServicioRegistro;
import com.workants.eboleta2.ui.view.ViewPrincipal.ViewPrincipal;
import com.workants.eboleta2.ui.view.ViewRegistro.ViewRegistro;
import com.workants.eboleta2.ui.view.responsive.ViewRegistro.ViewRegistroResponsive;

public class PresenterViewRegistro implements IViewRegistroHandler, Serializable{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewRegistro viewRegistro;
	private ViewRegistroResponsive viewRegistroR;
	private ServicioRegistro service;
	
	
	public PresenterViewRegistro(ViewRegistro viewRegistro, ServicioRegistro service){
		
		this.viewRegistro = viewRegistro;
		this.service = service;
	}
	
	public PresenterViewRegistro(ViewRegistroResponsive viewRegistroR, ServicioRegistro service){
		
		this.viewRegistroR = viewRegistroR;
		this.service = service;
	}

	
	
	@Override
	public void subscribir() {
		
		Titular titular = new Titular();		
		titular.setIdPersona(0);
		titular.setApellido(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtApellido().getValue().toUpperCase());
		titular.setNombre(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNombre().getValue().toUpperCase());
		titular.setTipoDocumentoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoDocumento().getValue().toString());
		titular.setNumeroDeDocumentoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeDocumento().getValue());
		titular.setTipoTelefonoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoTelefono().getValue().toString());
		titular.setTipoTelefonoAlternativoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoTelefonoAlternativo().getValue().toString());
		titular.setEstadoPersona(true);	
		
		
		System.out.println(titular.getApellido());
		System.out.println(titular.getTipoDocumentoTitular());
		System.out.println(titular.getNumeroDeDocumentoTitular());
		
		
		
		//telefono y caracteristica 
		if(!viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoTelefono().getValue().toString().equalsIgnoreCase("no posee")){ // o sea es FIJO O CEL
			titular.setNumeroDeTelefonoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtCaracteristica().getValue()+
					viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeTelefono().getValue());	
		}else{
			titular.setNumeroDeTelefonoTitular("no posee telefono");
		}
		
		//telefono y caracteristica alternativo		
		if (!viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoTelefonoAlternativo().getValue().toString().equalsIgnoreCase("no posee")){ // o sea es FIJO O CEL
			titular.setNumeroDeTelefonoAlternativoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtCaracteristicaAlternativo().getValue()+
					viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeTelefonoAlternativo().getValue());
		}else{
			titular.setNumeroDeTelefonoAlternativoTitular("no posee telefono alternativo");
		}			
	
		titular.setCorreoElectronicoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtEmail().getValue());
		
		String nombreImpuesto = (String) UI.getCurrent().getSession().getAttribute("nombreImpuesto");
		String codigo = (String) UI.getCurrent().getSession().getAttribute("codigo");
		String clave = (String) UI.getCurrent().getSession().getAttribute("clave");
		
		
		if (service.subscribir(titular, nombreImpuesto, codigo, clave) != 0){
			viewRegistro.suscribirExitoso();
		} else viewRegistro.suscribirError();
		
		
	}



	@Override
	public void subscribirSolicitante() {
		
		Titular titular = new Titular();	
		titular.setIdPersona(0);
		titular.setApellido(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtApellido().getValue().toUpperCase());
		titular.setNombre(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNombre().getValue().toUpperCase());
		titular.setTipoDocumentoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getCmbTipoDocumento().getValue().toString());
		titular.setNumeroDeDocumentoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNumeroDeDocumento().getValue());
		titular.setTipoTelefonoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getCmbTipoTelefono().getValue().toString());
		
		titular.setNumeroDeTelefonoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtCaracteristica().getValue()+
				viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNumeroDeTelefono().getValue());
		
		
		if (viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getCmbTipoTelefonoAlternativo().getValue() != null){ 
			titular.setTipoTelefonoAlternativoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getCmbTipoTelefonoAlternativo().getValue().toString());
			}
			else {
				titular.setTipoTelefonoAlternativoTitular("");
				}
			
		if (viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getCmbTipoTelefonoAlternativo().getValue() != null){
			titular.setNumeroDeTelefonoAlternativoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNumeroDeTelefonoAlternativo().getValue());
		}   else{
			titular.setNumeroDeTelefonoAlternativoTitular("");
		}
		
		Solicitante solicitante = new Solicitante();
		solicitante.setIdPersona(0);
		solicitante.setApellido(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtApellido().getValue());
		solicitante.setNombre(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNombre().getValue());
		solicitante.setTipoDocumentoTitular(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getCmbTipoDocumento().getValue().toString());
		solicitante.setNumeroDeDocumentoSolicitante(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNumeroDeDocumento().getValue());
		solicitante.setTipoTelefonoSolicitante(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getCmbTipoTelefono().getValue().toString());
		solicitante.setNumeroDeTelefonoSolicitante( viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtCaracteristica().getValue() +
				viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNumeroDeTelefono().getValue());
		solicitante.setCorreoElectronicoSolicitante(viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtEmail().getValue());
		

		
		
		
		String nombreImpuesto = (String) UI.getCurrent().getSession().getAttribute("nombreImpuesto");
		String codigo = (String) UI.getCurrent().getSession().getAttribute("codigo");
		String clave = (String) UI.getCurrent().getSession().getAttribute("clave");
		
		if (service.subscribirSolicitante(titular, solicitante, nombreImpuesto, codigo, clave) != 0){
			viewRegistro.subscribirSolicitanteExitoso();
		}else viewRegistro.subscribirSolicitanteErroneo();
	}



	@Override
	public void limpiar() {
		
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtApellido().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNombre().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtEmail().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtEmailReingreso().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeDocumento().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeTelefono().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeTelefonoAlternativo().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtCaracteristica().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getTxtCaracteristicaAlternativo().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoDocumento().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoTelefono().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoTelefonoAlternativo().clear();


	}



	@Override
	public void volver() {
		
		UI.getCurrent().getSession().close(); // cerramos todo las variables de sesion
		UI.getCurrent().getUI().getPage().setLocation(ViewPrincipal.NAME); //redireccionamos
		
	}



	@Override
	public void limpiarSolicitante() {
		
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtApellido().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNombre().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNumeroDeDocumento().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNumeroDeTelefono().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtCaracteristica().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNumeroDeTelefonoAlternativo().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtCaracteristicaAlternativo().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getCmbTipoDocumento().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getCmbTipoTelefono().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getCmbTipoTelefonoAlternativo().clear();
		
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtApellido().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNombre().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtEmail().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNumeroDeDocumento().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNumeroDeTelefono().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getCmbTipoDocumento().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getCmbTipoTelefono().clear();			
		viewRegistro.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtCaracteristica().clear();
		
	}



	@Override
	public void subscribirJuridica() {
		
		Juridica juridica = new Juridica();
		juridica.setId(0);
		juridica.setCuit(viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtCuit().getValue());
		juridica.setRazonSocial(viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtRazonSocial().getValue());
		juridica.setTipoTelefono(viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getCmbTipoTelefono().getValue().toString());
		juridica.setTipoTelefonoAlternativo(viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getCmbTipoTelefonoAlternativo().getValue().toString());
		juridica.setCorreoElectronico(viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtEmail().getValue());
		
		if(!viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getCmbTipoTelefono().getValue().toString().equalsIgnoreCase("no posee")){ // o sea es FIJO O CEL
			juridica.setNumeroDeTelefono(viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtCaracteristica().getValue()+
					viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtNumeroDeTelefono().getValue());	
		}else{
			juridica.setNumeroDeTelefono("no posee telefono");
		}
		
		if (!viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getCmbTipoTelefonoAlternativo().getValue().toString().equalsIgnoreCase("no posee")){ // o sea es FIJO O CEL
			juridica.setNumeroDeTelefonoAlternativo(viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtCaracteristicaAlternativo().getValue()+
					viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtNumeroDeTelefonoAlternativo().getValue());
		}else{
			juridica.setNumeroDeTelefonoAlternativo("no posee telefono alternativo");
		}		
		
		juridica.setTipo("");
		juridica.setEstado(true);	
		
			
		String nombreImpuesto = (String) UI.getCurrent().getSession().getAttribute("nombreImpuesto");
		String codigo = (String) UI.getCurrent().getSession().getAttribute("codigo");
		String clave = (String) UI.getCurrent().getSession().getAttribute("clave");
		
		if (service.subscribirJuridica(juridica, nombreImpuesto, codigo, clave) != 0){
			viewRegistro.suscribirJuridicaExitoso();		
		} else viewRegistro.suscribirJuridicasError();
		
	}



	@Override
	public void limpiarJuridica() {
		
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtCuit().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtRazonSocial().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getCmbTipoTelefono().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtCaracteristica().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtNumeroDeTelefono().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getCmbTipoTelefonoAlternativo().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtCaracteristicaAlternativo().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtNumeroDeTelefonoAlternativo().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtEmail().clear();
		viewRegistro.getViewRegistroCuerpo().getLayoutCuit().getTxtEmailReingreso().clear();
		
		
	}

}
