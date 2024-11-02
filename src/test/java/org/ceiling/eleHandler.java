package org.ceiling;

import org.ceiling.base.BaseHandler;

import java.util.logging.Handler;

public class eleHandler extends BaseHandler {

    private static final elePage elePage;

    static {

        elePage = new elePage();

    }
    public void input_content(String content){

        inputText(elePage.input_content(), content);

    }

    public void button_click(){

        clickElement(elePage.clickEle());

    }




}
