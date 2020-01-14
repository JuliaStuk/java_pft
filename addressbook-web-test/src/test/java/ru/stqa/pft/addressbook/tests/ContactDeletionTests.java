package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().returnToHomePage();
        if (!app.getContactHelper().isThereAContact(1)) {
            app.getContactHelper().createContact(new ContactData("FirstTest", "MiddleTest", "LastTest", "NickTest", "CompanyTest", "AddressTest, 12", "HomeTest", "test1"));
        }
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().getContactByRowNumber(1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().returnToHomePage();
    }

}
