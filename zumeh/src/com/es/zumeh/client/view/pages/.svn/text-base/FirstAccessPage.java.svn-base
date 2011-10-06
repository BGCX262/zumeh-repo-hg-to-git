package com.es.zumeh.client.view.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.es.zumeh.client.facade.ZumehCallBack;
import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.facade.ZumehServiceAsync;
import com.es.zumeh.client.model.work.Work;
import com.es.zumeh.shared.util.Validate;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.text.client.IntegerRenderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FirstAccessPage implements EntryPoint {
	
	private TextArea whoAreYouTextArea;
	private TextBox firstNameTextBox;
	private TextBox lastNameTextBox;
	private TextBox birthDayTextBox;
	private String gender;
	private String interestAreas;
	private String birthDay;
	private RootPanel rootPanel;
	private String token;
	
	private static final String FEMALE = "Female";
	private static final String MALE = "Male";
	private static final String[] INTEREST_AREAS = {"data base and systems", 
			"distributed systems", "networks", "formal methods", 
			"software engineering", "artificial intelligence"};
	
	private final ZumehServiceAsync zumehService = GWT.create(ZumehService.class);
	
	public FirstAccessPage(String token) {
		this.token = token;
	}

	@Override
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		interestAreas = "";
		
		rootPanel = RootPanel.get("nameFieldContainer");
		rootPanel.setStyleName("rootPanelProfile");
		rootPanel.setSize("1024", "768");
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("h1");
		rootPanel.add(absolutePanel, 143, 74);
		absolutePanel.setSize("657px", "489px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		rootPanel.add(absolutePanel_1, 133, 0);
		absolutePanel_1.setSize("821px", "68px");
		
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		rootPanel.add(absolutePanel_2, 27, 45);
		absolutePanel_2.setSize("100px", "100px");
		
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel_3.setStyleName("h2");
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
		
		ValueListBox valueListBox = new ValueListBox(IntegerRenderer.instance());
		absolutePanel_3.add(valueListBox, 82, 178);
		//TODO OP��ES DE LUGARES
		
		Label lblLocation = new Label("Location:");
		absolutePanel_3.add(lblLocation, 10, 186);
		
		Label lblDescriptionAboutThis = new Label("Description about this project");
		absolutePanel.add(lblDescriptionAboutThis, 25, 388);
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
	
	public Widget onInitialize() {
		VerticalPanel vPanel = new VerticalPanel();
	    vPanel.setSpacing(5);
	    
	    vPanel.add(new Button());
		
		return vPanel;
		
	}

	private void whoAreYouTextArea(AbsolutePanel absolutePanel) {
		whoAreYouTextArea = new TextArea();
		whoAreYouTextArea.setText("Who are you?");
		absolutePanel.add(whoAreYouTextArea, 329, 266);
		whoAreYouTextArea.setSize("312px", "88px");
		whoAreYouTextArea.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				whoAreYouTextArea.setText("");
			}
		});
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
		Label lblWelcome = new Label("Welcome NOME DA PESSOA, you're in your first access" +
				" in Zumeh's site. We need to collect some informations about" +
				" you to complete your account.");
		absolutePanel_1.add(lblWelcome, 31, 10);
		lblWelcome.setSize("456px", "41px");
	}

	private void informationsAboutTheUsers(AbsolutePanel absolutePanel_3) {
		Label firstNameLabel = new Label("First name:");
		absolutePanel_3.add(firstNameLabel, 10, 10);
		
		firstNameTextBox = new TextBox(); //TODO criar verificador para o tamanho do nome
		
		absolutePanel_3.add(firstNameTextBox, 82, 10);
		firstNameTextBox.setSize("143px", "10px");
		
		setDefaultFields();
		
		Label lblLastName = new Label("Last name:");
		absolutePanel_3.add(lblLastName, 10, 39);
		
		lastNameTextBox = new TextBox(); //TODO criar verificador para o tamanho do nome
		absolutePanel_3.add(lastNameTextBox, 82, 39);
		lastNameTextBox.setSize("143px", "10px");
		
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
		absolutePanel_3.add(lblGender, 10, 120);
	}

	private void radioButtons(AbsolutePanel absolutePanel_3) {
		RadioButton rdbtnFemale = new RadioButton("new name", FEMALE);
		absolutePanel_3.add(rdbtnFemale, 62, 140);
		
		rdbtnFemale.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				setGender(FEMALE);
			}
		});
		
		RadioButton rdbtnMale = new RadioButton("new name", MALE);
		absolutePanel_3.add(rdbtnMale, 62, 118);
		rdbtnMale.setValue(true);
		setGender(MALE);
		
		rdbtnMale.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				setGender(MALE);
			}
		});

	}

	private void saveButton(AbsolutePanel absolutePanel) {
		Button btnSave = new Button("Save");
		absolutePanel.add(btnSave, 423, 388);
		btnSave.setSize("56px", "24px");
		btnSave.addClickHandler(new ClickHandler() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(ClickEvent event) {
				if (birthDay != null) {
					Window.alert("Your informations was saved with success."
							+ "\nSEXO: " + gender  +
							"\n" + birthDay + "\n" + "TESTE = " + interestAreas);
					//TODO MUDAR DE PAGINA
				} else {
					Window.alert("Some informations is incomplete.");
				}
			}
		});
	}

	private void loadImages(AbsolutePanel absolutePanel, AbsolutePanel absolutePanel_2) {
		Image image = new Image("images/macaco1.jpg");
		absolutePanel_2.add(image, 0, 0);
		image.setSize("100px", "100px");
		
		Image image_1 = new Image("images/.svn/text-base/bode2.png.svn-base");
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
				// TODO Auto-generated method stub
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Work result) {
				// TODO Auto-generated method stub
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
				firstNameTextBox.setText(result.get("firstname"));
				lastNameTextBox.setText(result.get("lastname"));
			}
			
		};
		
		zumehService.getGoogleInfo(token, callback);
	}
}
