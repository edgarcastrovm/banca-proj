package com.banca.customer.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class RequestInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            // Generar un UUID como requestId
            String requestId = UUID.randomUUID().toString();

            // Agregar el requestId al MDC (Mapped Diagnostic Context) de Log4j2
            ThreadContext.put("requestId", requestId);

            return true;
        }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Limpiar el MDC después de la petición
        ThreadContext.clearAll();
    }
}
