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
- Search items by keywords: Perform a search Item by keywords, it could be the keyword from any field of the item list
- Add new item: Add new item to the list, can only be Book, Journal, and DVD
- Update item info: Updating existing Item information, select the item ID to update it
- Search members by keywords: Perform a search Member by keywords, it could be the keyword from any field of the member list 
- Register new member: Register new member to the list
- Update member info: Update current information of existing Member in the list, member can extend their expire date
- Borrow items: Perform a borrow for a Member, only items which is in stock can be borrowed.
- Return items: Return a borrowed item for a Member, if the return date is late, it will automatically calculate late fee for Member and add to their record.
- Save data: Save all existing information
- Quit: Save all existing information and exit program

Additional feature:
 - Validation of entries upon input the data to the program: Entries such as year, ISBN, ISSN is validated accordingly, wrong input will prompt user to input again
 - Pagination: View 10 items/members per page, press n to next page, p to previous page
 - Press enter to exit to menu: User press Enter to go back to menu rather than the application automatically back to menu after completing a task

3. DESIGN
- Each item object is a book/journal/DVD with multiple copies
- Item object is the super class of Book, DVD, Journal class
- Member object also stored the borrow information: Borrow Items and Borrow date
- The Model are set to the controller - view: Member model linked to MemberList, Item linked to ItemList
- The QuickLibUI will act as the main controller and main view

4. INSTALLATION

- InteliJ IDEA Ultimate
- Java JDK 11

5. KNOWN BUGS

- Validation does not work when updating member and items.
- The scanner buffer does not properly clear when the user spam enter

6. ACKNOWLEDGEMENT.

- I would like to thank Dr. Tran Ngoc Quang who have provided me with valuable support and guidance in the completion of this project.
- course Object-Oriented Programming, the lectures.
- javatpoint, "Java instanceof", Available at: https://www.javatpoint.com/downcasting-with-instanceof-operator
- HowToDoInJava, "How to serialize and deserialize ArrayList in Java", Available at: https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/
- Stackoverflow, "Java regex email", Available at: https://stackoverflow.com/questions/8204680/java-regex-email
- Baeldung, "Difference Between Two Dates in Java", Available at: https://www.baeldung.com/java-date-difference
- Baeldung, "Validate Phone Numbers With Java Regex", Available at: https://www.baeldung.com/java-regex-validate-phone-numbers
