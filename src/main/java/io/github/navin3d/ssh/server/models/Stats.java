package io.github.navin3d.ssh.server.models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Stats implements Serializable {

	private static final long serialVersionUID = -618501582385421256L;

	private Long availableRam;

	private Long totalRam;

	private Long totalSwap;

	private Long freeSwap;
	
	private String uptime;
	
	private Double cpuPerformance;
	
	private Double ramPerformance;

	private Double serverLoad;

	private Boolean isActive;

	private LocalDateTime capturedAt;
	
	public Stats() {
		this.isActive = false;
		this.capturedAt = LocalDateTime.now();
	}
	
	public Stats(String cpuLine, String ramLine, String swapLine, String loadLine) {
		/*
		 * RAM Lines
		 */
		String[] ramlines = ramLine.split("\n");
		String totlram[] = ramlines[0].split("\\s+");
		String memavail[] = ramlines[2].split("\\s+");
		
		/*
		 * CPU Lines
		 */
		String[] strarray = cpuLine.split("\\s+");
		Double cpuPerformance = 100 - (Double.parseDouble(strarray[11]));
		DecimalFormat twoDForm = new DecimalFormat("#.##");

		/*
		 * SWAP Lines
		 */
		String[] requiredSwapLines = swapLine.split("\n")[2].split("\\s+");

		/*
		 * LOAD Lines
		 */
		Double requiredLoadLines = Double.parseDouble(loadLine.split("users,\\s+")[1].split("\\s+")[4].trim());

		this.uptime = loadLine.split(",")[0].trim();
		this.availableRam = Long.parseLong(memavail[1]);
		this.totalRam = Long.parseLong(totlram[1]);
		this.totalSwap = Long.valueOf(requiredSwapLines[1].trim());
		this.freeSwap = Long.valueOf(requiredSwapLines[3].trim());
		this.serverLoad = Double.valueOf(twoDForm.format(requiredLoadLines));
		this.cpuPerformance = Double.valueOf(twoDForm.format(cpuPerformance));
		this.isActive = true;
		this.capturedAt = LocalDateTime.now();
	}
	
	public Long usedRam() {
		return this.totalRam - this.availableRam;
	}
	
	public void setRAM(String ramLine) {
		/*
		 * Ram Lines
		 */
		String[] ramlines = ramLine.split("\n");
		String totlram[] = ramlines[0].split("\\s+");
		String memavail[] = ramlines[2].split("\\s+");
		
		this.availableRam = Long.parseLong(memavail[1]);
		this.totalRam = Long.parseLong(totlram[1]);
		this.isActive = true;
		this.capturedAt = LocalDateTime.now();
	}
	
	public void setCPU(String cpuLine) {
		/*
		 * CPU Lines
		 */
		String[] cpulines = cpuLine.split("\n");
		String[] strarray = cpulines[3].split("\\s+");
		Double cpuPerformance = 100 - (Double.parseDouble(strarray[11]));
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		
		this.cpuPerformance = Double.valueOf(twoDForm.format(cpuPerformance));
		this.isActive = true;
		this.capturedAt = LocalDateTime.now();
	}
	
	public void setIsActive(Boolean active) {
		this.isActive = active;
	}

	public Long getAvailableRam() {
		return availableRam;
	}

	public void setAvailableRam(Long availableRam) {
		this.availableRam = availableRam;
	}

	public Long getTotalRam() {
		return totalRam;
	}

	public void setTotalRam(Long totalRam) {
		this.totalRam = totalRam;
	}

	public Long getTotalSwap() {
		return totalSwap;
	}

	public void setTotalSwap(Long totalSwap) {
		this.totalSwap = totalSwap;
	}

	public Long getFreeSwap() {
		return freeSwap;
	}

	public void setFreeSwap(Long freeSwap) {
		this.freeSwap = freeSwap;
	}

	public Double getCpuPerformance() {
		return cpuPerformance;
	}

	public void setCpuPerformance(Double cpuPerformance) {
		this.cpuPerformance = cpuPerformance;
	}

	public Double getRamPerformance() {
		return ramPerformance;
	}

	public void setRamPerformance(Double ramPerformance) {
		this.ramPerformance = ramPerformance;
	}

	public Double getServerLoad() {
		return serverLoad;
	}

	public void setServerLoad(Double serverLoad) {
		this.serverLoad = serverLoad;
	}

	public LocalDateTime getCapturedAt() {
		return capturedAt;
	}

	public void setCapturedAt(LocalDateTime capturedAt) {
		this.capturedAt = capturedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	
}
