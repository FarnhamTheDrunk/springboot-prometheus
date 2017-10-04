package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.GarbageCollectorExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import io.prometheus.client.hotspot.ThreadExports;

/**
 * @author Thomas Waschau
 * @since 02 Okt. 2017
 */
@Configuration
@ConditionalOnClass(CollectorRegistry.class)
public class PrometheusConfiguration {

	@Bean
	@ConditionalOnMissingBean
	CollectorRegistry metricRegistry() {
		return CollectorRegistry.defaultRegistry;
	}

	@Bean
	ServletRegistrationBean registerPrometheusExporterServlet(CollectorRegistry metricRegistry) {
		return new ServletRegistrationBean(new MetricsServlet(metricRegistry), "/prometheus");
	}

	@Bean
	ExporterRegister exporterRegister() {
		List<Collector> collectors = new ArrayList<>();
		collectors.add(new StandardExports());
		collectors.add(new MemoryPoolsExports());
		collectors.add(new GarbageCollectorExports());
		collectors.add(new ThreadExports());
		ExporterRegister register = new ExporterRegister(collectors);
		return register;
	}
}
