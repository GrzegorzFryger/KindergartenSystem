INSERT INTO scheduler.qrtz_job_details
VALUES ('defoultScheduler', '1Mw49Sr-TnWKpJJVXxieBg', 'meals', 'send mail with meals order',
        'pl.edu.pja.prz.meal.job.SendMailWithOrdersJob', 1, 0, 0, 0, _binary '#\r\n#Sun May 17 12:18:24 CEST 2020\r\n'),
       ('defoultScheduler', 'K3ctO0MnQaSNT9zxr6Nj5w', 'tuition', 'responsible for execute tuition payments',
        'pl.edu.pja.prz.payments.job.TuitionJob', 1, 0, 0, 0, _binary '#\r\n#Fri May 15 01:45:36 CEST 2020\r\n');
INSERT INTO scheduler.qrtz_triggers
VALUES ('defoultScheduler', '-dgqt7EUQJWYIoCKHULueQ', 'meals', '1Mw49Sr-TnWKpJJVXxieBg', 'meals',
        'send mail with meals order', 1589752800000, -1, 0, 'WAITING', 'CRON', 1589710704000, 0, NULL, 0, ''),
       ('defoultScheduler', 'uhGK2q_jQLaobMr-D0FJEw', 'tuition', 'K3ctO0MnQaSNT9zxr6Nj5w', 'tuition',
        'execute tuition payments', 1589752800000, 1589707537844, 0, 'WAITING', 'CRON', 1589499936000, 0, NULL, 0, '');
INSERT INTO scheduler.qrtz_cron_triggers
VALUES ('defoultScheduler', '-dgqt7EUQJWYIoCKHULueQ', 'meals', '0/1 0 0 ? * * *', 'Europe/Belgrade'),
       ('defoultScheduler', 'uhGK2q_jQLaobMr-D0FJEw', 'tuition', '* 0 0 ? * * *', 'Europe/Belgrade');
