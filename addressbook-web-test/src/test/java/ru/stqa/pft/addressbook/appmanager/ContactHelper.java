package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContact() {
        click(By.name("submit"));
    }

    public void fillContact(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());

    }

    public void addContact() {
        click(By.linkText("add new"));
    }

    public void selectContact(int n) {
        click(By.xpath(String.format("(//img[@alt='Edit'])[%s]", n)));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void getContactByRowNumber(int n) {
        click(By.xpath(String.format("(//input[@name='selected[]'])[%s]", n)));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
}
