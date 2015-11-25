# CREATE TABLE oauth_client_details (
#   client_id               VARCHAR(128) NOT NULL PRIMARY KEY,
#   resource_ids            VARCHAR(256)  DEFAULT NULL,
#   client_secret           VARCHAR(256)  DEFAULT NULL,
#   scope                   VARCHAR(256)  DEFAULT NULL,
#   authorized_grant_types  VARCHAR(256)  DEFAULT NULL,
#   web_server_redirect_uri VARCHAR(256)  DEFAULT NULL,
#   authorities             VARCHAR(256)  DEFAULT NULL,
#   access_token_validity   INT(11)       DEFAULT NULL,
#   refresh_token_validity  INT(11)       DEFAULT NULL,
#   additional_information  VARCHAR(4096) DEFAULT NULL,
#   autoapprove             VARCHAR(4096) DEFAULT NULL
# );


CREATE TABLE oauth_client_details (
  client_id               VARCHAR(256) PRIMARY KEY,
  resource_ids            VARCHAR(256),
  client_secret           VARCHAR(256),
  scope                   VARCHAR(256),
  authorized_grant_types  VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities             VARCHAR(256),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(256)
);

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES
  ('rest_id', 'springsec', 'rest_key', 'trust,read,write', 'client_credentials,password,refresh_token', 'ROLE_USER',
   '1800', '45000');

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES ('ios_id', 'springsec', 'ios_key', 'trust,read,write', 'client_credentials,password,refresh_token', 'ROLE_USER',
        '1800', '45000');

CREATE TABLE oauth_access_token (
  token_id          VARCHAR(256) DEFAULT NULL,
  token             BLOB,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name         VARCHAR(256) DEFAULT NULL,
  client_id         VARCHAR(256) DEFAULT NULL,
  authentication    BLOB,
  refresh_token     VARCHAR(256) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE TABLE oauth_refresh_token (
  token_id       VARCHAR(256) DEFAULT NULL,
  token          BLOB,
  authentication BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;