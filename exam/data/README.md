# Changes

* numeric(10, 0) -> bigint, because otherwise hibernate would map all entity IDs to BigDecimal
* device_type super_type_fk from 0 -> null