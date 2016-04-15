/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.zenika.twitter.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.zenika.twitter.api.TwitterService;

/**
 * The module that binds the TwitterService so that it can be served.
 */
public class TwitterServiceModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindServices(serviceBinding(TwitterService.class, TwitterServiceImpl.class));
  }
}
