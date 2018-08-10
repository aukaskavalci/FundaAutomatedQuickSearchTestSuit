package methodsfortests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SetFieldsInQuickSearch {

	public static void setDistanceDropDown(WebDriver wd, String distance) {

		Select dropdown = new Select(wd.findElement(By.id(FrontEndConstants.DISTANCE_DROPDOWN_ID)));
		dropdown.selectByValue(distance);
	}

	public static void setPriceDropDown(WebDriver wd, String tab, String priceFrom, String priceTo) {
		String priceFromId, priceToId;
		if (tab.contains(FrontEndConstants.RENT_TAB)) {
			priceFromId = FrontEndConstants.PRICEFROM_DROPDOWN_ID_SECONDARY;
			priceToId = FrontEndConstants.PRICETO_DROPDOWN_ID_SECONDARY;
		} else {
			priceFromId = FrontEndConstants.PRICEFROM_DROPDOWN_ID;
			priceToId = FrontEndConstants.PRICETO_DROPDOWN_ID;
		}
		Select dropdownForPrice = new Select(wd.findElement(By.id(priceFromId)));
		dropdownForPrice.selectByValue(priceFrom);

		Select dropdownForPriceMax = new Select(wd.findElement(By.id(priceToId)));
		dropdownForPriceMax.selectByValue(priceTo);
	}

	public static void setAdress(WebDriver wd, String address) throws InterruptedException {
		wd.findElement(By.id(FrontEndConstants.ADDRESS_CITY_ID)).sendKeys(address);
		Thread.sleep(6000);
		wd.findElement(By.id(FrontEndConstants.ADDRESS_CITY_SELECT)).click();
	}

	public static void setCountry(WebDriver wd, String country) throws InterruptedException {
		wd.findElement(By.className(FrontEndConstants.COUNTRY_ID)).sendKeys(country);
	}

}
