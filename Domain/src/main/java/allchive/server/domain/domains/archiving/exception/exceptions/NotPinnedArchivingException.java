package allchive.server.domain.domains.archiving.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.archiving.exception.ArchivingErrorCode;

public class NotPinnedArchivingException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NotPinnedArchivingException();

    private NotPinnedArchivingException() {
        super(ArchivingErrorCode.NOT_PINNED_ARCHIVING);
    }
}
