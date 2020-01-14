package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().selectContact(1);
        app.getContactHelper().fillContact(new ContactData("FirstTest", "MiddleTest", "LastTest", "NickTest", "CompanyTest", "AddressTest, 12", "HomeTest", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
    }

}
