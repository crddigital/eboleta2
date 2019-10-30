package com.workants.eboleta2.componentes;


import com.vaadin.ui.Accordion;
import com.vaadin.ui.Component;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.ui.FormDatosSolicitante;
import com.workants.eboleta2.ui.FormDatosTitular;
import com.workants.eboleta2.ui.view.responsive.formularios.FormDatosSolicitanteResponsive;
import com.workants.eboleta2.ui.view.responsive.formularios.FormDatosTitularResponsive;

public class AcordionResponsive extends Accordion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormDatosTitularResponsive formDatosTitular;
	private FormDatosSolicitanteResponsive formDatosSolicitante;
	
	public AcordionResponsive(){
		
		setHeight(100.0f, Unit.PERCENTAGE);
		setImmediate(true);		
		setWidth("300px");
		addTab(generarFormDatosTitular(),"Datos del titular del impuesto");		
		addTab(generarFormDatosSolicitante(),"Datos del solicitante del impuesto");
		
		
	}

	public AcordionResponsive(Titular titular, Solicitante solicitante) {
		
	//	setHeight(100.0f, Unit.PERCENTAGE);
		setWidth("200px");
		setImmediate(true);		
		addTab(generarFormDatosTitular(titular),"Editar datos del titular del impuesto");		
		addTab(generarFormDatosSolicitante(solicitante),"Editar datos del solicitante del impuesto");
		setTabIndex(1);
		
	}

	private Component generarFormDatosSolicitante(Solicitante solicitante) {
		
		
		this.setFormDatosSolicitante(new FormDatosSolicitanteResponsive(solicitante));
		return this.getFormDatosSolicitante();
	}

	private Component generarFormDatosTitular(Titular titular) {
		
		this.setFormDatosTitular(new FormDatosTitularResponsive(titular));
		return this.getFormDatosTitular();
	}

	private Component generarFormDatosSolicitante() {
		
		this.setFormDatosSolicitante(new FormDatosSolicitanteResponsive());
		return this.getFormDatosSolicitante();
	}

	private Component generarFormDatosTitular() {
		
		this.setFormDatosTitular(new FormDatosTitularResponsive());
		return this.getFormDatosTitular();
	}

	public FormDatosTitularResponsive getFormDatosTitular() {
		return formDatosTitular;
	}

	public void setFormDatosTitular(FormDatosTitularResponsive formDatosTitular) {
		this.formDatosTitular = formDatosTitular;
	}

	public FormDatosSolicitanteResponsive getFormDatosSolicitante() {
		return formDatosSolicitante;
	}

	public void setFormDatosSolicitante(FormDatosSolicitanteResponsive formDatosSolicitante) {
		this.formDatosSolicitante = formDatosSolicitante;
	}
	

}
