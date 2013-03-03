package webp_rmi;

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  
 
 public class appconfig{  
	
	public String jdbccriver="";
	public String dburl="";
	public String dbusername="";
	public String dbpasswd="";
	public String blport="";
	public String tempdatadir="";
	public String modemport = "";
	public String netaddr= "";

	public appconfig() {
		doSomeOperation();	
	}

	public void doSomeOperation() {  
	
		try{	
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties properties = new Properties();  
			properties.load(inputStream);  
			jdbccriver = properties.getProperty("jdbccriver");  
			//System.out.println("Property value is: " + bl_ip);
			dburl = properties.getProperty("dburl");
			dbusername = properties.getProperty("dbusername");  
			dbpasswd = properties.getProperty("dbpasswd");  
			blport = properties.getProperty("blport");  
			tempdatadir = properties.getProperty("tempdatadir");  
			modemport = properties.getProperty("modemport");
			netaddr=properties.getProperty("netaddr");
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
}  
