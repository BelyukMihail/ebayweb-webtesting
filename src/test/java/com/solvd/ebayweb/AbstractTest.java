package com.solvd.ebayweb;

import com.solvd.ebayweb.utils.ConfigFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static com.solvd.ebayweb.utils.PageFactory.getCapabilities;

public abstract class AbstractTest {
    ChromeDriver driver = new ChromeDriver();

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date = new Date();
        String strDate = dateFormat.format(date.getTime());
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath + strDate + ".png");
        FileUtils.copyFile(SrcFile, DestFile);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodSetup(ITestResult result) throws Exception {
        if (result.getStatus()==ITestResult.FAILURE) {
            takeSnapShot(driver, ConfigFileReader.getData("screenShotsPath"));
            throw new IOException(result.getName()+ " is failed");
        }
        driver.quit();
    }
}
