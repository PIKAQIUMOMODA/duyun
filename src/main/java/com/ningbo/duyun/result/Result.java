package com.ningbo.duyun.result;

public enum Result {

    REQUEST_FAILED(10000,"请求失败");

    private  int code;
    private  String message;

     Result(int code,String message)
    {
        this.code=code;
        this.message=message;
    }

    @Override
    public String toString()
    {
        return "{\"code\":"+code+",\"message\":\""+message+"\"}";
    }
}
