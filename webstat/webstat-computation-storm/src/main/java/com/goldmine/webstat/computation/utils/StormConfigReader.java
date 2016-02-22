package com.goldmine.webstat.computation.utils;

import java.util.Properties;

public class StormConfigReader {

	private static final String CONFIG_FILE = "storm_config.properties";

	private static final String SPOUT_EMIT_INTERVAL = "spoutEmitInterval";

	public static int getSpoutEmitInterval() {
		return Integer.parseInt(getProps().getProperty(SPOUT_EMIT_INTERVAL));
	}

	private static Properties getProps() {
		return PropUtils.getProps(CONFIG_FILE);
	}

}
