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
public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().createContact(new NewContact()
              .withGroup("test1")
              .withFirstName("Kleofas")
              .withSecondName("G")
              .withLastName("Filipiak")
              .withNickName("trr")
              .withTitle("Pan")
              .withCompany("comp")
              .withAddress("Zachodnia 12/b33-333 Krakow")
              .withMobile("484787545")
              .withEmail("hotmail@htomail.com"), true);
     // app.goTo().returnToHomepage();
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {
    Contacts before = app.contact().all();
    NewContact modifiedContact = before.iterator().next();
    NewContact contact = new NewContact()
            .withFirstName("TTT")
            .withSecondName("KKK")
            .withLastName("ZZZ")
            .withNickName("eew")
            .withTitle("Pani")
            .withCompany("aweerer")
            .withAddress("Lesna 80\n04-555 Warszawa")
            .withMobile("111111111")
            .withEmail("poczta@poczta.com");

    app.contact().modifyContact(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before. without(modifiedContact).withAdded(contact)));

  }
}
