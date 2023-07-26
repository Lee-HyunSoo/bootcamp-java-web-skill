create table t_member (
    id varchar2(10) NOT NULL,
    pwd varchar2(10) NOT NULL,
    name varchar2(50),
    email varchar2(50) NOT NULL,
    joinDate date default sysdate NOT NULL,
    PRIMARY KEY(id)
    )

insert into t_member(id, pwd, name, email) values ('id1', '1234', 'name1', 'www.id1.com');
insert into t_member(id, pwd, name, email) values ('id2', '1234', 'name2', 'www.id2.com');
insert into t_member(id, pwd, name, email) values ('id3', '1234', 'name3', 'www.id3.com');

commit;

select * from t_member;
select count(*) from t_member;