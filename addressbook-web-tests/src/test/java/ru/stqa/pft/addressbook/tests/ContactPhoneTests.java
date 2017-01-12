package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Dominik on 2017-01-12.
 */
public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData()
                    .withGroup("test1")
                    .withFirstName("Maciek")
                    .withSecondName("V")
                    .withLastName("Zbiec")
                    .withNickName("trr")
                    .withTitle("Pan")
                    .withCompany("comp")
                    .withAddress("Zachodnia 12/b33-333 Krakow")
                    .withHome("111")
                    .withMobile("222")
                    .withWork("333")
                    .withEmail("hotmail@htomail.com"), true);
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().gotoToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    }

}
