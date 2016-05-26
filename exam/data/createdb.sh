psql -d repairshop -a -f db/create_db_subjekt.sql
psql -d repairshop -a -f db/create_db_remonditeenus.sql
psql -d repairshop -a -f db/schema.sql
psql -d repairshop -a -f db/insert_data_subjekt.sql
psql -d repairshop -a -f db/insert_data_remonditeenus.sql
psql -d repairshop -a -f db/testdata.sql