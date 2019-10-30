package com.workants.eboleta2.ui.view.responsive.ViewRegistro;


import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.ui.FormLayoutCuit;
import com.workants.eboleta2.ui.FormLayoutEsTitular;
import com.workants.eboleta2.ui.FormLayoutNoEsTitular;
import com.workants.eboleta2.ui.view.responsive.formularios.FormLayoutCuitResponsive;
import com.workants.eboleta2.ui.view.responsive.formularios.FormLayoutEsTitularResponsive;
import com.workants.eboleta2.ui.view.responsive.formularios.FormLayoutNoEsTitularResponsive;

public class ViewRegistroCuerpoResponsive extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OptionGroup seleccionador;
	private FormLayoutEsTitularResponsive layoutEsTitularR;
	private FormLayoutNoEsTitularResponsive layoutNoEsTitularR;	
	private FormLayoutCuitResponsive layoutCuitR;
	
	
	public ViewRegistroCuerpoResponsive(){
	
		
	 this.setMargin(true);
	 addComponent(generarSeleccionador()); 	 
	 addComponent(new Label(" ", ContentMode.HTML));	 
	 addComponent(generarLayoutNoEsTitular());
	 addComponent(generarLayoutTitular());
	 addComponent(generarLayoutCuit());	
		
	}
	

	
	private Component generarLayoutCuit() {
	
		this.setLayoutCuit(new FormLayoutCuitResponsive());
		return this.getLayoutCuit();
	}



	private Component generarLayoutTitular() {
		 this.setLayoutEsTitular(new FormLayoutEsTitularResponsive());		
		return this.getLayoutEsTitular();
	}



	private Component generarLayoutNoEsTitular() {
		this.setLayoutNoEsTitular(new FormLayoutNoEsTitularResponsive());		
		return this.getLayoutNoEsTitular();
	}



	private Component generarSeleccionador() {
		this.setSeleccionador(new OptionGroup("Seleccione opción:"));		
		this.getSeleccionador().addItem(1);
		this.getSeleccionador().setItemCaption(1, "Es titular del impuesto");
		this.getSeleccionador().addItem(2);
		this.getSeleccionador().setItemCaption(2, "No es titular del impuesto");
		this.getSeleccionador().addItem(3);
		this.getSeleccionador().setItemCaption(3, "Es persona juridica");
		return this.getSeleccionador();

	}

	public FormLayoutEsTitularResponsive getLayoutEsTitular() {
		return layoutEsTitularR;
	}



	public void setLayoutEsTitular(FormLayoutEsTitularResponsive layoutEsTitular) {
		this.layoutEsTitularR = layoutEsTitular;
	}



	public FormLayoutNoEsTitularResponsive getLayoutNoEsTitular() {
		return layoutNoEsTitularR;
	}



	public void setLayoutNoEsTitular(FormLayoutNoEsTitularResponsive layoutNoEsTitular) {
		this.layoutNoEsTitularR = layoutNoEsTitular;
	}
	
	public OptionGroup getSeleccionador() {
		return seleccionador;
	}

	public void setSeleccionador(OptionGroup seleccionador) {
		this.seleccionador = seleccionador;
	}



	public FormLayoutCuitResponsive getLayoutCuit() {
		return layoutCuitR;
	}



	public void setLayoutCuit(FormLayoutCuitResponsive layoutCuit) {
		this.layoutCuitR = layoutCuit;
	}
	
	

	
	

}
