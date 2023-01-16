package org.goafabric.calleeservice.crossfunctional;

import io.quarkus.oidc.OidcRequestContext;
import io.quarkus.oidc.OidcTenantConfig;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Slf4j
@Provider

@ApplicationScoped
public class HttpInterceptor implements ContainerRequestFilter, ContainerResponseFilter {
    @Inject SecurityIdentity securityIdentity;
    private static final ThreadLocal<String> tenantId = new ThreadLocal<>();
    private static final ThreadLocal<String> userName = new ThreadLocal<>();

    public static String getTenantId() { return tenantId.get(); }
    public static String getUserName() { return userName.get(); }

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        tenantId.set(request.getHeaderString("X-TenantId") != null ? request.getHeaderString("X-TenantId") : "0"); //TODO
        userName.set(request.getHeaderString("X-Auth-Request-Preferred-Username") != null ? request.getHeaderString("X-Auth-Request-Preferred-Username")
                :  securityIdentity.getPrincipal().getName());
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        tenantId.remove();
        userName.remove();
    }

    public Uni<OidcTenantConfig> resolve(RoutingContext routingContext, OidcRequestContext<OidcTenantConfig> requestContext) {
        final String tenantId = routingContext.request().getHeader("X-TenantId");

        final OidcTenantConfig tenantConfig = new OidcTenantConfig();

        tenantConfig.setTenantId(tenantId != null ? tenantId : "0");
        tenantConfig.setApplicationType(
                OidcTenantConfig.ApplicationType.valueOf(ConfigProvider.getConfig().getValue("quarkus.oidc.application-type", String.class).toUpperCase()));

        final OidcTenantConfig.Roles roles = new OidcTenantConfig.Roles();
        roles.setSource(OidcTenantConfig.Roles.Source.accesstoken); //config.getValue("quarkus.oidc.roles.source", OidcTenantConfig.Roles.Source.class));
        tenantConfig.setRoles(roles);

        tenantConfig.setClientId(ConfigProvider.getConfig().getValue("quarkus.oidc.client-id", String.class));
        tenantConfig.setAuthServerUrl(ConfigProvider.getConfig().getValue("quarkus.oidc.auth-server-url", String.class));
        return Uni.createFrom().item(tenantConfig);
    }

}
