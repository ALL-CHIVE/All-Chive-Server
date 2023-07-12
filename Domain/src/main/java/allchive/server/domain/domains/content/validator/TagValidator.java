package allchive.server.domain.domains.content.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.exception.exceptions.NoAuthorityUpdateException;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class TagValidator {
    private final TagAdaptor tagAdaptor;

    public void verifyUser(Long tagId, Long userId) {
        tagAdaptor.findById(tagId).validateUser(userId);
    }
}
