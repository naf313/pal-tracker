package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTimeEntryRepository implements ITimeEntryRepository{

    private Long currentId = 1L;
    private HashMap<Long, TimeEntry> entries = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(currentId);
        entries.put(currentId,timeEntry);
        currentId ++;
        return timeEntry;
    }

    public TimeEntry find(Long id) {
        return entries.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList(entries.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (entries.containsKey(id)) {
            TimeEntry oldEntry = entries.get(id);
            timeEntry.setId(oldEntry.getId());
            entries.replace(id, timeEntry);
            return timeEntry;
        }
        return null;
    }

    public void delete(Long id) {
        entries.remove(id);
    }
}
