package pl.edu.pja.prz.receivables.service;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  File convertMultipartToFile(MultipartFile file) throws IOException;

  void cleanUpFile(File file) throws IOException;
}
