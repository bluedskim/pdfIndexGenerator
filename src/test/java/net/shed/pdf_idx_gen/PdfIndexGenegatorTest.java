package net.shed.pdf_idx_gen;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PdfIndexGenegatorTest {
	@Test
	void sortTest() throws IOException {
		PdfIndexGenegator pdfIndexGenegator = new PdfIndexGenegator();
		PDDocument original = new PDDocument();
		PDFTextStripper reader = new PDFTextStripper();
		for(int i = 1 ; i <= 10 ; i++) {
			PDPage newPage = new PDPage();
			original.addPage(newPage);
			PDPageContentStream contentStream = new PDPageContentStream(original, newPage);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
			contentStream.showText((10 - i) + "_title");
			contentStream.endText();
			contentStream.close();

			reader.setStartPage(i-1);
			reader.setEndPage(i-1);
			log.info("original i={}, pageText={}", i-1, reader.getText(original).trim());
		}

		PDDocument sorted = pdfIndexGenegator.sortPages(original);
		for(int i = 0 ; i < sorted.getNumberOfPages() ; i++) {
			reader.setStartPage(i);
			reader.setEndPage(i);
			String pageText = reader.getText(sorted);
			log.info("sorted i={}, pageText={}", i, pageText);
		}
	}
}
