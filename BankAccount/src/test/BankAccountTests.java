
package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import app.*;
public class BankAccountTests {
    
    BankAccount account;

    @Before
    public void setUp() throws Exception {
        account = new BankAccount(27, "default");
    }
    
    @Test
    public void testDeposit() {
        assertEquals(27, account.balance, 0.1);
        account.deposit(1);
        assertEquals(28, account.balance, 0.1);
    }
    
    @Test
    public void testOwner1() {
        assertEquals(account, new BankAccount(27, "default"));
    }

    @Test
    public void testOwner2() {
        assertTrue(account == new BankAccount(27, "default"));
    }

    @Test
    public void testOwner3() {
        assertTrue(account.equals(new BankAccount(27, "default")));
    }
    
}
