package es.uvigo.esei.dai.hybridserver;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import es.uvigo.esei.dai.hybridserver.model.Page;

@WebService()
@SOAPBinding(style = Style.RPC)
public interface HybridServerService {
	@WebMethod
	public String[] getValue(String uuid, String dbTable);

	@WebMethod
	public String[] listUuidFromTable(String dbTable);

	@WebMethod
	public boolean containsUuid(String uuid, String dbTable);
}