package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class AddNewContactTest extends TestBase{

   @Test
  public void testAddNewContact() {

    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().fillNewContactForm(new NewContact("test11","Zenon", "Zenon", "Zenon", "tadziu", "Pan", "kanjiclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"), true);
    app.getContactHelper().submitNewContact();
    app.getNavigationHelper().returnToHomepage();
  }
}
