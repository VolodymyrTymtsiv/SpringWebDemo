package ua.lviv.lgs.reporitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.entities.BookCoverFile;

@Repository
public interface BookCoverFileRepository extends CrudRepository<BookCoverFile, String> {

}
