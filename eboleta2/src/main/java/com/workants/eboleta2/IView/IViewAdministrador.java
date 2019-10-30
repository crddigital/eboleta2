package com.workants.eboleta2.IView;

import com.vaadin.navigator.View;
import com.workants.eboleta2.handler.IViewAdministradorHandler;

public interface IViewAdministrador extends View {

	
	public void setHandler (IViewAdministradorHandler handler);
	public IViewAdministradorHandler getHandler ();
}
