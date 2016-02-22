package com.goldmine.webstat.computation.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import com.goldmine.webstat.model.PageView;

public class PageViewSerializer extends BeanSerializer<PageView> {

	@SuppressWarnings("rawtypes")
	public PageViewSerializer(Kryo kryo, Class type) {
		super(kryo, type);
	}

}
