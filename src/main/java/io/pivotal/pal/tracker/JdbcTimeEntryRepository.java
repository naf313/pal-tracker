package io.pivotal.pal.tracker;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTimeEntryRepository implements TimeEntryRepository {


    private DataSource dataSource;

    public JdbcTimeEntryRepository(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        SimpleJdbcInsert timeEntryInsert = new SimpleJdbcInsert(dataSource).withTableName("time_entries");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("project_id", timeEntry.getProjectId());
        parameters.put("user_id", timeEntry.getUserId());
        parameters.put("date", timeEntry.getDate());
        parameters.put("hours", timeEntry.getHours());
        Number id = timeEntryInsert
                .usingGeneratedKeyColumns("id")
                .usingColumns("project_id", "user_id", "date", "hours")
                .executeAndReturnKey(parameters);

        return template.queryForObject(
                "select * from time_entries where id = " + id.longValue(),
                new BeanPropertyRowMapper<TimeEntry>(TimeEntry.class));
    }


    @Override
    public TimeEntry find(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        try {
            return template.queryForObject(
                    "select * from time_entries where id = " + id,
                    new BeanPropertyRowMapper<TimeEntry>(TimeEntry.class));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<TimeEntry> list() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = "select * from time_entries";

        return template.query(sql,
                new BeanPropertyRowMapper<TimeEntry>(TimeEntry.class));
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = String.format("update time_entries set project_id = '%s', user_id = '%s', date = '%s', hours = '%s' where id = '%s'", timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours(), id);

        template.update(sql);

        return template.queryForObject(
                "select * from time_entries where id = " + id,
                new BeanPropertyRowMapper<TimeEntry>(TimeEntry.class));
    }

    @Override
    public void delete(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = String.format("delete from time_entries where id = '%s'", id);

        template.execute(sql);
    }
}
