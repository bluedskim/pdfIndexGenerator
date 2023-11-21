package net.shed.pdf_idx_gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PdfIndexGeneratorApplication {
	public static void main(String[] args) {
		log.info("##### starting...");
		SpringApplication.run(PdfIndexGeneratorApplication.class, args);
	}
}
