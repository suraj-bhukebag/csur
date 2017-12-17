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
    
    SET targetStation = 6;
    SET counter = 1;
    SET trainIdSB = 1;
    SET trainIdNB = 62;
    
    SET c1 = time_format('06:00:00', '%h:%i:%s');
    SET arv = time_format('06:00:00', '%h:%i:%s');
    
	CREATE TEMPORARY TABLE IF NOT EXISTS expressTrainsSB AS (SELECT id,trainnumber FROM train WHERE Type = 'E' AND id <= 61);
    CREATE TEMPORARY TABLE IF NOT EXISTS expressTrainsNB AS (SELECT id,trainnumber FROM train WHERE Type = 'E' AND id > 61);
    
    -- Delete previous records of express train for SB trains.
    
    DELETE FROM trainschedule
    WHERE trainid in (SELECT id FROM train WHERE Type = 'E' AND id <= 61);
    
    -- Delete previous records of express train for NB trains.
    
    DELETE FROM trainschedule
    WHERE trainid in (SELECT id FROM train WHERE Type = 'E' AND id > 61);
    
    SET prev_time = time_format('05:00:00', '%h:%i:%s');
    
    WHILE trainIdSB <= 61 DO
    
    -- Increment the time by 15 mins for next train.
        SET st = (SELECT addtime(prev_time, '01:00:00'));
		SET arv = st;
        SET dep = st;
        SET c1 = st;
        
        -- Counter for 26 train stations and calculating their time.
        SET counter = 1;
        
        INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime) VALUES(trainIdSB, counter, arv, arv);
    
		SET counter = counter + 5;
        
        WHILE counter <= 26 DO
            
					SET arv = (SELECT addtime(c1, '00:37:00'));
					SET dep = (SELECT addtime(arv, '00:03:00'));
			
					INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime) VALUES(trainIdSB, counter, arv, dep);
				
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
        
        -- Counter for 26 train stations and calculating their time.
        SET counter = 26;
        
        INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime) VALUES(trainIdNB, counter, arv, arv);
    
		SET counter = counter - 5;
        
			WHILE counter >= 1 DO
            
					SET arv = (SELECT addtime(c1, '00:37:00'));
					SET dep = (SELECT addtime(arv, '00:03:00'));
			
					INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime) VALUES(trainIdNB, counter, arv, dep);
				
					SET counter = counter - 5;
                    
                    SET c1 = dep;
			
			END WHILE;
            
            SET trainIdNB = trainIdNB + 4;
            
            -- Setting the previous time of train just processed.
			SET prev_time = st;
    
    
    END WHILE;
    
    
    DROP TABLE expressTrainsSB;
    DROP TABLE expressTrainsNB;
    
    CREATE TEMPORARY TABLE IF NOT EXISTS trainscheduleBkp_b4_repopulations AS (SELECT *  FROM trainschedule ORDER BY 2,3);
    
    TRUNCATE TABLE trainschedule;
    
    INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime)
	SELECT trainId,stationId,arrivalTime,departureTime
	FROM trainscheduleBkp_b4_repopulations;
    
    -- DROP TABLE trainscheduleBkp_b4_repopulations;
    
END