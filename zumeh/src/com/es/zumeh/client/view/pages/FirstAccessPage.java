package com.es.zumeh.client.view.pages;

import java.util.HashMap;

import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.facade.ZumehServiceAsync;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.client.model.work.Work;
import com.es.zumeh.client.view.screenfactory.ScreenFactory;
import com.es.zumeh.shared.util.Validate;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class FirstAccessPage implements EntryPoint {
	
	private TextArea whoAreYouTextArea;
	private TextBox firstNameTextBox;
	private TextBox lastNameTextBox;
	private TextBox birthDayTextBox;
	private TextBox emailTextBox;
	private String gender;
	private String interestAreas;
	private String birthDay;
	private String token;
	private String userLocation;
	private String email;
	private String whoAreYou;
	private String fullName;
	
	private RootPanel rootPanel;
	
	private static final String FEMALE = "Female";
	private static final String MALE = "Male";
	
	private static final String[] INTEREST_AREAS = {"data base and systems", 
			"distributed systems", "networks", "formal methods", 
			"software engineering", "artificial intelligence"};
	
	private static final String[] LOCATIONS = {"Campina Grande", "Pesqueira",
			"Fortaleza", "Joao Pessoa", "Recife"};
	
	private static final String MESSAGE = "Welcome to Zumeh\nThanks for use Zumeh app!" +
			"\n\nMore details HERE{Link} ";
	
	private final ZumehServiceAsync zumehService = GWT.create(ZumehService.class);
	
	private static final String ZUMEH_USER = "zumeh.app@gmail.com";
	
	
	public FirstAccessPage(String token) {
		this.token = token;
	}

	@Override
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		interestAreas = "";
		fullName = "";
		
		rootPanel = RootPanel.get("nameFieldContainer");
		rootPanel.setStyleName("profileBackGround-works");
		rootPanel.setSize("640", "480");
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("profileBackGround-works");
		rootPanel.add(absolutePanel, 143, 74);
		absolutePanel.setSize("657px", "489px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		rootPanel.add(absolutePanel_1, 133, 0);
		absolutePanel_1.setSize("821px", "68px");
		
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		rootPanel.add(absolutePanel_2, 10, 36);
		absolutePanel_2.setSize("135px", "181px");
		
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel_3.setStyleName("teste");
		absolutePanel.add(absolutePanel_3, 10, 20);
		absolutePanel_3.setSize("313px", "350px");
		
		whoAreYouTextArea(absolutePanel);
		
		informationsAboutTheUsers(absolutePanel_3);
		
		radioButtons(absolutePanel_3);
		
		saveButton(absolutePanel);
		
		cancelButton(absolutePanel);
		
		welcomeLabel(absolutePanel_1);
		
		Hyperlink hprlnkSignOut = new Hyperlink("sign out", false, "");
		absolutePanel_1.add(hprlnkSignOut, 749, 10);
		hprlnkSignOut.asWidget();
		
		loadImages(absolutePanel, absolutePanel_2);
		
	    MultiWordSuggestOracle oracle = getSuggestOracle();
		suggestBoxes(absolutePanel_3, oracle);
		
		loadLocationListBox(absolutePanel_3);
		
		Label lblLocation = new Label("Location:");
		absolutePanel_3.add(lblLocation, 154, 170);
		
		emailTextBox(absolutePanel_3);
		
		Label lblDescriptionAboutThis = new Label("Description about this project");
		absolutePanel.add(lblDescriptionAboutThis, 25, 388);
	}

	private void emailTextBox(AbsolutePanel absolutePanel_3) {
		Label lblEmail = new Label("E-mail:");
		absolutePanel_3.add(lblEmail, 10, 123);
		
		emailTextBox = new TextBox();
		absolutePanel_3.add(emailTextBox, 82, 123);
		emailTextBox.setSize("143px", "10px");
		
		emailTextBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				email = emailTextBox.getText();
				
			}
		});
		
	}

	private void loadLocationListBox(AbsolutePanel absolutePanel_3) {
		final ListBox locationListBox = new ListBox();
		absolutePanel_3.add(locationListBox, 154, 190);
		
		for (int i = 0; i < LOCATIONS.length; i++) {
			locationListBox.addItem(LOCATIONS[i]);
		}
		
		locationListBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				int index = locationListBox.getSelectedIndex();
				setUserLocation(locationListBox.getItemText(index));
				
			}
		});
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	private void cancelButton(AbsolutePanel absolutePanel) {
		Button btnCancel = new Button("Cancel");
		btnCancel.setText("Cancel");
		absolutePanel.add(btnCancel, 503, 388);
		btnCancel.setSize("56px", "24px"); //FIXME ajeitar aqui!
//		btnCancel.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				//log.info("The clickHandler was called with success. The token for this process is: " + token);
//				
//	            rootPanel.clear();
//	            LoginPage loginPage = ScreenFactory.getInstance().getLoginPage();
//	            loginPage.onModuleLoad();
//				
//			}
//		});
	}

	private void whoAreYouTextArea(AbsolutePanel absolutePanel) {
		whoAreYouTextArea = new TextArea();
		whoAreYouTextArea.setText("Who are you?");
		absolutePanel.add(whoAreYouTextArea, 329, 266);
		whoAreYouTextArea.setSize("312px", "88px");
		whoAreYouTextArea.addFocusHandler(new FocusHandler() {
			
		int counter = 0;

			@Override
			public void onFocus(FocusEvent event) {
				if (counter == 0) {
					whoAreYouTextArea.setText("");
					counter++;
				}
			}
		});
		
		whoAreYouTextArea.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				setWhoAreYou(whoAreYouTextArea.getText());
			}
		});
	}

	public String getWhoAreYou() {
		return whoAreYou;
	}

	public void setWhoAreYou(String whoAreYou) {
		this.whoAreYou = whoAreYou;
	}

	private void suggestBoxes(AbsolutePanel absolutePanel_3,
			MultiWordSuggestOracle oracle) {
		
		AbsolutePanel absolutePanel_4 = new AbsolutePanel();
		absolutePanel_3.add(absolutePanel_4, 0, 222);
		absolutePanel_4.setSize("266px", "128px");

		final SuggestBox suggestBox = new SuggestBox(oracle);
		suggestBox.addSelectionHandler(new SelectionHandler<Suggestion>() {
			public void onSelection(SelectionEvent<Suggestion> event) {
				setInterestAreas(suggestBox.getValue());
			}
		});
		absolutePanel_4.add(suggestBox, 105, 19);
		suggestBox.ensureDebugId("cwSuggestBox");
		suggestBox.setSize("141px", "17px");

		Label lblNewLabel = new Label("Interest areas:");
		absolutePanel_4.add(lblNewLabel, 10, 10);

		final SuggestBox suggestBox_1 = new SuggestBox(oracle);
		suggestBox_1.addSelectionHandler(new SelectionHandler<Suggestion>() {
			public void onSelection(SelectionEvent<Suggestion> event) {
				setInterestAreas(suggestBox_1.getValue());
			}
		});
		absolutePanel_4.add(suggestBox_1, 105, 54);
		suggestBox_1.setSize("141px", "17px");

		final SuggestBox suggestBox_2 = new SuggestBox(oracle);
		suggestBox_2.addSelectionHandler(new SelectionHandler<Suggestion>() {
			public void onSelection(SelectionEvent<Suggestion> event) {
				setInterestAreas(suggestBox_2.getValue());
			}
		});
		absolutePanel_4.add(suggestBox_2, 105, 89);
		suggestBox_2.setSize("141px", "17px");		
	}


	private MultiWordSuggestOracle getSuggestOracle() {
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	    for (int i = 0; i < INTEREST_AREAS.length; ++i) {
	      oracle.add(INTEREST_AREAS[i]);
	    }
		return oracle;
	}

	private void welcomeLabel(AbsolutePanel absolutePanel_1) {
		Label lblWelcome = new Label("Welcome,  you're in your first access in" +
				" Zumeh's site. We need to collect some informations about" +
				" you to complete your account.");
		absolutePanel_1.add(lblWelcome, 31, 10);
		lblWelcome.setSize("456px", "41px");
	}

	private void informationsAboutTheUsers(AbsolutePanel absolutePanel_3) {
		fullNameTextBox(absolutePanel_3);
		
		Label lblBirthday = new Label("Birthday:");
		absolutePanel_3.add(lblBirthday, 10, 70);
		
		birthDayTextBox = new TextBox();
		absolutePanel_3.add(birthDayTextBox, 82, 70);
		birthDayTextBox.setSize("143px", "10px");
		birthDayTextBox.setMaxLength(10);
		birthDayTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (Validate.verifyDate(birthDayTextBox.getText()))
					birthDay = birthDayTextBox.getText();
			}
		});
		
		Label lblDdmmyyy = new Label("dd/mm/yyyy");
		absolutePanel_3.add(lblDdmmyyy, 82, 86);
		lblDdmmyyy.setSize("74px", "18px");
		
		Label lblGender = new Label("Gender:");
		absolutePanel_3.add(lblGender, 10, 172);
	}

	private void fullNameTextBox(AbsolutePanel absolutePanel_3) {
		setDefaultFields();
		
		Label firstNameLabel = new Label("First name:");
		absolutePanel_3.add(firstNameLabel, 10, 10);
		
		firstNameTextBox = new TextBox();
		
		absolutePanel_3.add(firstNameTextBox, 82, 10);
		firstNameTextBox.setSize("143px", "10px");
		fullName += firstNameTextBox.getText();
		
		firstNameTextBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				fullName += firstNameTextBox.getSelectedText();
				
			}
		});
		
		Label lblLastName = new Label("Last name:");
		absolutePanel_3.add(lblLastName, 10, 39);
		
		lastNameTextBox = new TextBox(); //TODO criar verificador para o tamanho do nome
		absolutePanel_3.add(lastNameTextBox, 82, 39);
		lastNameTextBox.setSize("143px", "10px");
		fullName += " " + lastNameTextBox.getText();
		
		lastNameTextBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				fullName += " " + lastNameTextBox.getSelectedText();
				
			}
		});
	}

	private void radioButtons(AbsolutePanel absolutePanel_3) {
		RadioButton rdbtnFemale = new RadioButton("new name", FEMALE);
		absolutePanel_3.add(rdbtnFemale, 63, 196);
		
		rdbtnFemale.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				setGender(FEMALE);
			}
		});
		
		RadioButton rdbtnMale = new RadioButton("new name", MALE);
		absolutePanel_3.add(rdbtnMale, 63, 170);
		rdbtnMale.setValue(true);
		setGender(MALE);
		
		rdbtnMale.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				setGender(MALE);
			}
		});

	}
	
	private boolean isAlldataCompleted() {
		return true;//birthDay != null;
	}

	private void saveButton(AbsolutePanel absolutePanel) {
		Button btnSave = new Button("Save");
		absolutePanel.add(btnSave, 423, 388);
		btnSave.setSize("56px", "24px");
		btnSave.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (isAlldataCompleted()) {
					
					AsyncCallback<Boolean> w = createAsyncCallbackForUser();
					
					AsyncCallback<Void> em = createAsyncCallbackForEmail();
					
					UserTO newUser = createUser(); //FIXME Deveria adicionar o usuario no BD.
					zumehService.addUser(newUser, w); //FIXME ADD USER
					
					sendMail(em);
					
					Window.confirm("Your informations was saved with success.");
					
					rootPanel.clear();
					ProfileReadOnlyPage profilePage = ScreenFactory.getInstance().
							getProfileReadOnlyPage(newUser);
					profilePage.onModuleLoad();

				} else {
					Window.alert("Some informations is incomplete.");
				}
			}

			private void sendMail(AsyncCallback<Void> em) {
				zumehService.sendEmail(ZUMEH_USER, email,
						"Welcome to Zumeh", MESSAGE, em);
			}

			private AsyncCallback<Boolean> createAsyncCallbackForUser() {
				AsyncCallback<Boolean> w = new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(Boolean result) {
						System.out.println("DEBUG1: On succes do createAsyncCallbackForUser");
						System.out.println(result);
					}
				};
				return w;
			}

			private AsyncCallback<Void> createAsyncCallbackForEmail() {
				AsyncCallback<Void> em = new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(Void result) {
						System.out.println(result);
						
					}
				};
				return em;
			}

			private UserTO createUser() {
				UserTO newUser = new UserTO();
				newUser.setBirthday(birthDay);
				newUser.setEmail(email);
				newUser.setName(fullName);
				newUser.setGender(gender);
				newUser.setInterestedArea(interestAreas);
				newUser.setLocation(userLocation);
				newUser.setWhoAreYou(whoAreYou);
				return newUser;
			}
		});
	}

	private void loadImages(AbsolutePanel absolutePanel, AbsolutePanel absolutePanel_2) {
		Image image = new Image("images/sheldon.jpg");
		absolutePanel_2.add(image, 14, 0);
		image.setSize("121px", "164px");
		
		Image image_1 = new Image("images/bode2.png");
		absolutePanel.add(image_1, 358, 20);
		image_1.setSize("289px", "218px");
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getInterestAreas() {
		return interestAreas;
	}

	public void setInterestAreas(String interestAreas) {
		this.interestAreas += interestAreas + " ";
	}
	
	private void setDefaultFields() {
		
		AsyncCallback<Work> w = new AsyncCallback<Work>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Work result) {
				System.out.println(result);
			}
		};
		
		zumehService.getWork(0, w);
		zumehService.getWork(1, w);
		
		
		
		AsyncCallback<HashMap<String, String>> callback = new AsyncCallback<HashMap<String, String>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Could not retrieve user google string informations.");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(HashMap<String, String> result) {
				String firstName = result.get("firstname");
				String lastName = result.get("lastname");
				email = result.get("email");
				fullName = completeName(firstName, lastName);
				
				
				firstNameTextBox.setText(firstName);
				lastNameTextBox.setText(lastName);
				emailTextBox.setText(email);
			}

			private String completeName(String firstName, String lastName) {
				return firstName + " " + lastName;
			}
			
		};
		
		zumehService.getGoogleInfo(token, callback);
	}
}
