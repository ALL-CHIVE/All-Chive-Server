package allchive.server.domain.domains.recycle.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.recycle.adaptor.RecycleAdaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import allchive.server.domain.domains.recycle.exception.exceptions.RecycleArchivingNotFoundException;
import allchive.server.domain.domains.recycle.exception.exceptions.RecycleContentNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class RecycleValidator {
    private final RecycleAdaptor recycleAdaptor;

    public void validateExist(List<Long> archivingIds, List<Long> contentIds, Long userId) {
        List<Recycle> recycleList =
                recycleAdaptor.queryRecycleByUserIdInArchivingIdListAndContentIdList(
                        archivingIds, contentIds, userId);
        Long archivingCnt =
                recycleList.stream()
                        .filter(recycle -> recycle.getRecycleType().equals(RecycleType.ARCHIVING))
                        .count();
        if (archivingCnt != archivingIds.size()) {
            throw RecycleArchivingNotFoundException.EXCEPTION;
        }
        Long contentCnt =
                recycleList.stream()
                        .filter(recycle -> recycle.getRecycleType().equals(RecycleType.CONTENT))
                        .count();
        if (contentCnt != contentIds.size()) {
            throw RecycleContentNotFoundException.EXCEPTION;
        }
    }
}
