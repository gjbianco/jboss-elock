jboss-elock: Web Service Permission-based Door Locking
========================
Author: Guy Bianco
Technologies: CDI, JPA, EJB, JAX-RS, Arquillian, Hibernate
Target Product: EAP

What is it?
-----------

This is a simple test project using JAX-RS and Hibernate 4.

The goal of this project is to provide a RESTful web service that checks door lock permissions on a user against required door access permissions stored on a database. The user must be entered into the system as well (unless requisite door permission == 0; see below).

In order for the user to be given access to the door, they must have a (non-expired) card registered for the proper permission (or higher) of that door. E.g. to open a door with required permission of 2, the user must own a card whose expiration is no earlier than the current date and the card must have a permission >= 2.

Users can own multiple cards. This allows for temporary priveleged access without having to issue 2 new cards. E.g. the user has card X with permission level 2, which expires in a year; the user is issued card Y with permission level 3, which expires in six months. The user can access level 3 doors for that time without needing a new card in six months (the level 3 card just expires).

Permissions:
  0 - anyone (no user or card required)
  1 - any user (must have user and card registered)
  2 - employee
  3 - maintenance
  4 - admin

Testing the Application
------------------------------------

Tests are implemented in Arquillian.

You can run the tests using Eclipse or by calling Maven directly. Suggested command:

`mvn test -Parquillian-jbossas-remote -Dtest=[test-name]`

Current potential test names:

 * UserDaoTest
 * CardDaoTest
 * DoorDaoTest