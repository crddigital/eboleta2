package com.workants.eboleta2.componentes;


import com.vaadin.data.Validator;
import com.vaadin.ui.Notification;


public class MyValidator implements Validator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MyValidator(){
	
	}

	@Override
	public void validate(Object value) throws InvalidValueException {
			
		
		String valor = (String) value;
		if(!valor.matches("^[a-zA-Z_áéíóúñ\\s]*$")){		
			Notification.show("Ingrese solo caracteres");			
		}
	}
	
	

}