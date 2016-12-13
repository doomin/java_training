package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by doomin on 13.12.16.
 */
public class ContactDeletionTest extends TestBase{

  @Test
  public void testContactDeletion() {
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().closePopUp();
    app.getNavigationHelper().returnToHome();
  }
}
