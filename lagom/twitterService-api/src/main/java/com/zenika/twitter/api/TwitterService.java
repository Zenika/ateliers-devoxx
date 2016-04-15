/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.zenika.twitter.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import java.util.List;

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
  ServiceCall<String, NotUsed, List<String>> tweets();

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("twitterservice").with(
        restCall(Method.GET,  "/api/twitter/:id",       tweets())
      ).withAutoAcl(true);
  }
}
