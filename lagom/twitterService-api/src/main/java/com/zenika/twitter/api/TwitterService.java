/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.zenika.twitter.api;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.List;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

/**
 * The tweets service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the TwitterService.
 */
public interface TwitterService extends Service {

    /**
     * Example: curl http://localhost:9000/api/hello/Alice
     */
    ServiceCall<String, NotUsed, Source<String, ?>> tweets();

    @Override
    default Descriptor descriptor() {
        return named("twitterservice").with(
                pathCall("/api/twitter/:term", tweets())
        ).withAutoAcl(true);
    }
}
