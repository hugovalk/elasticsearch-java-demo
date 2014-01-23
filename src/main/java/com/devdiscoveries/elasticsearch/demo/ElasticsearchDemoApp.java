package com.devdiscoveries.elasticsearch.demo;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.devdiscoveries.elasticsearch.demo.domain.Vacancy;

/**
 * Elasticsearch demo application. 
 */
public class ElasticsearchDemoApp {
    public static void main(String[] args) {
        System.out.println( "Hello World!" );
    }
    
    private void indexDocument() {
    	
    }
    
    private Vacancy createTestVacancy() {
    	final Vacancy vacancy = new Vacancy();
    	vacancy.setTitle("Software developer");
    	vacancy.setDescription("A well-known company is looking for an experienced Java developer.");
    	return vacancy;
    }
    
    private Client getClient() {    
    	final Settings settings = ImmutableSettings.settingsBuilder()
    		.put("node.name", "My beautiful node")
    		.build();
    	
    	final Node node = new NodeBuilder()
    		.settings(settings)
    		.clusterName("elasticsearch")
    		.local(true)
    		.client(true)
    		.build();
    	
    	final Client client = node.client();
    	return client;
    }
}
