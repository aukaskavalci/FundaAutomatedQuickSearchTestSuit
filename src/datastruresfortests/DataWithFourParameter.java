package datastruresfortests;

/*
 * This class is used as a structure of the test cases of QuickSearch page. 
 * In these cases there are two option one is "For Sale" and the other is "For Rent". 
 * In both cases there are four input in page as "City","km","PriceFrom","PriceTo".
 */
public class DataWithFourParameter {
	String tabName;
	String city;
	String distanceInKm;
	String priceFrom;
	String priceTo;

	public DataWithFourParameter(String tabName, String city, String distanceInKm, String priceFrom, String priceTo) {
		this.tabName = tabName;
		this.city = city;
		this.distanceInKm = distanceInKm;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
	}

	public String getTabName() {
		return tabName;
	}

	public String getCity() {
		return city;
	}

	public String getDistanceInKm() {
		return distanceInKm;
	}

	public String getPriceFrom() {
		return priceFrom;
	}

	public String getPriceTo() {
		return priceTo;
	}

}
