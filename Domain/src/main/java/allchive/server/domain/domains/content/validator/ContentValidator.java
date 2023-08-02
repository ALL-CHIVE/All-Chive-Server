package allchive.server.domain.domains.content.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.exception.exceptions.AlreadyDeletedContentException;
import allchive.server.domain.domains.content.exception.exceptions.ContentNotFoundException;
import allchive.server.domain.domains.content.exception.exceptions.NoAuthorityUpdateContentException;
import java.util.List;

import allchive.server.domain.domains.content.exception.exceptions.NotPublicContentException;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class ContentValidator {
    private final ContentAdaptor contentAdaptor;
    private final ArchivingAdaptor archivingAdaptor;

    public void validateExistInIdList(List<Long> contentIdList) {
        List<Content> contentList = contentAdaptor.findAllByIdIn(contentIdList);
        if (contentList.size() != contentIdList.size()) {
            throw ContentNotFoundException.EXCEPTION;
        }
    }

    public void verifyUserInIdList(Long userId, List<Long> contentIds) {
        List<Long> archivingIds =
                contentAdaptor.findAllByIdIn(contentIds).stream()
                        .map(Content::getArchivingId)
                        .distinct()
                        .toList();
        List<Archiving> archivingList = archivingAdaptor.findAllByIdIn(archivingIds);
        archivingList.forEach(
                archiving -> {
                    if (!archiving.getUserId().equals(userId)) {
                        throw NoAuthorityUpdateContentException.EXCEPTION;
                    }
                });
    }

    public void validateExistById(Long contentId) {
        if (!contentAdaptor.queryContentExistById(contentId)) {
            throw ContentNotFoundException.EXCEPTION;
        }
    }

    public void verifyUser(Long contentId, Long userId) {
        Long archivingId = contentAdaptor.findById(contentId).getArchivingId();
        Long archivingUserId = archivingAdaptor.findById(archivingId).getUserId();
        if (!archivingUserId.equals(userId)) {
            throw NoAuthorityUpdateContentException.EXCEPTION;
        }
    }

    public void validateNotDelete(Long contentId) {
        if (contentAdaptor.findById(contentId).isDeleteStatus()) {
            throw AlreadyDeletedContentException.EXCEPTION;
        }
    }

    public void validatePublic(Long contentId, Long userId) {
        Content content = contentAdaptor.findById(contentId);
        Archiving archiving = archivingAdaptor.findById(content.getArchivingId());
        if (archiving.getPublicStatus().equals(Boolean.FALSE) && !archiving.getUserId().equals(userId)) {
            throw NotPublicContentException.EXCEPTION;
        }
    }
}
