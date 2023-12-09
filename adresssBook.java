package com.day5;

import java.util.Scanner;

//@desc : representing contact
class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String email;

    // Constructor for creating a contact
    public Contact(String firstName, String lastName, String address, String city,
                   String state, String zipCode, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // toString method to display contact information
    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
public class adresssBook {

    public static void main(String[] args) {
        // Displaying a welcome message
        displayWelcomeMessage();

        // Creating a new contact
        Contact newContact = createContact();

        // Displaying the contact information
        System.out.println("Contact Information:");
        System.out.println(newContact);


    }

    /*
    @desc: Method to create a new contact
    @params:none
    @return:Contact object
     */
    private static Contact createContact() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter contact details:");

        String firstName = getInput("First Name: ", scanner);
        String lastName = getInput("Last Name: ", scanner);
        String address = getInput("Address: ", scanner);
        String city = getInput("City: ", scanner);
        String state = getInput("State: ", scanner);
        String zipCode = getInput("ZIP Code: ", scanner);
        String phoneNumber = getInput("Phone Number: ", scanner);
        String email = getInput("Email: ", scanner);

        // Creating and returning a new Contact object
        return new Contact(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
    }

    /*
    @desc: Method to handle user input for a specific field
    @params:string-prompt, scanner objectg
    @return:string-input
     */
    private static String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Method to display a welcome message
    private static void displayWelcomeMessage() {
        System.out.println("Welcome to Address Book Program!");
    }
}
