package io.github.navin3d.ssh.server.models;

import java.io.Serializable;


public class DiscMount implements Serializable {

	private static final long serialVersionUID = -5454181583827669670L;
	
	public DiscMount() {}
	
	public DiscMount(String line) {
		String[] words = line.replaceAll("\\s{2,}", " ").split(" ");
		this.fileSystem = words[0];
		this.size = words[1];
		this.used = words[2];
		this.available = words[3];
		this.use = words[4];
		this.mountedOn = words[5];
	}
	
	private String fileSystem;

	private String size;

	private String used;

	private String available;

	private String use;

	private String mountedOn;

	public String getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(String fileSystem) {
		this.fileSystem = fileSystem;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getMountedOn() {
		return mountedOn;
	}

	public void setMountedOn(String mountedOn) {
		this.mountedOn = mountedOn;
	}

}
