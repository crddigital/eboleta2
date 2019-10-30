package com.workants.eboleta2.IView;

import com.vaadin.navigator.View;
import com.workants.eboleta2.handler.ILayoutABMUsuariosHandler;

public interface ILayoutABMUsuarios extends View{
	
	
	public void setHandler(ILayoutABMUsuariosHandler handler);
	public ILayoutABMUsuariosHandler getHandler();

}
