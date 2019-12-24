package com.egrand.cloud.zuul.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul代理后响应处理器
 *
 * @author liuyadu
 */
@Slf4j
public class ZuulResponseFilter extends ZuulFilter {

    public ZuulResponseFilter() {

    }

    /**
     * 是否应该执行该过滤器，如果是false，则不执行该filter
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器类型
     * 顺序: pre ->routing -> post ,以上3个顺序出现异常时都可以触发error类型的filter
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * 同filterType类型中，order值越大，优先级越低
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 2;
    }

    /**
     *
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 把路由的serviceId放入作用域
        request.setAttribute(FilterConstants.SERVICE_ID_KEY, ctx.get(FilterConstants.SERVICE_ID_KEY));
        System.out.println("=======================ZuulResponseFilter==================");
        System.out.println("SERVICE_ID = " + ctx.get(FilterConstants.SERVICE_ID_KEY));
        System.out.println("=======================ZuulResponseFilter==================");
        return null;
    }
}
