--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = walletgw, pg_catalog;

SET default_tablespace = '';

CREATE TABLE merchant_profile (
  id BIGSERIAL,
  created_time TIMESTAMP WITHOUT TIME ZONE,
  creator_id BIGINT,
  db_active CHAR(1),
  last_updated_time TIMESTAMP WITHOUT TIME ZONE,
  last_updated_id BIGINT,
  access_key VARCHAR(255),
  description VARCHAR(255),
  merchant_code VARCHAR(255),
  name VARCHAR(255),
  secret_key VARCHAR(255),
  status VARCHAR(255),
  CONSTRAINT merchant_profile_pkey PRIMARY KEY(id)
) 
WITH (oids = false);

CREATE TABLE sys_seq (
  id_seq BIGSERIAL,
  created_time TIMESTAMP WITHOUT TIME ZONE,
  creator_id BIGINT,
  db_active CHAR(1),
  last_updated_time TIMESTAMP WITHOUT TIME ZONE,
  last_updated_id BIGINT,
  str_name VARCHAR(255) NOT NULL,
  str_value BIGINT NOT NULL,
  CONSTRAINT sys_seq_pkey PRIMARY KEY(id_seq),
  CONSTRAINT uk_9efqermii1cv5kor66i9f77ug UNIQUE(str_name)
) 
WITH (oids = false);


CREATE TABLE transactions (
  id BIGSERIAL,
  created_time TIMESTAMP WITHOUT TIME ZONE,
  creator_id BIGINT,
  db_active CHAR(1),
  last_updated_time TIMESTAMP WITHOUT TIME ZONE,
  last_updated_id BIGINT,
  agent_name VARCHAR(255),
  amount NUMERIC(15,2),
  description VARCHAR(255),
  merchant VARCHAR(255),
  merchant_order_id VARCHAR(255),
  provider_order_id VARCHAR(255),
  request_id VARCHAR(255),
  request_time TIMESTAMP WITHOUT TIME ZONE,
  respcode VARCHAR(255),
  service_id VARCHAR(50),
  status VARCHAR(255),
  type VARCHAR(255),
  walletgw_request_id VARCHAR(255),
  wallet_id VARCHAR(255),
  wallet_order_id VARCHAR(255),
  CONSTRAINT transactions_pkey PRIMARY KEY(id)
) 
WITH (oids = false);

CREATE SEQUENCE hibernate_sequence
  INCREMENT 1 MINVALUE 1
  MAXVALUE 9223372036854775807 START 1
  CACHE 1;

ALTER SEQUENCE walletgw.hibernate_sequence RESTART WITH 17;