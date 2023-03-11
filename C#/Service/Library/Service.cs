using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace Library {
  [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
  public class Service : IService {
    /* ********************************************************************** */
    /*                                                                        */
    /* ********************************************************************** */
    // Public variables

    /* ********************************************************************** */
    /*                                                                        */
    /* ********************************************************************** */
    public String GetVersion() {
      return "0.0.0-rc01";
    }

    public String UpdateAdminPassword(String newpassword) {
      return "Sample return";
    }
  }
}
