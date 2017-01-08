package com.imopan.glm.mongo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class MongoDatastore extends AbstractFactoryBean<Datastore>{
	
	private String packageName;
	private String username;
	private String password;
	private List<MongoHP> mongoHP;
	private String dbName;
	private MongoOption mongoOption;
	private StringBuffer url = new StringBuffer();
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<MongoHP> getMongoHP() {
		return mongoHP;
	}

	public void setMongoHP(List<MongoHP> mongoHP) {
		this.mongoHP = mongoHP;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public MongoOption getMongoOption() {
		return mongoOption;
	}

	public void setMongoOption(MongoOption mongoOption) {
		this.mongoOption = mongoOption;
	}

	public String getUrl() {
		return url.toString();
	}

	public void setUrl(String url) {
		this.url.append(url);
	}

	@Override
	public Class<?> getObjectType() {
		return Datastore.class;
	}

	@Override
	protected Datastore createInstance() throws Exception {
		Morphia morphia = new Morphia();
		morphia.mapPackage(packageName);
		if(StringUtils.isBlank(url)){
			url.append("mongodb://");
			if(StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)){
				url.append(username+":"+password+"@");
			}
			long hpSize = mongoHP.size();
			for (int i=1;i<=hpSize;i++) {
				MongoHP hp = mongoHP.get(i-1);
				url.append(hp.getHost());
				if(hp.getPort()!=null){
					url.append(":"+hp.getPort());
				}
				if(i<hpSize){
					url.append(",");
				}
			}
			if(StringUtils.isNotBlank(dbName)){
				url.append("/"+dbName);
			}
			if(mongoOption!=null){
				url.append("/?");
				//还差选项配置
			}
		}
		System.out.println(url);
		MongoClientURI mongoClientURI = new MongoClientURI(url.toString());
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		return morphia.createDatastore(mongoClient, dbName);
	}

}

