package ibm.gse.eda.domain;

import javax.enterprise.context.ApplicationScoped;

import ibm.gse.eda.app.dto.TrainSearchRequest;
import ibm.gse.eda.app.dto.TrainSearchResponse;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class SearchService {

    
	public Uni<TrainSearchResponse> searchService(TrainSearchRequest searchRequest) {
        TrainSearchResponse aggregatedResponse = new TrainSearchResponse();
        return Uni.createFrom().item(aggregatedResponse);
	}

	public Uni<TrainOffer> getOffer(String offerId) {
		return null;
	}
    
}
