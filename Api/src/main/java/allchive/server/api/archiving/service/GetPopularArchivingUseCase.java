package allchive.server.api.archiving.service;

import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.archiving.model.dto.response.ArchivingTitleResponse;
import allchive.server.api.archiving.model.dto.response.ArchivingsResponse;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetPopularArchivingUseCase {
    private final ArchivingAdaptor archivingAdaptor;

    public ArchivingsResponse execute() {
        List<ArchivingResponse> archivingResponses = archivingAdaptor.queryArchivingOrderByScrapCntLimit5().stream()
                .filter(archiving -> archiving.getScrapCnt() > 0)
                .map(archiving -> ArchivingResponse.of(archiving, Boolean.FALSE))
                .collect(Collectors.toList());
        return ArchivingsResponse.of(archivingResponses);
    }
}
