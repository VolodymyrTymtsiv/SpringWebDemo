package ua.lviv.lgs;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping("/")
  public String allBooks(HttpServletRequest req) {
    req.setAttribute("books", bookService.findAllBooks());
    req.setAttribute("mode", "BOOK_VIEW");
    return "book";
  }

  @GetMapping("/new")
  public String newBook(HttpServletRequest req) {
    req.setAttribute("mode", "BOOK_CREATE");
    return "book";
  }

  @PostMapping("/save")
  public String save(@ModelAttribute Book book, HttpServletRequest req) {
    bookService.save(book);
    req.setAttribute("books", bookService.findAllBooks());
    req.setAttribute("mode", "BOOK_VIEW");
    return "book";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute BookRequest bookRequest, HttpServletRequest req) {
    bookService.save(bookRequest);
    req.setAttribute("books", bookService.findAllBooks());
    req.setAttribute("mode", "BOOK_VIEW");
    return "book";
  }

  @GetMapping("/update")
  public String update(@RequestParam int id, HttpServletRequest req) {
    Optional<Book> bookMaybe = bookService.findOne(id);

    if(bookMaybe.isPresent()) {
      req.setAttribute("book", bookMaybe.get());
      req.setAttribute("mode", "BOOK_EDIT");
      return "book";
    }
    return "noBookExist";
  }

  @GetMapping("/delete")
  public String deleteBook(@RequestParam(name = "id") int bookId, HttpServletRequest req) {
    bookService.delete(bookId);
    req.setAttribute("books", bookService.findAllBooks());
    req.setAttribute("mode", "BOOK_VIEW");
    // Todo Fix
    return "/books/";
  }
}
