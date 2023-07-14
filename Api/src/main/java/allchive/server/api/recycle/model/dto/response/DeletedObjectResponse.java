package allchive.server.api.recycle.model.dto.response;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.content.model.dto.response.ContentResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeletedObjectResponse {
    private List<ContentResponse> contents;
    private List<ArchivingResponse> archivings;

    @Builder
    private DeletedObjectResponse(
            List<ContentResponse> contents, List<ArchivingResponse> archivings) {
        this.contents = contents;
        this.archivings = archivings;
    }

    public static DeletedObjectResponse of(
            List<ArchivingResponse> archivings, List<ContentResponse> contents) {
        return DeletedObjectResponse.builder().archivings(archivings).contents(contents).build();
    }
}
