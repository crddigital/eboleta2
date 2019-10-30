package com.workants.eboleta2.ui.view.ViewRegistro;


import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.ui.FormLayoutCuit;
import com.workants.eboleta2.ui.FormLayoutEsTitular;
import com.workants.eboleta2.ui.FormLayoutNoEsTitular;

public class ViewRegistroCuerpo extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OptionGroup seleccionador;
	private FormLayoutEsTitular layoutEsTitular;
	private FormLayoutNoEsTitular layoutNoEsTitular;	
	private FormLayoutCuit layoutCuit;
	
	
	public ViewRegistroCuerpo(){
	
		
	 this.setMargin(true);
	 addComponent(generarSeleccionador()); 	 
	 addComponent(new Label(" ", ContentMode.HTML));	 
	 addComponent(generarLayoutNoEsTitular());
	 addComponent(generarLayoutTitular());
	 addComponent(generarLayoutCuit());	
		
	}
	

	
	private Component generarLayoutCuit() {
	
		this.setLayoutCuit(new FormLayoutCuit());
		return this.getLayoutCuit();
	}



	private Component generarLayoutTitular() {
		 this.setLayoutEsTitular(new FormLayoutEsTitular());		
		return this.getLayoutEsTitular();
	}



	private Component generarLayoutNoEsTitular() {
		this.setLayoutNoEsTitular(new FormLayoutNoEsTitular());		
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

	public FormLayoutEsTitular getLayoutEsTitular() {
		return layoutEsTitular;
	}



	public void setLayoutEsTitular(FormLayoutEsTitular layoutEsTitular) {
		this.layoutEsTitular = layoutEsTitular;
	}



	public FormLayoutNoEsTitular getLayoutNoEsTitular() {
		return layoutNoEsTitular;
	}



	public void setLayoutNoEsTitular(FormLayoutNoEsTitular layoutNoEsTitular) {
		this.layoutNoEsTitular = layoutNoEsTitular;
	}
	
	public OptionGroup getSeleccionador() {
		return seleccionador;
	}

	public void setSeleccionador(OptionGroup seleccionador) {
		this.seleccionador = seleccionador;
	}



	public FormLayoutCuit getLayoutCuit() {
		return layoutCuit;
	}



	public void setLayoutCuit(FormLayoutCuit layoutCuit) {
		this.layoutCuit = layoutCuit;
	}
	
	

	
	

}
