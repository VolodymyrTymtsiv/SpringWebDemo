package ua.lviv.lgs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

  @GetMapping("/")
  public String allBooks() {
    return "index";
  }

}
