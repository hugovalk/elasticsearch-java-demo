package com.devdiscoveries.elasticsearch.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devdiscoveries.elasticsearch.demo.client.ESClient;
import com.devdiscoveries.elasticsearch.demo.client.ESLocalNodeClient;
import com.devdiscoveries.elasticsearch.demo.domain.Vacancy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Elasticsearch demo application.
 */
public class ElasticsearchDemoApp {

    private static final Logger LOG = LoggerFactory
	    .getLogger(ElasticsearchDemoApp.class);

    public static void main(String[] args) {
	new ElasticsearchDemoApp().indexDocument();
    }

    private final ESClient esClient = new ESLocalNodeClient();

    private void indexDocument() {
	final Vacancy vacancy = createTestVacancy();
	String vacancyJson = null;
	try {
	    vacancyJson = new ObjectMapper().writeValueAsString(vacancy);
	    esClient.getClient().prepareIndex("vacancies", "vacancy")
		    .setSource(vacancyJson).execute().actionGet();
	    LOG.info("Vacancy has been indexed.");
	} catch (final JsonProcessingException jpe) {
	    LOG.error("Vacancy could not be serialized. ", jpe);
	}
	esClient.shutdown();
    }

    private Vacancy createTestVacancy() {
	final Vacancy vacancy = new Vacancy();
	vacancy.setTitle("Software developer");
	vacancy.setDescription("A well-known company is looking for an experienced Java developer.");
	return vacancy;
    }
}
