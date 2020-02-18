package com.adverity.person

import javax.inject.Singleton
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

@Singleton
class PersonRepositoryImpl implements PersonRepository {

    private Connection c

    @Override
    void addPerson(String name) {
        if (c == null || c.isClosed()) {
            String url = "jdbc:postgresql://localhost/test"
            Properties props = new Properties()
            props.setProperty("user", "test")
            props.setProperty("password", "test")
            c = DriverManager.getConnection(url, props)
        }
        def statement = c.createStatement()
        statement.execute("INSERT INTO persons (name) values ('${name}')")
        c.close()
    }

    @Override
    int count() {
        if (c == null || c.isClosed()) {
            String connectionString = "jdbc:postgresql://localhost/test?user=test&password=test"
            c = DriverManager.getConnection(connectionString)
        }
        def statement = c.createStatement()
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM persons")
        rs.next()
        int _c = rs.getInt(1)
        c.close()
        _c
    }
}
