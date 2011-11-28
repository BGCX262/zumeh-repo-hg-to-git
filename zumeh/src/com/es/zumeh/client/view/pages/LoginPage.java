package com.es.zumeh.client.view.pages;

import java.util.logging.Logger;

import com.es.zumeh.client.control.ClientSessionManager;
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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.InlineLabel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LoginPage extends Page implements EntryPoint {
	
	private static final Auth AUTH = Auth.get();
	private static final Logger log = Logger.getLogger(LoginPage.class.getName());
	private UserTO userTO;
	RootPanel rootPanel = RootPanel.get("nameFieldContainer");
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = initializeVariables();
		rootPanel.setStyleName("profileBackGround-works");
		rootPanel.setSize("640", "480");
		
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		loadImage();
		
		AbsolutePanel absolutePanel = createAbsolutePanel();

		textBoxLogin(absolutePanel);
		
		loadLabes(absolutePanel);
		
		textBoxPassword(absolutePanel);
		
		signInButton(absolutePanel);
		
		loadSimpleCheckBox(absolutePanel);
		
		cantAccessYourAccount(absolutePanel);
	}


	private Label initializeVariables() {
		userTO = new UserTO();
		final Label errorLabel = new Label();
		return errorLabel;
	}


	private AbsolutePanel createAbsolutePanel() {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("body");
		rootPanel.add(absolutePanel, 0, 0);
		absolutePanel.setPixelSize(getScreenWidth(), getScreenHeight());
		
		PushButton googleButton_1 = new PushButton("Google");
		absolutePanel.add(googleButton_1, 150, 235);
		googleButton_1.setStyleName("botaoTeste");
		googleButton_1.addClickHandler(new GoogleClickHandler(AUTH));
		Image googleImage = new Image(StringConstants.GOOGLE_IMAGE.getValue());
		googleImage.setHeight("38px");
		googleButton_1.getUpFace().setImage(googleImage);
		googleButton_1.setSize("51px", "45px");
		
		PushButton facebookButton = new PushButton("Facebook");
		absolutePanel.add(facebookButton, 38, 235);
		facebookButton.setStyleName("botaoTeste");
		Image facebookImage = new Image(StringConstants.FACEBOOK_IMAGE.getValue());
		facebookImage.setSize("47px", "42px");
		facebookButton.getUpFace().setImage(facebookImage);
		facebookButton.setSize("51px", "45px");
		
		InlineLabel nlnlblNewInlinelabel = new InlineLabel("User your google or facebook account");
		absolutePanel.add(nlnlblNewInlinelabel, 26, 203);
		nlnlblNewInlinelabel.setStyleName("h1");
		nlnlblNewInlinelabel.setSize("233px", "18px");
		
		Button btnCreateNewAccount = new Button("Create New Account");
		absolutePanel.add(btnCreateNewAccount, 47, 399);
		btnCreateNewAccount.setStyleName("botaoTeste");
		btnCreateNewAccount.addClickHandler(clickHandler());
		btnCreateNewAccount.setSize("164px", "32px");
		Image image = new Image("images/bode2.png");
		absolutePanel.add(image, 14, 10);
		image.setSize("245px", "131px");
		return absolutePanel;
	}


	private void loadLabes(AbsolutePanel absolutePanel) {
		Label lblSignInWith = new Label("Sign In With Your Account");
		absolutePanel.add(lblSignInWith, 423, 197);
		
		Label lblUsername = new Label("Username:");
		absolutePanel.add(lblUsername, 423, 245);
		
		Label lblNewLabel = new Label("e.g. peterTosh ");
		absolutePanel.add(lblNewLabel, 504, 267);
		
		
		Label lblPassword = new Label("Password:");
		absolutePanel.add(lblPassword, 425, 309);
		
		Label lblStaySignedIn = new Label("Stay signed in");
		absolutePanel.add(lblStaySignedIn, 504, 348);
	}


	private void loadSimpleCheckBox(AbsolutePanel absolutePanel) {
		SimpleCheckBox checkBoxStaySigned = new SimpleCheckBox();
		absolutePanel.add(checkBoxStaySigned, 478, 347);
	}


	private void loadImage() {
		rootPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
	}


	private void cantAccessYourAccount(AbsolutePanel absolutePanel) {
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
					ClientSessionManager clienteSessionManager = createClienteSessionManager(result);
					loadProfilePage(clienteSessionManager);
				}
			}

			private ClientSessionManager createClienteSessionManager(UserTO result) {
				ClientSessionManager clientSession = new ClientSessionManager();
				clientSession.setUserOwner(result);
				return clientSession;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				//TODO ADD LOG
			}
		};
	}
	
 
	private void signInButton(AbsolutePanel absolutePanel) {
		Button signInButton = new Button("Sign in");
		signInButton.setStyleName("botaoTeste");
		absolutePanel.add(signInButton, 530, 406);
		signInButton.setSize("66px", "25px");

		signInButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				callChecker();
			}
		});
	}
	
	private void callChecker() {
		AsyncCallback<UserTO> signInCall = signInCallback();
		zumehService.verifyUser(userTO, signInCall);
	}


	private void textBoxPassword(AbsolutePanel absolutePanel) {
		final PasswordTextBox textBoxPassword = new PasswordTextBox();
		absolutePanel.add(textBoxPassword, 504, 309);
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
		
		//addKeyPressHandler(textBoxPassword);
	}
	
	private void addKeyPressHandler(TextBox text) { //FIXME AJEITAR ISSO!
		
		text.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					callChecker();
				}
			}
		});
		
	}
	



	private void textBoxLogin(AbsolutePanel absolutePanel) {
		final TextBox textBoxUserName = new TextBox();
		absolutePanel.add(textBoxUserName, 504, 245);
		textBoxUserName.setSize("145px", "13px");
		textBoxUserName.setMaxLength(50);
		
		textBoxUserName.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				userTO.setLogin(textBoxUserName.getText());
				
			}
		});
		
		//addKeyPressHandler(textBoxUserName);
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
					
					zumehService.openSession(token, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							firstPageAccess(null);
						}

						@Override
						public void onFailure(Throwable caught) {
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