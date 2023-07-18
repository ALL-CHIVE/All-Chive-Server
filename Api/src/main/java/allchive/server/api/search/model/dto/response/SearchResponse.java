package allchive.server.api.search.model.dto.response;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.common.slice.SliceResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchResponse {
    SliceResponse<ArchivingResponse> archivings;
    SliceResponse<ArchivingResponse> community;

    @Builder
    private SearchResponse(
            SliceResponse<ArchivingResponse> archivings,
            SliceResponse<ArchivingResponse> community) {
        this.archivings = archivings;
        this.community = community;
    }

    public static SearchResponse forAll(
            SliceResponse<ArchivingResponse> archivings,
            SliceResponse<ArchivingResponse> community) {
        return SearchResponse.builder().archivings(archivings).community(community).build();
    }

    public static SearchResponse forMy(SliceResponse<ArchivingResponse> archivings) {
        return SearchResponse.builder().archivings(archivings).community(null).build();
    }

    public static SearchResponse forCommunity(SliceResponse<ArchivingResponse> community) {
        return SearchResponse.builder().archivings(null).community(community).build();
    }
}
