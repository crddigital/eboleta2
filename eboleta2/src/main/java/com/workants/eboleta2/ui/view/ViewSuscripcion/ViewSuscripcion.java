package com.workants.eboleta2.ui.view.ViewSuscripcion;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.IViewSuscripcion;
import com.workants.eboleta2.handler.IViewABMSuscripcionesHandler;

public class ViewSuscripcion extends VerticalLayout implements IViewSuscripcion{
	
	private static final long serialVersionUID = 1L;
	public static final String NAME = "viewSuscripcion";
	private String codigoPagoElectronico;
	private IViewABMSuscripcionesHandler handler;	

	
	
   public ViewSuscripcion(){
		
		//this.codigoPagoElectronico = cpe;
		setMargin(true);
		setSpacing(true);
		setCaption("** Confirmación boleta electronica de impuesto electronico**");
		
	}



   @Override
	public void enter(ViewChangeEvent event) {
	
	   if(event.getParameters() != null){
			  
			  String[] msgs = event.getParameters().split("/");
			   // split at "/", add each part as a label
	           for (String msg : msgs) {
	        	   this.setCodigoPagoElectronico(msg);
	        	   handler.verificarCpeSuscripcion();
	           }
	       }	
	
   }



	public String getCodigoPagoElectronico() {
		return codigoPagoElectronico;
	}
	
	
	
	public void setCodigoPagoElectronico(String codigoPagoElectronico) {
		this.codigoPagoElectronico = codigoPagoElectronico;
	}
	
	
	
	public void generarLayoutSuscripcionOK() {
		
		Notification notif = new Notification("Atención","Suscripcion procesada exitosamente. Gracias",Notification.Type.ASSISTIVE_NOTIFICATION);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.setDelayMsec(5000);
		notif.show(Page.getCurrent());
		
		try
		{
		    Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
		
		UI.getCurrent().getUI().getPage().setLocation("http://www.comodoro.gov.ar");
		
		
		
		
	}
	
	
	public void generarLayoutSuscripcionError() {
		
		//Notification notif = new Notification("Error","No es posible encontrar el impuesto para realizar la alta o bien ya fue dado de alta . "
		Notification notif = new Notification("Error","No es posible procesar el impuesto actual. "
				+ "Por favor comuniquese al 08080808080 ",Notification.Type.ERROR_MESSAGE);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.setDelayMsec(3000);
		notif.show(Page.getCurrent());
		
		
		try
		{
		    Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
		
		UI.getCurrent().getUI().getPage().setLocation("http://www.comodoro.gov.ar");
		
		
	}



	public IViewABMSuscripcionesHandler getHandler() {
		return this.handler;
	}



	public void setHandler(IViewABMSuscripcionesHandler handler) {
		this.handler = handler;
	}



	public void generarLayoutSuscripcionError2() {
		
		
		Notification notif = new Notification("Error","Impuesto ya dado de alta o inexistente. Por favor comuniquese al 08080808080 ",Notification.Type.ERROR_MESSAGE);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());
		notif.setDelayMsec(3000);
		
		
		try
		{
		    Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
		
		UI.getCurrent().getUI().getPage().setLocation("http://www.comodoro.gov.ar");
		
	}






}
