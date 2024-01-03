package com.day5;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class SystemAddressBook {
    private Map<String, AddressBookList> addressBooks;

    public SystemAddressBook() {
        this.addressBooks = new HashMap<>();
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
}