package net.shed.pdf_idx_gen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PageTestSortTest {
	@Test
	void sortTest() throws IOException {
		PDDocument pdf = new PDDocument();
		List<PageText> pageTexts = new ArrayList<>();
		PDFTextStripper reader = new PDFTextStripper();
		for (int i = 1; i <= 10; i++) {
			PDPage newPage = new PDPage();
			pdf.addPage(newPage);

			PDPageContentStream contentStream = new PDPageContentStream(pdf, newPage);

			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
			contentStream.showText((10 - i) + "_title");
      contentStream.newLineAtOffset(25, 500);
			contentStream.endText();
			contentStream.close();

			reader.setStartPage(i);
			reader.setEndPage(i);
			pageTexts.add(new PageText(reader.getText(pdf).trim(), newPage));

		}

		log.info("original {}", pageTexts.toString());
		assertEquals("[9_title, 8_title, 7_title, 6_title, 5_title, 4_title, 3_title, 2_title, 1_title, 0_title]", pageTexts.toString());
		Collections.sort(pageTexts);
		log.info("sorted {}", pageTexts.toString());
		assertEquals("[0_title, 1_title, 2_title, 3_title, 4_title, 5_title, 6_title, 7_title, 8_title, 9_title]", pageTexts.toString());

		pdf.close();
	}
}
