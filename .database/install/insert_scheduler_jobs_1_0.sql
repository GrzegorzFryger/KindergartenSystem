INSERT INTO scheduler.qrtz_job_details VALUES ('defoultScheduler','K3ctO0MnQaSNT9zxr6Nj5w','tuition','responsible for execute tuition payments','pl.edu.pja.prz.payments.job.TuitionJob',1,0,0,0,_binary '#\r\n#Fri May 15 01:45:36 CEST 2020\r\n');
INSERT INTO scheduler.qrtz_triggers VALUES ('defoultScheduler', 'uhGK2q_jQLaobMr-D0FJEw', 'tuition', 'K3ctO0MnQaSNT9zxr6Nj5w', 'tuition', 'execute tuition payments', 1589580000000, -1, 0, 'WAITING', 'CRON', 1589499936000, 0, NULL, 0, '');
INSERT INTO scheduler.qrtz_cron_triggers VALUES ('defoultScheduler', 'uhGK2q_jQLaobMr-D0FJEw', 'tuition', '* 0 0 ? * * *', 'Europe/Belgrade');
