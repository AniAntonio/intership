package internship.bookstore.converters;

import java.util.ArrayList;
import java.util.List;

import internship.bookstore.dto.BookDto;
import internship.bookstore.entities.Author;
import internship.bookstore.entities.Book;

public class BookConverter {

	private BookConverter() {
	}

	public static Book toBookEntity(BookDto bookDto) {
		Book book = new Book();
		book.setDescription(bookDto.getDescription());
		book.setPublishingdate(bookDto.getPublishingdate());
		book.setTitle(bookDto.getTitle());
		book.setIsbn(bookDto.getIsbn());
		book.setUser(bookDto.getUser());
		book.setRating(bookDto.getRating());
		return book;
	}

	public static BookDto toBookDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setDescription(book.getDescription());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setTitle(book.getTitle());
		bookDto.setPublishingdate(book.getPublishingdate());
		bookDto.setRating(book.getRating());
		List<Long> idAuthors = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			idAuthors.add(author.getId());
		}
		bookDto.setRating(book.getRating());
		bookDto.setIdAuthors(idAuthors);
		bookDto.setBookauthors(AuthorConverter.toAuthorDtoList(book.getAuthors()));
		bookDto.setUser(book.getUser());
		return bookDto;
	}
}
