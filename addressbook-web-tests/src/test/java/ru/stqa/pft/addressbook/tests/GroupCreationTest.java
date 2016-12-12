package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase{

  @Test
  public void testGroupCreation() {

    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test7", "test8", "test9"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
