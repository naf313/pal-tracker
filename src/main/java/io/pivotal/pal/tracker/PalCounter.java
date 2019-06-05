package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Component;

@Component
public class PalCounter implements Counter {

    private int count = 0;

    @Override
    public void increment() {
        count++;
    }

    @Override
    public void increment(double amount) {
        count += amount;
    }

    @Override
    public double count() {
        return count;
    }

    @Override
    public Id getId() {
        return null;
    }
}
