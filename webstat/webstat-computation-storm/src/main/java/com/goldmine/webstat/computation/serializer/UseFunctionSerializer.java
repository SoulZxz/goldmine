package com.goldmine.webstat.computation.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import com.goldmine.webstat.model.UseFunction;

public class UseFunctionSerializer extends BeanSerializer<UseFunction> {

	@SuppressWarnings("rawtypes")
	public UseFunctionSerializer(Kryo kryo, Class type) {
		super(kryo, type);
	}

}
