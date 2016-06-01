## How to set up the database

Install postgres. Replace *username* in the following:

```
sudo su postgres
psql -d template1
CREATE DATABASE repairshop WITH OWNER username ENCODING 'UTF8';
\q
exit
cd exam/data
./createdb.sh
```

Use ```psql -d repairshop``` to use database.

## Changes

* numeric(10, 0) -> bigint, because otherwise hibernate would map all entity IDs to BigDecimal
* device_type super_type_fk from 0 -> null
