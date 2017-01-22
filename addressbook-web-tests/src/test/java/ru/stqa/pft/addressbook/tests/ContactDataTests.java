package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
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
public class ContactDataTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoToHomepage();
            app.contact().createContact(new ContactData()
                    .withGroup("test1")
                    .withFirstName("Maciek")
                    .withMiddlename("V")
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
    public void testContactPhones(){
        app.goTo().gotoToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
     return Arrays
             .asList(contact.getHome(),contact.getMobile(),contact.getWork())
             .stream().filter((s) -> !s.equals(""))
             .map(ContactDataTests::cleanedPhone)
             .collect(Collectors.joining("\n"));
    }
    public static String cleanedPhone(String phone){
        return phone.replaceAll("\\s+","").replaceAll("[-()]","");
    }

  @Test
    public  void testContactEmails(){
      app.goTo().gotoToHomepage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditForm)));
  }

    private String mergeEmails(ContactData contact) {
        return Arrays
                .asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDataTests::cleanedEmail)
                .collect(Collectors.joining("\n"));
    }
    public static String cleanedEmail(String email){
        return email.replaceAll("\\s+","");
    }

    @Test
    public void testAddress(){
        app.goTo().gotoToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(cleanedAddress(contact.getAddress()),equalTo(mergeAddress(contactInfoFromEditForm)));
    }
    private String mergeAddress(ContactData contact) {
        return Arrays
                .asList(contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDataTests::cleanedAddress)
                .collect(Collectors.joining("\n"));
    }
    public static String cleanedAddress(String address){
        return address.replaceAll("\\s+","");
    }


    @Test
    public void testDetails(){
        app.goTo().gotoToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetails = app.contact().infoFromDetails(contact);
        assertThat(contactInfoFromDetails.getAllDetails(),equalTo(mergeDetails(contactInfoFromEditForm)));
    }

    private String mergeDetails(ContactData contact) {
        return Arrays
                .asList(contact.getFirstname(),
                        contact.getMiddlename(),
                        contact.getLastname(),
                        contact.getNickname(),
                        contact.getTitle(),
                        contact.getCompany(),
                        contact.getAddress(),
                        contact.getHome(),
                        contact.getMobile(),
                        contact.getWork(),
                        contact.getEmail(),
                        contact.getEmail2(),
                        contact.getEmail3(),
                        contact.getAddress2())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDataTests::cleanedDetails)
                .collect(Collectors.joining(""));
    }
    private static String cleanedDetails(String details) {
        return details.replaceAll("\\s+","");
    }

}

