package org.jboss.as.elock.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table
@NamedQueries({
		@NamedQuery(name = "findCardById", query = "FROM Card c where c.id = :id"),
		@NamedQuery(name = "findAllCards", query = "FROM Card")
})
public class Card implements Serializable {

	private static final long serialVersionUID = 7778154337737619266L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name = "card_expiration")
	private Date expiration;
	
	@NotNull
	@Column(name = "card_perm_level")
	private int permLevel = 0;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
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

	public int getPermissionLevel() {
		return permLevel;
	}

	public void setPermissionLevel(int permLevel) {
		this.permLevel = permLevel;
	}
}
