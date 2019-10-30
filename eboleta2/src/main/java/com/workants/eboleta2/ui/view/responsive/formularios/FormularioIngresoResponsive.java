package com.workants.eboleta2.ui.view.responsive.formularios;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class FormularioIngresoResponsive extends FormLayout implements ValueChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComboBox cmbTipoUsuario;
	private TextField txtNombreDeUsuario;
	private PasswordField txtContrasenia;
	private Button btnIngreso;
	private Button btnVolver;
	
	
	public FormularioIngresoResponsive(){
		
		setMargin(true);
		//addComponent(generarCmbTipoUsuario());
		addComponent(generarTxtNombreDeUsuario());
		addComponent(generarTxtcontrasenia());
		addComponent(generarBtns());
				
	}


//	private Component generarCmbTipoUsuario() {
//
//		this.setCmbTipoUsuario(new ComboBox("Tipo usuario:"));
//		this.getCmbTipoUsuario().setNullSelectionAllowed(false);
//		this.getCmbTipoUsuario().setWidth("300px");
//		this.getCmbTipoUsuario().addItem("MCR");
//		this.getCmbTipoUsuario().addItem("Empresa");
//		this.getCmbTipoUsuario().addValueChangeListener(this);
//		return this.getCmbTipoUsuario();
//	}


	private Component generarBtns() {
		
		this.setBtnIngreso(new Button("Ingresar"));
		this.getBtnIngreso().setClickShortcut(KeyCode.ENTER);		
		this.setBtnVolver(new Button("Volver"));
		HorizontalLayout botonera = new HorizontalLayout();
		botonera.setSpacing(true);
		botonera.addComponent(this.getBtnIngreso());
		botonera.addComponent(this.getBtnVolver());		
		return botonera;
		
	}


	private Component generarTxtcontrasenia() {
	
		this.setTxtContrasenia(new PasswordField("Contraseña:"));
		this.getTxtContrasenia().setWidth("300px");
		this.getTxtContrasenia().setRequired(true);
		this.getTxtContrasenia().addValidator(new PasswordValidator());
		this.getTxtContrasenia().setRequired(true);
		this.getTxtContrasenia().setInvalidAllowed(false);
		this.getTxtContrasenia().setReadOnly(false);
		return this.getTxtContrasenia();
	}


	private Component generarTxtNombreDeUsuario() {
		
		this.setTxtNombreDeUsuario(new TextField("Nombre de Usuario:"));
		this.getTxtNombreDeUsuario().setWidth("300px");
		this.getTxtNombreDeUsuario().setRequired(true);
		//this.getTxtNombreDeUsuario().setInputPrompt("Ingrese nombre de usuario");
		this.getTxtNombreDeUsuario().focus();
		this.getTxtNombreDeUsuario().addValidator(new StringLengthValidator("Error en cantidad de caracteres", 1, 15, false));
		this.getTxtNombreDeUsuario().setInvalidAllowed(false);
		this.getTxtNombreDeUsuario().setReadOnly(false);
		this.getTxtNombreDeUsuario().focus();
		return this.getTxtNombreDeUsuario();
	}


	public TextField getTxtNombreDeUsuario() {
		return txtNombreDeUsuario;
	}


	public PasswordField getTxtContrasenia() {
		return txtContrasenia;
	}


	public Button getBtnIngreso() {
		return btnIngreso;
	}


	public void setTxtNombreDeUsuario(TextField txtNombreDeUsuario) {
		this.txtNombreDeUsuario = txtNombreDeUsuario;
	}


	public void setTxtContrasenia(PasswordField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}


	public void setBtnIngreso(Button btnIngreso) {
		this.btnIngreso = btnIngreso;
	}
	
	
	//Validador de password
	private static final class PasswordValidator extends AbstractValidator<String>{
		
		private static final long serialVersionUID = 1L;
		public PasswordValidator(){
			super("Contrase�a mal compuesta");
		}
		 
		@Override
	        protected boolean isValidValue(String value) {
	            
	            // Password debe contenter al menos 5 caracteres y 1 digito
				//System.out.println("\nsoy value: " + value);
				//System.out.println("soy tama�o value: " + value.length());
	            if (value != null 
	            		//&& (value.length() < 8 || !value.matches(".*\\d.*"))) {	                
	            		&& (value.length() < 4) && !value.equalsIgnoreCase("")) {
	            	return false;
	            }	            
	            return true;	            
	        }

	        @Override
	        public Class<String> getType() {
	            return String.class;
	        }
	}
		
		
		
		
	


	public Button getBtnVolver() {
		return btnVolver;
	}


	public void setBtnVolver(Button btnVolver) {
		this.btnVolver = btnVolver;
	}


	public ComboBox getCmbTipoUsuario() {
		return cmbTipoUsuario;
	}


	public void setCmbTipoUsuario(ComboBox cmbTipoUsuario) {
		this.cmbTipoUsuario = cmbTipoUsuario;
	}


	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getCmbTipoUsuario()){
			
			if(this.getCmbTipoUsuario().getValue().toString().equalsIgnoreCase("empresa")){
				
				this.getTxtNombreDeUsuario().setCaption("CUIT");
				this.getTxtNombreDeUsuario().setInputPrompt("CUIT sin guiones");
			}else{
				this.getTxtNombreDeUsuario().setCaption("CUIT");
				this.getTxtNombreDeUsuario().setInputPrompt("");
			}
		}
		
	}
	
	

}
