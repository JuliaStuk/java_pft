package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.addNewContact();
        app.fillNewContact(new ContactData("FirstTest", "MiddleTest", "LastTest", "NickTest", "CompanyTest", "AddressTest, 12", "HomeTest"));
        app.submitNewContact();
        app.returnToHomePage();
    }

}
