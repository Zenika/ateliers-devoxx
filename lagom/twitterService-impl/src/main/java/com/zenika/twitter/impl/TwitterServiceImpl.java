/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.zenika.twitter.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import com.zenika.twitter.api.TwitterService;
import play.api.libs.json.jackson.JacksonJson;
import play.libs.Json;
import scala.util.parsing.json.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * Implementation of the TwitterService.
 */
public class TwitterServiceImpl implements TwitterService {

    TwitterClient tc = new TwitterClient(Arrays.asList("java"));


    @Override
    public ServiceCall<String, NotUsed, List<String>> tweets() {
        return (id, request) -> {

            return completedFuture(tc.getQueue().stream().limit(10)
                    .map(t -> Json.parse(t).get("text").asText()).collect(Collectors.toList()));
        };
    }


    private class TwitterClient {
        private BlockingQueue<String> queue;
        private Client client;

        public BlockingQueue<String> getQueue() {
            return queue;
        }

        public Client getClient() {
            return client;
        }

        public TwitterClient(List<String> terms) {
            queue = new LinkedBlockingQueue<>(10);
            StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
            endpoint.trackTerms(terms);

            String consumerKey = "8rrHt6IqXQ945ZYfWxBHNVEFQ";
            String consumerSecret = "uQL67NQhClSFeCWFUiufLLk0zFsUpEEHATydgTtqE0Ot7BDMVu";
            String token = "141184524-L7ezeCaQrsNmYlZfYu0TiLzyijHdcOSMfbJ7IZfm";
            String secret = "LolnPSS3a0qtWSM6USRzzUdEz95S0NTdpRZXNgK2aA71q";
            Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);

            client = new ClientBuilder().hosts(Constants.STREAM_HOST)
                    .endpoint(endpoint).authentication(auth)
                    .processor(new StringDelimitedProcessor(queue)).build();

            client.connect();
        }
    }


}

