package com.neo.gw.services;

import com.neo.gw.controller.vm.ConfigurationLazySupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

public class LimitProviderService {

    private Logger logger = LoggerFactory.getLogger(LimitProviderService.class);

    public ConfigurationLazySupplier getLimitForRequest(long tps, String key, long perSecond) {

//        try {
//            ConfigurationLazySupplier config = new ConfigurationLazySupplier();
//
//            Supplier<BucketConfiguration> bucketConfiguration = () -> Bucket4j.extension(Coherence.class).builder()
//                    .addLimit(Bandwidth.classic(tps , Refill.intervally(tps, Duration.ofMinutes(perSecond))))
//                    .build(tradesCache, key, RecoveryStrategy.RECONSTRUCT);
//
//            config.setConfig(bucketConfiguration);
//            config.setKey(key);
//            return config;
//        } catch (Exception e) {
//            e.printStackTrace();
//           return null;
//        }
        return null;
    }
}
