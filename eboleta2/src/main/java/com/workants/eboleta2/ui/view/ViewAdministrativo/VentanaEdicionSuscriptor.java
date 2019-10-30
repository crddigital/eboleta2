package com.workants.eboleta2.ui.view.ViewAdministrativo;

import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.ui.FormLayoutCuit;
import com.workants.eboleta2.ui.FormLayoutEsTitular;
import com.workants.eboleta2.ui.FormLayoutNoEsTitular;

public class VentanaEdicionSuscriptor extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnEditarSuscriptor;
	private Item itemTabla;
		
	
	
	
	
	
	
	
	public VentanaEdicionSuscriptor() {		

		
		center();
		setResizable(false);
		setClosable(true);
		setModal(true);
		setCaptionAsHtml(true);
		
		
		
	
	}
	
	
	
	public VentanaEdicionSuscriptor(Item itemTabla) {
	
		
		
//		center();
//		setResizable(false);
//		setClosable(true);
//		setModal(true);
//		setCaptionAsHtml(true);
//		
//		
//		VerticalLayout layout = new VerticalLayout();
//		layout.setSpacing(true);
//		layout.setMargin(true);
//		//layout.setSizeFull();
//		setWidth("800px");
//		setHeight("600px");
		
	//	this.setItemTabla(itemTabla);
		
		
		
	
	//System.out.println("Titular es:"+titular);
	//System.out.println("Juridica es:"+juridica);
	//System.out.println("Solicitante es:"+solicitante);
		
	
	
	
	
	
		
	}
	
	public void init(Item itemTabla){
		
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		//layout.setSizeFull();
		setWidth("800px");
		setHeight("600px");
		
		this.setItemTabla(itemTabla);
		
		Titular titular = (Titular)this.getItemTabla().getItemProperty("titular").getValue();
		Juridica juridica = (Juridica)this.getItemTabla().getItemProperty("juridica").getValue();
		Solicitante solicitante = (Solicitante)this.getItemTabla().getItemProperty("solicitante").getValue();
		
		System.out.println();
		System.out.println("******");
		System.out.println("titular:"+ titular);
		System.out.println("juridica:"+ juridica);
		System.out.println("solicitante:"+ solicitante);
		
		
		
		if(titular == null && solicitante == null){ //si o si es juridico
			
			setCaption("<big><strong>Editar datos Juridicos");
			FormLayoutCuit formLayoutCuit = new FormLayoutCuit(juridica);
			layout.addComponent(formLayoutCuit);
		}else 	
		{
			if(solicitante == null){ // <-- es titular 
				
				setCaption("<big><strong>Editar datos suscriptor - Titular");
				FormLayoutEsTitular formLayoutEsTitular = new FormLayoutEsTitular(titular);				
				layout.addComponent(formLayoutEsTitular);
			}	
			else{
				setCaption("<big><strong>Editar datos suscriptor - Solicitante");
				FormLayoutNoEsTitular formLayoutNoEsTitular = new FormLayoutNoEsTitular(titular,solicitante);			
				layout.addComponent(formLayoutNoEsTitular);			
			}
		}
		layout.addComponent(generarBotonera());
		setContent(layout);
		
	}
	

	private Component generarBotonera() {
		
//		HorizontalLayout lay = new HorizontalLayout();
//		lay.setSizeFull();
//		lay.setMargin(true);
//		this.setBtnEditarSuscriptor(new Button("Editar"));
//		lay.addComponent(this.getBtnEditarSuscriptor());
//		return lay;
		this.setBtnEditarSuscriptor(new Button("Editar"));
		return this.getBtnEditarSuscriptor();
	}

	public Button getBtnEditarSuscriptor() {
		return btnEditarSuscriptor;
	}

	public void setBtnEditarSuscriptor(Button btnEditarSuscriptor) {
		this.btnEditarSuscriptor = btnEditarSuscriptor;
	}

	public Item getItemTabla() {
		return itemTabla;
	}

	public void setItemTabla(Item itemTabla) {
		this.itemTabla = itemTabla;
	}
	
	
	

}
