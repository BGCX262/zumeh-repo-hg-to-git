package com.es.zumeh.client.model.to;

import java.io.Serializable;

public class CommentTO implements Serializable {
	
	private static final long serialVersionUID = -5497792772421045048L;
	
	private String comment;
	private int commentId;
	private String owner;
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public void setOwer(String owner) {
		this.owner = owner;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public int getCommentId() {
		return this.commentId;
	}
	
	public String getOwer() {
		return this.owner;
	}
	
	@Override
	public String toString() {
		return "Comment: " + this.comment + " CommentId: " + this.commentId;
	}
}