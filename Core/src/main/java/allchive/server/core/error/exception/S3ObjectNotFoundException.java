package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class S3ObjectNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new S3ObjectNotFoundException();

    private S3ObjectNotFoundException() {
        super(GlobalErrorCode.S3_OBJECT_NOT_FOUND);
    }
}
