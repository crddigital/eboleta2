package com.workants.eboleta2.ui.view.ViewAdministrativo;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.workants.eboleta2.componentes.TextFieldNumero;

public class VentanaBuscarSuscriptor extends Window implements ValueChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComboBox cmbTipoDeDocumento;
	private TextFieldNumero txtNumeroDeDocumento;
	private Button btnBuscar;
	
	
	public VentanaBuscarSuscriptor() {
		
		setCaption("Ingrese datos suscriptor");
		center();
		setResizable(false);
		setClosable(true);
		setModal(true);
		setWidth("350px");
		setHeight("250px");
		
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		this.setCmbTipoDeDocumento(new ComboBox("Tipo de Documento:"));
		this.getCmbTipoDeDocumento().setInvalidAllowed(false);
		this.getCmbTipoDeDocumento().setRequired(true);
		this.getCmbTipoDeDocumento().addItem("DNI");
		this.getCmbTipoDeDocumento().addItem("DU");
		this.getCmbTipoDeDocumento().addItem("LC");
		this.getCmbTipoDeDocumento().addItem("PASAPORTE");
		this.getCmbTipoDeDocumento().addItem("CUIT");
		this.getCmbTipoDeDocumento().setNullSelectionAllowed(false);
		this.getCmbTipoDeDocumento().addValueChangeListener(this);
		this.getCmbTipoDeDocumento().focus();
		
		
		this.setTxtNumeroDeDocumento(new TextFieldNumero("Número de documento:"));
		
		this.setBtnBuscar(new Button("Buscar"));
		this.getBtnBuscar().setClickShortcut(KeyCode.ENTER);
		
		layout.addComponent(this.getCmbTipoDeDocumento());
		layout.addComponent(this.getTxtNumeroDeDocumento());
		layout.addComponent(this.getBtnBuscar());
		
		setContent(layout);
		
		
		
		
		
		
		
		
	}


	public ComboBox getCmbTipoDeDocumento() {
		return cmbTipoDeDocumento;
	}


	public void setCmbTipoDeDocumento(ComboBox cmbTipoDeDocumento) {
		this.cmbTipoDeDocumento = cmbTipoDeDocumento;
	}


	public TextFieldNumero getTxtNumeroDeDocumento() {
		return txtNumeroDeDocumento;
	}


	public void setTxtNumeroDeDocumento(TextFieldNumero txtNumeroDeDocumento) {
		this.txtNumeroDeDocumento = txtNumeroDeDocumento;
	}


	public Button getBtnBuscar() {
		return btnBuscar;
	}


	public void setBtnBuscar(Button btnBuscar) {
		this.btnBuscar = btnBuscar;
	}


	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getCmbTipoDeDocumento()){
			
			if(this.getCmbTipoDeDocumento().getValue().toString().equalsIgnoreCase("cuit")){				
				this.getTxtNumeroDeDocumento().setCaption("Número de CUIT:");
				this.getTxtNumeroDeDocumento().focus();
			}else{
				this.getTxtNumeroDeDocumento().setCaption("Número de documento:");
				this.getTxtNumeroDeDocumento().focus();
			}
		}
		
	}
	
	
	

}
