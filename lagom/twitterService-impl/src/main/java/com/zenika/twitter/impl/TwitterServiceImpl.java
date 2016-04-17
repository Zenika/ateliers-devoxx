/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.zenika.twitter.impl;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.pubsub.PubSubRef;
import com.lightbend.lagom.javadsl.pubsub.PubSubRegistry;
import com.lightbend.lagom.javadsl.pubsub.TopicId;
import com.lightbend.lagom.javadsl.server.ServerServiceCall;
import com.zenika.twitter.api.TwitterService;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the TwitterService.
 */
public class TwitterServiceImpl implements TwitterService {
    private final TwitterStream twitterStream;
    private final PubSubRegistry pubSub;

    @Inject
    public TwitterServiceImpl(PubSubRegistry registry) {

        pubSub = registry;

        final PubSubRef<String> topic =
                pubSub.refFor(TopicId.of(String.class, "twitter"));

        System.out.println("status  =test");

        String consumerKey = "8rrHt6IqXQ945ZYfWxBHNVEFQ";
        String consumerSecret = "uQL67NQhClSFeCWFUiufLLk0zFsUpEEHATydgTtqE0Ot7BDMVu";
        String token = "141184524-L7ezeCaQrsNmYlZfYu0TiLzyijHdcOSMfbJ7IZfm";
        String secret = "LolnPSS3a0qtWSM6USRzzUdEz95S0NTdpRZXNgK2aA71q";
        final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(token)
                .setOAuthAccessTokenSecret(secret);
        twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();

        twitterStream.addListener(new StatusListener() {

            @Override
            public void onStatus(Status status) {

                topic.publish(status.getText());
                System.out.println("status  =" + status.getText());

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }

            @Override
            public void onException(Exception ex) {

            }
        });

        twitterStream.filter(new FilterQuery("AfterEart", "ASMOM"));
    }

    @Override
    public ServerServiceCall<String, NotUsed, Source<String, ?>> tweets() {


        return (id, request) -> {
            final PubSubRef<String> topic =
                    pubSub.refFor(TopicId.of(String.class, "twitter"));
            return CompletableFuture.completedFuture(topic.subscriber());
        };


    }
}

