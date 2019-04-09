package intership.bookstore.converters;

import internship.bookstore.entities.Author;
import intership.bookstore.dto.AuthorDto;

public class AuthorConverter {

	public static Author toAuthorEntity(AuthorDto authorDto) {
		Author author = new Author();
		author.setDateofbirth(authorDto.getDateofbirth());
		author.setFirstname(authorDto.getFirstname());
		author.setId(authorDto.getId());
		author.setLastname(authorDto.getLastname());
		author.setUser(authorDto.getUser());
		return author;
	}

	public static AuthorDto toAuthorDto(Author author) {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setDateofbirth(author.getDateofbirth());
		authorDto.setFirstname(author.getFirstname());
		authorDto.setId(author.getId());
		authorDto.setLastname(author.getLastname());
		authorDto.setUser(author.getUser());
		return authorDto;
	}
}
