Input Options
==================================================================

* Text:             Text Entry
* ID:               ID is assigned automatically
* Selection:        Select Box with reference to existing records
* Multiple:         Selection Select Box with references (N: M) to existing records
* Boolean:          Yes/No
* Attach:           Upload all files
* Domain Username:  Logged users

Containers input masks
---------------------------------------------------------------------
- [x] IBC Cubic
- [x] Cylindrical
- [x] Bulk
- [x] Cylindrical clamping ring
- [x] Conical clamping ring
- [x] Cubic Canister
- [x] Canonical Cans
- [x] Big Bags

Containers input masks References
-------------------------------------------------------------------
- [x] Verp Instruction (Verp Anweis)
- [x] Transport
- [x] Coatings
- [x] Gasket
- [x] Handles
- [x] Couplings
- [x] Palette Types
- [x] Closure
- [x] Materials
- [x] Packaging form
- [x] Test medium
- [x] Authorization
- [x] Assimilation
- [x] Business Unit
- [ ] Status
- [x] Language
- [x] Address
- [x] Country
- [x] Location
- [x] Companies
### Generic fields

* CreationDate
* CreatedBy
* LastUpdateDate
* UpdatedBy

#### Instruct

* Transport Law
* Verp provision
* Pack outdoor
* Pack inside
* Vol VerGrp II
* Vol VerGrp III
* BME

#### Transport

* Verp Grp
* class
* Verp Vorschr
* VerpSo suggest and
* IBC Vorschr
* LtdQty Vorschr
* TaVoSW
* IATA LQ Vol
* IATA_LQ_V
* IATA LQ amount
* IATA_LQ_M
* IATA LQ BME
* IATA_LQ_BME
* IATA PASSENGER ago
* IATA_PAS_V
* IATA PASSENGER M
* IATA_PAS_M
* IATA PASSENGER BME
* IATA_PAS_BME
* IATA CARGO ago
* IATA_CAR_V
* IATA CARGO M
* IATA_CAR_M
* IATA CARGO BME
* IATA_CAR_BME
* Assim.
* permeate
* explosion group
* Data Source

#### Coatings (beschichtung)

* ID                Long PK
* SW code coating   varchar
* SW code hierarchy varchar
* description       varchar
* certificate DFC   blob
* Remarks           varchar
* utilisation       text(blob)

#### Seals (Gasket) (Dichtungen)

* ID            Long
* description   varchar(255)
* circumference Float(5,1)
* thickness     Float(5,1)
* material      FK
* utilisation   text(blob)

#### Handles (Griffe)

* ID            Long
* name          Varchar
* remark        Varchar

#### Couplings (Kupplungen)

* ID                Long PK
* description       varchar
* Pipe diameter     int(5)
* material          FK - Material
* sealing material  FK - Gaskets
* image             varchar
* drawing           varchar
* Others            varchar
* TS couplings      varchar
* LA couplings      varchar

#### Palette Types (ML)

* ID
* palette type

#### Closure (Verschluesse)

* ID                    Long PK
* closure               varchar
* closure diameter      Float(5,1)
* coating the outside   FK - Coatings
* coating inside        FK - Coatings
* material closure      FK - Material
* seal closure          FK - Gaskets
* Remarks               varchar

#### Material (Werkstoffe)

* ID                - Long
* material name     - Varchar
* short name        - Varchar
* Remarks           - Text(Blob)

#### Packaging form

* ID
* code
* from GE
* Others

#### Test medium

* ID
* medium
* code
* Others

#### Authorization

* ID
* authorization
* Test medium
* UN Code
* Vapor pressure at 50 C
* Vapor pressure at 55 C
* density X
* density Y
* density Z
* container type
* UNPR confirmation.
* MAH
* Sleeps
* unit
* Filling aggregate state
* Max. Gross Weight
* means of transport
* authorization
* version
* date of issue
* Permeation f? R PE
* un assimilation
* UN packing instructions
* UN transport

#### Assimilation

* ID                    Long PK
* designation           varchar(255)
* class                 Varchar(4)
* classification code   varchar(5)
* classification group
* packing group         varchar(5)
* assimilation code     int(10)
* Data Source           blob

#### Business Unit (businessunit)

* ID            - Long
* match code    - Varchar
* name          - Varchar
* Misc.         - Varchar

#### Location

* ID            - Long
* SAP Code      - Varchar
* name          - Varchar
* road          - Varchar
* zip code      - Varchar
* place         - Varchar
* country       - Varchar
* Others        - Varchar

#### Companies

* ID
* company
* vendor number
* Company Category
* road
* zip code
* place
* country
* phone
* fax
* website

#### Status Enum

* active
* inactive
* reserved