package com.devdiscoveries.elasticsearch.demo;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devdiscoveries.elasticsearch.demo.domain.Vacancy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Elasticsearch demo application. 
 */
public class ElasticsearchDemoApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchDemoApp.class);
	
    public static void main(String[] args) {
        new ElasticsearchDemoApp().indexDocument();
    }
    
    private Node node;
    
    private void indexDocument() {
    	final Vacancy vacancy = createTestVacancy();
    	final Client client = getClient();
    	String vacancyJson = null;
    	try {
    		vacancyJson = new ObjectMapper().writeValueAsString(vacancy);
        	client.prepareIndex("vacancies", "vacancy")
        			.setSource(vacancyJson)
        			.execute().actionGet();
        	LOG.info("Vacancy has been indexed.");
    	} catch (final JsonProcessingException jpe) {
    		LOG.error("Vacancy could not be serialized. ", jpe);
    	}
    	client.close();
    	node.stop();
    }
    
    private Vacancy createTestVacancy() {
    	final Vacancy vacancy = new Vacancy();
    	vacancy.setTitle("Software developer");
    	vacancy.setDescription("A well-known company is looking for an experienced Java developer.");
    	return vacancy;
    }
    
    private Client getTransportClient() {
    	final Settings settings = ImmutableSettings.settingsBuilder()
    	        .put("client.transport.sniff", true)
    	        .put("cluster.name", "elasticsearch-hugo").build();
    	return new TransportClient(settings)
    	        .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
    }
    
    private Client getClient() {
    	if (node == null) {
	    	final Settings settings = ImmutableSettings.settingsBuilder()
	    		.put("node.name", "My beautiful node")
	    		.build();
	    	
	    	node = new NodeBuilder()
	    		.settings(settings)
	    		.clusterName("elasticsearch-hugo")
	    		//.local(true)
	    		.client(true)
	    		.build().start();
    	}
    	final Client client = node.client();
    	return client;
    }
}
