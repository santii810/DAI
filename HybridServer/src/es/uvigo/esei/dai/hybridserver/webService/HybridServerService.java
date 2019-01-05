package es.uvigo.esei.dai.hybridserver.webService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import es.uvigo.esei.dai.hybridserver.model.Page;

@WebService()
@SOAPBinding(style = Style.RPC)
public interface HybridServerService {
	@WebMethod
	public Page getValue(String uuid, String dbTable);

	@WebMethod
	public List<String> listUuidFromTable(String dbTable);
}