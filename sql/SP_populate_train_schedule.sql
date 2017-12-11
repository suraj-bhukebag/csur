
use mydb;
DELIMITER //

CREATE PROCEDURE sp_populate_train_schedule()
BEGIN

	DECLARE counter INT;
    DECLARE c1 time;
    DECLARE arv time;
    DECLARE dep time;
    DECLARE st time;
    DECLARE prev_time time;
    
    DECLARE train_counter INT;

    SET c1 = time_format('06:00:00', '%h:%i:%s');
    
	
    
    SET arv = c1;
    SET dep = c1;
    SET st = c1;
    
    SET train_counter = 1;
    
    SET prev_time = time_format('05:45:00', '%h:%i:%s');
    
    WHILE train_counter <= 61 DO
    
		-- Increment the time by 15 mins for next train.
        SET st = (SELECT addtime(prev_time, '00:15:00'));
		SET arv = st;
        SET dep = st;
        SET c1 = st;
        
        -- Counter for 26 train stations and calculating their time.
        SET counter = 1;
        
        -- First record for the train starting at SOURCE station A/Z.
        INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime) VALUES(train_counter, counter, arv, dep);
        
        SET counter = counter + 1;

		WHILE counter <= 26 DO
			SET arv = (SELECT addtime(c1, '00:05:00'));
			SET dep = (SELECT addtime(arv, '00:03:00'));
            
            INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime) VALUES(train_counter, counter, arv, dep);
			
			SET c1 = dep;
			
			SET counter = counter + 1;
			
		END WHILE;
        
        SET train_counter = train_counter + 1;
        
        -- Setting the previous time of train just processed.
        SET prev_time = st;
        
	END WHILE;
   
   SET prev_time = time_format('05:45:00', '%h:%i:%s');
    
    WHILE train_counter <= 122 DO
    
		-- Increment the time by 15 mins for next train.
        SET st = (SELECT addtime(prev_time, '00:15:00'));
		SET arv = st;
        SET dep = st;
        SET c1 = st;
        
        -- Counter for 26 train stations and calculating their time.
        SET counter = 1;
        
        -- First record for the train starting at SOURCE station A/Z.
        INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime) VALUES(train_counter, counter, arv, dep);
        
        SET counter = counter + 1;

		WHILE counter <= 26 DO
			SET arv = (SELECT addtime(c1, '00:05:00'));
			SET dep = (SELECT addtime(arv, '00:03:00'));
            
            INSERT INTO trainschedule (trainId,stationId,arrivalTime,departureTime) VALUES(train_counter, counter, arv, dep);
			
			SET c1 = dep;
			
			SET counter = counter + 1;
			
		END WHILE;
        
        SET train_counter = train_counter + 1;
        
        -- Setting the previous time of train just processed.
        SET prev_time = st;
        
	END WHILE;
	
END //

