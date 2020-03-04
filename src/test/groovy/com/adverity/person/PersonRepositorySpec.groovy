package com.adverity.person

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Person
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Unroll
import spock.lang.Shared

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

@MicronautTest
@Stepwise
class PersonRepositorySpec extends Specification {

    @Shared Connection conn
    @Shared Statement sql
    @Shared String db

    def setupSpec() {
        String url = "jdbc:postgresql://localhost/"
        db = "db" + UUID.randomUUID().toString().replace("-", "")
        Properties props = new Properties()
        props.setProperty("user", "test")
        props.setProperty("password", "test")

        conn = DriverManager.getConnection(url, props)
        sql = conn.createStatement()
        sql.executeUpdate("CREATE DATABASE ${db}")
        sql.close()
        conn.close()

        conn = DriverManager.getConnection("${url}${db}")
        sql = conn.createStatement()
        sql.execute("CREATE TABLE IF NOT EXISTS persons (id SERIAL, name text)")
    }

    def cleanupSpec() {
        sql.close()
        conn.close()

        String url = "jdbc:postgresql://localhost/"
        Properties props = new Properties()
        props.setProperty("user", "test")
        props.setProperty("password", "test")

        conn = DriverManager.getConnection(url, props)
        sql = conn.createStatement()
        sql.executeUpdate("DROP DATABASE ${db}")
        sql.close()
        conn.close()
    }

    PersonRepository personRepository = new PersonRepositoryImpl(conn)

    /**
     * DO NOT MODIFY THIS TEST
     */
    @Unroll
    def "test"() {
        given:
        Fairy fairy = Fairy.create()
        Person person = fairy.person()
        personRepository.addPerson(person.fullName)

        expect:
        i == personRepository.count()

        where:
        i << (1..100)
    }

}
