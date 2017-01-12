package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContact;

import javax.xml.ws.WebEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

  public void fillNewContactForm(NewContact newContact, boolean creation) {

    type(By.name("firstname"),newContact.getFirstname());
    type(By.name("middlename"),newContact.getSecondname());
    type(By.name("lastname"),newContact.getLastname());
    type(By.name("nickname"),newContact.getNickname());
    type(By.name("title"),newContact.getTitle());
    type(By.name("company"),newContact.getCompany());
    type(By.name("address"),newContact.getAddress());
    type(By.name("mobile"),newContact.getMobile());
    type(By.name("email"),newContact.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContact.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

    public void returnToHome() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

    public void delete(NewContact newContact) {
        selectContactById(newContact.getId());
        deleteSelectedContact();
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
  }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
       }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<NewContact> all() {
      Contacts contacts = new Contacts();
    List<WebElement> trCollection = wd.findElements(By.tagName("tr"));
     // List<WebElement> tdCollection = wd.findElements(By.tagName("td"));

      for (WebElement trElement : trCollection) {

         List<WebElement> cells = trElement.findElements(By.tagName("td"));
          String firstname = cells.get(3).getText();
          String lastname = cells.get(2).getText();
          int id = Integer.getInteger(cells.get(1).getAttribute("id"));

          /*
          List<WebElement> cellsFirstName = trElement.findElements(By.cssSelector("td:nth-child(3)"));
          List<WebElement> cellsLastName = trElement.findElements(By.cssSelector("td:nth-child(2)"));
          List<WebElement> cellsId = trElement.findElements(By.tagName("input"));
          String firstname = "";
          String lastname = "";
          int id = 0;

          for (WebElement element1 : cellsFirstName) {
              firstname = element1.getText();
          }
          for (WebElement element2 : cellsLastName) {
              lastname = element2.getText();
          }
          for (WebElement element3 : cellsId) {
              id = Integer.parseInt(element3.findElement(By.tagName("input")).getAttribute("value"));
          }
          */

          contacts.add(
                  new NewContact()
                          .withId(id)
                          .withFirstName(firstname)
                          .withLastName(lastname));
      }
    //contacts.remove(0);
    return (List<NewContact>) contacts;
  }
}
