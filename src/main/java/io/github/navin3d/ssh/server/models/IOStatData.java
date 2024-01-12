package io.github.navin3d.ssh.server.models;

import java.io.Serializable;

public class IOStatData implements Serializable {

    private static final long serialVersionUID = -5454181583827669670L;
	
	public IOStatData() {}
	
	public IOStatData(String line) {
		String[] words = line.replaceAll("\\s+", " ").split(" ");
		this.device = words[0];
		this.transferPerSecond = words[1];
		this.readPerSecond = words[2];
		this.writePerSecond = words[3];
		this.averageRead = words[5];
		this.averageWrite = words[6];
	}
	
	private String device;

	private String transferPerSecond;

	private String readPerSecond;

	private String writePerSecond;

	private String averageRead;

	private String averageWrite;

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getTransferPerSecond() {
		return transferPerSecond;
	}

	public void setTransferPerSecond(String transferPerSecond) {
		this.transferPerSecond = transferPerSecond;
	}

	public String getReadPerSecond() {
		return readPerSecond;
	}

	public void setReadPerSecond(String readPerSecond) {
		this.readPerSecond = readPerSecond;
	}

	public String getWritePerSecond() {
		return writePerSecond;
	}

	public void setWritePerSecond(String writePerSecond) {
		this.writePerSecond = writePerSecond;
	}

	public String getAverageRead() {
		return averageRead;
	}

	public void setAverageRead(String averageRead) {
		this.averageRead = averageRead;
	}

	public String getAverageWrite() {
		return averageWrite;
	}

	public void setAverageWrite(String averageWrite) {
		this.averageWrite = averageWrite;
	}
    
}
