package intership.bookstore.converters;

import internship.bookstore.entities.Book;
import intership.bookstore.dto.BookDto;

public class BookConverter {
	
	public static Book toBookEntity(BookDto bookDto) {
		Book book = new Book();
		book.setDescription(bookDto.getDescription());
		book.setPublishingdate(bookDto.getPublishingdate());
		book.setTitle(bookDto.getTitle());
		book.setIsbn(bookDto.getIsbn());
		return book;
	}
	
	public static BookDto toBookDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setDescription(book.getDescription());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setTitle(book.getTitle());
		bookDto.setPublishingdate(book.getPublishingdate());
		return bookDto;
	}
}
