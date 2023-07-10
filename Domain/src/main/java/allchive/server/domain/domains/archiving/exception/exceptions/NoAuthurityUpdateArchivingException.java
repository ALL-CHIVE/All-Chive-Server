package allchive.server.domain.domains.archiving.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.archiving.exception.ArchivingErrorCode;

public class NoAuthurityUpdateArchivingException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoAuthurityUpdateArchivingException();

    private NoAuthurityUpdateArchivingException() {
        super(ArchivingErrorCode.NO_AUTHORITY_UPDATE_ARCHIVING);
    }
}
