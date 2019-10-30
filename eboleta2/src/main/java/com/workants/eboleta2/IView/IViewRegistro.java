package com.workants.eboleta2.IView;


import com.vaadin.navigator.View;
import com.workants.eboleta2.handler.IViewRegistroHandler;

public interface IViewRegistro extends View {

	public void setHandler (IViewRegistroHandler handler);
	public IViewRegistroHandler getHandler();
}
