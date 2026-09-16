package com.product.star.homework;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

public class ContactDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private final RowMapper<Contact> contactRowMapper = (resultSet, rowNum) ->
            new Contact(
                    resultSet.getLong("ID"),
                    resultSet.getString("NAME"),
                    resultSet.getString("SURNAME"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("PHONE_NUMBER")
            );

    public ContactDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        String sql = "SELECT ID, NAME, SURNAME, EMAIL, PHONE_NUMBER " +
                "FROM CONTACT";

        return namedJdbcTemplate.query(sql, contactRowMapper);
    }

    public Contact getContact(long contactId) {
        String sql = "SELECT ID, NAME, SURNAME, EMAIL, PHONE_NUMBER " +
                "FROM CONTACT " +
                "WHERE ID = :id";

        return namedJdbcTemplate.queryForObject(
                sql,
                Map.of("id", contactId),
                contactRowMapper
        );
    }

    public long addContact(Contact contact) {
        String sql = "INSERT INTO CONTACT (NAME, SURNAME, EMAIL, PHONE_NUMBER) " +
                "VALUES (:name, :surname, :email, :phone) " +
                "RETURNING ID";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", contact.getName())
                .addValue("surname", contact.getSurname())
                .addValue("email", contact.getEmail())
                .addValue("phone", contact.getPhone());

        Long id = namedJdbcTemplate.queryForObject(
                sql,
                parameters,
                Long.class
        );

        return id;
    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {
        String sql = "UPDATE CONTACT " +
                "SET PHONE_NUMBER = :phone " +
                "WHERE ID = :id";

        namedJdbcTemplate.update(
                sql,
                Map.of(
                        "phone", phoneNumber,
                        "id", contactId
                )
        );
    }

    public void updateEmail(long contactId, String email) {
        String sql = "UPDATE CONTACT " +
                "SET EMAIL = :email " +
                "WHERE ID = :id";

        namedJdbcTemplate.update(
                sql,
                Map.of(
                        "email", email,
                        "id", contactId
                )
        );
    }

    public void deleteContact(long contactId) {
        String sql = "DELETE FROM CONTACT " +
                "WHERE ID = :id";

        namedJdbcTemplate.update(
                sql,
                Map.of("id", contactId)
        );
    }
}