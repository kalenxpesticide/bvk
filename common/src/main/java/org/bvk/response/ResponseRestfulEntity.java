package org.bvk.response;

import org.bvk.enumeration.ResponseCode;

public class ResponseRestfulEntity {
    private int code;
    private String message;
    private Object data;
    private boolean successed;

    public ResponseRestfulEntity(int code, String message, Object data, boolean successed) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.successed = successed;
    }

    public ResponseRestfulEntity(ResponseCode responseCode, Object data, boolean successed) {
        this.code = responseCode.getCode();
        this.message = responseCode.getDesc();
        this.data = data;
        this.successed = successed;
    }

    public ResponseRestfulEntity(ResponseCode responseCode, boolean successed) {
        this.code = responseCode.getCode();
        this.message = responseCode.getDesc();
        this.successed = successed;
    }

    /**
     * @return int return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Object return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return boolean return the successed
     */
    public boolean isSuccessed() {
        return successed;
    }

    /**
     * @param successed the successed to set
     */
    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

}
