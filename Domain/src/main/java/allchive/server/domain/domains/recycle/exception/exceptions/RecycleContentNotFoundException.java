package allchive.server.domain.domains.recycle.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.recycle.exception.RecycleErrorCode;

public class RecycleContentNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new RecycleContentNotFoundException();

    private RecycleContentNotFoundException() {
        super(RecycleErrorCode.RECYCLE_CONTENT_NOT_FOUND);
    }
}
