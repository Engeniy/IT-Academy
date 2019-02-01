package ru.mail.krivonos.al.lesson.nineteen.model;

public class Person {

    private String firstName;
    private String lastName;
    private int yearOfBirth;

    private Person(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        yearOfBirth = builder.yearOfBirth;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private int yearOfBirth;

        private Builder() {
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder yearOfBirth(int val) {
            yearOfBirth = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
