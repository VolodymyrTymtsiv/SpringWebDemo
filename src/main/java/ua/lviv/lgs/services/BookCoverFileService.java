package ua.lviv.lgs.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ua.lviv.lgs.entities.BookCoverFile;
import ua.lviv.lgs.reporitories.BookCoverFileRepository;

@Service
public class BookCoverFileService {

  private final BookCoverFileRepository bookCoverFileRepository;

  @Autowired
  public BookCoverFileService(BookCoverFileRepository bookCoverFileRepository) {
    this.bookCoverFileRepository = bookCoverFileRepository;
  }

  public BookCoverFile save(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      BookCoverFile bookCoverFile = new BookCoverFile(fileName, file.getContentType(), file.getBytes());
      return bookCoverFileRepository.save(bookCoverFile);
    } catch (IOException ex) {
      throw new RuntimeException("Could not store file " + fileName, ex);
    }
  }

  public BookCoverFile findById(String fileId) {
    return bookCoverFileRepository.findById(fileId)
      .orElseThrow(() -> new RuntimeException("File not found with id " + fileId));
  }
}
