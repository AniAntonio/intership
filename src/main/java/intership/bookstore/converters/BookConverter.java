package intership.bookstore.converters;

import java.util.ArrayList;
import java.util.List;

import internship.bookstore.entities.Author;
import internship.bookstore.entities.Book;
import internship.bookstore.entities.BookAuthor;
import intership.bookstore.dto.AuthorDto;
import intership.bookstore.dto.BookDto;

public class BookConverter {

	public static Book toBookEntity(BookDto bookDto) {
		Book book = new Book();
		book.setDescription(bookDto.getDescription());
		book.setPublishingdate(bookDto.getPublishingdate());
		book.setTitle(bookDto.getTitle());
		book.setIsbn(bookDto.getIsbn());
		book.setUser(bookDto.getUser());
		return book;
	}

	public static BookDto toBookDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setDescription(book.getDescription());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setTitle(book.getTitle());
		bookDto.setPublishingdate(book.getPublishingdate());
		List<BookAuthor> bookAuthors = book.getBookAuthors();
		List<AuthorDto> authors = new ArrayList<AuthorDto>();
		for (BookAuthor bookAuthor : bookAuthors) {
			authors.add(AuthorConverter.toAuthorDto(bookAuthor.getAuthor()));
		}
		bookDto.setBookauthors(authors);
		bookDto.setUser(book.getUser());
		return bookDto;
	}
}
