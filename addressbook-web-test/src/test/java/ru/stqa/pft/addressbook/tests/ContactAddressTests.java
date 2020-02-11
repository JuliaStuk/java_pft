package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

        @BeforeMethod
        public void ensurePreconditions(){
            app.goTo().homePage();
            if (app.contact().all().size() == 0) {
                app.contact().create(new ContactData().withFirstname("ModCrFirstTest").withMiddlename("MiddleTest").withLastname("LastTest")
                        .withNickname("NickTest").withCompany("CompanyTest").withAddress("AddressTest, 12/1:89\n74-41"));
                app.goTo().homePage();
            }
        }

        @Test
        public void testContactAddress (){
            app.goTo().homePage();
            ContactData contact = app.contact().all().iterator().next();
            ContactData contactInfoFromEditForm = app.contact().infoFromEditFrom(contact);
            assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        }

}
