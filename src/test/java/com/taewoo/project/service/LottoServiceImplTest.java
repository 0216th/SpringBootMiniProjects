package com.taewoo.project.service;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class LottoServiceImplTest {
    private WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    //프로퍼티
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "C:/chromedriver.exe";
    public static String URL = "https://www.lotto.co.kr/article/list/AC01";

    @Test
    void getLottoHistory() {


            try {
                ArrayList<ArrayList<String>> getLottoNumber = new ArrayList<>();

                System.setProperty(WEB_DRIVER_ID , WEB_DRIVER_PATH);
                options.addArguments("headless");
                driver = new ChromeDriver(options);
                driver.get(URL);

                ListIterator<WebElement> lottoCount = driver.findElements(By.cssSelector("#data_table > div > div > ul p > span:nth-child(1)")).listIterator(); //회차
                ListIterator<WebElement> lottoDays = driver.findElements(By.cssSelector("#data_table > div > div > ul p > span:nth-child(2)")).listIterator(); //일자
                int cnt = 1 ;
                String lottoNumber = "" ;
                while(lottoCount.hasNext()) {
                    ArrayList<String> list = new ArrayList<>();

                    list.add(lottoCount.next().getText());  //회차
                    list.add(lottoDays.next().getText());   //일자
                    ListIterator<WebElement> lottoNums = driver.findElements(By.cssSelector("#data_table > div > div > ul > li:nth-child(" + cnt + ") > p > span:nth-child(3) > a > img")).listIterator();

                    while(lottoNums.hasNext()) {
                        lottoNumber = lottoNums.next().getAttribute("src").split("/")[7];
                        list.add(lottoNumber.substring(0 , lottoNumber.length() -4));
                    }

                    list.set(8 , "bonus");
                    getLottoNumber.add(cnt-1 , list);

                    cnt++;
                    if(cnt == 11) {
                        break;
                    }
               }

            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("url을 확인하세요");
                System.exit(0);
            }
        }
    }
