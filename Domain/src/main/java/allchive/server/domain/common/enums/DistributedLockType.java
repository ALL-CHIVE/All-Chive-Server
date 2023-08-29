package allchive.server.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DistributedLockType {
    ARCHIVING_SCRAP("archivingScrap"),
    ARCHIVING_PIN("archivingPin"),
    ARCHIVING("archiving"),

    BLOCK("block"),
    CONTENT("content"),
    RECYCLE("recycle"),
    REPORT("report"),
    TAG("tag"),
    USER("user");

    private String lockName;
}
