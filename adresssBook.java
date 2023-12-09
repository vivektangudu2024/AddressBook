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

    // Getter methods for retrieving contact information

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }


    // Setters for updating contact attributes

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
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

    /*
     @desc: Method to edit an existing contact based on the person's name
     @params: string- firstname, sting-lastname
     @reuturn: none
     */
    public void editContact(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName) &&
                    contact.getLastName().equalsIgnoreCase(lastName)) {

                // Prompt user for updated information
                System.out.println("Enter updated contact details:");

                contact.setAddress(getInput("Address: ", new Scanner(System.in)));
                contact.setCity(getInput("City: ", new Scanner(System.in)));
                contact.setState(getInput("State: ", new Scanner(System.in)));
                contact.setZipCode(getInput("ZIP Code: ", new Scanner(System.in)));
                contact.setPhoneNumber(getInput("Phone Number: ", new Scanner(System.in)));
                contact.setEmail(getInput("Email: ", new Scanner(System.in)));

                System.out.println("Contact updated successfully.");
                return; // Exit the loop if contact is found and updated
            }
        }

        System.out.println("Contact not found with the given name.");
    }

    // Method to delete a contact based on the person's name
    public void deleteContact(String firstName, String lastName) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getFirstName().equalsIgnoreCase(firstName) &&
                    contact.getLastName().equalsIgnoreCase(lastName)) {
                iterator.remove();
                System.out.println("Contact deleted successfully.");
                return; // Exit the loop if contact is found and deleted
            }
        }

        System.out.println("Contact not found with the given name.");
    }

    private static String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine();
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

        // Editing an existing contact in the address book
        editExistingContact(addressBook);

        // Displaying all contacts in the address book
        addressBook.displayContacts();

        // Deleting a contact from the address book
        deleteContact(addressBook);

        // Displaying all contacts in the address book after deletion
        addressBook.displayContacts();

    }

    /*
     @desc: Method to delete an existing contact in the address book

     */
    private static void deleteContact(AddressBookList addressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the contact to delete:");
        String firstName = getInput("First Name: ", scanner);
        String lastName = getInput("Last Name: ", scanner);

        // Deleting the existing contact
        addressBook.deleteContact(firstName, lastName);
    }
    /*
     @desc: Method to edit an existing contact in the address book
     @params:AdressBookList object to edit list
     @desc: none
     */
    private static void editExistingContact(AddressBookList addressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the contact to edit:");
        String firstName = getInput("First Name: ", scanner);
        String lastName = getInput("Last Name: ", scanner);

        // Editing the existing contact
        addressBook.editContact(firstName, lastName);
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
