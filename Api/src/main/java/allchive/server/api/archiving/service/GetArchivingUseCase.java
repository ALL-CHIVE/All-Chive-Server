package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.block.adaptor.BlockAdaptor;
import allchive.server.domain.domains.block.domain.Block;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetArchivingUseCase {
    private final ScrapAdaptor scrapAdaptor;
    private final BlockAdaptor blockAdaptor;
    private final ArchivingAdaptor archivingAdaptor;

    @Transactional(readOnly = true)
    public SliceResponse<ArchivingResponse> execute(Category category, Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Long> archivingIdList =
                scrapAdaptor.findAllByUserId(userId).stream().map(Scrap::getArchivingId).toList();
        List<Long> blockList =
                blockAdaptor.findByBlockFrom(userId).stream().map(Block::getBlockUser).toList();
        Slice<ArchivingResponse> archivingSlices =
                archivingAdaptor
                        .querySliceArchivingExceptBlock(archivingIdList, blockList, category, pageable)
                        .map(
                                archiving ->
                                        ArchivingResponse.of(
                                                archiving,
                                                archivingIdList.contains(archiving.getId())));
        return SliceResponse.of(archivingSlices);
    }
}
