package es.uvigo.esei.dai.hybridserver.http;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HTTPResponse {

	private HTTPResponseStatus status;
	private String version;
	private String content;
	private Map<String, String> parameters;

	public HTTPResponse() {
		this.parameters = new LinkedHashMap<String, String>();

	}

	public HTTPResponseStatus getStatus() {
		return this.status;
	}

	public void setStatus(HTTPResponseStatus status) {
		this.status = status;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getParameters() {
		return this.parameters;
	}

	public String putParameter(String name, String value) {
		return this.parameters.put(name, value);
	}

	public boolean containsParameter(String name) {
		return parameters.containsKey(name);
	}

	public String removeParameter(String name) {
		return this.parameters.remove(name);
	}

	public void clearParameters() {
		this.parameters.clear();
	}

	public List<String> listParameters() {
		List<String> toret = new LinkedList<String>();
		for (String key : parameters.keySet()) {
			toret.add(key + ": " + parameters.get(key) + "\r\n");
		}
		return toret;
	}

	public void print(Writer writer) throws IOException {
		BufferedWriter bw = new BufferedWriter(writer);
		StringBuilder message = new StringBuilder(this.getVersion());
		message.append(" ");
		message.append(this.getStatus().getCode());
		message.append(" ");
		message.append(this.status.getStatus());
		message.append("\r\n");

		if (!this.getParameters().isEmpty()) {
			for (String parameter : listParameters()) {
				message.append(parameter);
			}
		}

		if (this.getContent() != null) {
			message.append("Content-Length: ");
			message.append(this.getContent().length());
			message.append("\r\n\r\n");
			message.append(this.getContent());
		} else {
			message.append("\r\n");
		}

		bw.write(message.toString());
		bw.flush();

	}

	@Override
	public String toString() {
		final StringWriter writer = new StringWriter();

		try {
			this.print(writer);
		} catch (IOException e) {
		}

		return writer.toString();
	}
}
