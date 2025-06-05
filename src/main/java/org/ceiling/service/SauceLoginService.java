package org.ceiling.service;

import org.ceiling.base.BaseService;
import org.ceiling.pages.SauceLoginPage;

public class SauceLoginService extends BaseService {
    private static final SauceLoginPage SAUCE_LOGIN_PAGE;

    static {
        SAUCE_LOGIN_PAGE = new SauceLoginPage();
    }

    public void inputUserName(String userName) {
        inputText(SAUCE_LOGIN_PAGE.findInputUserNameElement(), userName);
    }

    public void inputPassword(String password) {
        inputText(SAUCE_LOGIN_PAGE.findInputPasswordElement(), password);
    }

    public void clickLoginButton() {
        clickElement(SAUCE_LOGIN_PAGE.findButtonLoginElement());
    }

}
