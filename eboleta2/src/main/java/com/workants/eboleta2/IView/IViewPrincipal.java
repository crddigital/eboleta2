package com.workants.eboleta2.IView;

import com.vaadin.navigator.View;
import com.workants.eboleta2.handler.IViewPrincipalHandler;

public interface IViewPrincipal extends View {

	public void setHandler (IViewPrincipalHandler handler);
	public IViewPrincipalHandler getHandler();
}
