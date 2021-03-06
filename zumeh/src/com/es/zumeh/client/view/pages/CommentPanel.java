package com.es.zumeh.client.view.pages;

import java.util.ArrayList;
import java.util.Iterator;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.model.to.CommentTO;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.client.view.pages.work.WorkPage;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CommentPanel extends ScrollPanel {
	final private String BACKGROUND_COLOR = "#BBBBBB";
	final private String COMMENT_BACKGROUND_COLOR = "#00FF00";
	final private int WIDTH = 390;
	final private int HEIGHT = getScreenHeight() - 160;
	
	private ArrayList<CommentTO> comments = new ArrayList<CommentTO>();
	private VerticalPanel commentsPanel = new VerticalPanel();
	private WorkPage workPage;
	private ClientSessionManager clientSessionManger;

	public CommentPanel(WorkPage workPage, ClientSessionManager clientSessionManger) {
		setSize(WIDTH+"px", HEIGHT+"px");
		//getElement().getStyle().setBackgroundColor(BACKGROUND_COLOR);
		this.workPage = workPage;
		this.clientSessionManger = clientSessionManger;
		
		add(commentsPanel);
		
		/*addComment("String comment");
		addComment("String comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2comment2 comment2 comment2 comment2 comment2 comment2 ");
		addComment("String comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2comment2 comment2 comment2 comment2 comment2 comment2 ");
		addComment("String comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2comment2 comment2 comment2 comment2 comment2 comment2 ");
		addComment("String comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2comment2 comment2 comment2 comment2 comment2 comment2 ");
		addComment("String comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2comment2 comment2 comment2 comment2 comment2 comment2 ");
		addComment("String comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2 comment2comment2 comment2 comment2 comment2 comment2 comment2 ");*/
		
		refreshCommentPanel();
		//setScrollPositio(getMaximumVerticalScrollPosition());
		scrollToBottom();
	}
	
	private void addComment(String comment) {
		CommentTO newComment = new CommentTO();
		newComment.setCommentText(comment);
		newComment.setCommentId((long) comments.size());
		newComment.setOwner(clientSessionManger.getUserOwner().getName()); // TODO it got to be actual user name
		newComment.setEmail(clientSessionManger.getUserOwner().getEmail());
		newComment.setRevisionId((Long) this.workPage.getRevisionTO().getRevisionId()); //TIRAR ISSO AQUI
		comments.add(newComment);
		refreshCommentPanel();
		
		workPage.getRevisionTO().setComments(comments);
		
		workPage.zumehService.addRevision(workPage.getRevisionTO(), new AsyncCallback<Long>() {
			
			@Override
			public void onSuccess(Long result) {
				workPage.getRevisionTO().setRevisionId(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	private Image getCommentOwnerPicture(String email) {
		Image image = new Image("http://127.0.0.1:8888/zumeh/upload?imagePath=" + email);// TODO it got to be actual user picture
		image.setHeight(100+"px");
		image.setWidth(80+"px");
		return image;
	}
	
	private HorizontalPanel getCommentPanel() {
		HorizontalPanel commentPanel = new HorizontalPanel();
		commentPanel.setHeight(100+"px");
		commentPanel.setWidth((WIDTH-15)+"px");
		//commentPanel.getElement().getStyle().setBackgroundColor(COMMENT_BACKGROUND_COLOR);
		commentPanel.setSpacing(10);
		return commentPanel;
	}
	
	private AbsolutePanel getTextPanel() {
		AbsolutePanel textPanel = new AbsolutePanel();
		textPanel.setHeight("80px");
		textPanel.setWidth("250px");
		return textPanel;
	}
	
	public ArrayList<CommentTO> getCommentTO() {
		return this.comments;
	}
	
	public void setCommentTO(ArrayList<CommentTO> comments) {
		this.comments = comments;
	}
	
	public void refreshCommentPanel() {
		commentsPanel.clear();
		Iterator<CommentTO> itComments = comments.iterator();
		
		while(itComments.hasNext()) {
			HorizontalPanel commentPanel = getCommentPanel();
			VerticalPanel commentPanelText = new VerticalPanel();
			CommentTO tmpComment = itComments.next();
			Image commentOwnerPicture = getCommentOwnerPicture(tmpComment.getEmail());
			
			Anchor profileAnchor = new Anchor(tmpComment.getOwner());
			profileAnchor.addClickHandler(createProfileHandler(tmpComment.getEmail())); //TODO HERE
			
			Label label2 = new Label(tmpComment.getCommentText());
			AbsolutePanel textPanel = getTextPanel();
			
			commentPanel.add(commentOwnerPicture);
			textPanel.add(label2);
			commentPanelText.add(profileAnchor);
			commentPanelText.add(textPanel);
			commentsPanel.add(commentPanel);
			commentPanel.add(commentPanelText);
		}
		
		Button addCommentBtn = new Button();
		addCommentBtn.setText("Add Comment");
		addCommentBtn.setWidth((WIDTH-15)+"px");
		addCommentBtn.addClickHandler(addCommenHandler);
		commentsPanel.add(addCommentBtn);
		scrollToBottom();
	}
	
	private ClickHandler createProfileHandler(final String email) {
		return new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				workPage.getZumehServiceAsync().getUserByEmail(
						email, createGetUserByEmailCallback());
			}

			private AsyncCallback<UserTO> createGetUserByEmailCallback() {
				return new AsyncCallback<UserTO>() {
					
					@Override
					public void onSuccess(UserTO result) {
						if (clientSessionManger.getUserOwner().
								getEmail().equals(result.getEmail())) {
							workPage.loadProfilePage(clientSessionManger);
							loadUsers();
						} else {
							clientSessionManger.setUserFriend(result);
							loadUsers();
							workPage.loadFriendsProfile(clientSessionManger, true);
						}
					}
					private void loadUsers() {
						workPage.getZumehServiceAsync().getUserList(new AsyncCallback<UserTO[]>() {
							
							@Override
							public void onSuccess(UserTO[] result) {
								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								
							}
						});
					}
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}
				};
			}
		};
	}

	ClickHandler addCommenHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			final DialogBox dialogBox = new DialogBox();
			dialogBox.setText("Adding Comment...");
			dialogBox.setAnimationEnabled(true);
			
			final Button okButton = new Button("Ok");
			okButton.getElement().setId("okButtonEditFullContent");
			
			final Button cancelButton = new Button("Cancel");
			cancelButton.getElement().setId("cancelButtonEditFullContent");
			
			final VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.addStyleName("dialogVPanel");
			dialogVPanel.add(new HTML("<b>Comment:</b>"));
			
			final HorizontalPanel d = new HorizontalPanel();
			
			final TextArea textBox = new TextArea();
			textBox.setPixelSize(300, 80);
			textBox.setEnabled(true);
			textBox.setFocus(true);
			
			dialogVPanel.add(textBox);
			dialogBox.setWidget(dialogVPanel);
			
			d.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
			d.add(okButton);
			d.add(cancelButton);
			dialogVPanel.add(d);
			
			dialogBox.center();
			dialogBox.setVisible(true);
			
			okButton.setFocus(true);
			
			cancelButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
				}
			});
			
			okButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
					addComment(textBox.getText());
				}
			});
			// ================= End of Dialog Box ===================
		}
	};
	
	/*
	 *  Natives
	 */
	public static native int getScreenWidth() /*-{ 
		return $wnd.screen.width;
	}-*/;

	public static native int getScreenHeight() /*-{ 
		return $wnd.screen.height;
	}-*/;
}
