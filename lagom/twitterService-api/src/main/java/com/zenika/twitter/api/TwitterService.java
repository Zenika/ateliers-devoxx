/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.zenika.twitter.api;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.*;

/**
 * The tweets service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the TwitterService.
 */
public interface TwitterService extends Service {

    /**
     * Returns one stream of tweets
     */
    ServiceCall<NotUsed, NotUsed, Source<String, ?>> tweets();

    /**
     * Updated the filter of tweets' stream
     */
    ServiceCall<String, NotUsed, NotUsed> updateFilter();

    @Override
    default Descriptor descriptor() {
        return named("twitterservice").with(
                namedCall("/api/twitter", tweets()),
                pathCall("/api/twitter/:filter", updateFilter()))
                .withAutoAcl(true);
    }
}
