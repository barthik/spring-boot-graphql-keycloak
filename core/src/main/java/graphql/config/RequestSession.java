package graphql.config;


import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestSession {
    private Map<String, String> headers;

    public void safePut(String header, Object value) {
        if(headers == null) {
            headers = new HashMap<>();
        }

        headers.put(header, String.valueOf(value));
    }

    public void resolve(Page<?> page) {
        safePut("X-Total-Elements", page.getTotalElements());
        safePut("X-Total-Pages", page.getTotalPages());
    }
}
