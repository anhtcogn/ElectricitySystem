package com.electricitysystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    public void testLoginSuccess() {
        Login login = new Login();
        boolean result = login.authenticate("admin", "Password123");
        assertTrue(result);
    }

    @Test
    public void testInvalidPassword() {
        Login login = new Login();
        boolean result = login.authenticate("admin123", "wrongpassword");
        assertFalse(result);
    }

    @Test
    public void testInvalidUsername() {
        Login login = new Login();
        boolean result = login.authenticate("invalidusername", "Password123");
        assertFalse(result);
    }

    @Test
    public void testEmptyUsername() {
        Login login = new Login();
        boolean result = login.authenticate("", "Password123");
        assertFalse(result);
    }

    @Test
    public void testEmptyPassword() {
        Login login = new Login();
        boolean result = login.authenticate("admin", "");
        assertFalse(result);
    }

    @Test
    public void testInvalidFormat() {
        Login login = new Login();
        boolean result = login.authenticate("admin123!", "Password123");
        assertFalse(result);
    }

}



