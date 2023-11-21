package net.shed.pdf_idx_gen;

import org.apache.pdfbox.pdmodel.PDPage;

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
		return content.compareTo(arg0.getContent());
	}

  @Override
  public boolean equals(Object arg0) {
		return content.equals(((PageText)arg0).getContent());
	}

	public String toString() {
		return content;
	}
}
