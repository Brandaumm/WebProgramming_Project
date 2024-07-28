package org.example.webprogramming_project.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagemService {

    private static final String IMAGEM_DIR = "/caminho/para/imagens";

    public String saveImagem(MultipartFile imagem) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
        Path filePath = Paths.get(IMAGEM_DIR, fileName);
        Files.write(filePath, imagem.getBytes());
        return fileName; // Retornar o nome do arquivo ou URI
    }
}

