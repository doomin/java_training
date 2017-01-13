package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String group;
  private String firstname;
  private String secondname;
  private String lastname;
  private String nickname;
  private String title;
  private String company;
  private String address;
  private String home;
  private String mobile;
  private String work;
  private String allPhones;
  private String email;
  private String email2;
  private String email3;
  private String allEmails;

  public String getGroup() {
        return group;
    }
  public String getFirstname() {
    return firstname;
  }
  public String getSecondname() {
    return secondname;
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
  public String getHome() {
        return home;
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
  public int getId() {
        return id;
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
  public ContactData withSecondName(String secondname){
        this.secondname = secondname;
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

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
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

}
