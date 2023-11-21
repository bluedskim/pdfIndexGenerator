package net.shed.pdf_idx_gen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PdfIndexGenegator {
	public Path sortPages(Path orgPdfPath) throws IOException {
		log.info("converting orgPdfPath={}",  orgPdfPath);

		PDDocument orgPdf = PDDocument.load(orgPdfPath.toFile());
		PDDocument sortedPdf = sortPages(orgPdf);

		File sortedPdfFile = new File(
			orgPdfPath.getParent().toString()
			+ File.separator
			+ orgPdfPath.toFile().getName()
			+ "_sorted.pdf"
		);

		sortedPdf.save(sortedPdfFile);
		sortedPdf.close();
		return sortedPdfFile.toPath();
	}

	public PDDocument sortPages(PDDocument orgPdf) throws IOException {
		PDFTextStripper reader = new PDFTextStripper();
		List<PageText> pageTexts = new ArrayList<>();
		for(int i = 1 ; i <= orgPdf.getNumberOfPages() ; i++) {
			reader.setStartPage(i);
			reader.setEndPage(i);
			String pageText = reader.getText(orgPdf);
			if(pageText.indexOf("\n") >= 0) {
				pageText = pageText.substring(0, pageText.indexOf("\n"));
			}
			log.info("original idx={}, pageText={}, page={}", i, pageText, orgPdf.getPage(i-1).toString());
			pageTexts.add(new PageText(pageText, orgPdf.getPage(i-1)));
		}

		Collections.sort(pageTexts);

		PDDocument sortedPdf = new PDDocument();
		int pageNo = 0;
		for(PageText pageText : pageTexts ) {
			pageNo++;
			log.info("sorted pageNo={}, pageText={}, page={}", pageNo, pageText, pageText.getPage().toString());
			sortedPdf.addPage(pageText.getPage());
		}
		return sortedPdf;
	}
}