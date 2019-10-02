-- #,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Stage,Legendary
-- Create a custom sequence that increments numbers with a prefix
-- Name is the Primary Key
create table table_pokemon_stats (
  pokemon_id VARCHAR(100) NOT NULL,
  pokemon_name VARCHAR(100) PRIMARY KEY NOT NULL,
  pokemon_type1 VARCHAR(100) NOT NULL,
  pokemon_type2 VARCHAR(100),
  pokemon_total_stats SMALLINT,
  pokemon_total_hp SMALLINT,
  pokemon_attack SMALLINT,
  pokemon_defense SMALLINT,
  pokemon_sp_atk SMALLINT,
  pokemon_sp_def SMALLINT,
  pokemon_speed SMALLINT,
  pokemon_stage TINYINT,
  pokemon_is_legendary BOOLEAN NOT NULL,
  inserted_on TIMESTAMP,
  modified_on TIMESTAMP
);

-- MySQL technique
-- Refer : https://stackoverflow.com/questions/17893988/how-to-make-mysql-table-primary-key-auto-increment-with-some-prefix

CREATE TABLE table_pokemon_seq (
  pokemon_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);


-- DROP DB Protocol
DROP TABLE table_pokemon_stats;
DROP TABLE table_pokemon_seq;
DROP TRIGGER trigger_poketable_insert;
DROP TRIGGER trigger_poketable_update;

-- Trigger for when entry was inserted into a table alongslide a custom ID for each Pokemon
CREATE TRIGGER trigger_poketable_insert
BEFORE INSERT ON table_pokemon_stats
FOR EACH ROW
BEGIN
  INSERT INTO table_pokemon_seq VALUES (NULL);
  SET NEW.pokemon_id = CONCAT('POKE_ID_', LPAD(LAST_INSERT_ID(), 3, '0'));
  SET NEW.inserted_on = NOW();
END

-- Trigger for any time record is updated the current time will be added to the updated_on column
CREATE TRIGGER trigger_poketable_update
BEFORE UPDATE ON table_pokemon_stats
FOR EACH ROW
BEGIN
  SET NEW.modified_on = NOW();
END

COMMIT;

-- Oracle Technique
CREATE SEQUENCE sequence_pokemon_id
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE OR REPLACE TRIGGER trigger_poketable
BEFORE INSERT ON table_pokemon_stats
FOR EACH ROW
BEGIN
  SELECT 'pokemon_' || to_char(sequence_pokemon_id.NEXTVAL, '999')
  INTO   :new.userID
  FROM   dual;
END;

-- Test schema
INSERT INTO table_pokemon_stats (pokemon_name, pokemon_type1, pokemon_is_legendary)
VALUES ('Pikachu','Electric', 0);
-- Successful. inserted_on is getting populated and Unique PokeID is generated.


UPDATE table_pokemon_stats SET pokemon_type2 = 'Normal' where pokemon_id = 'POKE_ID_001';
-- Successful. updated_on is getting populated when UPDATE query is called.
