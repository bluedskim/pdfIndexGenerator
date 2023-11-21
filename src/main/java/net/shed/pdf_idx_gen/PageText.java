package net.shed.pdf_idx_gen;

import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PageText implements Comparable<PageText>{
	private String content;
	private PDPage page;

	@Override
	public int compareTo(PageText arg0) {
		String stripedContent = StringUtils.stripAccents(content);
		String stripedCompare = StringUtils.stripAccents(arg0.getContent());
		return stripedContent.compareTo(stripedCompare);
	}

  @Override
  public boolean equals(Object arg0) {
		String stripedContent = StringUtils.stripAccents(content);
		String stripedCompare = StringUtils.stripAccents(
			((PageText)arg0).getContent()
		);
		return stripedContent.equals(stripedCompare);
	}

	public String toString() {
		return content;
	}
}
