package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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
        type(By.name("home"), contactData.getHome());
        //     if (creation) {
        //          new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        //     } else {
        //        Assert.assertFalse(isElementPresent(By.name("new_group")));
        //     }
    }


    public void addContact() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.xpath(String.format("(//img[@alt='Edit'])"))).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void getContactByRowNumber(int index) {
        wd.findElements(By.xpath(String.format("(//input[@name='selected[]'])"))).get(index).click();
        //   click(By.xpath(String.format("(//input[@name='selected[]'])[%s]", n)));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(ContactData contact) {
        addContact();
        fillContact(contact, true);
        submitContact();
    }

    public void modify(int index, ContactData contact) {
        selectContact(index);
        fillContact(contact, false);
        submitContactModification();

    }

    public void delete(int index) throws InterruptedException {
      getContactByRowNumber(index);
      deleteContact();
      acceptAlert();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath(String.format("(//input[@name='selected[]'])")));
    }

    //  public boolean isThereAContact(int n) {
    //      return isElementPresent(By.xpath(String.format("(//img[@alt='Edit'])[%s]", n)));
    //  }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.tagName("tr").name("entry"));
        for (WebElement element : elements) {
            List<WebElement> tdList = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(tdList.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstname = tdList.get(2).getText();
            String lastname = tdList.get(1).getText();
            ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}