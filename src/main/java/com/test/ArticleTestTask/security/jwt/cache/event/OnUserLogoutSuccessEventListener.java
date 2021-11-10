package com.test.ArticleTestTask.security.jwt.cache.event;

import com.test.ArticleTestTask.security.jwt.cache.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnUserLogoutSuccessEventListener implements ApplicationListener<OnUserLogoutSuccessEvent> {

    @Autowired
    private final TokenCache tokenCache;

    public OnUserLogoutSuccessEventListener(TokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }

    @Override
    public void onApplicationEvent(OnUserLogoutSuccessEvent event) {
            tokenCache.markLogoutEventForToken(event);
    }
}
