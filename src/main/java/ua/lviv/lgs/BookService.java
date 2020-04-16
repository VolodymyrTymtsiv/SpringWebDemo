package ua.lviv.lgs;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public void save(BookRequest bookRequest) {
    Book book = new Book(bookRequest.getName(), bookRequest.getAuthor(), new Date());
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
    bookRepository.deleteById(id);
  }
}
