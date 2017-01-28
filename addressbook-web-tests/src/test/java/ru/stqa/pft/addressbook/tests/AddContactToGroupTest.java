package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by doomin on 1/24/17.
 */
public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        Groups groups = app.db().groups();

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        GroupData group = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        Contacts contactsInGroup = group.getContacts();
        contacts.removeAll(contactsInGroup);

        if (contacts.size() == 0) {
            app.goTo().gotoToHomepage();
            app.contact().createContact(new ContactData()
                    .withFirstName("Grzegorz")
                    .withMiddlename("V")
                    .withLastName("Markowiak")
                    .withNickName("trr")
                    .withTitle("Pan")
                    .withCompany("comp")
                    .withAddress("Zachodnia 12/b33-333 Krakow")
                    .withMobile("484787545")
                    .withEmail("hotmail@htomail.com"), true);

        }
    }

    @Test
    public void testAddContactToGroup() {

        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        Contacts contactsInGroup = group.getContacts();
        contacts.removeAll(contactsInGroup);

        ContactData contact = contacts.iterator().next();

        app.goTo().gotoToHomepage();
        app.contact().addToGroup(contact, group);


        app.db().refreshContactData(contact);
        assertThat(contact.getGroups(), hasItem(hasProperty("name", is(group.getName()))));
    }
}
