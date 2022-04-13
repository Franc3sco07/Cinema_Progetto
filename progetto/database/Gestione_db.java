package progetto.database;

import java.io.*;
import java.util.*;

public class Gestione_db {
    private String path = ".\\progetto\\database\\";

    public BufferedReader getTable(String tableName) {

        try{
            FileReader w = new FileReader(path + tableName);
            BufferedReader in = new BufferedReader(w);

            // w.close(); // questo lo puoi sistemare con un try with resource
            // in.close();

            return in;
        }
        catch (FileNotFoundException e){}
        catch (IOException e){}

        return null;
    }

    public String deleteRow(String ID, String tableName ){
        return null;
    }

    public String modifyRow(String ID, String tableName, List<String> modifyElement ){
        return null;
    }

    public String insertRow(String tableName, List<String> insertElement){
        return null;
    }

}
