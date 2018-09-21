package com.dn.gaverzicht.renderingstatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.dn.gaverzicht.renderingstatistics.exceptions.RenderingUidOverwriteException;
import com.dn.gaverzicht.slp.concretelogs.GetRenderingReturn;
import com.dn.gaverzicht.slp.concretelogs.StartRenderingRequest;
import com.dn.gaverzicht.slp.concretelogs.StartRenderingReturn;
import com.dn.gaverzicht.slp.core.LogTimestamp;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "documentId", "documentPage", "uid", "allStartRenderingReturnTimestampStrings",
		"getRenderingReturnTimestampStrings" })
	class Rendering {
	
	@XmlTransient
	private final Document document;

	private String uid;

	@XmlTransient
	private List<LogTimestamp> unnecessaryStartRenderingReturnTimestamps = new ArrayList<>();
	@XmlTransient
	private List<LogTimestamp> startRenderingReturnTimestamps = new ArrayList<>();
	@XmlTransient
	private List<LogTimestamp> getRenderingReturnTimestamps = new ArrayList<>();

	public Rendering(StartRenderingRequest srRequest) {
		if (srRequest == null) {
			throw new IllegalArgumentException();
		}
		this.document = srRequest.getDocument();
	}

	public Document getDocument() {
		return document;
	}

	public String getUid() {
		return uid;
	}

	public void processStartRenderingReturn(StartRenderingReturn srReturn) {
		if (srReturn.getUid() != null) {
			if (this.uid != null) {
				if (!srReturn.getUid().equals(this.uid)) {
					throw new RenderingUidOverwriteException(this.uid, srReturn.getUid());
				}
			} else {
				this.uid = srReturn.getUid();
			}
		}
		unnecessaryStartRenderingReturnTimestamps.add(srReturn.getTimestamp());
	}

	public void processGetRenderingReturn(GetRenderingReturn grReturn) {
		if (!unnecessaryStartRenderingReturnTimestamps.isEmpty()) {
			getRenderingReturnTimestamps.add(grReturn.getTimestamp());
			startRenderingReturnTimestamps.add(unnecessaryStartRenderingReturnTimestamps.remove(0));
		}
	}

	int getDuplicates() {
		int duplicates = getCount() - 1;
		return (duplicates >= 0) ? duplicates : 0;
	}

	int getCount() {
		return startRenderingReturnTimestamps.size() + unnecessaryStartRenderingReturnTimestamps.size();
	}

	int getUnnecessaryRequestsCount() {
		return unnecessaryStartRenderingReturnTimestamps.size();
	}

	public List<LogTimestamp> getAllStartRenderingReturnTimestamps() {
		List<LogTimestamp> allStartRenderingReturnTimestamps = new ArrayList<>(unnecessaryStartRenderingReturnTimestamps);
		allStartRenderingReturnTimestamps.addAll(startRenderingReturnTimestamps);
		return allStartRenderingReturnTimestamps;
	}

	@XmlElement(name = "document")
	public long getDocumentId() {
		return document.getId();
	}

	@XmlElement(name = "page")
	public int getDocumentPage() {
		return document.getPage();
	}

	@XmlElement(name = "start")
	private List<String> getAllStartRenderingReturnTimestampStrings() {
		return getAllStartRenderingReturnTimestamps().stream().map(LogTimestamp::toString)
				.collect(Collectors.toList());
	}

	@XmlElement(name = "get")
	private List<String> getGetRenderingReturnTimestampStrings() {
		return getRenderingReturnTimestamps.stream().map(LogTimestamp::toString)
				.collect(Collectors.toList());
	}
}
