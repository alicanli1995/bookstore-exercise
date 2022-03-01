
-- For Cassandra
CREATE KEYSPACE crm
WITH replication = {'class':'SimpleStrategy', 'replication_factor': 3};
-- For mongodb

use crmdb

-- For mysql

create database crmdb