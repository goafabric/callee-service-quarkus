package org.goafabric.calleeservice.extensions;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.core.interception.jaxrs.PostMatchContainerRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Provider
@ApplicationScoped
public class HttpInterceptor implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger log = LoggerFactory.getLogger("HttpInterceptor");
    private final SecurityIdentity securityIdentity;

    public HttpInterceptor(SecurityIdentity securityIdentity) {
        this.securityIdentity = securityIdentity;
    }

    private static final ThreadLocal<String> tenantId = new ThreadLocal<>();
    private static final ThreadLocal<String> userName = new ThreadLocal<>();

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        tenantId.set(request.getHeaderString(request.getHeaderString("X-TenantId")));
        userName.set(request.getHeaderString("X-Auth-Request-Preferred-Username") != null ? request.getHeaderString("X-Auth-Request-Preferred-Username")
                :  securityIdentity != null ? securityIdentity.getPrincipal().getName() : "");
        //MDC.put("tenantId", getTenantId());
        if (request instanceof PostMatchContainerRequestContext) {
            var method = ((PostMatchContainerRequestContext) request).getResourceMethod().getMethod();
            log.info("{} called for user {} ", method.getDeclaringClass().getName() + "." + method.getName(), getUserName());
        }


    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        tenantId.remove();
        userName.remove();
        //MDC.remove("tenantId");
    }

    public static void setTenantId(String tenantId) {
        HttpInterceptor.tenantId.set(tenantId);
    }

    public static String getTenantId() {
        return tenantId.get() != null ? tenantId.get() : "0"; //tdo
    }

    public static String getUserName() {
        return userName.get();
    }

}
