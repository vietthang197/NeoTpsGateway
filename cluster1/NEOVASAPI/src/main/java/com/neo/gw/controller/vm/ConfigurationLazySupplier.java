package com.neo.gw.controller.vm;

import io.github.bucket4j.BucketConfiguration;

import java.util.function.Supplier;

public class ConfigurationLazySupplier {
    private String key;

    private Supplier<BucketConfiguration> config;

    public ConfigurationLazySupplier() {
    }

    public ConfigurationLazySupplier(String key, Supplier<BucketConfiguration> config) {
        this.key = key;
        this.config = config;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Supplier<BucketConfiguration> getConfig() {
        return config;
    }

    public void setConfig(Supplier<BucketConfiguration> config) {
        this.config = config;
    }
}
