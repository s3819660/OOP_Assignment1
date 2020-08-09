RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2020B
  Assessment: Assignment 1
  Author: Nguyen Phuoc Nhu Phuc
  ID: s3819660
  Created  date: 28/07/2020
  Last modified: 09/08/2020 

1. INTRODUCTION
QuickLib is an applicant which interacts with its user in this case the librarians by Command Line Interface.
The application is implemented to manage library items and members. An item can either be a book, a journal or a DVD.
A book has a title, authors, edition, publication, year, ISBN (13 or 10 digits), language, subject, and status (available or on loan)
A journal has a title, publication, year, ISSN (8 digits), language, subject, and status.
A DVD has a title, authors, publication, year, language, subject, and status.
A member has full name, ID (a driver’s license number or a passport number), phone, email, address, expired date (same as the expired date of the ID provided), and status (active or expired).

2. FEATURES
The main functions of this simple applications are listed below:
- Search items by keywords
- Add new item
- Update item info
- Search members by keywords
- Register new member
- Update member info
- Borrow items
- Return items
- Save data
- Quit

3. DESIGN
- Each item object is a book/journal/DVD with multiple copies.


4. INSTALLATION
- InteliJ IDEA Ultimate
- Java 11

5. KNOWN BUGS
- Validation does not work when updating member and items.

6. ACKNOWLEDGEMENT.
- I would like to thank Dr. Tran Ngoc Quang who have provided me with valuable support and guidance in the completion of this project.
- course Object-Oriented Programming, the lectures.
- javatpoint, "Java instanceof", Available at: https://www.javatpoint.com/downcasting-with-instanceof-operator
- HowToDoInJava, "How to serialize and deserialize ArrayList in Java", Available at: https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/
- Stackoverflow, "Java regex email", Available at: https://stackoverflow.com/questions/8204680/java-regex-email
- Baeldung, "Difference Between Two Dates in Java", Available at: https://www.baeldung.com/java-date-difference
- Baeldung, "Validate Phone Numbers With Java Regex", Available at: https://www.baeldung.com/java-regex-validate-phone-numbers