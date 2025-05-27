-- V2__seed_data.sql

INSERT INTO asset_types (name) VALUES
  ('COMPUTER'),
  ('MONITOR'),
  ('KEYBOARD'),
  ('MOUSE'),
  ('CABLE'),
  ('PRINTER');

INSERT INTO assets (asset_tag, hostname, type_id, description, status, location)
VALUES
  ('PC-001', 'DESKTOP-001', 1, 'Estación de trabajo Windows 10', 'AVAILABLE', 'Oficina A'),
  ('MON-001','', 2, 'Monitor 24" Dell', 'AVAILABLE', 'Oficina A'),
  ('KEY-001','', 3, 'Teclado mecánico', 'AVAILABLE', 'Oficina B'),
  ('CAB-001','', 5, 'Cable HDMI 2m', 'AVAILABLE', 'Bodega');

INSERT INTO asset_transactions (asset_id, action, performed_by, notes)
VALUES
  (1, 'LOAN', 'juan.perez', 'Prestado para auditoría'),
  (1, 'RETURN', 'juan.perez', 'Regresado sin fallos');
