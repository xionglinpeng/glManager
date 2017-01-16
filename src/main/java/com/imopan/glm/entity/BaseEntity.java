package com.imopan.glm.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseEntity implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	private ObjectId id;

	public String getId() {
		if (id == null ){
			return new ObjectId().toString();
		}
		return id.toString();
	}

	public void setId(final ObjectId id) {
		this.id = id;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
