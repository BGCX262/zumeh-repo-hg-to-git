package com.es.zumeh.server.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENTS")
@SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq") 
public class Comment implements Serializable {

	private static final long serialVersionUID = 4890545091357671071L;
	
	private Long commentId;
	private String commentText;
	private Long revisionId;
	private String owner;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "comment_seq")
	@Column(name = "comment_id")
	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	@Column(nullable = false)
	public Long getRevisionId() {
		return this.revisionId;
	}
	
	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
	}

	@Column(nullable = false)
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Lob
	public String getCommentText() {
		return this.commentText;
	}
	
	public void setCommentText(String comment) {
		this.commentText = comment;
	}
}
