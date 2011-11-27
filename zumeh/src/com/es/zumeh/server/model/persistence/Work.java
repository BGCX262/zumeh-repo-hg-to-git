package com.es.zumeh.server.model.persistence;

import java.io.Serializable;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.es.zumeh.client.model.to.NodeTO;

@Entity
@Table(name = "WORKS")
@SequenceGenerator(name = "work_seq", sequenceName = "work_seq")
public class Work implements Serializable {

	private static final long serialVersionUID = -8757009501517179120L;

	private Long workId;
	private LinkedList<NodeTO> nodeTOList = new LinkedList<NodeTO>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "work_seq")
	@Column(name = "work_id")
	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	@Lob
	public LinkedList<NodeTO> getNodes() {
		return nodeTOList;
	}

	public void setNodes(LinkedList<NodeTO> nodeTOList) {
		this.nodeTOList = nodeTOList;
	}

}
