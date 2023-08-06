package allchive.server.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DistributedLockType {
    ARCHIVING_SCRAP("archivingScrap"),
    ARCHIVING_PIN("archivingPin");

    private String lockName;
}
