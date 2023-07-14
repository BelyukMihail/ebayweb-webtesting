package com.solvd.ebayweb.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;

public class PageFactory {
    private static final Logger LOGGER = LogManager.getLogger(PageFactory.class);
    public static Capabilities capabilities;

    public  static Capabilities getCapabilities (String browser) throws MalformedURLException {
        switch (browser) {
            case "firefox":
                capabilities = new FirefoxOptions();
                break;
            case "chrome":
                capabilities = new ChromeOptions();
                break;
            case "safari":
                capabilities = new SafariOptions();
                break;
            default:
                capabilities = new ChromeOptions();
                throw new MalformedURLException("browser : " + browser + " is invalid, Launching Chrome as browser of choice..");
        }
        return capabilities;
    }
}
