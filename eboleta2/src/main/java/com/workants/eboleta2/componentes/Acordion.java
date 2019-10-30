package com.workants.eboleta2.componentes;


import com.vaadin.ui.Accordion;
import com.vaadin.ui.Component;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.ui.FormDatosSolicitante;
import com.workants.eboleta2.ui.FormDatosTitular;

public class Acordion extends Accordion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormDatosTitular formDatosTitular;
	private FormDatosSolicitante formDatosSolicitante;
	
	public Acordion(){
		
		setHeight(100.0f, Unit.PERCENTAGE);
		setImmediate(true);		
		addTab(generarFormDatosTitular(),"Datos del titular del impuesto");		
		addTab(generarFormDatosSolicitante(),"Datos del solicitante del impuesto");
		
		
	}

	public Acordion(Titular titular, Solicitante solicitante) {
		
	//	setHeight(100.0f, Unit.PERCENTAGE);
		setImmediate(true);		
		addTab(generarFormDatosTitular(titular),"Editar datos del titular del impuesto");		
		addTab(generarFormDatosSolicitante(solicitante),"Editar datos del solicitante del impuesto");
		setTabIndex(1);
		
	}

	private Component generarFormDatosSolicitante(Solicitante solicitante) {
		
		this.setFormDatosSolicitante(new FormDatosSolicitante(solicitante));
		return this.getFormDatosSolicitante();
	}

	private Component generarFormDatosTitular(Titular titular) {
		
		this.setFormDatosTitular(new FormDatosTitular(titular));
		return this.getFormDatosTitular();
	}

	private Component generarFormDatosSolicitante() {
		
		this.setFormDatosSolicitante(new FormDatosSolicitante());
		return this.getFormDatosSolicitante();
	}

	private Component generarFormDatosTitular() {
		
		this.setFormDatosTitular(new FormDatosTitular());
		return this.getFormDatosTitular();
	}

	public FormDatosTitular getFormDatosTitular() {
		return formDatosTitular;
	}

	public void setFormDatosTitular(FormDatosTitular formDatosTitular) {
		this.formDatosTitular = formDatosTitular;
	}

	public FormDatosSolicitante getFormDatosSolicitante() {
		return formDatosSolicitante;
	}

	public void setFormDatosSolicitante(FormDatosSolicitante formDatosSolicitante) {
		this.formDatosSolicitante = formDatosSolicitante;
	}
	

}
