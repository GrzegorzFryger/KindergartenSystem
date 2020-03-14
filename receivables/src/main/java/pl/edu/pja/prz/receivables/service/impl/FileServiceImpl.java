package pl.edu.pja.prz.receivables.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.service.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public File convertMultipartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }

    @Override
    public void cleanUpFile(File file) throws IOException {
        Files.delete(file.toPath());
    }
}
