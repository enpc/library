CREATE DATABASE library;
CREATE USER usr;
ALTER USER usr WITH PASSWORD 'usr';
GRANT ALL PRIVILEGES ON DATABASE library TO usr;