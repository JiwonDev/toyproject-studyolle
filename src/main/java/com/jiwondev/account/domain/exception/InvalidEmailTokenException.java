package com.jiwondev.account.domain.exception;

import com.jiwondev.common.exception.BaseException;
import com.jiwondev.common.response.ErrorCode;

public final class InvalidEmailTokenException extends BaseException {

    public InvalidEmailTokenException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
