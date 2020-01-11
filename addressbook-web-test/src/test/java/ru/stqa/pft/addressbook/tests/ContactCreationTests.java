package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillNewContact(new ContactData("FirstTest", "MiddleTest", "LastTest", "NickTest", "CompanyTest", "AddressTest, 12", "HomeTest"));
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().returnToHomePage();
    }

}
