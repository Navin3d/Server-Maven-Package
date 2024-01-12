package gmc.library.ssh.server.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOStat implements Serializable {
    
    private static final long serialVersionUID = -2835692209855993705L;
		
	private List<IOStatData> ioDatas = new ArrayList<>();
		
	private LocalDate capturedAt;
	
	public IOStat() {
		this.capturedAt = LocalDate.now();
	}

	public List<IOStatData> getIoDatas() {
		return ioDatas;
	}

	public void setIoDatas(List<IOStatData> ioDatas) {
		this.ioDatas = ioDatas;
	}

	public LocalDate getCapturedAt() {
		return capturedAt;
	}

	public void setCapturedAt(LocalDate capturedAt) {
		this.capturedAt = capturedAt;
	}
    
}
