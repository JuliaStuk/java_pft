package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().addContact();
        app.getContactHelper().fillContact(new ContactData("FirstTest", "MiddleTest", "LastTest", "NickTest", "CompanyTest", "AddressTest, 12", "HomeTest", "test1"), true);
        app.getContactHelper().submitContact();
        app.getNavigationHelper().returnToHomePage();
    }

}
