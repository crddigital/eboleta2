package com.workants.eboleta2.ui.view.responsive.ViewPrincipal;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.IViewPrincipal;
import com.workants.eboleta2.handler.IViewPrincipalHandler;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.ui.view.ViewAdministrador.ViewAdministrador;
import com.workants.eboleta2.ui.view.ViewAdministrativo.ViewAdministrativo;
import com.workants.eboleta2.ui.view.ViewRegistro.ViewRegistro;
import com.workants.eboleta2.ui.view.ViewRegistroCuit.ViewRegistroCuit;
import com.workants.eboleta2.ui.view.responsive.ViewRegistro.ViewRegistroResponsive;


public class ViewPrincipalResponsive extends VerticalLayout implements IViewPrincipal, ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IViewPrincipalHandler handler;
	private ViewPrincipalEncabezadoResponsive viewPrincipalEncabezado;
	private ViewPrincipalCuerpoResponsive viewPrincipalCuerpo;	
	public static final String NAME = "viewPrincipal";


	public ViewPrincipalResponsive(){
		
		
		
		addStyleName("backColorGrey");
		//addComponent(generarEncabezado());
		addComponent(new Label("<hr />", ContentMode.HTML));
		addComponent(generarCuerpo());
		
		
		this.getViewPrincipalCuerpo().getFormularioCredencialR().getBtnValidarNumero().addClickListener(this);		
		
		//this.getViewPrincipalEncabezado().getBtnAccesoMCR().addClickListener(this);
		//this.getViewPrincipalEncabezado().getBtnDesuscribir().addClickListener(this);
		
		
		this.getViewPrincipalCuerpo().getFomularioIngresoR().getBtnIngreso().addClickListener(this);
		this.getViewPrincipalCuerpo().getFomularioIngresoR().getBtnVolver().addClickListener(this);
		
		
		
		
		
	}




	private Component generarCuerpo() {
		
		this.setViewPrincipalCuerpo(new ViewPrincipalCuerpoResponsive());
		this.getViewPrincipalCuerpo().seteo();
		return this.getViewPrincipalCuerpo();
	}


	private Component generarEncabezado() {
		
		this.setViewPrincipalEncabezado(new ViewPrincipalEncabezadoResponsive());
		return this.getViewPrincipalEncabezado();
	}


	@Override
	public void enter(ViewChangeEvent event) {
		
		//Notification.show("Ingreso vista principal");
		
	}


	@Override
	public void setHandler(IViewPrincipalHandler handler) {
		
		this.handler = handler;
		
	}


	@Override
	public IViewPrincipalHandler getHandler() {
		
		return this.handler;
	}


	public ViewPrincipalEncabezadoResponsive getViewPrincipalEncabezado() {
		return viewPrincipalEncabezado;
	}


	public void setViewPrincipalEncabezado(ViewPrincipalEncabezadoResponsive viewPrincipalEncabezado) {
		this.viewPrincipalEncabezado = viewPrincipalEncabezado;
	}


	public ViewPrincipalCuerpoResponsive getViewPrincipalCuerpo() {
		return viewPrincipalCuerpo;
	}


	public void setViewPrincipalCuerpo(ViewPrincipalCuerpoResponsive viewPrincipalCuerpo) {
		this.viewPrincipalCuerpo = viewPrincipalCuerpo;
	}


	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getViewPrincipalCuerpo().getFormularioCredencialR().getBtnValidarNumero()){			
			
			
			if(this.getViewPrincipalCuerpo().getFormularioCredencialR().getTxtNumeroDePartida().isValid() &&
				this.getViewPrincipalCuerpo().getFormularioCredencialR().getSeleccionadorImpuesto().getValue() != null){			
			  this.getHandler().validar();
			} else {
				Notification.show("Seleccione un tipo de impuesto");
			}
		}
		
		//no aplica estos eventos en vista responsive
//		if(event.getSource() == this.getViewPrincipalEncabezado().getBtnAccesoMCR()){		
//						
//			
//			if(!this.getViewPrincipalEncabezado().getBtnAccesoMCR().getCaption().equalsIgnoreCase("volver")){
//				this.getViewPrincipalEncabezado().getBtnAccesoMCR().setCaption("Volver");
//				this.getViewPrincipalEncabezado().getBtnAccesoMCR().setIcon(FontAwesome.ANGLE_LEFT);
//				this.getViewPrincipalCuerpo().replace();
//			}else{
//			
//				
//				VaadinSession.getCurrent().getSession().invalidate();
//				Page.getCurrent().setLocation("/eboleta/");
//			}
//			
//			
//		}
//		
//		if(event.getSource() == this.getViewPrincipalEncabezado().getBtnDesuscribir()){
//			
//			handler.desuscribir();
//		}
		
		if(event.getSource() == this.getViewPrincipalCuerpo().getFomularioIngresoR().getBtnIngreso()){
			
			handler.login();
		}
		
		if(event.getSource() == this.getViewPrincipalCuerpo().getFomularioIngresoR().getBtnVolver()){
			
			this.getViewPrincipalCuerpo().seteo();
		}
		
		
	}




	public void accesoValido(String tipoImpuesto, String codigo, int rta) {
		
		
		String nombreImpuesto = "";
		if (tipoImpuesto.equalsIgnoreCase("1")){
			nombreImpuesto = "Impuesto automotor";
		}
		if (tipoImpuesto.equalsIgnoreCase("2")){
			nombreImpuesto = "Impuesto Inmobiliario";
		}
		if (tipoImpuesto.equalsIgnoreCase("3")){
			nombreImpuesto = "Impuesto Tasa de Higiene Urbana";
		}
		if (tipoImpuesto.equalsIgnoreCase("4")){
			nombreImpuesto = "Impuesto Derecho Ocupante";
		}
		if (tipoImpuesto.equalsIgnoreCase("5")){
			nombreImpuesto = "Impuesto Derecho Ocupante Tasa de Higiene";
		}
		UI.getCurrent().getSession().setAttribute("nombreImpuesto", nombreImpuesto);		
		UI.getCurrent().getSession().setAttribute("codigo", codigo);
		UI.getCurrent().getSession().setAttribute("nombreImpuesto", nombreImpuesto);
		UI.getCurrent().getNavigator().navigateTo(ViewRegistroResponsive.NAME);
		
	}




	public void accesoInvalido(int rta) {
		
		Notification notif = new Notification("Error","Código incorrecto y/o inválido. Verifique impuesto seleccionado. Código:" + rta, Notification.Type.ERROR_MESSAGE);
		notif.setDelayMsec(4000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());		
	}




	public void accesoError(String tipoImpuesto, String codigo, int rta) {
		
		Notification notif = new Notification("Error","Error al procesar solicitud. Código:" + rta, Notification.Type.ERROR_MESSAGE);
		notif.setDelayMsec(4000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());
		
	}




	public void accesoValidoPendiente(String tipoImpuesto, String codigo,
			int rta) {
		
		Notification notif = new Notification("Atención","Codigo ya procesado. Pendiente de v�lidaci�n. Código:" + rta, Notification.Type.WARNING_MESSAGE);
		notif.setDelayMsec(4000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());
		
	}




	public void accesoValidoProcesado(String tipoImpuesto, String codigo,
			int rta) {
		
		Notification notif = new Notification("Atención","Codigo ya ingresado. Código:" + rta, Notification.Type.WARNING_MESSAGE);
		notif.setDelayMsec(4000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());
		
	}




	public void accesoInexistente(String tipoImpuesto, String codigo, int rta) {
		
		Notification notif = new Notification("Error","Código incorrecto y/o inválido. Verifique impuesto seleccionado. Código:" + rta, Notification.Type.ERROR_MESSAGE);
		notif.setDelayMsec(4000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());	
		
	}


	public void accesoIncorrecto() {
		
		Notification notif = new Notification("Error","Usuario y/o Contrase�a incorrecta", Notification.Type.ERROR_MESSAGE);
		notif.setDelayMsec(4000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());	
		
	}




	public void accesoAdministrativo(Usuario usuario) {
		
		UI.getCurrent().getSession().setAttribute("usuario", usuario);
		UI.getCurrent().getNavigator().navigateTo(ViewAdministrativo.NAME);
	//	this.getViewPrincipalCuerpo().getFomularioIngreso().getTxtContrasenia().setValue("");
	//	this.getViewPrincipalCuerpo().getFomularioIngreso().getTxtNombreDeUsuario().setValue("");
		
	}




	public void accesoAdministrador(Usuario usuario) {
		
		UI.getCurrent().getSession().setAttribute("usuario", usuario);
		UI.getCurrent().getNavigator().navigateTo(ViewAdministrador.NAME);
		//this.getViewPrincipalCuerpo().getFomularioIngreso().getTxtContrasenia().setValue(new String(" "));
		//this.getViewPrincipalCuerpo().getFomularioIngreso().getTxtNombreDeUsuario().setValue(new String(" "));
	}




	public void accesoValidoAutomotorEmpresa(String tipoImpuesto,String codigo, int rta) {
		
		String nombreImpuesto = "";
		if (tipoImpuesto.equalsIgnoreCase("1")){
			nombreImpuesto = "Impuesto automotor";
		}
		if (tipoImpuesto.equalsIgnoreCase("2")){
			nombreImpuesto = "Impuesto Inmobiliario";
		}
		if (tipoImpuesto.equalsIgnoreCase("3")){
			nombreImpuesto = "Impuesto Tasa de Higiene Urbana";
		}
		if (tipoImpuesto.equalsIgnoreCase("4")){
			nombreImpuesto = "Impuesto Derecho Ocupante";
		}
		if (tipoImpuesto.equalsIgnoreCase("5")){
			nombreImpuesto = "Impuesto Derecho Ocupante Tasa de Higiene";
		}
		UI.getCurrent().getSession().setAttribute("nombreImpuesto", nombreImpuesto);		
		UI.getCurrent().getSession().setAttribute("codigo", codigo);
		UI.getCurrent().getSession().setAttribute("nombreImpuesto", nombreImpuesto);		
		UI.getCurrent().getNavigator().navigateTo(ViewRegistroCuit.NAME);
		
	}
	




	
}
