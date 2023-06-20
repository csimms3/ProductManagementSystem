# Store Inventory Manager

## What will it do?
The Store Inventory Manager will allow a store owner to manage their product inventory by:
- Creating **digital versions of their products**, with pricing, descriptions and images
- Keeping track of their **stock numbers**
- Processing **sales and shipments**
- Allowing them to **search** through their products digitally

## Who will use it?
The Store Inventory Manager will be used by any store employees 
to perform job duties, as well as by customers, who may search the
store's inventory and view products online. When used by customers,
certain actions may be restricted to prevent unauthorized price
alterations and similar activity.

## Why this project?
During high school, I had a part-time retail job which used an
inventory manager to do the things listed above, as
almost all stores do. As I became more interested in
programming and software development, I began to think
about how such a system could be designed and implemented.
This project will allow me to further pursue my curiosity
around this topic, allowing me to dive deeper into the specifics.

## User Stories
- As a user, I want to be able to create a new product and add this product to the catalogue
- As a user, I want to be able to browse the catalogue (view all products)
- As a user, I want to be able to search for a product by product number
- As a user, I want to be able to process sales, updating stock numbers in doing so
- As a user, I want to be able to save the state of my inventory to file (if I want)
- As a user, I want to be given the option to load my inventory from file when I start the application

## Instructions for Grader
- Start the application by running ProductSystemUI.main().
- Note the graphic that shows up while loading, this is the visual element.
- Now, you may click "load from file" to optionally load the application state.
- Whether you loaded the file or not, you can click "create product" to create a new product. 
- Enter a name, a price in form (xx.xx), and a 4-digit id. If you load from file make sure
that this id is unique (see below).
- Now, go back to the main menu, and click "view catalogue".
- Here you see all products, note the unique id proceeded by "#". This is how you will search.
- Now, make note of one of these id's, and return to the main menu.
- Click on "search", and enter the given product number.
- This will display a product view that will allow you to update its stock, or delete the product.

## Phase 4: Task 2
Wed Mar 29 13:48:43 PDT 2023 \
Product 24 added. \
Wed Mar 29 13:48:43 PDT 2023 \
Product 100 added. \
Wed Mar 29 13:48:43 PDT 2023 \
Product 2020 added. \
Wed Mar 29 13:48:44 PDT 2023 \
Viewing product 24 \
Wed Mar 29 13:48:44 PDT 2023 \
Viewing product 100 \
Wed Mar 29 13:48:44 PDT 2023 \
Viewing product 2020 \
Wed Mar 29 13:48:58 PDT 2023 \
Product 24 was deleted. \
Wed Mar 29 13:49:01 PDT 2023 \
Viewing product 100 \
Wed Mar 29 13:49:01 PDT 2023 \
Viewing product 2020 

## Phase 4: Task 3

Looking at the UML diagram of my project, two things are immediately evident to me.
For one, all my UI classes, which each represent an individual window, are completely
separate. Two, all these separate windows have a ProductManagementSystem, which leads
to the fairly messy connections of associations to ProductManagementSystem.

To fix the first problem, I would notice that there is likely a certain design/style I
want all my windows to follow, which means common colors/margins/button styles between 
windows. This is something I could abstract out into a "window template" class or something
of the sort. I could also further group my windows, for example, as shown in the UML diagram,
both ProductViewerUI and UpdateStockUI work with individual products, and it could be argued 
that ProductCreationUI does too, even if it does not have a direct association. I could therefore
abstract these out, taking their common features as classes that work with individual products and
putting this into another class/interface.

In my opinion, the second "problem" is more of a result of a design choice I made than an actual problem.
This is because I chose to have each window delete itself when a new window is summoned, and then when
the "x" is pressed, a new copy of the old window is created. Due to this, each window needs to be able to
completely construct itself off only a ProductManagementSystem or Product (or both!), and therefore does 
not rely on other window's information. An alternate choice here would be to have all windows access a central
association to ProductManagementSystem, and instead of calling frame.dispose() to delete a given window,
I could simply hide windows then have them reappear. The complication here would be dealing with window updating,
which my solution managed to bypass.
