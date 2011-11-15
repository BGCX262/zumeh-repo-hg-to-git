package com.es.zumeh.client.view.pages;

import java.util.ArrayList;
import java.util.Iterator;

import com.es.zumeh.client.model.to.CommentTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CommentPanel extends ScrollPanel {
	final private String BACKGROUND_COLOR = "#BBBBBB";
	final private String COMMENT_BACKGROUND_COLOR = "#00FF00";
	final private int WIDTH = 380;
	final private int HEIGHT = getScreenHeight() - 160;
	
	private ArrayList<CommentTO> comments = new ArrayList<CommentTO>();
	private VerticalPanel commentsPanel = new VerticalPanel();

	public CommentPanel() {
		setSize(WIDTH+"px", HEIGHT+"px");
		getElement().getStyle().setBackgroundColor(BACKGROUND_COLOR);
		add(commentsPanel);
		// ******* Test ********
		addComment("Comment 1");
		// 160 caracteres no maximo.
		addComment("Comment 2");
		addComment("Comment 3");
		addComment("Comment 4");
		addComment("Comment 5");
		addComment("Comment 6");
		addComment("Comment 7");
		/*String t = "==============================+ "+
		"==============================+ "+
		"==============================+ "+
		"==============================+ "+
		"==============================+ ";
		System.out.println("Tamanho: " + t.length());*/
		addComment("==============================+ "+
				"==============================+ "+
				"==============================+ "+
				"==============================+ "+
				"==============================+ ");
		// ******** End ********
		
		//setScrollPositio(getMaximumVerticalScrollPosition());
		//scrollToTop();
	}
	
	private void addComment(String comment) {
		CommentTO newComment = new CommentTO();
		newComment.setComment(comment);
		newComment.setCommentId(comments.size());
		newComment.setOwer("Heitor");
		comments.add(newComment);
		refreshCommentPanel();
	}
	
	
	private Image getCommentOwnerPicture() {
		Image image = new Image("images/sheldon.jpg");
		image.setHeight(100+"px");
		image.setWidth(80+"px");
		return image;
	}
	
	private HorizontalPanel getCommentPanel() {
		HorizontalPanel commentPanel = new HorizontalPanel();
		commentPanel.setHeight(100+"px");
		commentPanel.setWidth((WIDTH-15)+"px");
		commentPanel.getElement().getStyle().setBackgroundColor(COMMENT_BACKGROUND_COLOR);
		commentPanel.setSpacing(10);
		return commentPanel;
	}
	
	public void refreshCommentPanel() {
		commentsPanel.clear();
		Iterator<CommentTO> itComments = comments.iterator();
		
		while(itComments.hasNext()) {
			HorizontalPanel commentPanel = getCommentPanel();
			VerticalPanel commentPanelText = new VerticalPanel();
			CommentTO tmpComment = itComments.next();
			
			Image commentOwnerPicture = getCommentOwnerPicture();
			commentPanel.add(commentOwnerPicture);
			
			
			AbsolutePanel textPanel = new AbsolutePanel();
			textPanel.getElement().getStyle().setBackgroundColor("#FF0000");
			textPanel.setHeight("80px");
			textPanel.setWidth("250px");
			
			//TextArea label2 = new TextArea();
			//label2.setText("Teste");
			
			Label label2 = new Label(tmpComment.getComment());
			textPanel.add(label2);
			Hyperlink hprlnkWork = new Hyperlink(tmpComment.getOwer(), false, "profile");
			hprlnkWork.addClickHandler(addCommenHandler);
			
			commentPanelText.add(hprlnkWork);
			//commentPanelText.add(label2);
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
