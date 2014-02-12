package com.devdiscoveries.elasticsearch.demo.indexer;

public interface Indexer<T> {

    boolean createIndex();

    boolean indexDocument(T document);
}
