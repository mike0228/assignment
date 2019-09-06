create table postIn
(
	id int auto_increment,
	title varchar(60),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256),
    primary key (id)
);
