package com.devdiscoveries.elasticsearch.demo.indexer;

public interface Indexer<T> {

    boolean createIndex();

    boolean upgradeIndex();

    boolean indexDocument(T document);

    boolean deleteDocument(T document);

}
