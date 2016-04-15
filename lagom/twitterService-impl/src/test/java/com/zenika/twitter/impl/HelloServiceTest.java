/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.zenika.twitter.impl;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.withServer;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import akka.NotUsed;
import com.zenika.twitter.api.TwitterService;

public class HelloServiceTest {

  @Test
  public void shouldStorePersonalizedGreeting() throws Exception {
    withServer(defaultSetup(), server -> {
      TwitterService service = server.client(TwitterService.class);

      String msg1 = service.tweets().invoke("Alice", NotUsed.getInstance()).toCompletableFuture().get(5, SECONDS);
      assertEquals("Hello, Alice!", msg1); // default greeting

      service.useGreeting().invoke("Alice", new GreetingMessage("Hi")).toCompletableFuture().get(5, SECONDS);
      String msg2 = service.tweets().invoke("Alice", NotUsed.getInstance()).toCompletableFuture().get(5, SECONDS);
      assertEquals("Hi, Alice!", msg2);

      String msg3 = service.tweets().invoke("Bob", NotUsed.getInstance()).toCompletableFuture().get(5, SECONDS);
      assertEquals("Hello, Bob!", msg3); // default greeting
    });
  }

}
