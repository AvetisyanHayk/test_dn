package com.dn.gaverzicht.slp.core;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public interface Report {
	
	void saveToXml();
	void saveToXml(String targetPath);
	
	static void saveToXml(Class<?> reportClass, Report report, String targetDir) {
		try {
			ReportFactory.checkOrCreateDirectory(targetDir);
			File file = new File(targetDir + "/" + reportClass.getSimpleName() + ".xml");
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			JAXBContext jaxbContext = JAXBContext.newInstance(reportClass);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(report, file);
			jaxbMarshaller.marshal(report, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	void init(String logFilePath);
}
