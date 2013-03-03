package webp_rmi;

import java.rmi.registry.*;
import java.net.*;
import java.io.*;
import webp_rmi.*;
import java.rmi.RMISecurityManager;

public class server
{
    public static void main(String[] args)
    {
	appconfig pinfo=new appconfig();
	if (System.getSecurityManager() == null)
	    {
		System.setSecurityManager(new RMISecurityManager());
	    } 		

	int thisPort=Integer.parseInt(pinfo.blport);
	String thisAddress=pinfo.netaddr;
	Registry registry;
	
	try{
			
	    System.out.println("this address="+thisAddress+",port="+thisPort );
	    registry = LocateRegistry.createRegistry(thisPort);
        	
	    jdbcrmi_impl jdbcrmi_impl_obj=new jdbcrmi_impl();
			
	    System.out.println("Remote object created ...");
	    
	    registry.rebind("jdbcrmi", jdbcrmi_impl_obj);
	    System.out.println("Remote object registered to registry ...");
	    System.out.println("dbJdbcDriver:"+pinfo.jdbccriver);
	    System.out.println("dbUrl:"+pinfo.dburl);
        }
        catch(Exception e){
	    System.out.println("error in server side"+e.toString());
        }
        
    }
}
