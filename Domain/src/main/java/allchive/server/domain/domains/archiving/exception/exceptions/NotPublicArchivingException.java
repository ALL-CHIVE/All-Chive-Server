package allchive.server.domain.domains.archiving.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.archiving.exception.ArchivingErrorCode;

public class NotPublicArchivingException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NotPublicArchivingException();

    private NotPublicArchivingException() {
        super(ArchivingErrorCode.NOT_PUBLIC_ARCHIVING);
    }
}
