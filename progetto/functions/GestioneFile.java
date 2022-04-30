package progetto.functions;

import progetto.model.Film;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GestioneFile {

    public static BufferedReader openFile(String path) {
        try {
            FileReader w = new FileReader(path);
            BufferedReader in = new BufferedReader(w);

            // w.close(); // questo lo puoi sistemare con un try with resource
            // in.close();

            return in;
        } catch (IOException e) {
        }

        return null;
    }

    public static String writeFile(String path, ArrayList<String> dati) {
        try {
            FileWriter w = new FileWriter(path);
            BufferedWriter in = new BufferedWriter(w);

            for (Iterator<String> iterator = dati.iterator(); iterator.hasNext(); ) {
                String line = iterator.next();

                if (iterator.hasNext()) {
                    in.write(line);
                    in.newLine();
                } else {
                    in.write(line);
                }
            }

            in.close();
            return "ok";

        } catch (IOException e) {
        }

        return "errore in scrittura";
    }

    public static Boolean readExceptID(String ID, String path, ArrayList<String> dati) {
        try {
            BufferedReader file = GestioneFile.openFile(path);

            boolean trovato = false;

            String l;
            while ((l = file.readLine()) != null) {
                String[] line = l.split(",");

                if (!line[0].equals(ID)) {
                    dati.add(l);
                    //System.out.println(l);
                } else {
                    trovato = true;
                }
            }
            return trovato;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return false;
    }

}


