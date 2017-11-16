package echsupport.rattrap;
import org.junit.Test;
import echsupport.rattrap.model.Model;
import static org.junit.Assert.*;
import echsupport.rattrap.model.AccountManager;
import echsupport.rattrap.controller.LoginActivity;

/**
 * Created by Emilee on 11/15/17.
 */


public class IsPasswordValidTest {
    private Model model = Model.getInstance();
    public AccountManager accMan = model.getAccountManager();
    LoginActivity loginTest = new LoginActivity();
    @Test
    public void testingIsPasswordValid() {
        accMan.addAccount("Test", "test@test.com", "testPassword");
        assertTrue("Existing password not found. Test failed.", loginTest.isPasswordValid("testPassword", "test@test.com"));
        assertFalse("Non-existent password found. Test failed.", loginTest.isPasswordValid("randomTest8768yughcf76uPassword", "test@test.com"));
    }
}