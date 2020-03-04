package com.adverity.person

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Person
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Unroll
import spock.lang.Shared

import javax.inject.Inject
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

@MicronautTest
@Stepwise
class PersonRepositorySpec extends Specification {

    @Shared Connection conn
    @Shared Statement sql

    def setupSpec() {
        String url = "jdbc:postgresql://localhost/test"
        Properties props = new Properties()
        props.setProperty("user", "test")
        props.setProperty("password", "test")
        conn = DriverManager.getConnection(url, props)
        sql = conn.createStatement()
        sql.execute("DROP TABLE IF EXISTS persons")
        sql.execute("CREATE TABLE IF NOT EXISTS persons (id SERIAL, name text)")
    }

    def cleanupSpec() {
        sql.execute("DROP TABLE IF EXISTS persons")
        conn.close()
    }

    @Inject
    PersonRepository personRepository

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
