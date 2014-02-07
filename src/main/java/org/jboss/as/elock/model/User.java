package org.jboss.as.elock.model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table
@NamedQueries({
	@NamedQuery(name = "findUserById", query = "FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "findAll", query = "FROM User"),
})
public class User implements Serializable {

	private static final long serialVersionUID = -5666950700144552087L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "user_name")
	private String name;
	
	@NotNull
	@Column(name = "user_birthdate")
	private Date birthdate;
	
	@NotNull
	@OneToMany(fetch = FetchType.EAGER)
	private List<Card> cards = new ArrayList<Card>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date date) {
		this.birthdate = date;
	}
}
