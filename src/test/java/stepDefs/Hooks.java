package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DBUtils;
import utilities.Driver;

import java.sql.SQLException;

public class Hooks {

    @Before
    public void setUp(){
        System.out.println("I am setting up the test from the Hooks class!!!");

    }
    @Before("@db")
    public void setUpDBConnection() throws SQLException {
        System.out.println("I am setting up the DB Connection!!!");
        DBUtils.createConnection();

    }
    @After("@db")
    public static void tearDownDBConnection(){
        System.out.println("I am closing the DB Connection!!!");
        try {
            DBUtils.destroy();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @After
    public void tearDown(Scenario scenario){
        System.out.println("I am reporting the results");
        // I want to take screenshot when current scenario fails.
        // scenario.isFailed()  --> tells if the scenario failed or not
        if (scenario.isFailed()) {
            // this line is for taking screenshot
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            // this line is adding the screenshot to the report
            scenario.attach(screenshot, "image/png", "Screenshot");
        }

        System.out.println("Closing driver");
        Driver.closeDriver();

    }
}
