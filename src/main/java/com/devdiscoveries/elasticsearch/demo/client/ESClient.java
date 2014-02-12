package com.devdiscoveries.elasticsearch.demo.client;

import org.elasticsearch.client.Client;

public interface ESClient {

    Client getClient();

    void shutdown();
}
