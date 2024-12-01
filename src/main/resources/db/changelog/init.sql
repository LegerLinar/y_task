--liquibase formatted sql

--changeset besedin:create_uuid_extension
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

--changeset besedin:create_process_step_type
CREATE TYPE process_step AS ENUM (
    'REGISTRATION',
    'COMPONENT_INSTALLATION',
    'QUALITY_CONTROL',
    'REPAIR',
    'PACKAGING'
);

CREATE TYPE process_status AS ENUM (
       'IN_WORK',
       'READY',
       'QUALITY_CONTROL_FAILED',
       'QUALITY_CONTROL_SUCCESSFUL'
)

--changeset besedin:create_pcb_table
CREATE TABLE pcb
(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(255) NOT NULL,
    current_step process_step NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_update TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    step_status process_status DEFAULT 'IN_WORK' NOT NULL
);

--changeset besedin:create_update_last_update_function
CREATE OR REPLACE FUNCTION update_last_update_column()
RETURNS TRIGGER AS $$
BEGIN NEW.last_update = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--changeset besedin:create_set_last_update_trigger
CREATE TRIGGER set_last_update
    BEFORE INSERT OR UPDATE ON pcb
    FOR EACH ROW
EXECUTE FUNCTION update_last_update_column();

--changeset besedin:create_pcb_log_table
CREATE TABLE pcb_log
(
    id BIGSERIAL PRIMARY KEY,
    pcb_id UUID NOT NULL REFERENCES pcb(id),
    step process_step NOT NULL,
    step_status process_status DEFAULT 'IN_WORK' NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);


--changeset besedin:create_pcb_log_changes_function
CREATE OR REPLACE FUNCTION log_pcb_changes()
    RETURNS TRIGGER AS $$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
            INSERT INTO pcb_log (pcb_id, step, timestamp)
            VALUES (NEW.id, NEW.current_step, CURRENT_TIMESTAMP);
        ELSIF (TG_OP = 'UPDATE') THEN
            INSERT INTO pcb_log (pcb_id, step, timestamp)
            VALUES (NEW.id, NEW.current_step, CURRENT_TIMESTAMP);
        END IF;
        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

--changeset besedin:create_pcb_log_function_trigger
CREATE TRIGGER pcb_changes_trigger
    AFTER INSERT OR UPDATE ON pcb
    FOR EACH ROW
EXECUTE FUNCTION log_pcb_changes();
