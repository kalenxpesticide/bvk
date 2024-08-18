package org.bvk.enumeration;

public enum ResponseCode {
    SUCCESS(0, "Success"),
    PRODUCT_NOT_FOUND(1, "Product not found"),
    INSUFICIENT_STOCK(2, "Insuficient stock"),
    EMPTY_STOCK(3, "Empty stock"),
    INTERNAL_SYSTEM_ERROR(9999, "Empty stock");

    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
