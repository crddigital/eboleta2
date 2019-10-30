package com.workants.eboleta2.ui.view.responsive.ViewPrincipal;




import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.ui.view.responsive.formularios.FormularioCredencialResponsive;
import com.workants.eboleta2.ui.view.responsive.formularios.FormularioIngresoResponsive;

public class ViewPrincipalCuerpoResponsive extends CustomComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormularioCredencialResponsive formularioCredencialR;
	private FormularioIngresoResponsive fomularioIngresoR;
	
	
	
	public ViewPrincipalCuerpoResponsive(){
				
		//instancio ya que necesito establecer los escuchadores de btn
		this.setFomularioIngresoR(new FormularioIngresoResponsive());
		this.setFormularioCredencialR(new FormularioCredencialResponsive());
		
	}



	



	public void accesoValido() {
		
		System.out.println("codigo correcto");
	}



	public void accesoInvalido() {
		
		System.out.println("codigo incorrecto");
		
	}






	public void replace() {
		
		
		VerticalLayout layoutIngreso = new VerticalLayout();
		layoutIngreso.setSizeFull();
		Panel panelIngreso = new Panel("Ingreso sistema");
		layoutIngreso.addComponent(panelIngreso);
		panelIngreso.setWidth("400px");
		panelIngreso.setHeight("250px");		
		panelIngreso.setSizeUndefined();
		panelIngreso.setContent(this.getFomularioIngresoR());
		layoutIngreso.setComponentAlignment(panelIngreso, Alignment.MIDDLE_CENTER);	
		setCompositionRoot(layoutIngreso);
		
		
	}



	public void seteo() {
		
		setSizeFull();
		//setHeight("660px");
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		
		Panel panelCredendiales = new Panel("Validación");
		panelCredendiales.setIcon(FontAwesome.INFO_CIRCLE);
		layout.addComponent(panelCredendiales);	
		panelCredendiales.setWidth("310px");
		panelCredendiales.setHeight("360px");		
		//panelCredendiales.setSizeUndefined();	
		
		panelCredendiales.setContent(this.getFormularioCredencialR()); 	
		
		layout.setComponentAlignment(panelCredendiales, Alignment.MIDDLE_CENTER);	
		
		
		
		setCompositionRoot(layout);
		
//		setSizeFull();
//		setHeight("660px");
//		VerticalLayout layout = new VerticalLayout();
//		layout.setSizeFull();
//		
//		Panel panelCredendiales = new Panel("Validación");
//		panelCredendiales.setIcon(FontAwesome.INFO);
//		layout.addComponent(panelCredendiales);
//	
//		panelCredendiales.setWidth("400px");
//		panelCredendiales.setHeight("250px");		
//		panelCredendiales.setSizeUndefined();
//		
//		
//		panelCredendiales.setContent(this.getFormularioCredencialR()); 			
//		layout.setComponentAlignment(panelCredendiales, Alignment.MIDDLE_CENTER);			
//		
//		setCompositionRoot(layout);
		
		
	}







	public FormularioCredencialResponsive getFormularioCredencialR() {
		return formularioCredencialR;
	}







	public void setFormularioCredencialR(FormularioCredencialResponsive formularioCredencialR) {
		this.formularioCredencialR = formularioCredencialR;
	}







	public FormularioIngresoResponsive getFomularioIngresoR() {
		return fomularioIngresoR;
	}







	public void setFomularioIngresoR(FormularioIngresoResponsive fomularioIngresoR) {
		this.fomularioIngresoR = fomularioIngresoR;
	}
	
	



	



	

}
