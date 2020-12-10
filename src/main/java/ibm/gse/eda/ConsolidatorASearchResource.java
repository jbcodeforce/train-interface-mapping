package ibm.gse.eda;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ibm.gse.eda.app.dto.TrainSearchRequest;
import ibm.gse.eda.app.dto.TrainSearchResponse;
import ibm.gse.eda.domain.SearchService;

@Path("/consolidatorA")
public class ConsolidatorASearchResource {


    @Inject
    SearchService service;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TrainSearchResponse searchRouters(TrainSearchRequest searchRequest){
        TrainSearchResponse rep = service.searchService(searchRequest);
        return rep;
    }
}