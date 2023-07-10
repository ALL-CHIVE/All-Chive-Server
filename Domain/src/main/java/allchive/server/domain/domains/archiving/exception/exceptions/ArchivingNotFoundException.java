package allchive.server.domain.domains.archiving.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.archiving.exception.ArchivingErrorCode;

public class ArchivingNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new ArchivingNotFoundException();

    private ArchivingNotFoundException() {
        super(ArchivingErrorCode.ARCHIVING_NOT_FOUND);
    }
}
