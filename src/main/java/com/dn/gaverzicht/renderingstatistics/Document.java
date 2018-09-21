package com.dn.gaverzicht.renderingstatistics;

public class Document {
	
	private final long id;
	private final int page;

	public Document(long id, int page) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		if (page < 0) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.page = page;
	}

	public long getId() {
		return id;
	}

	public int getPage() {
		return page;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + page + 1;
		return result;	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Document)) {
			return false;
		}
		Document other = (Document) obj;
		if (id != other.id) {
			return false;
		}
		if (page != other.page) {
			return false;
		}
		return true;
	}
}
