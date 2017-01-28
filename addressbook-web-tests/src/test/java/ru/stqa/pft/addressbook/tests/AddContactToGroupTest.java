package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

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
        app.db().refreshContactData(contact);

        assertThat(contact.getGroups(), hasItem(hasProperty("name", is(group.getName()))));
    }
}
