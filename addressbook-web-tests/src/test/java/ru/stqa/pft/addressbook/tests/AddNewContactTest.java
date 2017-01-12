package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContactTest extends TestBase {



  @Test //(enabled = false)
  public void testAddNewContact() {
    Contacts before = app.contact().all();
    NewContact contact = new NewContact()
            .withGroup("test1")
            .withFirstName("Zennon")
            .withSecondName("F")
            .withLastName("Grabarczyk")
            .withNickName("err")
            .withTitle("Pan")
            .withCompany("clubclub")
            .withAddress(" Wschodnia 15\n04-333 Putki")
            .withMobile("666555888")
            .withEmail("mail@mail.com");

    app.contact().createContact(contact,true);
    app.goTo().returnToHomepage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
