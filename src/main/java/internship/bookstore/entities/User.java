package internship.bookstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1268317671009653176L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "username", length = 50)
	@Size(min = 3, max = 50, message = "Username must be from 3 to 50 letters!")
	private String username;

	@Column(name = "password", length = 100)
	@Size(min = 3, max = 50, message = "Password must be from 3 to 50 letters!")
	private String password;

	@Column(name = "firstname", length = 50)
	@Size(min = 3, max = 50, message = "Firstname must be from 3 to 50 letters!")
	private String firstname;

	@Column(name = "lastname", length = 50)
	@Size(min = 3, max = 50, message = "Lastname must be from 3 to 50 letters!")
	private String lastname;

	@Column(name = "email", length = 50)
	@Email(message = "Enter a valid email!")
	@NotNull
	private String email;

	@ManyToOne
	@JoinColumn(name = "idrole")
	private Role role;

	@Column(name = "valid")
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
