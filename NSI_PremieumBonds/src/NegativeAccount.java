public class NegativeAccount extends Exception {

    int errorMessage;
    NegativeAccount(int theError){
        errorMessage = theError;
    }

    @Override
    public String toString() {
        return "Bonds is negative " + errorMessage;
    }
}
