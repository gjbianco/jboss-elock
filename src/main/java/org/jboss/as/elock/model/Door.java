package org.jboss.as.elock.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Door
 *
 */
@Entity
@XmlRootElement
@Table
public class Door implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@Column(name = "req_perm")
	private int reqPerm = 0;

	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}   
	public int getReqPerm() {
		return this.reqPerm;
	}
	public void setReqPerm(int reqPerm) {
		this.reqPerm = reqPerm;
	}
   
}
