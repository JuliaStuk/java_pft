package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePrecondirions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            GroupData group = new GroupData().withName("addContact").withHeader("header").withFooter("footer");
            app.group().create(group);
        }
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData[] contactsWithGroups = contacts.stream().filter((s) -> s.getGroups().size() > 0).toArray(ContactData[]::new);
        // контакт создаем только если нет контактов с группой
        if (contactsWithGroups.length == 0){
            app.goTo().homePage();
            ContactData contact = new ContactData().withFirstname("FirstTest").withMiddlename("MiddleTest").withLastname("1New-LastTest")
                    .withNickname("NickTest").withCompany("CompanyTest").withAddress("AddressTest, 12").inGroup(groups.iterator().next());
            app.contact().create(contact);
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        Contacts before = app.db().contacts();

        ContactData[] beforeWithGroups = before.stream().filter((s) -> s.getGroups().size() > 0).toArray(ContactData[]::new);

        // хоть один контакт с группами будет, так как мы его должны были создать в предусловиях
        ContactData removeContact = beforeWithGroups[0];
        GroupData removeGroup = removeContact.getGroups().iterator().next();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(removeContact, removeGroup);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(removeContact).withAdded(removeContact.removeGroup(removeGroup))));
    }
}
