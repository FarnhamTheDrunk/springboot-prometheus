package com.example.demo;

import java.util.List;

import io.prometheus.client.Collector;

/**
 * @author Thomas Waschau
 * @since 02 Okt. 2017
 */
public class ExporterRegister {

	private List<Collector> collectors;

	public ExporterRegister(List<Collector> collectors) {
		for (Collector collector : collectors) {
			collector.register();
		}
		this.collectors = collectors;
	}

	public List<Collector> getCollectors() {
		return collectors;
	}

}
