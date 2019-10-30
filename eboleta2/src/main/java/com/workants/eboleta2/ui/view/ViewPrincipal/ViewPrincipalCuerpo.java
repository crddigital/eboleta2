package com.workants.eboleta2.ui.view.ViewPrincipal;


import org.vaadin.teemu.VaadinIcons;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.ui.formularios.FormularioCredencial;
import com.workants.eboleta2.ui.formularios.FormularioIngreso;

public class ViewPrincipalCuerpo extends CustomComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormularioCredencial formularioCredencial;
	private FormularioIngreso fomularioIngreso;
	
	
	
	public ViewPrincipalCuerpo(){
				
		//instancio ya que necesito establecer los escuchadores de btn
		this.setFomularioIngreso(new FormularioIngreso());
		this.setFormularioCredencial(new FormularioCredencial());
		
	}



	public FormularioCredencial getFormularioCredencial() {
		return formularioCredencial;
	}



	public void setFormularioCredencial(FormularioCredencial formularioCredencial) {
		this.formularioCredencial = formularioCredencial;
	}



	public void accesoValido() {
		
		System.out.println("codigo correcto");
	}



	public void accesoInvalido() {
		
		System.out.println("codigo incorrecto");
		
	}



	public FormularioIngreso getFomularioIngreso() {
		return fomularioIngreso;
	}



	public void setFomularioIngreso(FormularioIngreso fomularioIngreso) {
		this.fomularioIngreso = fomularioIngreso;
	}



	public void replace() {
		
		
		VerticalLayout layoutIngreso = new VerticalLayout();
		layoutIngreso.setSizeFull();
		Panel panelIngreso = new Panel("Ingreso sistema");
		layoutIngreso.addComponent(panelIngreso);
		panelIngreso.setWidth("400px");
		panelIngreso.setHeight("250px");		
		panelIngreso.setSizeUndefined();
		panelIngreso.setContent(this.getFomularioIngreso());
		layoutIngreso.setComponentAlignment(panelIngreso, Alignment.MIDDLE_CENTER);	
		setCompositionRoot(layoutIngreso);
		
		
	}



	public void seteo() {
		
		setSizeFull();
		setHeight("660px");
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		
		Panel panelCredendiales = new Panel("Validación");
		panelCredendiales.setIcon(FontAwesome.INFO);
		layout.addComponent(panelCredendiales);
	
		panelCredendiales.setWidth("400px");
		panelCredendiales.setHeight("250px");		
		panelCredendiales.setSizeUndefined();
		
		
		panelCredendiales.setContent(this.getFormularioCredencial()); 	
		
		layout.setComponentAlignment(panelCredendiales, Alignment.MIDDLE_CENTER);	
		
		
		
		setCompositionRoot(layout);
		
		
	}



	



	

}
