package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.archiving.model.dto.response.ArchivingsResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import java.util.List;
import java.util.stream.Collectors;

import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetPopularArchivingUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final ScrapAdaptor scrapAdaptor;

    public ArchivingsResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Scrap> scraps = scrapAdaptor.findAllByUserId(userId);
        List<ArchivingResponse> archivingResponses =
                archivingAdaptor.queryArchivingOrderByScrapCntLimit5().stream()
                        .filter(archiving -> archiving.getScrapCnt() > 0)
                        .map(archiving ->
                            ArchivingResponse.of(archiving, calculateIsScrap(scraps, archiving))
                        )
                        .collect(Collectors.toList());
        return ArchivingsResponse.of(archivingResponses);
    }

    private Boolean calculateIsScrap(List<Scrap> scraps, Archiving archiving) {
        if (scraps.stream().filter(scrap -> scrap.getArchivingId().equals(archiving.getId())).count() == 1L) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
