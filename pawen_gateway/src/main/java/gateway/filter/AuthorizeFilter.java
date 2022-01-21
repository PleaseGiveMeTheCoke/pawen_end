package gateway.filter;

import gateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        //获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        //判断当前请求是否为开放请求,若是,直接放行
        if(isUtilReq(request)){
            return chain.filter(exchange);
        }
        //获取请求头信息
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("token");
        if(StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        }
        try{
            JwtUtil.parseJWT(token);
        }catch (Exception e){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    private boolean isUtilReq(ServerHttpRequest request) {
        if(request.getURI().getPath().contains("/login/login")){
            return true;
        }
        if(request.getURI().getPath().contains("/login/refresh")){
            return true;
        }
        if(request.getURI().getPath().contains("/login/register")){
            return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
