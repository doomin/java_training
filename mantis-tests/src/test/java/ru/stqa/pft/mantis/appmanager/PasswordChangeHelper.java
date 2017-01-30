package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;
import java.sql.*;

/**
 * Created by root on 1/30/17.
 */
public class PasswordChangeHelper extends HelperBase{

    public PasswordChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void changePasswordByAdmin(String user) throws IOException {
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
        click(By.cssSelector("input[value='Login']"));
        click(By.cssSelector(String.format("a[href='manage_user_create_page.php']")));
        click(By.linkText("Manage Users"));

        type(By.name("username"), user);
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
