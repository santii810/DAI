package es.uvigo.esei.dai.hybridserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRequest {

	private BufferedReader br;
	private HTTPRequestMethod method;
	private String resourceChain;
	private String[] resourcePath = new String[0];
	private String resourceName;
	private Map<String, String> resourceParameters;
	private String httpVersion;
	private Map<String, String> headerParameters;
	private String content;
	private int contentLength;

	public HTTPRequest(Reader reader) throws IOException, HTTPParseException {
		br = new BufferedReader(reader);
		buildRequest();

	}

	private void buildRequest() throws IOException, HTTPParseException {
		String readed;
		String[] firstLine = br.readLine().split(" ");
		resourceParameters = new LinkedHashMap<>();
		headerParameters = new LinkedHashMap<>();

		try {
			readFirstLine(firstLine);

			while ((readed = br.readLine()) != null && !readed.equals("")) {
				String hp[] = readed.split(": ");
				headerParameters.put(hp[0], hp[1]);
			}

			resourceName = resourceChain.split("\\?")[0].substring(1);
			if (!resourceName.isEmpty())
				resourcePath = resourceName.split("\\/");

			if (headerParameters.containsKey("Content-Length")) {
				readParamsFromContent();
			} else {
				if (resourceChain.contains("?")) {
					readParamsFromResourceChain();
				}
			}
		} catch (Exception e) {
			throw new HTTPParseException("HTTP Method incorrect.", e);
		}

	}

	private void readParamsFromResourceChain() {
		String parametersString[] = resourceChain.split("\\?");
		if (parametersString.length > 1) {
			parametersString = parametersString[1].split("&");
			for (String itParameters : parametersString) {
				String[] parameters = itParameters.split("=");
					resourceParameters.put(parameters[0], parameters[1]);
			}
		}
	}

	private void readParamsFromContent() throws IOException, UnsupportedEncodingException {
		contentLength = Integer.parseInt(headerParameters.get("Content-Length"));

		char[] contentChar = new char[contentLength];
		br.read(contentChar);
		content = String.valueOf(contentChar);

		if (headerParameters.containsKey("Content-Type")) {
			String contentType = headerParameters.get("Content-Type");
			if (contentType != null && contentType.startsWith("application/x-www-form-urlencoded")) {
				content = URLDecoder.decode(content, "UTF-8");
			}
		}

		String parametersString[] = content.split("&");
		for (int i = 0; i < parametersString.length; i++) {
			resourceParameters.put(parametersString[i].split("=")[0], parametersString[i].split("=")[1]);
		}
	}

	private void readFirstLine(String[] firstLine) {
		method = HTTPRequestMethod.valueOf(firstLine[0]);
		resourceChain = firstLine[1];
		httpVersion = firstLine[2];
	}

	public HTTPRequestMethod getMethod() {
		return method;
	}

	public String getResourceChain() {
		return resourceChain;
	}

	public String[] getResourcePath() {
		return resourcePath;
	}

	public String getResourceName() {
		return resourceName;
	}

	public Map<String, String> getResourceParameters() {
		return resourceParameters;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public Map<String, String> getHeaderParameters() {
		return headerParameters;
	}

	public String getContent() {
		return content;
	}

	public int getContentLength() {
		return contentLength;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(this.getMethod().name()).append(' ').append(this.getResourceChain())
				.append(' ').append(this.getHttpVersion()).append("\r\n");

		for (Map.Entry<String, String> param : this.getHeaderParameters().entrySet()) {
			sb.append(param.getKey()).append(": ").append(param.getValue()).append("\r\n");
		}

		if (this.getContentLength() > 0) {
			sb.append("\r\n").append(this.getContent());
		}

		return sb.toString();
	}
}
