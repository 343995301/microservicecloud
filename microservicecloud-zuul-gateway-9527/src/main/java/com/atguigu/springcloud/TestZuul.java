package com.atguigu.springcloud;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class TestZuul extends ZuulFilter{
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {

        return false;
    }

    @Override
    public Object run() {
           RequestContext ctx = RequestContext.getCurrentContext();
//        ctx.put(FilterConstants.REQUEST_URI_KEY, modifiedRequestPath);
        String ssss= "aaaa";
        return null;
    }
}
