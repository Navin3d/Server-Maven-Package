package io.github.navin3d.ssh.server.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DiscStats implements Serializable {
	
	private static final long serialVersionUID = -2835692209855993705L;
	
	private Set<DiscMount> discMounts = new HashSet<>();
		
	private LocalDate capturedAt;
	
	public DiscStats() {
		this.capturedAt = LocalDate.now();
	}

	public Set<DiscMount> getDiscMounts() {
		return discMounts;
	}

	public void setDiscMounts(Set<DiscMount> discMounts) {
		this.discMounts = discMounts;
	}

	public LocalDate getCapturedAt() {
		return capturedAt;
	}

	public void setCapturedAt(LocalDate capturedAt) {
		this.capturedAt = capturedAt;
	}

}
