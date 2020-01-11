package com.example.router_core.exception;


public class NoRouteFoundException extends RuntimeException {

    public NoRouteFoundException(String detailMessage) {
        super(detailMessage);
    }
}
