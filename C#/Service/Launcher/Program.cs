using Library;
using System;
using System.ServiceModel;
using System.ServiceModel.Description;

namespace Launcher {
  class Program {
    static void Main(string[] args) {
      using (ServiceHost serviceHost = new ServiceHost(typeof(Service))) {
        serviceHost.Open();
        ServiceDescription serviceDescription = serviceHost.Description;
        foreach (ServiceEndpoint serviceEndpoint in serviceDescription.Endpoints) {
          Console.WriteLine(String.Format("URL: {0}", serviceEndpoint.Address.ToString()));
          Console.WriteLine(String.Format("     Endpoint name: {0}", serviceEndpoint.Binding.Name));
          Console.WriteLine(String.Format("     Service: {0}", serviceEndpoint.Contract.Name));
        }
        Console.WriteLine("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        Console.WriteLine("Service is host at " + DateTime.Now.ToString());
        Console.WriteLine("Host is running... Press <ENTER> key to stop");
        Console.WriteLine("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        Console.ReadLine();
        serviceHost.Close();
      }
    }
  }
}
