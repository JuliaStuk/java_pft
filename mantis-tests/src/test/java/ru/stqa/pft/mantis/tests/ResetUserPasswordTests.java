package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ResetUserPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test

    public void testChangePassword() throws IOException, MessagingException {
        Users users = app.db().users();
        Set<UserData> filteredUsers = users.stream()
                .filter(u -> u.getUsername().equals(app.getProperty("web.userName")))
                .collect(Collectors.toSet());

        UserData filteredUser = filteredUsers.iterator().next();
        int filteredUserId = filteredUser.getId();
        String filteredUserEmail = filteredUser.getEmail();
        String filteredUserName = filteredUser.getUsername();

        app.adminNavigation().loginAdmin(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.adminNavigation().gotoManageUserPage(filteredUserId);
        app.adminNavigation().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, filteredUserEmail);
        String newPassword = String.format("password%s", System.currentTimeMillis());
        app.registration().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(filteredUserName, newPassword));
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)

    public void stopMailServer() {
        app.mail().stop();
    }
}
