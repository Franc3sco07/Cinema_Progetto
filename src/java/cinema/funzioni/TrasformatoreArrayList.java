package cinema.funzioni;

import java.util.ArrayList;

public class TrasformatoreArrayList {
    public static String arrayListToStringMat(ArrayList<String> lista) {
        return lista.toString().replaceAll(",", "").replaceAll("\\[|\\]", "");
    }

}
