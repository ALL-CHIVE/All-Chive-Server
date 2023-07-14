package allchive.server.domain.domains.recycle.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.recycle.exception.RecycleErrorCode;

public class RecycleArchivingNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new RecycleArchivingNotFoundException();

    private RecycleArchivingNotFoundException() {
        super(RecycleErrorCode.RECYCLE_ARCHIVING_NOT_FOUND);
    }
}
