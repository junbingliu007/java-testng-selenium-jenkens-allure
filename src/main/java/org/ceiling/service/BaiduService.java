package org.ceiling.service;

import org.ceiling.base.BaseService;
import org.ceiling.pages.BaiduPage;

/**
 * service 元素操作层 (点击 输入等操作)
 */
public class BaiduService extends BaseService {

    private static final BaiduPage BAIDU_PAGE;

    static {
        BAIDU_PAGE = new BaiduPage();
    }

    /**
     *
     * @param content 输入框输入内容
     */
    public void inputContent(String content){

        inputText(BAIDU_PAGE.findInputContentElement(), content);

    }

    /**
     * 点击百度搜索按钮元素
     */
    public void clickSearchButton() {
        clickElement(BAIDU_PAGE.findSearchButtonElement());
    }
}
