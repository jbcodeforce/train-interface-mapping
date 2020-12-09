package ibm.gse.eda;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ibm.gse.eda.app.dto.TrainSearchRequest;
import ibm.gse.eda.app.dto.TrainSearchResponse;
import ibm.gse.eda.domain.router.TrainRouter;

@Path("/consolidatorA")
public class ConsolidatorASearchResource {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TrainSearchResponse searchRouters(TrainSearchRequest searchRequest){
        TrainSearchResponse rep = new TrainSearchResponse();
        rep.routingID = UUID.randomUUID().toString();
        rep.routerList = new ArrayList<TrainRouter>();
        rep.routerList.add( new TrainRouter("SNCF",searchRequest.originLocation,searchRequest.destinationLocation));
        rep.routerList.add( new TrainRouter("DB",searchRequest.originLocation,searchRequest.destinationLocation));
       
        return rep;
        
    }
}