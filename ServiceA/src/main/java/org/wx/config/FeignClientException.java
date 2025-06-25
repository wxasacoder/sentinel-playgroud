//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.wx.config;

import feign.FeignException;

public class FeignClientException extends FeignException {
    private String errorCode;
    private String errorMsg;

    public FeignClientException(int status, String errorCode, String message) {
        super(status, message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public FeignClientException(int status, String errorCode, String message, Throwable cause) {
        this(status, message, cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public FeignClientException(int status, String message, Throwable cause) {
        super(status, message, cause);
        this.errorMsg = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
