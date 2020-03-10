package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePrecondirions() {
        Contacts contacts = app.db().contacts();
        ContactData[] contactsWithoutGroups = contacts.stream().filter((s) -> s.getGroups().size() == 0).toArray(ContactData[]::new);
        // контакт создаем только если нет контактов без групп
        if (contactsWithoutGroups.length == 0){
            app.goTo().homePage();
            ContactData contact = new ContactData().withFirstname("FirstTest").withMiddlename("MiddleTest").withLastname("1New-LastTest")
                    .withNickname("NickTest").withCompany("CompanyTest").withAddress("AddressTest, 12");
            app.contact().create(contact);
        }
        // группу создаем только если нет групп
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            GroupData group = new GroupData().withName("addContact").withHeader("header").withFooter("footer");
            app.group().create(group);
        }
    }

    @Test
    public void testContactAddToGroup() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();

        ContactData[] beforeWithoutGroups = before.stream().filter((s) -> s.getGroups().size() == 0).toArray(ContactData[]::new);

        // хоть один контакт без групп будет, так как мы его должны были создать в предусловиях
        ContactData addContact = beforeWithoutGroups[0];
        GroupData group = groups.iterator().next();
        app.goTo().homePage();
        app.contact().addContactToGroup(addContact, group);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(addContact).withAdded(addContact.inGroup(group))));
    }

}
