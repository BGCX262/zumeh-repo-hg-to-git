package com.es.zumeh.server.model.persistence;

import java.io.Serializable;
import java.util.LinkedList;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.es.zumeh.client.model.to.WorkTO;


@Entity
@Table(name = "REVISIONS")
@SequenceGenerator(name = "revision_seq", sequenceName = "revision_seq") 
public class Revision implements Serializable {

	private static final long serialVersionUID = 8231074699510318810L;
	
	private LinkedList<WorkTO> workTOList = new LinkedList<WorkTO>();
	private Long revisionId;
	private String fullDescriptionRevisionText;
	private String shortDescriptionRevisionText;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "revision_seq")
	@Column(name = "revision_id")
	public Long getRevisionId() {
		return this.revisionId;
	}
	
	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
	}
	
	@CollectionTable
	public LinkedList<WorkTO> getTOWorks() {
		return this.workTOList;
	}
	
	public void setTOWorks(LinkedList<WorkTO> workTOList) {
		this.workTOList = workTOList;
	}

	@Lob
	public String getFullDescriptionText() {
		return this.fullDescriptionRevisionText;
	}
	
	public void setFullDescriptionText(String fullDescriptionText) {
		this.fullDescriptionRevisionText = fullDescriptionText;
	}
	
	@Column(nullable=false, length = 200)
	public String getShortDescriptionText() {
		return this.shortDescriptionRevisionText;
	}
	
	public void setShortDescriptionText(String shortDescriptionText) {
		this.shortDescriptionRevisionText = shortDescriptionText;
	}

}
