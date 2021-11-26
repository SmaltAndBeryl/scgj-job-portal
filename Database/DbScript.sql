use cgsc_placement_portal;
CREATE USER 'cpat_db_user'@'localhost' IDENTIFIED BY 'CpAt@786#$$!';
CREATE USER 'cpat_app_user'@'localhost' IDENTIFIED BY 'CpAt@786#$$!';
CREATE USER 'cpat_user'@'localhost' IDENTIFIED BY 'CpAt@786#$$!';


GRANT ALL ON *.* TO 'cpat_db_user'@'localhost';
GRANT SELECT,UPDATE,INSERT,DELETE ON cgsc_placement_portal.* TO 'cpat_app_user'@'localhost';