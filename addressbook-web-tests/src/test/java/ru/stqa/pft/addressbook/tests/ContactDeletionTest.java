package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContact;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by doomin on 13.12.16.
 */
public class ContactDeletionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().createContact(new NewContact()
              .withGroup("test1")
              .withFirstName("Grzegorz")
              .withSecondName("V")
              .withLastName("Markowiak")
              .withNickName("trr")
              .withTitle("Pan")
              .withCompany("comp")
              .withAddress("Zachodnia 12/b33-333 Krakow")
              .withMobile("484787545")
              .withEmail("hotmail@htomail.com"), true);
     }
  }
  @Test //(enabled = false)
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    NewContact deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
