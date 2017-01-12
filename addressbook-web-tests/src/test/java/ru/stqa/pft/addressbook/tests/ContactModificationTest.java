package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Comparator;
import java.util.List;

/**
 * Created by doomin on 13.12.16.
 */
public class ContactModificationTest extends TestBase {

  @Test //(enabled = false)
  public void testContactModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new NewContact("test11", "Kleofas", "Kacper", "Kazmirz", "elo", "Pan", "clubclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"), true);
      app.goTo().returnToHomepage();
    }
    List<NewContact> before = app.getContactHelper().getContactList();
    app.getContactHelper().viewContactDetails();
    app.getContactHelper().modifyContactDetails();
    NewContact contact = new NewContact(null, "Euzebiusz", "Wio", "Smolarek", "ziutek", "Pan", "auto", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail");
    app.getContactHelper().fillNewContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.goTo().returnToHomepage();
    List<NewContact> after = app.getContactHelper().getContactList();

    before.remove(0 );
    before.add(contact);
    Comparator<? super NewContact> byName = (c1, c2) -> c1.getLastname().compareTo(c2.getLastname());
    before.sort(byName);
    after.sort(byName);
    Assert.assertEquals(before, after);
  }
}
