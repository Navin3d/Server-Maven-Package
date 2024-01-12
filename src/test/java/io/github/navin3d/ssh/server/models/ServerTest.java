package gmc.library.ssh.server.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.navin3d.ssh.server.exceptions.CommandExecutionException;
import io.github.navin3d.ssh.server.exceptions.ServerConnectionException;
import io.github.navin3d.ssh.server.models.Server;

public class ServerTest {
		
	@Test
	public void itShouldGetDiscStat() throws ServerConnectionException, CommandExecutionException {
		Server server = new Server("localhost", "navin", "navin", 1022);
		System.out.print(server.getDiscStats().getDiscMounts().size());
		assertEquals(5, server.getDiscStats().getDiscMounts().size());
	}
	
	@Test
	public void itShouldGetIOStat() throws ServerConnectionException, CommandExecutionException {
		Server server = new Server("localhost", "navin", "navin", 1022);
		System.out.print(server.getIOStat().getIoDatas().get(0).getDevice());
		assertEquals(1, server.getIOStat().getIoDatas().size());
	}
	
	@Test
	public void itShouldGetStat() throws ServerConnectionException, CommandExecutionException {
		Server server = new Server("localhost", "navin", "navin", 1022);
		System.out.print(server.getStats().getTotalRam());
	}

}
