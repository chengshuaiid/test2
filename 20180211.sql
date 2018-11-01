drop table if exists bbi_scnTempletInfo;

/*==============================================================*/
/* Table: bbi_scnTempletInfo                                    */
/*==============================================================*/
create table bbi_scnTempletInfo
(
   scnId                varchar(36) not null,
   scnName              varchar(50),
   scnKeywords          varchar(100),
   scnContent           varchar(300),
   isForbidden          decimal(1,0) default 0,
   isDel                decimal(1,0) default 0,
   creator              varchar(36),
   createtime           datetime,
   modifier             varchar(36),
   modifytime           datetime,
   primary key (scnId)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
