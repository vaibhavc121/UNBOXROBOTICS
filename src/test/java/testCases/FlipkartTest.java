package testCases;

import base.BasePage;
import base.BaseTest;
import factory.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlipkartPage;
import utilities.RetryAnalyzer;

public class FlipkartTest extends BaseTest
{
    @Test(groups = "regression", retryAnalyzer = RetryAnalyzer.class)
    public void verifyFlipkartScenarios()
    {
        FlipkartPage fp=new FlipkartPage();

        fp.clickGlobalSearch();
        fp.provideValue("mobile");
        BasePage.pressEnter();
        softAssert.assertTrue(fp.isMessageDisplay(), "Mobile result message is not display");

        fp.scrollPage();
        fp.click10thCheckbox();
        String tengthMblValue=fp.get10thMblValue();
        fp.click11thCheckbox();
        fp.moveToElement();
        try
        {
            softAssert.assertTrue(fp.isComparePopupDisplay(), "Item is not added to the compare tray");
        } catch (Exception e)
        {

        }


        fp.click10thPhone();
        BasePage.switchTab();
        String tenghthMblValueForCompare=fp.get11thMblValue();
        fp.clickAddToCart();
        BasePage.navigateBack(DriverFactory.getDriver());
        softAssert.assertTrue(fp.isGoToCartDisplay(),"Button name not changes to 'going to cart'");

        BasePage.navigateForward(DriverFactory.getDriver());
        softAssert.assertTrue(fp.isItemAddedToTheCart(), "Item is not added to the cart");
        softAssert.assertEquals(tengthMblValue, tenghthMblValueForCompare, "Amount is not equal");

        fp.clickPlusBtn();
        try
        {
            softAssert.assertTrue(fp.isQtyAddMsgDisplay(), "Qty added messsage is not display");
        } catch (Exception e)
        {

        }


        fp.clickRemove();
        softAssert.assertTrue(fp.isRemoveCancelPopupDisplay(),"Remove & Cancel Popup is not display");

        fp.clickRemoveBtn();
        try
        {
            softAssert.assertTrue(fp.isRemoveBtnPopupMsgDisplay(), "After click on remove button popup message is not display");
        } catch (Exception e)
        {

        }

        softAssert.assertTrue(fp.isEmptyCartMessagesDisplay(), "On empty cart messages are not display");



    }

}