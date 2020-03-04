package com.adverity.person

import javax.inject.Singleton
import java.sql.Connection
import java.sql.ResultSet

@Singleton
class PersonRepositoryImpl implements PersonRepository {
    private Connection conn

    PersonRepositoryImpl(Connection conn) {
        this.conn = conn
    }

    @Override
    void addPerson(String name) {
        def statement = this.conn.createStatement()
        statement.execute("INSERT INTO persons (name) values ('${name}')")
    }

    @Override
    int count() {
        def statement = this.conn.createStatement()
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM persons")
        rs.next()
        return rs.getInt(1)
    }
}
