package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Wait
public class ItemsUAT extends FluentTest {
    @Value("${local.server.port}")
    private String port;

    @Override
    public WebDriver newWebDriver() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--headless");
        opt.addArguments("--disable-gpu");
        opt.addArguments("--no-sandbox");
        opt.addArguments("--whitelisted-ips=''");
        String path = System.getenv("GOOGLE_CHROME_BIN"); // heroku-specific
        if (!StringUtils.isEmpty(System.getenv("GOOGLE_CHROME_BIN"))) {
            System.out.println("Setting binary path to: " + path);
            opt.setBinary(path);
        }
        WebDriver driver = new ChromeDriver(opt);
        return driver;
    }

    @Test
    public void shouldSeeItemList() {
        // Setup
        Item testItem = new Item("123", "Saag");
        ItemController.items.add(testItem);

        // Exercise
        goTo("http://localhost:" + this.port + "/");
        await().until(() -> $("h2").present());

        // Assert
        FluentWebElement item = $("li").get(0);
        assertThat(item.text()).isEqualTo("Saag");
    }
}
