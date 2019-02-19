import java.util.ArrayList;
import java.util.Collections;

/**
 * This class does processing needed to get the averages of NS&I premium bonds
 *
 * @author Iestyn Gage
 * @version 1 (06/09/2018)
 */
public class Application {

    final private int accountInTotal;     //the total amount of accounts
    final private int startingBonds;     //the starting amount of premium bonds for each account

    private ArrayList<Account> theAccounts;     //Array list of all the accounts

    /**
     * initialized the application, and Arraylist and sets the constant integers
     *
     */
    private Application(){
        theAccounts = new ArrayList<Account>();

        accountInTotal = 100;
        startingBonds = 10000;
    }

    /**
     * Creates the amount of accounts deemed by accountInTotal value in the arrayList
     */
    private void createAccounts(){
        for(int i = 0; i < accountInTotal; i++){
            try {
                Account tempAccount = new Account(startingBonds);
                theAccounts.add(tempAccount);
            } catch(Exception e){
                System.err.println("Error");
            }
        }
    }

    /**
     * This runs runYear cmd for each account in the arrayList
     */
    private void processAccounts(){
        for (Account aAccount: theAccounts) {
            aAccount.runYear();
        }

    }

    /**
     * This prints the information in theAccounts arrayList in a sorted manner. It then prints four different types of
     * averages(Mediem, Mean, Mode, Range)
     */
    private void outputAccounts(){
        float total = 0;
        Collections.sort(theAccounts);
        for(Account aAccount:theAccounts){
            System.out.println(aAccount.toString());
            total += aAccount.getPremiumBonds();
        }

        System.out.println("Mediem");
        calculateMediem();

        System.out.println("Mean:");
        calculateMean(total);

        System.out.println("Mode:");
        calculateMode();

        System.out.println("Range");
        calculateRange();
    }

    /**
     * This works out the mediem by finding the half value of accountsInTotal and using that as
     * index for theAccounts arrayList
     */
    private void calculateMediem(){
        int mediemNum = accountInTotal/2;
        Account mediemAccount = theAccounts.get(mediemNum);
        System.out.println(mediemAccount.toString());
    }

    /**
     * this calculates the mean by giving the total and dividing it by accountInTotal to find the average amount of bonds
     *
     * @param total
     */
    private void calculateMean(float total){
        float averageBonds = total/accountInTotal;
        float averageAER = (averageBonds-startingBonds)/(startingBonds/100);

        Account averageAccount = new Account(startingBonds,averageBonds,averageAER);
        System.out.println(averageAccount.toString());

    }

    /**
     * This finds the mode by making tally of number and saves it's results if it beats the previous one
     */
    private void calculateMode(){
        float[][] tally = new float[2][2];
        //The "best" so far
        tally[0][0] = 0;    //AER
        tally[0][1] = 0;    //amount
        //Works out the total
        tally[1][0] = 0;    //AER
        tally[1][1] = 0;    //amount

        for(Account aAccount: theAccounts){
            if(aAccount.getAer() == tally[1][0]){
                tally[1][1]++;
            }
            else{
                if(tally[1][1]>tally[0][1]){
                    tally[0][0] = tally[1][0];
                    tally[0][1] = tally[1][1];

                }
                tally[1][0] = aAccount.getAer();
                tally[1][1] = 0;
            }
        }
        float averageAER = tally[0][0];
        float averageBond = startingBonds + (startingBonds*averageAER/100);

        Account averageAccount = new Account(startingBonds,averageBond,averageAER);
        System.out.println(averageAccount.toString());

    }

    /**
     * This uses highest and lowest values in theAccounts ArrayList and uses values to caculate the range
     */
    private void calculateRange(){
        float minAER = theAccounts.get(0).getAer();
        float maxAER = theAccounts.get(accountInTotal-1).getAer();
        float averageAER = maxAER-minAER;
        float averageBond = startingBonds + (startingBonds*averageAER/100);

        Account averageAccount = new Account(startingBonds,averageBond,averageAER);
        System.out.println(averageAccount.toString());
    }

    /**
     * This iniliazed the class and then runs Create,process, and output cmds
     *
     * @param args
     */
    public static void main(String args[]){
        Application theApp = new Application();
        theApp.createAccounts();
        theApp.processAccounts();
        theApp.outputAccounts();

    }
}
