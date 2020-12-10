package ibm.gse.eda.app.dto;

import java.util.List;

import ibm.gse.eda.domain.router.TrainRouter;

public class TrainSearchResponse {
    public String routingId;
    public String userId;
    public String creationDate;
    public List<TrainRouter> routerList;
    
    public TrainSearchResponse(){}
}
