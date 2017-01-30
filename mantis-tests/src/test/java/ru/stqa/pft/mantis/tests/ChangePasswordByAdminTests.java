package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by root on 1/30/17.
 */
public class ChangePasswordByAdminTests extends TestBase{


        @BeforeMethod
        public void startMailServer(){
            app.mail().start();
        }

        @Test
        public void testChangePasswordByAdmin() throws IOException, MessagingException {
            //select username and email from DB
            String username = "";
            String email = "";
            String oldPassword = "";
            String newPassword = "";

            app.passwordChange().changePasswordByAdmin(username);

            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String passwordChangeLink = findConfirmationLink(mailMessages, email);

            app.passwordChange().finish(passwordChangeLink, oldPassword, newPassword);

            assertTrue(app.newSession().login(username, newPassword));

        }

        private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
            MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
            VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
            return regex.getText(mailMessage.text);
        }

        @AfterMethod(alwaysRun = true)
        public void stopMailServer(){
            app.mail().stop();
        }
    }

