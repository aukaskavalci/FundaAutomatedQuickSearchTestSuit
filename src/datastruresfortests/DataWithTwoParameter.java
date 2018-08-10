package datastruresfortests;

/*
 * This class is used as a structure of the test cases of QuickSearch page. 
 * In these cases there are two option one is "Newly Built" and the other is "Recreation". 
 * In both cases there are two input in page as "City/Adress","km".
 */
public class DataWithTwoParameter {
	String tabName;
	String adress;
	String kmDistance;

	public DataWithTwoParameter(String tabName, String adress, String kmDistance) {
		this.tabName = tabName;
		this.adress = adress;
		this.kmDistance = kmDistance;
	}

	public String getTabName() {
		return tabName;
	}

	public String getAdress() {
		return adress;
	}

	public String getKmDistance() {
		return kmDistance;
	}
}
