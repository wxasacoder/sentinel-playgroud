//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.wx;

public class Result<T> {
    public static final String HTTP_SUCCESS_CODE = "200";
    private String status;
    private String errorCode;
    private String errorMsg;
    private T content;

    public Result(T content, String status, String errorCode, String errorMsg) {
        this.content = content;
        this.status = status;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Result() {
        this.status = Status.SUCCESS.name();
    }

    public Result(T content) {
        this.status = Status.SUCCESS.name();
        this.content = content;
    }

    public Result(String errorCode, String errorMsg, Status status) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.status = status.name();
    }

    public static Result error(String errorCode, String errorMsg) {
        return new Result(errorCode, errorMsg, Status.ERROR);
    }

    public String getStatus() {
        return this.status;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public T getContent() {
        return this.content;
    }

    public Result<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$status = this.getStatus();
                    Object other$status = other.getStatus();
                    if (this$status == null) {
                        if (other$status == null) {
                            break label59;
                        }
                    } else if (this$status.equals(other$status)) {
                        break label59;
                    }

                    return false;
                }

                Object this$errorCode = this.getErrorCode();
                Object other$errorCode = other.getErrorCode();
                if (this$errorCode == null) {
                    if (other$errorCode != null) {
                        return false;
                    }
                } else if (!this$errorCode.equals(other$errorCode)) {
                    return false;
                }

                Object this$errorMsg = this.getErrorMsg();
                Object other$errorMsg = other.getErrorMsg();
                if (this$errorMsg == null) {
                    if (other$errorMsg != null) {
                        return false;
                    }
                } else if (!this$errorMsg.equals(other$errorMsg)) {
                    return false;
                }

                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if (this$content == null) {
                    if (other$content != null) {
                        return false;
                    }
                } else if (!this$content.equals(other$content)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Result;
    }

    public String toString() {
        return "Result(status=" + this.getStatus() + ", errorCode=" + this.getErrorCode() + ", errorMsg=" + this.getErrorMsg() + ", content=" + this.getContent() + ")";
    }

    public static enum Status {
        SUCCESS,
        ERROR;

        private Status() {
        }
    }
}
