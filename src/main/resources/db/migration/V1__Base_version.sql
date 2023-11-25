create table device
(
    id  binary(16) not null
        primary key,
    current_consumption FLOAT  null,
    maxim_hourly_energy int  null
);

create table measure
(
    id        binary(16)  not null
        primary key,
    timestamp int null,
    value     FLOAT         null,
    device_id binary(16)  null,
    constraint FK487e3tii2n3mbs04r70ncmjs2
        foreign key (device_id) references device (id)
);

