package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

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

  public void fillNewContactForm(ContactData contact, boolean creation) {

    type(By.name("firstname"),contact.getFirstname());
    type(By.name("middlename"),contact.getMiddlename());
    type(By.name("lastname"),contact.getLastname());
    type(By.name("nickname"),contact.getNickname());
    type(By.name("title"),contact.getTitle());
    type(By.name("company"),contact.getCompany());
    type(By.name("address"),contact.getAddress());
    type(By.name("address2"),contact.getAddress());
    type(By.name("home"),contact.getHome());
    type(By.name("mobile"),contact.getMobile());
    type(By.name("work"),contact.getWork());
    type(By.name("email"),contact.getEmail());
    type(By.name("email2"),contact.getEmail());
    type(By.name("email3"),contact.getEmail());
//    attach(By.name("photo"),contact.getPhoto());

    if (creation){
      //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
    public void returnToHomepage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }

    public void returnToHome() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.cssSelector(String.format("a[href^='./']")));
        }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
    public void closePopUp(){
        wd.switchTo().alert().accept();
    }

    public void modifyContact(ContactData contact){
        //viewContactDetails(contact.getId());
        modifyContactDetails();
        fillNewContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHome();
    }
    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        closePopUp();
        contactCache = null;
        returnToHome();
    }

  public void viewContactDetails(int id) {

      WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
      WebElement row = checkbox.findElement(By.xpath("./../.."));
      List<WebElement> cells = row.findElements(By.tagName("td"));
      cells.get(6).findElement(By.tagName("a")).click();
  }

  public void modifyContactDetails() {
    click(By.name("modifiy"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void gotoAddNewContact() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Edytuj / dodaj wpis do książki adresowej")
            && isElementPresent(By.name("submit"))
            && isElementPresent(By.name("new_group"))){
      return;
    }
    //click(By.linkText("nowy wpis"));
    click(By.cssSelector("a[href = 'edit.php']"));
  }

  public void createContact(ContactData contact, boolean b) {
    gotoAddNewContact();
    fillNewContactForm(contact,b);
    submitNewContact();
    contactCache = null;
    returnToHome();
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
          List<WebElement> cells = trElement.findElements(By.tagName("td"));
          int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
          String firstname = cells.get(2).getText();
          String lastname = cells.get(1).getText();
          String allPhones = cells.get(5).getText();
          String allEmails = cells.get(4).getText();
          String address = cells.get(3).getText();

          contactCache.add(
                  new ContactData()
                          .withId(id)
                          .withFirstName(firstname)
                          .withLastName(lastname)
                          .withAddress(address)
                          .withAllPhones(allPhones)
                          .withAllEmails(allEmails));
      }
    return new Contacts(contactCache);
  }

    public int count() {
      return wd.findElements(By.name("entry")).size();
        }

    public ContactData infoFromEditForm(ContactData contact) {

      initContactModificationById(contact.getId());
      String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
      String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
      String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
      String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
      String company = wd.findElement(By.name("company")).getAttribute("value");
      String title = wd.findElement(By.name("title")).getAttribute("value");
      String home = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      String address = wd.findElement(By.name("address")).getAttribute("value");
      String address2 = wd.findElement(By.name("address2")).getAttribute("value");
      String email = wd.findElement(By.name("email")).getAttribute("value");
      String email2 = wd.findElement(By.name("email2")).getAttribute("value");
      String email3 = wd.findElement(By.name("email3")).getAttribute("value");
      wd.navigate().back();
      return new ContactData()
              .withId(contact.getId())
              .withFirstName(firstname)
              .withMiddlename(middlename)
              .withLastName(lastname)
              .withNickName(nickname)
              .withCompany(company)
              .withTitle(title)
              .withHome(home)
              .withMobile(mobile)
              .withWork(work)
              .withAddress(address)
              .withAddress2(address2)
              .withEmail(email)
              .withEmail2(email2)
              .withEmail3(email3);
    }

    private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

   // wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
   // wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();
   // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']"))).click();
    }

    private void initContactDetailsById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(6).findElement(By.tagName("a")).click();
    }

    public ContactData infoFromDetails(ContactData contact) {
        initContactDetailsById(contact.getId());
        String details = wd.findElement(By.cssSelector("#content")).getText()
                    .replaceAll("H:", "")
                    .replaceAll("M:", "")
                    .replaceAll("W:", "")
                    .replaceAll("\\s+", "")
                    .replaceAll("Członekgrupy: ", "")
                    .replaceAll("Memberof: ", "");

        if (isElementPresent((By.cssSelector(String.format("a[href^='./index.php?group']"))))){
            details = details
                    .replaceAll(wd.findElement(By.cssSelector(String.format("a[href^='./index.php?group']"))).getText().replaceAll("\\s+",""), "")
                    .replaceAll("Członekgrupy:", "")
                    .replaceAll("Członekgrupy: ", "")
                    .replaceAll("Memberof: ", "")
                    .replaceAll("Memberof:", "");

        }
        wd.navigate().back();
        return new ContactData().withAllDetails(details);
        }

    public void removeFromGroup(ContactData contact, GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
        wd.findElement(By.cssSelector(String.format("input[value='%s']",contact.getId()))).click();
        click(By.xpath("//div[@id='content']/form[2]/div[3]/input"));
        returnToHome();
    }

    public void addToGroup(ContactData contact, GroupData group){
        wd.findElement(By.cssSelector(String.format("input[value='%s']",contact.getId()))).click();
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        click(By.xpath("//div[@id='content']/form[2]/div[4]/input"));
        returnToHome();
    }
}

