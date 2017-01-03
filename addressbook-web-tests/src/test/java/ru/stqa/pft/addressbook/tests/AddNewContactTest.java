package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.List;

public class AddNewContactTest extends TestBase {

  @Test
  public void testAddNewContact() {
    List<NewContact> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new NewContact("test1", "Zenon", "K", "Ksantyp", "err", "Pan", "clubclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"), true);
    app.getNavigationHelper().returnToHomepage();
    List<NewContact> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
