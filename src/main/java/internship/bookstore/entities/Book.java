package internship.bookstore.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable {
	private static final long serialVersionUID = 1268317671009653177L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long isbn;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "publishingdate")
	private String publishingdate;

	@Column(name = "valid")
	private boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iduser")
	private User user;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "book_author", joinColumns = { @JoinColumn(name = "isbnbook") }, inverseJoinColumns = {
			@JoinColumn(name = "idauthor") })
	private List<Author> authors;

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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
