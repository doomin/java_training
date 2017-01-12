package ru.stqa.pft.addressbook.model;

public class NewContact {
    private int id = Integer.MAX_VALUE;
    private String group;
    private String firstname;
    private String secondname;
    private String lastname;
    private String nickname;
    private String title;
    private String company;
    private String address;
    private String mobile;
    private String email;

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

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
        return id;
    }

  public NewContact withId(int id){
        this.id = id;
        return this;
    }
    public NewContact withGroup(String group){
        this.group = group;
        return this;
    }
    public NewContact withFirstName(String firstname){
        this.firstname = firstname;
        return this;
    }
    public NewContact withSecondName(String secondname){
        this.secondname = secondname;
        return this;
    }
    public NewContact withLastName(String lastname){
        this.lastname = lastname;
        return this;
    }
    public NewContact withNickName(String nickname){
        this.nickname = nickname;
        return this;
    }
    public NewContact withTitle(String title){
        this.title = title;
        return this;
    }
    public NewContact withCompany(String company){
        this.company = company;
        return this;
    }
    public NewContact withAddress(String address){
        this.address = address;
        return this;
    }
    public NewContact withMobile(String mobile){
        this.mobile = mobile;
        return this;
    }
    public NewContact withEmail(String email){
        this.email = email;
        return this;
    }

  @Override
  public String toString() {
    return "NewContact{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NewContact that = (NewContact) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
