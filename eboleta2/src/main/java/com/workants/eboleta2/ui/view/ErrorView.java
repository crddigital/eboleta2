package com.workants.eboleta2.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class ErrorView extends VerticalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label explanation;
	
	public ErrorView(){
		
		 setMargin(true);
	     setSpacing(true);
	     
	     //Label header = new Label("The view could not be found");
	     Label header = new Label("La vista no puede ser encontrada");
	     header.addStyleName(Reindeer.LABEL_H1);
	     addComponent(header);
	     addComponent(explanation = new Label());
	}
	
	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
        explanation.setValue(String.format(
               // "You tried to navigate to a view ('%s') that does not exist.",
        		 "Esta intentando navegar a una vista ('%s') que no existe.",
                event.getViewName()));
	
	
	}

}
