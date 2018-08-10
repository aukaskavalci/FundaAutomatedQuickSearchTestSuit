package datastruresfortests;

/*
 * This class is used as a structure of the test cases of QuickSearch page. 
 * In these cases there is one option as "Europe" . 
 * In both cases there is one input in page as "Country".
 */
public class DataWithOneParameter {
	String country;
	String tabName;

	public DataWithOneParameter(String tabName, String country) {
		this.tabName = tabName;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public String getTabName() {
		return tabName;
	}
}
