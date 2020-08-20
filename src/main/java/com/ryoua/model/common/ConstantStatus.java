package com.ryoua.model.common;

public class ConstantStatus {

    /**
     * 请求类型 0-get  1-post 2-put 3-delete
     */
    public enum RequestType {

    	GET, POST, PUT, DELETE;

        public Integer getValue() {
            return this.ordinal();
        }
    }

}
