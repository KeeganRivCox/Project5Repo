# TeamProject5Repo
Shared repository regarding project 5 for group members.
Must include 3 Selection Features for 5 group members.
Can complete Optional Features for Extra Credit.
# Project 5 - CS180

# Instruction

To compile and run our project:

```
javac Server.java
java Server.java
```

then run the following

```
javac BoilerBay.java
java BoilerBay.java
```

It will load the Account class where you can choose to log in, create account, forgot password or exit. Once you log in, the program will load the Marketplace.

# Submission

Project 5 was submitted by Keegan Cox in Vocareum. <br />
Report was submitted by Adeetya Upadhyay on BrightSpace. <br />
Presentation was submitted by Adeetya Upadhyay on BrightSpace. <br />


# Functions as required by Project 5

## Testing

All of the testing for project 5 can be found in the Test.md file.
The testing ensures that all methods work as intended. 


Our Option: Option 3
- The third option is to implement the official marketplace of the application. The marketplace will allow sellers to list their products and customers to purchase them. 
- Looking for an example? Review the listing pages for any online store. 
- Reminder: You can assume that only one user is accessing the application at a time. A seller might log in, list a product, then log out. A customer could then log in and purchase the item.

Our implementation has the following: 
- Common Features
    - While the specifics of each implementation will be related to the option you select, there are several common features for every project. 

- User Interactions
    - All Project 5 input and output should be handled via the keyboard (System.in) and screen (System.out), just as with all of your previous projects. There is no GUI for this project.
      
- Data 
    - Data must persist regardless of whether or not a user is connected. If a user disconnects and reconnects, their data should still be present. 
    - If a user creates an account and then closes the application (it is no longer running), they should still be able to log in when running it again.
    - Descriptive errors should appear as appropriate. For example, if someone tries to log in with an invalid account. The application should not crash under any circumstances. 
- Roles
    - There will be two defined roles in the application: Sellers and Customers.
    - Sellers will be able to create stores to sell their products and maintain relationships with customers. 
    - Customers will be able to purchase products and contact sellers. 
    -Users will select their role  while creating an account, with permissions associated with their actions accordingly. 
    - Permissions details will be noted in the option requirements. 
- Accounts
    - Users can create, edit, and delete accounts for themselves.
    - The attributes you collect as part of account creation are up to you. At a minimum, there should be an email and password associated with each account. 
    - Users should be required to either create an account or sign in before gaining access to the application. 
    - Whichever identifier you maintain for the user must be unique. 
    - During account creation, a user will specify their role.
      
- Stores
    - Each application must support multiple stores.
    - Sellers will be able to create as many stores as they like.
    - Customers can access any store that has been created. 
    - Note: The term "user" is used to refer to any user of the application, including both customers and sellers. 

Core:
  - Market
    - The marketplace will be a centralized page listing available products for purchase. 
    - Products will include the following information: 
      - Name of the product
      - The store selling the product. 
      - A description of the product
      - The quantity available for purchase
      - The price
    - The marketplace listing page will show the store, product name, and price of the available goods. Customers can select a specific product to be taken to that product's page, which will include a description and the quantity available. 
    - When items are purchased, the quantity available for all users decreases by the amount being purchased.
      
  - Sellers
    - Sellers can create, edit, or delete products associated with their stores. 
    - Sellers can view a list of their sales by store, including customer information and revenues from the sale.
      
  - Customers
    - Customers can view the overall marketplace listing products for sale, search for specific products using terms that match the name, store, or description, and sort the marketplace on price or quantity available. 
    - Customers can purchase items from the product page and review a history of their previously purchased items. 
    
Selections:
  - Files
    - All file imports must occur as a prompt to enter the file path.  
    - Sellers can import or export products for their stores using a csv file. 
    - All product details should be included, with one row per product. 
    - Customers can export a file with their purchase history.
       
  - Statistics
    - Sellers can view a dashboard that lists statistics for each of their stores.
    - Data will include a list of customers with the number of items that they have purchased and a list of products with the number of sales. 
    - Sellers can choose to sort the dashboard.
    - Customers can view a dashboard with store and seller information.
    - Data will include a list of stores by number of products sold and a list of stores by the products purchased by that particular customer. 
    - Customers can choose to sort the dashboard.
        
  - Shopping cart
    - Customers can add products from different stores to a shopping cart to purchase all at once, and can remove any product if they choose to do so.
    - The shopping cart is preserved between sessions, so a customer may choose to sign out and return to make the purchase later.  
    - Sellers can view the number of products currently in customer shopping carts, along with the store and details associated with the products. 

Optional Features: 
  - Sellers can elect to hold sales that reduce the price of a product until a specified number of units are sold. Customers will be informed of the original and sale price when browsing the marketplace. 
  - Customers can leave reviews associated with specific products from sellers. Other customers can view the reviews after they post. Sellers may view reviews on their products. 
  - Sellers may set a per product order quantity limit that prohibits customers from ordering more units than the limit.
  - Customers will not be able to place any additional orders for a product after they reach the limit, unless the seller increases or removes it.

# Detailed Description of Each Class

## Accounts and AccountsPanel Class
- User attributes include name, email, username, and authentication flags for seller and customer roles.
- Main functionality involves creating, signing in, resetting passwords, and deleting accounts.
- File I/O is used to read and write user data to a text file (user_data.txt).
- Account creation checks for valid email format and enforces password requirements.
- Deletion requires confirmation with username, email, and password.
- Account data is stored in a comma-separated format in the user data file.
- Password validation includes checks for length, numbers, and special characters.
- Email format validation checks for the presence of '@' and '.' in the email.
- The sign-in process allows a limited number of attempts (3) and checks credentials against stored data.
- Users can reset their password by providing username, email, and a new password.
- Role selection prompts users to choose between "Customer" and "Seller."

## Customer and CustomerPanel Class
- Fetches and updates customer data based on the provided account information.
- Provides functionality to display a list of products, check if a product is in the shopping cart, and navigate through seller information, and contact them if required.
- Additional functionality includes viewing previously purchased products, managing the shopping cart, searching and sorting products, and returning shopping carts.
- Handles purchasing items, exporting purchase history to a text file, and searching for stores and obtaining product information.
- Some methods involve exporting data to text files, and the code includes exception handling for file operations.

## BoilerBay Class
- Displays a login interface and prompts users to choose actions such as creating an account, signing in, resetting passwords, or exiting.
- Upon successful login, differentiates between seller and customer roles.
- If the user is a seller, a seller interface is presented with options to manage stores, view statistics, create, edit, or delete stores, and log out.
- If the user is a customer, a customer interface is presented with options to view products, sellers, search for products, manage their account, or log out.
- Validity checks are in place to ensure the entered choices are numeric, and appropriate error messages are displayed otherwise.

## Product Class
- Represents a product in a market application with attributes such as name, description, quantity, price, and associated store.
- Prints detailed information about a product, including seller, store, name, description, quantity available, and price.
- Prompts the user to input details for a new product and creates an instance with the provided information.
- Returns a formatted string containing essential product details for display.
- Getter methods retrieve information about the product, including name, description, quantity, price, quantity sold, revenue generated, and associated store.
- Setter methods update product attributes.
- Equals method compares two products for equality based on their associated store, description, and price.

## PurchasedProducts and Purchaser Class
- Represents a purchased product in a market application.
- Contains attributes for the purchased product and the quantity of that product purchased.
- Constructor initializes a PurchasedProduct with a specified product and the quantity purchased.
- Getter methods provide access to the name, amount purchased, store name, and the purchased product itself.
- Encapsulates the details of a purchased product, facilitating serialization for storage or transfer of purchase information.

## Sellers and SellerPanels Class
- Includes an "equals" method to check if two sellers are equal based on their username and email.
- Methods to fetch and update seller data, including retrieving all seller data, updating specific sellers, and updating all sellers.
- Handles file I/O for reading and writing seller data to a file.
- Offers functionality to list detailed information about the seller's stores, including product details.
- Additional features include displaying store statistics, such as overall store sales, specific store statistics, and customer shopping cart information.
- Methods handle user input for various options, providing a menu-like interface for the seller to interact with their data.
- Displays product sales information for a given store, allowing sorting by quantity sold or revenue generated.
- Helper methods for sorting products by revenue generated or quantity sold.
- Displays customer sales information for a store, offering sorting options by quantity purchased or revenue generated.
- Helper methods for sorting purchasers by revenue generated or quantity purchased.
- Guides the seller in creating a new store, providing options to add products manually or via CSV entry.
- Allows the seller to edit store information or export store products to a CSV file.
- Removes a specific product from a store based on provided details.
- Guides the seller in deleting a store, providing a confirmation prompt.
- Prints total revenue statistics for each store and the combined revenue from all stores.
- Contains various helper methods for input validation.

## Shopping Cart Class
- Manages a list of products in a shopping cart.
- Uses serialization to store shopping cart data in the "shopping_cart.txt" file.
- Provides a constructor to initialize an empty shopping cart with an ArrayList of products.
- Contains a method to retrieve the list of products in the shopping cart.
- Includes methods related to adding, removing, and viewing products in the shopping cart.

## Store Class
- Provides methods for editing products within the store, including changing product details and adding new products.
- Contains a method for exporting the list of products to a specified file.
- Implements getters and setters for the store's name, seller, purchaser list, and product list.
- Includes an equals method to check if two stores are equal based on name and seller.
- Contains methods related to calculating store revenue, individual purchaser revenue, and exporting store product data.
- Also includes methods for exporting store products to both a CSV file and a text file.
