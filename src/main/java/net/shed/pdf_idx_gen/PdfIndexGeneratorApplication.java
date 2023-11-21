package net.shed.pdf_idx_gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class PdfIndexGeneratorApplication {
	@PostMapping(value="/genIdx")
	String index(@RequestParam("orgPdf") MultipartFile orgPdf) {
		log.info("orgPdf={}", orgPdf.getOriginalFilename());
		return orgPdf.getOriginalFilename();
	}

	public static void main(String[] args) {
		SpringApplication.run(PdfIndexGeneratorApplication.class, args);
	}
}
