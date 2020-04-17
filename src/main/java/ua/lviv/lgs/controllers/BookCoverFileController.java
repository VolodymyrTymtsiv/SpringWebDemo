package ua.lviv.lgs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ua.lviv.lgs.entities.BookCoverFile;
import ua.lviv.lgs.services.BookCoverFileService;

@RestController
@RequestMapping("/book-cover-files")
public class BookCoverFileController {

  @Autowired
  private BookCoverFileService bookCoverFileService;

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("coverFile") MultipartFile file) {
    BookCoverFile bookCoverFile = bookCoverFileService.save(file);
    return bookCoverFile.getId();
  }

  @GetMapping("/download/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    BookCoverFile bookCoverFile = bookCoverFileService.findById(fileId);
    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(bookCoverFile.getFileType()))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + bookCoverFile.getFileName())
      .body(new ByteArrayResource(bookCoverFile.getData()));
  }

}
