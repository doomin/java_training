package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class AddNewContactTest extends TestBase {

  @Test
  public void testAddNewContact() {

    app.getContactHelper().createContact(new NewContact("test11", "Grzegorz", "K", "Bidny", "err", "Pan", "clubclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"), true);
    app.getNavigationHelper().returnToHomepage();
  }
}
