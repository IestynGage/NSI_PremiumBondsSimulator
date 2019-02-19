import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void createNormalAccount(){
        int startingAmount = 10000;
        try{
            Account newAccount = new Account(startingAmount);
            int result = (int) newAccount.getPremiumBonds();


            int expectedResult = startingAmount;
            assertEquals(expectedResult,result);

        } catch(NegativeAccount e){
            System.err.println("Negative account balance");
        };
    }

    @Test(expected = NegativeAccount.class)
    public void createRejectedAccount() throws NegativeAccount {
        int startingAmount = -40;

        Account newAccount = new Account(startingAmount);
    }

}