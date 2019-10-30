package com.workants.eboleta2.ui.view.ViewAdministrador;


import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.IViewAdministrador;
import com.workants.eboleta2.handler.IViewAdministradorHandler;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.presenter.PresenterLayoutABMUsuarios;
import com.workants.eboleta2.servicio.ServicioUsuario;
import com.workants.eboleta2.ui.formularios.LayoutABMUsuarios;

public class ViewAdministrador extends VerticalLayout implements IViewAdministrador, ClickListener, ValueChangeListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "viewAdministrador";
	private IViewAdministradorHandler handler;
	private ViewAdministradorEncabezado viewAdministradorEncabezado;	
	private ViewAdministradorCuerpo viewAdministradorCuerpo;
	 	

	public ViewAdministrador(){
		
		//setPrimaryStyleName(ValoTheme.MENU_ROOT);
		setMargin(true);
		addComponent(generarEncabezado());
		addComponent(new Label("<hr />", ContentMode.HTML));
		addComponent(generarCuerpo());
	    
	
	    //listeners
	    this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnAbmUsuario().addClickListener(this);
	    this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnOperaciones().addClickListener(this);
	 //   this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnLogout().addClickListener(this);
	    this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnSuscripciones().addClickListener(this);
	    this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnActualizarEmision().addClickListener(this);
	    this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnExportarInscripciones().addClickListener(this);
	    this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnImportarInscripciones().addClickListener(this);
	    this.getViewAdministradorCuerpo().getLayoutOperaciones().getBtnGenerarEnvio().addClickListener(this);
	    
	    
	    this.getViewAdministradorEncabezado().getBtnSalir().addClickListener(this);
	    
	    
	    
	    
	    //suscripciones 
	    this.getViewAdministradorCuerpo().getLayoutSuscripciones().getBtnFisicas().addClickListener(this);
	    this.getViewAdministradorCuerpo().getLayoutSuscripciones().getBtnJuridicas().addClickListener(this);
	    this.getViewAdministradorCuerpo().getLayoutSuscripciones().getBtnCancelar().addClickListener(this);
	    
	    
	    
	    
	    this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnCuerpoMail().addClickListener(this);

	    //Emision
	    this.getViewAdministradorCuerpo().getLayoutActualizarEmision().getCmbTipoImpuesto().addValueChangeListener(this);
	    this.getViewAdministradorCuerpo().getLayoutActualizarEmision().getBtnEliminarEmision().addClickListener(this);
	    
	    //Exportacion
	    this.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getBtnGenerarExportacionAdheridos().addClickListener(this);
	 
	    //Importacion
	    this.getViewAdministradorCuerpo().getLayoutImportarAdheridos().getBtnProcesarAdheridos().addClickListener(this);
	    	
	}	
	
	private Component generarEncabezado() {
		
		this.setViewAdministradorEncabezado(new ViewAdministradorEncabezado());
		return this.getViewAdministradorEncabezado();
	}



	private Component generarCuerpo() {
		
		this.setViewAdministradorCuerpo(new ViewAdministradorCuerpo());		
		return this.getViewAdministradorCuerpo();
	}



	@Override
	public void enter(ViewChangeEvent event) {
		
		Notification.show("Vista Adminstrador");		
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
		this.getViewAdministradorEncabezado().getLblEncabezado().setCaption("Bienvenido: " + usuario.getApellido().toUpperCase() + ","+usuario.getNombre().toUpperCase());
		
	}

	@Override
	public void setHandler(IViewAdministradorHandler handler) {
		
		this.handler = handler;
		
	}

	@Override
	public IViewAdministradorHandler getHandler() {
		
		return this.handler;
	}
	
	public ViewAdministradorCuerpo getViewAdministradorCuerpo() {
		return viewAdministradorCuerpo;
	}



	public void setViewAdministradorCuerpo(
			ViewAdministradorCuerpo viewAdministradorCuerpo) {
		this.viewAdministradorCuerpo = viewAdministradorCuerpo;
	}



	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnAbmUsuario()){
			
			LayoutABMUsuarios layABMUsuarios = new LayoutABMUsuarios();
			PresenterLayoutABMUsuarios presenterLayABMUsuarios = new PresenterLayoutABMUsuarios(layABMUsuarios,ServicioUsuario.getIntance());
			layABMUsuarios.setHandler(presenterLayABMUsuarios);		
			
			if (this.getViewAdministradorCuerpo().getSplitter().getSecondComponent() != null){
				this.getViewAdministradorCuerpo().getSplitter().removeComponent(this.getViewAdministradorCuerpo().getSplitter().getSecondComponent());
				//this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutABMUsuarios());
				this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(layABMUsuarios);
			} else {
				//this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutABMUsuarios());
				this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(layABMUsuarios);
			}
		}
		
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnOperaciones()){
											
			if (this.getViewAdministradorCuerpo().getSplitter().getSecondComponent() != null){
				this.getViewAdministradorCuerpo().getSplitter().removeComponent(this.getViewAdministradorCuerpo().getSplitter().getSecondComponent());
				this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutOperaciones());
				this.getViewAdministradorCuerpo().getSplitter().getSecondComponent().setSizeFull();
			} else {
				this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutOperaciones());
			}				
		    }
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutOperaciones().getBtnGenerarEnvio()) {
			
			ConfirmDialog.show(UI.getCurrent(), "Atención","Esta seguro de comenzar con "
					+ "el proceso de envio de emails?","Si","No", new ConfirmDialog.Listener() {
				
				
				private static final long serialVersionUID = 1L;
				@Override
				public void onClose(ConfirmDialog rta) {
					if(rta.isConfirmed()){
						handler.generarProceso();
					}
					
				}
			});
		}
		
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnCuerpoMail()){
			
			handler.obtenerCuerpoMail();
		}
		
		if(event.getSource() == this.getViewAdministradorEncabezado().getBtnSalir()){
			
			ConfirmDialog.show(UI.getCurrent(), "Atención", "Desea cerrar sesión?", "SI", "NO", new ConfirmDialog.Listener() {
				
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
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnSuscripciones()){
			
			if (this.getViewAdministradorCuerpo().getSplitter().getSecondComponent() != null){
				this.getViewAdministradorCuerpo().getSplitter().removeComponent(this.getViewAdministradorCuerpo().getSplitter().getSecondComponent());
				this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutSuscripciones());
				this.getViewAdministradorCuerpo().getSplitter().getSecondComponent().setSizeFull();
			} else {
				this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutSuscripciones());
			}
			
		}
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutSuscripciones().getBtnFisicas()){
			
			if(this.getViewAdministradorCuerpo().getLayoutSuscripciones().getDtfFechaSuscripcionDesde().getValue() != null 
					&& this.getViewAdministradorCuerpo().getLayoutSuscripciones().getDtfFechaSuscripcionHasta().getValue() != null){
				
				handler.presuscripcionesFisicas();
			}else{
				Notification.show("Atención", "Seleccione fecha", Type.ERROR_MESSAGE);
			}
		}
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutSuscripciones().getBtnJuridicas()){
			//avanzo
			if(this.getViewAdministradorCuerpo().getLayoutSuscripciones().getDtfFechaSuscripcionDesde().getValue() != null 
					&& this.getViewAdministradorCuerpo().getLayoutSuscripciones().getDtfFechaSuscripcionHasta().getValue() != null){
				handler.presuscripcionesJuridicas();
		} else{
			Notification.show("Atención", "Seleccione fecha", Type.ERROR_MESSAGE);
		}
		}
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutSuscripciones().getBtnCancelar()){
				
			if(this.getViewAdministradorCuerpo().getLayoutSuscripciones().getTablaSuscripciones().getValue() != null){
					
					ConfirmDialog.show(UI.getCurrent(), "Atecnión", "Desea cancelar presolicitud?", "SI", "NO", new ConfirmDialog.Listener() {
						
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void onClose(ConfirmDialog rta) {
							
							if(rta.isConfirmed()){								
								handler.cancelarPresuscripcion();							
							}							
						}
					});			
				}
			}
		if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnActualizarEmision()){
			
			//System.out.println("aca llego: " + this.getViewAdministradorCuerpo().getSplitter().getSecondComponent());
			 
			if (this.getViewAdministradorCuerpo().getSplitter().getSecondComponent() != null){
				this.getViewAdministradorCuerpo().getSplitter().removeComponent(this.getViewAdministradorCuerpo().getSplitter().getSecondComponent());
				this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutActualizarEmision());
				this.getViewAdministradorCuerpo().getSplitter().getSecondComponent().setSizeFull();
			} else {
				this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutActualizarEmision());
			}
			
		}
		
		 if (event.getSource() == this.getViewAdministradorCuerpo().getLayoutActualizarEmision().getBtnEliminarEmision()){
			 
				ConfirmDialog.show(UI.getCurrent(), "Atención", "Desea Eliminar las emisiones ya generadas. Recuerde "
						+ "recibir confirmación de partidas actualizadas para poder eliminar impuesto ya emitidos", "SI", "NO", new ConfirmDialog.Listener() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog rta) {
						
						if(rta.isConfirmed()){								
							handler.eliminarEmision();							
						}							
					}
				});	
			}	
		 
		 if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnExportarInscripciones()){
			 
			 //Notification.show("Hola!!!");
			 if (this.getViewAdministradorCuerpo().getSplitter().getSecondComponent() != null){
					this.getViewAdministradorCuerpo().getSplitter().removeComponent(this.getViewAdministradorCuerpo().getSplitter().getSecondComponent());
					this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutExportarAdheridos());
					this.getViewAdministradorCuerpo().getSplitter().getSecondComponent().setSizeFull();
				} else {
					this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutExportarAdheridos());
				}
			 
		 }
		 
		 
		 
		 if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutLateralIzquierdo().getBtnImportarInscripciones()){
			 
			 if (this.getViewAdministradorCuerpo().getSplitter().getSecondComponent() != null){
					this.getViewAdministradorCuerpo().getSplitter().removeComponent(this.getViewAdministradorCuerpo().getSplitter().getSecondComponent());
					this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutImportarAdheridos());
					this.getViewAdministradorCuerpo().getSplitter().getSecondComponent().setSizeFull();
				} else {
					this.getViewAdministradorCuerpo().getSplitter().setSecondComponent(this.getViewAdministradorCuerpo().generarLayoutImportarAdheridos());
				}
		 }
		 
		 if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutExportarAdheridos().getBtnGenerarExportacionAdheridos()){
			 
			//validar fechas, desde, hasta, hasta no puede ser menor que desde
			 handler.generarExportarAdheridos();			 
		 }		 
		 
		 if(event.getSource() == this.getViewAdministradorCuerpo().getLayoutImportarAdheridos().getBtnProcesarAdheridos()){
		
			 handler.generarImportarAdheridos();
		 }

	}


	public ViewAdministradorEncabezado getViewAdministradorEncabezado() {
		return viewAdministradorEncabezado;
	}



	public void setViewAdministradorEncabezado(
			ViewAdministradorEncabezado viewAdministradorEncabezado) {
		this.viewAdministradorEncabezado = viewAdministradorEncabezado;
	}

	public void procesoOk() {
		
		Notification.show("Operación exitosa", Type.HUMANIZED_MESSAGE);
		
	}

	public void procesoError() {
		
		Notification.show("Error al procesar envio de mails", Type.ERROR_MESSAGE);
		
		
	}

	public void mostrarCuerpoMail(String cuerpoMail) {
		// TODO Auto-generated method stub
		
	}

	public void cancelarPresuscripcionError() {
		
		Notification.show("Error al procesar desuscripción", Type.ERROR_MESSAGE);
	}

	public void cancelarPresuscripcionOk() {
		
		Notification.show("Desuscripcion generarada exitosa", Type.HUMANIZED_MESSAGE);
		
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getViewAdministradorCuerpo().getLayoutActualizarEmision().getCmbTipoImpuesto()){
			
			handler.cargarProcesados();
			
		}
		
	}

	public void importarAdheridosOK() {
		
		Notification.show("Adheridos procesados exitosamente", Type.HUMANIZED_MESSAGE);
		
	}

	public void importarAdheridosError() {
		
		
		Notification.show("Error al procesar adheridos", Type.ERROR_MESSAGE);
		
	}




	
	

}
