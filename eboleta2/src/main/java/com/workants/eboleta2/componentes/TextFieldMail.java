package com.workants.eboleta2.componentes;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.TextField;

public class TextFieldMail extends TextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TextFieldMail(String cadena){
		
		   setCaption(cadena);       
	       setNullSettingAllowed(false);
	       setRequired(true);
	       Validator StringValidator = new RegexpValidator("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$", "Dirección de correo mal compuesta");
	       addValidator(StringValidator);
	       setImmediate(true);
		
	}
	
	

}
