--liquibase formatted sql

--changeset V1_0__create_schema
--context pg-dml
CREATE SCHEMA IF NOT EXISTS systemeio_app;