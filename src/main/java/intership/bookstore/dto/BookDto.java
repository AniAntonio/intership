package intership.bookstore.dto;

public class BookDto {

	protected Long isbn;

	private String title;

	private String description;

	private String publishingdate;

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

}
