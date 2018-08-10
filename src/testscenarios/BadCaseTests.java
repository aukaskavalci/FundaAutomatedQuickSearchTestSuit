package testscenarios;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import datastruresfortests.DataWithFourParameter;
import datastruresfortests.DataWithTwoParameter;
import methodsfortests.SetFieldsInQuickSearch;
import methodsfortests.VerifyQuickSearch;

public class BadCaseTests {
	static WebDriver wd;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");

		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		wd.get("https://www.funda.nl/en/huur/");
	}

	@AfterClass
	public static void terminateTest() {
		wd.quit();
	}

	@AfterMethod
	public void returnMainPage() {
		wd.findElement(By.className(TestConstants.RETURN_MAIN_PAGE)).click();
		wd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "getInvalidFourParameterData", expectedExceptions = NoSuchElementException.class)
	void webPageReadyForSentOrRent_setInputs_VerifyTheResults(DataWithFourParameter data) throws InterruptedException {
		wd.findElement(By.linkText(data.getTabName())).click();

		SetFieldsInQuickSearch.setAdress(wd, data.getCity());
		SetFieldsInQuickSearch.setDistanceDropDown(wd, data.getDistanceInKm());
		SetFieldsInQuickSearch.setPriceDropDown(wd, data.getTabName(), data.getPriceFrom(), data.getPriceTo());

		wd.findElement(By.className(TestConstants.SEARCH_BUTTON_ID)).click();

		VerifyQuickSearch.verifyPriceRangeOnResultPage(wd, data.getPriceFrom(), data.getPriceTo(),
				TestConstants.BAD_CASE);
		VerifyQuickSearch.verifyOwnershipPlan(wd, data.getTabName().substring(4));
		VerifyQuickSearch.verifyQuickSearchWorksProperly(wd);
		VerifyQuickSearch.verifyCityOnLastSearchPage(wd, data.getCity());
		VerifyQuickSearch.verifyDistanceOnLastSearchPage(wd, data.getDistanceInKm());
	}

	@Test(dataProvider = "getInvalidTwoParameterData")
	void webPageReadyForNewlyBuiltOrRecreation_setInputs_VerifyTheResults(DataWithTwoParameter data)
			throws InterruptedException {

		wd.findElement(By.linkText(data.getTabName())).click();

		SetFieldsInQuickSearch.setAdress(wd, data.getAdress());
		SetFieldsInQuickSearch.setDistanceDropDown(wd, data.getKmDistance());

		wd.findElement(By.className(TestConstants.SEARCH_BUTTON_ID)).click();

		VerifyQuickSearch.verifyOwnershipPlan(wd, data.getTabName().substring(0, 3));
		VerifyQuickSearch.verifyQuickSearchWorksProperly(wd);
		VerifyQuickSearch.verifyCityOnLastSearchPage(wd, data.getAdress());
		VerifyQuickSearch.verifyDistanceOnLastSearchPage(wd, data.getKmDistance());
	}

	@DataProvider
	public Object[] getInvalidFourParameterData() {

		Object[] data = {
				/// Invalid City
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.INVALID_TEXT, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.NEGATIVE_NUMBER, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.HUGE_NUMBER, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.NONSECURE_TEXT, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.INVALID_TEXT, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.NEGATIVE_NUMBER, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.HUGE_NUMBER, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.NONSECURE_TEXT, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				//// Invalid Distance (Invalid Distance cannot be entered from UI as it can be
				// seen it test this is why here is comment out.)
				// new DataWithFourParameter(TestConstants.SALE_TAB,
				// TestConstants.AMSTERDAM_CITY,
				// TestConstants.HUGE_NUMBER, TestConstants.PRICE_FROM_SALE,
				// TestConstants.PRICE_TO_SALE),
				// new DataWithFourParameter(TestConstants.SALE_TAB,
				// TestConstants.AMSTERDAM_CITY,
				// TestConstants.NEGATIVE_NUMBER, TestConstants.PRICE_FROM_SALE,
				// TestConstants.PRICE_TO_SALE),
				// new DataWithFourParameter(TestConstants.SALE_TAB,
				// TestConstants.AMSTERDAM_CITY,
				// TestConstants.INVALID_TEXT, TestConstants.PRICE_FROM_SALE,
				// TestConstants.PRICE_TO_SALE),
				// new DataWithFourParameter(TestConstants.SALE_TAB,
				// TestConstants.AMSTERDAM_CITY, TestConstants.SPACE,
				// TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				// new DataWithFourParameter(TestConstants.RENT_TAB,
				// TestConstants.AMSTERDAM_CITY,
				// TestConstants.HUGE_NUMBER, TestConstants.PRICE_FROM_RENT,
				// TestConstants.PRICE_TO_RENT),
				// new DataWithFourParameter(TestConstants.RENT_TAB,
				// TestConstants.AMSTERDAM_CITY,
				// TestConstants.NEGATIVE_NUMBER, TestConstants.PRICE_FROM_RENT,
				// TestConstants.PRICE_TO_RENT),
				// new DataWithFourParameter(TestConstants.RENT_TAB,
				// TestConstants.AMSTERDAM_CITY,
				// TestConstants.INVALID_TEXT, TestConstants.PRICE_FROM_RENT,
				// TestConstants.PRICE_TO_RENT),
				// new DataWithFourParameter(TestConstants.RENT_TAB,
				// TestConstants.AMSTERDAM_CITY, TestConstants.SPACE,
				// TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				///// Invalid Price Range
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.AMSTERDAM_CITY, TestConstants.DISTANCE,
						TestConstants.INVALID_TEXT, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.DWINGELOO_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.INVALID_TEXT),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.ULFT_CITY, TestConstants.DISTANCE,
						TestConstants.NEGATIVE_NUMBER, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.BARENDRECTH_CITY,
						TestConstants.DISTANCE, TestConstants.PRICE_FROM_SALE, TestConstants.NEGATIVE_NUMBER),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.CASTRICUM_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.CASTRICUM_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_TO_SALE, TestConstants.PRICE_FROM_SALE),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.AMSTERDAM_CITY, TestConstants.DISTANCE,
						TestConstants.INVALID_TEXT, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.DWINGELOO_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.INVALID_TEXT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.ULFT_CITY, TestConstants.DISTANCE,
						TestConstants.NEGATIVE_NUMBER, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.BARENDRECTH_CITY,
						TestConstants.DISTANCE, TestConstants.PRICE_FROM_RENT, TestConstants.NEGATIVE_NUMBER),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.CASTRICUM_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_TO_RENT, TestConstants.PRICE_FROM_RENT) };
		return data;
	}

	@DataProvider
	public Object[] getInvalidTwoParameterData() {

		Object[] data = {
				new DataWithTwoParameter(TestConstants.NEWLY_BUILT_TAB, TestConstants.INVALID_TEXT,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.NEWLY_BUILT_TAB, TestConstants.NEGATIVE_NUMBER,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.NEWLY_BUILT_TAB, TestConstants.HUGE_NUMBER,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.RECREATION_TAB, TestConstants.INVALID_TEXT,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.RECREATION_TAB, TestConstants.NEGATIVE_NUMBER,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.RECREATION_TAB, TestConstants.HUGE_NUMBER,
						TestConstants.DISTANCE) };
		return data;
	}

	// For Europe Tab there is no way to enter invalid value the only possibility
	// enter the country that does not provide result.
}
