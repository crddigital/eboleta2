package com.workants.eboleta2.componentes;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.TextField;

public class TextFieldNumero extends TextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public TextFieldNumero(){
		
		   setCaption("Código pago electrónico:");
	       setNullSettingAllowed(false);
	       setRequired(true);
	       Validator numberValidator = new RegexpValidator("\\d{11}", "Ingrese solo digitos (11)");
	       addValidator(numberValidator);
	       setImmediate(true);
	}
	
	public TextFieldNumero(String cadena){
		
		   setCaption(cadena);		 
		   setNullSettingAllowed(false);
	       setRequired(true);
	       RegexpValidator numberValidator = new RegexpValidator("\\d*", "Ingrese solo digitos");
	       addValidator(numberValidator);
	       setImmediate(true);
	}
	
	public TextFieldNumero(String cadena, String cuit){
		
		   setCaption(cadena);
		   setNullSettingAllowed(false);
	       setRequired(true);
	       RegexpValidator numberValidator = new RegexpValidator("\\d{11}", "Ingrese solo digitos. 11 digitos para el CUIT");
	       addValidator(numberValidator);
	       setImmediate(true);
	}
	
	
	
	

}
