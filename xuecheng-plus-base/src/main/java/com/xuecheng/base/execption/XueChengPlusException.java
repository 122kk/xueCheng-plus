package com.xuecheng.base.execption;

/**
 * @author kj
 * @date 2023/3/14
 * @apiNote
 */
public class XueChengPlusException extends RuntimeException {

    private String errMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String message) {
        super(message);
        this.errMessage = message;
    }

    public String getErrMessage(){
        return errMessage;
    }

    public static void cast(String errMessage){
        throw new XueChengPlusException(errMessage);
    }
    public static void cast(com.xuecheng.base.execption.CommonError commonError){
        throw new XueChengPlusException(commonError.getErrMessage());
    }
}
