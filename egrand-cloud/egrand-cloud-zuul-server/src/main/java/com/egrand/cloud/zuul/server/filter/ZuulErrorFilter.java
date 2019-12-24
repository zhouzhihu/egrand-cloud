package com.egrand.cloud.zuul.server.filter;

import com.egrand.cloud.core.exception.OpenGlobalExceptionHandler;
import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.core.utils.StringUtils;
import com.egrand.cloud.core.utils.WebUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * zuul错误过滤器
 *
 * @author liuyadu
 */
@Slf4j
public class ZuulErrorFilter extends ZuulFilter {

    public ZuulErrorFilter() {

    }

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // 代理错误日志记录
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        Exception exception = new Exception(ctx.getThrowable());
        if(StringUtils.toBoolean(ctx.get("rateLimitExceeded"))){
            exception = new Exception(HttpStatus.TOO_MANY_REQUESTS.name());
        }
        ResultBody resultBody = OpenGlobalExceptionHandler.resolveException(exception,request.getRequestURI());
        WebUtils.writeJson(response, resultBody);
        return null;
    }
}
