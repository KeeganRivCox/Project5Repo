# Manage Account

## Test 1 - User Log In:
1. Launch the application.
2. Navigate to the "Sign In" panel.
3. Select the username textbox.
4. Enter the username "customer@purdue.edu" via the keyboard.
5. Select the password textbox.
6. Enter the password "Purdue@123" via the keyboard.
7. Select the "Log in" button.

   **Expected Outcome:** 
   - Successful authentication redirects the user to the appropriate panel based on their role as a "Customer" or "Seller."

## Test 2 - Creating an Account (Selecting Customer):
1. Launch the application and navigate to the "Sign In" panel.
2. Click on the "Create a New Account" button.
3. Fill in the required information, including name ("Customer"), username ("PurdueCustomer"), email ("customer@purdue.edu"), and password ("Purdue@123").
4. Choose the account role as "Customer" from the dropdown menu.
5. Click the "Submit" button to create a new account.

   **Expected Outcome:** 
   - Successful account creation redirects the user to the "Sign In" panel.

## Test 3 - Creating an Account (Selecting Seller):
1. Launch the application and navigate to the "Sign In" panel.
2. Click on the "Create a New Account" button.
3. Fill in the required information, including name ("Seller"), username ("PurdueSeller"), email ("seller@purdue.edu"), and password ("Purdue@123").
4. Choose the account role as "Seller" from the dropdown menu.
5. Click the "Submit" button to create a new account.
   
   **Expected Outcome:** 
   - Successful account creation redirects the user to the "Sign In" panel.

## Test 4 - Forgetting Password:
1. From the "Sign In" panel, click on the "Forgot Password" button.
2. Enter the email "customer@purdue.edu" associated with your account in the Email Field.
3. Enter the user "PurdueCustomer" associated with your account in the Username Field.
4. Click the "Submit" button.
5. If the email and username match, you will be prompted to enter a new password.
6. Follow the instructions to reset your password, and upon success, you'll be redirected to the "Sign In" panel.

   **Expected Outcome:** 
   - Successful password reset redirects the user to the "Sign In" panel, and the Customer/Seller can sign in with the new password.

## Test 5 - Deleting Account:
1. Access the application and go to the "Sign In" panel.
2. Click on the "Delete Account" button.
3. Enter your email "customer@purdue.edu" and username "PurdueCustomer" to confirm your identity.
4. Follow the on-screen instructions to enter your current password ("Purdue@123") twice.
5. Confirm the deletion.
6. Upon success, your account will be deleted, and you will be redirected to the "Sign In" panel. The Customer/Seller will no longer be able to access their account.

   **Expected Outcome:** 
   - Successful account deletion redirects the user to the "Sign In" panel.


# SellerGUI/CustomerGUI

## Test 1 - Login:
1. Run `Server.java`
2. Run `BoilerBay.java`
3. Click on the email box
4. Enter "seller@purdue.edu"
5. Click the password box and enter "seller"
6. Click the "Submit" button

**Expected Result:** Application verifies the Seller’s email and password and loads the Seller homepage automatically

**Test Status:** Passed.

## Test 2 - Logging Out:
1. Log in with the same seller credentials
2. Seller homepage should appear
3. Select the “Log Out” button
4. Log out page should appear
5. Select “No” button
6. Seller homepage should appear
7. Select “Log Out” button
8. Log out page should appear
9. Select “Yes” button

**Expected Result:** User is able to login as a seller and access the seller homepage. Then the user will be able to log out and log back in the same way.

**Test Status:** Passed.

## Test 3 - Login as Customer:
1. Run `Server.java`
2. Run `BoilerBay.java`
3. Click on email box
4. Enter "customer@purdue.edu"
5. For the Password put in "Purdue@123"
6. Click the password box and enter "customer"
7. Click the "Submit" button

## Test 4 - Message System:
1. Run `Server.java`
2. Run `BoilerBay.java`
3. Login as "seller@purdue.edu" with password "Purdue@123"
4. Run another instance of `BoilerBay.java`
5. Login as "customer@purdue.edu" with the password "Purdue@123"
6. On the customer client, click the sellers button, and then click on contact sellers
7. Select “PurdueSeller” and type “Hello” in the text field and click send the send message button
8. On the seller client, click message customer and click on “PurdueCustomer”
9. In the text field, type “hello” and click the send message button
10. On both clients, go back to the main page
11. On customer client, click on sellers and contact sellers and from there click on “received from [SELLER-PurdueSeller]”
12. On seller client, click on message customer, and click on messages, and click on “received from [CUSTOMER-PurdueCustomer]”

**Expected Result:** The customer should be able to see the seller’s message, and the seller should be able to see the customer’s message after going back to the main page, which works as a refresh button; both the customer and seller do not have to log out to view the updates in the message system.

## Test 5 - Creating/Searching for Products and Stores:
1. Run `Server.java`
2. Run `BoilerBay.java`
3. Enter "seller@purdue.edu" and password "Purdue@123"
4. Run another instance of `BoilerBay.java` and log in as “customer@purdue.edu” with the password “Purdue@123”
5. On the seller client, click on the create store button
6. For the name of the Store, put in “Shoe Store” into the text field and click the confirm button
7. Go back to the main page, and repeat step 6 with the name as “Phone Store”
8. Go back to main page, and repeat step 6 with the name as “Furniture Store”
9. Go back to the main page and press the create product button
10. For the product fields, put “Nike Shoe” for the name, “comfortable” for the description, “99.99” for the price, “10” for the quantity, and “Shoe Store” for the store name. Click create product after.
11. Repeat steps 9 and 10 with these fields: “Adidas Shoe” for the name, “extra comfortable” for the Product Description, “89.99” for the price, “13” for the quantity, and “Shoe Store” for the store name. Click create product after.
12. Repeat 9 and 10 with these fields: “iPhone 10”, “fast charge time” for the Product Description, 550.99 for the price, “15” for the quantity, and “Phone Store” for the store name. Click create product after.
13. Repeat 9 and 10 with these fields: “iPhone 11” for the name, “smartphone” for the description, “599.99” for the price, “2” for the quantity, and “Phone Store” for the store name. Click create product after
14. Repeat 9 and 10 with the fields “IKEA Chair” for the name, “soft” for the description, “100.50” for the price, “20” for the quantity, and “Furniture Store” for the store name. Click Create Product after.
15. From the customer main page, click on stores
16. Customer should be able to see “Shoe Store”, “Furniture Store”, “Phone Store”
17. Go back to the main page, and click on products
18. Customer should be able to see “Nike Shoe”, “Adidas Shoe”, “iPhone 10”, and “iPhone 11”, “IKEA Chair”
19. Go back to the customer main page, and press the search button
20. Choose search by product
21. Search “Nike Shoe”. If the user enters nothing or an invalid product, they get an error message and must search again.
22. Customer should be able to see “Nike Shoe” as the search result
23. Repeat steps 15 and 16 but search by store instead
24. Search “Phone Store” If the user enters nothing or an invalid store name is put in, the user will receive an error message.
25. Customer should be able to see "iPhone 10" and "iPhone 11"
26. Repeat steps 15 and 16 but search by product description
27. In the search text field, type “comfortable” and click the search button. If the user enters nothing or an invalid Product Description, the user will receive an error message.
28. Customer should be able to see “Nike Shoe” as the search result

**Expected Result:** Sellers are able to create stores and products. Customers can search by name, description, and store to see products and stores created by sellers.

## Test 6 - Editing Stores and Products:
1. Run `Server.java`
2. Run `BoilerBay.java`
3. Login as “seller@purdue.edu” with password “Purdue@123”
4. Run another instance of `BoilerBay.java`
5. Login as “customer@purdue.edu” with the password “Purdue@123”
6. On the seller client, click on edit store
7. Select “Shoe Store” and select change store name
8. Enter “Footwear Store” into the text field and click new name and confirm
9. Go back to the main page and click edit store and select “iPhone Store”
10. Click delete store and confirm that you want to delete it
11. Go back to the main page, and click on edit product
12. Click on “Footwear Store” and select “Nike Shoe”
13. Change the name to “Nike Air Forces” and change the price to “79.99” and change the quantity to “5”
14. On the customer client, click on stores
15. The customer should only see “Footwear Store” and “Furniture Store”
16. Go back to the main page and click on products
17. The customer should only see “Nike Air Forces”, “Adidas Shoe”, and “IKEA Chair”

**Expected Result:** Sellers can edit products or delete them, and the customer should be able to see the products and stores with their updated info but cannot view a deleted product anymore when viewing all the products.

## Test 7 - Purchasing Products/Viewing Product Listing Page/View Shopping Cart/View Individual Product Page:
1. Run `Server.java`
2. Run `BoilerBay.java`
3. Enter "seller@purdue.edu" and password "Purdue@123"
4. Run another instance of `BoilerBay.java` and log in as “customer@purdue.edu” with the password “Purdue@123”.
5. In the Customer Main Page, Click Products
6. From the sort drop-down, select Price High to Low
7. Products are sorted in this order: IKEA Chair, Adidas Shoe, Nike Air Forces
8. Click the sort drop-down, select Price Low to High
9. Products are sorted in this order: Nike Air Forces, Adidas Shoe, IKEA Chair
10. Click the sort drop-down, select Quantity Least to Greatest
11. Products are sorted in this order: Adidas Shoe, Nike Air Forces, IKEA Chair
12. Click the sort drop-down, select Quantity Greatest to Least
13. Products are sorted in this order: IKEA Chair, Nike Air Forces, Nike Air Forces
14. From the Product Listing Page, click Nike Air Forces
15. From the Nike Air Forces Product Page, Increase the Quantity to One.
16. Click Checkout
17. Go Back to the Customer Main Page, and click Accounts
18. From Accounts, click View Shopping Cart
19. Check to ensure that Nike Air Forces is in the Shopping Cart, and then press Checkout. A pop-up should appear asking if you are ready to check out and click yes.
20. From the Seller Main Page, Click List Stores
21. From the stores listed, click Store One
22. Go back to the main page and click Store Statistics
23. Click view all store stats
24. Should see Total Revenue Generated from all Stores is $79.99

**Expected Result:** Customer can Purchase Product, View Product Page, Product after being added to the shopping cart can be checked out. Store Revenue for the store the product was purchased from increases by the amount purchased. Store One revenue should be $5.99.

## Test 8 - Export CSV:
1. Run `Server.java`
2. Run `BoilerBay.java`
3. Login as “customer@purdue.edu” with password “Purdue@123"
4. Click on account and then click on purchase history
5. Customer should only see Nike Airforce
6. Click export 

**Expected result:** The user should have an exported csv file with all their past purchases.
