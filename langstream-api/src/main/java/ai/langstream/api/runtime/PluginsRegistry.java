/*
 * Copyright DataStax, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ai.langstream.api.runtime;

import lombok.extern.slf4j.Slf4j;

import java.util.ServiceLoader;

@Slf4j
public class PluginsRegistry {
    public AgentNodeProvider lookupAgentImplementation(String type, ComputeClusterRuntime clusterRuntime) {
        log.info("Looking for an implementation of agent type {} on {}", type, clusterRuntime.getClusterType());
        ServiceLoader<AgentNodeProvider> loader = ServiceLoader.load(AgentNodeProvider.class);
        ServiceLoader.Provider<AgentNodeProvider> agentRuntimeProviderProvider = loader
                .stream()
                .filter(p -> {
                    AgentNodeProvider agentNodeProvider = p.get();
                    return agentNodeProvider.supports(type, clusterRuntime);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No AgentNodeProvider found for type " + type
                        + " for cluster type "+ clusterRuntime.getClusterType()));
        return agentRuntimeProviderProvider.get();
    }

}
