package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Comparator;
import java.util.List;

public class AddNewContactTest extends TestBase {

  @Test
  public void testAddNewContact() {
    List<NewContact> before = app.getContactHelper().getContactList();

    NewContact contact = new NewContact("test1", "Karol", "K", "Karolkiewicz", "err", "Pan", "clubclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail");
    app.getContactHelper().createContact(contact, true);
    app.getNavigationHelper().returnToHomepage();

    List<NewContact> after = app.getContactHelper().getContactList();

    before.add(contact);
    Comparator<? super NewContact> byName = (c1, c2) -> c1.getLastname().compareTo(c2.getLastname());
    before.sort(byName);
    after.sort(byName);
    Assert.assertEquals(before, after);
  }
}
