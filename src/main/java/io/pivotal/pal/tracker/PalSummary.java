package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.distribution.HistogramSnapshot;
import org.springframework.stereotype.Component;

@Component
public class PalSummary implements io.micrometer.core.instrument.DistributionSummary {

    private long count;

    @Override
    public void record(double amount) {
        count = (long)amount;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public double totalAmount() {
        return count;
    }

    @Override
    public double max() {
        return count;
    }

    @Override
    public HistogramSnapshot takeSnapshot() {
        return null;
    }

    @Override
    public Id getId() {
        return null;
    }
}
