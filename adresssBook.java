package com.day5;

import java.util.*;

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
/*
* @desc:contains lists of contacts
* */
class AddressBookList {
    private List<Contact> contacts;

    // @desc: Constructor to initialize the contacts list
    public AddressBookList() {
        this.contacts = new ArrayList<>();
    }

    /*
     @desc:Method to add a new contact to the address book
     @params:Contact object
     @return:none
     */
    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully to the address book.");
    }

    //@desc: Method to display all contacts in the address book
    public void displayContacts() {
        System.out.println("Contacts in the address book:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
public class adresssBook {

    public static void main(String[] args) {
        // Displaying a welcome message
        displayWelcomeMessage();

        // Creating a new address book
        AddressBookList addressBook = new AddressBookList();

        // Adding a new contact to the address book
        Contact newContact = createContact();
        addressBook.addContact(newContact);

        // Displaying all contacts in the address book
        addressBook.displayContacts();


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
