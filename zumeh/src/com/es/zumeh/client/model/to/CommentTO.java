package com.es.zumeh.client.model.to;

import java.io.Serializable;

public class CommentTO implements Serializable {
	
	private static final long serialVersionUID = -5497792772421045048L;
	
	private String commentText;
	private Long commentId;
	private Long revisionId;
	private String owner;
	
	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
	}
	
	public Long getRevisionId() {
		return this.revisionId;
	}
	
	public void setCommentText(String comment) {
		this.commentText = comment;
	}
	
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getCommentText() {
		return this.commentText;
	}
	
	public Long getCommentId() {
		return this.commentId;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	@Override
	public String toString() {
		return "Comment: " + this.commentText + " CommentId: " + this.commentId;
	}
}