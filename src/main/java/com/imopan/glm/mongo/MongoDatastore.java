package com.imopan.glm.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

public class MongoDatastore extends AbstractFactoryBean<Datastore>{

	private String host;

	private Integer port;

	private String packageName;

	private String dbName;
	
	private String uri;
	

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	@Override
	public Class<?> getObjectType() {
		return Datastore.class;
	}

	@Override
	protected Datastore createInstance() throws Exception {
		Morphia morphia = new Morphia();
		morphia.mapPackage(packageName);
		MongoClientURI mongoClientURI = new MongoClientURI(uri);
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		return morphia.createDatastore(mongoClient, dbName);
	}
}
