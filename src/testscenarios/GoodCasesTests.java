package testscenarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import datastruresfortests.DataWithFourParameter;
import datastruresfortests.DataWithOneParameter;
import datastruresfortests.DataWithTwoParameter;
import methodsfortests.SetFieldsInQuickSearch;
import methodsfortests.VerifyQuickSearch;

public class GoodCasesTests {
	static WebDriver wd;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");

		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		wd.get(TestConstants.FUNDA_WEBSITE);

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

	@Test(dataProvider = "getFourParameterData")
	void webPageReadyForSentOrRent_setInputs_VerifyTheResults(DataWithFourParameter data) throws InterruptedException {

		wd.findElement(By.linkText(data.getTabName())).click();

		SetFieldsInQuickSearch.setAdress(wd, data.getCity());
		SetFieldsInQuickSearch.setDistanceDropDown(wd, data.getDistanceInKm());
		SetFieldsInQuickSearch.setPriceDropDown(wd, data.getTabName(), data.getPriceFrom(), data.getPriceTo());

		wd.findElement(By.className(TestConstants.SEARCH_BUTTON_ID)).click();

		VerifyQuickSearch.verifyPriceRangeOnResultPage(wd, data.getPriceFrom(), data.getPriceTo(),
				TestConstants.GOOD_CASE);
		VerifyQuickSearch.verifyOwnershipPlan(wd, data.getTabName().substring(4));
		VerifyQuickSearch.verifyQuickSearchWorksProperly(wd);
		VerifyQuickSearch.verifyCityOnLastSearchPage(wd, data.getCity());
		VerifyQuickSearch.verifyDistanceOnLastSearchPage(wd, data.getDistanceInKm());
	}

	@Test(dataProvider = "getTwoParameterData")
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

	@Test(dataProvider = "getOneParameterData")
	void webPageReadyForEurope_setInputs_VerifyTheResults(DataWithOneParameter data) throws InterruptedException {

		wd.findElement(By.linkText(data.getTabName())).click();

		SetFieldsInQuickSearch.setCountry(wd, data.getCountry());

		wd.findElement(By.className(TestConstants.SEARCH_BUTTON_ID)).click();

		VerifyQuickSearch.verifyOwnershipPlan(wd, data.getTabName());
		VerifyQuickSearch.verifyQuickSearchWorksProperly(wd);
		// This feature does not work.
		// (Can be BUG!!)VerifyQuickSearch.verifyCityOnLastSearchPage(wd,
		// data.getCountry());
	}

	public static List<String> getDropdownValues(String id) {

		WebElement dropdownValues = wd.findElement(By.id(id));

		List<WebElement> options = dropdownValues.findElements(By.tagName("option"));

		Iterator<WebElement> it = options.iterator();
		List<String> dropdownValuesList = new ArrayList<>();
		while (it.hasNext()) {
			String value = it.next().getAttribute("Value");
			dropdownValuesList.add(value);
		}
		return dropdownValuesList;
	}

	@DataProvider
	public Object[] getFourParameterData() {

		Object[] data = {
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.AMSTERDAM_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.DWINGELOO_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.ULFT_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.BARENDRECTH_CITY,
						TestConstants.DISTANCE, TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.SALE_TAB, TestConstants.CASTRICUM_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_SALE, TestConstants.PRICE_TO_SALE),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.AMSTERDAM_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.DWINGELOO_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.ULFT_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.BARENDRECTH_CITY,
						TestConstants.DISTANCE, TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT),
				new DataWithFourParameter(TestConstants.RENT_TAB, TestConstants.CASTRICUM_CITY, TestConstants.DISTANCE,
						TestConstants.PRICE_FROM_RENT, TestConstants.PRICE_TO_RENT) };
		return data;
	}

	@DataProvider
	public Object[] getTwoParameterData() {

		Object[] data = {
				new DataWithTwoParameter(TestConstants.NEWLY_BUILT_TAB, TestConstants.AMSTERDAM_CITY,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.NEWLY_BUILT_TAB, TestConstants.DWINGELOO_CITY,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.NEWLY_BUILT_TAB, TestConstants.ULFT_CITY,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.NEWLY_BUILT_TAB, TestConstants.BARENDRECTH_CITY,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.NEWLY_BUILT_TAB, TestConstants.CASTRICUM_CITY,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.RECREATION_TAB, TestConstants.AMSTERDAM_CITY,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.RECREATION_TAB, TestConstants.DWINGELOO_CITY,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.RECREATION_TAB, TestConstants.ULFT_CITY, TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.RECREATION_TAB, TestConstants.BARENDRECTH_CITY,
						TestConstants.DISTANCE),
				new DataWithTwoParameter(TestConstants.RECREATION_TAB, TestConstants.CASTRICUM_CITY,
						TestConstants.DISTANCE) };
		return data;
	}

	@DataProvider
	public Object[] getOneParameterData() {

		Object[] data = { new DataWithOneParameter(TestConstants.EUROPE_TAB, TestConstants.BELGIUM_COUNTRY),
				new DataWithOneParameter(TestConstants.EUROPE_TAB, TestConstants.BULGARIA_COUNTRY),
				new DataWithOneParameter(TestConstants.EUROPE_TAB, TestConstants.DENMARK_COUNTRY),
				new DataWithOneParameter(TestConstants.EUROPE_TAB, TestConstants.GERMANY_COUNTRY),
				new DataWithOneParameter(TestConstants.EUROPE_TAB, TestConstants.FRANCE_COUNTRY) };
		return data;
	}

	// wd.findElement(By.linkText(EUROPE_TAB)).click();
	// wd.findElement(By.className("selected-option")).click();
	//
	// WebElement dropdownValues =
	// wd.findElement(By.className("selectbox-list-container"));
	//
	// List<WebElement> options =
	// dropdownValues.findElements(By.className("selectbox-list is-open"));
	// //linkText("data-selectbox-option-display-value"));
	//
	// Iterator<WebElement> it = options.iterator();
	// List<DataWithOneParameter> list = new ArrayList<>();
	// while (it.hasNext()) {
	// String value = it.next().getAttribute("data-selectbox-option-display-value");
	// list.add(new DataWithOneParameter(EUROPE_TAB, value));
	// }
	//
	// return list.toArray();
	// }
}
