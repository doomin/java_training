package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NewContact;

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
    click(By.id("6"));
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

}
