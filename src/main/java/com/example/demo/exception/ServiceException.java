package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
    public class ServiceException  extends RuntimeException {
        private String code;

        public ServiceException(String code, String msg) {
            super(msg);
            this.code = code;
        }

}