package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by doomin on 12.12.16.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void goToGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void gotoAddNewContact() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void returnToHomepage() {
    wd.findElement(By.linkText("home page")).click();
  }
}
