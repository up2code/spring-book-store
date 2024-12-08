package dev.up2up.springbookstore.repository;

import dev.up2up.springbookstore.entity.Author;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
