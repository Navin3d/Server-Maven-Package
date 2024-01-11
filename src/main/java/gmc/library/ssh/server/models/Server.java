package gmc.library.ssh.server.models;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import gmc.library.ssh.server.exceptions.CommandExecutionException;
import gmc.library.ssh.server.exceptions.ServerConnectionException;

public class Server {
	
	private static final Logger log = Logger.getLogger("Server");
	
	public Server(String host, String userName, String password) {
		this.host = host;
		this.userName = userName;
		this.password = password;
	}
	
	public Server(String host, String userName, String password, Integer port) {
		this.host = host;
		this.userName = userName;
		this.password = password;
		this.port = port;
	}
	
	private String host;
	
	private Integer port = 22;
	
	private String userName;
	
	private String password;
	
	private String keyPath;
	
	private String keySecret;
	
	private Session session;
	
	public Boolean isConnected() {
		return this.session.isConnected();
	}
	
	public Boolean disconnect() {
		try {
			this.session.disconnect();
			log.info("Disconnected connection with server " + host);
			return true;
		} catch(Exception e) {
			log.info("Error disconnecting connection with server " + host);
			return false;
		}
	}
	
	public Boolean refreshConnection() {
		try {
			disconnect();
			getSession();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Session getSession() throws ServerConnectionException {
		if(!session.isConnected()) {
			Properties jschConfig = new Properties();
			jschConfig.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			
			log.info("Attempting to get session of " + this.host + " with user " + this.userName + ".");
			try {
				this.session = jsch.getSession(this.userName, this.host, this.port);
				this.session.setConfig(jschConfig);
				this.session.setPassword(this.password);
				this.session.connect();
				log.info("Successfully connected with session of " + host + " with user " + this.userName + ".");
			} catch (Exception e) {
				throw new ServerConnectionException("Error connecting to " + host + " with User: " + this.userName);
			}
		}
		return this.session;
	}
	
	public List<String> executeCommand(String command) throws ServerConnectionException, CommandExecutionException {
		Channel channel = null;
		List<String> response = new ArrayList<>();
		session = getSession();
		try {
			log.info("Attempting to execute command " + command);
			channel = session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec)channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			
			byte[] lineByte = new byte[1024];
			while(true) {
				while(in.available() > 0) {
					int lineLength = in.read(lineByte, 0, 1024);
					if(lineLength < 0) break;
					String lineString = new String(lineByte, 0, lineLength);
					if(lineString.isEmpty()) continue;
					response.add(lineString);
				}
				if(channel.isClosed()) {
					if(response.size() == 0)
						log.info("Exit status = " + channel.getExitStatus() + " while running command " + command);
					break;
				}
				try {
					Thread.sleep(1000);
				} catch(Exception e) {}
			}
		} catch (Exception e) {
			throw new CommandExecutionException("Facing exception while running command " + command + " with log " + e.getMessage());
		}
		if(response.size() == 0)
			throw new CommandExecutionException("Exit status = " + channel.getExitStatus() + " while running command " + command);
		channel.disconnect();
		return response;
	}
	
	public static List<String> executeCommand(String command, Session session) throws ServerConnectionException, CommandExecutionException {
		Channel channel = null;
		List<String> response = new ArrayList<>();
		try {
			log.info("Attempting to execute command " + command);
			channel = session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec)channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			
			byte[] lineByte = new byte[1024];
			while(true) {
				while(in.available() > 0) {
					int lineLength = in.read(lineByte, 0, 1024);
					if(lineLength < 0) break;
					String lineString = new String(lineByte, 0, lineLength);
					if(lineString.isEmpty()) continue;
					response.add(lineString);
				}
				if(channel.isClosed()) {
					if(response.size() == 0)
						log.info("Exit status = " + channel.getExitStatus() + " while running command " + command);
					break;
				}
				try {
					Thread.sleep(1000);
				} catch(Exception e) {}
			}
		} catch (Exception e) {
			throw new CommandExecutionException("Facing exception while running command " + command + " with log " + e.getMessage());
		}
		if(response.size() == 0)
			throw new CommandExecutionException("Exit status = " + channel.getExitStatus() + " while running command " + command);
		channel.disconnect();
		return response;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	public String getKeySecret() {
		return keySecret;
	}

	public void setKeySecret(String keySecret) {
		this.keySecret = keySecret;
	}

}
