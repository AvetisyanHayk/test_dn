package com.dn.gaverzicht.renderingstatistics;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.dn.gaverzicht.slp.core.Report;
import com.dn.gaverzicht.slp.core.ReportFactory;

@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "renderings", "summary" })
public class RenderingStatisticsReport implements Report {

	@XmlElement(name = "rendering")
	private Set<Rendering> renderings;
	@XmlElement(name = "summary")
	private Summary summary;

	@Override
	public void init(String logFilePath) {
		renderings = RenderingFactory.createRenderings(logFilePath);
		summary = new Summary(getRenderingsCount(), getRenderingDuplicates(), getUnnecessaryRenderingRequestsCount());
	}

	public Set<Rendering> getRenderings() {
		return Collections.unmodifiableSet(renderings);
	}

	@Override
	public void saveToXml() {
		saveToXml(ReportFactory.DEFAULT_REPORTS_DIR);
	}

	@Override
	public void saveToXml(String targetDir) {
		Report.saveToXml(this.getClass(), this, targetDir);
	}

	private int getRenderingDuplicates() {
		return renderings.stream().mapToInt(Rendering::getDuplicates).sum();
	}

	private int getRenderingsCount() {
		return renderings.size();
	}

	private int getUnnecessaryRenderingRequestsCount() {
		return renderings.stream().mapToInt(Rendering::getUnnecessaryRequestsCount).sum();
	}

}
