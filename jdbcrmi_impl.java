package webp_rmi;

import java.rmi.*;
import java.io.*;
import java.rmi.server.*;
import webp_rmi.*;
import java.sql.*;
import java.util.*;
import java.lang.*;
import java.util.ArrayList;


public class jdbcrmi_impl extends UnicastRemoteObject implements jdbcrmi
{
	public jdbcrmi_impl() throws RemoteException
	{
		super();
	}
    public String ExecuteSql(int queryNumber, ArrayList paramList) throws RemoteException
	{
		jdbcrmiConnection jrcon=new jdbcrmiConnection();
		String result = jrcon.execute(queryNumber, paramList);
		return result;
	}
}
