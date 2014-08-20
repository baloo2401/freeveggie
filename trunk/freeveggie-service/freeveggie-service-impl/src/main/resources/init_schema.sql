CREATE TABLE "TJ_REAP_CALENDAR" (
    rpc_id integer NOT NULL,
    rmn_id integer NOT NULL
);

CREATE TABLE "TJ_SEED_CALENDAR" (
    rpc_id integer NOT NULL,
    rmn_id integer NOT NULL
);

CREATE TABLE "T_ADDRESS" (
    adr_id bigint NOT NULL, 
    adr_name character varying(32) NOT NULL,
    adr_street_number character varying(32) NOT NULL, 
    adr_street_name character varying(128) NOT NULL,
    adr_rci_id integer,
    adr_latitude double precision NOT NULL, 
    adr_longitude double precision NOT NULL, 
    adr_locality character varying(128) NOT NULL, 
    adr_adm_area_l2 character varying(128) NOT NULL,
    adr_adm_area_l1 character varying(128) NOT NULL, 
    adr_country character varying(128) NOT NULL, 
    adr_postal_code character varying(128) NOT NULL
);

CREATE TABLE "T_COMMENT_GARDEN" (
    cgr_id bigint NOT NULL,
    cgr_comment character varying(512) NOT NULL,
    cgr_creation_date timestamp NOT NULL,
    cgr_res_id integer NOT NULL,
    cgr_grd_id bigint NOT NULL,
    cgr_usr_id bigint NOT NULL,
    cgr_ren_id integer NOT NULL
);

--Add constraint on cpr_usr_id, cpr_prd_id unicity
CREATE TABLE "T_COMMENT_PRODUCT" (
    cpr_id bigint NOT NULL,
    cpr_comment character varying(512) NOT NULL,
    cpr_creation_date timestamp NOT NULL,
    cpr_res_id integer NOT NULL,
    cpr_prd_id bigint NOT NULL,
    cpr_usr_id bigint NOT NULL,
    cpr_ren_id integer NOT NULL
);

CREATE TABLE "T_GARDEN" (
    grd_id bigint NOT NULL,
    grd_creation_date timestamp NOT NULL,
    grd_description character varying(512),
    grd_name character varying(32) NOT NULL,
    grd_img_filename character varying(128),
    grd_usr_id bigint NOT NULL,
    grd_sts_id integer NOT NULL,
    grd_adr_id bigint NOT NULL
);

CREATE TABLE "T_LIKE_GARDEN" (
    lgr_id bigint NOT NULL,
    lgr_creation_date timestamp NOT NULL,
    lgr_res_id integer NOT NULL,
    lgr_grd_id bigint NOT NULL,
    lgr_usr_id bigint NOT NULL
);

CREATE TABLE "T_LIKE_PRODUCT" (
    lpr_id bigint NOT NULL,
    lpr_creation_date timestamp NOT NULL,
    lpr_res_id integer NOT NULL,
    lpr_prd_id bigint NOT NULL,
    lpr_usr_id bigint NOT NULL
);

CREATE TABLE "T_PRODUCT" (
    prd_id bigint NOT NULL,
    prd_name character varying(128),
    prd_rcm_id integer NOT NULL,
    prd_rct_id integer NOT NULL,
    prd_description character varying(512),
    prd_chg_id integer NOT NULL,
    prd_img_filename character varying(128),
    prd_quantity double precision NOT NULL,
    prd_grd_id bigint NOT NULL,
    prd_sts_id integer NOT NULL,
    prd_rpc_id integer NOT NULL
);

CREATE TABLE "T_PROFIL" (
    prf_id bigint NOT NULL,
    prf_experience character varying(512),
    prf_interest character varying(512),
    prf_other character varying(512),
    prf_participation character varying(512),
    prf_description character varying(512),
    prf_philosophy character varying(512),
    prf_meals character varying(512),
    prf_img_filename character varying(128),
    prf_reason character varying(512)
);

CREATE TABLE "T_REF_CITY" (
    rci_id integer NOT NULL,
    rci_name character varying(64) NOT NULL,
    rci_zip_code integer NOT NULL,
    rci_rre_id  integer NOT NULL
);

CREATE TABLE "T_REF_REGION" (
    rre_id integer NOT NULL,
    rre_name character varying(64) NOT NULL,
    rre_rst_id integer NOT NULL
);

CREATE TABLE "T_REF_STATE" (
    rst_id integer NOT NULL,
    rst_name character varying(64) NOT NULL,
    rst_rcn_id integer NOT NULL
);

CREATE TABLE "T_REF_COUNTRY" (
    rcn_id integer NOT NULL,
    rcn_code_iso_a2 character(2) NOT NULL,
    rcn_code_iso_a3 character(3) NOT NULL,
    rcn_code_iso_number integer NOT NULL,
    rcn_country_name character varying(64) NOT NULL
);

CREATE TABLE "T_REF_CULTURE_MODE" (
    rcm_id integer NOT NULL,
    rcm_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_CULTURE_TYPE" (
    rct_id integer NOT NULL,
    rct_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_EVALUATION_NOTE" (
    ren_id integer NOT NULL,
    ren_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_STATUS" (
    sts_id integer NOT NULL,
    sts_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_EVALUATION_STATUS" (
    res_id integer NOT NULL,
    res_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_EXCHANGE_TYPE" (
    chg_id integer NOT NULL,
    chg_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_MONTH" (
    rmn_id integer NOT NULL,
    rmn_name character varying(32)
);

CREATE TABLE "T_REF_PRODUCT" (
    rpc_id integer NOT NULL,
    rpc_carbohydrate double precision,
    rpc_lipid double precision,
    rpc_name character varying(32) NOT NULL,
    rpc_img_filename character varying(128),
    rpc_protein double precision,
    rpc_rpt_id integer NOT NULL
);

CREATE TABLE "T_REF_PRODUCT_REQUEST_STATUS" (
    rpr_id integer NOT NULL,
    rpr_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_PRODUCT_TYPE" (
    rpt_id integer NOT NULL,
    rpt_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_RELATIONS_STATUS" (
    rrs_id integer NOT NULL,
    rrs_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_RELATION_TYPE" (
    rrt_id integer NOT NULL,
    rrt_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_USER_ROLE" (
    rur_id integer NOT NULL,
    rur_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_REF_USER_STATUS" (
    rus_id integer NOT NULL,
    rus_libelle character varying(32) NOT NULL
);

CREATE TABLE "T_RELATIONSHIP" (
    rlt_id bigint NOT NULL,
    rlt_answer character varying(512),
    rlt_creation_date timestamp NOT NULL,
    rlt_rrs_id integer NOT NULL,
    rlt_rrt_id integer NOT NULL,
    rlt_usr_id_sender bigint NOT NULL,
    rlt_usr_id_recipient bigint NOT NULL,
    rlt_request character varying(512) NOT NULL
);

CREATE TABLE "T_REQUEST_PRODUCT" (
    rqt_id bigint NOT NULL,
    rqt_prd_id bigint NOT NULL,
    rqt_usr_id bigint NOT NULL,
    rqt_quantity double precision NOT NULL,
    rqt_msg character varying(512) NOT NULL,
    rqt_answer_msg character varying(512),
    rqt_rpr_id integer NOT NULL,
    rqt_creation_date timestamp NOT NULL,
    rqt_answer_date timestamp ,
    rqt_picking_date timestamp NOT NULL
);

CREATE TABLE "T_SUBSCRIPTION" (
    sbr_id bigint NOT NULL,
    sbr_share_personal boolean NOT NULL,
    sbr_share_garden boolean NOT NULL,
    sbr_newsletter boolean NOT NULL,
    sbr_freeveggie_agreement boolean NOT NULL
);

CREATE TABLE "T_USER" (
    usr_id bigint NOT NULL,
    usr_blacklisted_date timestamp ,
    usr_cancellation_date timestamp ,
    usr_creation_date timestamp NOT NULL,
    usr_email_address character varying(128) NOT NULL,
    usr_firstname character varying(64) NOT NULL,
    usr_last_connexion timestamp NOT NULL,
    usr_lastname character varying(64) NOT NULL,
    usr_password character varying(32) NOT NULL,
    usr_rur_id integer NOT NULL,
    usr_rus_id integer NOT NULL,
    usr_temp_password character varying(32),
    usr_username character varying(32) NOT NULL,
    usr_uuid character varying(32) NOT NULL,
    usr_adr_id bigint NOT NULL,
    usr_img_filename character varying(64)
);

CREATE TABLE "T_PICTURE_GARDEN"
(
   grp_id bigint, 
   grp_mine_type bigint  NOT NULL, 
   grp_grd_id bigint, 
   picture LONGVARBINARY NOT NULL,
   grp_creation_date timestamp NOT NULL
) 

CREATE TABLE "T_PICTURE_PRODUCT"
(
   prp_id bigint, 
   prp_mine_type bigint NOT NULL, 
   prp_prd_id bigint, 
   picture LONGVARBINARY NOT NULL,
   prp_creation_date timestamp NOT NULL
) 

CREATE SEQUENCE s_adr_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_cgr_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_cpr_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_grd_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_lgr_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_lpr_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_prd_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_rlt_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_rqt_seq
    START WITH 10000
    INCREMENT BY 1;

CREATE SEQUENCE s_usr_seq
    START WITH 10000
    INCREMENT BY 1;
	
CREATE SEQUENCE s_grp_seq
    START WITH 10000
    INCREMENT BY 1;
	
CREATE SEQUENCE s_prp_seq
    START WITH 10000
    INCREMENT BY 1;

ALTER TABLE "T_ADDRESS"
    ADD CONSTRAINT t_address_pkey PRIMARY KEY (adr_id);

ALTER TABLE "T_COMMENT_GARDEN"
    ADD CONSTRAINT t_comment_garden_pkey PRIMARY KEY (cgr_id);

ALTER TABLE "T_COMMENT_PRODUCT"
    ADD CONSTRAINT t_comment_product_pkey PRIMARY KEY (cpr_id);

ALTER TABLE "T_GARDEN"
    ADD CONSTRAINT t_garden_pkey PRIMARY KEY (grd_id);

ALTER TABLE "T_LIKE_GARDEN"
    ADD CONSTRAINT t_like_garden_pkey PRIMARY KEY (lgr_id);

ALTER TABLE "T_LIKE_PRODUCT"
    ADD CONSTRAINT t_like_product_pkey PRIMARY KEY (lpr_id);

ALTER TABLE "T_PRODUCT"
    ADD CONSTRAINT t_product_pkey PRIMARY KEY (prd_id);

ALTER TABLE "T_PROFIL"
    ADD CONSTRAINT t_profil_pkey PRIMARY KEY (prf_id);

ALTER TABLE "T_REF_CITY"
    ADD CONSTRAINT t_ref_city_pkey PRIMARY KEY (rci_id);

ALTER TABLE "T_REF_REGION"
    ADD CONSTRAINT t_ref_region_pkey PRIMARY KEY (rre_id);

ALTER TABLE "T_REF_STATE"
    ADD CONSTRAINT t_ref_state_pkey PRIMARY KEY (rst_id);

ALTER TABLE "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_pkey PRIMARY KEY (rcn_id);

ALTER TABLE "T_REF_CULTURE_MODE"
    ADD CONSTRAINT t_ref_culture_mode_pkey PRIMARY KEY (rcm_id);

ALTER TABLE "T_REF_CULTURE_TYPE"
    ADD CONSTRAINT t_ref_culture_type_pkey PRIMARY KEY (rct_id);

ALTER TABLE "T_REF_EVALUATION_NOTE"
    ADD CONSTRAINT t_ref_evaluation_note_pkey PRIMARY KEY (ren_id);

ALTER TABLE "T_REF_STATUS"
    ADD CONSTRAINT t_ref_status_pkey PRIMARY KEY (sts_id);

ALTER TABLE "T_REF_EVALUATION_STATUS"
    ADD CONSTRAINT t_ref_evaluation_status_pkey PRIMARY KEY (res_id);

ALTER TABLE "T_REF_EXCHANGE_TYPE"
    ADD CONSTRAINT t_ref_exchange_type_pkey PRIMARY KEY (chg_id);

ALTER TABLE "T_REF_MONTH"
    ADD CONSTRAINT t_ref_month_pkey PRIMARY KEY (rmn_id);

ALTER TABLE "T_REF_PRODUCT"
    ADD CONSTRAINT t_ref_product_pkey01 PRIMARY KEY (rpc_id);

ALTER TABLE "T_REF_PRODUCT_REQUEST_STATUS"
    ADD CONSTRAINT t_ref_product_request_status_pkey PRIMARY KEY (rpr_id);

ALTER TABLE "T_REF_PRODUCT_TYPE"
    ADD CONSTRAINT t_ref_product_type_pkey PRIMARY KEY (rpt_id);

ALTER TABLE "T_REF_RELATIONS_STATUS"
    ADD CONSTRAINT t_ref_relation_status_pkey PRIMARY KEY (rrs_id);

ALTER TABLE "T_REF_RELATION_TYPE"
    ADD CONSTRAINT t_ref_relation_type_pkey PRIMARY KEY (rrt_id);

ALTER TABLE "T_REF_USER_ROLE"
    ADD CONSTRAINT t_ref_user_role_pkey PRIMARY KEY (rur_id);

ALTER TABLE "T_REF_USER_STATUS"
    ADD CONSTRAINT t_ref_user_status_pkey PRIMARY KEY (rus_id);

ALTER TABLE "T_RELATIONSHIP"
    ADD CONSTRAINT t_relationship_pkey PRIMARY KEY (rlt_id);

ALTER TABLE "T_REQUEST_PRODUCT"
    ADD CONSTRAINT t_request_product_pkey PRIMARY KEY (rqt_id);

ALTER TABLE "T_SUBSCRIPTION"
    ADD CONSTRAINT t_subscription_pkey PRIMARY KEY (sbr_id);

ALTER TABLE "T_USER"
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (usr_id);

ALTER TABLE "TJ_REAP_CALENDAR"
    ADD CONSTRAINT tj_reap_calendar_pkey PRIMARY KEY (rpc_id, rmn_id);

ALTER TABLE "TJ_SEED_CALENDAR"
    ADD CONSTRAINT tj_seed_month_pkey PRIMARY KEY (rpc_id, rmn_id);
		
ALTER TABLE "T_PICTURE_GARDEN"
  ADD CONSTRAINT t_garden_picuture_pkey PRIMARY KEY (grp_id); 
  
ALTER TABLE "T_PICTURE_PRODUCT"
  ADD CONSTRAINT t_product_picuture_pkey PRIMARY KEY (prp_id); 

ALTER TABLE "T_RELATIONSHIP"
    ADD CONSTRAINT unq_t_relationship_user_id_one_user_id_two UNIQUE (rlt_usr_id_sender, rlt_usr_id_recipient);

ALTER TABLE "T_USER"
    ADD CONSTRAINT unq_t_user_username UNIQUE (usr_username);

ALTER TABLE "T_ADDRESS"
    ADD CONSTRAINT fk_t_address_rci_d FOREIGN KEY (adr_rci_id) REFERENCES "T_REF_CITY"(rci_id);

ALTER TABLE "T_REF_CITY"
    ADD CONSTRAINT fk_t_city_rre_d FOREIGN KEY (rci_rre_id) REFERENCES "T_REF_REGION"(rre_id);

ALTER TABLE "T_REF_REGION"
    ADD CONSTRAINT fk_t_region_rci_d FOREIGN KEY (rre_rst_id) REFERENCES "T_REF_STATE"(rst_id);

ALTER TABLE "T_REF_STATE"
    ADD CONSTRAINT fk_t_state_rci_d FOREIGN KEY (rst_rcn_id) REFERENCES "T_REF_COUNTRY"(rcn_id);

ALTER TABLE "T_COMMENT_GARDEN"
    ADD CONSTRAINT fk_t_comment_grd_id FOREIGN KEY (cgr_grd_id) REFERENCES "T_GARDEN"(grd_id);

ALTER TABLE "T_COMMENT_PRODUCT"
    ADD CONSTRAINT fk_t_comment_product_note_id FOREIGN KEY (cpr_ren_id) REFERENCES "T_REF_EVALUATION_NOTE"(ren_id);

ALTER TABLE "T_COMMENT_PRODUCT"
    ADD CONSTRAINT fk_t_comment_product_prd_id FOREIGN KEY (cpr_prd_id) REFERENCES "T_PRODUCT"(prd_id);

ALTER TABLE "T_COMMENT_PRODUCT"
    ADD CONSTRAINT fk_t_comment_product_sts_id FOREIGN KEY (cpr_res_id) REFERENCES "T_REF_EVALUATION_STATUS"(res_id);

ALTER TABLE "T_COMMENT_PRODUCT"
    ADD CONSTRAINT fk_t_comment_product_user_id FOREIGN KEY (cpr_usr_id) REFERENCES "T_USER"(usr_id);

ALTER TABLE "T_COMMENT_GARDEN"
    ADD CONSTRAINT fk_t_comment_ren_id FOREIGN KEY (cgr_ren_id) REFERENCES "T_REF_EVALUATION_NOTE"(ren_id);

ALTER TABLE "T_COMMENT_GARDEN"
    ADD CONSTRAINT fk_t_comment_res_id FOREIGN KEY (cgr_res_id) REFERENCES "T_REF_EVALUATION_STATUS"(res_id);

ALTER TABLE "T_COMMENT_GARDEN"
    ADD CONSTRAINT fk_t_comment_usr_id FOREIGN KEY (cgr_usr_id) REFERENCES "T_USER"(usr_id);

ALTER TABLE "T_GARDEN"
    ADD CONSTRAINT fk_t_garden_adr_id FOREIGN KEY (grd_adr_id) REFERENCES "T_ADDRESS"(adr_id);

ALTER TABLE "T_GARDEN"
    ADD CONSTRAINT fk_t_garden_usr_id FOREIGN KEY (grd_usr_id) REFERENCES "T_USER"(usr_id);

ALTER TABLE "T_GARDEN"
    ADD CONSTRAINT fk_t_garden_sts_id FOREIGN KEY (grd_sts_id) REFERENCES "T_REF_STATUS"(sts_id);

ALTER TABLE "T_LIKE_GARDEN"
    ADD CONSTRAINT fk_t_like_garden_grd_id FOREIGN KEY (lgr_grd_id) REFERENCES "T_GARDEN"(grd_id);

ALTER TABLE "T_LIKE_GARDEN"
    ADD CONSTRAINT fk_t_like_garden_res_id FOREIGN KEY (lgr_res_id) REFERENCES "T_REF_EVALUATION_STATUS"(res_id);

ALTER TABLE "T_LIKE_GARDEN"
    ADD CONSTRAINT fk_t_like_garden_usr_id FOREIGN KEY (lgr_usr_id) REFERENCES "T_USER"(usr_id);

ALTER TABLE "T_LIKE_PRODUCT"
    ADD CONSTRAINT fk_t_like_product_prd_id FOREIGN KEY (lpr_prd_id) REFERENCES "T_PRODUCT"(prd_id);

ALTER TABLE "T_LIKE_PRODUCT"
    ADD CONSTRAINT fk_t_like_product_sts_id FOREIGN KEY (lpr_res_id) REFERENCES "T_REF_EVALUATION_STATUS"(res_id);

ALTER TABLE "T_LIKE_PRODUCT"
    ADD CONSTRAINT fk_t_like_product_user_id FOREIGN KEY (lpr_usr_id) REFERENCES "T_USER"(usr_id);

ALTER TABLE "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_grd_id FOREIGN KEY (prd_grd_id) REFERENCES "T_GARDEN"(grd_id);

ALTER TABLE "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_rcm_id FOREIGN KEY (prd_rcm_id) REFERENCES "T_REF_CULTURE_MODE"(rcm_id);

ALTER TABLE "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_rct_id FOREIGN KEY (prd_rct_id) REFERENCES "T_REF_CULTURE_TYPE"(rct_id);

ALTER TABLE "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_ret_id FOREIGN KEY (prd_chg_id) REFERENCES "T_REF_EXCHANGE_TYPE"(chg_id);

ALTER TABLE "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_sts_id FOREIGN KEY (prd_sts_id) REFERENCES "T_REF_STATUS"(sts_id);

ALTER TABLE "T_PROFIL"
    ADD CONSTRAINT fk_t_profil_usr_id FOREIGN KEY (prf_id) REFERENCES "T_USER"(usr_id);

ALTER TABLE "TJ_REAP_CALENDAR"
    ADD CONSTRAINT fk_t_reap_calendar_mnt_id FOREIGN KEY (rmn_id) REFERENCES "T_REF_MONTH"(rmn_id);

ALTER TABLE "TJ_REAP_CALENDAR"
    ADD CONSTRAINT fk_t_reap_calendar_rpc_id FOREIGN KEY (rpc_id) REFERENCES "T_REF_PRODUCT"(rpc_id);

ALTER TABLE "T_PRODUCT"
    ADD CONSTRAINT fk_t_ref_product_rpc_id FOREIGN KEY (prd_rpc_id) REFERENCES "T_REF_PRODUCT"(rpc_id);

ALTER TABLE "T_REF_PRODUCT"
    ADD CONSTRAINT fk_t_ref_product_rpt_id FOREIGN KEY (rpc_rpt_id) REFERENCES "T_REF_PRODUCT_TYPE"(rpt_id);

ALTER TABLE "T_RELATIONSHIP"
    ADD CONSTRAINT fk_t_relationship_rss_id FOREIGN KEY (rlt_rrs_id) REFERENCES "T_REF_RELATIONS_STATUS"(rrs_id);

ALTER TABLE "T_RELATIONSHIP"
    ADD CONSTRAINT fk_t_relationship_rtt_id FOREIGN KEY (rlt_rrt_id) REFERENCES "T_REF_RELATION_TYPE"(rrt_id);

ALTER TABLE "T_RELATIONSHIP"
    ADD CONSTRAINT fk_t_relationship_usr_id_recipient FOREIGN KEY (rlt_usr_id_recipient) REFERENCES "T_USER"(usr_id);

ALTER TABLE "T_RELATIONSHIP"
    ADD CONSTRAINT fk_t_relationship_usr_id_sender FOREIGN KEY (rlt_usr_id_sender) REFERENCES "T_USER"(usr_id);

ALTER TABLE "T_REQUEST_PRODUCT"
    ADD CONSTRAINT fk_t_request_product_prd_id FOREIGN KEY (rqt_prd_id) REFERENCES "T_PRODUCT"(prd_id);

ALTER TABLE "T_REQUEST_PRODUCT"
    ADD CONSTRAINT fk_t_request_product_rpr_id FOREIGN KEY (rqt_rpr_id) REFERENCES "T_REF_PRODUCT_REQUEST_STATUS"(rpr_id);

ALTER TABLE "T_REQUEST_PRODUCT"
    ADD CONSTRAINT fk_t_request_product_usr_id FOREIGN KEY (rqt_usr_id) REFERENCES "T_USER"(usr_id);

ALTER TABLE "TJ_SEED_CALENDAR"
    ADD CONSTRAINT fk_t_seed_calendar_rpc_id FOREIGN KEY (rpc_id) REFERENCES "T_REF_PRODUCT"(rpc_id);

ALTER TABLE "TJ_SEED_CALENDAR"
    ADD CONSTRAINT fk_t_seed_month_mnt_id FOREIGN KEY (rmn_id) REFERENCES "T_REF_MONTH"(rmn_id);

ALTER TABLE "T_SUBSCRIPTION"
    ADD CONSTRAINT fk_t_subscription_usr_id FOREIGN KEY (sbr_id) REFERENCES "T_USER"(usr_id);

ALTER TABLE "T_USER"
    ADD CONSTRAINT fk_t_user_adr_id FOREIGN KEY (usr_adr_id) REFERENCES "T_ADDRESS"(adr_id);

ALTER TABLE "T_USER"
    ADD CONSTRAINT fk_t_user_rur_id FOREIGN KEY (usr_rur_id) REFERENCES "T_REF_USER_ROLE"(rur_id);

ALTER TABLE "T_USER"
    ADD CONSTRAINT fk_t_user_rus_id FOREIGN KEY (usr_rus_id) REFERENCES "T_REF_USER_STATUS"(rus_id);
   
ALTER TABLE "T_PICTURE_GARDEN"
   ADD CONSTRAINT fk_T_PICTURE_GARDEN_grd_id FOREIGN KEY (grp_grd_id) REFERENCES "T_GARDEN" (grd_id);
   
ALTER TABLE "T_PICTURE_PRODUCT"
   ADD CONSTRAINT fk_T_PICTURE_PRODUCT_grd_id FOREIGN KEY (prp_prd_id) REFERENCES "T_GARDEN" (grd_id);
