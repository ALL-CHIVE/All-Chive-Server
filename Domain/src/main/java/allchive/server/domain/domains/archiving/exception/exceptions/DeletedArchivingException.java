package allchive.server.domain.domains.archiving.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.archiving.exception.ArchivingErrorCode;

public class DeletedArchivingException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new DeletedArchivingException();

    private DeletedArchivingException() {
        super(ArchivingErrorCode.DELETED_ARCHIVING);
    }
}
