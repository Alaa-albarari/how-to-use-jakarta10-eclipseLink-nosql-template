package com.albarari.jakarta.mapper;

import org.bson.types.ObjectId;

public class ObjectIdMapper {

    public String asString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    public ObjectId asObjectId(String id) {
        return (id != null && !id.isEmpty()) ? new ObjectId(id) : null;
    }
}
