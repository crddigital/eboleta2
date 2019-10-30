package com.workants.eboleta2.ui;


import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.workants.eboleta2.componentes.TextFieldNumero;
import com.workants.eboleta2.componentes.TextFieldString;
import com.workants.eboleta2.model.Titular;

public class FormDatosTitular extends FormLayout implements ValueChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextFieldString txtApellido;
	private TextFieldString txtNombre;
	private ComboBox cmbTipoDocumento;
	private TextFieldNumero txtNumeroDeDocumento;
	private ComboBox cmbTipoTelefono;
	private TextField txtCaracteristica;
	private TextField txtNumeroDeTelefono;
	private ComboBox cmbTipoTelefonoAlternativo;
	private TextField txtNumeroDeTelefonoAlternativo;
	private TextField txtCaracteristicaAlternativo;
	private Label lblRerodatorio;
	
	public FormDatosTitular(){
		
		setCaption("Datos del titular del impuesto");
		setSpacing(true);
		setMargin(true);
		
		addComponent(generarTxtApellido());
		addComponent(generarTxtNombre());
		addComponent(generarCmbTipoDocumento());
		addComponent(generarTxtNumeroDeDocumento());
		addComponent(generarCmbTipoDeTelefono());
		addComponent(generarCmbCaracteristica());
		addComponent(generarTxtNumeroDeTelefono());
		addComponent(generarCmbTipoDeTelefonoAleternativo());
		addComponent(generarTxtCaracteristicaAlternativo());
		addComponent(generarTxtNumeroDeTelefonoAlternativo());
		
			
		
		
		//addComponent(generarRecordatorio());

	}
	
	public FormDatosTitular(Titular titular) {
	
		setCaptionAsHtml(true);
		setCaption("<big><strong>Edicion datos del titular del impuesto");
		setSpacing(true);
		setMargin(true);
		
		GridLayout grid = new GridLayout();
		grid.setSpacing(true);
		grid.setColumns(3);
		grid.addComponent(generarTxtApellido(titular));
		grid.addComponent(generarTxtNombre(titular));
		grid.addComponent(generarCmbTipoDocumento(titular));
		grid.addComponent(generarTxtNumeroDeDocumento(titular));
		grid.addComponent(generarCmbTipoDeTelefono(titular));
		grid.addComponent(generarCmbCaracteristica(titular));
		grid.addComponent(generarTxtNumeroDeTelefono(titular));
		grid.addComponent(generarCmbTipoDeTelefonoAleternativo(titular));
		grid.addComponent(generarTxtCaracteristicaAlternativo(titular));
		grid.addComponent(generarTxtNumeroDeTelefonoAlternativo(titular));
		
		addComponent(grid);
	}

	private Component generarTxtCaracteristicaAlternativo() {
		
		this.setTxtCaracteristicaAlternativo(new TextField("Caracteristica alternativo:"));
		this.getTxtCaracteristicaAlternativo().setInputPrompt("Prefijo sin el cero");		
		//Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		//this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		this.getTxtCaracteristicaAlternativo().addValidator(caracteristicaValidator);
		this.getTxtCaracteristicaAlternativo().setEnabled(false);
		return this.getTxtCaracteristicaAlternativo();
	}
	
	private Component generarCmbCaracteristica() {
	
		this.setTxtCaracteristica(new TextField("Caracteristica:"));
		this.getTxtCaracteristica().setInputPrompt("Sin cero");
		//this.getTxtCaracteristica().addValidator(new StringLengthValidator("Minimo 2 digitos, m�ximo 4 digitos", 2, 4, false));
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		this.getTxtCaracteristica().setRequired(true);
		return this.getTxtCaracteristica();
	}

	/*private Component generarRecordatorio() {//new Label("<hr />", ContentMode.HTML)

		//this.setLblRerodatorio(new Label("<b><em><strong> &#8595 RECUERDE LLENAR LOS DATOS DEL SOLICITANTE &#8595 </strong></em></b>", ContentMode.HTML));
		this.setLblRerodatorio(new Label("<div align='center'><font size='+1'><marquee direction='left' loop='5'>"
				+ "<b><em><strong> &#8595 RECUERDE LLENAR LOS DATOS DEL SOLICITANTE &#8595 </strong></em></b></marquee></font></div>", ContentMode.HTML));
		this.getLblRerodatorio().setWidth("200px");
		return this.getLblRerodatorio();
	}*/

	private Component generarTxtNumeroDeTelefonoAlternativo() {
		
		this.setTxtNumeroDeTelefonoAlternativo(new TextField("Número de telefono alternativo:"));
		//this.getTxtNumeroDeTelefonoAlternativo().addValidator(new StringLengthValidator("Minimo 7 n�meros", 7, 10, false));
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefonoAlternativo().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefonoAlternativo().setInputPrompt("Cel sin 0 y 15");
		this.getTxtNumeroDeTelefonoAlternativo().setEnabled(false);
		return this.getTxtNumeroDeTelefonoAlternativo();

	}

	private Component generarCmbTipoDeTelefonoAleternativo() {
		
		this.setCmbTipoTelefonoAlternativo(new ComboBox("Tipo Telefono alternativo:"));
		this.getCmbTipoTelefonoAlternativo().setInvalidAllowed(false);
		this.getCmbTipoTelefonoAlternativo().addItem("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().addItem("FIJO");
		this.getCmbTipoTelefonoAlternativo().addItem("CELULAR");
		this.getCmbTipoTelefonoAlternativo().setValue("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().setNullSelectionAllowed(false);
		this.getCmbTipoTelefonoAlternativo().addValueChangeListener(this);
		return this.getCmbTipoTelefonoAlternativo();
	}
	
	private Component generarTxtNumeroDeTelefono() {
		
		this.setTxtNumeroDeTelefono(new TextField("Número de telefono:"));
		//this.getTxtNumeroDeTelefono().addValidator(new StringLengthValidator("Minimo 7 n�meros", 7, 10, false));
		this.getTxtNumeroDeTelefono().setInputPrompt("Cel sin 0 y 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefono().addValidator(telefonoValidator);
		return this.getTxtNumeroDeTelefono();
	}

	private Component generarCmbTipoDeTelefono() {
		
		this.setCmbTipoTelefono(new ComboBox("Tipo Telefono:"));
		this.getCmbTipoTelefono().setInvalidAllowed(false);
		this.getCmbTipoTelefono().setRequired(true);
		this.getCmbTipoTelefono().addItem("FIJO");
		this.getCmbTipoTelefono().addItem("CELULAR");		
		this.getCmbTipoTelefono().setNullSelectionAllowed(false);
		return this.getCmbTipoTelefono();
	}

	private Component generarTxtNumeroDeDocumento() {
		
		this.setTxtNumeroDeDocumento(new TextFieldNumero("Número de documento:"));	
		//this.getTxtNumeroDeDocumento().addValidator(new StringLengthValidator("Minimo 6 n�mero, m�ximo 8", 7, 8, false));
		return this.getTxtNumeroDeDocumento();
	}

	private Component generarCmbTipoDocumento() {
	
		this.setCmbTipoDocumento(new ComboBox("Tipo Documento:"));
		this.getCmbTipoDocumento().setInvalidAllowed(false);
		this.getCmbTipoDocumento().setRequired(true);
		this.getCmbTipoDocumento().addItem("DNI");
		this.getCmbTipoDocumento().addItem("DU");
		this.getCmbTipoDocumento().addItem("LC");
		this.getCmbTipoDocumento().addItem("PASAPORTE");
		this.getCmbTipoDocumento().setNullSelectionAllowed(false);
		this.getCmbTipoDocumento().addValueChangeListener(this);
		return this.getCmbTipoDocumento();
	}

	private Component generarTxtNombre() {
		
		this.setTxtNombre(new TextFieldString("Nombre:"));
		return this.getTxtNombre();
	}

	private Component generarTxtApellido() {
		
		this.setTxtApellido(new TextFieldString("Apellido:"));			
		return this.getTxtApellido();
	}
	
	
	
	//Edicion !!!!
	
	
	private Component generarTxtCaracteristicaAlternativo(Titular titular) {
		
		this.setTxtCaracteristicaAlternativo(new TextField("Caracteristica alternativo:"));
		this.getTxtCaracteristicaAlternativo().setInputPrompt("Prefijo sin el cero");		
		//Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		//this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		this.getTxtCaracteristicaAlternativo().addValidator(caracteristicaValidator);
		this.getTxtCaracteristicaAlternativo().setEnabled(false);
		this.getTxtCaracteristicaAlternativo().setValue("297");
		return this.getTxtCaracteristicaAlternativo();
	}
	
	private Component generarCmbCaracteristica(Titular titular) {
	
		this.setTxtCaracteristica(new TextField("Caracteristica:"));
		this.getTxtCaracteristica().setInputPrompt("Sin cero");
		//this.getTxtCaracteristica().addValidator(new StringLengthValidator("Minimo 2 digitos, m�ximo 4 digitos", 2, 4, false));
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		this.getTxtCaracteristica().setRequired(true);
		this.getTxtCaracteristica().setValue("297");
		return this.getTxtCaracteristica();
	}

	/*private Component generarRecordatorio() {//new Label("<hr />", ContentMode.HTML)

		//this.setLblRerodatorio(new Label("<b><em><strong> &#8595 RECUERDE LLENAR LOS DATOS DEL SOLICITANTE &#8595 </strong></em></b>", ContentMode.HTML));
		this.setLblRerodatorio(new Label("<div align='center'><font size='+1'><marquee direction='left' loop='5'>"
				+ "<b><em><strong> &#8595 RECUERDE LLENAR LOS DATOS DEL SOLICITANTE &#8595 </strong></em></b></marquee></font></div>", ContentMode.HTML));
		this.getLblRerodatorio().setWidth("200px");
		return this.getLblRerodatorio();
	}*/

	private Component generarTxtNumeroDeTelefonoAlternativo(Titular titular) {
		
		this.setTxtNumeroDeTelefonoAlternativo(new TextField("Número de telófono alternativo:"));
		//this.getTxtNumeroDeTelefonoAlternativo().addValidator(new StringLengthValidator("Minimo 7 n�meros", 7, 10, false));
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefonoAlternativo().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefonoAlternativo().setInputPrompt("Cel sin 0 y 15");
		this.getTxtNumeroDeTelefonoAlternativo().setEnabled(false);
		this.getTxtNumeroDeTelefonoAlternativo().setValue(titular.getNumeroDeTelefonoAlternativoTitular());
		return this.getTxtNumeroDeTelefonoAlternativo();

	}

	private Component generarCmbTipoDeTelefonoAleternativo(Titular titular) {
		
		this.setCmbTipoTelefonoAlternativo(new ComboBox("Tipo Telefono alternativo:"));
		this.getCmbTipoTelefonoAlternativo().setInvalidAllowed(false);
		this.getCmbTipoTelefonoAlternativo().addItem("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().addItem("FIJO");
		this.getCmbTipoTelefonoAlternativo().addItem("CELULAR");
		this.getCmbTipoTelefonoAlternativo().setValue("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().setNullSelectionAllowed(false);
		this.getCmbTipoTelefonoAlternativo().setValue(titular.getTipoTelefonoAlternativoTitular());
		this.getCmbTipoTelefonoAlternativo().addValueChangeListener(this);
		return this.getCmbTipoTelefonoAlternativo();
	}
	
	private Component generarTxtNumeroDeTelefono(Titular titular) {
		
		this.setTxtNumeroDeTelefono(new TextField("Número de telefóno:"));
		//this.getTxtNumeroDeTelefono().addValidator(new StringLengthValidator("Minimo 7 n�meros", 7, 10, false));
		this.getTxtNumeroDeTelefono().setInputPrompt("Cel sin 0 y 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefono().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefono().setValue(titular.getNumeroDeTelefonoTitular());
		return this.getTxtNumeroDeTelefono();
	}

	private Component generarCmbTipoDeTelefono(Titular titular) {
		
		this.setCmbTipoTelefono(new ComboBox("Tipo Telefono:"));
		this.getCmbTipoTelefono().setInvalidAllowed(false);
		this.getCmbTipoTelefono().setRequired(true);
		this.getCmbTipoTelefono().addItem("FIJO");
		this.getCmbTipoTelefono().addItem("CELULAR");		
		this.getCmbTipoTelefono().setNullSelectionAllowed(false);
		this.getCmbTipoTelefono().setValue(titular.getTipoTelefonoTitular().toString());
		this.getCmbTipoTelefono().addValueChangeListener(this);
		return this.getCmbTipoTelefono();
	}

	private Component generarTxtNumeroDeDocumento(Titular titular) {
		
		this.setTxtNumeroDeDocumento(new TextFieldNumero("Número de documento:"));	
		//this.getTxtNumeroDeDocumento().addValidator(new StringLengthValidator("Minimo 6 n�mero, m�ximo 8", 7, 8, false));
		this.getTxtNumeroDeDocumento().setValue(titular.getNumeroDeDocumentoTitular());
		return this.getTxtNumeroDeDocumento();
	}

	private Component generarCmbTipoDocumento(Titular titular) {
	
		this.setCmbTipoDocumento(new ComboBox("Tipo Documento:"));
		this.getCmbTipoDocumento().setInvalidAllowed(false);
		this.getCmbTipoDocumento().setRequired(true);
		this.getCmbTipoDocumento().addItem("DNI");
		this.getCmbTipoDocumento().addItem("DU");
		this.getCmbTipoDocumento().addItem("LC");
		this.getCmbTipoDocumento().addItem("PASAPORTE");
		this.getCmbTipoDocumento().setNullSelectionAllowed(false);		
		this.getCmbTipoDocumento().setValue(titular.getTipoDocumentoTitular());
		this.getCmbTipoDocumento().addValueChangeListener(this);
		return this.getCmbTipoDocumento();
	}

	private Component generarTxtNombre(Titular titular) {
		
		this.setTxtNombre(new TextFieldString("Nombre:"));
		this.getTxtNombre().setValue(titular.getNombre());
		return this.getTxtNombre();
	}

	private Component generarTxtApellido(Titular titular) {
		
		this.setTxtApellido(new TextFieldString("Apellido:"));		
		this.getTxtApellido().setValue(titular.getApellido());
		return this.getTxtApellido();
	}
	
	public void reset(){
		
		this.getTxtApellido().setValue("");
		this.getTxtNombre().setValue("");
		this.getTxtNumeroDeDocumento().setValue("");
		this.getTxtNumeroDeTelefono().setValue("");
		this.getCmbTipoDocumento().setValue(null);
		this.getCmbTipoTelefono().setValue(null);
	}

	public TextFieldString getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(TextFieldString txtApellido) {
		this.txtApellido = txtApellido;
	}

	public TextFieldString getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(TextFieldString txtNombre) {
		this.txtNombre = txtNombre;
	}

	public ComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(ComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public TextFieldNumero getTxtNumeroDeDocumento() {
		return txtNumeroDeDocumento;
	}

	public void setTxtNumeroDeDocumento(TextFieldNumero txtNumeroDeDocumento) {
		this.txtNumeroDeDocumento = txtNumeroDeDocumento;
	}

	public ComboBox getCmbTipoTelefono() {
		return cmbTipoTelefono;
	}

	public void setCmbTipoTelefono(ComboBox cmbTipoTelefono) {
		this.cmbTipoTelefono = cmbTipoTelefono;
	}

	public TextField getTxtNumeroDeTelefono() {
		return txtNumeroDeTelefono;
	}

	public void setTxtNumeroDeTelefono(TextField txtNumeroDeTelefono) {
		this.txtNumeroDeTelefono = txtNumeroDeTelefono;
	}

	public ComboBox getCmbTipoTelefonoAlternativo() {
		return cmbTipoTelefonoAlternativo;
	}

	public void setCmbTipoTelefonoAlternativo(ComboBox cmbTipoTelefonoAlternativo) {
		this.cmbTipoTelefonoAlternativo = cmbTipoTelefonoAlternativo;
	}

	public TextField getTxtNumeroDeTelefonoAlternativo() {
		return txtNumeroDeTelefonoAlternativo;
	}

	public void setTxtNumeroDeTelefonoAlternativo(
			TextField txtNumeroDeTelefonoAlternativo) {
		this.txtNumeroDeTelefonoAlternativo = txtNumeroDeTelefonoAlternativo;
	}

	public Label getLblRerodatorio() {
		return lblRerodatorio;
	}

	public void setLblRerodatorio(Label lblRerodatorio) {
		this.lblRerodatorio = lblRerodatorio;
	}

	public TextField getTxtCaracteristica() {
		return txtCaracteristica;
	}

	public void setTxtCaracteristica(TextField txtCaracteristica) {
		this.txtCaracteristica = txtCaracteristica;
	}

	public TextField getTxtCaracteristicaAlternativo() {
		return txtCaracteristicaAlternativo;
	}

	public void setTxtCaracteristicaAlternativo(
			TextField txtCaracteristicaAlternativo) {
		this.txtCaracteristicaAlternativo = txtCaracteristicaAlternativo;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getCmbTipoDocumento()){
			
			this.getTxtNumeroDeDocumento().focus();
		}
		
		
		
		if(event.getProperty() == this.getCmbTipoTelefonoAlternativo()){
		
			//no  validamos para tipoTelefono ya que si o si se requiere q tenga uno
			
			if(this.getCmbTipoTelefonoAlternativo().getValue() != null){
				
				if(!this.getCmbTipoTelefonoAlternativo().getValue().toString().equalsIgnoreCase("no posee")){ //posee fijo o celular, por ende habilitamos
					this.getTxtCaracteristicaAlternativo().setEnabled(true);
					this.getTxtCaracteristicaAlternativo().focus();
					//this.getTxtCaracteristicaAlternativo().addValidator(new StringLengthValidator("Minimo 2 digitos, m�ximo 4 digitos", 2, 4, false));
					this.getTxtCaracteristicaAlternativo().setRequired(true);
					
					this.getTxtNumeroDeTelefonoAlternativo().setEnabled(true);
					this.getTxtNumeroDeTelefonoAlternativo().setRequired(true);
					//this.getTxtNumeroDeTelefonoAlternativo().addValidator(new StringLengthValidator("Minimo 7 numeros", 7, 10, false));
					
				}else{
					this.getTxtCaracteristicaAlternativo().setEnabled(false);
					//this.getTxtCaracteristicaAlternativo().removeValidator(null);
					this.getTxtCaracteristicaAlternativo().setRequired(false);
					
					this.getTxtNumeroDeTelefonoAlternativo().setEnabled(false);
					//this.getTxtNumeroDeTelefonoAlternativo().removeValidator(null);
					this.getTxtNumeroDeTelefonoAlternativo().setRequired(false);
					
					
				}
				
			}
			
		}
		
	}

}
