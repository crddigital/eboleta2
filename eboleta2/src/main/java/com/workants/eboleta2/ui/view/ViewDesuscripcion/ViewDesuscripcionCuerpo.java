package com.workants.eboleta2.ui.view.ViewDesuscripcion;


import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class ViewDesuscripcionCuerpo extends VerticalLayout{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormLayoutDesuscripcion layoutDesuscripcion;
	private FormLayoutRespuesta layoutRespuesta; 

	public ViewDesuscripcionCuerpo(){
		
		setMargin(true);
		addComponent(generarLayoutDescripcion());		
		addComponent(generarLayoutRespuesta());	
	}

	private Component generarLayoutRespuesta() {
		
		this.setLayoutRespuesta(new FormLayoutRespuesta());
		this.getLayoutRespuesta().setVisible(false);
		return this.getLayoutRespuesta();
	}

	private Component generarLayoutDescripcion() {
		
		this.setLayoutDesuscripcion(new FormLayoutDesuscripcion());
		return this.getLayoutDesuscripcion();
	}

	public FormLayoutDesuscripcion getLayoutDesuscripcion() {
		return layoutDesuscripcion;
	}

	public void setLayoutDesuscripcion(FormLayoutDesuscripcion layoutDesuscripcion) {
		this.layoutDesuscripcion = layoutDesuscripcion;
	}

	public FormLayoutRespuesta getLayoutRespuesta() {
		return layoutRespuesta;
	}

	public void setLayoutRespuesta(FormLayoutRespuesta layoutRespuesta) {
		this.layoutRespuesta = layoutRespuesta;
	}





}
