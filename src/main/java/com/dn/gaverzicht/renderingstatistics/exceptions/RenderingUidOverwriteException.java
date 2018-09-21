package com.dn.gaverzicht.renderingstatistics.exceptions;

public class RenderingUidOverwriteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RenderingUidOverwriteException(String oldUid, String newUid) {
		super("Old uid: " + oldUid + "; New uid: " + newUid);
	}
}
