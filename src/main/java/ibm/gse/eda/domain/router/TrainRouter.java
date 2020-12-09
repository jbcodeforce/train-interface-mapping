package ibm.gse.eda.domain.router;

public class TrainRouter {
    public String supplier;
    public boolean complete = false;
    public RequestedLocation requestedLocation;

    public TrainRouter(){}

	public TrainRouter(String supplierName,String originLocation, String destinationLocation) {
        this.supplier = supplierName;
        this.requestedLocation = new RequestedLocation();
        this.requestedLocation.origin = new Location(originLocation);
        this.requestedLocation.destination = new Location(destinationLocation);
	}
}
