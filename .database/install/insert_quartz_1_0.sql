DROP TABLE IF EXISTS scheduler.qrtz_FIRED_TRIGGERS;
DROP TABLE IF EXISTS scheduler.qrtz_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS scheduler.qrtz_SCHEDULER_STATE;
DROP TABLE IF EXISTS scheduler.qrtz_LOCKS;
DROP TABLE IF EXISTS scheduler.qrtz_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS scheduler.qrtz_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS scheduler.qrtz_CRON_TRIGGERS;
DROP TABLE IF EXISTS scheduler.qrtz_BLOB_TRIGGERS;
DROP TABLE IF EXISTS scheduler.qrtz_TRIGGERS;
DROP TABLE IF EXISTS scheduler.qrtz_JOB_DETAILS;
DROP TABLE IF EXISTS scheduler.qrtz_CALENDARS;

CREATE TABLE scheduler.qrtz_JOB_DETAILS(
sched_name VARCHAR(120) NOT NULL,
job_name VARCHAR(200) NOT NULL,
job_group VARCHAR(200) NOT NULL,
description VARCHAR(250) NULL,
job_class_name VARCHAR(250) NOT NULL,
is_durable BOOLEAN NOT NULL,
is_nonconcurrent BOOLEAN NOT NULL,
is_update_data BOOLEAN NOT NULL,
requests_recovery BOOLEAN NOT NULL,
job_data BLOB NULL,
PRIMARY KEY (sched_name,job_name,job_group))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_TRIGGERS (
sched_name VARCHAR(120) NOT NULL,
trigger_name VARCHAR(200) NOT NULL,
trigger_group VARCHAR(200) NOT NULL,
job_name VARCHAR(200) NOT NULL,
job_group VARCHAR(200) NOT NULL,
description VARCHAR(250) NULL,
next_fire_time BIGINT(19) NULL,
prev_fire_time BIGINT(19) NULL,
priority INTEGER NULL,
trigger_state VARCHAR(16) NOT NULL,
trigger_type VARCHAR(8) NOT NULL,
start_time BIGINT(19) NOT NULL,
end_time BIGINT(19) NULL,
calendar_name VARCHAR(200) NULL,
misfire_instr SMALLINT(2) NULL,
job_data BLOB NULL,
PRIMARY KEY (sched_name,trigger_name,trigger_group),
foreign key (sched_name,job_name,job_group)
REFERENCES qrtz_JOB_DETAILS(sched_name,job_name,job_group))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_SIMPLE_TRIGGERS (
sched_name VARCHAR(120) NOT NULL,
trigger_name VARCHAR(200) NOT NULL,
trigger_group VARCHAR(200) NOT NULL,
repeat_count BIGINT(7) NOT NULL,
repeat_interval BIGINT(12) NOT NULL,
times_triggered BIGINT(10) NOT NULL,
PRIMARY KEY (sched_name,trigger_name,trigger_group),
FOREIGN KEY (sched_name,trigger_name,trigger_group)
REFERENCES qrtz_TRIGGERS(sched_name,trigger_name,trigger_group))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_CRON_TRIGGERS (
sched_name VARCHAR(120) NOT NULL,
trigger_name VARCHAR(200) NOT NULL,
trigger_group VARCHAR(200) NOT NULL,
cron_expression VARCHAR(120) NOT NULL,
time_zone_id VARCHAR(80),
PRIMARY KEY (sched_name,trigger_name,trigger_group),
FOREIGN KEY (sched_name,trigger_name,trigger_group)
REFERENCES qrtz_TRIGGERS(sched_name,trigger_name,trigger_group))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_SIMPROP_TRIGGERS (
sched_name VARCHAR(120) NOT NULL,
trigger_name VARCHAR(200) NOT NULL,
trigger_group VARCHAR(200) NOT NULL,
str_prop_1 VARCHAR(512) NULL,
str_prop_2 VARCHAR(512) NULL,
str_prop_3 VARCHAR(512) NULL,
int_prop_1 INT NULL,
int_prop_2 INT NULL,
long_prop_1 BIGINT NULL,
long_prop_2 BIGINT NULL,
dec_prop_1 numeric(13,4) NULL,
dec_prop_2 numeric(13,4) NULL,
bool_prop_1 BOOLEAN NULL,
bool_prop_2 BOOLEAN NULL,
time_zone_id VARCHAR(80) null,
PRIMARY KEY (sched_name,trigger_name,trigger_group),
FOREIGN KEY (sched_name,trigger_name,trigger_group)
REFERENCES qrtz_TRIGGERS(sched_name,trigger_name,trigger_group))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_BLOB_TRIGGERS (
sched_name VARCHAR(120) NOT NULL,
trigger_name VARCHAR(200) NOT NULL,
trigger_group VARCHAR(200) NOT NULL,
blob_data BLOB null,
PRIMARY KEY (sched_name,trigger_name,trigger_group),
index (sched_name,trigger_name, trigger_group),
FOREIGN KEY (sched_name,trigger_name,trigger_group)
REFERENCES qrtz_TRIGGERS(sched_name,trigger_name,trigger_group))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_CALENDARS (
sched_name VARCHAR(120) NOT NULL,
calendar_name VARCHAR(200) NOT NULL,
calendar BLOB NOT NULL,
PRIMARY KEY (sched_name,calendar_name))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_PAUSED_TRIGGER_GRPS (
sched_name VARCHAR(120) NOT NULL,
trigger_group VARCHAR(200) NOT NULL,
PRIMARY KEY (sched_name,trigger_group))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_FIRED_TRIGGERS (
sched_name VARCHAR(120) NOT NULL,
entry_id VARCHAR(140) NOT NULL,
trigger_name VARCHAR(200) NOT NULL,
trigger_group VARCHAR(200) NOT NULL,
instance_name VARCHAR(200) NOT NULL,
fired_time BIGINT(19) NOT NULL,
sched_time BIGINT(19) NOT NULL,
priority integer NOT NULL,
state VARCHAR(16) NOT NULL,
job_name VARCHAR(200) NULL,
job_group VARCHAR(200) NULL,
is_nonconcurrent BOOLEAN NULL,
requests_recovery BOOLEAN NULL,
PRIMARY KEY (sched_name,entry_id))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_SCHEDULER_STATE (
sched_name VARCHAR(120) NOT NULL,
instance_name VARCHAR(200) NOT NULL,
last_checkin_time BIGINT(19) NOT NULL,
checkin_interval BIGINT(19) NOT NULL,
PRIMARY KEY (sched_name,instance_name))
engine=InnoDB;

CREATE TABLE scheduler.qrtz_LOCKS (
sched_name VARCHAR(120) NOT NULL,
lock_name VARCHAR(40) NOT NULL,
PRIMARY KEY (sched_name,lock_name))
engine=InnoDB;

CREATE INDEX idx_qrtz_j_req_recovery ON scheduler.qrtz_JOB_DETAILS(sched_name,requests_recovery);
CREATE INDEX idx_qrtz_j_grp ON scheduler.qrtz_JOB_DETAILS(sched_name,job_group);

CREATE INDEX idx_qrtz_t_j ON scheduler.qrtz_TRIGGERS(sched_name,job_name,job_group);
CREATE INDEX idx_qrtz_t_jg ON scheduler.qrtz_TRIGGERS(sched_name,job_group);
CREATE INDEX idx_qrtz_t_c ON scheduler.qrtz_TRIGGERS(sched_name,calendar_name);
CREATE INDEX idx_qrtz_t_g ON scheduler.qrtz_TRIGGERS(sched_name,trigger_group);
CREATE INDEX idx_qrtz_t_state ON scheduler.qrtz_TRIGGERS(sched_name,trigger_state);
CREATE INDEX idx_qrtz_t_n_state ON scheduler.qrtz_TRIGGERS(sched_name,trigger_name,trigger_group,trigger_state);
CREATE INDEX idx_qrtz_t_n_g_state ON scheduler.qrtz_TRIGGERS(sched_name,trigger_group,trigger_state);
CREATE INDEX idx_qrtz_t_next_fire_time ON scheduler.qrtz_TRIGGERS(sched_name,next_fire_time);
CREATE INDEX idx_qrtz_t_nft_st ON scheduler.qrtz_TRIGGERS(sched_name,trigger_state,next_fire_time);
CREATE INDEX idx_qrtz_t_nft_misfire ON scheduler.qrtz_TRIGGERS(sched_name,misfire_instr,next_fire_time);
CREATE INDEX idx_qrtz_t_nft_st_misfire ON scheduler.qrtz_TRIGGERS(sched_name,misfire_instr,next_fire_time,trigger_state);
CREATE INDEX idx_qrtz_t_nft_st_misfire_grp ON scheduler.qrtz_TRIGGERS(sched_name,misfire_instr,next_fire_time,trigger_group,trigger_state);

CREATE INDEX idx_qrtz_ft_trig_inst_name ON scheduler.qrtz_FIRED_TRIGGERS(sched_name,instance_name);
CREATE INDEX idx_qrtz_ft_inst_job_req_rcvry ON scheduler.qrtz_FIRED_TRIGGERS(sched_name,instance_name,requests_recovery);
CREATE INDEX idx_qrtz_ft_j_g ON scheduler.qrtz_FIRED_TRIGGERS(sched_name,job_name,job_group);
CREATE INDEX idx_qrtz_ft_jg ON scheduler.qrtz_FIRED_TRIGGERS(sched_name,job_group);
CREATE INDEX idx_qrtz_ft_t_g ON scheduler.qrtz_FIRED_TRIGGERS(sched_name,trigger_name,trigger_group);
CREATE INDEX idx_qrtz_ft_tg ON scheduler.qrtz_FIRED_TRIGGERS(sched_name,trigger_group);

COMMIT;
