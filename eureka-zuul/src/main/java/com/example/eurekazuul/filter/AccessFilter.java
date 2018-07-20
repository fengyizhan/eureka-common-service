package com.example.eurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: AccessFilter
 * @Author: long-zp
 * @Date: 2018/7/20 10:01
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Log
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /**
         * pre 代表在ing求被路由前执行
         */
        return "pre";
    }

    @Override
    public int filterOrder() {
        /**
         * 过滤器执行的顺序
         */
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        /**
         * 判断过滤器是否执行
         */
        return true;
    }

    /**
     * 具体的过滤逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info("send " + request.getMethod() + " Request to " + request.getRequestURL().toString());
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            log.warning("access token is empty");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
