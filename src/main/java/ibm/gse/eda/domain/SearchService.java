package ibm.gse.eda.domain;

import java.util.ArrayList;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import ibm.gse.eda.app.dto.TrainSearchRequest;
import ibm.gse.eda.app.dto.TrainSearchResponse;
import ibm.gse.eda.domain.router.TrainRouter;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;

@ApplicationScoped
public class SearchService {

	@Inject
    @Channel("search")
	Emitter<TrainSearchResponse> emitter;
	
	public TrainSearchResponse searchService(TrainSearchRequest searchRequest) {
		TrainSearchResponse aggregatedResponse = new TrainSearchResponse();
		aggregatedResponse.routingId = UUID.randomUUID().toString();
		aggregatedResponse.userId = searchRequest.userId;
        aggregatedResponse.routerList = new ArrayList<TrainRouter>();
        aggregatedResponse.routerList.add( new TrainRouter("SNCF",searchRequest.originLocation,searchRequest.destinationLocation));
        aggregatedResponse.routerList.add( new TrainRouter("DB",searchRequest.originLocation,searchRequest.destinationLocation));
        Message<TrainSearchResponse> record = KafkaRecord.of(aggregatedResponse.userId,aggregatedResponse);
        emitter.send(record );
		
        return aggregatedResponse;
	}

	public Uni<TrainOffer> getOffer(String offerId) {
		return null;
	}
    
}
