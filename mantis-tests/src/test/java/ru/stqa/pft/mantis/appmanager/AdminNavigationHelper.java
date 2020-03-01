package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminNavigationHelper extends HelperBase {

    public AdminNavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAdmin(String username, String password) {
        wd.get(app.getProperty("web.baseURL")+"/login_page.php");
        type(By.name("username"),username);
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"),password);
        click(By.xpath("//input[@type='submit']"));
    }

    public void gotoManageUserPage(int id) {
        click(By.xpath("//a[@href='/mantisbt-2.23.0/manage_overview_page.php']"));
        click(By.xpath("//a[@href='/mantisbt-2.23.0/manage_user_page.php']"));
        click(By.xpath("//a[@href='manage_user_edit_page.php?user_id="+id+"']"));

    }

    public void resetPassword() {
        click(By.xpath("//input[@value='Reset Password']"));
    }


}
