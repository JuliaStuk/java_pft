package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        int n = 1;
        app.getNavigationHelper().returnToHomePage();
        if (!app.getContactHelper().isThereAContact(n)) {
            app.getContactHelper().createContact(new ContactData("FirstTest", "MiddleTest", "LastTest", "NickTest", "CompanyTest", "AddressTest, 12", "HomeTest", "test1"));
        }
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().selectContact(n);
        app.getContactHelper().fillContact(new ContactData("FirstTest", "MiddleTest", "LastTest", "NickTest", "CompanyTest", "AddressTest, 12", "HomeTest", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
    }

}
