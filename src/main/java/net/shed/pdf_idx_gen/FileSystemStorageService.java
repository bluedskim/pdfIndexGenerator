package net.shed.pdf_idx_gen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemStorageService {
	Path store(MultipartFile orgPdf) throws IOException {
		Path destFile = Paths.get(
			System.getProperty("java.io.tmpdir")
			+ File.separator
			+ System.nanoTime());
		log.info("destFile={}", destFile);
		try (InputStream inputStream = orgPdf.getInputStream()) {
			Files.copy(inputStream, destFile);
		}
		return destFile;
	}

	public Resource loadAsResource(String filename) throws MalformedURLException {
		Path file = Paths.get(
			System.getProperty("java.io.tmpdir")
			+ File.separator
			+ filename
		);
		return new UrlResource(file.toUri());
	}
}
