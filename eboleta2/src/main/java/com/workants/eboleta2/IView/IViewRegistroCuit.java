package com.workants.eboleta2.IView;

import com.vaadin.navigator.View;
import com.workants.eboleta2.handler.IViewRegistroCuitHandler;

public interface IViewRegistroCuit extends View {

	
	public void setHandler(IViewRegistroCuitHandler handler);
	public IViewRegistroCuitHandler getHandler();
}
