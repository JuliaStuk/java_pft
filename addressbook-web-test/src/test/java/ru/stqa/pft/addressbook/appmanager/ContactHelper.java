package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper<creation> extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContact() {
        click(By.name("submit"));
    }

    public void fillContact(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"),contactData.getWorkPhone());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());
        //     if (creation) {
        //          new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        //     } else {
        //        Assert.assertFalse(isElementPresent(By.name("new_group")));
        //     }
    }

    public void addContact() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void getContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id + "']")).click();
    }

    public void create(ContactData contact) {
        addContact();
        fillContact(contact, true);
        submitContact();
        contactCash = null;
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        fillContact(contact, false);
        submitContactModification();
        contactCash = null;
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void delete(ContactData contact) {
        getContactById(contact.getId());
        deleteContact();
        try {
            acceptAlert();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contactCash = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath(String.format("(//input[@name='selected[]'])")));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCash = null;

    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }
        contactCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.tagName("tr").name("entry"));
        for (WebElement element : elements) {
            List<WebElement> tdList = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(tdList.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = tdList.get(1).getText();
            String firstname = tdList.get(2).getText();
            String address = tdList.get(3).getText();
            String allEmails = tdList.get(4).getText();
            String allPhones = tdList.get(5).getText();

            contactCash.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).withAddress(address)
                    .withAllEmail(allEmails).withAllPhones(allPhones));
        }
        return new Contacts(contactCash);
    }

    public ContactData infoFromEditFrom(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}