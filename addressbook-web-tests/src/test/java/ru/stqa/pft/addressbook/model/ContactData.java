package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
  private int id = Integer.MAX_VALUE;
    @Expose
    @Transient
  private String group;
    @Expose
    @Column(name = "firstname")
  //  @Type(type = "text")
  private String firstname;
    @Expose
    @Column(name = "middlename")
   // @Type(type = "text")
  private String middlename;
    @Expose
    @Column(name = "lastname")
  //  @Type(type = "text")
  private String lastname;
    @Expose
    @Column(name = "nickname")
 //   @Type(type = "text")
  private String nickname;
    @Expose
    @Column(name = "title")
  //  @Type(type = "text")
  private String title;
    @Expose
    @Column(name = "company")
    //@Type(type = "text")
  private String company;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
  private String address;
    @Expose
    @Column(name = "address2")
    @Type(type = "text")
  private String address2;
    @Expose
    @Transient
  private String allAddress;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
  private String home;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
  private String mobile;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
  private String work;
    @Transient
  private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
  private String email;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
  private String email2;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
  private String email3;
    @Transient
  private String allEmails;
    @Transient
  private String allDetails;

    public File getPhoto() {
        return new File (photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

  public String getGroup() {
        return group;
    }
  public String getFirstname() {
    return firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public String getNickname() {
    return nickname;
  }
  public String getTitle() {
    return title;
  }
  public String getCompany() {
    return company;
  }
  public String getAddress() {
    return address;
  }
  public String getAddress2() {
        return address2;
    }
  public String getHome() {
        return home;
    }
  public String getAllAddress() {
        return allAddress;
    }
  public String getMobile() {
    return mobile;
  }
  public String getWork() {
        return work;
    }
  public String getAllPhones() {
        return allPhones;
    }
  public String getEmail() {
    return email;
  }
  public String getEmail2() {
        return email2;
    }
  public String getEmail3() {
        return email3;
    }
  public String getAllEmails() {
        return allEmails;
    }
  public String getAllDetails() {
        return allDetails;
    }
  public int getId() {
        return id;
    }
  public String getMiddlename() {
        return middlename;
    }

  public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }
  public ContactData withId(int id){
        this.id = id;
        return this;
    }
  public ContactData withGroup(String group){
        this.group = group;
        return this;
    }
  public ContactData withFirstName(String firstname){
        this.firstname = firstname;
        return this;
    }
  public ContactData withLastName(String lastname){
        this.lastname = lastname;
        return this;
    }
  public ContactData withNickName(String nickname){
        this.nickname = nickname;
        return this;
    }
  public ContactData withTitle(String title){
        this.title = title;
        return this;
    }
  public ContactData withCompany(String company){
        this.company = company;
        return this;
    }
  public ContactData withAddress(String address){
        this.address = address;
        return this;
    }
  public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }
  public ContactData withAllAddress(String allAddress) {
        this.allAddress = allAddress;
        return this;
    }
  public ContactData withHome(String home){
        this.home = home;
        return this;
    }
  public ContactData withMobile(String mobile){
        this.mobile = mobile;
        return this;
    }
  public ContactData withWork(String work){
        this.work = work;
        return this;
    }
  public ContactData withEmail(String email){
        this.email = email;
        return this;
    }
  public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
  public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
  public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
  public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }
  public ContactData withAllDetails(String allDetails) {
        this.allDetails = allDetails;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
  public String toString() {
    return "ContactData{" +
            "id = '" + id + '\'' +
            "firstname ='" + firstname + '\'' +
            ", lastname ='" + lastname + '\'' +
            '}';
  }

}
