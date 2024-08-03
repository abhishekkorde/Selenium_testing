package com.dtcc.ete.apitest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileAutomation {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Program Files (x86)/Chrome/ChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("http://www.fitpeo.com");
            WebElement revenueCalculatorLink = driver.findElement(By.xpath("//div[contains(text(),'Revenue Calculator')]"));
            driver.manage().window().maximize();
            Thread.sleep(2000);
            revenueCalculatorLink.click();
            
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement waiter = driver.findElement(By.xpath("//*[@id='nro:1']"));
            Actions a = new Actions(driver);
            a.moveToElement(waiter).build().perform();
            
            Actions move = new Actions(driver);
            WebElement sliderHandle = driver.findElement(By.xpath("//html/body/div[2]/div[1]/div[2]/div/span[1]/span[2]"));
            Thread.sleep(4000);
            move.dragAndDropBy(sliderHandle, 107, 0).perform();
            Thread.sleep(5000);
            
            WebElement sliderValue = driver.findElement(By.xpath("//*[@id='inf:1']"));
            if (Integer.parseInt(sliderValue.getText()) != 820) {
                System.out.println("Slider did not set to 820 correctly.");
            }
            
            WebElement textField = driver.findElement(By.id("revenue_text_field"));
            textField.clear();
            textField.sendKeys("560");
            if (Integer.parseInt(sliderValue.getText()) != 560) {
                System.out.println("Slider did not update to 560 correctly.");
            }
            
            String[] cptCodes = {"CPT-99493", "CPT-99494"}; // Replace with actual IDs
            for (String code : cptCodes) {
                WebElement checkbox = driver.findElement(By.id(code)); // Replace with actual IDs
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
            
            WebElement totalReimbursement = driver.findElement(By.id("total_reimbursement")); // Replace with actual ID
            if (!totalReimbursement.getText().equals("$110700")) {
                System.out.println("Total Recurring Reimbursement did not match expected value.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
