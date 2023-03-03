package ir.zencore.api.general.implementation;

import ir.zencore.api.constants.Defaults;
import ir.zencore.api.constants.webservice.soap.Actions;
import ir.zencore.api.constants.webservice.soap.Implementations;
import ir.zencore.api.constants.webservice.soap.Operations;
import ir.zencore.api.general.interfaces.soap.SGeneral;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = Implementations.GENERAL_WEBSERVICE_NAME,
  targetNamespace = Defaults.TARGET_NAME_SPACE,
  serviceName = Implementations.GENERAL_SERVICE_NAME,
  portName = Implementations.GENERAL_PORT_NAME)

//@HandlerChain(file = "handler-chain.xml")

public class GeneralImpl implements SGeneral {

  @Override
  @WebMethod(operationName = Operations.GLOBAL_GET_VERSION, action = Actions.GLOBAL_GET_VERSION)
  @WebResult(name = Operations.GLOBAL_GET_VERSION, partName = Operations.GLOBAL_GET_VERSION_RESPONSE, targetNamespace = Defaults.TARGET_NAME_SPACE)
  @RequestWrapper(targetNamespace = Defaults.TARGET_NAME_SPACE)
  @ResponseWrapper(targetNamespace = Defaults.TARGET_NAME_SPACE)
  @XmlElement(name = Operations.GLOBAL_GET_VERSION, namespace = Defaults.TARGET_NAME_SPACE)
  public String GetVersion() {
    return "1.0.0-rc1";
  }
}
