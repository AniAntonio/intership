package internship.bookstore.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book", uniqueConstraints = @UniqueConstraint(columnNames = { "isbn" }))
public class Book implements Serializable {
	private static final long serialVersionUID = 1268317671009653177L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long isbn;

	@Column(name = "title", length = 100)
	@Size(max = 100)
	private String title;

	@Column(name = "description", length = 1000)
	@Size(max = 1000)
	private String description;

	@Column(name = "publishingdate", length = 200)
	@Size(max = 200)
	private String publishingdate;

	@Column(name = "valid")
	private boolean valid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iduser")
	private User user;

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishingdate() {
		return publishingdate;
	}

	public void setPublishingdate(String publishingdate) {
		this.publishingdate = publishingdate;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
