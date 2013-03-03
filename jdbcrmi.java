package webp_rmi;

import java.rmi.*;
import java.util.ArrayList;

public interface jdbcrmi extends Remote
{
    public String ExecuteSql(int queryNumber, ArrayList paramList) throws RemoteException;
}
