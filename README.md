# DevOps Challenge

## Background

In this simple [micronaut](https://micronaut.io/)-[groovy](http://groovy-lang.org/) project we have set up a
[Specification](http://spockframework.org/spock/docs/1.0/spock_primer.html#_specification)
`com.adverity.person.PersonRepositorySpec` using
the [Spock Framework](http://spockframework.org/) to test the
`com.adverity.person.PersonRepositoryImpl` class.

The class `com.adverity.person.PersonRepositoryImpl` implements the 
interface `com.adverity.person.PersonRepository` and has only two methods.

- `void addPerson(String name)`

  Inserts a new row into the persons table of a [PostgreSQL](https://www.postgresql.org/) database.

- `int count()`
  
  Returns the number of rows in the persons table of a PostgreSQL database.


Furthermore, the specification `com.adverity.person.PersonRepositorySpec` 
contains only one relevant test. In this integration test, 100 persons are
successively stored into the database. 
After each insertion it is checked whether the correct
number of people have been saved.

## Problem

In its current state, the test can only be executed once.
When the test is executed a second time, 
the test would return an error because there already are persons in the database from
the previous run.
Therefore, the number of persons in the test would no longer match.

## Goal

Provide a way to run this test successfully several times in a row. Also,
keep in mind that this test will run on our CI Server,
and that this test is potentially executed in parallel by different builds.

## Constraints

There is no right or wrong way to solve this challenge.
You can change or improve everything from configuration, to adding dependencies,
to refactoring implementation details. In addition, any technology for providing
the PostgreSQL database is allowed.

The only parts you must not modify is the interface `com.adverity.person.PersonRepository` and 
the method `com.adverity.person.PersonRepositorySpec.test()`.

