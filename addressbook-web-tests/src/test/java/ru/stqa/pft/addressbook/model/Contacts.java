package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dominik on 2017-01-11.
 */
public class Contacts extends ForwardingSet<NewContact> {

    private Set<NewContact> delegate;

    public Contacts(Contacts contacts){
        this.delegate = new HashSet<NewContact>(contacts.delegate);
    }

    public  Contacts(){
        this.delegate = new HashSet<NewContact>();
    }

    @Override
    protected Set<NewContact> delegate() {
        return null;
    }

    public Contacts withAdded(NewContact contact){
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(NewContact contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
