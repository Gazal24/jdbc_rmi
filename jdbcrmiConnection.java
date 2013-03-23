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
	// System.out.println(paramList.get(0));
	Statement stmt;
	Connection con;
	ResultSet rs= null;   
	String uname_db = "aa";
	CachedRowSetImpl crs;	
	String query = "";
	int is_update_query = 0;
	int query_status = 0;
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


		// QUERIES FROM game1.jsp
	    case 50:
		task_id = (String)paramList.get(0);
		query = "SELECT * from `method` WHERE task_id=" + task_id;
		rs = stmt.executeQuery(query);
		break;

	    case 51:
		task_id = (String)paramList.get(0);
		query = "SELECT * from `set` WHERE task_id=" + task_id;
		rs = stmt.executeQuery(query);
		break;


		// QUERIES FROM result.jsp
	    case 60:
		task_id = (String)paramList.get(0);
		query = "SELECT `score` FROM `game`,`play` WHERE `game`.`task_id` = " + task_id + " AND `play`.`game_id` = `game`.`id`";
		rs = stmt.executeQuery(query);
		break;

	    case 61:
		String play_id = (String)paramList.get(0);
		query = "SELECT `score` FROM `play` WHERE `id`=" + play_id;
		rs = stmt.executeQuery(query);
		break;


		// QUERIES FROM creategame.jsp
	    case 70:
		task_id = (String)paramList.get(0);
		query = "Select * from task WHERE id=" + task_id;
		rs = stmt.executeQuery(query);
		break;



		// QUERIES FROM savegame.jsp
	    case 80:
		is_update_query = 1;
		task_id = (String)paramList.get(0);
		code = (String)paramList.get(1);
		query = "INSERT INTO `game` (`task_id`, `code`) VALUES ('" + task_id + "', '" + code + "')" ;
		query_status = stmt.executeUpdate(query);
		break;

	    case 81:
		code = (String)paramList.get(0);
		query = "SELECT id as game_id FROM `game` WHERE `code` = '" + code +"'";
		rs = stmt.executeQuery(query);
		break;

	    case 82:
		is_update_query = 1;
		game_id = (String) paramList.get(0);
		code = (String) paramList.get(1);
		String email = (String) paramList.get(2);
		query =  "INSERT INTO `play` (`game_id`, `code`, `player_email_id`) VALUES ('" + game_id + "', '" + code + "', '" + email + "')" ;
		query_status = stmt.executeUpdate(query);
		break;


		// QUERIES FROM savetask.jsp
	    case 90:
		is_update_query = 1;
		uname = (String) paramList.get(0);
		String tname = (String) paramList.get(1);
		String mcount = (String) paramList.get(2);
		query = query =  "INSERT INTO `task` (`uname`, `name`, `method_count`) VALUES ('" + uname + "', '" + tname + "', '" + mcount + "')" ;
		query_status = stmt.executeUpdate(query);
		break;

	    case 91:
		tname = (String) paramList.get(0);
		query = "SELECT max(id) as task_id FROM `task` WHERE `name` = '" + tname + "'";
		rs = stmt.executeQuery(query);
		break;


		// QUERIES FROM todo.jsp
	    case 100:
		query = "SELECT * from todo ORDER BY `id`";
		rs = stmt.executeQuery(query);
		break;


		// QUERIES FROM savemethod.jsp
	    case 110:
		is_update_query = 1;
		task_id = (String) paramList.get(0);
		String mname = (String) paramList.get(1);
		query =  "INSERT INTO `method` (`task_id`, `name`) VALUES ('" + task_id + "', '" + mname + "')" ;
		query_status = stmt.executeUpdate(query);
		break;


		// QUERIES FROM savemethod.jsp
	    case 120:
		task_id = (String) paramList.get(0);
		query = "SELECT * FROM `method` WHERE `task_id` =" +task_id;
		rs = stmt.executeQuery(query);
		break;


		// QUERIES FROM saveimage.jsp
	    case 130:
		is_update_query = 1;
		task_id = (String) paramList.get(0);
		String sname = (String) paramList.get(1);
		query = "INSERT INTO `set` (`task_id`, `name`) VALUES ('"+ task_id+"','"+ sname+"')";
		query_status = stmt.executeUpdate(query);
		break;

	    case 131:
		task_id = (String) paramList.get(0);
		query = "SELECT max(`id`) as set_id FROM `set` WHERE task_id='" + task_id + "'";
		rs = stmt.executeQuery(query);
		break;

	    case 132:
		is_update_query = 1;
		String set_id = (String) paramList.get(0);
		set_id = (String) paramList.get(1);
		query = "UPDATE `set` SET `name` = '"+ set_id +"' WHERE  `set`.`id` ="+set_id+"";
		query_status = stmt.executeUpdate(query);
		break;

	    case 133:
		task_id = (String) paramList.get(0);
		query = "SELECT * from method WHERE task_id=" + task_id;
		rs = stmt.executeQuery(query);
		break;

		// QUERIES FROM savename.jsp
	    case 140:
		uname = (String) paramList.get(0);
		query = "SELECT * from `user` WHERE `uname` = '"+ uname +"'";
		rs = stmt.executeQuery(query);
		break;
		
	    case 141:
		is_update_query = 1;
		uname = (String) paramList.get(0);
		String name =  (String) paramList.get(1);
		email = (String) paramList.get(2);
		passwd =  (String) paramList.get(3);
		query = "INSERT INTO `user` (`uname`, `name`, `email`, `password`, `age`) VALUES ('" + uname + "', '" + name + "', '" + email + "', '" + passwd + "', '22 ')" ;
		query_status = stmt.executeUpdate(query);

	    } // END OF CASES

	    XStream xstream = new XStream();
	    String xml="";
	    if(is_update_query == 1) {
		xml = xstream.toXML(query_status); // serialize to XML
	    }
	    else { //SELECT Query
		crs = new CachedRowSetImpl();
		crs.populate(rs);
		xml = xstream.toXML(crs); // serialize to XML
	    }
	    return xml;
	}

	
	catch(Exception e){
	    System.out.println("QNUM: " + queryNumber+ "ERROR in server side "+e.toString());
	}
	return uname_db;
		
    }
}
