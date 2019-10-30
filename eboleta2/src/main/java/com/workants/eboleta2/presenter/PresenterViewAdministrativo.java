package com.workants.eboleta2.presenter;

import java.io.Serializable;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.handler.IViewAdministrativoHandler;
import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.PreSolicitud;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.servicio.ServicioSolicitudes;
import com.workants.eboleta2.ui.FormLayoutCuit;
import com.workants.eboleta2.ui.FormLayoutEsTitular;
import com.workants.eboleta2.ui.FormLayoutNoEsTitular;
import com.workants.eboleta2.ui.view.ViewAdministrativo.ViewAdministrativo;

public class PresenterViewAdministrativo implements IViewAdministrativoHandler, Serializable{

	
	
	private static final long serialVersionUID = 1L;
	private ViewAdministrativo view;
	private ServicioSolicitudes services;
	
	 

	public PresenterViewAdministrativo(ViewAdministrativo viewAdministrativo,
			ServicioSolicitudes services) {
		
		this.view = viewAdministrativo;
		this.services = services;
	}

	@Override
	public void detalleTitular() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aceptarSuscripcion() {
				
		PreSolicitud preSolicitud = (PreSolicitud) view.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue();
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
		int rta = services.aceptarSuscripcion(preSolicitud, usuario);
		if ( rta != 0){
			view.aceptarSolicitudOK();
		}else {
			view.aceptarSolicitudError(rta);
		}
		
	}

	@Override
	public void cancelarSuscripcion() {
		
		PreSolicitud preSolicitud = (PreSolicitud) view.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue();
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
		int rta = services.cancelarSuscripcion(preSolicitud, usuario);
		if ( rta != 0){
			view.cancelarSuscripcionOK();
		}else {
			view.cancelarSuscripcionError(rta);
		}
		
		
	}

	@Override
	public void buscarSuscriptor() {

		
		String tipoDeDocumento = view.getVentanaBuscarSuscriptor().getCmbTipoDeDocumento().getValue().toString();
		String numeroDeDocumento =  view.getVentanaBuscarSuscriptor().getTxtNumeroDeDocumento().getValue();
		
		BeanItemContainer<PreSolicitud> objects = services.buscarSuscriptro(tipoDeDocumento,numeroDeDocumento);
		
		System.out.println(view.getViewAdministrativoCuerpo().getTablaJuridica());
				System.out.println(view.getViewAdministrativoCuerpo().getTablaSolicitudes());
		
		
		if(objects.size() !=0){ //<--algo trae
			
			if(tipoDeDocumento.equalsIgnoreCase("cuit")){ //<--verifico que busque para cargar en la tabla correspondiente 
					
				
				
				if(view.getViewAdministrativoCuerpo().getComponent(1).getCaption().equalsIgnoreCase("persona fisica")){
					
					view.getViewAdministrativoCuerpo().replaceComponent(view.getViewAdministrativoCuerpo().getTablaSolicitudes(), 
						view.getViewAdministrativoCuerpo().getTablaJuridica());
					
					view.getViewAdministrativoCuerpo().getTablaJuridica().getContainerDataSource().removeAllItems();
					for(Object object : objects.getItemIds()){					
						view.getViewAdministrativoCuerpo().getTablaJuridica().getContainerDataSource().addItem(object);
					}					
				}else{					
					
					view.getViewAdministrativoCuerpo().getTablaJuridica().getContainerDataSource().removeAllItems();
					for(Object object : objects.getItemIds()){					
						view.getViewAdministrativoCuerpo().getTablaJuridica().getContainerDataSource().addItem(object);
					}					
				}				
			}else{
				
				if(view.getViewAdministrativoCuerpo().getComponent(1).getCaption().equalsIgnoreCase("personas juridicas")){
					
					view.getViewAdministrativoCuerpo().replaceComponent(view.getViewAdministrativoCuerpo().getTablaJuridica(), 
							view.getViewAdministrativoCuerpo().getTablaSolicitudes());
					
					view.getViewAdministrativoCuerpo().getTablaSolicitudes().getContainerDataSource().removeAllItems();
					for(Object object : objects.getItemIds()){					
						view.getViewAdministrativoCuerpo().getTablaSolicitudes().getContainerDataSource().addItem(object);
					}
				}else{
					
					view.getViewAdministrativoCuerpo().getTablaSolicitudes().getContainerDataSource().removeAllItems();
					for(Object object : objects.getItemIds()){					
						view.getViewAdministrativoCuerpo().getTablaSolicitudes().getContainerDataSource().addItem(object);
					}					
				}
			}
			
			view.getVentanaBuscarSuscriptor().close();
			
		
			
			
		}else{
			view.buscarSuscriptorNoSeEncuentra();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizarSuscriptor() {
		
		VerticalLayout lay = (VerticalLayout)view.getVentanaEdicionSuscriptor().getContent();
		String nombreImpuesto = view.getVentanaEdicionSuscriptor().getItemTabla().getItemProperty("tipoImpuesto").getValue().toString();
		String codigo = view.getVentanaEdicionSuscriptor().getItemTabla().getItemProperty("codigoPagoElectronico").getValue().toString();
		
		if(lay.getComponent(0).getClass().getSimpleName().equalsIgnoreCase("FormLayoutEsTitular")){
			
			FormLayoutEsTitular formEsTitular = (FormLayoutEsTitular)lay.getComponent(0);
			
			Titular titular = new Titular();
			Titular titularTabla = (Titular)view.getVentanaEdicionSuscriptor().getItemTabla().getItemProperty("titular").getValue(); //recupero id			
			
			titular.setIdPersona(titularTabla.getIdPersona());
			titular.setApellido(formEsTitular.getTxtApellido().getValue().toUpperCase());
			titular.setNombre(formEsTitular.getTxtNombre().getValue().toUpperCase());
			titular.setTipoDocumentoTitular(formEsTitular.getCmbTipoDocumento().getValue().toString());
			titular.setNumeroDeDocumentoTitular(formEsTitular.getTxtNumeroDeDocumento().getValue());
			titular.setTipoTelefonoTitular(formEsTitular.getCmbTipoTelefono().getValue().toString());
			titular.setTipoTelefonoAlternativoTitular(formEsTitular.getCmbTipoTelefonoAlternativo().getValue().toString());
			titular.setEstadoPersona(true);	
			
			
			
			//telefono y caracteristica 
			if(!formEsTitular.getCmbTipoTelefono().getValue().toString().equalsIgnoreCase("no posee")){ // o sea es FIJO O CEL
				titular.setNumeroDeTelefonoTitular(formEsTitular.getTxtCaracteristica().getValue()+
						formEsTitular.getTxtNumeroDeTelefono().getValue());	
			}else{
				titular.setNumeroDeTelefonoTitular("");
			}
			
			//telefono y caracteristica alternativo		
			if (!formEsTitular.getCmbTipoTelefonoAlternativo().getValue().toString().equalsIgnoreCase("no posee")){ // o sea es FIJO O CEL
				titular.setNumeroDeTelefonoAlternativoTitular(formEsTitular.getTxtCaracteristicaAlternativo().getValue()+
						formEsTitular.getTxtNumeroDeTelefonoAlternativo().getValue());
			}else{
				titular.setNumeroDeTelefonoAlternativoTitular("");
			}			
		
			titular.setCorreoElectronicoTitular(formEsTitular.getTxtEmail().getValue());			
				
			if (services.actualizarSuscriptor(titular, nombreImpuesto, codigo) != 0){
				Item itemUpdate = view.getViewAdministrativoCuerpo().getTablaSolicitudes().getItem(view.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue());		
				itemUpdate.getItemProperty("titular.apellido").setValue(titular.getApellido());
				itemUpdate.getItemProperty("titular.nombre").setValue(titular.getNombre());
				itemUpdate.getItemProperty("titular.tipoDocumentoTitular").setValue(titular.getTipoDocumentoTitular());
				itemUpdate.getItemProperty("titular.numeroDeDocumentoTitular").setValue(titular.getNumeroDeDocumentoTitular());
				itemUpdate.getItemProperty("titular.correoElectronicoTitular").setValue(titular.getCorreoElectronicoTitular());
				view.actualizarSuscriptorExitoso();
			} else view.actualizarSuscriptorError();	
			
						
		}
		
		if(lay.getComponent(0).getClass().getSimpleName().equalsIgnoreCase("FormLayoutNoEsTitular")){
			
			FormLayoutNoEsTitular formNoEsTitular = (FormLayoutNoEsTitular)lay.getComponent(0);
			Titular titular = new Titular();
			Titular titularTabla = (Titular)view.getVentanaEdicionSuscriptor().getItemTabla().getItemProperty("titular").getValue(); //recupero id		
			
			titular.setIdPersona(titularTabla.getIdPersona());
			titular.setApellido(formNoEsTitular.getAcordion().getFormDatosTitular().getTxtApellido().getValue().toUpperCase());
			titular.setNombre(formNoEsTitular.getAcordion().getFormDatosTitular().getTxtNombre().getValue().toUpperCase());
			titular.setTipoDocumentoTitular(formNoEsTitular.getAcordion().getFormDatosTitular().getCmbTipoDocumento().getValue().toString());
			titular.setNumeroDeDocumentoTitular(formNoEsTitular.getAcordion().getFormDatosTitular().getTxtNumeroDeDocumento().getValue());
			titular.setTipoTelefonoTitular(formNoEsTitular.getAcordion().getFormDatosTitular().getCmbTipoTelefono().getValue().toString());
			titular.setNumeroDeTelefonoTitular(formNoEsTitular.getAcordion().getFormDatosTitular().getTxtNumeroDeTelefono().getValue());
			if (formNoEsTitular.getAcordion().getFormDatosTitular().getCmbTipoTelefonoAlternativo().getValue() != null){ 
				titular.setTipoTelefonoAlternativoTitular(formNoEsTitular.getAcordion().getFormDatosTitular().getCmbTipoTelefonoAlternativo().getValue().toString());
				}
				else {
					titular.setTipoTelefonoAlternativoTitular("");
					}
				
			if (formNoEsTitular.getAcordion().getFormDatosTitular().getCmbTipoTelefonoAlternativo().getValue() != null){
				titular.setNumeroDeTelefonoAlternativoTitular(formNoEsTitular.getAcordion().getFormDatosTitular().getTxtNumeroDeTelefonoAlternativo().getValue());
			}   else{
				titular.setNumeroDeTelefonoAlternativoTitular("");
			}
			
			Solicitante solicitante = new Solicitante();
			Solicitante solicitanteTabla = (Solicitante)view.getVentanaEdicionSuscriptor().getItemTabla().getItemProperty("solicitante").getValue(); //recupero id
			solicitante.setIdPersona(solicitanteTabla.getIdPersona());
			solicitante.setApellido(formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtApellido().getValue());
			solicitante.setNombre(formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtNombre().getValue());
			solicitante.setTipoDocumentoTitular(formNoEsTitular.getAcordion().getFormDatosSolicitante().getCmbTipoDocumento().getValue().toString());
			solicitante.setNumeroDeDocumentoSolicitante(formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtNumeroDeDocumento().getValue());
			solicitante.setTipoTelefonoSolicitante(formNoEsTitular.getAcordion().getFormDatosSolicitante().getCmbTipoTelefono().getValue().toString());
			solicitante.setNumeroDeTelefonoSolicitante(formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtNumeroDeTelefono().getValue());
			solicitante.setCorreoElectronicoSolicitante(formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtEmail().getValue());
			
			if (services.actualizarSolicitante(titular, solicitante, nombreImpuesto, codigo) != 0){
				Item itemUpdate = view.getViewAdministrativoCuerpo().getTablaSolicitudes().getItem(view.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue());		
				itemUpdate.getItemProperty("titular.apellido").setValue(titular.getApellido());
				itemUpdate.getItemProperty("titular.nombre").setValue(titular.getNombre());
				itemUpdate.getItemProperty("titular.tipoDocumentoTitular").setValue(titular.getTipoDocumentoTitular());
				itemUpdate.getItemProperty("titular.numeroDeDocumentoTitular").setValue(titular.getNumeroDeDocumentoTitular());
				itemUpdate.getItemProperty("titular.correoElectronicoTitular").setValue(titular.getCorreoElectronicoTitular());				
				view.subscribirSolicitanteExitoso();
			}else view.subscribirSolicitanteError();
		
		}
		
		if(lay.getComponent(0).getClass().getSimpleName().equalsIgnoreCase("FormLayoutCuit")){	
			
			FormLayoutCuit formCuit = (FormLayoutCuit)lay.getComponent(0);
			Juridica juridica = new Juridica();
			Juridica juridicaTabla = (Juridica)view.getVentanaEdicionSuscriptor().getItemTabla().getItemProperty("juridica").getValue(); //recupero id		
			juridica.setId(juridicaTabla.getId());
			juridica.setRazonSocial(formCuit.getTxtRazonSocial().getValue());
			juridica.setCuit(formCuit.getTxtCuit().getValue());
			juridica.setNumeroDeTelefono(formCuit.getTxtNumeroDeTelefono().getValue());
			juridica.setTipoTelefono(formCuit.getCmbTipoTelefono().getValue().toString());
			juridica.setNumeroDeTelefonoAlternativo(formCuit.getTxtNumeroDeTelefonoAlternativo().getValue());
			juridica.setTipoTelefonoAlternativo(formCuit.getCmbTipoTelefonoAlternativo().getValue().toString());
			juridica.setCorreoElectronico(formCuit.getTxtEmail().getValue());
			

			if (services.actualizarJuridica(juridica, nombreImpuesto, codigo) != 0){
				
				Item itemUpdate = view.getViewAdministrativoCuerpo().getTablaJuridica().getItem(view.getViewAdministrativoCuerpo().getTablaJuridica().getValue());				
				itemUpdate.getItemProperty("juridica.razonSocial").setValue(juridica.getRazonSocial());
				itemUpdate.getItemProperty("juridica.cuit").setValue(juridica.getCuit());
				itemUpdate.getItemProperty("juridica.correoElectronico").setValue(juridica.getCorreoElectronico());				
				view.actualizarJuridicaExitoso();
			}else view.actualizarJuridicaError();
			
			
			
		}
		
		
		
	}

}
