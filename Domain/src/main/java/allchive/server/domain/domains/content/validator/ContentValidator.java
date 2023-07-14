package allchive.server.domain.domains.content.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.exception.exceptions.ContentNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Validator
@RequiredArgsConstructor
public class ContentValidator {
    private final ContentAdaptor contentAdaptor;

    public void validateExistInIdList(List<Long> contentIdList) {
        List<Content> contentList = contentAdaptor.findAllByIdIn(contentIdList);
        if (contentList.size() != contentIdList.size()) {
            throw ContentNotFoundException.EXCEPTION;
        }
    }
}
