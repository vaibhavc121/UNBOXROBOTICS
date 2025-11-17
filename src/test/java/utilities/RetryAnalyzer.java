package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer
{
    int counter = 1;
    int retrylimit = 1;

    @Override
    public boolean retry(ITestResult result)
    {
        if (counter <= retrylimit)
        {
            counter++;
            return true;
        }

        return false;
    }

}