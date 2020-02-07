package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("FirstTest").withMiddlename("MiddleTest").withLastname("1New-LastTest")
                .withNickname("NickTest").withCompany("CompanyTest").withAddress("AddressTest, 12").withHome("HomeTest");
        app.contact().create(contact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> ById = Comparator.comparingInt(ContactData::getId);
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
    }

}
