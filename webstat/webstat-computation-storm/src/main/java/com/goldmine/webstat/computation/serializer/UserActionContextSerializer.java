package com.goldmine.webstat.computation.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import com.goldmine.webstat.model.UserActionContext;

public class UserActionContextSerializer extends BeanSerializer<UserActionContext> {

	@SuppressWarnings("rawtypes")
	public UserActionContextSerializer(Kryo kryo, Class type) {
		super(kryo, type);
	}

}
