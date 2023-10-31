CREATE OR REPLACE PROCEDURE take_random_pizza_order(IN id_customer VARCHAR(15), IN method CHAR(1), OUT order_taken BOOLEAN)
AS $$
DECLARE
    id_random_pizza INT;
    price_random_pizza DECIMAL(5,2);
    price_with_discount DECIMAL(5,2);
    with_errors BOOLEAN DEFAULT FALSE;
    last_order_id INT;
BEGIN
    BEGIN
        SELECT id_pizza, price
        INTO id_random_pizza, price_random_pizza
        FROM pizza
        WHERE available = 1
        ORDER BY RANDOM()
        LIMIT 1;
        
        price_with_discount := price_random_pizza - (price_random_pizza * 0.20);
        
        BEGIN
            -- Realizar los inserts dentro del bloque BEGIN...END
            BEGIN
                -- Insertar en la tabla pizza_order
                INSERT INTO pizza_order (id_customer, date, total, method, additional_notes)
                VALUES (id_customer, CURRENT_DATE, price_with_discount, method, '20% OFF PIZZA RANDOM PROMOTION');
                
                -- Obtener el último ID insertado en pizza_order
                last_order_id := currval('pizza_order_id_order_seq');
                
                -- Insertar en la tabla order_item
                INSERT INTO order_item (id_item, id_order, id_pizza, quantity, price)
                VALUES (1, last_order_id, id_random_pizza, 1, price_random_pizza);
            EXCEPTION WHEN OTHERS THEN
                with_errors := TRUE;
                -- Deshacer la transacción en caso de error
                ROLLBACK;
            END;
            
            IF with_errors THEN 
                order_taken := FALSE;
            ELSE 
                order_taken := TRUE;
            END IF;
        END;
    EXCEPTION WHEN OTHERS THEN
        with_errors := TRUE;
    END;
    
    IF with_errors THEN 
        order_taken := FALSE;
    END IF;
END;
$$ LANGUAGE plpgsql;
