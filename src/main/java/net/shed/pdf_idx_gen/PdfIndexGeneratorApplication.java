package net.shed.pdf_idx_gen;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class PdfIndexGeneratorApplication {
	@Autowired
	FileSystemStorageService fileSystemStorageService;

	@Autowired
	PdfIndexGenegator pdfIndexGenegator;

	@PostMapping(value="/genIdx")
	ResponseEntity<Resource> index(@RequestParam("orgPdf") MultipartFile orgPdf) throws IOException {
		log.info("orgPdf={}", orgPdf.getOriginalFilename());
		Path orgPdfPath = fileSystemStorageService.store(orgPdf);
		Path resultPdfPath = pdfIndexGenegator.sortPages(orgPdf.getOriginalFilename(), orgPdfPath);
		Resource resultRsc =  new UrlResource(resultPdfPath.toUri());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + resultRsc.getFilename() + "\"").body(resultRsc);
	}

	public static void main(String[] args) {
		SpringApplication.run(PdfIndexGeneratorApplication.class, args);
	}
}
