Java_ECommerce_JDBC
--------------
 
This java console application for E-Commerce has the following sections:
------------------------------------------------------------------------
    1. Login - Existing User and Admin
    2. Register - New User
    3. Home - User
        * View Categories 
            - View products based on category.
        * View Products 
        * View Cart 
            - Add products to cart.
            - Check out products from cart.
        * View Order 
        * Logout
 
-> Java for coding 
-> SQL and JDBC for storing and retrieving data.
 
SQL Tables used for each sections:
-----------------------------
    1. "User" Table for storing user data.
    2. "Category" Table for storing categories.
    3. "Product" Table for storing Products.
    4. "Cart" Table for storing user cart details.
    5. "Order" Table for storing user order details.
 
 
Steps used and challenges faced to build this application:-
-----------------------------------------------------------
 
    Steps:
    ------
    1. Used MVC (Model, View, Controller) architecture for developing the console application.
    2. Added the required Models, Views and Controller along with Interface implementation to achieve abstraction.
    3. Added user-defined exceptions to catch anf handle the exceptions.
    4. Used encapsulation to hide data and used getter and setter for getting and setting the data for the models.
    5. Used "SQL TABLES" to store and manipulate data according to the user preferrences.
    6. Used "SQL Database" Files for handling data.
    7. Used JDBC to connect to SQL server.
    8. Used Singleton pattern to avoid creating objects.
    9. Used "Date" class for handling date for orders.
    10. Handled exceptions for invalid choices.
 
    Challenges:
    -----------
    1. Had a problem While inserting and soft-deleting a record simultaneously;
		->Used INSERT SELECT  Querry to do the operation
  
 
 
Working on the following sections 
---------------------------------
  1. Add email validation
 
Credentials 
-----------
  -> For Login use the following credentials
	- *Email = "a" | Password = "a"
	- *Email = "b" | Password = "b"
	  
  
