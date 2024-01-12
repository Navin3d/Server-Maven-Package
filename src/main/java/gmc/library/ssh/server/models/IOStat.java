package gmc.library.ssh.server.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class IOStat implements Serializable {
    
    private static final long serialVersionUID = -2835692209855993705L;
		
	private Set<IOStatData> ioDatas = new HashSet<>();
		
	private LocalDate capturedAt;
	
	public IOStat() {
		this.capturedAt = LocalDate.now();
	}

	public Set<IOStatData> getIoDatas() {
		return ioDatas;
	}

	public void setIoDatas(Set<IOStatData> ioDatas) {
		this.ioDatas = ioDatas;
	}

	public LocalDate getCapturedAt() {
		return capturedAt;
	}

	public void setCapturedAt(LocalDate capturedAt) {
		this.capturedAt = capturedAt;
	}
    
}
