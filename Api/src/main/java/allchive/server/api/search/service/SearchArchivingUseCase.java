package allchive.server.api.search.service;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.common.util.StringParamUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.search.model.dto.response.SearchResponse;
import allchive.server.api.search.model.enums.ArchivingType;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.block.adaptor.BlockAdaptor;
import allchive.server.domain.domains.block.domain.Block;
import allchive.server.domain.domains.content.adaptor.ContentTagGroupAdaptor;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.search.adaptor.LatestSearchAdaptor;
import allchive.server.domain.domains.search.domain.LatestSearch;
import allchive.server.domain.domains.search.service.LatestSearchDomainService;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class SearchArchivingUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final ScrapAdaptor scrapAdaptor;
    private final BlockAdaptor blockAdaptor;
    private final LatestSearchAdaptor latestSearchAdaptor;
    private final LatestSearchDomainService latestSearchDomainService;
    private final TagAdaptor tagAdaptor;
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;

    @Transactional
    public SearchResponse execute(Pageable pageable, ArchivingType type, String word) {
        validateExecution(word);
        Long userId = SecurityUtil.getCurrentUserId();
        Set<Long> tagArchivingIds = getTagArchivingIds(userId, word);
        SliceResponse<ArchivingResponse> my;
        SliceResponse<ArchivingResponse> community;
        renewalLatestSearch(userId, word);
        switch (type) {
            case ALL -> {
                my = getMyArchivings(userId, word, pageable, tagArchivingIds);
                community = getCommunityArchivings(userId, word, pageable, tagArchivingIds);
                return SearchResponse.forAll(my, community);
            }
            case MY -> {
                my = getMyArchivings(userId, word, pageable, tagArchivingIds);
                return SearchResponse.forMy(my);
            }
            case COMMUNITY -> {
                community = getCommunityArchivings(userId, word, pageable, tagArchivingIds);
                return SearchResponse.forCommunity(community);
            }
        }
        return null;
    }

    private void validateExecution(String word) {
        StringParamUtil.checkEmptyString(word);
    }

    private Set<Long> getTagArchivingIds(Long userId, String word) {
        List<Tag> tags = tagAdaptor.findAllByUserIdAndName(userId, word);
        return contentTagGroupAdaptor.queryContentTagGroupByTagInWithContent(tags).stream()
                .map(contentTagGroup -> contentTagGroup.getContent().getArchivingId())
                .collect(Collectors.toSet());
    }

    private void renewalLatestSearch(Long userId, String keyword) {
        List<LatestSearch> searches = latestSearchAdaptor.findAllByUserIdOrderByCreatedAt(userId);
        if (!isExistSearchKeyword(searches, keyword)) {
            if (searches.size() == 5) {
                latestSearchDomainService.delete(searches.get(0));
            }
            LatestSearch newSearch = LatestSearch.of(keyword, userId);
            latestSearchDomainService.save(newSearch);
        }
    }

    private boolean isExistSearchKeyword(List<LatestSearch> searches, String keyword) {
        return !searches.stream()
                .filter(search -> search.getKeyword().equals(keyword))
                .toList()
                .isEmpty();
    }

    private SliceResponse<ArchivingResponse> getMyArchivings(
            Long userId, String keyword, Pageable pageable, Set<Long> tagArchivingIds) {
        Slice<ArchivingResponse> archivingSlices =
                archivingAdaptor
                        .querySliceArchivingByUserIdAndKeywordsOrderByTagArchvingIds(
                                userId, keyword, pageable, tagArchivingIds)
                        .map(archiving -> ArchivingResponse.of(archiving, Boolean.FALSE));
        return SliceResponse.of(archivingSlices);
    }

    private SliceResponse<ArchivingResponse> getCommunityArchivings(
            Long userId, String keyword, Pageable pageable, Set<Long> tagArchivingIds) {
        List<Long> archivingIdList =
                scrapAdaptor.findAllByUserId(userId).stream().map(Scrap::getArchivingId).toList();
        List<Long> blockList =
                blockAdaptor.findByBlockFrom(userId).stream().map(Block::getBlockUser).toList();
        Slice<ArchivingResponse> archivingSlices =
                archivingAdaptor
                        .querySliceArchivingByKeywordExceptBlockOrderByTagArchvingIds(
                                archivingIdList, blockList, keyword, pageable, tagArchivingIds)
                        .map(archiving -> ArchivingResponse.of(archiving, Boolean.FALSE));
        return SliceResponse.of(archivingSlices);
    }
}
