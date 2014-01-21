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
 * Represents the concept of a door. Doors have their own individual required permission level that must be met in order for the user to be given access.
 *
 * @author Guy J. Bianco IV
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
	@Column(name = "required_perm")
	private int requiredPerm = 0;

	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}   
	public int getRequiredPerm() {
		return this.requiredPerm;
	}
	public void setRequiredPerm(int requiredPerm) {
		this.requiredPerm = requiredPerm;
	}
   
}
