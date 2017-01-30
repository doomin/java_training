package ru.stqa.pft.addressbook.tests;

import org.hamcrest.core.IsNot;
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
public class RemoveContactFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        app.goTo().gotoToHomepage();

        if (app.db().contacts().size() == 0 ) {

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

            Groups groups = app.db().groups();
            GroupData group = groups.iterator().next();
            ContactData addedContact = app.db().selectMaxContactId();
            app.contact().addToGroup(addedContact, group);
        }

        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Contacts contactsInGroup = group.getContacts();
        contacts.retainAll(contactsInGroup);

        if (contacts.size() == 0){

            contacts = app.db().contacts();
            ContactData contact = contacts.iterator().next();
            app.contact().addToGroup(contact, group);
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        Contacts contactsInGroup = group.getContacts();
        contacts.retainAll(contactsInGroup);

        ContactData contact = contacts.iterator().next();
        app.goTo().gotoToHomepage();
        app.contact().removeFromGroup(contact, group);
        app.db().refreshContactData(contact);

        assertThat(contact.getGroups(), not(hasItem(hasProperty("name", is(group.getName())))));
    }
}
