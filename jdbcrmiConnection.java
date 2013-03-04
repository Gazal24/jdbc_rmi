package webp_rmi;

import java.io.*;
import java.rmi.*;
import java.util.*;
import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;
import com.thoughtworks.xstream.XStream;

public class jdbcrmiConnection
{
    public String execute(int queryNumber, ArrayList paramList)
    {
	appconfig pinfo = new appconfig();	
	System.out.println("hello");
	System.out.println(paramList.get(0));
	Statement stmt;
	Connection con;
	ResultSet rs= null;   
	String uname_db = "aa";
	CachedRowSetImpl crs;	
	String query = "";

	try {
	    Class.forName(pinfo.jdbccriver);
	    con = DriverManager.getConnection(pinfo.dburl, pinfo.dbusername, pinfo.dbpasswd); 
	    //con = DriverManager.getConnection(url, "root", "root"); 
	    stmt = con.createStatement();

	    switch(queryNumber){
	    case 1: 
		query = "Select * from user";
		rs = stmt.executeQuery(query);		    
		break;


		// QUERIES FROM checklogin.jsp
	    case 20: 
		String uname = (String)paramList.get(0);
		String passwd = (String)paramList.get(1);
		query = "SELECT * from `proj1`.`user` where `uname` = '" + uname + "' AND `password` = '" + passwd + "'";
		rs = stmt.executeQuery(query);
		break;

	    case 21:
		String email_id = (String)paramList.get(0);
		String code = (String)paramList.get(1);
		query = "SELECT * from `play` WHERE `player_email_id` = '" + email_id + "' AND `code` = '"+ code +"'";

		rs = stmt.executeQuery(query);
		break;

	    case 22:
		String game_id =  (String)paramList.get(0);
		query = "SELECT `task_id` FROM `game` WHERE `id` = '"+ game_id +"'";
		rs = stmt.executeQuery(query);
		break;
		
	    case 23:
		String task_id = (String)paramList.get(0);
		query = "SELECT `uname` FROM `task` WHERE `id` = '"+ task_id +"'";
		rs = stmt.executeQuery(query);
		break;

		// QUERIES FROM task.jsp
	    case 30:
		uname = (String)paramList.get(0);
		query = "SELECT * from task WHERE uname = '" + uname +"'";
		rs = stmt.executeQuery(query);
		break;


		// QUERIES FROM method.jsp
	    case 40:
		task_id = (String)paramList.get(0);
		query = "Select * FROM task WHERE id=" + task_id;
		rs = stmt.executeQuery(query);
		break;

	    case 41:
		task_id = (String)paramList.get(0);
		query = "SELECT * from `method` WHERE task_id=" + task_id;
		rs = stmt.executeQuery(query);
		break;

	    case 42:
		task_id = (String)paramList.get(0);
		query = "SELECT * from `set` WHERE task_id=" + task_id;
		rs = stmt.executeQuery(query);
		break;

	    }

	    crs = new CachedRowSetImpl();
	    crs.populate(rs);
	    
	    XStream xstream = new XStream();
	    String xml = xstream.toXML(crs); // serialize to XML
	    return xml;
	}
	
	catch(Exception e){
	    System.out.println("error in server side "+e.toString());
	}
	return uname_db;
		
    }
}
