package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

/**
 * Created by doomin on 1/24/17.
 */
public class AddContactToGroupTest extends TestBase {

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        app.goTo().gotoToHomepage();
        app.contact().addToGroup(contact, group);
    }
}
