package dev.up2up.springbookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.up2up.springbookstore.entity.Book;
import dev.up2up.springbookstore.model.BookDto;

@Mapper(componentModel = "spring")
public interface BookMapper {
   
    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "author.id", target = "authorId")
    BookDto toDto(Book book);

    @Mapping(target = "author", ignore = true)
    Book toEntity(BookDto bookDto);
}
