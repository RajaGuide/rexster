package com.tinkerpop.rexster.config;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.rexster.RexsterApplicationGraph;
import org.apache.commons.configuration.Configuration;

import java.util.Map;

/**
 * The GraphConfiguration interface is used to take a Configuration object from rexster.xml and from that
 * generate a new Graph instance.
 * <p/>
 * This interface can be used to construct new Graph configuration types for Rexster for other Blueprints
 * implementations not yet supported by Rexster (i.e. Infinite Graph).
 *
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public interface GraphConfiguration {
    Graph configureGraphInstance(Configuration properties,
                                 Map<String, RexsterApplicationGraph> graphs) throws GraphConfigurationException;
}
