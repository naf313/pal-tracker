package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class PalInfo implements InfoContributor {

    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    @Autowired
    public PalInfo(MeterRegistry meterRegistry) {
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @Override
    public void contribute(Info.Builder builder) {

        builder.withDetail("summary", timeEntrySummary.count());
        builder.withDetail("count", actionCounter.count());
    }
}
