package br.com.icc.model;

public class HostPort {
	private String host;
	private Integer port;
	
	public HostPort(String host, Integer port) {
		super();
		this.host = host;
		this.port = port;
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
	
	
}
