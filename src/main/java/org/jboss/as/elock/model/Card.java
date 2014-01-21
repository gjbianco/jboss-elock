package org.jboss.as.elock.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Card implements Serializable {

	private static final long serialVersionUID = 7778154337737619266L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Date expiration;
	
	@NotNull
	@Column(name = "perm_level")
	private int permLevel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public int getBuildings() {
		return permLevel;
	}

	public void setBuildings(int permLevel) {
		this.permLevel = permLevel;
	}
}
