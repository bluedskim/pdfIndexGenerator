package net.shed.pdf_idx_gen;

import java.io.IOException;
import java.nio.file.Path;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PdfIndexGenegator {
	public Path genIdx(Path orgPdfPath) throws IOException {
		log.info("converting orgPdfPath={}" + orgPdfPath);
		File indexedPdfFile = new File(
			orgPdfPath.getParent().toString()
			+ File.separator
			+ orgPdfPath.toFile().getName()
			+ "_indexed"
		);
		PDDocument orgPdf = PDDocument.load(orgPdfPath.toFile());
		PDFTextStripper reader = new PDFTextStripper();
		try(PDDocument indexedPdf = new PDDocument();) {
			for(int i = 0 ; i < orgPdf.getNumberOfPages() ; i++) {
				reader.setStartPage(i);
				reader.setEndPage(i);
				String pageText = reader.getText(orgPdf);
				log.info(pageText);
				indexedPdf.addPage(orgPdf.getPage(i));
			}
			indexedPdf.save(indexedPdfFile);
		}
		return indexedPdfFile.toPath();
	}
}
