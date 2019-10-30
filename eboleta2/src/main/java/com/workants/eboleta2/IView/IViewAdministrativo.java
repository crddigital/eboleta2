package com.workants.eboleta2.IView;

import com.vaadin.navigator.View;
import com.workants.eboleta2.handler.IViewAdministrativoHandler;

public interface IViewAdministrativo extends View{

	public void setHandler(IViewAdministrativoHandler handler);
	public IViewAdministrativoHandler getHandler();
}
