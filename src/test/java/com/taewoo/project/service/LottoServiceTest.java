package com.taewoo.project.service;

import com.taewoo.project.ProjectApplication;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoServiceTest {

    private WebDriver driver;
    private String url;
    ChromeOptions options = new ChromeOptions();

    //프로퍼티
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "C:/chromedriver.exe";
    public static String URL = "https://dhlottery.co.kr/gameResult.do?method=byWin";

    @Test
    public ArrayList<ArrayList<String>> getLottoHistory() {
        ArrayList<ArrayList<String>> getLottoNumber = new ArrayList<>();

        try {
            System.setProperty(WEB_DRIVER_ID , WEB_DRIVER_PATH);
            options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.get(URL);

            String[] lottoCount = driver.findElements(By.cssSelector("#dwrNoList option")).toString().split(" " , 10);

            //상위 회차 10개 체크
            System.out.println(lottoCount.toString());

            for(int i = 0 ; i<lottoCount.length ; i++) {
                System.out.println(lottoCount[i]);
            }

            /*
             * 여기서 selected 를 조절해 당첨 값을 가져온다.
             * */
            for(int i = 0; i<lottoCount.length; i++) {

                List<WebElement> lottoArray = driver.findElements(By.className(".ball_645"));
                ArrayList<String> list = new ArrayList<>();

                for(WebElement lottoNum : lottoArray) {
                    list.add(lottoNum.getTagName());
                    System.out.println(lottoNum.getTagName());
                }

                getLottoNumber.add(list);
                list.clear();


            }


        } catch(Exception e) {
            System.out.println("url을 확인하세요");
            System.exit(0);
        }

        return getLottoNumber;

    }
}