### 1. Introduction
The company Siegwerk AG & CO. KGaA is one with 4,500 employees in more than 30 country companies to the world's leading producers of printing inks.
To be able to deliver the printing inks produced by Siegwerk,
these must first be packed according to the customer and dangerous demands. Printing inks are produced at Siegwerk in different batches.
The volumes used the containers vary significantly.
At present, about 173 containers in the current PDB (Packaging database) are managed.
A container is defined by a variety of properties such as paints, volume, material, etc. handles.
The aim of the project is a web app based on its SharePoint, which can be used by key users globally.
The implementation of the project must be done in consultation with the Siegwerk project management in the short term.
Essential requirements for the new application are: A comfortable search, clear and simple menu structure, implementation of an authorization concept.
In addition, a data exchange between the PDB and the existing SAP to be implemented if necessary..

### 2. Description of the current situation.

#### 2.1 Current system environment
The existing application is a web application that consists of the following components.
Web server: Apache 2.2.3
Database: My SQL 5.0.32
Programming language: PHP 5.2.0-8

#### 2.2 Structure of Software
The current software is a user-programmed web application in HTML.

#### 2.2.1 Home Screen
The start screen of the application includes important and current information. The information can be displayed on the home screen can be adjusted in the maintenance menu.

#### 2.2.2 menu
The menu is built rudimentary. Here repeat partially menu items, -which proves inconvenient for the user. Furthermore,
the individual relationships of data is not visible, resulting in very expense intuitive operation.

#### 2.2.3 menu options and their functions
All menu items are listed as in the inventory software. The arrangement of menu items is not always logical.
Currently, the menu is based on the following:

#### 10 - Global Processes
* Global processes:
    Serves for describing global processes such as the introduction of a new packaging.
* Care Policy:
    Care Mask for processing packaging guidelines.
* Care processes:
    Care Mask to global processes.
    Figure 4 Page 10 - Global Processes
* Policy:
    List of guidelines for packaging materials (relates to maintenance processes).

####

#### 2.2.1 Home Screen
The start screen of the application includes important and current information. The information can be displayed on the home screen can be adjusted in the maintenance menu.

### 3. Description of the target concept.

#### 3.1. System environment
The new application should be able to be accessed via SharePoint 2013 Foundation. The application is to be made as modern
SharePoint app available that will retrieve your data from a SharePoint independent and relational MS-SQL database.
After the development of the database, this is to be installed on an existing SQL cluster.

#### 3.2. layout of the database
The layout of the database is adapted to the supplied input masks. Relations can the ER models of pack types and the references
of the selection fields (Excel data entry screens) are removed.

#### 3.3 surfaces and structure of the application

#### 3.3.1 Surface design
The interface design of the new application should be based on Siegwerk homepage. The use of Siegwerk red for the application
In addition, the Siegwerk logo should serve in the application as a navigational button to start the application.
The logo can be found in the appendices (SW_Logo_Screen.zip).

#### 3.3.2 menu
The application menu should be set up as follows:

* Start Page
* Global Processes & Information
    - Global Processes
    - Global Policies
* container
* Manufacturer
* Registration certificates
* Administration

### 4. Description of interfaces.

#### MS-SQL
Since the data storage in a relational MS-SQL database is to be implemented, the
Interface between the to developing SharePoint app and the existing SQLCluster
(Data base) is required.

### 5. Functional Requirements

#### 5.3 Global processes and information

#### 5.3.1 Global Processes

This menu item only one type of document library is needed. A title should describe the attached document.
In addition, should be saved who uploaded the document and when this is done.
The following columns are required:
* Title
* Document Upload
* Uploaded by
* Uploaded

As functionalities an up- and download a function is to be provided. The download is available from any authorized user
with read permissions. The upload functionality is to be used only by users who have write rights ("pdb_writer", "pdb_admin") feature.

#### 5.3.2 Global Information
And the function of this menu item is to be identical in structure to the point "Global processes".

#### 5.4 container
A container describes a package possibility of printing inks of Siegwerk.

#### 5.4.1 Summary Table
There is a summary table to be developed that will list all the packages. This table should list the following basic information of a bundle:

- Name/Title
- VPS number
- Nominal volume
- Container type
- Image
- Manufacturer 1-x (N to M relationship)
- Approval 0-x (N to M relationship)
- Download Product Sheet SCM (portrait)
- Download product sales (landscape)
- Button / Link Detail (Can also be implemented by clicking, for example, the name / label)

The button / Link Detail functionality to the more detailed information is necessary to display a record.
This view also to all functionalities are available.

- Download product sheets
- Editing the record
- Delete the record
- Upload images (1 image is displayed in Table View)
- Upload documents (Manufacturer Specifications, drawings, ... entry upload)

#### 5.4.2 Container Designed
Above the summary table is a link / button are made available, which forwards to the input screen of a new container.
For a new container, the plant, the VPS specifications should be entered first. Following this entry, the type of the package
should be selected. The types must be offered:

- IBC Cubic
- IBC Cylindrical
- IBC bulk
- Clamping ring Cylindrical
- Conical clamping ring
- Canister Cubic
- Cans Conical
- Big Bag

After selecting a package types, the input form must adapt according to the container sizes types.
The different input masks can be found in the attached Excel spreadsheet ("EingabemaskenGebine.xls").
Before saving a record to be checked whether all mandatory fields have been filled in accordingly.

#### 5.4.2.1 Selection Fields

Selection fields are referenced to existing records such as Coatings s. Annex ("Eingabemaskengebinde_Referenzen.xls").
To select only is the unique name of a data set are.

#### 5.4.2.2 Care checkmarks

Selection fields must be maintained. New records are created, can be existing modified or deleted.
For this purpose, a treatment mask is required for each selection field. In the respective maintenance
screen can be accessed via a button / link next to the relevant selection field.
The treatment mask should have a table view that lists all the registered records of the relevant selection field.
In addition to the view be possible to add a new record to be able to edit an existing and delete a record.

When deleting is important to ensure that the referential integrity is not violated.

#### 5.4.3 Container change
In the summary table, a link / button to be provided, leading to the edit screen of the respective container.
The mask should be the same as a new installation. Fields should be filled with the stored data.

#### 5.4.4 Deleting containers
In the summary table, the possibility should be offered to be able to delete old container.
A deletion is to be confirmed with a query window.
The data is no longer displayed in the overview, marked in the database only as disabled.

#### 5.4.5 Sheet-export
Each packaging is a product sheet can be exported. The export will be provided in PDF format.
The export differs by packaging type and region (SCM / Sales). For each Nonslip classification exists
a sheet template, which is to be used automatically when exporting and filled accordingly. The following product types are available:

- IBC Cubic
- IBC cylindrical
- IBC bulk
- Cylindrical clamping ring
- Conical clamping ring
- Canister cubic
- doses
- Big Bags

The templates to be filled can be found in the appendix (Sales_Ansicht_QF, SCM_Ansicht_HF).

#### 5.5 Manufacturer
Packaging materials are sourced from Siegwerk from different manufacturers. Since there may be N to M relationship
between packaging / producers and manufacturers / registration certificates here, a private nursing menu is required.
The table manufacturer is described in annex (EingabemaskenGebinde_Referenzen.xlsx / company).
In addition to the investment of producers, it must be possible a manufacturer several packaging materials,
such as being able to add registration certificates. All associated packaging and registration certificates
should be displayed so that you get directly by clicking on the respective link in the record of the package or the certificate of approval.

#### 5.6 registration certificates
Registration certificates are documents / approvals from a certified testing laboratory which enables the Siegwerk
for permission to use packaging with the specifications listed.
A registration certificate must be added to the system in the form of uploads.
The Certificate of Approval can be also provided at this location for the user to download.
If a registration certificate uploaded so it can be linked with one or more VPS numbers.
All linked VPS / packaging materials are to be displayed under a certificate of approval.
A Certificate of Approval is a packaging manufacturer assigned must also be selected when uploading.
In addition to display by linking the registration certificate in the VPS / container sizes specification.
Here is a direct link to the registration certificate exist, which allows to keep the document View directly.

#### 5.7 Administration
In the administration area, there will be two types of administration.

- User management:
In the user administration, administrators are shown three different permission levels get (see 5.7 permissions).
After selecting a privilege level of admin enters the group in which he receives all authorized users listed.
Here he can add the Domain Users group or remove it (SharePoint default permissions).

- Release of records:
Once a user is authorized "pdb_writer" must make these changes. These changes must be reviewed and approved by a "pdb_admin".
Therefore it is necessary to show the change at this point and give the admin the ability to release this change or discard.

If changed, the Admin should the records be notified by email to be released.

#### 5.8 permissions
There are the following permissions are applied.

- Pdb_reader:
The pdb_reader allowed to access the application, can see content and access product sheets

- pdb_writer
The pdb_writer included the pdb_reader, this is supplemented by the right
Creating records and allowed to edit.

- pdb_admin
The pdb_admin included the pdb_writer, this is supplemented by the permission of approval for new and edited content,
maintaining the permissions (user access), and deleting records.

#### 5.9 multilingualism
The application is to be made available in multiple languages.
It should be set by the user if the application is called in German or English. Default language is English.
When a user with a "German" Internet Explorer, so the application should recognize this and the system language for
the user in German automates change, unless the user has explicitly selected English as the system language (Can).
Manual entries are to be stored in titles and descriptions in both languages. The columns to be stored in both languages,
please refer to the Excel files (Care Masks).

### 6. Nonfunctional requirements

#### 6.1. Easy operation
In a particularly simple operation particularly great importance is attached. The
existing application already included many fields and is only
replaced because of poor service to end users. should therefore following
Points are integrated into the new application:

* Structured menu guidance
* For entry forms that refer to foreign keys that possibility should
consist in:
    - to look o For more information on foreign keys in detail be able to create o New foreign key without the made
    - losing entries
* Convenient search:
    - Full text search
    - Column-related search criteria on all attributes
        - Begins with
        - Ends with
        - Contains
        - lst
        - Greater
        - Smaller
        - Option search (foreign key)
* Table filters
* Care masks should be all created equal
* Information pages should be installed
* Released by administrators

#### 6.2. Reliability
The reliability of the application should refer to the availability of SharePoint Server and MS-SQL data management.

#### 6.3. changeability
All contents should be changed by authorized users. The layout can be set by the developer and must for an end user is not changeable.

#### 6.4. Transferability
The database should be accessible for an authorized user at the database level. In addition, it should be possible
to transfer the application to future versions of SharePoint newer.


### 7. development cycle
The choice of process models for the development of the application is up to the contractor.
However, it should be ensured that the customer is involved in the entire development process.

### 8. delivery
Delivery is to install SharePoint "app" with a to install MS SQL database. In addition,
the programming code must be completely transferred to the client.

### 9. Acceptability
As acceptance criteria the successful completion of acceptance tests of Siegwerk be defined and the full handover of the delivery and service scope.

