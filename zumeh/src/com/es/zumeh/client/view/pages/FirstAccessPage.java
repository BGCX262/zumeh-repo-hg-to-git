package com.es.zumeh.client.view.pages;

import java.util.HashMap;

import com.es.zumeh.client.model.Password;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.shared.util.Validate;
import com.google.gwt.core.client.EntryPoint;
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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class FirstAccessPage extends Page implements EntryPoint {
	
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
	private String login;
	private String password;
	private byte[] image;
	//private String fileName;
	
	
	private static final String FEMALE = "Female";
	private static final String MALE = "Male";
	
	private static final String[] INTEREST_AREAS = {"data base and systems", 
			"distributed systems", "networks", "formal methods", 
			"software engineering", "artificial intelligence"};
	
	private static final String[] LOCATIONS = {"Select...", "Campina Grande", "Pesqueira",
			"Fortaleza", "Joao Pessoa", "Recife"};
	
	private static final String MESSAGE = "Welcome to Zumeh\nThanks for use Zumeh app!" +
			"\n\nMore details HERE{Link} ";
	
	private static final String ZUMEH_USER = "zumeh.app@gmail.com";
	
	
	public FirstAccessPage(String token) {
		this.token = token;
	}

	@Override
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		
		initializeSomeVariables();
		
		rootPanel.setStyleName("profileBackGround-works");
		rootPanel.setSize("640", "480");
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		setDefaultFields();
		
		AbsolutePanel absolutePanel = createAbsolutePanel();
		
		AbsolutePanel absolutePanel_1 = createAbsolutePanel1();
		
		AbsolutePanel absolutePanel_2 = createAbsolutePanel2();
		
		AbsolutePanel absolutePanel_3 = createAbsolutePanel3(absolutePanel);
		
		whoAreYouTextArea(absolutePanel);
		
		informationsAboutTheUsers(absolutePanel_3);
		
		radioButtons(absolutePanel_3);
		
		saveButton(absolutePanel);
		
		cancelButton(absolutePanel);
		
		welcomeLabel(absolutePanel_1);
		
		loadSignOutHyperLink();
		
		loadImages(absolutePanel, absolutePanel_2);
		
	    loadMultiSuggestedBoxes(absolutePanel_3);
		
		loadLocationListBox(absolutePanel_3);
		
		loadLabels(absolutePanel_3);
		
		loadEmailTextBox(absolutePanel_3);
		
		fullNameTextBox(absolutePanel_3);
		
		AbsolutePanel absolutePanel_4 = createAbsolutePanel4(absolutePanel_3);
		
		loginAndPassTextBox(absolutePanel_4);
		
		loadLabelsFromPanel4(absolutePanel_4);
		
		loadLabelsFromPanel3(absolutePanel_3);
		
		loadLabelsFromPanel1(absolutePanel);
		
		//uploadUserImage(absolutePanel);
	}

	private void initializeSomeVariables() {
		interestAreas = "";
	}

	private AbsolutePanel createAbsolutePanel() {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("profileBackGround-works");
		rootPanel.add(absolutePanel, 143, 74);
		absolutePanel.setSize("657px", "489px");
		return absolutePanel;
	}

	private AbsolutePanel createAbsolutePanel1() {
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		rootPanel.add(absolutePanel_1, 133, 0);
		absolutePanel_1.setSize("821px", "68px");
		return absolutePanel_1;
	}

	private AbsolutePanel createAbsolutePanel2() {
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		rootPanel.add(absolutePanel_2, 10, 36);
		absolutePanel_2.setSize("135px", "181px");
		return absolutePanel_2;
	}

	private AbsolutePanel createAbsolutePanel3(AbsolutePanel absolutePanel) {
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel_3.setStyleName("teste");
		absolutePanel.add(absolutePanel_3, 10, 30);
		absolutePanel_3.setSize("313px", "459px");
		return absolutePanel_3;
	}

	private void loadMultiSuggestedBoxes(AbsolutePanel absolutePanel_3) {
		MultiWordSuggestOracle oracle = getSuggestOracle();
		suggestBoxes(absolutePanel_3, oracle);
	}

	private AbsolutePanel createAbsolutePanel4(AbsolutePanel absolutePanel_3) {
		AbsolutePanel absolutePanel_4 = new AbsolutePanel();
		absolutePanel_3.add(absolutePanel_4, 10, 356);
		absolutePanel_4.setSize("256px", "100px");
		return absolutePanel_4;
	}

	private void loadLabelsFromPanel1(AbsolutePanel absolutePanel) {
		Label lblDescriptionAboutThis = new Label("Description about this project");
		absolutePanel.add(lblDescriptionAboutThis, 415, 431);
	}

	private void loadLabelsFromPanel4(AbsolutePanel absolutePanel_4) {
		Label lblLogin = new Label("Login: ");
		absolutePanel_4.add(lblLogin, 10, 10);
		
		Label lblPassword = new Label("Password: ");
		absolutePanel_4.add(lblPassword, 10, 50);
		lblPassword.setSize("63px", "18px");
		
		Label label_4 = new Label("*");
		absolutePanel_4.add(label_4, 239, 0);
		label_4.setSize("5px", "18px");
		
		Label label_5 = new Label("*");
		absolutePanel_4.add(label_5, 240, 40);
		label_5.setSize("5px", "18px");
	}

	private void loadLabelsFromPanel3(AbsolutePanel absolutePanel_3) {
		Label label = new Label("*");
		absolutePanel_3.add(label, 234, 10);
		
		Label label_1 = new Label("*");
		absolutePanel_3.add(label_1, 234, 39);
		label_1.setSize("5px", "18px");
		
		Label label_2 = new Label("*");
		absolutePanel_3.add(label_2, 234, 123);
		label_2.setSize("5px", "18px");
		
		Label label_3 = new Label("*");
		absolutePanel_3.add(label_3, 213, 172);
		label_3.setSize("5px", "18px");
	}

	private void loadLabels(AbsolutePanel absolutePanel_3) {
		Label lblLocation = new Label("Location:");
		absolutePanel_3.add(lblLocation, 154, 170);
	}

//	private void uploadUserImage(AbsolutePanel absolutePanel) {
//		final FileUpload fileUpload = new FileUpload();
//		
//		fileUpload.addChangeHandler(new ChangeHandler() {
//			
//			@Override
//			public void onChange(ChangeEvent event) {
//				fileName = fileUpload.getFilename();
//				
//				if (fileName.length() == 0) {
//					Window.alert("Problems...");
//				} else {
//					Window.alert("OK");
//				}
//			}
//		});
//		
//		absolutePanel.add(fileUpload, 10, 0);
//		
//		
//		
//		//File file = new File(fileName);
//		File file = new File("images" + FILE_SEPARATOR + "sheldon.jpg");
//		
//		byte[] bFile = new byte[(int) file.length()];
//		
//		try {
//			BufferedImage im = new BufferedImage(width, height, imageType)
//			FileInputStream fileInputStream = new FileInputStream(file);
//			// convert file into array of bytes
//			fileInputStream.read(bFile);
//			image = bFile;
//			fileInputStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}

	private void loginAndPassTextBox(AbsolutePanel absolutePanel_4) {
		final TextBox loginTextBox = new TextBox();
		loginTextBox.setMaxLength(50);
		absolutePanel_4.add(loginTextBox, 79, 4);
		loginTextBox.setSize("146px", "16px");
		
		loginTextBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				setLogin(loginTextBox.getText());
			}
		});
		
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		passwordTextBox.setMaxLength(100);
		absolutePanel_4.add(passwordTextBox, 79, 40);
		
		passwordTextBox.addChangeHandler(passwordChangeHandler(passwordTextBox));
	}

	private ChangeHandler passwordChangeHandler(
			final PasswordTextBox passwordTextBox) {
		return new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				Password pass = new Password();
				try {
					pass.setPassword(passwordTextBox.getText());
					setPassword(pass.getPassword());
				} catch (Exception e) {
					e.printStackTrace(); //TODO LOG
				}
			}
		};
	}

	private void loadEmailTextBox(AbsolutePanel absolutePanel_3) {
		Label lblEmail = new Label("E-mail:");
		absolutePanel_3.add(lblEmail, 10, 123);
		
		emailTextBox = new TextBox();
		emailTextBox.setMaxLength(100);
		absolutePanel_3.add(emailTextBox, 82, 123);
		emailTextBox.setSize("143px", "10px");
		
		emailTextBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				setEmail(emailTextBox.getText());
				
			}
		});
	}
	
	private void setEmail(String inputEmail) {
		this.email = inputEmail;
	}
	
	private void setLogin(String inputLogin) {
		this.login = inputLogin;
		
	}
	
	private void setPassword(String inputPassword) {
		this.password = inputPassword.toString();
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
		
		btnCancel.addClickHandler(cancelClickHandler());

	}

	private ClickHandler cancelClickHandler() {
		return new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				boolean confirmation = Window.confirm("All of your " +
						"informations will be losted." +
						"\nAre you sure?");
				
				if (confirmation == true) {
					loadLoginPageAccess();
				}
			}
		};
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
		Label firstNameLabel = new Label("First name:");
		//final String temporaryFullName = "";
		absolutePanel_3.add(firstNameLabel, 10, 10);
		
		firstNameTextBox = new TextBox();
		firstNameTextBox.setMaxLength(50);
		
		absolutePanel_3.add(firstNameTextBox, 82, 10);
		firstNameTextBox.setSize("143px", "10px");
		
		firstNameTextBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				resetFullName();
				setFullName(firstNameTextBox.getText());
				
			}
		});
		
		Label lblLastName = new Label("Last name:");
		absolutePanel_3.add(lblLastName, 10, 39);
		
		lastNameTextBox = new TextBox();
		lastNameTextBox.setMaxLength(50);
		absolutePanel_3.add(lastNameTextBox, 82, 39);
		lastNameTextBox.setSize("143px", "10px");
		
		lastNameTextBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				setFullName(" " + lastNameTextBox.getText());
			}
		});
	}
	
	private void resetFullName() {
		this.fullName = "";
		
	}

	private void setFullName(String fullName) {
		this.fullName += fullName;
		
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
		return email != null &&	login != null && password != null &&
				fullName != null && !userLocation.equals("Select...");
	}

	private void saveButton(AbsolutePanel absolutePanel) {
		Button btnSave = new Button("Save");
		absolutePanel.add(btnSave, 423, 388);
		btnSave.setSize("56px", "24px");
		btnSave.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (isAlldataCompleted()) {
					
					AsyncCallback<Boolean> assyncCallback = createAsyncCallbackForUser();
					
					AsyncCallback<Void> em = createAsyncCallbackForEmail();
					
					UserTO newUser = createUser();
					zumehService.addUser(newUser, assyncCallback);
					
					//sendMail(em);
					
					Window.alert("Your informations was saved with success.");
					
					loadProfilePage(newUser);

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
				newUser.setLogin(login);
				newUser.setPassword(password);
				newUser.setImage(image);
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
		
		
		AsyncCallback<HashMap<String, String>> callback = new AsyncCallback<HashMap<String, String>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Could not retrieve user google string informations.");
				//caught.printStackTrace();
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
		
		if (!token.equals(null)) {
			zumehService.getGoogleInfo(token, callback);
		} else {
			//TODO ADD LOG
		}
			
	}
}
