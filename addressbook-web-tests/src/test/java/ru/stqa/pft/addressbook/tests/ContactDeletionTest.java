package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

/**
 * Created by doomin on 13.12.16.
 */
public class ContactDeletionTest extends TestBase{

  @Test
  public void testContactDeletion() {
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new NewContact("test11","Kleofas", "Kacper", "Kazmirz", "elo", "Pan", "clubclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"), true);
      app.getNavigationHelper().returnToHomepage();
    }
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().closePopUp();
    app.getNavigationHelper().returnToHome();
  }
}
