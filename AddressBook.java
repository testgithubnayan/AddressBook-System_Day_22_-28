package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBook {

	static HashMap<String, ArrayList<ContactPerson>> AllAddresssBookLIst = new HashMap<>();
	static ArrayList<ContactPerson> UsedAddressBook;

	static HashMap<String, ArrayList<ContactPerson>> CityContactsArrayList = new HashMap<>();
	static HashMap<String, ArrayList<ContactPerson>> StateContactArrayList = new HashMap<>();
	static String UsedAddressBookName;

	static Scanner sc = new Scanner(System.in);


	
	static String UsedAddressBookName;

	static Scanner sc = new Scanner(System.in);

	 * Java HashMap contains values based on the key. Java HashMap contains only
	 * unique keys. Java HashMap may have one null key and multiple null values.
	 * Java HashMap is non synchronized. Java HashMap maintains no order. The
	 * initial default capacity of Java HashMap class is 16 with a load factor of
	 * 0.75
	 */


	void welcomeMassage() {

		int i, j, row = 6;

		for (i = 0; i < row; i++) {
			// inner loop work for space
			for (j = row - i; j > 1; j--) {

				System.out.print(" ");
			}
			// inner loop for columns
			for (j = 0; j <= i; j++) {

				System.out.print("   *  ");

			}
			System.out.println("    " + "  ");

		}
	}

	ContactPerson createContact() {
		ContactPerson sp = new ContactPerson();
		System.out.println("Enter first name: ");
		String firstName = sc.next();
		System.out.println("Enter last name: ");
		String lastName = sc.next();
		System.out.println("Enter address: ");
		String address = sc.next();
		System.out.println("Enter city: ");
		String city = sc.next();
		System.out.println("Enter state: ");
		String state = sc.next();
		System.out.println("Enter ZipCode: ");
		int zipCode = sc.nextInt();
		System.out.println("Enter phoneNumber: ");
		long phoneNumber = sc.nextLong();
		System.out.println("Enter Email: ");
		String email = sc.next();
		System.out.println("created new contact");

		ContactPerson person = new ContactPerson(firstName, lastName, address, city, state, zipCode, phoneNumber,
				email);
		System.out.println(person);
		return person;
	}

	void addContact(ContactPerson person) {
		boolean IFDuplicate = checkWheatherItIsDuplicateContact(person);
		if (IFDuplicate) {
			System.out.println("BUDDY !!CONTACT NAME ALREADY EXISTS IN THEADDRESSBOK SURFE MORE ....");
		} else {
			UsedAddressBook.add(person);

		}
	}

	void editContact() {
		System.out.println("Enter name to edit contact");
		String name = sc.next();
		for (ContactPerson person : UsedAddressBook) {
			if (person.getFirstName().equals(name)) {
				System.out.println("Enter first name: ");
				person.setFirstName(sc.next());
				System.out.println("Enter last name: ");
				person.setLastName(sc.next());
				System.out.println("Enter address: ");
				person.setAddress(sc.next());
				System.out.println("Enter city: ");
				person.setCity(sc.next());
				System.out.println("Enter state: ");
				person.setState(sc.next());
				System.out.println("Enter ZipCode:");
				person.setZipCode(sc.nextInt());
				System.out.println("Enter phoneNumber: ");
				person.setPhoneNumber(sc.nextLong());
				System.out.println("Enter Email: ");
				person.setEmail(sc.next());
				System.out.println("CONTACT UPDATED SUCCESSFULLY AND NOW YOU CAN SURFE MORE.");
				System.out.println(person);
				break;
			}
		}
	}

	void deleteContact() {
		boolean IfontactFound = false;
		System.out.println("ENTER NAME TO DELETE THE  CONTACT: ");
		String name = sc.next();
		for (ContactPerson contact : UsedAddressBook) {
			if (contact.getFirstName().equals(name)) {
				System.out.println("CONTACT FOUND:");
				IfontactFound = true;
				System.out.println(contact);
				System.out.println("PLEASE CONFUIRME ONCE MORE DO U REALLY WANT TO DELETE TYPE Y FOR YES OTHERWISE N ");
				if (sc.next().equals("y")) {
					UsedAddressBook.remove(contact);
					System.out.println(" SUCSESFULLY!!!CONTACT DELETED NOW");
				}
				break;
			}
		}
		if (!IfontactFound) {
			System.out.println("SORRY USER... CONTACT NOT FOUND");
		}
	}

	void addNewAddressBook() {
		System.out.println(" PLEASE ..ENTER THE  NAME FOR ADDRESSBOOK: -->");
		String AddressBookName = sc.next();
		ArrayList<ContactPerson> AddressBook = new ArrayList<ContactPerson>();
		AllAddresssBookLIst.put(AddressBookName, AddressBook);
		System.out.println("NEW ADDRESSBOOK CREATED-->");
		UsedAddressBook = AllAddresssBookLIst.get(AddressBookName);
		String Name = AddressBookName;
	}

	void selectAddressBook() {

		// Also using entrySet()........
		System.out.println(AllAddresssBookLIst.keySet());
		System.out.println("ENTER THE NAME OF ADDRESS BOOK:-->");
		String addressBookName = sc.next();

		for (String key : AllAddresssBookLIst.keySet()) {
			if (key.equals(addressBookName)) {
				UsedAddressBook = AllAddresssBookLIst.get(key);
				UsedAddressBookName = key;
			}
		}
		System.out
				.println("NOTE:-IF THE VALUE IS NULL BEACAUSE WHEATHER YOU NOT SELECTED THE ADDRESSBOOK OR NOR CREATD");
		System.out.println("THE CUREENTLY USED ADDRESSBOOK IS:-> " + UsedAddressBookName);
	}

// Type Arguments
	void displayContact(ArrayList<?> addressBook) {
		System.out.println("Contacts: ");
		for (Object O : addressBook) {
			ContactPerson person = (ContactPerson) O;
			System.out.println(" THE CONTACT DETAILES ARE-->" + person);

		}
	}

	boolean checkWheatherItIsDuplicateContact(ContactPerson wantedToAddingnewPerson) {
		return UsedAddressBook.stream()
				.anyMatch((person) -> person.getFirstName().equals(wantedToAddingnewPerson.getFirstName()));
	}

	void searchContact() {
		System.out.println("1.SEARCH BY CITY \n2.SEARCH BY STATE ,  CHOOSE- >");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			System.out.println("ENTER THAT CITY ->:");
			searchByCity(sc.next());
			break;
		case 2:
			System.out.println("Enter that State ->:");
			searchByState(sc.next());
			break;
		default:
			searchContact();
			break;
		}
	}

	void searchByCity(String city) {
		System.out.println("Search Result: ");
		for (String addressBookName : AllAddresssBookLIst.keySet()) {
			AllAddresssBookLIst.get(addressBookName).forEach((person) -> {
				if (person.getCity().equals(city))
					System.out.println(person);
			});
		}
	}

	void searchByState(String state) {
		System.out.println("SEARCH RESULT: ");
		for (String addressBookName : AllAddresssBookLIst.keySet()) {
			AllAddresssBookLIst.get(addressBookName).forEach((person) -> {
				if (person.getState().equals(state))
					System.out.println(person);
			});
		}
	}


	/*
	 * Java HashMap class implements the Map interface which allows us to store key
	 * and value pair, where keys should be unique. If you try to insert the
	 * duplicate key, it will replace the element of the corresponding key. It is
	 * easy to perform operations using the key index like updation, deletion, etc.
	 * HashMap class is found in the java.util package.
	 */

	public void CityAndStateContactArrayListPreparedForCallingLikeInitialization() {
		for (String key : AllAddresssBookLIst.keySet()) {
			for (ContactPerson person : AllAddresssBookLIst.get(key)) {
				String city = person.getCity();
				if (CityContactsArrayList.containsKey(city)) {
					CityContactsArrayList.get(city).add(person);
				} else {
					ArrayList<ContactPerson> list = new ArrayList<>();
					list.add(person);
					CityContactsArrayList.put(city, list);
				}

				String state = person.getState();
				if (StateContactArrayList.containsKey(state)) {
					StateContactArrayList.get(state).add(person);
				} else {
					ArrayList<ContactPerson> list = new ArrayList<>();
					list.add(person);
					StateContactArrayList.put(state, list);
				}
			}
		}
	}

	void viewContacts() {
		CityAndStateContactArrayListPreparedForCallingLikeInitialization();
		System.out.println("\n1.VIEW BY CITY \n2.VIEW BY STATE");
		switch (sc.nextInt()) {
		case 1:
			viewContactByCity();
			break;
		case 2:
			viewContactByState();
			break;
		default:
			viewContacts();
			break;
		}
	}

	void displayingTheContactByCity() {
		System.out.println("Enter City:");
		String city = sc.next();
		for (String key : CityContactsArrayList.keySet()) {
			if (key.equals(city)) {
				CityContactsArrayList.get(key).forEach(person -> System.out.println(person));
			}
		}
	}

	void displayingTheContactByState() {
		System.out.println("Enter State:");
		String state = sc.next();
		for (String key : StateContactArrayList.keySet()) {
			if (key.equals(state)) {
				StateContactArrayList.get(state).forEach(person -> System.out.println(person));
			}
		}
	}

	public void addMultipleContacts() {
		System.out.println(" YES ENTER  THE MULTIPLE CONTACTS: ");
        
		ContactPerson contactPerson = createContact();
		UsedAddressBook.add(contactPerson);
		System.out.println(contactPerson);
		System.out.println("Contact added successfully");
	}

	
	    public void addMultipleContacts() {
			System.out.println(" YES ENTER  THE MULTIPLE CONTACTS: ");
			ContactPerson contactPerson = createContact();
			UsedAddressBook.add(contactPerson);
			System.out.println(contactPerson);
			System.out.println("Contact added successfully");
		}

}




