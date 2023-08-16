package allchive.server.api.search.model.dto.response;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.common.page.PageResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchResponse {
    PageResponse<ArchivingResponse> archivings;
    PageResponse<ArchivingResponse> community;

    @Builder
    private SearchResponse(
            PageResponse<ArchivingResponse> archivings, PageResponse<ArchivingResponse> community) {
        this.archivings = archivings;
        this.community = community;
    }

    public static SearchResponse forAll(
            PageResponse<ArchivingResponse> archivings, PageResponse<ArchivingResponse> community) {
        return SearchResponse.builder().archivings(archivings).community(community).build();
    }

    public static SearchResponse forMy(PageResponse<ArchivingResponse> archivings) {
        return SearchResponse.builder().archivings(archivings).community(null).build();
    }

    public static SearchResponse forCommunity(PageResponse<ArchivingResponse> community) {
        return SearchResponse.builder().archivings(null).community(community).build();
    }
}
