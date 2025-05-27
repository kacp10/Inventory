-- V1__create_schema.sql

CREATE TABLE asset_types (
  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE assets (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  asset_tag   VARCHAR(50) NOT NULL UNIQUE,
  hostname    VARCHAR(100),
  type_id     BIGINT NOT NULL,
  description TEXT,
  status      VARCHAR(20) NOT NULL,
  location    VARCHAR(100),
  created_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (type_id) REFERENCES asset_types(id)
);

CREATE TABLE asset_transactions (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  asset_id     BIGINT NOT NULL,
  action       VARCHAR(20) NOT NULL,
  performed_by VARCHAR(100),
  notes        TEXT,
  created_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (asset_id) REFERENCES assets(id)
);
