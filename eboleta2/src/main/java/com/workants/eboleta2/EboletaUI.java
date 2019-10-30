package com.workants.eboleta2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.workants.eboleta2.model.PerfilAdministrador;
import com.workants.eboleta2.model.PerfilAdministrativo;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.presenter.PresenterPrincipal;
import com.workants.eboleta2.presenter.PresenterViewABMSuscripciones;
import com.workants.eboleta2.presenter.PresenterViewAdministrador;
import com.workants.eboleta2.presenter.PresenterViewDesuscripcion;
import com.workants.eboleta2.presenter.PresenterViewRegistro;
import com.workants.eboleta2.presenter.PresenterViewRegistroCuit;
import com.workants.eboleta2.servicio.ServicioEnvio;
import com.workants.eboleta2.servicio.ServicioRegistro;
import com.workants.eboleta2.servicio.ServicioValidar;
import com.workants.eboleta2.ui.view.ErrorView;
import com.workants.eboleta2.ui.view.ViewAdministrador.ViewAdministrador;
import com.workants.eboleta2.ui.view.ViewBaja.ViewBaja;
import com.workants.eboleta2.ui.view.ViewDesuscripcion.ViewDesuscripcion;
import com.workants.eboleta2.ui.view.ViewPrincipal.ViewPrincipal;
import com.workants.eboleta2.ui.view.ViewRegistro.ViewRegistro;
import com.workants.eboleta2.ui.view.ViewRegistroCuit.ViewRegistroCuit;
import com.workants.eboleta2.ui.view.ViewSuscripcion.ViewSuscripcion;
import com.workants.eboleta2.ui.view.responsive.ViewPrincipal.ViewPrincipalResponsive;
import com.workants.eboleta2.ui.view.responsive.ViewRegistro.ViewRegistroResponsive;
import com.vaadin.ui.UI;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("eboleta2")
public class EboletaUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Navigator navigator;
	
	
	public enum LayoutMode {
        SMALL, DESKTOP
    }
	
	

	@Override
    protected void init(VaadinRequest request) {
		
		Responsive.makeResponsive(this);		
		getPage().setTitle("Registro recepción de Impuestos MCR");
		UI.getCurrent().getSession().setAttribute("usuario", null);
		navigator = new Navigator(this,this);
		
		//Notification.show("Alto: " + getPage().getBrowserWindowHeight() + " Ancho:" +getPage().getBrowserWindowWidth());
		
		
		
		
//		if (getLayoutMode() == LayoutMode.SMALL) {
//			
//			//cels
//			ViewPrincipalResponsive viewPrincipalR = new ViewPrincipalResponsive();
//			PresenterPrincipal presenterPrincipal = new PresenterPrincipal(viewPrincipalR, ServicioValidar.getIntance());
//			viewPrincipalR.setHandler(presenterPrincipal);
//			
//			ViewRegistroResponsive viewRegistroR = new ViewRegistroResponsive();
//			//PresenterViewRegistro viewRegistroPresenter = new PresenterViewRegistro(viewRegistro, ServicioRegistro.getIntance());
//			//viewRegistro.setHandler(viewRegistroPresenter);
//			
//			
//			navigator.addView("", viewPrincipalR);		
//			navigator.addView(ViewRegistroResponsive.NAME, viewRegistroR);
//			
//			
//		}
//		
//		else{			
			//desktop
		
			
			ViewPrincipal viewPrincipal = new ViewPrincipal();
			PresenterPrincipal presenterPrincipal = new PresenterPrincipal(viewPrincipal, ServicioValidar.getIntance());
			viewPrincipal.setHandler(presenterPrincipal);
			
			ViewRegistro viewRegistro = new ViewRegistro();
			PresenterViewRegistro viewRegistroPresenter = new PresenterViewRegistro(viewRegistro, ServicioRegistro.getIntance());
			viewRegistro.setHandler(viewRegistroPresenter);
			
			
			//Disable por el momento
			//ViewAdministrativo viewAdministrativo = new ViewAdministrativo();
			//PresenterViewAdministrativo presenterViewAdministrativo = new PresenterViewAdministrativo(viewAdministrativo, ServicioSolicitudes.getInstance());
			//viewAdministrativo.setHandler(presenterViewAdministrativo);
			
			ViewAdministrador viewAdministrador = new ViewAdministrador();
			PresenterViewAdministrador presenterViewAdministrador = new PresenterViewAdministrador(viewAdministrador, ServicioEnvio.getIntance());
			viewAdministrador.setHandler(presenterViewAdministrador);
			
			
			ViewDesuscripcion viewDesuscripcion = new ViewDesuscripcion();
			PresenterViewDesuscripcion viewDesuscripcionPresenter = new PresenterViewDesuscripcion(viewDesuscripcion, ServicioValidar.getIntance());
			viewDesuscripcion.setHandler(viewDesuscripcionPresenter);
			
		//	String cpe = request.getParameter("cpe");		
		//	System.out.println("parametro enviado: " + cpe);
			ViewBaja viewBaja = new ViewBaja();
			PresenterViewABMSuscripciones presenterViewBaja = new PresenterViewABMSuscripciones(viewBaja, ServicioValidar.getIntance());
			viewBaja.setHandler(presenterViewBaja);
			
			ViewSuscripcion viewSuscripcion = new ViewSuscripcion();
			PresenterViewABMSuscripciones presenterViewSuscripcion = new PresenterViewABMSuscripciones(viewSuscripcion, ServicioValidar.getIntance());
			viewSuscripcion.setHandler(presenterViewSuscripcion);
			
			ViewRegistroCuit viewRegistroCuit = new ViewRegistroCuit();
			PresenterViewRegistroCuit presenterViewRegistroCuit = new PresenterViewRegistroCuit(viewRegistroCuit,ServicioRegistro.getIntance());
			viewRegistroCuit.setHandler(presenterViewRegistroCuit);
			
			
			
			//Registro vistas
			navigator.addView("", viewPrincipal);		
			navigator.addView(ViewRegistro.NAME, viewRegistro);
			//navigator.addView(ViewAdministrativo.NAME, viewAdministrativo);
			navigator.addView(ViewAdministrador.NAME, viewAdministrador);	
			navigator.addView(ViewDesuscripcion.NAME,viewDesuscripcion);
			navigator.addView(ViewBaja.NAME, viewBaja);
			navigator.addView(ViewSuscripcion.NAME, viewSuscripcion);
			navigator.addView(ViewRegistroCuit.NAME, viewRegistroCuit);		
			navigator.setErrorView(ErrorView.class);
	//	}
		
//		ViewPrincipal viewPrincipal = new ViewPrincipal();
//		PresenterPrincipal presenterPrincipal = new PresenterPrincipal(viewPrincipal, ServicioValidar.getIntance());
//		viewPrincipal.setHandler(presenterPrincipal);
//		
//		ViewRegistro viewRegistro = new ViewRegistro();
//		PresenterViewRegistro viewRegistroPresenter = new PresenterViewRegistro(viewRegistro, ServicioRegistro.getIntance());
//		viewRegistro.setHandler(viewRegistroPresenter);
//		
//		
//		//Disable por el momento
//		//ViewAdministrativo viewAdministrativo = new ViewAdministrativo();
//		//PresenterViewAdministrativo presenterViewAdministrativo = new PresenterViewAdministrativo(viewAdministrativo, ServicioSolicitudes.getInstance());
//		//viewAdministrativo.setHandler(presenterViewAdministrativo);
//		
//		ViewAdministrador viewAdministrador = new ViewAdministrador();
//		PresenterViewAdministrador presenterViewAdministrador = new PresenterViewAdministrador(viewAdministrador, ServicioEnvio.getIntance());
//		viewAdministrador.setHandler(presenterViewAdministrador);
//		
//		
//		ViewDesuscripcion viewDesuscripcion = new ViewDesuscripcion();
//		PresenterViewDesuscripcion viewDesuscripcionPresenter = new PresenterViewDesuscripcion(viewDesuscripcion, ServicioValidar.getIntance());
//		viewDesuscripcion.setHandler(viewDesuscripcionPresenter);
//		
//	//	String cpe = request.getParameter("cpe");		
//	//	System.out.println("parametro enviado: " + cpe);
//		ViewBaja viewBaja = new ViewBaja();
//		PresenterViewABMSuscripciones presenterViewBaja = new PresenterViewABMSuscripciones(viewBaja, ServicioValidar.getIntance());
//		viewBaja.setHandler(presenterViewBaja);
//		
//		ViewSuscripcion viewSuscripcion = new ViewSuscripcion();
//		PresenterViewABMSuscripciones presenterViewSuscripcion = new PresenterViewABMSuscripciones(viewSuscripcion, ServicioValidar.getIntance());
//		viewSuscripcion.setHandler(presenterViewSuscripcion);
//		
//		ViewRegistroCuit viewRegistroCuit = new ViewRegistroCuit();
//		PresenterViewRegistroCuit presenterViewRegistroCuit = new PresenterViewRegistroCuit(viewRegistroCuit,ServicioRegistro.getIntance());
//		viewRegistroCuit.setHandler(presenterViewRegistroCuit);
//		
//		
//		
//		//Registro vistas
//		navigator.addView("", viewPrincipal);		
//		navigator.addView(ViewRegistro.NAME, viewRegistro);
//		//navigator.addView(ViewAdministrativo.NAME, viewAdministrativo);
//		navigator.addView(ViewAdministrador.NAME, viewAdministrador);	
//		navigator.addView(ViewDesuscripcion.NAME,viewDesuscripcion);
//		navigator.addView(ViewBaja.NAME, viewBaja);
//		navigator.addView(ViewSuscripcion.NAME, viewSuscripcion);
//		navigator.addView(ViewRegistroCuit.NAME, viewRegistroCuit);		
//		navigator.setErrorView(ErrorView.class);
		
		request.getService().setSystemMessagesProvider(new SystemMessagesProvider() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public SystemMessages getSystemMessages(
					SystemMessagesInfo systemMessagesInfo) {
				 CustomizedSystemMessages msgs = new CustomizedSystemMessages();
                 msgs.setSessionExpiredMessage("Su sesion ah expirado. Recargue su navegador");
                 msgs.setSessionExpiredCaption("Atención");
                 msgs.setSessionExpiredNotificationEnabled(true);
                 msgs.setSessionExpiredURL(null);
                 return msgs;
			}
		});
		
		//atributos
		//Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");	
		
		navigator.addViewChangeListener(new ViewChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;




			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				
				
				Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");				
				View nuevaView = event.getNewView();//nueva vista a mostrar
			    String nombreNuevaView = event.getViewName(); // nombre de la nuevaVista
			    //System.out.println(usuario);
			    if(usuario != null){
			    	return verificarVisibilidadUsuario(nuevaView,nombreNuevaView);
				}else{
					if(nombreNuevaView.equalsIgnoreCase("viewRegistro")||nombreNuevaView.equalsIgnoreCase("")){
						return true;						
					} else if(nombreNuevaView.equalsIgnoreCase("viewDesuscripcion")||nombreNuevaView.equalsIgnoreCase("")){
						return true;
					} else if(nombreNuevaView.equalsIgnoreCase("viewBaja")||nombreNuevaView.equalsIgnoreCase("")){
						return true;
					} else if(nombreNuevaView.equalsIgnoreCase("viewRegistroCuit")||nombreNuevaView.equalsIgnoreCase("")){
							return true;	
					}else if(nombreNuevaView.equalsIgnoreCase("viewSuscripcion")||nombreNuevaView.equalsIgnoreCase("")){
						return true;
					}	
					else {
						vistaNoAccesible();
						return false;
					}
				}		   
			}
			
		

			private boolean verificarVisibilidadUsuario(View nuevaView,
					String nombreNuevaView) {
				
				
				Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");		
				System.out.println("nombreNuevaView:" + nombreNuevaView);
				System.out.println("nuevaView:" + nuevaView);
				if(usuario != null){
				
					/*System.out.println("esa ehh:" + nombreNuevaView);
					System.out.println("perfil:" + usuario.getPerfil().getNombreDePerfil());
					System.out.println("perfil simpleName:" + usuario.getPerfil().getClass().getSimpleName());
					*/							 
					if((usuario.getPerfil() instanceof PerfilAdministrador) && 
							(nombreNuevaView.equalsIgnoreCase("viewAdministrador") 
								||nombreNuevaView.equalsIgnoreCase("") )){
							return true;
					}else {
						//System.out.println("por el 1ero");
						//vistaNoAccesible();
						if((usuario.getPerfil() instanceof PerfilAdministrativo) && 
								(nombreNuevaView.equalsIgnoreCase("viewAdministrativo")														   
									||nombreNuevaView.equalsIgnoreCase("")) ){
									return true;
						}else{
							 if(nombreNuevaView.equalsIgnoreCase("viewRegistro")
									    ||nombreNuevaView.equalsIgnoreCase("")){
									    return true;
								}else {
									vistaNoAccesible();
									return false;
								}
						}
					}
					
				}
					else return false;

							/*
					if((usuario.getPerfil() instanceof PerfilAdministrativo) && 
						(nombreNuevaView.equalsIgnoreCase("viewAdministrativo")														   
							||nombreNuevaView.equalsIgnoreCase("")) ){
							return true;
					}else {
						System.out.println("por el 2do");
						vistaNoAccesible();				
					}
					
			        if(nombreNuevaView.equalsIgnoreCase("viewRegistrol")
						    ||nombreNuevaView.equalsIgnoreCase("")){
						    return true;
					}else {
						System.out.println("por el 3ero");
						vistaNoAccesible();	
					}
				}return false;*/
			}

			private boolean vistaNoAccesible() {
				
				Notification.show("No posee permiso para acceder a esta vista.", Type.ERROR_MESSAGE);
				return false;
			}
			
			


			@Override
			public void afterViewChange(ViewChangeEvent event) {
				
				
			}
		});		
    }
	
	private LayoutMode getLayoutMode() {
        
        String browserApplication = Page.getCurrent().getWebBrowser().getBrowserApplication();
        boolean touchDevice = Page.getCurrent().getWebBrowser().isTouchDevice();

        int width = Page.getCurrent().getBrowserWindowWidth();
        if (width < 730) {
            return LayoutMode.SMALL;
        } else {
            return LayoutMode.DESKTOP;
        }
		
        
        

    }

    @WebServlet(urlPatterns = "/*", name = "EboletaUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = EboletaUI.class, productionMode = false)
    public static class EboletaUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    }
}
