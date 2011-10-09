package com.es.zumeh.client.view.pages;

import java.util.logging.Logger;

import com.es.zumeh.client.facade.ZumehCallBack;
import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.facade.ZumehServiceAsync;
import com.es.zumeh.client.view.screenfactory.ScreenFactory;
import com.es.zumeh.shared.util.StringConstants;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.oauth2.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LoginPage extends Widget implements EntryPoint {
	
	private static final Auth AUTH = Auth.get();
	private static final Logger log = Logger.getLogger(LoginPage.class.getName());
	private RootPanel rootPanel;
	private final ZumehServiceAsync zumehService = GWT.create(ZumehService.class);

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		rootPanel = RootPanel.get("nameFieldContainer");
		rootPanel.setStyleName("gwt-DialogBox");
		rootPanel.setSize("1024", "768");
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		Image image = new Image(StringConstants.ZUMEH_LOGO_IMAGE.getValue());
		rootPanel.add(image, 72, 132);
		image.setSize("245px", "171px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("h1");
		rootPanel.add(absolutePanel, 650, 92);
		absolutePanel.setSize("254px", "332px");
		
		Label lblSignInWith = new Label("Sign In With Your Account");
		absolutePanel.add(lblSignInWith, 10, 10);
		
		TextBox textBoxUserName = new TextBox();
		absolutePanel.add(textBoxUserName, 91, 58);
		textBoxUserName.setSize("145px", "13px");
		
		Label lblUsername = new Label("Username:");
		absolutePanel.add(lblUsername, 10, 58);
		
		Label lblNewLabel = new Label("e.g. pat@example.com ");
		absolutePanel.add(lblNewLabel, 91, 80);
		
		PasswordTextBox textBoxPassword = new PasswordTextBox();
		absolutePanel.add(textBoxPassword, 91, 122);
		textBoxPassword.setSize("145px", "13px");
		
		Label lblPassword = new Label("Password:");
		absolutePanel.add(lblPassword, 12, 122);
		
		Button signInButton = new Button("Sign in");
		absolutePanel.add(signInButton, 118, 185);
		signInButton.setSize("66px", "25px");
		
		Label lblStaySignedIn = new Label("Stay signed in");
		absolutePanel.add(lblStaySignedIn, 91, 161);
		
		SimpleCheckBox checkBoxStaySigned = new SimpleCheckBox();
		absolutePanel.add(checkBoxStaySigned, 65, 160);
		
		PushButton googleButton = new PushButton("Google");
		absolutePanel.add(googleButton, 163, 230);
		Image googleImage = new Image(StringConstants.GOOGLE_IMAGE.getValue());
		googleButton.getUpFace().setImage(googleImage);
		googleButton.setSize("51px", "45px");
		
		PushButton facebookButton = new PushButton("Facebook");
		absolutePanel.add(facebookButton, 76, 230);
		Image facebookImage = new Image(StringConstants.FACEBOOK_IMAGE.getValue());
		facebookButton.getUpFace().setImage(facebookImage);
		facebookButton.setSize("51px", "45px");
		
		Hyperlink cantAccessButton = new Hyperlink("Can't access your account?", true,
				"www.google.com");
		absolutePanel.add(cantAccessButton, 93, 304);
		
		cantAccessButton.setDirectionEstimator(true);
		googleButton.addClickHandler(new GoogleClickHandler(AUTH));

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

	}
	
	
	private class GoogleClickHandler implements ClickHandler {
		public Auth AUTH;
		
		public GoogleClickHandler(Auth aUTH) {
			this.AUTH = aUTH;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(ClickEvent event) {
			final AuthRequest req = new AuthRequest(StringConstants.GOOGLE_AUTH_URL.getValue(),
					StringConstants.GOOGLE_CLIENT_ID.getValue()).withScopes(
							 StringConstants.BUZZ_READONLY_SCOPE.getValue()
							+StringConstants.PROFILE_READONLY_SCOPE.getValue()
							+StringConstants.DOCS_SCOPE.getValue()
							+StringConstants.FEEDS.getValue());

			AUTH.login(req, new Callback<String, Throwable>() {
				@Override
				public void onSuccess(final String token) {
					log.info("The clickHandler was called with success. The token for this process is: " + token);
					
					zumehService.openSession(token, new ZumehCallBack() {
						
						@Override
						public void onSuccess(final String token2) {
							// TODO Auto-generated method stub
							//zumehService.getUserEmail(token, );
							//if (!userExist()) {
								//System.out.println("ACertou sem servir pra nada... " + token2);
								rootPanel.clear();
					            FirstAccessPage pagTest = ScreenFactory.getInstance().getFirstAccessPage(token);
					            pagTest.onModuleLoad();
								System.out.println("Deu certo. lol");
							
//							} else {
//								rootPanel.clear();
//								ProfileReadOnlyPage profilePage = ScreenFactory.getInstance().getProfileReadOnlyPage(token);
//								profilePage.onModuleLoad();
//							}
							
							
							
							
							
						}
						
						private boolean userExist() {
//							ZumehDAOFactory factory = ZumehDAOFactoryImpl.sharedSessionFactory();
//							UserDAO userDAO = factory.getUserDAO();
							
							//User userRec = userDAO.findByPK(user.getId());
							
							return false;
						}
						
						

					

						@Override
						public void onFailure(Throwable arg0) {
							System.out.println("Falhou sem servir pra nada...");
						}
					});
							            
		            //TODO FAZER a tela de editar dados.
		            
				}

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("Error:\n" + caught.getMessage());
				}
			});
		}
	}
}