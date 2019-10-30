package com.workants.eboleta2.ui;





import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.workants.eboleta2.componentes.Acordion;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;


public class FormLayoutNoEsTitular extends HorizontalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Acordion acordion;
	

	public FormLayoutNoEsTitular(){
		
		setCaption("No es Titular del impuesto - Atencion: Se requiere completar información del titular \n y del solicitante.");
		addStyleName("my-style");
		setMargin(true);
		setSpacing(true);
		addComponent(generarAcordion());		
	}


	public FormLayoutNoEsTitular(Titular titular, Solicitante solicitante) {
		
		//setMargin(true);
		setSpacing(true);
		setCaptionAsHtml(true);
		//setCaption("<big><strong>No es Titular del impuesto - Atencion: Se requiere completar información del titular \n y del solicitante.");
		//setSizeFull();
		addComponent(generarAcordion(titular, solicitante));
		
		
		
		
	}


	private Component generarAcordion(Titular titular, Solicitante solicitante) {
		this.setAcordion(new Acordion(titular, solicitante));		
		return this.getAcordion();
	}


	private Component generarAcordion() {
		
		this.setAcordion(new Acordion());		
		return this.getAcordion();
	}


	public Acordion getAcordion() {
		return acordion;
	}


	public void setAcordion(Acordion acordion) {
		this.acordion = acordion;
	}


}
