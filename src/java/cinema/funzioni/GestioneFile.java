package cinema.funzioni;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;

public class GestioneFile {

    private static final String PERCORSOGRAFICA = "Immagini/";
    private static final String immagineErrore = "errore.png";

    public static BufferedReader openFile(String path) throws IOException {
        try {
            FileReader w = new FileReader(path);
            BufferedReader in = new BufferedReader(w);
            return in;
        } catch (IOException e) {
        }

        return null;
    }

    public static String writeFile(String path, Collection<String> dati) {
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

    public static Boolean readExceptID(String ID, String path, Collection<String> dati) {
        try {
            BufferedReader file = GestioneFile.openFile(path);
            return dati.addAll( file.lines().parallel()
                    .filter(s -> !s.split(",")[0].equals(ID))
                    .toList()
            );
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return false;
    }

    public static Boolean salvaImmagine(Image immagine, String nomeFile) {
        try {
            BufferedImage immagineBuffered = new BufferedImage(immagine.getWidth(null), immagine.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            immagineBuffered.getGraphics().drawImage(immagine, 0, 0, null);
            ImageIcon icon = new ImageIcon(immagineBuffered);
            File fileImmagine = new File(PERCORSOGRAFICA + nomeFile);
            fileImmagine.createNewFile();
            return ImageIO.write(immagineBuffered, "png", fileImmagine);
        } catch (IOException e) {
            System.out.println("ERRORE IO");
            return false;
        }

    }

    public static ImageIcon apriImmagine(String nomeFile) {
        ImageIcon icon = new ImageIcon(PERCORSOGRAFICA + nomeFile);
        if (icon.getIconWidth() != -1) {
            return icon;
        } else {
            return new ImageIcon(PERCORSOGRAFICA + immagineErrore);
        }

    }

}


