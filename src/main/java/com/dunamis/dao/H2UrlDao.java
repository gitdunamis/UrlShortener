package com.dunamis.dao;

import com.dunamis.domain.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class H2UrlDao implements IUrlDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createUrl(Url url) {
        jdbcTemplate.update("INSERT INTO Url (urlId, longUrl, uniqueKey) VALUES (?, ?, ?)",
               url.getUrlId(), url.getLongUrl(), url.getUniqueKey());
    }

    @Override
    public Url getUrlByIndex(int index) {
        String sql = "SELECT urlId, longUrl, uniqueKey FROM Url WHERE urlId = ?";
        var url = jdbcTemplate.queryForObject( //SELECT * FROM materials WHERE title = ?
                sql,
                this::mapRowToUrl, index);


//
//        SqlParameterSource source = new MapSqlParameterSource().addValue("urlId", 1);
//
//        var url2 = namedParameterJdbcTemplate.queryForObject(sql, source, Url.class);

        return url;
    }

    private Url mapRowToUrl(ResultSet rs, int rowNum) throws SQLException {
        var url = new Url();
        url.setUrlId(rs.getString("urlId"));
        url.setLongUrl(rs.getString("longUrl"));
        url.setUniqueKey(rs.getString("uniqueKey"));
        return url;
    }

    @Override
    public int deleteUrl(int index) {
        return jdbcTemplate.update(
                "DELETE FROM Url WHERE urlId=?", new Object[]{index});

    }

    @Override
    public long updateUrl(Url url) {
        return 0;
    }

    @Override
    public long getLastIndex() {
        // SELECT urlId FROM your_table WHERE urlId = (SELECT MAX(urlId) FROM your_table)
        var rowCountCallback = new RowCountCallbackHandler();
        jdbcTemplate.query("SELECT * FROM Url", rowCountCallback);

        return rowCountCallback.getColumnCount();
    }
}
