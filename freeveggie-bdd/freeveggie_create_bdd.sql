--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.3
-- Dumped by pg_dump version 9.0.3
-- Started on 2011-12-08 23:53:31

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.t_user DROP CONSTRAINT fk_t_user_sts_id;
ALTER TABLE ONLY public.t_user DROP CONSTRAINT fk_t_user_sbs_id;
ALTER TABLE ONLY public.t_user DROP CONSTRAINT fk_t_user_prf_id;
ALTER TABLE ONLY public.t_user DROP CONSTRAINT fk_t_user_adr_id;
ALTER TABLE ONLY public.t_seed_calendar DROP CONSTRAINT fk_t_seed_month_prd_ref_id;
ALTER TABLE ONLY public.t_seed_calendar DROP CONSTRAINT fk_t_seed_month_mnt_id;
ALTER TABLE ONLY public.t_request_product DROP CONSTRAINT fk_t_request_product_user_rqt_id;
ALTER TABLE ONLY public.t_request_product DROP CONSTRAINT fk_t_request_product_sts_id;
ALTER TABLE ONLY public.t_request_product DROP CONSTRAINT fk_t_request_product_prd_id;
ALTER TABLE ONLY public.t_relationship DROP CONSTRAINT fk_t_relationship_user_id_two;
ALTER TABLE ONLY public.t_relationship DROP CONSTRAINT fk_t_relationship_user_id_one;
ALTER TABLE ONLY public.t_relationship DROP CONSTRAINT fk_t_relationship_type_id;
ALTER TABLE ONLY public.t_relationship DROP CONSTRAINT fk_t_relationship_sts_d;
ALTER TABLE ONLY public.t_ref_product DROP CONSTRAINT fk_t_ref_product_ptp_id;
ALTER TABLE ONLY public.t_reap_calendar DROP CONSTRAINT fk_t_reap_calendar_prd_ref_id;
ALTER TABLE ONLY public.t_reap_calendar DROP CONSTRAINT fk_t_reap_calendar_mnt_id;
ALTER TABLE ONLY public.t_product DROP CONSTRAINT fk_t_produit_prd_ref_id;
ALTER TABLE ONLY public.t_product DROP CONSTRAINT fk_t_produit_grd_id;
ALTER TABLE ONLY public.t_product DROP CONSTRAINT fk_t_product_type_id;
ALTER TABLE ONLY public.t_note_product DROP CONSTRAINT fk_t_product_prd_id;
ALTER TABLE ONLY public.t_product DROP CONSTRAINT fk_t_product_cmd_id;
ALTER TABLE ONLY public.t_product DROP CONSTRAINT fk_t_product_chg_id;
ALTER TABLE ONLY public.t_note_product DROP CONSTRAINT fk_t_note_produit_user_id;
ALTER TABLE ONLY public.t_note_product DROP CONSTRAINT fk_t_note_product_sts_id;
ALTER TABLE ONLY public.t_note_product DROP CONSTRAINT fk_t_note_product_note_id;
ALTER TABLE ONLY public.t_note_garden DROP CONSTRAINT fk_t_note_garden_user_id;
ALTER TABLE ONLY public.t_note_garden DROP CONSTRAINT fk_t_note_garden_sts_id;
ALTER TABLE ONLY public.t_note_garden DROP CONSTRAINT fk_t_note_garden_note_id;
ALTER TABLE ONLY public.t_note_garden DROP CONSTRAINT fk_t_note_garden_grd_id;
ALTER TABLE ONLY public.t_like_product DROP CONSTRAINT fk_t_like_product_user_id;
ALTER TABLE ONLY public.t_like_product DROP CONSTRAINT fk_t_like_product_sts_id;
ALTER TABLE ONLY public.t_like_product DROP CONSTRAINT fk_t_like_product_prd_id;
ALTER TABLE ONLY public.t_like_garden DROP CONSTRAINT fk_t_like_garden_user_id;
ALTER TABLE ONLY public.t_like_garden DROP CONSTRAINT "fk_t_like_garden_sts_Ã¨d";
ALTER TABLE ONLY public.t_like_garden DROP CONSTRAINT fk_t_like_garden_grd_id;
ALTER TABLE ONLY public.t_garden DROP CONSTRAINT fk_t_garden_grd_id;
ALTER TABLE ONLY public.t_garden DROP CONSTRAINT fk_t_garden_adr_id;
ALTER TABLE ONLY public.t_comment_product DROP CONSTRAINT fk_t_comment_product_user_id;
ALTER TABLE ONLY public.t_comment_product DROP CONSTRAINT fk_t_comment_product_sts_id;
ALTER TABLE ONLY public.t_comment_product DROP CONSTRAINT fk_t_comment_product_prd_id;
ALTER TABLE ONLY public.t_comment_garden DROP CONSTRAINT fk_t_comment_garden_user_id;
ALTER TABLE ONLY public.t_comment_garden DROP CONSTRAINT fk_t_comment_garden_sts_id;
ALTER TABLE ONLY public.t_comment_garden DROP CONSTRAINT fk_t_comment_garden_grd_id;
ALTER TABLE ONLY public.t_user DROP CONSTRAINT unq_t_user_0;
ALTER TABLE ONLY public.t_relationship DROP CONSTRAINT unq_t_relationship_0;
ALTER TABLE ONLY public.t_user_subscription DROP CONSTRAINT t_user_subscription_pkey;
ALTER TABLE ONLY public.t_user DROP CONSTRAINT t_user_pkey;
ALTER TABLE ONLY public.t_seed_calendar DROP CONSTRAINT t_seed_month_pkey;
ALTER TABLE ONLY public.t_request_product DROP CONSTRAINT t_request_product_pkey;
ALTER TABLE ONLY public.t_relationship DROP CONSTRAINT t_relationship_pkey;
ALTER TABLE ONLY public.t_ref_user_status DROP CONSTRAINT t_ref_user_status_pk;
ALTER TABLE ONLY public.t_ref_user_role DROP CONSTRAINT t_ref_user_role_pkey;
ALTER TABLE ONLY public.t_ref_relation_type DROP CONSTRAINT t_ref_relation_type_pkey;
ALTER TABLE ONLY public.t_ref_relation_status DROP CONSTRAINT t_ref_relation_status_pkey;
ALTER TABLE ONLY public.t_ref_product_type DROP CONSTRAINT t_ref_product_type_pkey;
ALTER TABLE ONLY public.t_ref_product_request_status DROP CONSTRAINT t_ref_product_request_status_pkey;
ALTER TABLE ONLY public.t_ref_product DROP CONSTRAINT t_ref_product_pkey;
ALTER TABLE ONLY public.t_ref_month DROP CONSTRAINT t_ref_month_pkey;
ALTER TABLE ONLY public.t_ref_exchange_type DROP CONSTRAINT t_ref_exchange_type_pkey;
ALTER TABLE ONLY public.t_ref_evaluation_status DROP CONSTRAINT t_ref_evaluation_status_pkey;
ALTER TABLE ONLY public.t_ref_evaluation_note DROP CONSTRAINT t_ref_evaluation_note_pkey;
ALTER TABLE ONLY public.t_ref_culture_type DROP CONSTRAINT t_ref_culture_type_pkey;
ALTER TABLE ONLY public.t_ref_culture_mode DROP CONSTRAINT t_ref_culture_mode_pkey;
ALTER TABLE ONLY public.t_ref_country DROP CONSTRAINT t_ref_country_pkey;
ALTER TABLE ONLY public.t_reap_calendar DROP CONSTRAINT t_reap_calendar_pkey;
ALTER TABLE ONLY public.t_profil DROP CONSTRAINT t_profil_pkey;
ALTER TABLE ONLY public.t_product DROP CONSTRAINT t_produit_pkey;
ALTER TABLE ONLY public.t_note_product DROP CONSTRAINT t_note_produit_pkey;
ALTER TABLE ONLY public.t_note_garden DROP CONSTRAINT t_note_garden_pkey;
ALTER TABLE ONLY public.t_like_product DROP CONSTRAINT t_like_product_pkey;
ALTER TABLE ONLY public.t_like_garden DROP CONSTRAINT t_like_garden_pkey;
ALTER TABLE ONLY public.t_garden DROP CONSTRAINT t_garden_pkey;
ALTER TABLE ONLY public.t_comment_product DROP CONSTRAINT t_comment_product_pkey;
ALTER TABLE ONLY public.t_comment_garden DROP CONSTRAINT t_comment_garden_pkey;
ALTER TABLE ONLY public.t_address DROP CONSTRAINT t_address_pkey;
ALTER TABLE public.t_ref_product ALTER COLUMN prd_ref_id DROP DEFAULT;
DROP VIEW public.v_partial_user;
DROP TABLE public.t_user_subscription;
DROP TABLE public.t_user;
DROP TABLE public.t_seed_calendar;
DROP TABLE public.t_request_product;
DROP TABLE public.t_relationship;
DROP TABLE public.t_ref_user_status;
DROP TABLE public.t_ref_user_role;
DROP TABLE public.t_ref_relation_type;
DROP TABLE public.t_ref_relation_status;
DROP TABLE public.t_ref_product_type;
DROP TABLE public.t_ref_product_request_status;
DROP SEQUENCE public.t_ref_product_prd_ref_id_seq;
DROP TABLE public.t_ref_product;
DROP TABLE public.t_ref_month;
DROP TABLE public.t_ref_exchange_type;
DROP TABLE public.t_ref_evaluation_status;
DROP TABLE public.t_ref_evaluation_note;
DROP TABLE public.t_ref_culture_type;
DROP TABLE public.t_ref_culture_mode;
DROP TABLE public.t_ref_country;
DROP TABLE public.t_reap_calendar;
DROP TABLE public.t_profil;
DROP TABLE public.t_product;
DROP TABLE public.t_note_product;
DROP TABLE public.t_note_garden;
DROP TABLE public.t_like_product;
DROP TABLE public.t_like_garden;
DROP TABLE public.t_garden;
DROP TABLE public.t_comment_product;
DROP TABLE public.t_comment_garden;
DROP TABLE public.t_address;
DROP SEQUENCE public.s_usr_seq;
DROP SEQUENCE public.s_sbr_seq;
DROP SEQUENCE public.s_rqt_seq;
DROP SEQUENCE public.s_rlt_seq;
DROP SEQUENCE public.s_prf_seq;
DROP SEQUENCE public.s_prd_seq;
DROP SEQUENCE public.s_nt_prd_seq;
DROP SEQUENCE public.s_nt_grd_seq;
DROP SEQUENCE public.s_lik_prd_seq;
DROP SEQUENCE public.s_lik_grd_seq;
DROP SEQUENCE public.s_img_seq;
DROP SEQUENCE public.s_grd_seq;
DROP SEQUENCE public.s_cmt_prd_seq;
DROP SEQUENCE public.s_cmt_grd_seq;
DROP SEQUENCE public.s_adr_seq;
DROP PROCEDURAL LANGUAGE plpgsql;
DROP SCHEMA public;
--
-- TOC entry 6 (class 2615 OID 108553)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 394 (class 2612 OID 108554)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- TOC entry 1586 (class 1259 OID 108555)
-- Dependencies: 6
-- Name: s_adr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_adr_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_adr_seq OWNER TO freeveggie;

--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 1586
-- Name: s_adr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_adr_seq', 1, true);


--
-- TOC entry 1587 (class 1259 OID 108557)
-- Dependencies: 6
-- Name: s_cmt_grd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_cmt_grd_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_cmt_grd_seq OWNER TO freeveggie;

--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 1587
-- Name: s_cmt_grd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_cmt_grd_seq', 1, true);


--
-- TOC entry 1588 (class 1259 OID 108559)
-- Dependencies: 6
-- Name: s_cmt_prd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_cmt_prd_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_cmt_prd_seq OWNER TO freeveggie;

--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 1588
-- Name: s_cmt_prd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_cmt_prd_seq', 1, true);


--
-- TOC entry 1589 (class 1259 OID 108561)
-- Dependencies: 6
-- Name: s_grd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_grd_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_grd_seq OWNER TO freeveggie;

--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 1589
-- Name: s_grd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_grd_seq', 1, true);


--
-- TOC entry 1590 (class 1259 OID 108563)
-- Dependencies: 6
-- Name: s_img_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_img_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_img_seq OWNER TO freeveggie;

--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 1590
-- Name: s_img_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_img_seq', 1, true);


--
-- TOC entry 1591 (class 1259 OID 108565)
-- Dependencies: 6
-- Name: s_lik_grd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_lik_grd_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_lik_grd_seq OWNER TO freeveggie;

--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 1591
-- Name: s_lik_grd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_lik_grd_seq', 1, true);


--
-- TOC entry 1592 (class 1259 OID 108567)
-- Dependencies: 6
-- Name: s_lik_prd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_lik_prd_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_lik_prd_seq OWNER TO freeveggie;

--
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 1592
-- Name: s_lik_prd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_lik_prd_seq', 1, true);


--
-- TOC entry 1593 (class 1259 OID 108569)
-- Dependencies: 6
-- Name: s_nt_grd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_nt_grd_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_nt_grd_seq OWNER TO freeveggie;

--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 1593
-- Name: s_nt_grd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_nt_grd_seq', 1, true);


--
-- TOC entry 1594 (class 1259 OID 108571)
-- Dependencies: 6
-- Name: s_nt_prd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_nt_prd_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_nt_prd_seq OWNER TO freeveggie;

--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 1594
-- Name: s_nt_prd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_nt_prd_seq', 1, true);


--
-- TOC entry 1595 (class 1259 OID 108573)
-- Dependencies: 6
-- Name: s_prd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_prd_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_prd_seq OWNER TO freeveggie;

--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 1595
-- Name: s_prd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_prd_seq', 1, true);


--
-- TOC entry 1596 (class 1259 OID 108575)
-- Dependencies: 6
-- Name: s_prf_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_prf_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_prf_seq OWNER TO freeveggie;

--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 1596
-- Name: s_prf_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_prf_seq', 1, true);


--
-- TOC entry 1597 (class 1259 OID 108577)
-- Dependencies: 6
-- Name: s_rlt_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_rlt_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_rlt_seq OWNER TO freeveggie;

--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 1597
-- Name: s_rlt_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_rlt_seq', 1, true);


--
-- TOC entry 1598 (class 1259 OID 108579)
-- Dependencies: 6
-- Name: s_rqt_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_rqt_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_rqt_seq OWNER TO freeveggie;

--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 1598
-- Name: s_rqt_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_rqt_seq', 1, true);


--
-- TOC entry 1599 (class 1259 OID 108581)
-- Dependencies: 6
-- Name: s_sbr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_sbr_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_sbr_seq OWNER TO freeveggie;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 1599
-- Name: s_sbr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_sbr_seq', 1, true);


--
-- TOC entry 1600 (class 1259 OID 108583)
-- Dependencies: 6
-- Name: s_usr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_usr_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_usr_seq OWNER TO freeveggie;

--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 1600
-- Name: s_usr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_usr_seq', 1, true);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1601 (class 1259 OID 108585)
-- Dependencies: 6
-- Name: t_address; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_address (
    adr_id bigint NOT NULL,
    address_line_1 character varying(255) NOT NULL,
    address_line_2 character varying(255),
    city character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    street_number integer NOT NULL,
    zipcode integer NOT NULL
);


ALTER TABLE public.t_address OWNER TO freeveggie;

--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 1601
-- Name: TABLE t_address; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_address IS 'The address of the user and/or the garden';


--
-- TOC entry 1602 (class 1259 OID 108591)
-- Dependencies: 6
-- Name: t_comment_garden; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_comment_garden (
    cmt_grd_id bigint NOT NULL,
    comment character varying(500),
    creation_date timestamp without time zone NOT NULL,
    sts_id bigint NOT NULL,
    grd_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.t_comment_garden OWNER TO freeveggie;

--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 1602
-- Name: TABLE t_comment_garden; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_comment_garden IS 'List of the comment made on gardens';


--
-- TOC entry 1603 (class 1259 OID 108597)
-- Dependencies: 6
-- Name: t_comment_product; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_comment_product (
    cmt_prd_id bigint NOT NULL,
    comment character varying(500) NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    sts_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.t_comment_product OWNER TO freeveggie;

--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 1603
-- Name: TABLE t_comment_product; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_comment_product IS 'List of comment made on the product';


--
-- TOC entry 1604 (class 1259 OID 108603)
-- Dependencies: 6
-- Name: t_garden; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_garden (
    grd_id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    description character varying(255),
    home_adr boolean NOT NULL,
    name character varying(255) NOT NULL,
    img_filename character varying(255),
    adr_id bigint
);


ALTER TABLE public.t_garden OWNER TO freeveggie;

--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 1604
-- Name: TABLE t_garden; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_garden IS 'The gardesn';


--
-- TOC entry 1605 (class 1259 OID 108609)
-- Dependencies: 6
-- Name: t_like_garden; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_like_garden (
    lik_grd_id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    sts_id bigint NOT NULL,
    grd_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.t_like_garden OWNER TO freeveggie;

--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 1605
-- Name: TABLE t_like_garden; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_like_garden IS 'The list of garden like by user';


--
-- TOC entry 1606 (class 1259 OID 108612)
-- Dependencies: 6
-- Name: t_like_product; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_like_product (
    lik_prd_id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    sts_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.t_like_product OWNER TO freeveggie;

--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 1606
-- Name: TABLE t_like_product; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_like_product IS 'The list of product like by user';


--
-- TOC entry 1607 (class 1259 OID 108615)
-- Dependencies: 6
-- Name: t_note_garden; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_note_garden (
    nt_grd_id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    sts_id bigint NOT NULL,
    note_id integer NOT NULL,
    grd_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.t_note_garden OWNER TO freeveggie;

--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 1607
-- Name: TABLE t_note_garden; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_note_garden IS 'The garden note made by user';


--
-- TOC entry 1608 (class 1259 OID 108618)
-- Dependencies: 6
-- Name: t_note_product; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_note_product (
    nt_prd_id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    sts_id bigint NOT NULL,
    note_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.t_note_product OWNER TO freeveggie;

--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 1608
-- Name: TABLE t_note_product; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_note_product IS 'The list of product note made by user';


--
-- TOC entry 1609 (class 1259 OID 108621)
-- Dependencies: 6
-- Name: t_product; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_product (
    prd_id bigint NOT NULL,
    cmd_id bigint NOT NULL,
    type_id bigint NOT NULL,
    description character varying(500),
    chg_id bigint NOT NULL,
    img_filename character varying(255),
    quantity double precision NOT NULL,
    grd_id bigint NOT NULL,
    prd_ref_id bigint NOT NULL
);


ALTER TABLE public.t_product OWNER TO freeveggie;

--
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 1609
-- Name: TABLE t_product; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_product IS 'The product made by user';


--
-- TOC entry 1610 (class 1259 OID 108627)
-- Dependencies: 6
-- Name: t_profil; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_profil (
    prf_id bigint NOT NULL,
    experience character varying(500),
    interest character varying(500),
    other character varying(500),
    participation character varying(500),
    description character varying(500),
    philosophy character varying(500),
    meals character varying(500),
    img_filename character varying(500)
);


ALTER TABLE public.t_profil OWNER TO freeveggie;

--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 1610
-- Name: TABLE t_profil; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_profil IS 'User profil';


--
-- TOC entry 1611 (class 1259 OID 108633)
-- Dependencies: 6
-- Name: t_reap_calendar; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_reap_calendar (
    prd_ref_id bigint NOT NULL,
    mnt_id bigint NOT NULL
);


ALTER TABLE public.t_reap_calendar OWNER TO freeveggie;

--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 1611
-- Name: TABLE t_reap_calendar; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_reap_calendar IS 'The reference product reaping calendar';


--
-- TOC entry 1632 (class 1259 OID 108987)
-- Dependencies: 6
-- Name: t_ref_country; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_country (
    cnt_ref_id bigint NOT NULL,
    country_name character varying NOT NULL,
    code_iso_a3 character(3) NOT NULL,
    code_iso_a2 character(2) NOT NULL,
    code_iso_number integer NOT NULL
);


ALTER TABLE public.t_ref_country OWNER TO freeveggie;

--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 1632
-- Name: TABLE t_ref_country; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_country IS 'The list of the country';


--
-- TOC entry 1612 (class 1259 OID 108636)
-- Dependencies: 6
-- Name: t_ref_culture_mode; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_culture_mode (
    cmd_id bigint NOT NULL,
    culture_mod_libelle character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_culture_mode OWNER TO freeveggie;

--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 1612
-- Name: TABLE t_ref_culture_mode; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_culture_mode IS 'The choice of culture mode';


--
-- TOC entry 1613 (class 1259 OID 108639)
-- Dependencies: 6
-- Name: t_ref_culture_type; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_culture_type (
    type_id bigint NOT NULL,
    type_libelle character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_culture_type OWNER TO freeveggie;

--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 1613
-- Name: TABLE t_ref_culture_type; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_culture_type IS 'The choice of culture type';


--
-- TOC entry 1614 (class 1259 OID 108642)
-- Dependencies: 6
-- Name: t_ref_evaluation_note; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_evaluation_note (
    "NOTE_ID" bigint NOT NULL,
    "NOTE_LIBELLE" character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_evaluation_note OWNER TO freeveggie;

--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 1614
-- Name: TABLE t_ref_evaluation_note; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_evaluation_note IS 'The value of a note (for a garden or a product)';


--
-- TOC entry 1615 (class 1259 OID 108645)
-- Dependencies: 6
-- Name: t_ref_evaluation_status; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_evaluation_status (
    "STS_ID" bigint NOT NULL,
    "STATUS_LIBELLE" character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_evaluation_status OWNER TO freeveggie;

--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 1615
-- Name: TABLE t_ref_evaluation_status; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_evaluation_status IS 'The status of an evaluation(product, garden, ...)';


--
-- TOC entry 1616 (class 1259 OID 108648)
-- Dependencies: 6
-- Name: t_ref_exchange_type; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_exchange_type (
    chg_id bigint NOT NULL,
    chg_libelle character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_exchange_type OWNER TO freeveggie;

--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 1616
-- Name: TABLE t_ref_exchange_type; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_exchange_type IS 'Choice of exchange type';


--
-- TOC entry 1617 (class 1259 OID 108651)
-- Dependencies: 6
-- Name: t_ref_month; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_month (
    mnt_id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.t_ref_month OWNER TO freeveggie;

--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 1617
-- Name: TABLE t_ref_month; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_month IS 'The reference mont of a year';


--
-- TOC entry 1618 (class 1259 OID 108654)
-- Dependencies: 6
-- Name: t_ref_product; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_product (
    prd_ref_id integer NOT NULL,
    carbohydrate double precision,
    lipid double precision,
    name character varying(255) NOT NULL,
    img_id character varying(255),
    protein double precision,
    ptp_id bigint NOT NULL
);


ALTER TABLE public.t_ref_product OWNER TO freeveggie;

--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 1618
-- Name: TABLE t_ref_product; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_product IS 'The reference of product';


--
-- TOC entry 1619 (class 1259 OID 108660)
-- Dependencies: 6 1618
-- Name: t_ref_product_prd_ref_id_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE t_ref_product_prd_ref_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_ref_product_prd_ref_id_seq OWNER TO freeveggie;

--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 1619
-- Name: t_ref_product_prd_ref_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freeveggie
--

ALTER SEQUENCE t_ref_product_prd_ref_id_seq OWNED BY t_ref_product.prd_ref_id;


--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 1619
-- Name: t_ref_product_prd_ref_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('t_ref_product_prd_ref_id_seq', 1, false);


--
-- TOC entry 1620 (class 1259 OID 108662)
-- Dependencies: 6
-- Name: t_ref_product_request_status; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_product_request_status (
    "STS_ID" bigint NOT NULL,
    "STAUTS_LIBELLE" character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_product_request_status OWNER TO freeveggie;

--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 1620
-- Name: TABLE t_ref_product_request_status; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_product_request_status IS 'The posible state of a product request';


--
-- TOC entry 1621 (class 1259 OID 108665)
-- Dependencies: 6
-- Name: t_ref_product_type; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_product_type (
    ptp_id bigint NOT NULL,
    ptp_libelle character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_product_type OWNER TO freeveggie;

--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 1621
-- Name: TABLE t_ref_product_type; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_product_type IS 'The reference of product type (fruit or vegetable)';


--
-- TOC entry 1622 (class 1259 OID 108668)
-- Dependencies: 6
-- Name: t_ref_relation_status; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_relation_status (
    sts_id bigint NOT NULL,
    status_libelle character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_relation_status OWNER TO freeveggie;

--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 1622
-- Name: TABLE t_ref_relation_status; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_relation_status IS 'The posible state of relationship';


--
-- TOC entry 1623 (class 1259 OID 108671)
-- Dependencies: 6
-- Name: t_ref_relation_type; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_relation_type (
    type_id bigint NOT NULL,
    type_libelle character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_relation_type OWNER TO freeveggie;

--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 1623
-- Name: TABLE t_ref_relation_type; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_relation_type IS 'The possible value of a relation type (friends, neighbors, ...)';


--
-- TOC entry 1624 (class 1259 OID 108674)
-- Dependencies: 6
-- Name: t_ref_user_role; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_ref_user_role (
    role_id bigint NOT NULL,
    role_libelle character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_user_role OWNER TO freeveggie;

--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 1624
-- Name: TABLE t_ref_user_role; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_ref_user_role IS 'The posible user role';


--
-- TOC entry 1625 (class 1259 OID 108677)
-- Dependencies: 6
-- Name: t_ref_user_status; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE t_ref_user_status (
    sts_id bigint NOT NULL,
    status_libelle character varying(255) NOT NULL
);


ALTER TABLE public.t_ref_user_status OWNER TO postgres;

--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 1625
-- Name: TABLE t_ref_user_status; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE t_ref_user_status IS 'The possible state of user (new, blacklisted, ....)';


--
-- TOC entry 1626 (class 1259 OID 108680)
-- Dependencies: 6
-- Name: t_relationship; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_relationship (
    rlt_id bigint NOT NULL,
    answer character varying(500) NOT NULL,
    creation_date date NOT NULL,
    sts_id bigint NOT NULL,
    type_id integer NOT NULL,
    user_id_one bigint NOT NULL,
    user_id_two bigint NOT NULL,
    request character varying(500) NOT NULL
);


ALTER TABLE public.t_relationship OWNER TO freeveggie;

--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 1626
-- Name: TABLE t_relationship; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_relationship IS 'The list of user relationship
';


--
-- TOC entry 1627 (class 1259 OID 108686)
-- Dependencies: 6
-- Name: t_request_product; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_request_product (
    rqt_id bigint NOT NULL,
    answer_msg character varying(500),
    answer_date timestamp without time zone,
    creation_date timestamp without time zone NOT NULL,
    request_msg character varying(500) NOT NULL,
    picking_daye timestamp without time zone NOT NULL,
    quantity double precision NOT NULL,
    sts_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.t_request_product OWNER TO freeveggie;

--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 1627
-- Name: TABLE t_request_product; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_request_product IS 'The product request made by users';


--
-- TOC entry 1628 (class 1259 OID 108692)
-- Dependencies: 6
-- Name: t_seed_calendar; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_seed_calendar (
    prd_ref_id bigint NOT NULL,
    mnt_id bigint NOT NULL
);


ALTER TABLE public.t_seed_calendar OWNER TO freeveggie;

--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 1628
-- Name: TABLE t_seed_calendar; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_seed_calendar IS 'The reference month of seedling product';


--
-- TOC entry 1629 (class 1259 OID 108695)
-- Dependencies: 6
-- Name: t_user; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_user (
    user_id bigint NOT NULL,
    blacklisted_date timestamp without time zone,
    cancellation_date timestamp without time zone,
    creation_date timestamp without time zone NOT NULL,
    email_address character varying(255) NOT NULL,
    firstname character varying(255) NOT NULL,
    last_connexion timestamp without time zone NOT NULL,
    lastname character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    sts_id bigint NOT NULL,
    temp_password character varying(255),
    username character varying(255) NOT NULL,
    adr_id bigint NOT NULL,
    prf_id bigint NOT NULL,
    sbs_id bigint NOT NULL
);


ALTER TABLE public.t_user OWNER TO freeveggie;

--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 1629
-- Name: TABLE t_user; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_user IS 'The website users';


--
-- TOC entry 1630 (class 1259 OID 108701)
-- Dependencies: 6
-- Name: t_user_subscription; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE t_user_subscription (
    sbr_id bigint NOT NULL,
    freeveggie_agreement boolean,
    newsletter boolean,
    share_garden_info boolean,
    share_personall_info boolean
);


ALTER TABLE public.t_user_subscription OWNER TO freeveggie;

--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 1630
-- Name: TABLE t_user_subscription; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE t_user_subscription IS 'The user subscription agreements';


--
-- TOC entry 1631 (class 1259 OID 108704)
-- Dependencies: 1719 6
-- Name: v_partial_user; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW v_partial_user AS
    SELECT t_user.user_id, t_user.firstname, t_user.last_connexion, t_user.lastname, t_user.username FROM t_user WHERE (t_user.sts_id = 2);


ALTER TABLE public.v_partial_user OWNER TO postgres;

--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 1631
-- Name: VIEW v_partial_user; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON VIEW v_partial_user IS 'A partial vue of a user';


--
-- TOC entry 1911 (class 2604 OID 108708)
-- Dependencies: 1619 1618
-- Name: prd_ref_id; Type: DEFAULT; Schema: public; Owner: freeveggie
--

ALTER TABLE t_ref_product ALTER COLUMN prd_ref_id SET DEFAULT nextval('t_ref_product_prd_ref_id_seq'::regclass);


--
-- TOC entry 2019 (class 0 OID 108585)
-- Dependencies: 1601
-- Data for Name: t_address; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2020 (class 0 OID 108591)
-- Dependencies: 1602
-- Data for Name: t_comment_garden; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2021 (class 0 OID 108597)
-- Dependencies: 1603
-- Data for Name: t_comment_product; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2022 (class 0 OID 108603)
-- Dependencies: 1604
-- Data for Name: t_garden; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2023 (class 0 OID 108609)
-- Dependencies: 1605
-- Data for Name: t_like_garden; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2024 (class 0 OID 108612)
-- Dependencies: 1606
-- Data for Name: t_like_product; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2025 (class 0 OID 108615)
-- Dependencies: 1607
-- Data for Name: t_note_garden; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2026 (class 0 OID 108618)
-- Dependencies: 1608
-- Data for Name: t_note_product; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2027 (class 0 OID 108621)
-- Dependencies: 1609
-- Data for Name: t_product; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2028 (class 0 OID 108627)
-- Dependencies: 1610
-- Data for Name: t_profil; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2029 (class 0 OID 108633)
-- Dependencies: 1611
-- Data for Name: t_reap_calendar; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2048 (class 0 OID 108987)
-- Dependencies: 1632
-- Data for Name: t_ref_country; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_country VALUES (1, 'AFGHANISTAN', 'AFG', 'AF', 4);
INSERT INTO t_ref_country VALUES (2, 'ALAND ISLANDS', 'ALA', 'AX', 248);
INSERT INTO t_ref_country VALUES (3, 'ALBANIA', 'ALB', 'AL', 8);
INSERT INTO t_ref_country VALUES (4, 'ALGERIA', 'DZA', 'DZ', 12);
INSERT INTO t_ref_country VALUES (5, 'AMERICAN SAMOA', 'ASM', 'AS', 16);
INSERT INTO t_ref_country VALUES (6, 'ANDORRA', 'AND', 'AD', 20);
INSERT INTO t_ref_country VALUES (7, 'ANGOLA', 'AGO', 'AO', 24);
INSERT INTO t_ref_country VALUES (8, 'ANGUILLA', 'AIA', 'AI', 660);
INSERT INTO t_ref_country VALUES (9, 'ANTARCTICA', 'ATA', 'AQ', 10);
INSERT INTO t_ref_country VALUES (10, 'ANTIGUA AND BARBUDA', 'ATG', 'AG', 28);
INSERT INTO t_ref_country VALUES (11, 'ARGENTINA', 'ARG', 'AR', 32);
INSERT INTO t_ref_country VALUES (12, 'ARMENIA', 'ARM', 'AM', 51);
INSERT INTO t_ref_country VALUES (13, 'ARUBA', 'ABW', 'AW', 533);
INSERT INTO t_ref_country VALUES (14, 'AUSTRALIA', 'AUS', 'AU', 36);
INSERT INTO t_ref_country VALUES (15, 'AUSTRIA', 'AUT', 'AT', 40);
INSERT INTO t_ref_country VALUES (16, 'AZERBAIJAN', 'AZE', 'AZ', 31);
INSERT INTO t_ref_country VALUES (17, 'BAHAMAS', 'BHS', 'BS', 44);
INSERT INTO t_ref_country VALUES (18, 'BAHRAIN', 'BHR', 'BH', 48);
INSERT INTO t_ref_country VALUES (19, 'BANGLADESH', 'BGD', 'BD', 50);
INSERT INTO t_ref_country VALUES (20, 'BARBADOS', 'BRB', 'BB', 52);
INSERT INTO t_ref_country VALUES (21, 'BELARUS', 'BLR', 'BY', 112);
INSERT INTO t_ref_country VALUES (22, 'BELGIUM', 'BEL', 'BE', 56);
INSERT INTO t_ref_country VALUES (23, 'BELIZE', 'BLZ', 'BZ', 84);
INSERT INTO t_ref_country VALUES (24, 'BENIN', 'BEN', 'BJ', 204);
INSERT INTO t_ref_country VALUES (25, 'BERMUDA', 'BMU', 'BM', 60);
INSERT INTO t_ref_country VALUES (26, 'BHUTAN', 'BTN', 'BT', 64);
INSERT INTO t_ref_country VALUES (27, 'BOLIVIA, PLURINATIONAL STATE OF', 'BOL', 'BO', 68);
INSERT INTO t_ref_country VALUES (28, 'BONAIRE, SINT EUSTATIUS AND SABA', 'BES', 'BQ', 535);
INSERT INTO t_ref_country VALUES (29, 'BOSNIA AND HERZEGOVINA', 'BIH', 'BA', 70);
INSERT INTO t_ref_country VALUES (30, 'BOTSWANA', 'BWA', 'BW', 72);
INSERT INTO t_ref_country VALUES (31, 'BOUVET ISLAND', 'BVT', 'BV', 74);
INSERT INTO t_ref_country VALUES (32, 'BRAZIL', 'BRA', 'BR', 76);
INSERT INTO t_ref_country VALUES (33, 'BRITISH INDIAN OCEAN TERRITORY', 'IOT', 'IO', 86);
INSERT INTO t_ref_country VALUES (34, 'BRUNEI DARUSSALAM', 'BRN', 'BN', 96);
INSERT INTO t_ref_country VALUES (35, 'BULGARIA', 'BGR', 'BG', 100);
INSERT INTO t_ref_country VALUES (36, 'BURKINA FASO', 'BFA', 'BF', 854);
INSERT INTO t_ref_country VALUES (37, 'BURUNDI', 'BDI', 'BI', 108);
INSERT INTO t_ref_country VALUES (38, 'CAMBODIA', 'KHM', 'KH', 116);
INSERT INTO t_ref_country VALUES (39, 'CAMEROON', 'CMR', 'CM', 120);
INSERT INTO t_ref_country VALUES (40, 'CANADA', 'CAN', 'CA', 124);
INSERT INTO t_ref_country VALUES (41, 'CAPE VERDE', 'CPV', 'CV', 132);
INSERT INTO t_ref_country VALUES (42, 'CAYMAN ISLANDS', 'CYM', 'KY', 136);
INSERT INTO t_ref_country VALUES (43, 'CENTRAL AFRICAN REPUBLIC', 'CAF', 'CF', 140);
INSERT INTO t_ref_country VALUES (44, 'CHAD', 'TCD', 'TD', 148);
INSERT INTO t_ref_country VALUES (45, 'CHILE', 'CHL', 'CL', 152);
INSERT INTO t_ref_country VALUES (46, 'CHINA', 'CHN', 'CN', 156);
INSERT INTO t_ref_country VALUES (47, 'CHRISTMAS ISLAND', 'CXR', 'CX', 162);
INSERT INTO t_ref_country VALUES (48, 'COCOS (KEELING) ISLANDS', 'CCK', 'CC', 166);
INSERT INTO t_ref_country VALUES (49, 'COLOMBIA', 'COL', 'CO', 170);
INSERT INTO t_ref_country VALUES (50, 'COMOROS', 'COM', 'KM', 174);
INSERT INTO t_ref_country VALUES (51, 'CONGO', 'COG', 'CG', 178);
INSERT INTO t_ref_country VALUES (52, 'CONGO, THE DEMOCRATIC REPUBLIC OF THE', 'COD', 'CD', 180);
INSERT INTO t_ref_country VALUES (53, 'COOK ISLANDS', 'COK', 'CK', 184);
INSERT INTO t_ref_country VALUES (54, 'COSTA RICA', 'CRI', 'CR', 188);
INSERT INTO t_ref_country VALUES (55, 'COTE D IVOIRE', 'CIV', 'CI', 384);
INSERT INTO t_ref_country VALUES (56, 'CROATIA', 'HRV', 'HR', 191);
INSERT INTO t_ref_country VALUES (57, 'CUBA', 'CUB', 'CU', 192);
INSERT INTO t_ref_country VALUES (58, 'CURACAO', 'CUW', 'CW', 531);
INSERT INTO t_ref_country VALUES (59, 'CYPRUS', 'CYP', 'CY', 196);
INSERT INTO t_ref_country VALUES (60, 'CZECH REPUBLIC', 'CZE', 'CZ', 203);
INSERT INTO t_ref_country VALUES (61, 'DENMARK', 'DNK', 'DK', 208);
INSERT INTO t_ref_country VALUES (62, 'DJIBOUTI', 'DJI', 'DJ', 262);
INSERT INTO t_ref_country VALUES (63, 'DOMINICA', 'DMA', 'DM', 212);
INSERT INTO t_ref_country VALUES (64, 'DOMINICAN REPUBLIC', 'DOM', 'DO', 214);
INSERT INTO t_ref_country VALUES (65, 'ECUADOR', 'ECU', 'EC', 218);
INSERT INTO t_ref_country VALUES (66, 'EGYPT', 'EGY', 'EG', 818);
INSERT INTO t_ref_country VALUES (67, 'EL SALVADOR', 'SLV', 'SV', 222);
INSERT INTO t_ref_country VALUES (68, 'EQUATORIAL GUINEA', 'GNQ', 'GQ', 226);
INSERT INTO t_ref_country VALUES (69, 'ERITREA', 'ERI', 'ER', 232);
INSERT INTO t_ref_country VALUES (70, 'ESTONIA', 'EST', 'EE', 233);
INSERT INTO t_ref_country VALUES (71, 'ETHIOPIA', 'ETH', 'ET', 231);
INSERT INTO t_ref_country VALUES (72, 'FALKLAND ISLANDS (MALVINAS)', 'FLK', 'FK', 238);
INSERT INTO t_ref_country VALUES (73, 'FAROE ISLANDS', 'FRO', 'FO', 234);
INSERT INTO t_ref_country VALUES (74, 'FIJI', 'FJI', 'FJ', 242);
INSERT INTO t_ref_country VALUES (75, 'FINLAND', 'FIN', 'FI', 246);
INSERT INTO t_ref_country VALUES (76, 'FRANCE', 'FRA', 'FR', 250);
INSERT INTO t_ref_country VALUES (77, 'FRENCH GUIANA', 'GUF', 'GF', 254);
INSERT INTO t_ref_country VALUES (78, 'FRENCH POLYNESIA', 'PYF', 'PF', 258);
INSERT INTO t_ref_country VALUES (79, 'FRENCH SOUTHERN TERRITORIES', 'ATF', 'TF', 260);
INSERT INTO t_ref_country VALUES (80, 'GABON', 'GAB', 'GA', 266);
INSERT INTO t_ref_country VALUES (81, 'GAMBIA', 'GMB', 'GM', 270);
INSERT INTO t_ref_country VALUES (82, 'GEORGIA', 'GEO', 'GE', 268);
INSERT INTO t_ref_country VALUES (83, 'GERMANY', 'DEU', 'DE', 276);
INSERT INTO t_ref_country VALUES (84, 'GHANA', 'GHA', 'GH', 288);
INSERT INTO t_ref_country VALUES (85, 'GIBRALTAR', 'GIB', 'GI', 292);
INSERT INTO t_ref_country VALUES (86, 'GREECE', 'GRC', 'GR', 300);
INSERT INTO t_ref_country VALUES (87, 'GREENLAND', 'GRL', 'GL', 304);
INSERT INTO t_ref_country VALUES (88, 'GRENADA', 'GRD', 'GD', 308);
INSERT INTO t_ref_country VALUES (89, 'GUADELOUPE', 'GLP', 'GP', 312);
INSERT INTO t_ref_country VALUES (90, 'GUAM', 'GUM', 'GU', 316);
INSERT INTO t_ref_country VALUES (91, 'GUATEMALA', 'GTM', 'GT', 320);
INSERT INTO t_ref_country VALUES (92, 'GUERNSEY', 'GGY', 'GG', 831);
INSERT INTO t_ref_country VALUES (93, 'GUINEA', 'GIN', 'GN', 324);
INSERT INTO t_ref_country VALUES (94, 'GUINEA-BISSAU', 'GNB', 'GW', 624);
INSERT INTO t_ref_country VALUES (95, 'GUYANA', 'GUY', 'GY', 328);
INSERT INTO t_ref_country VALUES (96, 'HAITI', 'HTI', 'HT', 332);
INSERT INTO t_ref_country VALUES (97, 'HEARD ISLAND AND MCDONALD ISLANDS', 'HMD', 'HM', 334);
INSERT INTO t_ref_country VALUES (98, 'HOLY SEE (VATICAN CITY STATE)', 'VAT', 'VA', 336);
INSERT INTO t_ref_country VALUES (99, 'HONDURAS', 'HND', 'HN', 340);
INSERT INTO t_ref_country VALUES (100, 'HONG KONG', 'HKG', 'HK', 344);
INSERT INTO t_ref_country VALUES (101, 'HUNGARY', 'HUN', 'HU', 348);
INSERT INTO t_ref_country VALUES (102, 'ICELAND', 'ISL', 'IS', 352);
INSERT INTO t_ref_country VALUES (103, 'INDIA', 'IND', 'IN', 356);
INSERT INTO t_ref_country VALUES (104, 'INDONESIA', 'IDN', 'ID', 360);
INSERT INTO t_ref_country VALUES (105, 'IRAN (ISLAMIC REPUBLIC OF)', 'IRN', 'IR', 364);
INSERT INTO t_ref_country VALUES (106, 'IRAQ', 'IRQ', 'IQ', 368);
INSERT INTO t_ref_country VALUES (107, 'IRELAND', 'IRL', 'IE', 372);
INSERT INTO t_ref_country VALUES (108, 'ISLE OF MAN', 'IMM', 'IM', 833);
INSERT INTO t_ref_country VALUES (109, 'ISRAEL', 'ISR', 'IL', 376);
INSERT INTO t_ref_country VALUES (110, 'ITALY', 'ITA', 'IT', 380);
INSERT INTO t_ref_country VALUES (111, 'JAMAICA', 'JAM', 'JM', 388);
INSERT INTO t_ref_country VALUES (112, 'JAPAN', 'JPN', 'JP', 392);
INSERT INTO t_ref_country VALUES (113, 'JERSEY', 'JEY', 'JE', 832);
INSERT INTO t_ref_country VALUES (114, 'JORDAN', 'JOR', 'JO', 400);
INSERT INTO t_ref_country VALUES (115, 'KAZAKHSTAN', 'KAZ', 'KZ', 398);
INSERT INTO t_ref_country VALUES (116, 'KENYA', 'KEN', 'KE', 404);
INSERT INTO t_ref_country VALUES (117, 'KIRIBATI', 'KIR', 'KI', 296);
INSERT INTO t_ref_country VALUES (123, 'LATVIA', 'LVA', 'LV', 428);
INSERT INTO t_ref_country VALUES (124, 'LEBANON', 'LBN', 'LB', 422);
INSERT INTO t_ref_country VALUES (125, 'LESOTHO', 'LSO', 'LS', 426);
INSERT INTO t_ref_country VALUES (126, 'LIBERIA', 'LBR', 'LR', 430);
INSERT INTO t_ref_country VALUES (127, 'LIBYA', 'LBY', 'LY', 434);
INSERT INTO t_ref_country VALUES (128, 'LIECHTENSTEIN', 'LIE', 'LI', 438);
INSERT INTO t_ref_country VALUES (129, 'LITHUANIA', 'LTU', 'LT', 440);
INSERT INTO t_ref_country VALUES (130, 'LUXEMBOURG', 'LUX', 'LU', 442);
INSERT INTO t_ref_country VALUES (131, 'MACAO', 'MAC', 'MO', 446);
INSERT INTO t_ref_country VALUES (132, 'MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF', 'MKD', 'MK', 807);
INSERT INTO t_ref_country VALUES (133, 'MADAGASCAR', 'MDG', 'MG', 450);
INSERT INTO t_ref_country VALUES (134, 'MALAWI', 'MWI', 'MW', 454);
INSERT INTO t_ref_country VALUES (135, 'MALAYSIA', 'MYS', 'MY', 458);
INSERT INTO t_ref_country VALUES (136, 'MALDIVES', 'MDV', 'MV', 462);
INSERT INTO t_ref_country VALUES (137, 'MALI', 'MLI', 'ML', 466);
INSERT INTO t_ref_country VALUES (138, 'MALTA', 'MLT', 'MT', 470);
INSERT INTO t_ref_country VALUES (139, 'MARSHALL ISLANDS', 'MHL', 'MH', 584);
INSERT INTO t_ref_country VALUES (140, 'MARTINIQUE', 'MTQ', 'MQ', 474);
INSERT INTO t_ref_country VALUES (141, 'MAURITANIA', 'MRT', 'MR', 478);
INSERT INTO t_ref_country VALUES (142, 'MAURITIUS', 'MUS', 'MU', 480);
INSERT INTO t_ref_country VALUES (143, 'MAYOTTE', 'MYT', 'YT', 175);
INSERT INTO t_ref_country VALUES (144, 'MEXICO', 'MEX', 'MX', 484);
INSERT INTO t_ref_country VALUES (145, 'MICRONESIA, FEDERATED STATES OF', 'FSM', 'FM', 583);
INSERT INTO t_ref_country VALUES (146, 'MOLDOVA, REPUBLIC OF', 'MDA', 'MD', 498);
INSERT INTO t_ref_country VALUES (147, 'MONACO', 'MCO', 'MC', 492);
INSERT INTO t_ref_country VALUES (148, 'MONGOLIA', 'MNG', 'MN', 496);
INSERT INTO t_ref_country VALUES (149, 'MONTENEGRO', 'MNE', 'ME', 499);
INSERT INTO t_ref_country VALUES (150, 'MONTSERRAT', 'MSR', 'MS', 500);
INSERT INTO t_ref_country VALUES (151, 'MOROCCO', 'MAR', 'MA', 504);
INSERT INTO t_ref_country VALUES (152, 'MOZAMBIQUE', 'MOZ', 'MZ', 508);
INSERT INTO t_ref_country VALUES (153, 'MYANMAR', 'MMR', 'MM', 104);
INSERT INTO t_ref_country VALUES (154, 'NAMIBIA', 'NAM', 'NA', 516);
INSERT INTO t_ref_country VALUES (155, 'NAURU', 'NRU', 'NR', 520);
INSERT INTO t_ref_country VALUES (156, 'NEPAL', 'NPL', 'NP', 524);
INSERT INTO t_ref_country VALUES (157, 'NETHERLANDS', 'NLD', 'NL', 528);
INSERT INTO t_ref_country VALUES (158, 'NEW CALEDONIA', 'NCL', 'NC', 540);
INSERT INTO t_ref_country VALUES (159, 'NEW ZEALAND', 'NZL', 'NZ', 554);
INSERT INTO t_ref_country VALUES (160, 'NICARAGUA', 'NIC', 'NI', 558);
INSERT INTO t_ref_country VALUES (161, 'NIGER', 'NER', 'NE', 562);
INSERT INTO t_ref_country VALUES (162, 'NIGERIA', 'NGA', 'NG', 566);
INSERT INTO t_ref_country VALUES (163, 'NIUE', 'NIU', 'NU', 570);
INSERT INTO t_ref_country VALUES (164, 'NORFOLK ISLAND', 'NFK', 'NF', 574);
INSERT INTO t_ref_country VALUES (165, 'NORTHERN MARIANA ISLANDS', 'MNP', 'MP', 580);
INSERT INTO t_ref_country VALUES (166, 'NORWAY', 'NOR', 'NO', 578);
INSERT INTO t_ref_country VALUES (167, 'OMAN', 'OMN', 'OM', 512);
INSERT INTO t_ref_country VALUES (168, 'PAKISTAN', 'PAK', 'PK', 586);
INSERT INTO t_ref_country VALUES (169, 'PALAU', 'PLW', 'PW', 585);
INSERT INTO t_ref_country VALUES (170, 'PALESTINIAN TERRITORY, OCCUPIED', 'PSE', 'PS', 275);
INSERT INTO t_ref_country VALUES (171, 'PANAMA', 'PAN', 'PA', 591);
INSERT INTO t_ref_country VALUES (172, 'PAPUA NEW GUINEA', 'PNG', 'PG', 598);
INSERT INTO t_ref_country VALUES (173, 'PARAGUAY', 'PRY', 'PY', 600);
INSERT INTO t_ref_country VALUES (174, 'PERU', 'PER', 'PE', 604);
INSERT INTO t_ref_country VALUES (175, 'PHILIPPINES', 'PHL', 'PH', 608);
INSERT INTO t_ref_country VALUES (176, 'PITCAIRN', 'PCN', 'PN', 612);
INSERT INTO t_ref_country VALUES (177, 'POLAND', 'POL', 'PL', 616);
INSERT INTO t_ref_country VALUES (178, 'PORTUGAL', 'PRT', 'PT', 620);
INSERT INTO t_ref_country VALUES (179, 'PUERTO RICO', 'PRI', 'PR', 630);
INSERT INTO t_ref_country VALUES (180, 'QATAR', 'QAT', 'QA', 634);
INSERT INTO t_ref_country VALUES (181, 'REUNION', 'REU', 'RE', 638);
INSERT INTO t_ref_country VALUES (182, 'ROMANIA', 'ROU', 'RO', 642);
INSERT INTO t_ref_country VALUES (183, 'RUSSIAN FEDERATION', 'RUS', 'RU', 643);
INSERT INTO t_ref_country VALUES (184, 'RWANDA', 'RWA', 'RW', 646);
INSERT INTO t_ref_country VALUES (185, 'SAINT BARTHELEMY', 'BLM', 'BL', 652);
INSERT INTO t_ref_country VALUES (186, 'SAINT HELENA, ASCENSION AND TRISTAN DA CUNHA', 'SHN', 'SH', 654);
INSERT INTO t_ref_country VALUES (187, 'SAINT KITTS AND NEVIS', 'KNA', 'KN', 659);
INSERT INTO t_ref_country VALUES (188, 'SAINT LUCIA', 'LCA', 'LC', 662);
INSERT INTO t_ref_country VALUES (189, 'SAINT MARTIN (FRENCH PART)', 'MAF', 'MT', 663);
INSERT INTO t_ref_country VALUES (190, 'SAINT PIERRE AND MIQUELON', 'SPM', 'PM', 666);
INSERT INTO t_ref_country VALUES (191, 'SAINT VINCENT AND THE GRENADINES', 'VCT', 'VC', 670);
INSERT INTO t_ref_country VALUES (192, 'SAMOA', 'WSM', 'WS', 882);
INSERT INTO t_ref_country VALUES (193, 'SAN MARINO', 'SMR', 'SM', 674);
INSERT INTO t_ref_country VALUES (194, 'SAO TOME AND PRINCIPE', 'STP', 'ST', 678);
INSERT INTO t_ref_country VALUES (195, 'SAUDI ARABIA', 'SAU', 'SA', 682);
INSERT INTO t_ref_country VALUES (196, 'SENEGAL', 'SEN', 'SN', 686);
INSERT INTO t_ref_country VALUES (197, 'SERBIA', 'SRB', 'RS', 688);
INSERT INTO t_ref_country VALUES (198, 'SEYCHELLES', 'SYC', 'SC', 690);
INSERT INTO t_ref_country VALUES (199, 'SIERRA LEONE', 'SLE', 'SL', 694);
INSERT INTO t_ref_country VALUES (200, 'SINGAPORE', 'SGP', 'SG', 702);
INSERT INTO t_ref_country VALUES (201, 'SINT MAARTEN (DUTCH PART)', 'SXM', 'SX', 534);
INSERT INTO t_ref_country VALUES (202, 'SLOVAKIA', 'SVK', 'SK', 703);
INSERT INTO t_ref_country VALUES (203, 'SLOVENIA', 'SVN', 'SI', 705);
INSERT INTO t_ref_country VALUES (204, 'SOLOMON ISLANDS', 'SLB', 'SB', 90);
INSERT INTO t_ref_country VALUES (205, 'SOMALIA', 'SOM', 'SO', 706);
INSERT INTO t_ref_country VALUES (206, 'SOUTH AFRICA', 'ZAF', 'ZA', 710);
INSERT INTO t_ref_country VALUES (207, 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS', 'SGS', 'GS', 239);
INSERT INTO t_ref_country VALUES (208, 'SOUTH SUDAN', 'SSD', 'SS', 728);
INSERT INTO t_ref_country VALUES (209, 'SPAIN', 'ESP', 'ES', 724);
INSERT INTO t_ref_country VALUES (210, 'SRI LANKA', 'LKA', 'LK', 144);
INSERT INTO t_ref_country VALUES (211, 'SUDAN', 'SDN', 'SD', 729);
INSERT INTO t_ref_country VALUES (212, 'SURINAME', 'SUR', 'SR', 740);
INSERT INTO t_ref_country VALUES (213, 'SVALBARD AND JAN MAYEN', 'SJM', 'SJ', 744);
INSERT INTO t_ref_country VALUES (214, 'SWAZILAND', 'SWZ', 'SZ', 748);
INSERT INTO t_ref_country VALUES (215, 'SWEDEN', 'SWE', 'SE', 752);
INSERT INTO t_ref_country VALUES (216, 'SWITZERLAND', 'CHE', 'CH', 756);
INSERT INTO t_ref_country VALUES (217, 'SYRIAN ARAB REPUBLIC', 'SYR', 'SY', 760);
INSERT INTO t_ref_country VALUES (218, 'TAIWAN, PROVINCE OF CHINA', 'TWN', 'TW', 158);
INSERT INTO t_ref_country VALUES (219, 'TAJIKISTAN', 'TJK', 'TJ', 762);
INSERT INTO t_ref_country VALUES (220, 'TANZANIA, UNITED REPUBLIC OF', 'TZA', 'TZ', 834);
INSERT INTO t_ref_country VALUES (221, 'THAILAND', 'THA', 'TH', 764);
INSERT INTO t_ref_country VALUES (222, 'TIMOR-LESTE', 'TLS', 'TL', 626);
INSERT INTO t_ref_country VALUES (223, 'TOGO', 'TGO', 'TG', 768);
INSERT INTO t_ref_country VALUES (224, 'TOKELAU', 'TKL', 'TK', 772);
INSERT INTO t_ref_country VALUES (225, 'TONGA', 'TON', 'TO', 776);
INSERT INTO t_ref_country VALUES (226, 'TRINIDAD AND TOBAGO', 'TTO', 'TT', 780);
INSERT INTO t_ref_country VALUES (227, 'TUNISIA', 'TUN', 'TN', 788);
INSERT INTO t_ref_country VALUES (228, 'TURKEY', 'TUR', 'TR', 792);
INSERT INTO t_ref_country VALUES (229, 'TURKMENISTAN', 'TKM', 'TM', 795);
INSERT INTO t_ref_country VALUES (230, 'TURKS AND CAICOS ISLANDS', 'TCA', 'TC', 796);
INSERT INTO t_ref_country VALUES (231, 'TUVALU', 'TUV', 'TV', 798);
INSERT INTO t_ref_country VALUES (232, 'UGANDA', 'UGA', 'UG', 800);
INSERT INTO t_ref_country VALUES (233, 'UKRAINE', 'UKR', 'UA', 804);
INSERT INTO t_ref_country VALUES (234, 'UNITED ARAB EMIRATES', 'ARE', 'AE', 784);
INSERT INTO t_ref_country VALUES (235, 'UNITED KINGDOM', 'GBR', 'GB', 826);
INSERT INTO t_ref_country VALUES (236, 'UNITED STATES', 'USA', 'US', 840);
INSERT INTO t_ref_country VALUES (237, 'UNITED STATES MINOR OUTLYING ISLANDS', 'UMI', 'UM', 581);
INSERT INTO t_ref_country VALUES (238, 'URUGUAY', 'URY', 'UY', 858);
INSERT INTO t_ref_country VALUES (239, 'UZBEKISTAN', 'UZB', 'UZ', 860);
INSERT INTO t_ref_country VALUES (240, 'VANUATU', 'VUT', 'VU', 548);
INSERT INTO t_ref_country VALUES (241, 'VATICAN CITY STATE (HOLY SEE)', 'VAT', 'VA', 336);
INSERT INTO t_ref_country VALUES (242, 'VENEZUELA, BOLIVARIAN REPUBLIC OF', 'VEN', 'VE', 862);
INSERT INTO t_ref_country VALUES (243, 'VIET NAM', 'VNM', 'VN', 704);
INSERT INTO t_ref_country VALUES (244, 'VIRGIN ISLANDS (BRITISH)', 'VGB', 'VG', 92);
INSERT INTO t_ref_country VALUES (245, 'VIRGIN ISLANDS (U.S.)', 'VIR', 'VI', 850);
INSERT INTO t_ref_country VALUES (246, 'WALLIS AND FUTUNA', 'WLF', 'WF', 876);
INSERT INTO t_ref_country VALUES (247, 'WESTERN SAHARA', 'ESH', 'EH', 732);
INSERT INTO t_ref_country VALUES (248, 'YEMEN', 'YEM', 'YE', 887);
INSERT INTO t_ref_country VALUES (249, 'YUGOSLAVIA', 'YUG', 'YU', 891);
INSERT INTO t_ref_country VALUES (250, 'ZAMBIA', 'ZMB', 'ZM', 894);
INSERT INTO t_ref_country VALUES (251, 'ZIMBABWE', 'ZWE', 'ZW', 716);


--
-- TOC entry 2030 (class 0 OID 108636)
-- Dependencies: 1612
-- Data for Name: t_ref_culture_mode; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_culture_mode VALUES (1, 'garden');
INSERT INTO t_ref_culture_mode VALUES (2, 'balcony');
INSERT INTO t_ref_culture_mode VALUES (3, 'terrace');


--
-- TOC entry 2031 (class 0 OID 108639)
-- Dependencies: 1613
-- Data for Name: t_ref_culture_type; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_culture_type VALUES (1, 'regular');
INSERT INTO t_ref_culture_type VALUES (2, 'bio');
INSERT INTO t_ref_culture_type VALUES (3, 'gmo');


--
-- TOC entry 2032 (class 0 OID 108642)
-- Dependencies: 1614
-- Data for Name: t_ref_evaluation_note; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_evaluation_note VALUES (1, 'very bad');
INSERT INTO t_ref_evaluation_note VALUES (2, 'bad');
INSERT INTO t_ref_evaluation_note VALUES (3, 'normal');
INSERT INTO t_ref_evaluation_note VALUES (4, 'good');
INSERT INTO t_ref_evaluation_note VALUES (5, 'very good');


--
-- TOC entry 2033 (class 0 OID 108645)
-- Dependencies: 1615
-- Data for Name: t_ref_evaluation_status; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_evaluation_status VALUES (1, 'setted');
INSERT INTO t_ref_evaluation_status VALUES (2, 'removed');
INSERT INTO t_ref_evaluation_status VALUES (3, 'archived');


--
-- TOC entry 2034 (class 0 OID 108648)
-- Dependencies: 1616
-- Data for Name: t_ref_exchange_type; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_exchange_type VALUES (1, 'shares');
INSERT INTO t_ref_exchange_type VALUES (2, 'gives');
INSERT INTO t_ref_exchange_type VALUES (3, 'sells');


--
-- TOC entry 2035 (class 0 OID 108651)
-- Dependencies: 1617
-- Data for Name: t_ref_month; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_month VALUES (1, 'january');
INSERT INTO t_ref_month VALUES (2, 'february');
INSERT INTO t_ref_month VALUES (3, 'march');
INSERT INTO t_ref_month VALUES (4, 'april');
INSERT INTO t_ref_month VALUES (5, 'may');
INSERT INTO t_ref_month VALUES (6, 'june');
INSERT INTO t_ref_month VALUES (7, 'july');
INSERT INTO t_ref_month VALUES (8, 'august');
INSERT INTO t_ref_month VALUES (9, 'september');
INSERT INTO t_ref_month VALUES (10, 'october');
INSERT INTO t_ref_month VALUES (11, 'november');
INSERT INTO t_ref_month VALUES (12, 'december');


--
-- TOC entry 2036 (class 0 OID 108654)
-- Dependencies: 1618
-- Data for Name: t_ref_product; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2037 (class 0 OID 108662)
-- Dependencies: 1620
-- Data for Name: t_ref_product_request_status; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_product_request_status VALUES (1, 'request');
INSERT INTO t_ref_product_request_status VALUES (2, 'pending');
INSERT INTO t_ref_product_request_status VALUES (3, 'accepted');
INSERT INTO t_ref_product_request_status VALUES (4, 'refused');


--
-- TOC entry 2038 (class 0 OID 108665)
-- Dependencies: 1621
-- Data for Name: t_ref_product_type; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_product_type VALUES (1, 'fruit');
INSERT INTO t_ref_product_type VALUES (2, 'vegetable');


--
-- TOC entry 2039 (class 0 OID 108668)
-- Dependencies: 1622
-- Data for Name: t_ref_relation_status; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_relation_status VALUES (1, 'request');
INSERT INTO t_ref_relation_status VALUES (2, 'pending');
INSERT INTO t_ref_relation_status VALUES (3, 'validated');
INSERT INTO t_ref_relation_status VALUES (4, 'refused');
INSERT INTO t_ref_relation_status VALUES (5, 'cancel');


--
-- TOC entry 2040 (class 0 OID 108671)
-- Dependencies: 1623
-- Data for Name: t_ref_relation_type; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_relation_type VALUES (1, 'freeveggie friend');
INSERT INTO t_ref_relation_type VALUES (2, 'friend');
INSERT INTO t_ref_relation_type VALUES (3, 'neighbors');


--
-- TOC entry 2041 (class 0 OID 108674)
-- Dependencies: 1624
-- Data for Name: t_ref_user_role; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO t_ref_user_role VALUES (1, 'user');
INSERT INTO t_ref_user_role VALUES (2, 'manager');
INSERT INTO t_ref_user_role VALUES (3, 'admin');
INSERT INTO t_ref_user_role VALUES (4, 'super admin');


--
-- TOC entry 2042 (class 0 OID 108677)
-- Dependencies: 1625
-- Data for Name: t_ref_user_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO t_ref_user_status VALUES (1, 'new');
INSERT INTO t_ref_user_status VALUES (2, 'valided');
INSERT INTO t_ref_user_status VALUES (3, 'blacklisted');
INSERT INTO t_ref_user_status VALUES (4, 'deleted');
INSERT INTO t_ref_user_status VALUES (5, 'archive');


--
-- TOC entry 2043 (class 0 OID 108680)
-- Dependencies: 1626
-- Data for Name: t_relationship; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2044 (class 0 OID 108686)
-- Dependencies: 1627
-- Data for Name: t_request_product; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2045 (class 0 OID 108692)
-- Dependencies: 1628
-- Data for Name: t_seed_calendar; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2046 (class 0 OID 108695)
-- Dependencies: 1629
-- Data for Name: t_user; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2047 (class 0 OID 108701)
-- Dependencies: 1630
-- Data for Name: t_user_subscription; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 1913 (class 2606 OID 108710)
-- Dependencies: 1601 1601
-- Name: t_address_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_address
    ADD CONSTRAINT t_address_pkey PRIMARY KEY (adr_id);


--
-- TOC entry 1915 (class 2606 OID 108712)
-- Dependencies: 1602 1602
-- Name: t_comment_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT t_comment_garden_pkey PRIMARY KEY (cmt_grd_id);


--
-- TOC entry 1917 (class 2606 OID 108714)
-- Dependencies: 1603 1603
-- Name: t_comment_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT t_comment_product_pkey PRIMARY KEY (cmt_prd_id);


--
-- TOC entry 1919 (class 2606 OID 108716)
-- Dependencies: 1604 1604
-- Name: t_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_garden
    ADD CONSTRAINT t_garden_pkey PRIMARY KEY (grd_id);


--
-- TOC entry 1921 (class 2606 OID 108718)
-- Dependencies: 1605 1605
-- Name: t_like_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_like_garden
    ADD CONSTRAINT t_like_garden_pkey PRIMARY KEY (lik_grd_id);


--
-- TOC entry 1923 (class 2606 OID 108720)
-- Dependencies: 1606 1606
-- Name: t_like_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_like_product
    ADD CONSTRAINT t_like_product_pkey PRIMARY KEY (lik_prd_id);


--
-- TOC entry 1925 (class 2606 OID 108722)
-- Dependencies: 1607 1607
-- Name: t_note_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_note_garden
    ADD CONSTRAINT t_note_garden_pkey PRIMARY KEY (nt_grd_id);


--
-- TOC entry 1927 (class 2606 OID 108724)
-- Dependencies: 1608 1608
-- Name: t_note_produit_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_note_product
    ADD CONSTRAINT t_note_produit_pkey PRIMARY KEY (nt_prd_id);


--
-- TOC entry 1929 (class 2606 OID 108726)
-- Dependencies: 1609 1609
-- Name: t_produit_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT t_produit_pkey PRIMARY KEY (prd_id);


--
-- TOC entry 1931 (class 2606 OID 108728)
-- Dependencies: 1610 1610
-- Name: t_profil_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_profil
    ADD CONSTRAINT t_profil_pkey PRIMARY KEY (prf_id);


--
-- TOC entry 1933 (class 2606 OID 108730)
-- Dependencies: 1611 1611 1611
-- Name: t_reap_calendar_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_reap_calendar
    ADD CONSTRAINT t_reap_calendar_pkey PRIMARY KEY (prd_ref_id, mnt_id);


--
-- TOC entry 1975 (class 2606 OID 108994)
-- Dependencies: 1632 1632
-- Name: t_ref_country_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_country
    ADD CONSTRAINT t_ref_country_pkey PRIMARY KEY (cnt_ref_id);


--
-- TOC entry 1935 (class 2606 OID 108732)
-- Dependencies: 1612 1612
-- Name: t_ref_culture_mode_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_culture_mode
    ADD CONSTRAINT t_ref_culture_mode_pkey PRIMARY KEY (cmd_id);


--
-- TOC entry 1937 (class 2606 OID 108734)
-- Dependencies: 1613 1613
-- Name: t_ref_culture_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_culture_type
    ADD CONSTRAINT t_ref_culture_type_pkey PRIMARY KEY (type_id);


--
-- TOC entry 1939 (class 2606 OID 108736)
-- Dependencies: 1614 1614
-- Name: t_ref_evaluation_note_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_evaluation_note
    ADD CONSTRAINT t_ref_evaluation_note_pkey PRIMARY KEY ("NOTE_ID");


--
-- TOC entry 1941 (class 2606 OID 108738)
-- Dependencies: 1615 1615
-- Name: t_ref_evaluation_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_evaluation_status
    ADD CONSTRAINT t_ref_evaluation_status_pkey PRIMARY KEY ("STS_ID");


--
-- TOC entry 1943 (class 2606 OID 108740)
-- Dependencies: 1616 1616
-- Name: t_ref_exchange_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_exchange_type
    ADD CONSTRAINT t_ref_exchange_type_pkey PRIMARY KEY (chg_id);


--
-- TOC entry 1945 (class 2606 OID 108742)
-- Dependencies: 1617 1617
-- Name: t_ref_month_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_month
    ADD CONSTRAINT t_ref_month_pkey PRIMARY KEY (mnt_id);


--
-- TOC entry 1947 (class 2606 OID 108744)
-- Dependencies: 1618 1618
-- Name: t_ref_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_product
    ADD CONSTRAINT t_ref_product_pkey PRIMARY KEY (prd_ref_id);


--
-- TOC entry 1949 (class 2606 OID 108746)
-- Dependencies: 1620 1620
-- Name: t_ref_product_request_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_product_request_status
    ADD CONSTRAINT t_ref_product_request_status_pkey PRIMARY KEY ("STS_ID");


--
-- TOC entry 1951 (class 2606 OID 108748)
-- Dependencies: 1621 1621
-- Name: t_ref_product_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_product_type
    ADD CONSTRAINT t_ref_product_type_pkey PRIMARY KEY (ptp_id);


--
-- TOC entry 1953 (class 2606 OID 108750)
-- Dependencies: 1622 1622
-- Name: t_ref_relation_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_relation_status
    ADD CONSTRAINT t_ref_relation_status_pkey PRIMARY KEY (sts_id);


--
-- TOC entry 1955 (class 2606 OID 108752)
-- Dependencies: 1623 1623
-- Name: t_ref_relation_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_relation_type
    ADD CONSTRAINT t_ref_relation_type_pkey PRIMARY KEY (type_id);


--
-- TOC entry 1957 (class 2606 OID 108754)
-- Dependencies: 1624 1624
-- Name: t_ref_user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_ref_user_role
    ADD CONSTRAINT t_ref_user_role_pkey PRIMARY KEY (role_id);


--
-- TOC entry 1959 (class 2606 OID 108756)
-- Dependencies: 1625 1625
-- Name: t_ref_user_status_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY t_ref_user_status
    ADD CONSTRAINT t_ref_user_status_pk PRIMARY KEY (sts_id);


--
-- TOC entry 1961 (class 2606 OID 108758)
-- Dependencies: 1626 1626
-- Name: t_relationship_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT t_relationship_pkey PRIMARY KEY (rlt_id);


--
-- TOC entry 1965 (class 2606 OID 108760)
-- Dependencies: 1627 1627
-- Name: t_request_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_request_product
    ADD CONSTRAINT t_request_product_pkey PRIMARY KEY (rqt_id);


--
-- TOC entry 1967 (class 2606 OID 108762)
-- Dependencies: 1628 1628 1628
-- Name: t_seed_month_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_seed_calendar
    ADD CONSTRAINT t_seed_month_pkey PRIMARY KEY (prd_ref_id, mnt_id);


--
-- TOC entry 1969 (class 2606 OID 108764)
-- Dependencies: 1629 1629
-- Name: t_user_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 1973 (class 2606 OID 108766)
-- Dependencies: 1630 1630
-- Name: t_user_subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_user_subscription
    ADD CONSTRAINT t_user_subscription_pkey PRIMARY KEY (sbr_id);


--
-- TOC entry 1963 (class 2606 OID 108768)
-- Dependencies: 1626 1626 1626
-- Name: unq_t_relationship_0; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT unq_t_relationship_0 UNIQUE (user_id_one, user_id_two);


--
-- TOC entry 1971 (class 2606 OID 108770)
-- Dependencies: 1629 1629
-- Name: unq_t_user_0; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT unq_t_user_0 UNIQUE (username);


--
-- TOC entry 1976 (class 2606 OID 108771)
-- Dependencies: 1602 1604 1918
-- Name: fk_t_comment_garden_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT fk_t_comment_garden_grd_id FOREIGN KEY (grd_id) REFERENCES t_garden(grd_id);


--
-- TOC entry 1977 (class 2606 OID 108776)
-- Dependencies: 1602 1615 1940
-- Name: fk_t_comment_garden_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT fk_t_comment_garden_sts_id FOREIGN KEY (sts_id) REFERENCES t_ref_evaluation_status("STS_ID");


--
-- TOC entry 1978 (class 2606 OID 108781)
-- Dependencies: 1968 1629 1602
-- Name: fk_t_comment_garden_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT fk_t_comment_garden_user_id FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- TOC entry 1979 (class 2606 OID 108786)
-- Dependencies: 1928 1609 1603
-- Name: fk_t_comment_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT fk_t_comment_product_prd_id FOREIGN KEY (prd_id) REFERENCES t_product(prd_id);


--
-- TOC entry 1980 (class 2606 OID 108791)
-- Dependencies: 1940 1603 1615
-- Name: fk_t_comment_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT fk_t_comment_product_sts_id FOREIGN KEY (sts_id) REFERENCES t_ref_evaluation_status("STS_ID");


--
-- TOC entry 1981 (class 2606 OID 108796)
-- Dependencies: 1603 1629 1968
-- Name: fk_t_comment_product_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT fk_t_comment_product_user_id FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- TOC entry 1982 (class 2606 OID 108801)
-- Dependencies: 1601 1604 1912
-- Name: fk_t_garden_adr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_garden
    ADD CONSTRAINT fk_t_garden_adr_id FOREIGN KEY (adr_id) REFERENCES t_address(adr_id);


--
-- TOC entry 1983 (class 2606 OID 108806)
-- Dependencies: 1629 1604 1968
-- Name: fk_t_garden_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_garden
    ADD CONSTRAINT fk_t_garden_grd_id FOREIGN KEY (grd_id) REFERENCES t_user(user_id);


--
-- TOC entry 1984 (class 2606 OID 108811)
-- Dependencies: 1605 1604 1918
-- Name: fk_t_like_garden_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_like_garden
    ADD CONSTRAINT fk_t_like_garden_grd_id FOREIGN KEY (grd_id) REFERENCES t_garden(grd_id);


--
-- TOC entry 1985 (class 2606 OID 108816)
-- Dependencies: 1605 1615 1940
-- Name: fk_t_like_garden_sts_Ã¨d; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_like_garden
    ADD CONSTRAINT "fk_t_like_garden_sts_Ã¨d" FOREIGN KEY (sts_id) REFERENCES t_ref_evaluation_status("STS_ID");


--
-- TOC entry 1986 (class 2606 OID 108821)
-- Dependencies: 1629 1968 1605
-- Name: fk_t_like_garden_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_like_garden
    ADD CONSTRAINT fk_t_like_garden_user_id FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- TOC entry 1987 (class 2606 OID 108826)
-- Dependencies: 1606 1609 1928
-- Name: fk_t_like_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_like_product
    ADD CONSTRAINT fk_t_like_product_prd_id FOREIGN KEY (prd_id) REFERENCES t_product(prd_id);


--
-- TOC entry 1988 (class 2606 OID 108831)
-- Dependencies: 1940 1606 1615
-- Name: fk_t_like_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_like_product
    ADD CONSTRAINT fk_t_like_product_sts_id FOREIGN KEY (sts_id) REFERENCES t_ref_evaluation_status("STS_ID");


--
-- TOC entry 1989 (class 2606 OID 108836)
-- Dependencies: 1629 1606 1968
-- Name: fk_t_like_product_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_like_product
    ADD CONSTRAINT fk_t_like_product_user_id FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- TOC entry 1990 (class 2606 OID 108841)
-- Dependencies: 1604 1607 1918
-- Name: fk_t_note_garden_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_note_garden
    ADD CONSTRAINT fk_t_note_garden_grd_id FOREIGN KEY (grd_id) REFERENCES t_garden(grd_id);


--
-- TOC entry 1991 (class 2606 OID 108846)
-- Dependencies: 1614 1607 1938
-- Name: fk_t_note_garden_note_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_note_garden
    ADD CONSTRAINT fk_t_note_garden_note_id FOREIGN KEY (note_id) REFERENCES t_ref_evaluation_note("NOTE_ID");


--
-- TOC entry 1992 (class 2606 OID 108851)
-- Dependencies: 1940 1615 1607
-- Name: fk_t_note_garden_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_note_garden
    ADD CONSTRAINT fk_t_note_garden_sts_id FOREIGN KEY (sts_id) REFERENCES t_ref_evaluation_status("STS_ID");


--
-- TOC entry 1993 (class 2606 OID 108856)
-- Dependencies: 1629 1607 1968
-- Name: fk_t_note_garden_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_note_garden
    ADD CONSTRAINT fk_t_note_garden_user_id FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- TOC entry 1994 (class 2606 OID 108861)
-- Dependencies: 1938 1608 1614
-- Name: fk_t_note_product_note_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_note_product
    ADD CONSTRAINT fk_t_note_product_note_id FOREIGN KEY (note_id) REFERENCES t_ref_evaluation_note("NOTE_ID");


--
-- TOC entry 1995 (class 2606 OID 108866)
-- Dependencies: 1940 1608 1615
-- Name: fk_t_note_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_note_product
    ADD CONSTRAINT fk_t_note_product_sts_id FOREIGN KEY (sts_id) REFERENCES t_ref_evaluation_status("STS_ID");


--
-- TOC entry 1996 (class 2606 OID 108871)
-- Dependencies: 1968 1629 1608
-- Name: fk_t_note_produit_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_note_product
    ADD CONSTRAINT fk_t_note_produit_user_id FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- TOC entry 1998 (class 2606 OID 108876)
-- Dependencies: 1942 1609 1616
-- Name: fk_t_product_chg_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_product_chg_id FOREIGN KEY (chg_id) REFERENCES t_ref_exchange_type(chg_id);


--
-- TOC entry 1999 (class 2606 OID 108881)
-- Dependencies: 1612 1609 1934
-- Name: fk_t_product_cmd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_product_cmd_id FOREIGN KEY (cmd_id) REFERENCES t_ref_culture_mode(cmd_id);


--
-- TOC entry 1997 (class 2606 OID 108886)
-- Dependencies: 1608 1609 1928
-- Name: fk_t_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_note_product
    ADD CONSTRAINT fk_t_product_prd_id FOREIGN KEY (prd_id) REFERENCES t_product(prd_id);


--
-- TOC entry 2000 (class 2606 OID 108891)
-- Dependencies: 1613 1609 1936
-- Name: fk_t_product_type_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_product_type_id FOREIGN KEY (type_id) REFERENCES t_ref_culture_type(type_id);


--
-- TOC entry 2001 (class 2606 OID 108896)
-- Dependencies: 1609 1918 1604
-- Name: fk_t_produit_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_produit_grd_id FOREIGN KEY (grd_id) REFERENCES t_garden(grd_id);


--
-- TOC entry 2002 (class 2606 OID 108901)
-- Dependencies: 1618 1946 1609
-- Name: fk_t_produit_prd_ref_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_produit_prd_ref_id FOREIGN KEY (prd_ref_id) REFERENCES t_ref_product(prd_ref_id);


--
-- TOC entry 2003 (class 2606 OID 108906)
-- Dependencies: 1611 1617 1944
-- Name: fk_t_reap_calendar_mnt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_reap_calendar
    ADD CONSTRAINT fk_t_reap_calendar_mnt_id FOREIGN KEY (mnt_id) REFERENCES t_ref_month(mnt_id);


--
-- TOC entry 2004 (class 2606 OID 108911)
-- Dependencies: 1946 1611 1618
-- Name: fk_t_reap_calendar_prd_ref_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_reap_calendar
    ADD CONSTRAINT fk_t_reap_calendar_prd_ref_id FOREIGN KEY (prd_ref_id) REFERENCES t_ref_product(prd_ref_id);


--
-- TOC entry 2005 (class 2606 OID 108916)
-- Dependencies: 1621 1618 1950
-- Name: fk_t_ref_product_ptp_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_ref_product
    ADD CONSTRAINT fk_t_ref_product_ptp_id FOREIGN KEY (ptp_id) REFERENCES t_ref_product_type(ptp_id);


--
-- TOC entry 2006 (class 2606 OID 108921)
-- Dependencies: 1622 1626 1952
-- Name: fk_t_relationship_sts_d; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT fk_t_relationship_sts_d FOREIGN KEY (sts_id) REFERENCES t_ref_relation_status(sts_id);


--
-- TOC entry 2007 (class 2606 OID 108926)
-- Dependencies: 1626 1623 1954
-- Name: fk_t_relationship_type_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT fk_t_relationship_type_id FOREIGN KEY (type_id) REFERENCES t_ref_relation_type(type_id);


--
-- TOC entry 2008 (class 2606 OID 108931)
-- Dependencies: 1626 1629 1968
-- Name: fk_t_relationship_user_id_one; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT fk_t_relationship_user_id_one FOREIGN KEY (user_id_one) REFERENCES t_user(user_id);


--
-- TOC entry 2009 (class 2606 OID 108936)
-- Dependencies: 1626 1629 1968
-- Name: fk_t_relationship_user_id_two; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT fk_t_relationship_user_id_two FOREIGN KEY (user_id_two) REFERENCES t_user(user_id);


--
-- TOC entry 2010 (class 2606 OID 108941)
-- Dependencies: 1627 1609 1928
-- Name: fk_t_request_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_request_product
    ADD CONSTRAINT fk_t_request_product_prd_id FOREIGN KEY (prd_id) REFERENCES t_product(prd_id);


--
-- TOC entry 2011 (class 2606 OID 108946)
-- Dependencies: 1627 1620 1948
-- Name: fk_t_request_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_request_product
    ADD CONSTRAINT fk_t_request_product_sts_id FOREIGN KEY (sts_id) REFERENCES t_ref_product_request_status("STS_ID");


--
-- TOC entry 2012 (class 2606 OID 108951)
-- Dependencies: 1627 1629 1968
-- Name: fk_t_request_product_user_rqt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_request_product
    ADD CONSTRAINT fk_t_request_product_user_rqt_id FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- TOC entry 2013 (class 2606 OID 108956)
-- Dependencies: 1617 1944 1628
-- Name: fk_t_seed_month_mnt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_seed_calendar
    ADD CONSTRAINT fk_t_seed_month_mnt_id FOREIGN KEY (mnt_id) REFERENCES t_ref_month(mnt_id);


--
-- TOC entry 2014 (class 2606 OID 108961)
-- Dependencies: 1628 1946 1618
-- Name: fk_t_seed_month_prd_ref_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_seed_calendar
    ADD CONSTRAINT fk_t_seed_month_prd_ref_id FOREIGN KEY (prd_ref_id) REFERENCES t_ref_product(prd_ref_id);


--
-- TOC entry 2015 (class 2606 OID 108966)
-- Dependencies: 1912 1629 1601
-- Name: fk_t_user_adr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT fk_t_user_adr_id FOREIGN KEY (adr_id) REFERENCES t_address(adr_id);


--
-- TOC entry 2016 (class 2606 OID 108971)
-- Dependencies: 1629 1610 1930
-- Name: fk_t_user_prf_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT fk_t_user_prf_id FOREIGN KEY (prf_id) REFERENCES t_profil(prf_id);


--
-- TOC entry 2017 (class 2606 OID 108976)
-- Dependencies: 1629 1972 1630
-- Name: fk_t_user_sbs_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT fk_t_user_sbs_id FOREIGN KEY (sbs_id) REFERENCES t_user_subscription(sbr_id);


--
-- TOC entry 2018 (class 2606 OID 108981)
-- Dependencies: 1629 1625 1958
-- Name: fk_t_user_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT fk_t_user_sts_id FOREIGN KEY (sts_id) REFERENCES t_ref_user_status(sts_id);


--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-12-08 23:53:33

--
-- PostgreSQL database dump complete
--

