package com.workants.eboleta2.componentes;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.TextField;

public class TextFieldString extends TextField {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TextFieldString(String cadena){
		
		   setCaption(cadena);       
	       setNullSettingAllowed(false);
	       setRequired(true);
	       Validator StringValidator = new RegexpValidator("^[a-zA-Z_áéíóúñ.\\s]*$", "Ingrese solo letras válido");
	       addValidator(StringValidator);
	       setImmediate(true);
	       

		 
	}

}
