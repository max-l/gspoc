package com.jlml.app1.client;

import com.google.gwt.user.client.ui.*;
import com.jlml.app1.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jlml.app1.shared.ProfileVO;

import java.util.ArrayList;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Salut implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final Button sendButton = new Button("Send !!!!");
    final Button showProfils = new Button("Profils");

    final TextBox nameField = new TextBox();
    nameField.setText("GWT User");
    final Label errorLabel = new Label();

    // We can add style names to widgets
    sendButton.addStyleName("sendButton");

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("sendButtonContainer").add(showProfils);
    RootPanel.get("errorLabelContainer").add(errorLabel);


    Grid gp = initPaneauProfils(RootPanel.get("paneauProfils"));
    showProfils.addClickHandler(clickProfilsHandler(gp));
    
    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
    nameField.selectAll();

    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        sendButton.setEnabled(true);
        sendButton.setFocus(true);
      }
    });

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
        sendNameToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToServer();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = nameField.getText();
        if (!FieldVerifier.isValidName(textToServer)) {
          errorLabel.setText("Please enter at least four characters");
          return;
        }

        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");
        greetingService.greetServer(textToServer, new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            dialogBox.setText("Remote Procedure Call - Failure");
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(SERVER_ERROR);
            dialogBox.center();
            closeButton.setFocus(true);
          }

          public void onSuccess(String result) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(result);
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
    nameField.addKeyUpHandler(handler);
  }

  private Grid initPaneauProfils(RootPanel rootPanel) {

        
    DockPanel dock = new DockPanel();
    dock.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
    HTML north0 = new HTML("This is the <i>first</i> north component", true);
    HTML east = new HTML("<center>This<br>is<br>the<br>east<br>component</center>", true);
    HTML south = new HTML("This is the south component");
    HTML west = new HTML("<center>This<br>is<br>the<br>west<br>component</center>", true);
    HTML north1 = new HTML("This is the <b>second</b> north component", true);
    dock.add(north0, DockPanel.NORTH);
    dock.add(east, DockPanel.EAST);
    dock.add(south, DockPanel.SOUTH);
    dock.add(west, DockPanel.WEST);
    dock.add(north1, DockPanel.NORTH);
    Grid g = new Grid(2, 3);

    g.setText(0, 0, "a1");
    g.setText(0, 1, "a1");
    g.setText(0, 2, "a1");

    g.setText(1, 0, "b1");
    g.setText(1, 1, "b1");
    g.setText(1, 2, "b1");

    dock.add(g, DockPanel.CENTER);

    rootPanel.add(dock);

    return g;
  }


  private ClickHandler clickProfilsHandler(final Grid g) {
    
    return new ClickHandler() {
      
      public void onClick(ClickEvent event) {
        greetingService.greetServer2("", new AsyncCallback<ArrayList<ProfileVO>>() {
          
          public void onFailure(Throwable caught) {
            throw new RuntimeException("!!!!", caught);
          }

          public void onSuccess(ArrayList<ProfileVO> res) {

            g.resizeRows(res.size());
            
            for(int i = 0; i < res.size(); i++) {
              g.setText(i, 0, res.get(i).nom);
              g.setText(i, 1, res.get(i).prenom);
              g.setText(i, 2, res.get(i).pseudo);             
            }
          }
        });
      }
    };
  }
}
