package ibm.gse.eda.app.dto;

import java.util.ArrayList;
import java.util.List;

import ibm.gse.eda.domain.Traveller;

public class TrainSearchRequest {
    public String userId;
    public String originLocation;
    public String originType;
    public String destinationLocation;
    public String destinationType;
    public String outwardDate;
    public int maxChanges;
    public int maxHops;
    public List<Traveller> travellerList = new ArrayList<Traveller>();
    
    public TrainSearchRequest(){}
}
