package com.adverity.person

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Person
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Unroll

import javax.inject.Inject
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

@MicronautTest
@Stepwise
class PersonRepositorySpec extends Specification {

    @Inject
    PersonRepository personRepository

    def "Setup database"() {
        given:
        String url = "jdbc:postgresql://localhost/test"
        Properties props = new Properties()
        props.setProperty("user", "test")
        props.setProperty("password", "test")
        Connection conn = DriverManager.getConnection(url, props)

        Statement stmt = conn.createStatement()
        stmt.execute("DROP TABLE IF EXISTS persons")
        stmt.execute("CREATE TABLE IF NOT EXISTS persons (id SERIAL, name text)")
        conn.close()

        expect:
        true
    }

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
