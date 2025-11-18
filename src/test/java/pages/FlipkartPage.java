package pages;

import base.BasePage;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class FlipkartPage extends BasePage
{
    //region Locators
    @FindBy(xpath="//input[@placeholder='Search for Products, Brands and More']")
    WebElement globalSearch;

    @FindBy(xpath="//span[@class='BUOuZu']") WebElement result;

    @FindBy(xpath = "(//span[contains(text(),'Add to Compare')])[10]") private WebElement ten;
    @FindBy(xpath = "(//span[contains(text(),'Add to Compare')])[9]") private WebElement nine;
    @FindBy(xpath = "(//span[contains(text(),'Add to Compare')])[11]") private WebElement eleven;
    @FindBy(xpath="//span[contains(text(),'COMPARE')]") WebElement compare;
    @FindBy(xpath="//div[@class='_59SWBg Kns3sd']") WebElement comparePopup;
    @FindBy(xpath = "(//div[@class='KzDlHZ'])[10]") private WebElement tenthPhone;
    @FindBy(xpath="//button[normalize-space()='Add to cart']") WebElement addToCart;
    @FindBy(xpath="//button[normalize-space()='GO TO CART']") WebElement gOTOCART;
    @FindBy(xpath="//div[contains(text(),'Price (1 item)')]") WebElement cartItem;
    @FindBy(xpath = "(//div[@class='Nx9bqj _4b5DiR'])[10]") private WebElement tengthMblValue;
    @FindBy(xpath = "//div[@class='Nx9bqj CxhGGd']") private WebElement tenghthMblValueForCompare;
    @FindBy(xpath = "(//button[normalize-space()='+'])[1]") private WebElement plusBtn;
    @FindBy(xpath="//div[@class='eIDgeN']") WebElement plusQtyPopupMsg;
    @FindBy(xpath = "(//div[contains(text(),'Remove')])[1]") private WebElement remove;
    @FindBy(xpath="//button[contains(text(),'✕')]") WebElement removeItemPopup;
    @FindBy(xpath = "//div[@class='sBxzFz fF30ZI A0MXnh']") private WebElement removeBtn;
    @FindBy(xpath="//div[@class='eIDgeN']") WebElement removedPhoneMsg;
    //endregion

    //region Action Methods
    public void clickGlobalSearch()
    {
        clickOnElement1(globalSearch);
    }

    public void provideValue(String value)
    {
        clearAndProvide1(globalSearch, value);
    }

    public boolean isMessageDisplay()
    {
        String actualResult=result.getText();
        String expectedResult="Showing 1 – 24 of 10,7";
        if(actualResult.contains(expectedResult))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void scrollPage()
    {
        scrollIntoView(DriverFactory.getDriver(), nine);
        //scrollIntoView(DriverFactory.getDriver(), ten);
    }

    public void click10thCheckbox()
    {
        clickOnElement1(ten);
    }

    public void click11thCheckbox()
    {
        clickOnElement1(eleven);
    }

    public void moveToElement()
    {
        hoverOverElement(compare);
    }

    public boolean isComparePopupDisplay()
    {
        if(comparePopup.isDisplayed())
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void click10thPhone()
    {
        clickOnElement1(tenthPhone);
        waitTS(2);
    }

    public void clickAddToCart()
    {
        clickOnElement1(addToCart);
        waitTS(2);
    }

    public boolean isGoToCartDisplay()
    {
        return isMsgDisplay(gOTOCART,"GO TO CART");
    }

    public boolean isItemAddedToTheCart()
    {
        return isMsgDisplay(cartItem, "Price (1 item)");
    }

    public String get10thMblValue()
    {
        return tengthMblValue.getText();
    }

    public String get11thMblValue()
    {
        return tenghthMblValueForCompare.getText();
    }

    public void clickPlusBtn()
    {
        clickElementByJavaScript(DriverFactory.getDriver(),plusBtn);
    }

    public boolean isQtyAddMsgDisplay()
    {
        return isMsgDisplay(plusQtyPopupMsg, "You've changed 'REDMI A3X (Ocean Green, 128 GB)' QUANTITY to '2'");
    }

    public void clickRemove()
    {
        clickElementByJavaScript(DriverFactory.getDriver(), remove);
    }

    public boolean isRemoveCancelPopupDisplay()
    {
        WebElement removeBtn=  DriverFactory.getDriver().findElement(By.xpath("//div[@class='sBxzFz fF30ZI A0MXnh']"));
        WebElement cancelBtn=  DriverFactory.getDriver().findElement(By.xpath("//div[@class='sBxzFz fF30ZI t9UCZh']"));

        if(removeBtn.isDisplayed() && cancelBtn.isDisplayed())
        {
            return true;
        }else
        {
            return false;
        }
    }

    public void clickRemoveItemPoupup()
    {
        clickOnElement1(removeItemPopup);
    }

    public void clickRemoveBtn()
    {
        clickOnElement1(removeBtn);
    }

    public boolean isRemoveBtnPopupMsgDisplay()
    {
        return isMsgDisplay(removedPhoneMsg, "Successfully removed REDMI A3X (Ocean Green, 128 GB) from your cart");
    }

    public boolean isEmptyCartMessagesDisplay()
    {
        String missingCart=  DriverFactory.getDriver().findElement(By.xpath("//div[@class='s2gOFd']")).getText();
        String login=  DriverFactory.getDriver().findElement(By.xpath("//div[@class='orqM3-']")).getText();

        String expectedMissingCart="Missing Cart items?";
        String expectedlogin="Login to see the items you added previously";


        if(missingCart.equalsIgnoreCase(expectedMissingCart) && login.equalsIgnoreCase(expectedlogin))
        {
            return true;
        }else
        {
            return false;
        }
    }











    //endregion
}