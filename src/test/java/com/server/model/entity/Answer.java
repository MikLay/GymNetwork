package com.server.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("javadoc")
public class Answer {

    @Entity
    @Builder(toBuilder = true)
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    @Setter(value = AccessLevel.PACKAGE)
    @Getter
    public static class Person {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        /*
         * IMPORTANT:
         * Set toString, equals, and hashCode as described in these
         * documents:
         * - https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/
         * - https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
         * - https://vladmihalcea.com/hibernate-facts-equals-and-hashcode/
         */
    }

    /**
     * Test person builder.
     */

    /**
     * Test person constructor.
     */
}