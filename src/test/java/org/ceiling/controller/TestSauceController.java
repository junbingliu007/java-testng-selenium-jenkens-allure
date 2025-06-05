package org.ceiling.controller;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSauceController {
    @Parameters({
            "productCount",
            "selectionMethod",
            "firstname",
            "lastname",
            "postcode"
    })
    @Test
    public void testSauce(@Optional("3") int productCount,
                          @Optional("sequential") String selectionMethod,
                          @Optional("John") String firstname,
                          @Optional("Doe") String lastname,
                          @Optional("12345") String postcode) throws IOException {
        SauceDemoController sauceDemoController = new SauceDemoController();
        sauceDemoController.setUp();
        SauceDemoController.testSauceDemo(productCount,
                selectionMethod,
                firstname,
                lastname,
                postcode);
        sauceDemoController.tearDown();

    }


}
