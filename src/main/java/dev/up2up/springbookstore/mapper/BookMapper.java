package dev.up2up.springbookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import dev.up2up.springbookstore.entity.Book;
import dev.up2up.springbookstore.model.BookDto;
import dev.up2up.springbookstore.model.CreateBookRequest;
import dev.up2up.springbookstore.model.UpdateBookRequest;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "author.id", target = "authorId")
    BookDto toDto(Book book);

    @Mapping(target = "author", ignore = true)
    Book toEntity(BookDto bookDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Book fromCreateRequestToEntity(CreateBookRequest createBookRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromUpdateRequest(UpdateBookRequest updateBookRequest, @MappingTarget Book book);
}
