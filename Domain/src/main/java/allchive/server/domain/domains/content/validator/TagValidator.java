package allchive.server.domain.domains.content.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.exception.exceptions.NoAuthorityUpdateTagException;
import allchive.server.domain.domains.content.exception.exceptions.TagNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class TagValidator {
    private final TagAdaptor tagAdaptor;

    public void verifyUser(Long tagId, Long userId) {
        tagAdaptor.findById(tagId).validateUser(userId);
    }

    public void validateExistTagsAndUser(List<Long> tagIds, Long userId) {
        List<Tag> tags = tagAdaptor.queryTagByTagIdIn(tagIds);
        if (tagIds.size() != tags.size()) {
            throw TagNotFoundException.EXCEPTION;
        }
        tags.forEach(tag -> tag.validateUser(userId));
    }
}
