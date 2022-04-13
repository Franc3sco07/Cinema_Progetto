package progetto.functions;

public class ValidatoreCampi {


    public  boolean isValidEmail(String email) {
        //dopo la @ non puoi inserire numeri
        if (!email.toLowerCase().matches("((\\w+)(\\.{0,1}))+@{1}(([a-zA-z])+(\\.))+[a-zA-z]{2,3}")) {
            return false; //verificare espressione regalare
        }// l'email deve essere minuscola
        return true;
    }

    public  boolean isValidString(String stringa){ //verificare nome e cognome, stringa costituita solo da caratteri
        // if (!stringa.matches("[a-zA-Z0 \\s]*")) {
        if (!stringa.matches("[a-zA-Z ].{1,20}")) {
            return false;
        }
        return true;
    }

    public boolean isValidPassword(String pass){
        if (!pass.matches("((?=.+\\d)(?=.+[a-z])(?=.+[A-Z])(?=.+[&%@#?!$]).{6,20})")) {//\d{2,}[&%@#?!$]{1,}
            return false; //[a-zA-Z]{6,}[\d]{2,}
        }//CiaoAA99&, cia77o&AA
        return true;
    }


    public boolean isValidCodiceFiscale(String codiceFiscale){
        if (!codiceFiscale.matches("[A-Z0-9]{16}")) {
            return false;
        }
        return true;
    }



    public  boolean isValidNickname(String nickname){
        if (!nickname.matches("[\\w]")) {
            return false;
        }
        return true;
    }

    public  boolean isValidDate(String data){
        if (!data.matches("(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d")) {
            return false;
        }
        return true;
    }

    public boolean isValidEta( String eta ) {

        // String agepattern =" \\d"  ok;"
        if (!eta.matches("^.*[0-9]$")){
            return false;
        }
        return true;
    }

    public static boolean isNumeric1(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /*
        public boolean isvalidationFoneNumber( String eta){

            // String agepattern =" \\d"  ok;"
            if (!eta.matches("\\d{3}-\\d{7}")){
                return false;
            }
            return true;
        }
    */
    public static boolean isNumeric(int j) {
        if ( j== 0) {
            return false;
        }
        return true;
    }
}



