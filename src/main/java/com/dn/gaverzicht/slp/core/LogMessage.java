package com.dn.gaverzicht.slp.core;

import java.util.ArrayList;
import java.util.List;

import com.dn.gaverzicht.slp.core.ReportFactory.Tools;

public class LogMessage {
	List<String> lines = new ArrayList<>();
	
	LogMessage(String line) {
		if (line == null) {
			throw new IllegalArgumentException();
		}
		lines.add(line.trim());
	}
	
	public void addLine(String line) {
		if (line == null) {
			throw new IllegalArgumentException();
		}
		lines.add(line);
	}
	
	public String get(int lineIndex) {
		return lines.get(lineIndex);
	}
	
	public boolean firstLineContains(String text) {
		return get(0).contains(text.trim());
	}
	
	@Override
	public String toString() {
		return Tools.linesToString(lines);
	}
}
