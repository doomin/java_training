package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

/**
 * Created by root on 1/30/17.
 */
public class PasswordChangeHelper extends HelperBase{

    public PasswordChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void changePasswordByAdmin(String username) throws IOException {
        //wd.get(app.getProperty("web.baseUrl" + "/login_page.php"));
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
      //  app.newSession().login("administrator", "root");
        click(By.cssSelector(String.format("a[href='manage_user_create_page.php']")));
        click(By.cssSelector(String.format("a[href='/mantisbt-2.0.0/manage_user_create_page.php']")));
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Manage User']"));
        click(By.cssSelector("input[value='New Password']"));
    }

    public void finish(String passwordChangeLink, String oldPassword, String newPassword) {
        wd.get(passwordChangeLink);
        type(By.name("old_password"), oldPassword);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.xpath("//*[@id='account-update-form']/fieldset/span/button/span"));
    }
}
