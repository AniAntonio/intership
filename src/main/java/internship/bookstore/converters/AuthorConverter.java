package internship.bookstore.converters;

import java.util.ArrayList;
import java.util.List;

import internship.bookstore.dto.AuthorDto;
import internship.bookstore.entities.Author;

public class AuthorConverter {

	private AuthorConverter() {
	}

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

	public static List<AuthorDto> toAuthorDtoList(List<Author> authors) {
		List<AuthorDto> authorsDto = new ArrayList<>();
		for (Author author : authors) {
			authorsDto.add(toAuthorDto(author));
		}
		return authorsDto;
	}
}
