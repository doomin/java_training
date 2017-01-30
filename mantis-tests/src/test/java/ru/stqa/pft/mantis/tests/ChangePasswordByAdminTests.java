package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.sql.*;
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

            String user = "";
            String newpassword = "newpassword";
            String email = "";

            Connection conn = null;

            try {

                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
                Statement st = conn.createStatement();


                    ResultSet rs = st.executeQuery("select username, email from mantis_user_table where username <> 'administrator' order by rand() limit 1");

                    while (rs.next()) {
                        user = rs.getString(1);
                        email = rs.getString(2);
                    }
                    rs.close();
                    st.close();
                    conn.close();


                } catch(SQLException ex){
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }

            app.passwordChange().changePasswordByAdmin(user);

            List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
            String passwordChangeLink = findConfirmationLink(mailMessages, email);

            app.passwordChange().finish(passwordChangeLink, newpassword);
            assertTrue(app.newSession().login(user, newpassword));

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

