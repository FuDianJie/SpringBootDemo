package cn.fu.filtel;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关 zuul的过滤器 这里假设登录验证功能
 */
@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {//过滤器类型
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {//过滤顺序
        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1; //请求头之前
    }

    @Override
    public boolean shouldFilter() {//是否执行过滤器
        return true;
    }

    @Override
    public Object run() throws ZuulException {
       //获取请求上下文
        RequestContext context = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = context.getRequest();
        //获取请求的参数 access-token
        String token = request.getParameter("access-token");
        //判断是否存在
        if(StringUtils.isBlank(token)){
            //如果未存在，则说明未登录，进行拦截
            context.setSendZuulResponse(false);
            //返回403
            context.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
