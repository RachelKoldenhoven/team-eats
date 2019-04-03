package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withName;

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

    @Before
    public void before() {
        ItemController.items.clear();
    }

    @After
    public void after() {
        ItemController.items.clear();
    }

    @Test
    public void shouldSeeItemList() {
        // Given that I have a web browser
        // Setup
        Item testItem = new Item("123", "Saag");
        ItemController.items.add(testItem);

        // When I navigate to the app
        // Exercise
        goTo("http://localhost:" + this.port + "/");
        await().until(() -> $("h2").present());

        // Then I see a list of food items
        // Assert
        FluentWebElement item = $("li").get(0);
        assertThat(item.text()).isEqualTo("Saag");
    }

    @Test
    public void shouldSaveItem() {
        // Given that I am on the TeamEats homepage
        goTo("http://localhost:" + this.port + "/");
        await().until(() -> $("h2").present());

        // When I enter an item in the text box and click 'save'
        FluentWebElement input = $("input", withName("item")).get(0);
        input.fill().with("Baingan Bharta");
        $("button", withName("save")).click();
        await().until(() -> $("[class^=\"Item\"]").present());

        // Then I see the item in a list
        FluentWebElement savedItem = $("li").get(0);
        assertThat(savedItem.text()).isEqualTo("Baingan Bharta");
        //assertThat(ItemController.items.get(0)). text is equal to 'baingan bharta
    }
}
