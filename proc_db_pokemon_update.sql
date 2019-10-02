/*
  Procedure inserts a new record in the table if it finds a duplicate it will update the record
*/
create or replace procedure proc_db_pokemon_update
(
IN in_pokemon_name VARCHAR,
IN in_pokemon_type1 VARCHAR,
IN in_pokemon_type2 VARCHAR,
IN in_pokemon_total_stats SMALLINT,
IN in_pokemon_total_hp SMALLINT,
IN in_pokemon_total_attack SMALLINT,
IN in_pokemon_total_defence SMALLINT,
IN in_pokemon_total_sp_atk SMALLINT,
IN in_pokemon_total_sp_def SMALLINT,
IN in_pokemon_speed SMALLINT,
IN in_pokemon_stage TINYINT,
IN in_pokemon_legendary TINYINT
OUT retcode TINYINT
)
BEGIN
    INSERT INTO table_pokemon_stats
    (
      pokemon_name,
      pokemon_type1,
      pokemon_type2,
      pokemon_total_stats,
      pokemon_total_hp,
      pokemon_attack,
      pokemon_defense,
      pokemon_sp_atk,
      pokemon_sp_def,
      pokemon_speed,
      pokemon_stage,
      pokemon_is_legendary
    ) VALUES
    (
      in_pokemon_name,
      in_pokemon_type1,
      in_pokemon_type2,
      in_pokemon_total_stats,
      in_pokemon_total_hp,
      in_pokemon_total_attack,
      in_pokemon_total_defence,
      in_pokemon_total_sp_atk,
      in_pokemon_total_sp_def,
      in_pokemon_speed,
      in_pokemon_stage,
      in_pokemon_legendary
    ) ON DUPLICATE KEY UPDATE
      pokemon_name = in_pokemon_name,
      pokemon_type1 = in_pokemon_type1,
      pokemon_type2 = in_pokemon_type2,
      pokemon_total_stats = in_pokemon_total_stats,
      pokemon_total_hp = in_pokemon_total_hp,
      pokemon_attack = in_pokemon_total_attack,
      pokemon_defense = in_pokemon_total_defence,
      pokemon_sp_atk = in_pokemon_total_sp_atk,
      pokemon_sp_def = in_pokemon_total_sp_def,
      pokemon_speed = in_pokemon_speed,
      pokemon_stage = in_pokemon_stage,
      pokemon_is_legendary = in_pokemon_legendary
END
