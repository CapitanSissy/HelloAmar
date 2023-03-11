using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace Library {
  [ServiceContract]
  [XmlSerializerFormat]
  public interface IService {
    /* ********************************************************************** */
    /*                                                                        */
    /* ********************************************************************** */
    [OperationContract(Name = "getVersion")]
    String GetVersion();

    /* ********************************************************************** */
    /*                                                                        */
    /* ********************************************************************** */
    [OperationContract(Name = "updateAdminPassword")]
    String UpdateAdminPassword(String newpassword);

    // Add whatever you want :)
  }
}
