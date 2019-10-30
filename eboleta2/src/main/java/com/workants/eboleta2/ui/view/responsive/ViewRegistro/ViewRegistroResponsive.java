package com.workants.eboleta2.ui.view.responsive.ViewRegistro;




import org.apache.log4j.Logger;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.IViewRegistro;
import com.workants.eboleta2.componentes.TextFieldMail;
import com.workants.eboleta2.handler.IViewRegistroHandler;
import com.workants.eboleta2.ui.view.ViewPrincipal.ViewPrincipal;

public class ViewRegistroResponsive extends VerticalLayout implements IViewRegistro, ValueChangeListener, ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IViewRegistroHandler handler;
	private ViewRegistroEncabezadoResponsive viewRegistroEncabezado;
	private ViewRegistroCuerpoResponsive viewRegistroCuerpo;
	
	public static final String NAME = "viewRegistrol";
	
	
	//public static Logger log = Logger.getLogger(ViewRegistroResponsive.class);

	public ViewRegistroResponsive(){	
		
	
		addComponent(generarEncabezado());
		addComponent(new Label("<hr />", ContentMode.HTML));
		addComponent(generarCuerpo());
		
		
		//escuchadores
		// 
		this.getViewRegistroCuerpo().getSeleccionador().addValueChangeListener(this);
		
		//btn Formulario  titular
		this.getViewRegistroCuerpo().getLayoutEsTitular().getBtnAceptar().addClickListener(this);
		this.getViewRegistroCuerpo().getLayoutEsTitular().getBtnVolver().addClickListener(this);
		
	
		//btn Formulario no titular
		this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getBtnAceptar().addClickListener(this);
		this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getBtnVolver().addClickListener(this);
		
		//btn Formulario juridico
		
		this.getViewRegistroCuerpo().getLayoutCuit().getBtnAceptar().addClickListener(this);
		this.getViewRegistroCuerpo().getLayoutCuit().getBtnVolver().addClickListener(this);
	}
	
	

	private Component generarEncabezado() {
		
		this.setViewRegistroEncabezado(new ViewRegistroEncabezadoResponsive());
		return this.getViewRegistroEncabezado();
	}



	private Component generarCuerpo() {
	
		this.setViewRegistroCuerpo(new ViewRegistroCuerpoResponsive());
		this.getViewRegistroCuerpo().getLayoutEsTitular().setVisible(false);
		this.getViewRegistroCuerpo().getLayoutNoEsTitular().setVisible(false);
		this.getViewRegistroCuerpo().getLayoutCuit().setVisible(false);
		return this.getViewRegistroCuerpo();
	}



	public ViewRegistroEncabezadoResponsive getViewRegistroEncabezado() {
		return viewRegistroEncabezado;
	}



	public void setViewRegistroEncabezado(
			ViewRegistroEncabezadoResponsive viewRegistroEncabezado) {
		this.viewRegistroEncabezado = viewRegistroEncabezado;
	}



	public ViewRegistroCuerpoResponsive getViewRegistroCuerpo() {
		return viewRegistroCuerpo;
	}



	public void setViewRegistroCuerpo(ViewRegistroCuerpoResponsive viewRegistroCuerpo) {
		this.viewRegistroCuerpo = viewRegistroCuerpo;
	}



	@Override
	public void setHandler(IViewRegistroHandler handler) {
		
		this.handler = handler;
		
	}

	@Override
	public IViewRegistroHandler getHandler() {
		
		return this.handler;
	}



	@Override
	public void enter(ViewChangeEvent event) {
		
		//Notification.show("Vista Registro");
		String nombreImpuesto = (String) UI.getCurrent().getSession().getAttribute("nombreImpuesto");
		//this.getViewRegistroEncabezado().getLblEncabezado().setCaption("Datos a completar del Contribuyente/Solicitante \\n Seleccionó: "+ nombreImpuesto);
		this.getViewRegistroEncabezado().getLblEncabezado().setValue("<strong>Datos a completar del Contribuyente/<br>Solicitante<br>Seleccion:</strong> "+ nombreImpuesto);
		
	}



	@Override
	public void valueChange(ValueChangeEvent event) {
	
		 
		int seleccionado = (Integer) event.getProperty().getValue();
		 if(seleccionado == 1){
			 this.getViewRegistroCuerpo().getLayoutEsTitular().setVisible(true);
			 this.getViewRegistroCuerpo().getLayoutEsTitular().reset();
			 this.getViewRegistroCuerpo().getLayoutNoEsTitular().setVisible(false);
			 this.getViewRegistroCuerpo().getLayoutCuit().setVisible(false);
		 }
		 
		 if(seleccionado == 2){		 
			 this.getViewRegistroCuerpo().getLayoutNoEsTitular().setVisible(true);
			 this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().reset();
			 this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().reset();
			 this.getViewRegistroCuerpo().getLayoutEsTitular().setVisible(false);
			 this.getViewRegistroCuerpo().getLayoutCuit().setVisible(false);
		 }
		 if(seleccionado == 3){		 
			 
			 this.getViewRegistroCuerpo().getLayoutCuit().setVisible(true);
			 this.getViewRegistroCuerpo().getLayoutNoEsTitular().setVisible(false);
			 this.getViewRegistroCuerpo().getLayoutEsTitular().setVisible(false);
			 
		 }
		
	}



	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getViewRegistroCuerpo().getLayoutEsTitular().getBtnAceptar()){			
								
			
			if (this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtApellido().isValid() &&
					this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNombre().isValid() &&
					this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeDocumento().isValid() &&
					this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeTelefono().isValid() &&
					this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtEmail().isValid() &&
					validarCmbTelefono(this.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoTelefono(),
							this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtCaracteristica(),this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeTelefono()) &&
					validarCmbTelefonoAlternativo()&&
					validarCorreos(this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtEmail(), this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtEmailReingreso())){		
				   // log.info("titular ok, puede seguir");
					this.getHandler().subscribir();
			}else{
				 Notification.show("Faltan completar campos");
			}						
		}	
		
		if(event.getSource() == this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getBtnAceptar()){
			
			if(this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtApellido().isValid() && 
	   		   this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNombre().isValid() &&
	   		   this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNumeroDeDocumento().isValid() &&
	   		   this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosTitular().getTxtNumeroDeTelefono().isValid() &&
	   		   this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtApellido().isValid() &&
	   		   this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNombre().isValid() &&
	   		   this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNumeroDeDocumento().isValid() &&
	   		   this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtNumeroDeTelefono().isValid()&&
	   		   validarCorreosNoEsTitular(this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtEmail(),
	   				this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getTxtEmailReingreso())){
			  //  log.info("solicitante ok, puede seguir");
				this.getHandler().subscribirSolicitante();
			}else{
				Notification aviso = new Notification("Faltan competar campos", Notification.Type.ERROR_MESSAGE);			
				aviso.setDelayMsec(3000);
				aviso.show(Page.getCurrent());
			}
			//Notification.show("Faltan completar campos");
			
		}
		
		if(event.getSource() == this.getViewRegistroCuerpo().getLayoutCuit().getBtnAceptar()){
			
			if(this.getViewRegistroCuerpo().getLayoutCuit().getTxtCuit().isValid() &&
					this.getViewRegistroCuerpo().getLayoutCuit().getTxtRazonSocial().isValid() &&
					this.getViewRegistroCuerpo().getLayoutCuit().getTxtCaracteristica().isValid()&&
					this.getViewRegistroCuerpo().getLayoutCuit().getTxtNumeroDeTelefono().isValid()&&
					this.getViewRegistroCuerpo().getLayoutCuit().getTxtCaracteristicaAlternativo().isValid()&&
					this.getViewRegistroCuerpo().getLayoutCuit().getTxtNumeroDeTelefonoAlternativo().isValid()&&
					this.getViewRegistroCuerpo().getLayoutCuit().getTxtEmail().isValid()&&
					this.getViewRegistroCuerpo().getLayoutCuit().getTxtEmailReingreso().isValid()&&
					validarCmbTelefono(this.getViewRegistroCuerpo().getLayoutCuit().getCmbTipoTelefono(), 
							this.getViewRegistroCuerpo().getLayoutCuit().getTxtCaracteristica(),
							this.getViewRegistroCuerpo().getLayoutCuit().getTxtNumeroDeTelefono()) &&
					validarCmbTelefono(this.getViewRegistroCuerpo().getLayoutCuit().getCmbTipoTelefonoAlternativo(),
							this.getViewRegistroCuerpo().getLayoutCuit().getTxtCaracteristicaAlternativo(),
							this.getViewRegistroCuerpo().getLayoutCuit().getTxtNumeroDeTelefonoAlternativo())&&
					validarCorreos(this.getViewRegistroCuerpo().getLayoutCuit().getTxtEmail(),this.getViewRegistroCuerpo().getLayoutCuit().getTxtEmailReingreso())
					){
				//log.info("juridica ok, puede seguir");
				this.getHandler().subscribirJuridica();
			}
			
		}
		
		
		if(event.getSource() == this.getViewRegistroCuerpo().getLayoutEsTitular().getBtnVolver() ||
				event.getSource() == this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getBtnVolver() ||
				event.getSource() == this.getViewRegistroCuerpo().getLayoutCuit().getBtnVolver()){
			
			this.getHandler().volver();
		}
		
	}



	private boolean validarCorreosNoEsTitular(TextField email, TextFieldMail emailReingreso) {
		

		boolean rta = true;
		if(!email.getValue().equalsIgnoreCase(
			emailReingreso.getValue())){			
				return rta = false;
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



	private boolean validarCmbTelefonoAlternativo() {
		
		boolean rta = true;
		if(!this.getViewRegistroCuerpo().getLayoutEsTitular().getCmbTipoTelefonoAlternativo().getValue().toString().equalsIgnoreCase("no posee")){
			if(this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtNumeroDeTelefonoAlternativo().isValid()
					&& this.getViewRegistroCuerpo().getLayoutEsTitular().getTxtCaracteristicaAlternativo().isValid()){
				return rta;
			}else{
				return rta = false;
			}
		}
		
		return rta;
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



	public void suscribirError() {
		
		//Notification notificacion = new Notification("Atención","Error al suscribir al impuesto ", Type.ERROR_MESSAGE);
		Notification notificacion = new Notification("Atención","Error al suscribir al impuesto. Reintente nuevamente  <a href='http://www.comodoro.gov.ar/eboleta2/'>Volver a e-boleta</a>", Type.ERROR_MESSAGE);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
		
		
//		try {
//			Thread.sleep(3000);
//			UI.getCurrent().getSession().close(); // cerramos todo las variables de sesion
//			UI.getCurrent().getUI().getPage().setLocation(ViewPrincipal.NAME); //redireccionamos
//			
//		} catch (InterruptedException e) {			
//			e.printStackTrace();
//		}
	}



	public void suscribirExitoso() {
		
		//Notification notificacion = new Notification("Atención","Verifique su correo electronico para finalizar el proceso de alta de impuestos ", Type.ASSISTIVE_NOTIFICATION);
		//Notification notificacion = new Notification("Atención","Verifique su correo electronico para finalizar el proceso de alta de impuestos.  <a href='http://www.comodoro.gov/eboleta/'>Volver a e-boleta</a> ", Type.ASSISTIVE_NOTIFICATION,true);
		  Notification notificacion = new Notification("Atención","Verifique su correo electronico para completar el proceso de suscripción. "
		    		+ "<br><b>Si no recibe un correo en los siguientes minutos, pudiera ser"
		    		+ "<br><b>que ingresó mal la dirección de correo electrónico ó que se filtró como SPAM o Correo No Deseado."
		    		+ "<br><b>Si el proceso no se completa en las siguientes 24 hs, se cancelaró y deberó realizar una nueva suscripción  "
		    		+ "<br><a href='http://www.comodoro.gov.ar/eboleta2/'>Volver a e-boleta</a> ", Type.ASSISTIVE_NOTIFICATION,true);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(4000);
		notificacion.show(Page.getCurrent());
		this.getHandler().limpiar();
		this.getViewRegistroCuerpo().getLayoutEsTitular().getBtnVolver().setEnabled(true);
		this.getViewRegistroCuerpo().getLayoutEsTitular().getBtnVolver().setVisible(true);
		this.getViewRegistroCuerpo().getLayoutEsTitular().getBtnAceptar().setVisible(false);
		
		
	}
	

	public void suscribirJuridicaExitoso() {
		
		
//		
//		VaadinSession.getCurrent().getSession().invalidate();
//		Page.getCurrent().setLocation("/eboleta");
		
		//Notification notificacion = new Notification("Atención","Verifique su correo electronico para finalizar el proceso de alta de impuestos  <a href='http://www.comodoro.gov/eboleta/'>Volver a e-boleta</a> ", Type.ASSISTIVE_NOTIFICATION,true);
		  Notification notificacion = new Notification("Atención","Verifique su correo electronico para completar el proceso de suscripción. "
		    		+ "<br><b>Si no recibe un correo en los siguientes minutos, pudiera ser"
		    		+ "<br><b>que ingresó mal la dirección de correo electrónico ó que se filtró como SPAM o Correo No Deseado."
		    		+ "<br><b>Si el proceso no se completa en las siguientes 24 hs, se cancelaró y deberó realizar una nueva suscripción  "
		    		+ "<br><a href='http://www.comodoro.gov.ar/eboleta2/'>Volver a e-boleta</a> ", Type.ASSISTIVE_NOTIFICATION,true);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(4000);
		notificacion.show(Page.getCurrent());
		this.getHandler().limpiarJuridica();
		this.getViewRegistroCuerpo().getLayoutCuit().getBtnVolver().setEnabled(true);
		this.getViewRegistroCuerpo().getLayoutCuit().getBtnVolver().setVisible(true);
		this.getViewRegistroCuerpo().getLayoutCuit().getBtnAceptar().setVisible(false);
		
	
		
	}
	
	public void suscribirJuridicasError() {
		
		//Notification notificacion = new Notification("Atención","Error al suscribir al impuesto ", Type.ERROR_MESSAGE);
		Notification notificacion = new Notification("Atención","Error al suscribir al impuesto. Reintente nuevamente  <a href='http://www.comodoro.gov.ar/eboleta2/'>Volver a e-boleta</a>", Type.ERROR_MESSAGE,true);
		notificacion.setPosition(Position.MIDDLE_CENTER);
		notificacion.setDelayMsec(3000);
		notificacion.show(Page.getCurrent());
	
	}


	public void subscribirSolicitanteExitoso() {
		
		
			
			//Notification notificacion = new Notification("Atención","Suscripción al impuesto Titular-Solicitante exitosa ", Type.HUMANIZED_MESSAGE);
//		    Notification notificacion = new Notification("Atención","Verifique su correo electronico para completar el proceso de suscripción. "
//		    		+ "<br>Si no recibe un correo en los siguientes minutos, pudiera ser"
//		    		+ "<br>que ingresó mal la dirección de correo electrónico ó que se filtró como SPAM o Correo No Deseado."
//		    		+ "Si el proceso no se completa en las siguientes 24 hs, se cancelaró y deberó realizar una nueva suscripción  <a href='http://www.comodoro.gov/eboleta/'>Volver a e-boleta</a> ", Type.ASSISTIVE_NOTIFICATION,true);
		  Notification notificacion = new Notification("Atención","Verifique su correo electronico para completar el proceso de suscripción. "
		    		+ "<br><b>Si no recibe un correo en los siguientes minutos, pudiera ser"
		    		+ "<br><b>que ingresó mal la dirección de correo electrónico ó que se filtró como SPAM o Correo No Deseado."
		    		+ "<br><b>Si el proceso no se completa en las siguientes 24 hs, se cancelaró y deberó realizar una nueva suscripción  "
		    		+ "<br><a href='http://www.comodoro.gov.ar/eboleta2/'>Volver a e-boleta</a> ", Type.ASSISTIVE_NOTIFICATION,true);
			notificacion.setPosition(Position.MIDDLE_CENTER);
			notificacion.setDelayMsec(4000);
			notificacion.show(Page.getCurrent());
			
			this.getHandler().limpiarSolicitante();
			
			this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getBtnVolver().setEnabled(true);
			this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getBtnVolver().setVisible(true);
			this.getViewRegistroCuerpo().getLayoutNoEsTitular().getAcordion().getFormDatosSolicitante().getBtnAceptar().setVisible(false);
			
		

		
		
	}



	public void subscribirSolicitanteErroneo() {
		

			UI.getCurrent().getSession().close(); // cerramos todo las variables de sesion
			UI.getCurrent().getUI().getPage().setLocation(ViewPrincipal.NAME); //redireccionamos
			//Notification notificacion = new Notification("Atención","Verifique su correo electronico para finalizar el proceso de alta de impuestos  <a href='http://www.comodoro.gov/eboleta'>Volver a e-boleta</a> ", Type.ASSISTIVE_NOTIFICATION,true);
			Notification notificacion = new Notification("Atención","Error al suscribir Titular-Solicitante al impuesto. Reintente nuevamente  <a href='http://www.comodoro.gov.ar/eboleta/'>Volver a e-boleta</a>", Type.ERROR_MESSAGE);
			notificacion.setPosition(Position.MIDDLE_CENTER);
			notificacion.setDelayMsec(3000);
			notificacion.show(Page.getCurrent());
	
	}



}
