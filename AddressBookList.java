package com.day5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            System.out.println("Contact added successfully to the address book.");
        } else {
            System.out.println("Duplicate entry! Contact with the same name already exists.");
        }
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

    public boolean isDuplicate(String firstName, String lastName) {
        return contacts.stream()
                .anyMatch(contact -> contact.getFirstName().equalsIgnoreCase(firstName) &&
                        contact.getLastName().equalsIgnoreCase(lastName));
    }

    public List<Contact> searchByCity(String city) {
        return contacts.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByState(String state) {
        return contacts.stream()
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    public void sortEntriesByName() {
        contacts = contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName))
                .collect(Collectors.toList());
    }

    // Method to sort entries alphabetically by city
    public void sortEntriesByCity() {
        contacts = contacts.stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .collect(Collectors.toList());
    }

    // Method to sort entries alphabetically by state
    public void sortEntriesByState() {
        contacts = contacts.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .collect(Collectors.toList());
    }

    // Method to sort entries alphabetically by zip code
    public void sortEntriesByZip() {
        contacts = contacts.stream()
                .sorted(Comparator.comparing(Contact::getZipCode))
                .collect(Collectors.toList());
    }

    public void saveToFile(String fileName) {
        try {
            // Check if the file exists, create it if not
            Path filePath = Paths.get(fileName);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {

            oos.writeObject(contacts);
            System.out.println("Address book saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving address book to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to load contacts from a file
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (List<Contact>) ois.readObject();
            System.out.println("Address book loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading address book from file: " + e.getMessage());
        }
    }
}