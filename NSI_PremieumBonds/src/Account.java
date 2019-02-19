import java.util.Random;

/**
 * This class contains data for each account
 *
 * @author Iestyn Gage
 * @version 1 (06/09/2018)
 */

public class Account implements Comparable<Account> {

    final private int startingAmount;   //hold the starting amount of bonds, can't be changed

    private float premiumBonds;         //holds the current amount of bond
    private float aer;                  //holds the AER (Annual Equivalent Rate) of account

    private int year;                   //holds the amount of years the accounts been going

    Random rand = new Random(); //creates a random object to be used by the code.

    /**
     * Creates an account giving the premum and starting amount as given input
     * years is initialized as 0
     *
     * @param bond the amount of bonds the account starts with
     */
    Account(int bond) throws NegativeAccount{
        if(bond< 0){
            throw new NegativeAccount(bond);
        }
        premiumBonds = bond;
        startingAmount = bond;
        year = 0;
    }

    /**
     * Creates an account setting the starting amound, premium bonds and the aer
     *
     * @param theStartingBond the amount the account started with
     * @param theBond
     * @param theAER
     */
    Account(int theStartingBond,float theBond,float theAER){
        startingAmount = theStartingBond;
        premiumBonds = theBond;
        aer = theAER;
    }

    /**
     * Gets the amount the account started with
     *
     * @return startingAmount
     */
    public int getStartingAmount() {
        return startingAmount;
    }

    /**
     * Gets the account current amount of bonds
     *
     * @return premiumBonds
     */
    public float getPremiumBonds() {
        return premiumBonds;
    }

    /**
     * Gets the annual equivalent rate of the accoun#
     *
     * @return aer(annual equivalent rate)
     */
    public float getAer() {
        return aer;
    }

    /**
     * Gets the number of how many years the account has been opened for
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Runs runYear code 10 times
     */
    public void run10Year(){
        for(int i = 0; i<=10; i++){
            runYear();
        }
    }

    /**
     * Runs runLottery code 12 times adds 1 to year and then calculates AER
     */
    public void runYear(){
        for(int i =0; i<=12; i++){
            runLottery();
        }
        year++;
        calculateAER();
    }

    /**
     * calls runBond command for each bond there is
     */
    public void runLottery(){
        for(float i=premiumBonds;i>=0;i--){
            runBond();
        }
    }

    /**
     * Runs the chance of winning  each prize pool
     *
     * Doesn't complete all prize pools as computer can't generate the range on numbers needed.
     *  Although there so small that it most likely won't have been won
     */
    public void runBond(){
        //£25
        chance(24949,25);
        //£50
        chance(3124543,50);
        //£100
        chance(3124543,100);
        //£500
        chance(14300000,500);
        //£1000
        chance(42800000,1000);
        //£5000
        chance(793000000,5000);
    }

    /**
     *Runs random number generator, if the random number generator gets number 0, then prize been won
     *
     * @param odd range of random number generator goes to
     * @param prize the number added to premiumbonds if prize is won
     */
    public void chance(int odd, int prize){
        long dice = rand.nextInt(odd);
        if(dice==0){
            //System.out.println("win " + prize);
            premiumBonds += prize;
        }
    }

    /**
     * Calculates average AER of the account
     */
    private void calculateAER(){
        aer = ((premiumBonds-startingAmount)/(startingAmount/100)) / year;
    }

    /**
     * This is an inherited method from Comparable<Accounts>
     * The cmd checks to see if which one is "higher"
     *
     * @param a the account to compare to
     * @return if the Account given is higher lower or the same as current account
     */
    @Override
    public int compareTo(Account a){
        if(aer < a.aer){
            return -1;
        }
        else if(aer > a.aer){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * gives basic information for users about the account
     *
     * @return String of values in account
     */
    @Override
    public String toString() {
        return "StartingAmount: " + startingAmount + ", premiumBonds: " + premiumBonds + ", AER: " + aer;
    }
}

