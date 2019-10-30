package com.workants.eboleta2.ui.view.ViewAdministrativo;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Item;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.IViewAdministrativo;
import com.workants.eboleta2.componentes.TextFieldMail;
import com.workants.eboleta2.handler.IViewAdministrativoHandler;
import com.workants.eboleta2.model.PreSolicitud;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.ui.FormLayoutCuit;
import com.workants.eboleta2.ui.FormLayoutEsTitular;
import com.workants.eboleta2.ui.FormLayoutNoEsTitular;
import com.workants.eboleta2.ui.VentanaDetalleTitular;

public class ViewAdministrativo extends VerticalLayout implements IViewAdministrativo, ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "viewAdministrativo";
	private IViewAdministrativoHandler handler;
	private ViewAdministrativoEncabezado viewAdministrativoEncabezado;
	private ViewAdministrativoCuerpo viewAdministrativoCuerpo;
	private VentanaBuscarSuscriptor ventanaBuscarSuscriptor = new VentanaBuscarSuscriptor();
	private VentanaEdicionSuscriptor ventanaEdicionSuscriptor = new VentanaEdicionSuscriptor();
	
	public ViewAdministrativo(){
		
		setMargin(true);
		addComponent(generarEncabezado());
		addComponent(new Label("<hr />", ContentMode.HTML));
		addComponent(generarCuerpo());
		
		
		//Escuchadores
		this.getViewAdministrativoCuerpo().getBtnJuridico().addClickListener(this);
		this.getViewAdministrativoCuerpo().getBtnFisico().addClickListener(this);
		this.getViewAdministrativoCuerpo().getBtnDetalleTitular().addClickListener(this);;
		this.getViewAdministrativoCuerpo().getBtnAprobarPresolicitud().addClickListener(this);
		this.getViewAdministrativoCuerpo().getBtnActulizarPresolicitudes().addClickListener(this);
		this.getViewAdministrativoCuerpo().getBtnBajaPresolicitud().addClickListener(this);
		this.getViewAdministrativoEncabezado().getBtnSalir().addClickListener(this);
		this.getViewAdministrativoCuerpo().getBtnEditarSuscriptor().addClickListener(this);
		
		
		
		
		//Ventana Suscriptor
		this.getViewAdministrativoCuerpo().getBtnBuscarSuscriptor().addClickListener(this);
		this.getVentanaBuscarSuscriptor().getBtnBuscar().addClickListener(this);
		
		
		
		
		
	}


	
	
	private Component generarCuerpo() {
		
		this.setViewAdministrativoCuerpo(new ViewAdministrativoCuerpo());
		return this.getViewAdministrativoCuerpo();
	}




	private Component generarEncabezado() {
		
		this.setViewAdministrativoEncabezado(new ViewAdministrativoEncabezado());
		return this.getViewAdministrativoEncabezado();
	}




	public ViewAdministrativoEncabezado getViewAdministrativoEncabezado() {
		return viewAdministrativoEncabezado;
	}




	public void setViewAdministrativoEncabezado(
			ViewAdministrativoEncabezado viewAdministrativoEncabezado) {
		this.viewAdministrativoEncabezado = viewAdministrativoEncabezado;
	}




	public ViewAdministrativoCuerpo getViewAdministrativoCuerpo() {
		return viewAdministrativoCuerpo;
	}




	public void setViewAdministrativoCuerpo(
			ViewAdministrativoCuerpo viewAdministrativoCuerpo) {
		this.viewAdministrativoCuerpo = viewAdministrativoCuerpo;
	}




	@Override
	public void enter(ViewChangeEvent event) {
		
		Notification.show("Vista Administrativo");
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
		this.getViewAdministrativoEncabezado().getLblEncabezado().setCaption("Bienvenido: " + usuario.getApellido().toUpperCase() + ","+usuario.getNombre().toUpperCase());
		
	}


	@Override
	public void setHandler(IViewAdministrativoHandler handler) {
		
		this.handler = handler;
		
	}


	@Override
	public IViewAdministrativoHandler getHandler() {
		
		return this.handler;
	
	}




	@Override
	public void buttonClick(ClickEvent event) {
	
		PreSolicitud presolicitud = (PreSolicitud) this.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue();
		
		if(event.getSource() == this.getViewAdministrativoCuerpo().getBtnDetalleTitular()){
				
			if(presolicitud.getSolicitante() != null){
				VentanaDetalleTitular ventanaDetalleTitular = new VentanaDetalleTitular(presolicitud);			
				UI.getCurrent().addWindow(ventanaDetalleTitular);
			}
		}
		
		if(event.getSource() == this.getViewAdministrativoCuerpo().getBtnAprobarPresolicitud()){
				
			if(this.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue() !=null){
			ConfirmDialog.show(UI.getCurrent(), "Atención", "¿Desea aprobar solicitud?","Si", "No", new ConfirmDialog.Listener() {
				
				private static final long serialVersionUID = 1L;
				@Override
				public void onClose(ConfirmDialog dialog) {
					
					if(dialog.isConfirmed()){
						handler.aceptarSuscripcion();
					}else {
						
					}
				}
			});			
		  }
		}	
		
		
		if(event.getSource() == this.getViewAdministrativoCuerpo().getBtnActulizarPresolicitudes()){
			
			
			this.getViewAdministrativoCuerpo().actualizarLabel();			
		}
		
		if(event.getSource() == this.getViewAdministrativoCuerpo().getBtnBajaPresolicitud()){
			
			if(this.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue() !=null){
				ConfirmDialog.show(UI.getCurrent(), "Atención", "¿Desea CANCELAR la solicitud seleccionada?","Si", "No", new ConfirmDialog.Listener() {
					
					private static final long serialVersionUID = 1L;
					@Override
					public void onClose(ConfirmDialog dialog) {
						
						if(dialog.isConfirmed()){
							handler.cancelarSuscripcion();
						}else {
							
						}
					}
				});			
			  }
		}
		if(event.getSource() == this.getViewAdministrativoCuerpo().getBtnFisico()){
			
			this.getViewAdministrativoCuerpo().replaceComponent(this.getViewAdministrativoCuerpo().getTablaJuridica(), this.getViewAdministrativoCuerpo().getTablaSolicitudes());
			this.getViewAdministrativoCuerpo().replaceComponent(this.getViewAdministrativoCuerpo().getLblCantidadPresolicitudesJuridicas(),
					this.getViewAdministrativoCuerpo().getLblCantidadPresolicitudes());
		}
		
		if(event.getSource() == this.getViewAdministrativoCuerpo().getBtnBuscarSuscriptor()){
			
			
			UI.getCurrent().addWindow(this.getVentanaBuscarSuscriptor());		
			
		}
		
		if(event.getSource() == this.getVentanaBuscarSuscriptor().getBtnBuscar()){			
			
			handler.buscarSuscriptor();
			
		}
		
		if(event.getSource() == this.getViewAdministrativoCuerpo().getBtnJuridico()){
			
			this.getViewAdministrativoCuerpo().replaceComponent(this.getViewAdministrativoCuerpo().getTablaSolicitudes(), this.getViewAdministrativoCuerpo().getTablaJuridica());
			this.getViewAdministrativoCuerpo().replaceComponent(this.getViewAdministrativoCuerpo().getLblCantidadPresolicitudes(),
					this.getViewAdministrativoCuerpo().getLblCantidadPresolicitudesJuridicas());
		}
		
		
		if(event.getSource() == this.getViewAdministrativoCuerpo().getBtnEditarSuscriptor()){
			
			Table table = (Table)this.getViewAdministrativoCuerpo().getComponent(1);
			
			if(table.getValue() != null){
				
				Item itemTabla = (Item) table.getItem(table.getValue());
				this.getVentanaEdicionSuscriptor().init(itemTabla);				
				UI.getCurrent().addWindow(this.getVentanaEdicionSuscriptor());
				
				//Ventana Edicion 
				//<--recien activo el escuchador aca
				this.getVentanaEdicionSuscriptor().getBtnEditarSuscriptor().addClickListener(this);
			}
			
			//101: Item itemTabla = view.getTablaUsuarios().getItem(view.getTablaUsuarios().getValue());  
			
			
			
		}
		
		if(event.getSource() == this.getVentanaEdicionSuscriptor().getBtnEditarSuscriptor()){
			
			VerticalLayout lay = (VerticalLayout)this.getVentanaEdicionSuscriptor().getContent();
			
			if(lay.getComponent(0).getClass().getSimpleName().equalsIgnoreCase("FormLayoutEsTitular")){
				
				FormLayoutEsTitular formEsTitular = (FormLayoutEsTitular)lay.getComponent(0);
				
				if (formEsTitular.getTxtApellido().isValid() &&
						formEsTitular.getTxtNombre().isValid() &&
						formEsTitular.getTxtNumeroDeDocumento().isValid() &&
						formEsTitular.getTxtNumeroDeTelefono().isValid() &&
						formEsTitular.getTxtEmail().isValid() &&
				validarCmbTelefono(formEsTitular.getCmbTipoTelefono(),formEsTitular.getTxtCaracteristica(),formEsTitular.getTxtNumeroDeTelefono()) &&
				validarCmbTelefonoAlternativo(formEsTitular)&&
				validarCorreos(formEsTitular.getTxtEmail(), formEsTitular.getTxtEmailReingreso())){		
			    
				this.getHandler().actualizarSuscriptor();
		}else{
			 Notification.show("Atención","Faltan completar campos",Type.ERROR_MESSAGE);
		}				
			}
			
			
			if(lay.getComponent(0).getClass().getSimpleName().equalsIgnoreCase("FormLayoutNoEsTitular")){
			
				
				FormLayoutNoEsTitular formNoEsTitular = (FormLayoutNoEsTitular)lay.getComponent(0);
				
				if(formNoEsTitular.getAcordion().getFormDatosTitular().getTxtApellido().isValid() && 
				   		   formNoEsTitular.getAcordion().getFormDatosTitular().getTxtNombre().isValid() &&
				   		   formNoEsTitular.getAcordion().getFormDatosTitular().getTxtNumeroDeDocumento().isValid() &&
				   		   formNoEsTitular.getAcordion().getFormDatosTitular().getTxtNumeroDeTelefono().isValid() &&
				   		   formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtApellido().isValid() &&
				   		   formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtNombre().isValid() &&
				   		   formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtNumeroDeDocumento().isValid() &&
				   		   formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtNumeroDeTelefono().isValid()&&
				   		   validarCorreosNoEsTitular(formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtEmail(),
				   				formNoEsTitular.getAcordion().getFormDatosSolicitante().getTxtEmailReingreso())){
						    getHandler().actualizarSuscriptor();
						}else{
							Notification aviso = new Notification("Faltan competar campos", Notification.Type.ERROR_MESSAGE);			
							aviso.setDelayMsec(3000);
							aviso.show(Page.getCurrent());
						}
						//Notification.show("Faltan completar campos");			
				}	
			if(lay.getComponent(0).getClass().getSimpleName().equalsIgnoreCase("FormLayoutCuit")){
				
				FormLayoutCuit formCuit = (FormLayoutCuit)lay.getComponent(0);
				
				if(formCuit.getTxtCuit().isValid() &&
						formCuit.getTxtRazonSocial().isValid() &&
						formCuit.getTxtCaracteristica().isValid()&&
						formCuit.getTxtNumeroDeTelefono().isValid()&&
						formCuit.getTxtCaracteristicaAlternativo().isValid()&&
						formCuit.getTxtNumeroDeTelefonoAlternativo().isValid()&&
						formCuit.getTxtEmail().isValid()&&
						formCuit.getTxtEmailReingreso().isValid()&&
						validarCmbTelefono(formCuit.getCmbTipoTelefono(), formCuit.getTxtCaracteristica(),formCuit.getTxtNumeroDeTelefono()) &&	
						validarCmbTelefono(formCuit.getCmbTipoTelefonoAlternativo(),formCuit.getTxtCaracteristicaAlternativo(),formCuit.getTxtNumeroDeTelefonoAlternativo()) &&
						validarCorreos(formCuit.getTxtEmail(),formCuit.getTxtEmailReingreso())	){
					getHandler().actualizarSuscriptor();
				}else{
					Notification aviso = new Notification("Faltan competar campos", Notification.Type.ERROR_MESSAGE);			
					aviso.setDelayMsec(3000);
					aviso.show(Page.getCurrent());
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		}
		
		
		if(event.getSource() == this.getViewAdministrativoEncabezado().getBtnSalir()){
			
			ConfirmDialog.show(UI.getCurrent(), "Atecnión", "Desea cerrar sesión?", "SI", "NO", new ConfirmDialog.Listener() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClose(ConfirmDialog rta) {
					
					if(rta.isConfirmed()){
						
						VaadinSession.getCurrent().getSession().invalidate();
						Page.getCurrent().setLocation("/eboleta2/");					
					}
					
				}
			});
			
		}
	}
	
	private boolean validarCmbTelefono(ComboBox cmbTipoTelefono, TextField caracteristica, TextField numero) {
		
		boolean rta = true;
		if(!cmbTipoTelefono.getValue().toString().equalsIgnoreCase("no posee")){			
			if(numero.isValid() && caracteristica.isValid()){
				return rta;
			}else{
				return rta = false;
			}
		}
		System.out.println(rta);
		return rta;		
	}
	
	private boolean validarCmbTelefonoAlternativo(FormLayoutEsTitular formEsTitular) {
		
		boolean rta = true;
		if(!formEsTitular.getCmbTipoTelefonoAlternativo().getValue().toString().equalsIgnoreCase("no posee")){
			if(formEsTitular.getTxtNumeroDeTelefonoAlternativo().isValid()
					&& formEsTitular.getTxtCaracteristicaAlternativo().isValid()){
				return rta;
			}else{
				return rta = false;
			}
		}
		
		return rta;
	}
	
	private boolean validarCorreos(TextFieldMail email, TextFieldMail emailReingreso) { //que sean iguales......
		
		boolean rta = true;
		if(!email.getValue().equalsIgnoreCase(emailReingreso.getValue())){			
				return rta = false;
			}		
		return rta;
	}
	
	private boolean validarCorreosNoEsTitular(TextField email, TextFieldMail emailReingreso) {
		

		boolean rta = true;
		if(!email.getValue().equalsIgnoreCase(
			emailReingreso.getValue())){			
				return rta = false;
			}		
		return rta;
		
	}




	public void aceptarSolicitudOK() {
		
		Notification notificacion = new Notification("Atención","Presolicitud aprobada ", Type.ASSISTIVE_NOTIFICATION);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		this.getViewAdministrativoCuerpo().getTablaSolicitudes().removeItem(this.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue());
		this.getViewAdministrativoCuerpo().actualizarLabel();
		
	}




	public void aceptarSolicitudError(int rta) {
		
		Notification notificacion = new Notification("Atención","Presolicitud no ha podido ser aprobada ", Type.ERROR_MESSAGE);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		
		
	}




	public void cancelarSuscripcionOK() {
		
		Notification notificacion = new Notification("Atención","Presolicitud cancelada exitosamente ", Type.ASSISTIVE_NOTIFICATION);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		this.getViewAdministrativoCuerpo().getTablaSolicitudes().removeItem(this.getViewAdministrativoCuerpo().getTablaSolicitudes().getValue());
		this.getViewAdministrativoCuerpo().actualizarLabel();
		
	}




	public void cancelarSuscripcionError(int rta) {
		
		Notification notificacion = new Notification("Atención","Presolicitud no ha podido ser cancelada ", Type.ERROR_MESSAGE);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		
	}




	public VentanaBuscarSuscriptor getVentanaBuscarSuscriptor() {
		return ventanaBuscarSuscriptor;
	}




	public void setVentanaBuscarSuscriptor(
			VentanaBuscarSuscriptor ventanaBuscarSuscriptor) {
		this.ventanaBuscarSuscriptor = ventanaBuscarSuscriptor;
	}




	public void buscarSuscriptorNoSeEncuentra() {
	
		Notification notificacion = new Notification("Atención","No se encuentran datos. Verifique Tipo de Documento y/o número", Type.ERROR_MESSAGE);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		
	}




	public VentanaEdicionSuscriptor getVentanaEdicionSuscriptor() {
		return ventanaEdicionSuscriptor;
	}




	public void setVentanaEdicionSuscriptor(
			VentanaEdicionSuscriptor ventanaEdicionSuscriptor) {
		this.ventanaEdicionSuscriptor = ventanaEdicionSuscriptor;
	}




	public void actualizarSuscriptorExitoso() {
		
		Notification notificacion = new Notification("Atención","Datos actualizados correctamente. Se envio un email al titular", Type.ASSISTIVE_NOTIFICATION);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		this.getVentanaEdicionSuscriptor().close();
		
	}




	public void actualizarSuscriptorError() {
		
		Notification notificacion = new Notification("Atención","Error al actualizar datos del suscriptor", Type.ERROR_MESSAGE);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		
	}




	public void subscribirSolicitanteExitoso() {
		
		Notification notificacion = new Notification("Atención","Datos actualizados correctamente. Se envio un email al solicitante", Type.ASSISTIVE_NOTIFICATION);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		this.getVentanaEdicionSuscriptor().close();
		
	}




	public void subscribirSolicitanteError() {
		
		Notification notificacion = new Notification("Atención","Error al actualizar datos del solicitante", Type.ERROR_MESSAGE);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		
	}




	public void actualizarJuridicaExitoso() {
		
		Notification notificacion = new Notification("Atención","Datos actualizados correctamente. Se envio un email al solicitante", Type.ASSISTIVE_NOTIFICATION);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		this.getVentanaEdicionSuscriptor().close();
		
		
	}




	public void actualizarJuridicaError() {
		
		Notification notificacion = new Notification("Atención","Error al actualizar datos del solicitante", Type.ERROR_MESSAGE);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		
	}

}
