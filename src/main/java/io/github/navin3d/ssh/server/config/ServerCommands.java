package io.github.navin3d.ssh.server.config;

public enum ServerCommands {
	
	CPU_UTILIZATION_COMMAND("mpstat 1 1"),
	
	RAM_UTILIZATION_COMMAND("cat /proc/meminfo | head -3"), 
	
	DISC_UTILIZATION_COMMAND("df -H"),
	
	LOAD_AND_UPTIME_COMMAND("uptime"),
	
	IO_READ_WRITE_COMMAND("iostat"),
	
	SWAP_STAT_COMMAND("free -m");
	
	private final String text;

    /**
     * @param text
     */
	ServerCommands(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
