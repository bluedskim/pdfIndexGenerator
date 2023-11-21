package net.shed.pdf_idx_gen;

import java.io.IOException;
import java.nio.file.Path;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PdfIndexGenegator {
	public Path genIdx(Path orgPdfPath) throws IOException {
		log.info("converting orgPdfPath={}" + orgPdfPath);
		PDDocument orgPdf = PDDocument.load(orgPdfPath.toFile());
		PDDocument indexedPdf = orgPdf;
		File indexedPdfFile = new File(
			orgPdfPath.getParent().toString()
			+ File.separator
			+ orgPdfPath.toFile().getName()
			+ "_indexed"
		);
		indexedPdf.save(indexedPdfFile);
		return indexedPdfFile.toPath();
	}
}
