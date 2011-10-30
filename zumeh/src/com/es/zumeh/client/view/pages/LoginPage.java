package com.es.zumeh.client.view.pages;

import java.util.logging.Logger;

import com.es.zumeh.client.facade.ZumehCallBack;
import com.es.zumeh.client.model.Password;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.shared.util.StringConstants;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.oauth2.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LoginPage extends Page implements EntryPoint {
	
	private static final Auth AUTH = Auth.get();
	private static final Logger log = Logger.getLogger(LoginPage.class.getName());
	private UserTO userTO;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = initializeVariables();

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		rootPanel.setStyleName("gwt-DialogBox");
		rootPanel.setSize("640", "480");
		
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		loadImage();
		
		AbsolutePanel absolutePanel = createAbsolutePanel();

		textBoxLogin(absolutePanel);
		
		loadLabes(absolutePanel);
		
		textBoxPassword(absolutePanel);
		
		signInButton(absolutePanel);
		
		loadSimpleCheckBox(absolutePanel);
		
		PushButton googleButton = loadLoginButtons(absolutePanel);
		
		cantAccessYourAccount(absolutePanel);
		
		googleButton.addClickHandler(new GoogleClickHandler(AUTH));
	}


	private Label initializeVariables() {
		userTO = new UserTO();
		final Label errorLabel = new Label();
		return errorLabel;
	}


	private AbsolutePanel createAbsolutePanel() {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("h1");
		rootPanel.add(absolutePanel, 600, 97);
		absolutePanel.setSize("254px", "332px");
		return absolutePanel;
	}


	private void loadLabes(AbsolutePanel absolutePanel) {
		Label lblSignInWith = new Label("Sign In With Your Account");
		absolutePanel.add(lblSignInWith, 10, 10);
		
		Label lblUsername = new Label("Username:");
		absolutePanel.add(lblUsername, 10, 58);
		
		Label lblNewLabel = new Label("e.g. pat@example.com ");
		absolutePanel.add(lblNewLabel, 91, 80);
		
		
		Label lblPassword = new Label("Password:");
		absolutePanel.add(lblPassword, 12, 122);
		
		Label lblStaySignedIn = new Label("Stay signed in");
		absolutePanel.add(lblStaySignedIn, 91, 161);
	}


	private void loadSimpleCheckBox(AbsolutePanel absolutePanel) {
		SimpleCheckBox checkBoxStaySigned = new SimpleCheckBox();
		absolutePanel.add(checkBoxStaySigned, 65, 160);
	}


	private void loadImage() {
		Image image = new Image(StringConstants.ZUMEH_LOGO_IMAGE.getValue());
		rootPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
		rootPanel.add(image, 72, 132);
		image.setSize("245px", "171px");
	}


	private PushButton loadLoginButtons(AbsolutePanel absolutePanel) {
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
		return googleButton;
	}


	@SuppressWarnings("deprecation")
	private void cantAccessYourAccount(AbsolutePanel absolutePanel) {
		Hyperlink cantAccessLink = new Hyperlink("Can't access your account?", true,
				"");
		absolutePanel.add(cantAccessLink, 93, 304);
		
		cantAccessLink.addClickHandler(clickHandler());
		
	}


	private ClickHandler clickHandler() {
		return new ClickHandler() {
			
			@Override
			public void onClick(final ClickEvent event) {
				firstPageAccess(null);
			}
		};
	}
	
	private AsyncCallback<UserTO> signInCallback() {
		return new AsyncCallback<UserTO>() {
			
			@Override
			public void onSuccess(UserTO result) {
				if (result == null) {
					Window.alert("Incorrect username or password. Please try again!");
				} else {
					loadProfilePage(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				//TODO ADD LOG
			}
		};
	}
	

	private void signInButton(AbsolutePanel absolutePanel) {
		Button signInButton = new Button("Sign in");
		absolutePanel.add(signInButton, 118, 185);
		signInButton.setSize("66px", "25px"); //FIXME add keydownhandler
		
		final AsyncCallback<UserTO> signInCall = signInCallback();
		
		signInButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				zumehService.verifyUser(userTO, signInCall);
			}
		});
	}


	private void textBoxPassword(AbsolutePanel absolutePanel) {
		final PasswordTextBox textBoxPassword = new PasswordTextBox();
		absolutePanel.add(textBoxPassword, 91, 122);
		textBoxPassword.setSize("145px", "13px");
		textBoxPassword.setMaxLength(100);
		
		textBoxPassword.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				Password pass = new Password();
				try {
					pass.setPassword(textBoxPassword.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
				userTO.setPassword(pass.getPassword());
				
			}
		});
	}


	private void textBoxLogin(AbsolutePanel absolutePanel) {
		final TextBox textBoxUserName = new TextBox();
		absolutePanel.add(textBoxUserName, 91, 58);
		textBoxUserName.setSize("145px", "13px");
		textBoxUserName.setMaxLength(50);
		
		textBoxUserName.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				userTO.setLogin(textBoxUserName.getText());
				
			}
		});
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
							firstPageAccess(token);
							//TODO add LOG
						}

						@Override
						public void onFailure(Throwable arg0) {
							System.out.println("FALHOU ");
							//TODO ADD LOG
						}
					});
							            
				}

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("Error:\n" + caught.getMessage());
				}
			});
		}
	}
}