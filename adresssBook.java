package com.day5;

import java.util.*;



public class adresssBook {

    public static void main(String[] args) {
        // Displaying a welcome message
        displayWelcomeMessage();

        // Creating a system with multiple address books
        SystemAddressBook systemAddressBook = new SystemAddressBook();

        // Adding new address books to the system
        addAddressBooks(systemAddressBook);

        // Displaying all address books in the system
        systemAddressBook.displayAddressBooks();

        // Choosing an address book to add contacts
        String selectedAddressBook = chooseAddressBook(systemAddressBook);

        // Creating a new contact in the selected address book
        createContact(systemAddressBook.getAddressBook(selectedAddressBook),systemAddressBook);

        // Creating a new contact in the selected address book
        createContact(systemAddressBook.getAddressBook(selectedAddressBook), systemAddressBook);

        // Displaying all contacts in the selected address book
        systemAddressBook.getAddressBook(selectedAddressBook).displayContacts();

        viewPersonsByCity(systemAddressBook);
    }

    // Method to get count of persons by city
    private static void getCountByCity(SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the city to get count: ");
        String city = scanner.next();

        long count = systemAddressBook.getCountByCity(city);

        System.out.println("Count of persons in the city '" + city + "': " + count);
    }

    // Method to get count of persons by state
    private static void getCountByState(SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the state to get count: ");
        String state = scanner.next();

        long count = systemAddressBook.getCountByState(state);

        System.out.println("Count of persons in the state '" + state + "': " + count);
    }


    // Method to view persons by city
    private static void viewPersonsByCity(SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the city to view persons: ");
        String city = scanner.next();

        systemAddressBook.viewPersonsByCity(city);
    }

    // Method to view persons by state
    private static void viewPersonsByState(SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the state to view persons: ");
        String state = scanner.next();

        systemAddressBook.viewPersonsByState(state);
    }


    // Method to search for a person in a city across all address books
    private static void searchPersonInCity(SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the city to search for: ");
        String city = scanner.next();

        List<Contact> result = systemAddressBook.searchPersonInCity(city);

        if (!result.isEmpty()) {
            System.out.println("Search results in city '" + city + "':");
            result.forEach(System.out::println);
        } else {
            System.out.println("No person found in the specified city.");
        }
    }

    // Method to search for a person in a state across all address books
    private static void searchPersonInState(SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the state to search for: ");
        String state = scanner.next();

        List<Contact> result = systemAddressBook.searchPersonInState(state);

        if (!result.isEmpty()) {
            System.out.println("Search results in state '" + state + "':");
            result.forEach(System.out::println);
        } else {
            System.out.println("No person found in the specified state.");
        }
    }

    // Method to add new address books to the system
    private static void addAddressBooks(SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of address books to add: ");
        int numberOfAddressBooks = scanner.nextInt();

        for (int i = 0; i < numberOfAddressBooks; i++) {
            System.out.print("Enter the name of Address Book " + (i + 1) + ": ");
            String addressBookName = scanner.next();

            // Add address book to the system
            systemAddressBook.addAddressBook(addressBookName);
        }
    }

    // Method to choose an address book from the system
    private static String chooseAddressBook(SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an address book from the system:");
        systemAddressBook.displayAddressBooks();

        System.out.print("Enter the name of the selected Address Book: ");
        return scanner.next();
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
    private static void createContact(AddressBookList addressBook, SystemAddressBook systemAddressBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter contact details:");

        String firstName = getInput("First Name: ", scanner);
        String lastName = getInput("Last Name: ", scanner);

        if (!addressBook.isDuplicate(firstName, lastName)) {
            String address = getInput("Address: ", scanner);
            String city = getInput("City: ", scanner);
            String state = getInput("State: ", scanner);
            String zipCode = getInput("ZIP Code: ", scanner);
            String phoneNumber = getInput("Phone Number: ", scanner);
            String email = getInput("Email: ", scanner);

            // Creating a new contact with the gathered information
            Contact newContact = new Contact(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
            // Adding the new contact to the specified address book
            addressBook.addContact(newContact);
            systemAddressBook.addContact(addressBook, newContact);

            System.out.println("Contact created successfully.");
        } else {
            System.out.println("Duplicate entry! Contact with the same name already exists.");
        }

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
