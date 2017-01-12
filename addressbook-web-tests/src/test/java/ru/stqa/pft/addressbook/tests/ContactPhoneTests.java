package ru.stqa.pft.addressbook.tests;

import org.apache.xerces.impl.xpath.regex.Match;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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

        assertThat(contact.getHome(), equalTo(cleaned(contactInfoFromEditForm.getHome())));
        assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
        assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
