USE eventuate;

DROP table IF EXISTS events;
DROP table IF EXISTS  entities;
DROP table IF EXISTS  snapshots;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS received_messages;

DROP table IF EXISTS management;
DROP table IF EXISTS account_transaction;

create table events (
  event_id varchar(100) PRIMARY KEY,
  event_type varchar(200),
  event_data varchar(1000) NOT NULL,
  entity_type VARCHAR(200) NOT NULL,
  entity_id VARCHAR(100) NOT NULL,
  triggering_event VARCHAR(1000),
  metadata VARCHAR(1000),
  published TINYINT DEFAULT 0
);

CREATE INDEX events_idx ON events(entity_type, entity_id, event_id);
CREATE INDEX events_published_idx ON events(published, event_id);

create table entities (
  entity_type VARCHAR(200),
  entity_id VARCHAR(100),
  entity_version VARCHAR(50) NOT NULL,
  PRIMARY KEY(entity_type, entity_id)
);

CREATE INDEX entities_idx ON events(entity_type, entity_id);

create table snapshots (
  entity_type VARCHAR(200),
  entity_id VARCHAR(100),
  entity_version VARCHAR(50),
  snapshot_type VARCHAR(1000) NOT NULL,
  snapshot_json VARCHAR(1000) NOT NULL,
  triggering_events VARCHAR(1000),
  PRIMARY KEY(entity_type, entity_id, entity_version)
);

CREATE TABLE message (
  id VARCHAR(1000) PRIMARY KEY,
  destination VARCHAR(1000) NOT NULL,
  headers VARCHAR(1000) NOT NULL,
  payload VARCHAR(1000) NOT NULL,
  published SMALLINT DEFAULT 0
);

CREATE TABLE received_messages (
  consumer_id VARCHAR(1000),
  message_id VARCHAR(1000),
  PRIMARY KEY(consumer_id, message_id)
);



create table account_transaction (
  aggregateId varchar(100),
  operation varchar(200),
  amount varchar(200)
);

create table customer_table (
  aggregateId varchar(100) PRIMARY KEY,
   phone varchar(200)
);

create table management_table (
  aggregateId varchar(100) PRIMARY KEY,
  account_status varchar(200)
);


