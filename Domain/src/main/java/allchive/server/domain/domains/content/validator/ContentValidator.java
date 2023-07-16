package allchive.server.domain.domains.content.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.exception.exceptions.ContentNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class ContentValidator {
    private final ContentAdaptor contentAdaptor;
    private final ArchivingValidator archivingValidator;

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
        archivingValidator.verifyUserInIdList(userId, archivingIds);
    }

    public void validateExistById(Long contentId) {
        if (!contentAdaptor.queryContentExistById(contentId)) {
            throw ContentNotFoundException.EXCEPTION;
        }
    }
}
