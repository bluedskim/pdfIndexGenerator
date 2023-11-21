package net.shed.pdf_idx_gen;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PdfIndexGenegator {
	public Path genIdx(Path orgPdfPath) {
		return orgPdfPath;
	}
}
