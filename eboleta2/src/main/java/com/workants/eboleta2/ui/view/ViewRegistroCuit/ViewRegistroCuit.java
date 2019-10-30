package com.workants.eboleta2.ui.view.ViewRegistroCuit;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.IViewRegistroCuit;
import com.workants.eboleta2.handler.IViewRegistroCuitHandler;


public class ViewRegistroCuit extends VerticalLayout implements IViewRegistroCuit, ValueChangeListener, ClickListener {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IViewRegistroCuitHandler handler;
	private ViewRegistroCuitEncabezado viewRegistroCuitEncabezado;
	private ViewRegistroCuitCuerpo viewRegistroCuitCuerpo;
	public static final String NAME = "viewRegistroCuit";
	
	
	public ViewRegistroCuit() {
	
			
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHandler(IViewRegistroCuitHandler handler) {
		
		this.handler = handler;
		
	}

	@Override
	public IViewRegistroCuitHandler getHandler() {
		
		return this.handler;
	}

	public ViewRegistroCuitEncabezado getViewRegistroCuitEncabezado() {
		return viewRegistroCuitEncabezado;
	}

	public void setViewRegistroCuitEncabezado(
			ViewRegistroCuitEncabezado viewRegistroCuitEncabezado) {
		this.viewRegistroCuitEncabezado = viewRegistroCuitEncabezado;
	}

	public ViewRegistroCuitCuerpo getViewRegistroCuitCuerpo() {
		return viewRegistroCuitCuerpo;
	}

	public void setViewRegistroCuitCuerpo(
			ViewRegistroCuitCuerpo viewRegistroCuitCuerpo) {
		this.viewRegistroCuitCuerpo = viewRegistroCuitCuerpo;
	}

	@Override
	public void enter(ViewChangeEvent event) {
				
		String nombreImpuesto = (String) UI.getCurrent().getSession().getAttribute("nombreImpuesto");
		
		if(nombreImpuesto.contains("automotor")){
			
			Notification.show("Automotor");
		}else{
			Notification.show("partidaaaaaaaaaa!");
		}
		
		
		
	}
	
	

}
