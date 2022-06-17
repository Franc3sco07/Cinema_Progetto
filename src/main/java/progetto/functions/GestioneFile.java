package progetto.functions;

import progetto.model.Film;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GestioneFile {

    private static final String PERCORSOGRAFICA = "src/main/java/progetto/elementiGrafici/";
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

    public static Boolean salvaImmagine(Image immagine, String nomeFile ) {
        String elementiGraficiPath = "src/main/java/progetto/elementiGrafici/";
        try {
            BufferedImage immagineBuffered = new BufferedImage(immagine.getWidth(null), immagine.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            immagineBuffered.getGraphics().drawImage(immagine,0,0,null );
            ImageIcon icon = new ImageIcon(immagineBuffered);
            File fileImmagine = new File(elementiGraficiPath+nomeFile) ;
            fileImmagine.createNewFile();
            return ImageIO.write(immagineBuffered,"png",fileImmagine);
        } catch (IOException e) {
            System.out.println("ERRORE IO");
            return false;
        }

    }

    public static ImageIcon apriImmagine(String nomeFile ) {
        //String elementiGraficiPath = "src/main/java/progetto/elementiGrafici/";

            return new ImageIcon(nomeFile);


    }

}


