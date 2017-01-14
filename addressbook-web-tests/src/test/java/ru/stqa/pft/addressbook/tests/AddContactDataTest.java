package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactDataTest extends TestBase {



  @Test //(enabled = false)
  public void testAddNewContact() {
    app.goTo().gotoToHomepage();
    Contacts before = app.contact().all();
      File photo = new File("src/test/resources/photo1.jpg");
      ContactData contact = new ContactData()
            .withGroup("test1")
            .withFirstName("Zennon")
            .withMiddlename("F")
            .withLastName("Grabarczyk")
            .withNickName("err")
            .withTitle("Pan")
            .withCompany("clubclub")
            .withAddress(" Wschodnia 15\n04-333 Putki")
            .withMobile("666555888")
            .withEmail("mail@mail.com")
            .withPhoto(photo);

    app.contact().createContact(contact,true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
    public void testCurrentDir(){
      File currentDir = new File(".");
      System.out.println(currentDir.getAbsolutePath());
      File photo = new File("src/test/resources/photo1.jpg");
      System.out.println(photo.getAbsolutePath());
      System.out.println(photo.exists());
  }
}
