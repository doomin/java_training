package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.List;

/**
 * Created by doomin on 12.12.16.
 */
public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewContactForm(NewContact contact, boolean creation) {

    type(By.name("firstname"),contact.getFirstname());
    type(By.name("middlename"),contact.getSecondname());
    type(By.name("lastname"),contact.getLastname());
    type(By.name("nickname"),contact.getNickname());
    type(By.name("title"),contact.getTitle());
    type(By.name("company"),contact.getCompany());
    type(By.name("address"),contact.getAddress());
    type(By.name("mobile"),contact.getMobile());
    type(By.name("email"),contact.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
    public void returnToHomepage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("strona główna"));
    }

    public void returnToHome() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("strona główna"));
    }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
    public void closePopUp(){
        wd.switchTo().alert().accept();
    }

    public void modifyContact(NewContact contact){
        viewContactDetails();
        modifyContactDetails();
        fillNewContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomepage();
    }
    public void delete(NewContact contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        closePopUp();
        contactCache = null;
        returnToHome();
    }

  public void viewContactDetails() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
  }

  public void modifyContactDetails() {
    click(By.name("modifiy"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void gotoAddNewContact() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))
            && isElementPresent(By.name("new_group"))){
      return;
    }
    click(By.linkText("nowy wpis"));
  }

  public void createContact(NewContact contact, boolean b) {
    gotoAddNewContact();
    fillNewContactForm(contact,b);
    submitNewContact();
    contactCache = null;
    returnToHomepage();
  }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
       }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

    private Contacts contactCache = null;

  public Contacts all() {
      if (contactCache != null){
          return new Contacts(contactCache);
      }
      contactCache = new Contacts();
    List<WebElement> trCollection = wd.findElements(By.name("entry"));
      for (WebElement trElement : trCollection) {
         List<WebElement> cells = trElement.findElements(By.cssSelector("td"));
          String firstname = cells.get(2).getText();
          String lastname = cells.get(1).getText();
          int id = Integer.parseInt(trElement.findElement(By.tagName("input")).getAttribute("id"));

          contactCache.add(
                  new NewContact()
                          .withId(id)
                          .withFirstName(firstname)
                          .withLastName(lastname));
      }
    return new Contacts(contactCache);
  }


    public int count() {
      return wd.findElements(By.name("entry")).size();
        }
}
