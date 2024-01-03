package com.day5;


import java.util.*;
import java.util.stream.Collectors;

class SystemAddressBook {
    private Map<String, AddressBookList> addressBooks;
    private Map<String, List<Contact>> cityToPersonMap;
    private Map<String, List<Contact>> stateToPersonMap;

    public SystemAddressBook() {
        this.addressBooks = new HashMap<>();
        this.cityToPersonMap = new HashMap<>();
        this.stateToPersonMap = new HashMap<>();
    }



    // Method to add a new address book to the system
    public void addAddressBook(String addressBookName) {
        AddressBookList addressBook = new AddressBookList();
        addressBooks.put(addressBookName, addressBook);
        System.out.println("Address Book '" + addressBookName + "' added to the system.");
    }

    // Method to get an address book by name
    public AddressBookList getAddressBook(String addressBookName) {
        return addressBooks.get(addressBookName);
    }

    // Method to display all address book names in the system
    public void displayAddressBooks() {
        System.out.println("Address Books in the system:");
        for (String addressBookName : addressBooks.keySet()) {
            System.out.println(addressBookName);
        }
    }

    // Method to search for a person in a city across all address books
    public List<Contact> searchPersonInCity(String city) {
        return addressBooks.values().stream()
                .flatMap(addressBookList -> addressBookList.searchByCity(city).stream())
                .collect(Collectors.toList());
    }

    // Method to search for a person in a state across all address books
    public List<Contact> searchPersonInState(String state) {
        return addressBooks.values().stream()
                .flatMap(addressBookList -> addressBookList.searchByState(state).stream())
                .collect(Collectors.toList());
    }

    // Method to view persons by city
    public void viewPersonsByCity(String city) {
        List<Contact> persons = cityToPersonMap.getOrDefault(city, Collections.emptyList());

        if (!persons.isEmpty()) {
            System.out.println("Persons in the city '" + city + "':");
            persons.forEach(System.out::println);
        } else {
            System.out.println("No persons found in the specified city.");
        }
    }

    // Method to view persons by state
    public void viewPersonsByState(String state) {
        List<Contact> persons = stateToPersonMap.getOrDefault(state, Collections.emptyList());

        if (!persons.isEmpty()) {
            System.out.println("Persons in the state '" + state + "':");
            persons.forEach(System.out::println);
        } else {
            System.out.println("No persons found in the specified state.");
        }
    }

    public void addContact(AddressBookList addressBook, Contact contact) {
        // Update cityToPersonMap
        cityToPersonMap.computeIfAbsent(contact.getCity(), k -> new ArrayList<>()).add(contact);

        // Update stateToPersonMap
        stateToPersonMap.computeIfAbsent(contact.getState(), k -> new ArrayList<>()).add(contact);
    }
}