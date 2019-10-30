package com.workants.eboleta2.ui.view.ViewDesuscripcion;


import com.vaadin.data.Validator;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.workants.eboleta2.componentes.TextFieldNumero;



public class FormLayoutDesuscripcion extends FormLayout implements ValueChangeListener{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField txtCodigoPagoElectronico;
	private ComboBox cmbTipoDeDocumento;
	private TextField txtNumeroDeDocumento;
	private Button btnAceptar;
	
	private Button btnVolver;
	private Button bntSolicitarBaja;

	public FormLayoutDesuscripcion(){
		
		setCaption("Contribuyente Fisico - Constribuyente Juridico");
		setMargin(true);
		setSpacing(true);
		
		addComponent(generarTxtCodigoPagoElectronico());
		addComponent(generarCmbTipoDocumento());
		addComponent(generarTxtNumeroDeDocumento());
		addComponent(generarComandos());
		
	}
	
	private Component generarComandos() {
		
		
		
		this.setBtnAceptar(new Button("Aceptar"));
		this.getBtnAceptar().setClickShortcut(KeyCode.ENTER);	
		
		return this.getBtnAceptar(); 
	}

	private Component generarTxtNumeroDeDocumento() {
			
		this.setTxtNumeroDeDocumento(new TextFieldNumero("Número de documento:"));
		//this.getTxtNumeroDeDocumento().addValidator(new StringLengthValidator("Minimo 6 nómero, móximo 8", 7, 8, false));
		return this.getTxtNumeroDeDocumento();
	}

	private Component generarCmbTipoDocumento() {
		
		this.setCmbTipoDeDocumento(new ComboBox("Tipo Documento / CUIT:"));
		this.getCmbTipoDeDocumento().setInvalidAllowed(false);
		this.getCmbTipoDeDocumento().setRequired(true);
		this.getCmbTipoDeDocumento().addItem("DNI");
		this.getCmbTipoDeDocumento().addItem("DU");
		this.getCmbTipoDeDocumento().addItem("LC");
		this.getCmbTipoDeDocumento().addItem("PASAPORTE");
		this.getCmbTipoDeDocumento().addItem("CUIT");
		this.getCmbTipoDeDocumento().setNullSelectionAllowed(false);
		this.getCmbTipoDeDocumento().addValueChangeListener(this);
		return this.getCmbTipoDeDocumento();
	}

	private Component generarTxtCodigoPagoElectronico() {
	
		this.setTxtCodigoPagoElectronico(new TextFieldNumero());
		this.getTxtCodigoPagoElectronico().setInputPrompt("Ingrese 11 digitos");
		this.getTxtCodigoPagoElectronico().setWidth("350px");
		this.getTxtCodigoPagoElectronico().setRequired(true);
		this.getTxtCodigoPagoElectronico().focus();
		return this.getTxtCodigoPagoElectronico();
	}


	public TextField getTxtCodigoPagoElectronico() {
		return txtCodigoPagoElectronico;
	}


	public void setTxtCodigoPagoElectronico(TextField txtCodigoPagoElectronico) {
		this.txtCodigoPagoElectronico = txtCodigoPagoElectronico;
	}


	public ComboBox getCmbTipoDeDocumento() {
		return cmbTipoDeDocumento;
	}


	public void setCmbTipoDeDocumento(ComboBox cmbTipoDeDocumento) {
		this.cmbTipoDeDocumento = cmbTipoDeDocumento;
	}


	public TextField getTxtNumeroDeDocumento() {
		return txtNumeroDeDocumento;
	}


	public void setTxtNumeroDeDocumento(TextField txtNumeroDeDocumento) {
		this.txtNumeroDeDocumento = txtNumeroDeDocumento;
	}


	public Button getBtnAceptar() {
		return btnAceptar;
	}


	public void setBtnAceptar(Button btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public void limpiar() {
		
		this.getCmbTipoDeDocumento().clear();
		this.getTxtCodigoPagoElectronico().clear();
		this.getTxtNumeroDeDocumento().clear();
		
	}

	public Button getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(Button btnVolver) {
		this.btnVolver = btnVolver;
	}

	public Button getBntSolicitarBaja() {
		return bntSolicitarBaja;
	}

	public void setBntSolicitarBaja(Button bntSolicitarBaja) {
		this.bntSolicitarBaja = bntSolicitarBaja;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getCmbTipoDeDocumento()){
			
			if(this.getCmbTipoDeDocumento().getValue() != null){
			
			if(this.getCmbTipoDeDocumento().getValue().toString().equalsIgnoreCase("cuit")){
				this.getTxtNumeroDeDocumento().setCaption("CUIT:");
				this.getTxtNumeroDeDocumento().removeAllValidators();
				Validator numberValidator = new RegexpValidator("\\d{11}", "Ingrese solo digitos. 11 digitos para el CUIT");
			    this.getTxtNumeroDeDocumento().addValidator(numberValidator);
			    this.getTxtNumeroDeDocumento().focus();				
			}
			if(!this.getCmbTipoDeDocumento().getValue().toString().equalsIgnoreCase("cuit")){
				this.getTxtNumeroDeDocumento().setCaption("Número de documento:");
				this.getTxtNumeroDeDocumento().removeAllValidators();
				Validator numberValidator = new RegexpValidator("\\d", "Ingrese solo digitos");
			    this.getTxtNumeroDeDocumento().addValidator(numberValidator);
			    this.getTxtNumeroDeDocumento().focus();				
			}
		}
			
		}//es null.....
		
	}
	

}
