package ibm.gse.eda.domain.router;

public class Location {
    public String type;
    public String code;

    public Location() {
        super();
    }

	public Location(String locationName) {
        this.code = locationName;
        this.type = "station";
	}
}
