package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

/**
 * Created by doomin on 13.12.16.
 */
public class ContactModificationTest extends TestBase{

  @Test
  public void testContactModification(){

  app.getContactHelper().viewContactDetails();
  app.getContactHelper().modifyContactDetails();
  app.getContactHelper().fillNewContactForm(new NewContact(null,"Włodzimierz", "Lato", "Tetmajer", "tadziu", "Pan", "kanjiclub", "Wschodnia 15\n04-333 Putki", "48444888666", "kazmisztet@mail.mail"), false);
  app.getContactHelper().submitContactModification();
  }
}
