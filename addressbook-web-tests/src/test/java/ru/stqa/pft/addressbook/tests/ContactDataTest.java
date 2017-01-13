package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dominik on 2017-01-12.
 */
public class ContactDataTest extends TestBase {

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
                    .withEmail("hotmail@htomail.com")
                    .withEmail2("asd@asd.com")
                    .withEmail3("weerewr@sdfsdf"), true);
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().gotoToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays
                .asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDataTest::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhone(String phone) {
        return phone.replaceAll("\\s+", "").replaceAll("[-()]", "");
    }

    @Test
    public void testContactEmails() {
        app.goTo().gotoToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays
                .asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDataTest::cleanedEmail)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedEmail(String email) {
        return email.replaceAll("\\s+", "");
    }

    @Test
    public void testAddress() {
        app.goTo().gotoToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(cleanedAddress(contact.getAddress()), equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays
                .asList(contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDataTest::cleanedAddress)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedAddress(String addres) {
        return addres.replaceAll("\\s+", "");
    }

    @Test (enabled = false)
    public void testDetails() {
        app.goTo().gotoToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);
       // assertThat(cleanedContactDetails(contact.getAllDetails()), equalTo(mergeContactDetails(contactInfoFromDetailsPage)));

    }
}