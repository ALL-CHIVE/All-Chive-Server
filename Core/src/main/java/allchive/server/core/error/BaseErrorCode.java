package allchive.server.core.error;


import allchive.server.core.dto.ErrorReason;

public interface BaseErrorCode {
    public ErrorReason getErrorReason();
}
