package com.es.zumeh.server.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.es.zumeh.client.model.to.NodeTO;
import com.es.zumeh.server.model.persistence.Node;

public class NodeDAO implements ObjectDAO<Node> {
	
	//TODO FAZER A VERIFICACAO ANTES DE INSERIR

	@Override
	public void save(Node node) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		session.saveOrUpdate(node);
		
		session.flush();
		HibernateUtil.commitTransaction();
	}

	@Override
	public void delete(Node node) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAllObjects() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String hql = "delete from com.es.zumeh.server.model.persistence.Node ";
		Query query = session.createQuery(hql);
		
		int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount); //TODO optional
        
        HibernateUtil.commitTransaction();
	}

	@Override
	public void update(Node object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node[] getAllObjects() {
		List<Node> realNodes = getAllNodes();
		Node[] nodes = new Node[realNodes.size()];
		
		for (int i=0; i < realNodes.size(); i++) {
			nodes[i] = realNodes.get(i);
		}
		
		return nodes;
	}
	
	@SuppressWarnings("unchecked")
	private List<Node> getAllNodes() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String query = "select n from com.es.zumeh.server.model.persistence.Node n";
		
		List<Node> nodes = new ArrayList<Node>(session.createQuery(query).list());
		
		return nodes;
	}
	
	private Node getNode(Node node) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		Node nodeQueryByChildLeft = getNodeQueryByChild(node, session, "leftChildNodeId");
		
		Node nodeQueryByChildRight = getNodeQueryByChild(node, session, "rightChildNodeId");
		
		
		if (nodeQueryByChildLeft != null) {
			return nodeQueryByChildLeft;
		} else if (nodeQueryByChildRight != null) {
			return nodeQueryByChildRight;
		}

		HibernateUtil.commitTransaction();
		return null;
	}

	private Node getNodeQueryByChild(Node node, Session session, String childType) {
		Criteria criteriaLeft = session.createCriteria(Node.class);
		criteriaLeft.add(Restrictions.eq(childType, node.getLeftChildNodeId()));

		Node nodeResult = (Node) criteriaLeft.uniqueResult();
		return nodeResult;
	}
	
	private NodeTO convertToTransferObjectNode(Node node) {
		NodeTO nodeTO = new NodeTO();
		
		return nodeTO;
	}
	
	public boolean verifyNode(Node node) {
		return (getNode(node) != null);
	}
	
	private Node convertedor(NodeTO nodeTO) {
		Node no = new Node();
	    no.setFullNodeText(nodeTO.getFullNodeText());
	   // no.setNodeId(nodeTO.getNodeId());
		//no.setLeftChildNodeId(nodeTO.getLeftChildNodeId());
		//no.setNodeStatus(nodeTO.getNodeStatus());
		//no.setRightChildNodeId(nodeTO.getRightChildNodeId());
		//no.setShortDescription(nodeTO.getShortDescription());
		
		return no;
	}
	
	public void saveNodeTO(NodeTO node) {
		
		save(convertedor(node));
	}
	
	public void saveNode(Node node) {
		save(node);
	}

}
