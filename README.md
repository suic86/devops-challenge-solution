# DevOps Challenge

## Background

In this simple [micronaut](https://micronaut.io/) project we have set up a
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
contains only one relevant test. In this test, 100 people are
successively stored into the database. 
After each insert, it is only tested whether the correct
number of persons have been stored.

## Problem

In its current state, the specification can only be executed once.
When the specification is executed a second time, 
the test would return an error because there already are persons in the database from
the previous run.
Therefore, the number of persons in the specification would no longer match.

## Goal

Provide a way to run this test successfully several times in a row. Also,
keep in mind that this specification will run on our CI Server,
and that this test is potentially executed in parallel by different builds.

## Constraints

There is no right or wrong way to solve this challenge.
You may change or improve everything starting with the configuration,
adding dependencies, up to refactoring implementation details.
Moreover, to provide the PostgreSQL database, any technology is allowed.

The only thing you must not modify is the interface `com.adverity.person.PersonRepository` and 
the method `com.adverity.person.PersonRepositorySpec.test()`.

