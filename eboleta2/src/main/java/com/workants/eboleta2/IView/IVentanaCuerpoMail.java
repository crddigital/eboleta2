package com.workants.eboleta2.IView;

import com.vaadin.navigator.View;
import com.workants.eboleta2.handler.IVentanaCuerpoMailHandler;

public interface IVentanaCuerpoMail extends View {


	public void setHandler(IVentanaCuerpoMailHandler handler);
	public IVentanaCuerpoMailHandler getHandler();
}
