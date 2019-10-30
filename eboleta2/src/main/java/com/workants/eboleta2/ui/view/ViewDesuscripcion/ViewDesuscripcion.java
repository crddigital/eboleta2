package com.workants.eboleta2.ui.view.ViewDesuscripcion;



import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.IViewDesuscripcion;
import com.workants.eboleta2.handler.IViewDesuscripcionHandler;

public class ViewDesuscripcion extends VerticalLayout implements IViewDesuscripcion, ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewDesuscripcionEncabezado viewDesuscripcionEncabezado;
	private ViewDesuscripcionCuerpo viewDesuscripcionCuerpo;
	private IViewDesuscripcionHandler handler;
	public static final String NAME = "viewDesuscripcion";
	
	
	
	public ViewDesuscripcion(){
		
		setMargin(true);		
		addComponent(generarEncabezado());
		addComponent(generarCuerpo());
		
		
		
		
		
		
		//listeners
		this.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getBtnAceptar().addClickListener(this);
		this.getViewDesuscripcionEncabezado().getBtnSalir().addClickListener(this);
		
		this.getViewDesuscripcionCuerpo().getLayoutRespuesta().getBtnVolver().addClickListener(this);
		this.getViewDesuscripcionCuerpo().getLayoutRespuesta().getBtnSolicitarBaja().addClickListener(this);
	}



	private Component generarCuerpo() {
	
		this.setViewDesuscripcionCuerpo(new ViewDesuscripcionCuerpo());
		return this.getViewDesuscripcionCuerpo();
	}



	private Component generarEncabezado() {
	
		this.setViewDesuscripcionEncabezado(new ViewDesuscripcionEncabezado());
		return this.getViewDesuscripcionEncabezado();
	}



	public ViewDesuscripcionEncabezado getViewDesuscripcionEncabezado() {
		return viewDesuscripcionEncabezado;
	}



	public void setViewDesuscripcionEncabezado(
			ViewDesuscripcionEncabezado viewDesuscripcionEncabezado) {
		this.viewDesuscripcionEncabezado = viewDesuscripcionEncabezado;
	}



	public ViewDesuscripcionCuerpo getViewDesuscripcionCuerpo() {
		return viewDesuscripcionCuerpo;
	}



	public void setViewDesuscripcionCuerpo(
			ViewDesuscripcionCuerpo viewDesuscripcionCuerpo) {
		this.viewDesuscripcionCuerpo = viewDesuscripcionCuerpo;
	}



	@Override
	public void enter(ViewChangeEvent event) {
	
		//Notification.show("Remove me !!!");
		
	}



	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getBtnAceptar()){
			
			if(this.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getCmbTipoDeDocumento().getValue() != null  
				&&	!this.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getTxtCodigoPagoElectronico().getValue().isEmpty()
				&&	!this.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().getTxtNumeroDeDocumento().getValue().isEmpty())
			handler.buscarImpuestoBaja();
		}
		if(event.getSource() == this.getViewDesuscripcionCuerpo().getLayoutRespuesta().getBtnVolver()){
			
			this.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().setVisible(true);
			this.getViewDesuscripcionCuerpo().getLayoutRespuesta().setVisible(false);
			this.getViewDesuscripcionCuerpo().getLayoutRespuesta().getLblRespuesta().setValue("");
		}
		
		if(event.getSource() == this.getViewDesuscripcionCuerpo().getLayoutRespuesta().getBtnSolicitarBaja()){
			
			ConfirmDialog.show(UI.getCurrent(), "Atención", "Al aceptar recibirá un correo electrónico "
					+ "para confirmar la desuscripción. ¿Desea continuar?", "Si", "No", 
					new ConfirmDialog.Listener() {
						
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void onClose(ConfirmDialog rta) {
							
							if(rta.isConfirmed()){					
								handler.generarMailBaja();
								getViewDesuscripcionCuerpo().getLayoutDesuscripcion().limpiar();
							}
						}
					});
		}
		
		if(event.getSource() == this.getViewDesuscripcionEncabezado().getBtnSalir()){
			
			ConfirmDialog.show(UI.getCurrent(), "Atención", "Desea salir del proceso de desuscripción?", "SI", "NO", new ConfirmDialog.Listener() {
				
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



	public IViewDesuscripcionHandler getHandler() {
		return handler;
	}

   

	public void setHandler(IViewDesuscripcionHandler handler) {
		this.handler = handler;
	}



	public void cargarResultado(String rta) {
		
		if(!rta.equalsIgnoreCase("")){
			this.getViewDesuscripcionCuerpo().getLayoutDesuscripcion().setVisible(false);	
			this.getViewDesuscripcionCuerpo().getLayoutRespuesta().setVisible(true);
			this.getViewDesuscripcionCuerpo().getLayoutRespuesta().getLblRespuesta().setValue(rta);
		}else{
			Notification notificacion = new Notification("Atención","Verifique datos ingresados. No se encuentra el impuesto para el documento y/o CUIT ingresado", Type.ERROR_MESSAGE,true);
			notificacion.setPosition(Position.MIDDLE_CENTER);
			notificacion.setDelayMsec(3000);
			notificacion.show(Page.getCurrent());		
		}
		
	}



	public void generarMailBajaOK() {
		
		
		 
		
		removeAllComponents();
		addComponent(new Label("<p><b>Se ha enviado exitosamente un mensaje al correo electronico suscripto. Por favor ejecuta el link enviado.. \nPuedes  "
				+ "seguir navegando o <a href='http://www.comodoro.gov.ar/eboleta2/'>regresar</a> para otras operaciones . Gracias</b></p>",ContentMode.HTML));
		
	}



	public void generarMailBajaError() {
		
		removeAllComponents();
		addComponent(new Label("<p><b>Error al procesar baja/envio de correo electronico. \n Por favor comuniquese: .  Gracias</b></p>",ContentMode.HTML));
		
	}

}
