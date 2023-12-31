package allchive.server.api.archiving.model.dto.response;


import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArchivingsResponse {
    List<ArchivingResponse> archivings;

    @Builder
    private ArchivingsResponse(List<ArchivingResponse> archivings) {
        this.archivings = archivings;
    }

    public static ArchivingsResponse of(List<ArchivingResponse> archivings) {
        return ArchivingsResponse.builder().archivings(archivings).build();
    }
}
