package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by doomin on 12.12.16.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoAddNewContact() {
    click(By.linkText("add new"));
  }

  public void returnToHomepage() {
    click(By.linkText("home page"));
  }
}
