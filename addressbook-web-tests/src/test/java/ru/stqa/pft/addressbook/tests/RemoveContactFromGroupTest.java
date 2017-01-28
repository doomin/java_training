package ru.stqa.pft.addressbook.tests;

import org.hamcrest.core.IsNot;
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

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        app.goTo().gotoToHomepage();
        app.contact().removeFromGroup(contact, group);
        app.db().refreshContactData(contact);

        assertThat(contact.getGroups(), not(hasItem(hasProperty("name", is(group.getName())))));
    }
}
