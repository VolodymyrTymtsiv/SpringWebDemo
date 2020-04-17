package ua.lviv.lgs.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dtos.BookRequest;
import ua.lviv.lgs.entities.Book;
import ua.lviv.lgs.reporitories.BookCoverFileRepository;
import ua.lviv.lgs.reporitories.BookRepository;

@Service
public class BookService {

  private BookRepository bookRepository;
  private final BookCoverFileRepository bookCoverFileRepository;

  @Autowired
  public BookService(BookRepository bookRepository, BookCoverFileRepository bookCoverFileRepository) {
    this.bookRepository = bookRepository;
    this.bookCoverFileRepository = bookCoverFileRepository;
  }

  public void save(BookRequest bookRequest) {
    Book book = new Book(bookRequest.getName(), bookRequest.getAuthor(), new Date());
    String coverId = bookRequest.getCoverId();
    if(!coverId.isEmpty()) {
      book.setCoverId(coverId);
    }
    bookRepository.save(book);
  }

  public void save(Book book) {
    bookRepository.save(book);
  }

  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Optional<Book> findOne(int id) {
    return bookRepository.findById(id);
  }

  public void delete(int id) {
    bookRepository.getCoverIdByBookId(id).ifPresent(bookCoverFileRepository::deleteById);
    bookRepository.deleteById(id);
  }
}
