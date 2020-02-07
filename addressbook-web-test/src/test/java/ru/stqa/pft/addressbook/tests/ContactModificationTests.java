package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("ModCrFirstTest").withMiddlename("MiddleTest").withLastname("LastTest")
                    .withNickname("NickTest").withCompany("CompanyTest").withAddress("AddressTest, 12").withHome("HomeTest").withGroup("test1"));
            app.goTo().homePage();
        }
    }
    @Test
    public void testContactModification() {

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("ModifyFirstTest").withMiddlename("MiddleTest").withLastname("LastTest")
                .withNickname("NickTest").withCompany("CompanyTest").withAddress("AddressTest, 12").withHome("HomeTest");

        app.contact().modify(index, contact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> ById = Comparator.comparingInt(ContactData::getId);
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
    }



}
