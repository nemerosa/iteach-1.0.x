package net.nemerosa.iteach.dao.jdbc;

import net.nemerosa.iteach.dao.PreferencesRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class PreferencesJdbcRepository extends AbstractJdbcRepository implements PreferencesRepository {

    @Autowired
    public PreferencesJdbcRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public boolean getBoolean(int teacherId, String name, boolean value) {
        return Boolean.valueOf(getValue(teacherId, name, String.valueOf(value)));
    }

    @Override
    public void setBoolean(int teacherId, String name, boolean value) {
        setValue(teacherId, name, String.valueOf(value));
    }

    private String getValue(int teacherId, String name, String defaultValue) {
        String value = getFirstItem(
                SQL.PREFERENCES_GET,
                params("teacherId", teacherId).addValue("name", name),
                String.class);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        } else {
            return value;
        }
    }

    private void setValue(int teacherId, String name, String value) {
        MapSqlParameterSource params = params("teacherId", teacherId)
                .addValue("name", name)
                .addValue("value", value);
        getNamedParameterJdbcTemplate().update(
                SQL.PREFERENCES_DELETE,
                params
        );
        getNamedParameterJdbcTemplate().update(
                SQL.PREFERENCES_INSERT,
                params
        );
    }
}
