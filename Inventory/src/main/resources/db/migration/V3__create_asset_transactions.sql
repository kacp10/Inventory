CREATE TABLE asset_transactions (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  asset_id          BIGINT NOT NULL,
  action            VARCHAR(20) NOT NULL,
  performed_by      VARCHAR(100),
  notes             TEXT,
  transaction_date  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (asset_id) REFERENCES assets(id)
);
