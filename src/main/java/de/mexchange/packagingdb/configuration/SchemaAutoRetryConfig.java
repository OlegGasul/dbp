package de.mexchange.packagingdb.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("schema.autoRetry")
public class SchemaAutoRetryConfig {
    private int attempts = 5;
    private long interval = 1000;
    private boolean retryAll  = false;

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public boolean getRetryAll() {
        return retryAll;
    }

    public void setRetryAll(boolean retryAll) {
        this.retryAll = retryAll;
    }
}
