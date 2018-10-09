package es.uvigo.esei.dai.hybridserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.derby.tools.sysinfo;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class HTTPRequest {

	private BufferedReader br;
	private HTTPRequestMethod method;
	private String resourceChain;
	private String[] resourcePath;
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
			method = HTTPRequestMethod.valueOf(firstLine[0]);
			resourceChain = firstLine[1];
			resourceName = firstLine[1].split("\\?")[0].substring(1);
			resourcePath = resourceName.split("\\/");
			httpVersion = firstLine[2];
			String parametersString[] = firstLine[1].split("\\?")[1].split("&");
			for (String itParameters : parametersString) {
				String[] parameters = itParameters.split("=");
				resourceParameters.put(parameters[0], parameters[1]);
			}

			while ((readed = br.readLine()) != null && !readed.equals("")) {
				String hp[] = readed.split(": ");
				headerParameters.put(hp[0], hp[1]);
			}

			content = br.readLine();
			if (headerParameters.containsKey("Content-Length"))
				contentLength = Integer.parseInt(headerParameters.get("Content-Length"));
			else
				contentLength = 0;

		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			throw new HTTPParseException("HTTP Method incorrect.");
		}

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
