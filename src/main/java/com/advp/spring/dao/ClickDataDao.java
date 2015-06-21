package com.advp.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.advp.spring.entities.ClickData;

@Component("clickDataDao")
public class ClickDataDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<ClickData> getClickDatas() {

		return jdbc.query("select * from clickdata", new RowMapper<ClickData>() {

			public ClickData mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClickData ClickData = new ClickData();

				ClickData.setId(rs.getInt("id"));
				ClickData.setIpAddress(rs.getString("ip_address"));
				ClickData.setDevice(rs.getString("device"));
				ClickData.setPublisherId(rs.getString("id_publisher"));
				ClickData.setCampaignId(rs.getString("id_campaign"));
				ClickData.setCountry(rs.getString("country"));
				ClickData.setCity(rs.getString("city"));
				ClickData.setPublisherChannelType(rs.getString("publisher_channel_type"));
				ClickData.setReferrerId(rs.getString("id_referrer"));
				ClickData.setTimestamp_sent(rs.getTimestamp("timestamp_sent"));
				ClickData.setTimestamp_received(rs.getTimestamp("timestamp_received"));
				return ClickData;
			}

		});
	}
	
	public List<ClickData> getNewClickDatas(long lastid) {

		return jdbc.query("select * from clickdata where id > " + lastid + " order by id desc", new RowMapper<ClickData>() {

			public ClickData mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClickData ClickData = new ClickData();

				ClickData.setId(rs.getInt("id"));
				ClickData.setIpAddress(rs.getString("ip_address"));
				ClickData.setDevice(rs.getString("device"));
				ClickData.setPublisherId(rs.getString("id_publisher"));
				ClickData.setCampaignId(rs.getString("id_campaign"));
				ClickData.setCountry(rs.getString("country"));
				ClickData.setCity(rs.getString("city"));
				ClickData.setPublisherChannelType(rs.getString("publisher_channel_type"));
				ClickData.setReferrerId(rs.getString("id_referrer"));
				ClickData.setTimestamp_sent(rs.getTimestamp("timestamp_sent"));
				ClickData.setTimestamp_received(rs.getTimestamp("timestamp_received"));
				return ClickData;
			}

		});
	}
	
	public boolean update(ClickData clickData) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(clickData);
		
		return jdbc.update("update clickdata set name=:name, text=:text, email=:email where id=:id", params) == 1;
	}
	
	public boolean create(ClickData clickData) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(clickData);
		
		return jdbc.update("insert into clickdata (company_name, website_url, campaign) values (:company_name, :website_url, :campaign)", params) == 1;
	}
	
	@Transactional
	public int[] create(List<ClickData> clickdata) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(clickdata.toArray());
		
		return jdbc.batchUpdate("insert into ClickDatas (id, name, text, email) values (:id, :name, :text, :email)", params);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from clickdata where id=:id", params) == 1;
	}

	public ClickData getClickData(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from clickdata where id=:id", params,
				new RowMapper<ClickData>() {

					public ClickData mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ClickData ClickData = new ClickData();

						ClickData.setId(rs.getInt("id"));
						ClickData.setIpAddress(rs.getString("ip_address"));
						ClickData.setDevice(rs.getString("device"));
						ClickData.setPublisherId(rs.getString("id_publisher"));
						ClickData.setCampaignId(rs.getString("id_campaign"));
						ClickData.setCountry(rs.getString("country"));
						ClickData.setCity(rs.getString("city"));
						ClickData.setPublisherChannelType(rs.getString("publisher_channel_type"));
						ClickData.setReferrerId(rs.getString("id_referrer"));

						return ClickData;
					}

				});
	}
	
}
