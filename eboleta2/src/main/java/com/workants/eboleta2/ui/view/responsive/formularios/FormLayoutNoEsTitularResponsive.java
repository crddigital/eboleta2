package com.workants.eboleta2.ui.view.responsive.formularios;





import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.workants.eboleta2.componentes.Acordion;
import com.workants.eboleta2.componentes.AcordionResponsive;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;


public class FormLayoutNoEsTitularResponsive extends HorizontalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AcordionResponsive acordion;
	

	public FormLayoutNoEsTitularResponsive(){
		
		setCaptionAsHtml(true);
		
		setCaption("No es Titular del impuesto<br> "
		+ "<strong>Atencion:</strong> Se requiere completar <br>"
		+ "información del titular y del solicitante.");
		addStyleName("my-style");
		//setMargin(true);
		setSpacing(true);
		addComponent(generarAcordion());		
	}


	public FormLayoutNoEsTitularResponsive(Titular titular, Solicitante solicitante) {
		
		//setMargin(true);
		setSpacing(true);
		setCaptionAsHtml(true);
		//setCaption("<big><strong>No es Titular del impuesto - Atencion: Se requiere completar información del titular \n y del solicitante.");
		//setSizeFull();
		addComponent(generarAcordion(titular, solicitante));
		
		
		
		
	}


	private Component generarAcordion(Titular titular, Solicitante solicitante) {
		this.setAcordionResponsive(new AcordionResponsive(titular, solicitante));		
		return this.getAcordion();
	}


	private Component generarAcordion() {
		
		this.setAcordionResponsive(new AcordionResponsive());		
		return this.getAcordion();
	}


	public AcordionResponsive getAcordion() {
		return acordion;
	}


	public void setAcordionResponsive(AcordionResponsive acordion) {
		this.acordion = acordion;
	}


}
