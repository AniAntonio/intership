package internship.bookstore.converters;

import java.util.ArrayList;
import java.util.List;

import internship.bookstore.dto.AuthorDto;
import internship.bookstore.dto.BookDto;
import internship.bookstore.entities.Author;
import internship.bookstore.entities.Book;

public class BookConverter {

	public static Book toBookEntity(BookDto bookDto) {
		Book book = new Book();
		book.setDescription(bookDto.getDescription());
		book.setPublishingdate(bookDto.getPublishingdate());
		book.setTitle(bookDto.getTitle());
		book.setIsbn(bookDto.getIsbn());
		book.setUser(bookDto.getUser());
		book.setAuthors(bookDto.getAuthors());
		return book;
	}

	public static BookDto toBookDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setDescription(book.getDescription());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setTitle(book.getTitle());
		bookDto.setPublishingdate(book.getPublishingdate());
		List<AuthorDto> authorsDto = new ArrayList<AuthorDto>();
		for (Author author : book.getAuthors()) {
			authorsDto.add(AuthorConverter.toAuthorDto(author));
		}
		bookDto.setBookauthors(authorsDto);
		bookDto.setUser(book.getUser());
		return bookDto;
	}
}
