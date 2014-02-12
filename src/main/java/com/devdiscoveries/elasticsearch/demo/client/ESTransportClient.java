package com.devdiscoveries.elasticsearch.demo.client;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * Wrapper around an ElasticSearch {@link TransportClient} node.
 * 
 * @author hugovalk
 * 
 */
public class ESTransportClient implements ESClient {

    private TransportClient client;

    /**
     * Default constructor, initializing the transport client.
     */
    public ESTransportClient() {
	final Settings settings = ImmutableSettings.settingsBuilder()
		.put("client.transport.sniff", true)
		.put("cluster.name", "elasticsearch-hugo").build();
	client = new TransportClient(settings)
		.addTransportAddress(new InetSocketTransportAddress(
			"127.0.0.1", 9300));
    }

    /** {@inheritDoc} */
    @Override
    public Client getClient() {
	return client;
    }

    /** {@inheritDoc} */
    @Override
    public void shutdown() {
	client.close();
	client = null;
    }

}
