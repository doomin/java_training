package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;

import java.io.File;

/**
 * Created by doomin on 13.12.16.
 */
public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By localizator) {
    wd.findElement(localizator).click();
  }

  protected void type(By localizator, String text) {
    click(localizator);
      if (text != null) {
        String existingText = wd.findElement(localizator).getAttribute("value");
        if (! text.equals(existingText)){
      wd.findElement(localizator).clear();
      wd.findElement(localizator).sendKeys(text);
    }
  }
  }

  protected void attach(By localizator, File file) {
    if (file != null) {
        wd.findElement(localizator).sendKeys(file.getAbsolutePath());
      }
    }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By localizator) {
     try{
       wd.findElement(localizator);
       return true;
     } catch (NoSuchElementException ex){
       return false;
     }
  }
}
