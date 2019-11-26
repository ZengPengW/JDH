package com.jdh.security.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class StaticFileFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       // System.out.println(request.getRequestURI()+" :请求地址");
        String url=request.getRequestURI();
        if (url.lastIndexOf(".js")!=-1
            ||url.lastIndexOf(".css")!=-1
            || url.contains("material")||url.contains("/img")){
            response.setHeader("cache-control","public, max-age=31536000, s-maxage=31536000, immutable");

        }
        filterChain.doFilter(request,response);
    }
}
