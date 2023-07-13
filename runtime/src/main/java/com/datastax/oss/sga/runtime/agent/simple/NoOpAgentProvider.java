package com.dastastax.oss.sga.runtime.simple;

import com.datastax.oss.sga.api.runner.code.AgentCode;
import com.datastax.oss.sga.api.runner.code.AgentCodeProvider;
import com.datastax.oss.sga.api.runner.code.Record;

import java.util.List;

public class NoOpAgentProvider implements AgentCodeProvider {
    @Override
    public boolean supports(String agentType) {
        return "noop".equals(agentType);
    }

    @Override
    public AgentCode createInstance(String agentType) {
        return new NoOpAgentCode();
    }

    private static class NoOpAgentCode implements AgentCode {
        @Override
        public List<Record> process(List<Record> record) {
            return List.of();
        }
    }
}