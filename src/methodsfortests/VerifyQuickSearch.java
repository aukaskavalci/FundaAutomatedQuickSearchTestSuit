package methodsfortests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class VerifyQuickSearch {

	public static String getLastSearch(WebDriver webDriver) {
		webDriver.findElement(By.className(FrontEndConstants.RETURN_MAIN_PAGE)).click();
		String lastSearch = webDriver.findElement(By.className(FrontEndConstants.LAST_SEARCH_ID)).getText()
				.toLowerCase();
		return lastSearch.replace(",", "");
	}

	public static void verifyCityOnLastSearchPage(WebDriver webDriver, String city) {

		Assert.assertTrue(getLastSearch(webDriver).contains(city.toLowerCase()), String
				.format("Last search with the parameter \"%s\" is not matched with the city that is checked.", city));

	}

	public static void verifyDistanceOnLastSearchPage(WebDriver webDriver, String distance) {

		Assert.assertTrue(getLastSearch(webDriver).contains(distance), String.format(
				"Last search with the distance parameter \"%s\" is not matched with the distance that is checked.",
				distance));

	}

	public static void verifyPriceRangeOnLastSearchPage(WebDriver webDriver, String priceFrom, String priceTo,
			boolean goodCase) {
		if (goodCase) {
			Assert.assertTrue(
					(getLastSearch(webDriver).contains(priceFrom) && getLastSearch(webDriver).contains(priceTo)),
					String.format("Price Range \"%s\" and \"%s\" is not matched with last search. ", priceFrom,
							priceTo));
		} else {
			Assert.assertTrue(
					(!(getLastSearch(webDriver).contains(priceFrom) && getLastSearch(webDriver).contains(priceTo))),
					String.format(
							"Although the Price Ranges set wrong as  \"%s\" and \"%s\" the website did not give any error!!! ",
							priceFrom, priceTo));
		}

	}

	public static void verifyQuickSearchWorksProperly(WebDriver webDriver) {
		String result = webDriver.findElement(By.className(FrontEndConstants.RESULT_ID)).getText();
		Assert.assertTrue(result.contains(FrontEndConstants.RESULT_CHECK),
				"With the given parameter, there is an error occurs evet it should not.");

	}

	public static void verifyPriceRangeOnResultPage(WebDriver webDriver, String priceFrom, String priceTo,
			boolean goodCase) {
		String range = webDriver.findElement(By.className(FrontEndConstants.RANGE_ID)).getText();
		range = range.replace(",", "");
		if (goodCase) {
			Assert.assertTrue(range.contains(priceFrom) && range.contains(priceTo),
					String.format("Range are not set correctly  as \"%s\" and \"%s\"", priceFrom, priceTo));
		} else {
			Assert.assertTrue(!(range.contains(priceFrom) && range.contains(priceTo)), String.format(
					"Although range are not set correctly as  as \"%s\" and \"%s\". Website did not give any error!!!",
					priceFrom, priceTo));
		}

	}

	public static void verifyOwnershipPlan(WebDriver webDriver, String plan) {
		String ownershipPlan = webDriver.findElement(By.className(FrontEndConstants.OWNERSHIP_ID)).getText();
		Assert.assertTrue(ownershipPlan.toLowerCase().contains(plan.toLowerCase()),
				String.format(
						"Ownership plan is not set accordingly input. The input was \"%s\" and the result is \"%s\"",
						plan, ownershipPlan));

	}
}
