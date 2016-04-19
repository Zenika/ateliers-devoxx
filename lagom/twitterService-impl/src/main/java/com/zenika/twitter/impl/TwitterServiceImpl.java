/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.zenika.twitter.impl;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.pubsub.PubSubRef;
import com.lightbend.lagom.javadsl.pubsub.PubSubRegistry;
import com.lightbend.lagom.javadsl.pubsub.TopicId;
import com.lightbend.lagom.javadsl.server.ServerServiceCall;
import com.zenika.ts.FunctionnalStatusListener;
import com.zenika.ts.TwitterStreamBuilder;
import com.zenika.twitter.api.TwitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.TwitterStream;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the TwitterService.
 */
public class TwitterServiceImpl implements TwitterService {

    private static final String ID = "twitter";
    private final TwitterStream twitterStream;
    private final PubSubRegistry pubSub;
    private final Logger log = LoggerFactory.getLogger(TwitterServiceImpl.class);


    @Inject
    public TwitterServiceImpl(PubSubRegistry pubSub) {
        this.pubSub = pubSub;
        twitterStream = TwitterStreamBuilder.build();
    }

    @Override
    public ServiceCall<NotUsed, NotUsed, Source<String, ?>> tweets() {
        return (id, request) -> {
            final PubSubRef<String> topic =
                    pubSub.refFor(TopicId.of(String.class, ID));
            return CompletableFuture.completedFuture(topic.subscriber());
        };
    }

    @Override
    public ServiceCall<String, NotUsed, NotUsed> updateFilter() {
        twitterStream.clearListeners();


        return (id, request) -> {
            twitterStream.addListener((FunctionnalStatusListener) (status) -> {

                final PubSubRef<String> topic =
                        pubSub.refFor(TopicId.of(String.class, ID));
                topic.publish(status.getText());
            });

            twitterStream.filter(new FilterQuery(id));
            return CompletableFuture.completedFuture(NotUsed.getInstance());
        };


    }
}

