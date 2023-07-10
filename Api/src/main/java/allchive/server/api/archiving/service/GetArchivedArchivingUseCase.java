package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetArchivedArchivingUseCase {
    // TODO : 이거 네이밍 한번 수정해야함
    private final ArchivingAdaptor archivingAdaptor;

    @Transactional(readOnly = true)
    public SliceResponse<ArchivingResponse> execute(Category category, Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        Slice<ArchivingResponse> archivingSlices =
                archivingAdaptor
                        .querySliceArchivingByUserId(userId, category, pageable)
                        .map(
                                archiving ->
                                        ArchivingResponse.of(
                                                archiving,
                                                archiving.getPinUserId().contains(userId)));
        return SliceResponse.of(archivingSlices);
    }
}
