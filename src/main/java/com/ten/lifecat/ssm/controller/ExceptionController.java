package com.ten.lifecat.ssm.controller;

import com.ten.lifecat.ssm.dto.ResponseCode;
import com.ten.lifecat.ssm.dto.ResultModel;
import com.ten.lifecat.ssm.exception.RequestException;
import com.ten.lifecat.ssm.exception.impl.RequestDataFormatException;
import com.ten.lifecat.ssm.exception.impl.RequestDataNullException;
import com.ten.lifecat.ssm.exception.impl.ResourceExecuteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    private static final String PARAM_ERROR = ResponseCode.PARAM_ERROR.getMsg() + ":";
    private static final String DATABASE_ERROR = ResponseCode.DATABASE_ERROR.getMsg() + ":";
    private static final String EXCEPTION = "EXCEPTION";

    /**
     * 请求参数错误 401
     *
     * @throws RequestDataNullException   请求参数为空
     * @throws RequestDataFormatException 请求参数格式错误
     */
    @ResponseBody
    @ExceptionHandler({RequestDataNullException.class, RequestDataFormatException.class})
    public ResultModel requestError(RequestException e) {
        logger.warn(PARAM_ERROR + e.getErrorMsg());
        return new ResultModel(ResponseCode.PARAM_ERROR, EXCEPTION);
    }

    /**
     * 数据库操作错误 501
     *
     * @throws ResourceExecuteException 数据库操作失败
     */
    @ResponseBody
    @ExceptionHandler(ResourceExecuteException.class)
    public ResultModel resourceExecuteError(ResourceExecuteException e) {
        logger.warn(DATABASE_ERROR + e.getErrorMsg());
        return new ResultModel(ResponseCode.DATABASE_ERROR, EXCEPTION);
    }
}
