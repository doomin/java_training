package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class AddNewContactTest extends TestBase{
  FirefoxDriver wd;

   @Test
  public void testAddNewContact() {

    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().fillNewContactForm(new NewContact("Kazimierz", "Przerwa", "Tetmajer", "tadziu", "Pan", "kanjiclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"));
    app.getContactHelper().submitNewContact();
    app.getNavigationHelper().returnToHomepage();
  }










  @AfterMethod
  public void tearDown() {
    wd.quit();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
