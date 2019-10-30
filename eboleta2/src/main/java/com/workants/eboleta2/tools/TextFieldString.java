package com.workants.eboleta2.tools;

import com.vaadin.ui.TextField;

public class TextFieldString extends TextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TextFieldString (String caption){
		
		setCaption(caption);
		setNullSettingAllowed(false);
		setRequired(true);
		//Validator stringValidator = new RegexpValidator("","Ingrese solo letras validas");
	}
	
	
	

}
