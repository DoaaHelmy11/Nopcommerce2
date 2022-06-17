package org.example.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.P01_Home;
import org.example.pages.P05_Search;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Locale;

public class D04_SearchStepDef {

    P01_Home home = new P01_Home();
    P05_Search search_page = new P05_Search();

    @When("^user search for ([^\"]*)$")
    public void search_by_name_or_sku(String searchValue)
    {

        home.searchBar().sendKeys(searchValue);
        home.searchBar().sendKeys(Keys.ENTER);
    }

    @Then("^user gets relative products for ([^\"]*)$")
    public void relative_products_are_retlived(String searchValue)
    {
        int count = search_page.titles_of_search_products().size();
        searchValue = searchValue.toLowerCase(Locale.ROOT);

        for (int i = 0 ; i<count; i++)
        {
            String text = search_page.titles_of_search_products().get(i).getText();
            text = text.toLowerCase(Locale.ROOT);
            Assert.assertTrue(text.contains(searchValue));

        }
    }

//    @Then("user gets the only relative product for ([^\"]*)$")
//    public void relative_product_for_sku(String searchValue)
//    {
//        int count = search_page.titles_of_search_products().size();
//        Assert.assertTrue(count==1);
//    }

    @Then("^user gets the only relative product for ([^\"]*)$")
    public void relative_product_for_sku(String searchValue)
    {
        int count = search_page.titles_of_search_products().size();
        Assert.assertTrue(count==1);

        search_page.titles_of_search_products().get(0).click();

       // System.out.println("search value is: "+ searchValue);
        Assert.assertEquals(searchValue,Hooks.driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[5]/div[1]/span[2]")).getText());
       // System.out.println("printed expected value is: "+ Hooks.driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[5]/div[1]/span[2]")).getText());

    }



}
