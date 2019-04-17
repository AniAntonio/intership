package internship.bookstore.dto;

public class BookRequestDto {

	private String searchedTitle;

	private int pageNumber;
	
	private int idAuthor;

	public String getSearchedTitle() {
		return searchedTitle;
	}

	public void setSearchedTitle(String searchedTitle) {
		this.searchedTitle = searchedTitle;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}

}
