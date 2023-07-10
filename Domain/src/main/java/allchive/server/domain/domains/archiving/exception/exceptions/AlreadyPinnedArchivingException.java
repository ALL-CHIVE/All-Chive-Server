package allchive.server.domain.domains.archiving.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.archiving.exception.ArchivingErrorCode;

public class AlreadyPinnedArchivingException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new AlreadyPinnedArchivingException();

    private AlreadyPinnedArchivingException() {
        super(ArchivingErrorCode.ALREADY_PINNED_ARCHIVING);
    }
}
