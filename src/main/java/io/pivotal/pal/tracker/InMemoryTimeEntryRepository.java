package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Long currentId = 1L;
    private HashMap<Long, TimeEntry> entries = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(currentId);
        entries.put(currentId,timeEntry);
        currentId ++;
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return entries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList(entries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (entries.containsKey(id)) {
            TimeEntry oldEntry = entries.get(id);
            timeEntry.setId(oldEntry.getId());
            entries.replace(id, timeEntry);
            return timeEntry;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        entries.remove(id);
    }
}
