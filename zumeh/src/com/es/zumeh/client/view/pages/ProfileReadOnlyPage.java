package com.es.zumeh.client.view.pages;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.UserTO;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class ProfileReadOnlyPage extends Page implements EntryPoint {
	
	private ClientSessionManager clientSessionManager;
	
	//private RootPanel rootPanel = RootPanel.get("nameFieldContainer");
	
	private AbsolutePanel absolutePanel;
	
	private boolean isVisitor;
	
	FlowPanel panelImages;
	
	RootPanel rootPanel = RootPanel.get("nameFieldContainer");

	public ProfileReadOnlyPage(ClientSessionManager clienteSessionManger) {
		setClientSessionManger(clienteSessionManger);
	}
	
	public ProfileReadOnlyPage(ClientSessionManager clienteSessionManger, boolean isVisitor) {
		setClientSessionManger(clienteSessionManger);
		setVisitor(isVisitor);
	}

	@Override
	public void onModuleLoad() {
		
		final Label errorLabel = new Label();
		
		final MultiUploader defaultUploader = new MultiUploader();
		
		panelImages = new FlowPanel();
		
		defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
		
		rootPanel.setSize("640", "480");
		
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		rootPanel.setStyleName("body");
		
		AbsolutePanel absoluteRootPanel = createAbsolutePanel();
		
		loadCreateRevisionButton(absoluteRootPanel);
		
		AbsolutePanel imageAbsolutePanel = createImageAbsolutePanel(absoluteRootPanel);
		
		loadImage(imageAbsolutePanel);
		
		loadAllUsers();
		
		AbsolutePanel descriptionAbsolutePanel = 
				createDescriptionAbsolutePanel(absoluteRootPanel);
		
		nameEmailLabel(descriptionAbsolutePanel);
		
		loadLocationAndBirthday(descriptionAbsolutePanel);
		
		whoAreYouText(descriptionAbsolutePanel);
		
		loadInterestedAreasLabel(descriptionAbsolutePanel);
		
		setAbsolutePanel(createAbsolutePanel(absoluteRootPanel));
		
		AbsolutePanel absolutePanel_1 = createAbsolutePanel1(absoluteRootPanel);
		
		AbsolutePanel absolutePanel_3 = createAbsolutePanel3(absolutePanel_1);
		
		loadHyperlinkWorker1(absolutePanel_3);
//		
//		loadHyperlinkWorker2(absolutePanel_3);
//		
//		loadHyperlinkWorker3(absolutePanel_3);
		
		AbsolutePanel absolutePanel_2 = createAbsolutePanel2(absoluteRootPanel);
		
		absolutePanel_2.add(createAboutUsLink());
		
		loadHomeButton(absoluteRootPanel);
		
		loadSignOutHyperLink();
		
		
	}

	private void loadCreateRevisionButton(AbsolutePanel absoluteRootPanel) {
		PushButton pushButtonCreateRevision = new PushButton("Create Revision");
		absoluteRootPanel.add(pushButtonCreateRevision, 183, 11);
		
		pushButtonCreateRevision.addClickHandler(pushButtonClickHandler());
	}

	private ClickHandler pushButtonClickHandler() {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loadWorkPage(clientSessionManager, new RevisionTO());
			}
		};
	}
	
	// Load the image in the document and in the case of success attach it to the viewer
	  private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
	    public void onFinish(IUploader uploader) {
	      if (uploader.getStatus() == Status.SUCCESS) {

	        new PreloadedImage(uploader.fileUrl(), showImage);
	        
	        // The server sends useful information to the client by default
	        UploadedInfo info = uploader.getServerInfo();
	        System.out.println("File name " + info.name);
	        System.out.println("File content-type " + info.ctype);
	        System.out.println("File size " + info.size);

	        // You can send any customized message and parse it 
	        System.out.println("Server message " + info.message);
	      }
	    }
	  };
	  
	  // Attach an image to the pictures viewer
	  private OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
	    public void onLoad(PreloadedImage image) {
	      image.setWidth("75px");
	      panelImages.add(image);
	    }
	  };

	private AbsolutePanel createAbsolutePanel() {
		AbsolutePanel absoluteRootPanel = new AbsolutePanel();
		absoluteRootPanel.setStyleName("profileBackGround");
		rootPanel.add(absoluteRootPanel, -26, 0);
		absoluteRootPanel.setSize("978px", "660px");
		return absoluteRootPanel;
	}

	private void loadHomeButton(AbsolutePanel absoluteRootPanel) {
		PushButton pshbtnHome = new PushButton("Home");
		absoluteRootPanel.add(pshbtnHome, 38, 11);
		pshbtnHome.setSize("109px", "18px");
		
		pshbtnHome.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				loadProfilePage(getClientSessionManger());
			}
		});
	}

	private void loadAllUsers() {
		zumehService.getUserList(new AsyncCallback<UserTO[]>() {
			
			@Override
			public void onSuccess(UserTO[] result) {
				loadStackLayoutPanel(getAbsolutePanel(), result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Add log
				
			}
		});
	}

	
	private AbsolutePanel createImageAbsolutePanel(
			AbsolutePanel absoluteRootPanel) {
		AbsolutePanel imageAbsolutePanel = new AbsolutePanel();
		imageAbsolutePanel.setStyleName("imagePanelSettings");
		absoluteRootPanel.add(imageAbsolutePanel, 38, 43);
		imageAbsolutePanel.setSize("121px", "164px");
		return imageAbsolutePanel;
	}
	
//	private Widget createAboutUsLink() {
//		
//	    final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
//	    simplePopup.ensureDebugId("cwBasicPopup-simplePopup");
//	    simplePopup.setWidth("150px");
//	    simplePopup.setWidget(new HTML("Algumas coisas sobre o nosso projeto"));
//	    
//	    Anchor link = new Anchor("About Us");
//	    link.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//	            Widget source = (Widget) event.getSource();
//	            int left = source.getAbsoluteLeft() + 10;
//	            int top = source.getAbsoluteTop() - 100;
//	            simplePopup.setPopupPosition(left, top);
//	            
//	            simplePopup.show();
//				
//			}
//		});
//	    VerticalPanel vPanel = new VerticalPanel();
//	    vPanel.setSpacing(5);
//	    vPanel.add(link);
//
//	    return vPanel;
//	}

	private AbsolutePanel createAbsolutePanel2(AbsolutePanel absoluteRootPanel) {
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		absoluteRootPanel.add(absolutePanel_2, 47, 614);
		absolutePanel_2.setSize("783px", "36px");
		return absolutePanel_2;
	}

	private void loadHyperlinkWorker3(AbsolutePanel absolutePanel_3) {
		Hyperlink hprlnkWork = new Hyperlink("Work 3", false, "newHistoryToken");
		absolutePanel_3.add(hprlnkWork, 0, 58);
	}

	private void loadHyperlinkWorker2(AbsolutePanel absolutePanel_3) {
		Hyperlink hprlnkWorker_1 = new Hyperlink("Work 2", false, "newHistoryToken");
		absolutePanel_3.add(hprlnkWorker_1, 0, 34);
	}

	private void loadHyperlinkWorker1(AbsolutePanel absolutePanel_3) { //TODO TO AQUI
		
		VerticalPanel verticalPanelWorkLinks = new VerticalPanel();
		absolutePanel_3.add(verticalPanelWorkLinks);
		verticalPanelWorkLinks.setWidth("158px");
		
		if (isVisitor) {
			zumehService.getAllRevisionsByOwner(clientSessionManager.
					getUserFriend().getEmail(),
					createGetRevisionsAsyncCallback(verticalPanelWorkLinks));
		} else {
		
			zumehService.getAllRevisionsByOwner(clientSessionManager.
					getUserOwner().getEmail(),
					createGetRevisionsAsyncCallback(verticalPanelWorkLinks));
		}
	}

	private AsyncCallback<RevisionTO[]> createGetRevisionsAsyncCallback(
			final VerticalPanel verticalPanelWorkLinks) {
		return new AsyncCallback<RevisionTO[]>() {
			
			@Override
			public void onSuccess(RevisionTO[] result) {
				for (int i = 0; i < result.length; i++) {
					createAnchor(result[i], verticalPanelWorkLinks);
				}
			}
			
			private void createAnchor(RevisionTO revisionTO,
					VerticalPanel verticalPanelWorkLinks) {
				Anchor anchor = new Anchor(revisionTO.getShortDescriptionText());
				verticalPanelWorkLinks.setSpacing(10);
				verticalPanelWorkLinks.add(anchor);
				
				anchor.addClickHandler(createAnchorClickHandler(revisionTO));
			}

			private ClickHandler createAnchorClickHandler(final RevisionTO revisionTO) {
				return new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						if (isVisitor) {
							loadWorkReadOnlyPage(clientSessionManager, revisionTO);
						} else {
							loadWorkPage(clientSessionManager, revisionTO);
						}
					}
				};
			}

			@Override
			public void onFailure(Throwable caught) {
				//TODO ADD logg
			}
		};
	}

	private AbsolutePanel createAbsolutePanel3(AbsolutePanel absolutePanel_1) {
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel_1.add(absolutePanel_3, 115, 35);
		absolutePanel_3.setSize("100px", "158px");
		return absolutePanel_3;
	}

	private AbsolutePanel createAbsolutePanel1(AbsolutePanel absoluteRootPanel) {
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel_1.setStyleName("profileBackGround-works");
		absoluteRootPanel.add(absolutePanel_1, 269, 236);
		absolutePanel_1.setSize("435px", "261px");
		return absolutePanel_1;
	}

	private AbsolutePanel createAbsolutePanel(AbsolutePanel absoluteRootPanel) {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absoluteRootPanel.add(absolutePanel, 38, 239);
		absolutePanel.setSize("141px", "352px");
		return absolutePanel;
	}

	private AbsolutePanel createDescriptionAbsolutePanel(
			AbsolutePanel absoluteRootPanel) {
		AbsolutePanel descriptionAbsolutePanel = new AbsolutePanel();
		descriptionAbsolutePanel.setStyleName("teste");
		absoluteRootPanel.add(descriptionAbsolutePanel, 181, 43);
		descriptionAbsolutePanel.setSize("542px", "164px");
		
		return descriptionAbsolutePanel;
	}

	private void loadLocationAndBirthday(AbsolutePanel descriptionAbsolutePanel) {
		if (isVisitor()) {
			createLocationAndBirthdayLabel(descriptionAbsolutePanel, getClientSessionManger().getUserFriend());
		} else {
			createLocationAndBirthdayLabel(descriptionAbsolutePanel, getClientSessionManger().getUserOwner());
		}
	}

	private void createLocationAndBirthdayLabel(AbsolutePanel descriptionAbsolutePanel, UserTO user) {
		Label lblLocation = new Label("Location: " + user.getLocation());
		descriptionAbsolutePanel.add(lblLocation, 10, 117);
		
		if (isValidBirthday(user.getBirthday())) {
		
			Label birthLabel = new Label("Birthday: " + user.getBirthday());
			descriptionAbsolutePanel.add(birthLabel, 10, 137);
		}
	}
	
	private boolean isValidBirthday(String birthday) {
		return (birthday != null &&
				!birthday.equals(""));
	}

	private void loadInterestedAreasLabel(AbsolutePanel descriptionAbsolutePanel) {
		if (isVisitor()) {
			createInterestedAreasLabel(descriptionAbsolutePanel,
					getClientSessionManger().getUserFriend());
		} else {
			createInterestedAreasLabel(descriptionAbsolutePanel,
					getClientSessionManger().getUserOwner());
		}
	}

	private void createInterestedAreasLabel(AbsolutePanel descriptionAbsolutePanel,
			UserTO user) {
		Label lblInterestedAreas = new Label("Interested Areas: " +
				user.getInterestedArea());
		descriptionAbsolutePanel.add(lblInterestedAreas, 10, 97);
	}

	private void loadImage(AbsolutePanel imageAbsolutePanel) {
		Image image = new Image("images/sheldon.jpg");
		imageAbsolutePanel.add(image, 0, 0);
		image.setStyleName("imageProfileBoard");
		image.setSize("121px", "164px");
	}

	private void whoAreYouText(AbsolutePanel descriptionAbsolutePanel) {
		if (isVisitor()) {
			loadTextAreaByUser(descriptionAbsolutePanel, getClientSessionManger().getUserFriend());
		} else {
			loadTextAreaByUser(descriptionAbsolutePanel, getClientSessionManger().getUserOwner());
		}
		
	}

	private void loadTextAreaByUser(AbsolutePanel descriptionAbsolutePanel, UserTO user) {
		TextArea whoAreYoutextArea = new TextArea();
		whoAreYoutextArea.setReadOnly(true);
		whoAreYoutextArea.setText(user.getWhoAreYou());
		descriptionAbsolutePanel.add(whoAreYoutextArea, 10, 36);
		whoAreYoutextArea.setSize("512px", "48px");
	}

	private void nameEmailLabel(AbsolutePanel descriptionAbsolutePanel) {
		if(isVisitor()) {
			getLabelWithUserInformations(descriptionAbsolutePanel, getClientSessionManger().getUserFriend());
		} else {
			getLabelWithUserInformations(descriptionAbsolutePanel, getClientSessionManger().getUserOwner());
		}
		
	}

	private void getLabelWithUserInformations(
			AbsolutePanel descriptionAbsolutePanel, UserTO user) {
		Label nameLabel = new Label("Name: " + user.getName());
		nameLabel.setStyleName("descriptionPanelSettings");
		descriptionAbsolutePanel.add(nameLabel, 10, 10);
		nameLabel.setSize("261px", "20px");
		
		Label lblNewLabel = new Label("Email: " + user.getEmail());
		descriptionAbsolutePanel.add(lblNewLabel, 277, 10);
		lblNewLabel.setSize("255px", "18px");
	}

	private void loadStackLayoutPanel(AbsolutePanel absolutePanel, UserTO[] result) {
		StackLayoutPanel stackLayoutPanel = new StackLayoutPanel(Unit.EM);
		absolutePanel.add(stackLayoutPanel, 0, 0);
		stackLayoutPanel.setSize("121px", "400px");
		
		completeStackLayoutPanel(stackLayoutPanel, result);
		
	}

	private void completeStackLayoutPanel(StackLayoutPanel stackLayoutPanel, UserTO[] result) {
		 Widget contactsHeader = createHeaderWidget();
		 stackLayoutPanel.add(createContactsItem(result), contactsHeader, 4);
	}
	
	 
	private Widget createHeaderWidget() {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHeight("100%");
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		HTML headerText = new HTML("Contacts");
		headerText.setStyleName("cw-StackPanelHeader");
		hPanel.add(headerText);
		return new SimplePanel(hPanel);
	}
	
	private Widget createContactsItem(final UserTO[] result) {
		HorizontalPanel contactPopupContainer = new HorizontalPanel();
		contactPopupContainer.setSpacing(5);
		final HTML contactInfo = new HTML();
		contactPopupContainer.add(contactInfo);
		final PopupPanel contactPopup = new PopupPanel(true, false);
		contactPopup.setWidget(contactPopupContainer);

		VerticalPanel contactsPanel = new VerticalPanel();
		contactsPanel.setSpacing(4);
		for (int i = 0; i < result.length; i++) {
			final UserTO actualUser = result[i];
			final String contactName = result[i].getName();
			final String contactEmail = result[i].getEmail();
			final Anchor contactLink = new Anchor(contactName);
			contactsPanel.add(contactLink);

			contactLink.addMouseOverHandler(new MouseOverHandler() {

				@Override
				public void onMouseOver(MouseOverEvent event) {
					SafeHtmlBuilder sb = new SafeHtmlBuilder();
					sb.appendEscaped(contactName);
					sb.appendHtmlConstant("<br><i>");
					sb.appendEscaped(contactEmail);
					sb.appendHtmlConstant("</i>");
					contactInfo.setHTML(sb.toSafeHtml());

					int left = contactLink.getAbsoluteLeft() + 14;
					int top = contactLink.getAbsoluteTop() + 14;
					contactPopup.setPopupPosition(left, top);
					contactPopup.show();

				}
			});

			contactLink.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					boolean isVisitor = true;
					clientSessionManager.setUserFriend(actualUser);
					loadFriendsProfile(clientSessionManager, isVisitor);
				}
			});
		}
		return new SimplePanel(contactsPanel);
	}

	public boolean isVisitor() {
		return isVisitor;
	}

	public void setVisitor(boolean visitor) {
		this.isVisitor = visitor;
	}

	public ClientSessionManager getClientSessionManger() {
		return clientSessionManager;
	}

	public void setClientSessionManger(ClientSessionManager clientSessionManger) {
		this.clientSessionManager = clientSessionManger;
	}
	
	public AbsolutePanel getAbsolutePanel() {
		return absolutePanel;
	}

	public void setAbsolutePanel(AbsolutePanel absolutePanel) {
		this.absolutePanel = absolutePanel;
	}
}
