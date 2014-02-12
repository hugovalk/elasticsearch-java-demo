package com.devdiscoveries.elasticsearch.demo.client;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

/**
 * Wrapper around an ElasticSearch {@link Node}, to obtain a {@link Client}
 * from.
 * 
 * @author hugovalk
 * 
 */
public class ESLocalNodeClient implements ESClient {

    private Node node;

    /**
     * Default constructor, initializing the client node.
     */
    public ESLocalNodeClient() {
	final Settings settings = ImmutableSettings.settingsBuilder()
		.put("node.name", "My beautiful node").build();

	node = new NodeBuilder().settings(settings)
		.clusterName("elasticsearch-hugo")
		// .local(true)
		.client(true).build().start();
    }

    /** {@inheritDoc} */
    @Override
    public Client getClient() {
	return node.client();
    }

    /** {@inheritDoc} */
    @Override
    public void shutdown() {
	getClient().close();
	node.close();
	node = null;
    }

}
