use mydb;

SET SQL_SAFE_UPDATES = 0;

DELIMITER //


CREATE PROCEDURE sp_populate_express_train_schedule()
BEGIN

	

	DECLARE targetStation int;
    DECLARE counter int;
    DECLARE trainIdSB int;
    DECLARE trainIdNB int;
    DECLARE c1 time;
    DECLARE arv time;
    DECLARE dep time;
    DECLARE st time;
    DECLARE prev_time time;
    
    DECLARE arvTimeInt BigInt;
    DECLARE depTimeInt BigInt;
    
    SET targetStation = 6;
    SET counter = 1;
    SET trainIdSB = 1;
    SET trainIdNB = 62;
    
    SET c1 = time_format('06:00:00', '%h:%i:%s');
    SET arv = time_format('06:00:00', '%h:%i:%s');
    
	CREATE TEMPORARY TABLE IF NOT EXISTS expressTrainsSB AS (SELECT id,trainnumber FROM train WHERE Type = 'E' AND id <= 61);
    CREATE TEMPORARY TABLE IF NOT EXISTS expressTrainsNB AS (SELECT id,trainnumber FROM train WHERE Type = 'E' AND id > 61);
    
    -- Delete previous records of express train for SB trains.
    
    DELETE FROM train_schedule
    WHERE train_id in (SELECT id FROM train WHERE Type = 'E' AND id <= 61);
    
    -- Delete previous records of express train for NB trains.
    
    DELETE FROM train_schedule
    WHERE train_id in (SELECT id FROM train WHERE Type = 'E' AND id > 61);
    
    SET prev_time = time_format('05:00:00', '%h:%i:%s');
    
    WHILE trainIdSB <= 61 DO
    
    -- Increment the time by 15 mins for next train.
        SET st = (SELECT addtime(prev_time, '01:00:00'));
		SET arv = st;
        SET dep = st;
        SET c1 = st;
        
        SET arvTimeInt = (SELECT time_to_sec(arv) * 1000);
        SET depTimeInt = (SELECT time_to_sec(dep) * 1000);
        
        -- Counter for 26 train stations and calculating their time.
        SET counter = 1;
        
        INSERT INTO train_schedule (train_id,station_id,arrivaltime,departuretime,arvtime,deptime) VALUES(trainIdSB, counter, arv, dep, arvTimeInt, depTimeInt);
    
		SET counter = counter + 5;
        
        WHILE counter <= 26 DO
            
					SET arv = (SELECT addtime(c1, '00:25:00'));
					SET dep = (SELECT addtime(arv, '00:03:00'));
                    
                    SET arvTimeInt = (SELECT time_to_sec(arv) * 1000);
					SET depTimeInt = (SELECT time_to_sec(dep) * 1000);
			
					INSERT INTO train_schedule (train_id,station_id,arrivaltime,departuretime,arvtime,deptime) VALUES(trainIdSB, counter, arv, dep, arvTimeInt, depTimeInt);
				
					SET counter = counter + 5;
                    
                    SET c1 = dep;
			
			END WHILE;
            
            SET trainIdSB = trainIdSB + 4;
            
            -- Setting the previous time of train just processed.
			SET prev_time = st;
    
    END WHILE;
    
    -- POPULATE NorthBound Trains
    
    SET prev_time = time_format('05:00:00', '%h:%i:%s');
    
    WHILE trainIdNB <= 122 DO
    
		-- Increment the time by 15 mins for next train.
        SET st = (SELECT addtime(prev_time, '01:00:00'));
		SET arv = st;
        SET dep = st;
        SET c1 = st;
        
        SET arvTimeInt = (SELECT time_to_sec(arv) * 1000);
        SET depTimeInt = (SELECT time_to_sec(dep) * 1000);
        
        -- Counter for 26 train stations and calculating their time.
        SET counter = 26;
        
        INSERT INTO train_schedule (train_id,station_id,arrivaltime,departuretime,arvtime,deptime) VALUES(trainIdNB, counter, arv, dep, arvTimeInt, depTimeInt);
    
		SET counter = counter - 5;
        
			WHILE counter >= 1 DO
            
					SET arv = (SELECT addtime(c1, '00:25:00'));
					SET dep = (SELECT addtime(arv, '00:03:00'));
                    
                    SET arvTimeInt = (SELECT time_to_sec(arv) * 1000);
					SET depTimeInt = (SELECT time_to_sec(dep) * 1000);
			
					INSERT INTO train_schedule (train_id,station_id,arrivaltime,departuretime,arvtime,deptime) VALUES(trainIdNB, counter, arv, dep, arvTimeInt, depTimeInt);
				
					SET counter = counter - 5;
                    
                    SET c1 = dep;
			
			END WHILE;
            
            SET trainIdNB = trainIdNB + 4;
            
            -- Setting the previous time of train just processed.
			SET prev_time = st;
    
    
    END WHILE;
    
    
    DROP TABLE expressTrainsSB;
    DROP TABLE expressTrainsNB;
    
    CREATE TEMPORARY TABLE IF NOT EXISTS train_scheduleBkp_b4_repopulations AS (SELECT *  FROM train_schedule ORDER BY train_id,station_id);
    
    TRUNCATE TABLE train_schedule;
    
    INSERT INTO train_schedule (train_id,station_id,arrivaltime,departuretime,arvtime,deptime)
	SELECT train_id,station_id,arrivaltime,departuretime,arvtime,deptime
	FROM train_scheduleBkp_b4_repopulations;
    
    DROP TABLE train_scheduleBkp_b4_repopulations;
    
END