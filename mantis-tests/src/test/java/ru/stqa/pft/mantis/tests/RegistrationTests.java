package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by doomin on 1/29/17.
 */
public class RegistrationTests extends TestBase{

    @Test
    public void testRegistration(){
        app.registration().start("user1", "user1@loalhost.localdomain");

    }
}
