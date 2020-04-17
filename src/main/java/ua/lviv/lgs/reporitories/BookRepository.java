package ua.lviv.lgs.reporitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
  @Query("select b.coverId from Book b where b.id = :bookId")
  Optional<String> getCoverIdByBookId(@Param("bookId") int bookId);
}
