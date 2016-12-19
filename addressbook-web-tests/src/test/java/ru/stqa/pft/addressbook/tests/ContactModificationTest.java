package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

/**
 * Created by doomin on 13.12.16.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new NewContact("test11", "Kleofas", "Kacper", "Kazmirz", "elo", "Pan", "clubclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"), true);
      app.getNavigationHelper().returnToHomepage();
    }
    app.getContactHelper().viewContactDetails();
    app.getContactHelper().modifyContactDetails();
    app.getContactHelper().fillNewContactForm(new NewContact(null, "Tomasz", "Wio", "Kulfon", "ziutek", "Pan", "auto", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"), false);
    app.getContactHelper().submitContactModification();
  }
}
