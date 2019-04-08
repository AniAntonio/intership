package internship.bookstore.repository;

import internship.bookstore.entities.BookAuthor;

public interface BookAuthorRepository {
	
	boolean checkIfAuthorHasBooks(Long idAuthor);
	
	boolean addBookAuthor(BookAuthor bookAuthor);
	
	boolean deleteBookAuthors(BookAuthor bookAuthor);
}
