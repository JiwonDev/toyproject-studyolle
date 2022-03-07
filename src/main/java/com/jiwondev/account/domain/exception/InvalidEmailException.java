package com.jiwondev.account.domain.exception;

import com.jiwondev.common.exception.BaseException;
import com.jiwondev.common.response.ErrorCode;

public final class InvalidEmailException extends BaseException {

    public InvalidEmailException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
