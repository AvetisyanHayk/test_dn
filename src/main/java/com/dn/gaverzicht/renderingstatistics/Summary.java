package com.dn.gaverzicht.renderingstatistics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Summary {
	private final int count;
	private final int duplicates;
	private final int unnecessary;
	
	Summary(int count, int duplicates, int unnecessary) {
		if (count < 0 || duplicates < 0 || unnecessary < 0) {
			throw new IllegalArgumentException();
		}
		this.count = count;
		this.duplicates = duplicates;
		this.unnecessary = unnecessary;
	}

	public int getCount() {
		return count;
	}

	public int getDuplicates() {
		return duplicates;
	}

	public int getUnnecessary() {
		return unnecessary;
	}
}
