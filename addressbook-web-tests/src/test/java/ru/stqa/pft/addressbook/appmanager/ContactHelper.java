package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.ArrayList;
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

  public void deleteContact() {
    click(By.name("selected[]"));
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
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
    click(By.linkText("add new"));
  }


  public void createContact(NewContact contact, boolean b) {
    gotoAddNewContact();
    fillNewContactForm(contact,b);
    submitNewContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<NewContact> getContactList() {
    List<NewContact> contacts = new ArrayList<NewContact>();
    List<WebElement> tr_collection = wd.findElements(By.tagName("tr"));

    for (WebElement trElement : tr_collection){
      List<WebElement> cellsFirstName = trElement.findElements(By.cssSelector("td:nth-child(3)"));
      List<WebElement> cellsLastName = trElement.findElements(By.cssSelector("td:nth-child(2)"));
      String firstname = "";
      String lastname = "";

      for (WebElement element1 : cellsFirstName) {
        firstname = element1.getText();
      }
      for (WebElement element2 : cellsLastName) {
        lastname = element2.getText();
      }
      NewContact contact = new NewContact(null, firstname, null, lastname, null, null, null, null, null, null);
      contacts.add(contact);
        }

    return contacts;
  }


}
