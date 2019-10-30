package com.workants.eboleta2.ui.formularios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LayoutImportarAdheridos extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Upload upload;
	private Button btnProcesarAdheridos;
	private UploadInfoWindow uploadInfoWindow;
	
	
	public LayoutImportarAdheridos() {
		
	
	LineBreakCounter lineBreakCounter = new LineBreakCounter();
	lineBreakCounter.setSlow(true);	
		
		
	this.setUpload(new Upload("Seleccionar archivo",lineBreakCounter));
	this.getUpload().setButtonCaption("Subir");
	this.getUpload().setImmediate(false);
	
	this.setBtnProcesarAdheridos(new Button("Procesar Adheridos"));
	
   
	
     
     uploadInfoWindow = new UploadInfoWindow(this.getUpload(), lineBreakCounter);
     uploadInfoWindow.center();
	
   
    
	
	setSpacing(true);
	setMargin(true);
	addComponent(this.getUpload());
	addComponent(this.getBtnProcesarAdheridos());
	
	
	this.getUpload().addStartedListener(new Upload.StartedListener() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void uploadStarted(StartedEvent event) {
		
			if (uploadInfoWindow.getParent() == null) {
                UI.getCurrent().addWindow(uploadInfoWindow);
            }
            uploadInfoWindow.setClosable(false);
            
			
		}
	});
	
	
	
	
	
	
	this.getUpload().addFinishedListener(new Upload.FinishedListener() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void uploadFinished(FinishedEvent event) {
			
			uploadInfoWindow.setClosable(true);
			
		}
	});
		
		
	}


	public Upload getUpload() {
		return upload;
	}


	public void setUpload(Upload upload) {
		this.upload = upload;
	}


	public Button getBtnProcesarAdheridos() {
		return btnProcesarAdheridos;
	}


	public void setBtnProcesarAdheridos(Button btnProcesarAdheridos) {
		this.btnProcesarAdheridos = btnProcesarAdheridos;
	}
	
	
	private static class UploadInfoWindow extends Window implements Upload.StartedListener,
	Upload.ProgressListener,Upload.FailedListener, Upload.SucceededListener, Upload.FinishedListener{
		
		 /**
		 * 
		 */
		 private static final long serialVersionUID = 1L;
		 private final Label state = new Label();
	     private final Label result = new Label();
	     private final Label fileName = new Label();
	     private final Label textualProgress = new Label();
	 
	     private final ProgressBar progressBar = new ProgressBar();
	     private final Button cancelButton;
	     private final LineBreakCounter counter;
	     
	     private UploadInfoWindow(final Upload upload, final LineBreakCounter lineBreakCounter) {
	    	 
	    	 super("Estado");
	         this.counter = lineBreakCounter;
	         
	         setResizable(false);
	         setDraggable(false);
	         
	         final FormLayout uploadInfoLayout = new FormLayout();
	         setContent(uploadInfoLayout);
	         uploadInfoLayout.setMargin(true);
	         
	         
	         final HorizontalLayout stateLayout = new HorizontalLayout();
	         stateLayout.setSpacing(true);
	         stateLayout.addComponent(state);
	         
	         cancelButton = new Button("Cancelar");
	         cancelButton.addClickListener(new ClickListener() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					
					upload.interruptUpload();
					
				}
			});
	         cancelButton.setVisible(false);
	         cancelButton.setStyleName("small");
	         stateLayout.addComponent(cancelButton);
	         
	         stateLayout.setCaption("Estado Actual");
	         state.setValue("Idle");
	         uploadInfoLayout.addComponent(stateLayout);
	         
	         fileName.setCaption("Nombre del archivo:");
	         uploadInfoLayout.addComponent(fileName);
	 
	         result.setCaption("Contador de lineas");
	         uploadInfoLayout.addComponent(result);
	 
	         progressBar.setCaption("Progreso");
	         progressBar.setVisible(false);
	         uploadInfoLayout.addComponent(progressBar);
	 
	         textualProgress.setVisible(false);
	         uploadInfoLayout.addComponent(textualProgress);
	 
	         upload.addStartedListener(this);
	         upload.addProgressListener(this);
	         upload.addFailedListener(this);
	         upload.addSucceededListener(this);
	         upload.addFinishedListener(this);
	         
	         
	    	 
	     }

		@Override
		public void uploadFinished(FinishedEvent event) {
			
			  state.setValue("Finalizado");
	          progressBar.setVisible(false);
	          textualProgress.setVisible(false);
	          cancelButton.setVisible(false);
			
		}

		@Override
		public void uploadSucceeded(SucceededEvent event) {
			
			 state.setValue("Finalizado");
			 result.setValue(counter.getLineBreakCount() + " (total)");
			
		}

		@Override
		public void uploadFailed(FailedEvent event) {
			
			result.setValue(counter.getLineBreakCount()
                    + " (counting interrupted at "
                    + Math.round(100 * progressBar.getValue()) + "%)");
			
		}

		@Override
		public void updateProgress(long readBytes, long contentLength) {
			
			  progressBar.setValue(readBytes / (float) contentLength);
	          textualProgress.setValue("Procesando " + readBytes + " bytes de " + contentLength);
	          result.setValue(counter.getLineBreakCount() + " (contando...)");
			
		}

		@Override
		public void uploadStarted(StartedEvent event) {
			
			  progressBar.setValue(0f);
	          progressBar.setVisible(true);
	          UI.getCurrent().setPollInterval(500);
	          textualProgress.setVisible(true);
	           // updates to client
	          state.setValue("Subiendo..");
	          fileName.setValue(event.getFilename());
	          cancelButton.setVisible(true);
			
		}
	     
	
		
	}

	
	public static class LineBreakCounter implements Receiver {
		 
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int counter;
	    private int total;
	    private boolean sleep;
		
	    @Override
        public OutputStream receiveUpload(final String filename, final String MIMEType) {
            counter = 0;
            total = 0;
            
    		FileOutputStream fos = null;				
			try {
				//File file = new File("C:\\pendrive\\hola\\"+filename); //<- lugar donde va a parar el archivo subido
				//File file = new File("//home//comodoro//public_html//salud_excel//"+filename);
				//file = new File("http:\\www.comodoro.gov.ar\\salud_excel\\"+filename);
				
				File file = new File("//home//comodoro//public_html//eb/files//FacturaElectronica.csv"); //hosting!	
				
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				Notification.show("Error al subir archivo", Notification.Type.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
			return fos;
            
            
            
            
            
            
//            return new OutputStream() {
//                private static final int searchedByte = '\n';
// 
//                @Override
//                public void write(final int b) {
//                    total++;
//                    if (b == searchedByte) {
//                        counter++;
//                    }
//                    if (sleep && total % 1000 == 0) {
//                        try {
//                            Thread.sleep(100);
//                        } catch (final InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            };
        }
	    
	    private int getLineBreakCount() {
            return counter;
        }
	    
		private void setSlow(boolean value) {
	          sleep = value;
	    }

}


	

	
}
