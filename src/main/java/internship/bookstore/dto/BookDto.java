package internship.bookstore.dto;

import java.util.List;

import internship.bookstore.entities.Author;
import internship.bookstore.entities.User;

public class BookDto {

	protected Long isbn;

	private String title;

	private String description;

	private String publishingdate;

	private List<AuthorDto> bookauthors;

	private List<Long> idAuthors;

	private User user;

	private List<Author> authors;

	private String searchParam;

	private Double rating;

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

	public List<AuthorDto> getBookauthors() {
		return bookauthors;
	}

	public void setBookauthors(List<AuthorDto> bookauthors) {
		this.bookauthors = bookauthors;
	}

	public List<Long> getIdAuthors() {
		return idAuthors;
	}

	public void setIdAuthors(List<Long> idAuthors) {
		this.idAuthors = idAuthors;
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

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

}
