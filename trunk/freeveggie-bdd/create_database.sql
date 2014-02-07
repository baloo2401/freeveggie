--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.3
-- Dumped by pg_dump version 9.0.1
-- Started on 2011-12-20 22:56:37

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1588 (class 1259 OID 122947)
-- Dependencies: 6
-- Name: TJ_REAP_CALENDAR; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "TJ_REAP_CALENDAR" (
    rpc_id integer NOT NULL,
    rmn_id integer NOT NULL
);


ALTER TABLE public."TJ_REAP_CALENDAR" OWNER TO freeveggie;

--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 1588
-- Name: TABLE "TJ_REAP_CALENDAR"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "TJ_REAP_CALENDAR" IS 'The reference product reaping calendar';


--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 1588
-- Name: COLUMN "TJ_REAP_CALENDAR".rpc_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "TJ_REAP_CALENDAR".rpc_id IS 'The product ';


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 1588
-- Name: COLUMN "TJ_REAP_CALENDAR".rmn_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "TJ_REAP_CALENDAR".rmn_id IS 'The reap mont';


--
-- TOC entry 1603 (class 1259 OID 123000)
-- Dependencies: 6
-- Name: TJ_SEED_CALENDAR; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "TJ_SEED_CALENDAR" (
    rpc_id integer NOT NULL,
    rmn_id integer NOT NULL
);


ALTER TABLE public."TJ_SEED_CALENDAR" OWNER TO freeveggie;

--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 1603
-- Name: TABLE "TJ_SEED_CALENDAR"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "TJ_SEED_CALENDAR" IS 'The reference month of seedling product';


--
-- TOC entry 2034 (class 0 OID 0)
-- Dependencies: 1603
-- Name: COLUMN "TJ_SEED_CALENDAR".rpc_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "TJ_SEED_CALENDAR".rpc_id IS 'The reference product';


--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 1603
-- Name: COLUMN "TJ_SEED_CALENDAR".rmn_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "TJ_SEED_CALENDAR".rmn_id IS 'The seed month';


--
-- TOC entry 1583 (class 1259 OID 122911)
-- Dependencies: 6
-- Name: T_ADDRESS; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_ADDRESS" (
    adr_id bigint NOT NULL,
    adr_line_1 character varying(128) NOT NULL,
    adr_line_2 character varying(128),
    adr_city character varying(64) NOT NULL,
    adr_name character varying(32) NOT NULL,
    adr_street_number integer NOT NULL,
    adr_zip_code integer NOT NULL,
    adr_rcn_id integer NOT NULL
);


ALTER TABLE public."T_ADDRESS" OWNER TO freeveggie;

--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 1583
-- Name: TABLE "T_ADDRESS"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_ADDRESS" IS 'The address of the user and/or the garden';


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 1583
-- Name: COLUMN "T_ADDRESS".adr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_ADDRESS".adr_id IS 'The database id';


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 1583
-- Name: COLUMN "T_ADDRESS".adr_line_1; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_ADDRESS".adr_line_1 IS 'The address line one';


--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 1583
-- Name: COLUMN "T_ADDRESS".adr_line_2; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_ADDRESS".adr_line_2 IS 'The address line two';


--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 1583
-- Name: COLUMN "T_ADDRESS".adr_city; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_ADDRESS".adr_city IS 'The city address';


--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 1583
-- Name: COLUMN "T_ADDRESS".adr_name; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_ADDRESS".adr_name IS 'The address name. This is a user information';


--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 1583
-- Name: COLUMN "T_ADDRESS".adr_street_number; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_ADDRESS".adr_street_number IS 'The street number';


--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 1583
-- Name: COLUMN "T_ADDRESS".adr_zip_code; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_ADDRESS".adr_zip_code IS 'The address zip code';


--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 1583
-- Name: COLUMN "T_ADDRESS".adr_rcn_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_ADDRESS".adr_rcn_id IS 'The country';


--
-- TOC entry 1584 (class 1259 OID 122917)
-- Dependencies: 6
-- Name: T_COMMENT_GARDEN; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_COMMENT_GARDEN" (
    cgr_id bigint NOT NULL,
    cgr_comment character varying(512),
    cgr_creation_date timestamp without time zone NOT NULL,
    cgr_res_id integer NOT NULL,
    cgr_grd_id bigint NOT NULL,
    cgr_usr_id bigint NOT NULL,
    cgr_ren_id integer
);


ALTER TABLE public."T_COMMENT_GARDEN" OWNER TO freeveggie;

--
-- TOC entry 2045 (class 0 OID 0)
-- Dependencies: 1584
-- Name: TABLE "T_COMMENT_GARDEN"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_COMMENT_GARDEN" IS 'List of the comment made on gardens';


--
-- TOC entry 2046 (class 0 OID 0)
-- Dependencies: 1584
-- Name: COLUMN "T_COMMENT_GARDEN".cgr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_GARDEN".cgr_id IS 'The database id';


--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 1584
-- Name: COLUMN "T_COMMENT_GARDEN".cgr_comment; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_GARDEN".cgr_comment IS 'The comment left';


--
-- TOC entry 2048 (class 0 OID 0)
-- Dependencies: 1584
-- Name: COLUMN "T_COMMENT_GARDEN".cgr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_GARDEN".cgr_creation_date IS 'The creation date of the comment';


--
-- TOC entry 2049 (class 0 OID 0)
-- Dependencies: 1584
-- Name: COLUMN "T_COMMENT_GARDEN".cgr_res_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_GARDEN".cgr_res_id IS 'The comment status';


--
-- TOC entry 2050 (class 0 OID 0)
-- Dependencies: 1584
-- Name: COLUMN "T_COMMENT_GARDEN".cgr_grd_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_GARDEN".cgr_grd_id IS 'The garden commented';


--
-- TOC entry 2051 (class 0 OID 0)
-- Dependencies: 1584
-- Name: COLUMN "T_COMMENT_GARDEN".cgr_usr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_GARDEN".cgr_usr_id IS 'The user leaving the comment';


--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 1584
-- Name: COLUMN "T_COMMENT_GARDEN".cgr_ren_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_GARDEN".cgr_ren_id IS 'The evaluation note';


--
-- TOC entry 1585 (class 1259 OID 122923)
-- Dependencies: 6
-- Name: T_COMMENT_PRODUCT; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_COMMENT_PRODUCT" (
    cpr_id bigint NOT NULL,
    cpr_comment character varying(512) NOT NULL,
    cpr_creation_date timestamp without time zone NOT NULL,
    cpr_res_id integer NOT NULL,
    cpr_prd_id bigint NOT NULL,
    cpr_usr_id bigint NOT NULL,
    cpr_ren_id integer NOT NULL
);


ALTER TABLE public."T_COMMENT_PRODUCT" OWNER TO freeveggie;

--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 1585
-- Name: TABLE "T_COMMENT_PRODUCT"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_COMMENT_PRODUCT" IS 'List of comment made on the product';


--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 1585
-- Name: COLUMN "T_COMMENT_PRODUCT".cpr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_PRODUCT".cpr_id IS 'The database id';


--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 1585
-- Name: COLUMN "T_COMMENT_PRODUCT".cpr_comment; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_PRODUCT".cpr_comment IS 'The comment write';


--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 1585
-- Name: COLUMN "T_COMMENT_PRODUCT".cpr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_PRODUCT".cpr_creation_date IS 'The date of when was write';


--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 1585
-- Name: COLUMN "T_COMMENT_PRODUCT".cpr_res_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_PRODUCT".cpr_res_id IS 'The evaluation status';


--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 1585
-- Name: COLUMN "T_COMMENT_PRODUCT".cpr_prd_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_PRODUCT".cpr_prd_id IS 'The product being commented';


--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 1585
-- Name: COLUMN "T_COMMENT_PRODUCT".cpr_usr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_PRODUCT".cpr_usr_id IS 'The user writing the comment';


--
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 1585
-- Name: COLUMN "T_COMMENT_PRODUCT".cpr_ren_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_COMMENT_PRODUCT".cpr_ren_id IS 'The value of the note';


--
-- TOC entry 1575 (class 1259 OID 109779)
-- Dependencies: 1891 1892 6
-- Name: T_GARDEN; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_GARDEN" (
    grd_id bigint NOT NULL,
    grd_creation_date timestamp without time zone NOT NULL,
    grd_description character varying(512),
    grd_home_adr boolean NOT NULL,
    grd_name character varying(32) NOT NULL,
    grd_img_filename character varying(128),
    grd_usr_id bigint NOT NULL,
    grd_adr_id bigint,
    CONSTRAINT home_address_check CHECK (((grd_home_adr = false) AND (grd_adr_id IS NOT NULL))),
    CONSTRAINT home_address_check_2 CHECK (((grd_home_adr = true) AND (grd_adr_id IS NULL)))
);


ALTER TABLE public."T_GARDEN" OWNER TO freeveggie;

--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 1575
-- Name: TABLE "T_GARDEN"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_GARDEN" IS 'The gardesn';


--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 1575
-- Name: COLUMN "T_GARDEN".grd_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_GARDEN".grd_id IS 'The database id';


--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 1575
-- Name: COLUMN "T_GARDEN".grd_creation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_GARDEN".grd_creation_date IS 'The date the garden was create';


--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 1575
-- Name: COLUMN "T_GARDEN".grd_description; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_GARDEN".grd_description IS 'The user description of the garden';


--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 1575
-- Name: COLUMN "T_GARDEN".grd_home_adr; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_GARDEN".grd_home_adr IS 'Is the garden a the same address than the user home address';


--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 1575
-- Name: COLUMN "T_GARDEN".grd_name; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_GARDEN".grd_name IS 'A user visible address name';


--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 1575
-- Name: COLUMN "T_GARDEN".grd_img_filename; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_GARDEN".grd_img_filename IS 'The image file name of the garden';


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 1575
-- Name: COLUMN "T_GARDEN".grd_usr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_GARDEN".grd_usr_id IS 'The owner';


--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 1575
-- Name: COLUMN "T_GARDEN".grd_adr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_GARDEN".grd_adr_id IS 'The garden address';


--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 1575
-- Name: CONSTRAINT home_address_check ON "T_GARDEN"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON CONSTRAINT home_address_check ON "T_GARDEN" IS 'If the the garden is not at the user home address then the garden address id should be filled';


--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 1575
-- Name: CONSTRAINT home_address_check_2 ON "T_GARDEN"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON CONSTRAINT home_address_check_2 ON "T_GARDEN" IS 'If the garden is at the user home address then the garden address id should be null';


--
-- TOC entry 1605 (class 1259 OID 139806)
-- Dependencies: 6
-- Name: T_LIKE_GARDEN; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_LIKE_GARDEN" (
    lgr_id bigint NOT NULL,
    lgr_creation_date timestamp without time zone NOT NULL,
    lgr_res_id integer NOT NULL,
    lgr_grd_id bigint NOT NULL,
    lgr_usr_id bigint NOT NULL
);


ALTER TABLE public."T_LIKE_GARDEN" OWNER TO freeveggie;

--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 1605
-- Name: TABLE "T_LIKE_GARDEN"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_LIKE_GARDEN" IS 'The list of garden like by user';


--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 1605
-- Name: COLUMN "T_LIKE_GARDEN".lgr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_GARDEN".lgr_id IS 'The database id';


--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 1605
-- Name: COLUMN "T_LIKE_GARDEN".lgr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_GARDEN".lgr_creation_date IS 'The creation date of the like';


--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 1605
-- Name: COLUMN "T_LIKE_GARDEN".lgr_res_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_GARDEN".lgr_res_id IS 'The evaluation status';


--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 1605
-- Name: COLUMN "T_LIKE_GARDEN".lgr_grd_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_GARDEN".lgr_grd_id IS 'The garden evaluated';


--
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 1605
-- Name: COLUMN "T_LIKE_GARDEN".lgr_usr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_GARDEN".lgr_usr_id IS 'The user that like the garden';


--
-- TOC entry 1586 (class 1259 OID 122932)
-- Dependencies: 6
-- Name: T_LIKE_PRODUCT; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_LIKE_PRODUCT" (
    lpr_id bigint NOT NULL,
    lpr_creation_date timestamp without time zone NOT NULL,
    lpr_res_id integer NOT NULL,
    lpr_prd_id bigint NOT NULL,
    lpr_usr_id bigint NOT NULL
);


ALTER TABLE public."T_LIKE_PRODUCT" OWNER TO freeveggie;

--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 1586
-- Name: TABLE "T_LIKE_PRODUCT"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_LIKE_PRODUCT" IS 'The list of product like by user';


--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 1586
-- Name: COLUMN "T_LIKE_PRODUCT".lpr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_PRODUCT".lpr_id IS 'The database id';


--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 1586
-- Name: COLUMN "T_LIKE_PRODUCT".lpr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_PRODUCT".lpr_creation_date IS 'The date when the like have been created';


--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 1586
-- Name: COLUMN "T_LIKE_PRODUCT".lpr_res_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_PRODUCT".lpr_res_id IS 'The evaluation status';


--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 1586
-- Name: COLUMN "T_LIKE_PRODUCT".lpr_prd_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_PRODUCT".lpr_prd_id IS 'The product';


--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 1586
-- Name: COLUMN "T_LIKE_PRODUCT".lpr_usr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_LIKE_PRODUCT".lpr_usr_id IS 'The user who like the product';


--
-- TOC entry 1573 (class 1259 OID 109681)
-- Dependencies: 6
-- Name: T_PRODUCT; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_PRODUCT" (
    prd_id bigint NOT NULL,
    prd_rcm_id integer NOT NULL,
    prd_rct_id integer NOT NULL,
    prd_description character varying(512),
    prd_ret_id integer NOT NULL,
    prd_img_filename character varying(128),
    prd_quantity double precision NOT NULL,
    prd_grd_id bigint NOT NULL,
    prd_rpc_id integer NOT NULL
);


ALTER TABLE public."T_PRODUCT" OWNER TO freeveggie;

--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 1573
-- Name: TABLE "T_PRODUCT"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_PRODUCT" IS 'The product made by user';


--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_id IS 'The database id';


--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_rcm_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_rcm_id IS 'The culture mode';


--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_rct_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_rct_id IS 'The culture type';


--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_description; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_description IS 'The user description of the product';


--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_ret_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_ret_id IS 'The exchange type';


--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_img_filename; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_img_filename IS 'The file name of the product image';


--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_quantity; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_quantity IS 'The quantity left available to exchange';


--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_grd_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_grd_id IS 'The garden where the product grow';


--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 1573
-- Name: COLUMN "T_PRODUCT".prd_rpc_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PRODUCT".prd_rpc_id IS 'The reference product';


--
-- TOC entry 1587 (class 1259 OID 122941)
-- Dependencies: 6
-- Name: T_PROFIL; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

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


ALTER TABLE public."T_PROFIL" OWNER TO freeveggie;

--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 1587
-- Name: TABLE "T_PROFIL"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_PROFIL" IS 'User profil';


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_id IS 'The database id.';


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_experience; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_experience IS 'A user experience of free veggie';


--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_interest; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_interest IS 'The interest of the user ';


--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_other; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_other IS 'Anything else that the user wanted to say';


--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_participation; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_participation IS 'A description why the user subscribe';


--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_description; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_description IS 'A description of the user';


--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_philosophy; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_philosophy IS 'Life philosophies of the user';


--
-- TOC entry 2102 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_meals; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_meals IS 'The prefered meal of the user';


--
-- TOC entry 2103 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_img_filename; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_img_filename IS 'The file name of the user picture';


--
-- TOC entry 2104 (class 0 OID 0)
-- Dependencies: 1587
-- Name: COLUMN "T_PROFIL".prf_reason; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_PROFIL".prf_reason IS 'The reason why the user got to this website';


--
-- TOC entry 1604 (class 1259 OID 139567)
-- Dependencies: 6
-- Name: T_REF_COUNTRY; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_COUNTRY" (
    rcn_id integer NOT NULL,
    rcn_code_iso_a2 character(2) NOT NULL,
    rcn_code_iso_a3 character(3) NOT NULL,
    rcn_code_iso_number integer NOT NULL,
    rcn_country_name character varying(64) NOT NULL
);


ALTER TABLE public."T_REF_COUNTRY" OWNER TO freeveggie;

--
-- TOC entry 2105 (class 0 OID 0)
-- Dependencies: 1604
-- Name: TABLE "T_REF_COUNTRY"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_COUNTRY" IS 'The list of country';


--
-- TOC entry 2106 (class 0 OID 0)
-- Dependencies: 1604
-- Name: COLUMN "T_REF_COUNTRY".rcn_code_iso_a2; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_COUNTRY".rcn_code_iso_a2 IS 'The is 2 letters code';


--
-- TOC entry 2107 (class 0 OID 0)
-- Dependencies: 1604
-- Name: COLUMN "T_REF_COUNTRY".rcn_code_iso_a3; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_COUNTRY".rcn_code_iso_a3 IS 'The iso 3 letters code';


--
-- TOC entry 2108 (class 0 OID 0)
-- Dependencies: 1604
-- Name: COLUMN "T_REF_COUNTRY".rcn_code_iso_number; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_COUNTRY".rcn_code_iso_number IS 'The iso code number';


--
-- TOC entry 2109 (class 0 OID 0)
-- Dependencies: 1604
-- Name: COLUMN "T_REF_COUNTRY".rcn_country_name; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_COUNTRY".rcn_country_name IS 'The country name';


--
-- TOC entry 1589 (class 1259 OID 122950)
-- Dependencies: 6
-- Name: T_REF_CULTURE_MODE; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_CULTURE_MODE" (
    rcm_id integer NOT NULL,
    rcm_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_CULTURE_MODE" OWNER TO freeveggie;

--
-- TOC entry 2110 (class 0 OID 0)
-- Dependencies: 1589
-- Name: TABLE "T_REF_CULTURE_MODE"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_CULTURE_MODE" IS 'The choice of culture mode';


--
-- TOC entry 2111 (class 0 OID 0)
-- Dependencies: 1589
-- Name: COLUMN "T_REF_CULTURE_MODE".rcm_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_CULTURE_MODE".rcm_id IS 'The database id';


--
-- TOC entry 2112 (class 0 OID 0)
-- Dependencies: 1589
-- Name: COLUMN "T_REF_CULTURE_MODE".rcm_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_CULTURE_MODE".rcm_libelle IS 'The culture mode description ';


--
-- TOC entry 1590 (class 1259 OID 122953)
-- Dependencies: 6
-- Name: T_REF_CULTURE_TYPE; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_CULTURE_TYPE" (
    rct_id integer NOT NULL,
    rct_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_CULTURE_TYPE" OWNER TO freeveggie;

--
-- TOC entry 2113 (class 0 OID 0)
-- Dependencies: 1590
-- Name: TABLE "T_REF_CULTURE_TYPE"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_CULTURE_TYPE" IS 'The choice of culture type';


--
-- TOC entry 2114 (class 0 OID 0)
-- Dependencies: 1590
-- Name: COLUMN "T_REF_CULTURE_TYPE".rct_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_CULTURE_TYPE".rct_id IS 'The database id';


--
-- TOC entry 2115 (class 0 OID 0)
-- Dependencies: 1590
-- Name: COLUMN "T_REF_CULTURE_TYPE".rct_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_CULTURE_TYPE".rct_libelle IS 'The culture type description';


--
-- TOC entry 1591 (class 1259 OID 122956)
-- Dependencies: 6
-- Name: T_REF_EVALUATION_NOTE; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_EVALUATION_NOTE" (
    ren_id integer NOT NULL,
    ren_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_EVALUATION_NOTE" OWNER TO freeveggie;

--
-- TOC entry 2116 (class 0 OID 0)
-- Dependencies: 1591
-- Name: TABLE "T_REF_EVALUATION_NOTE"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_EVALUATION_NOTE" IS 'The value of a note (for a garden or a product)';


--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 1591
-- Name: COLUMN "T_REF_EVALUATION_NOTE".ren_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_EVALUATION_NOTE".ren_id IS 'The database id';


--
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 1591
-- Name: COLUMN "T_REF_EVALUATION_NOTE".ren_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_EVALUATION_NOTE".ren_libelle IS 'The evaluation note description';


--
-- TOC entry 1592 (class 1259 OID 122959)
-- Dependencies: 6
-- Name: T_REF_EVALUATION_STATUS; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_EVALUATION_STATUS" (
    res_id integer NOT NULL,
    res_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_EVALUATION_STATUS" OWNER TO freeveggie;

--
-- TOC entry 2119 (class 0 OID 0)
-- Dependencies: 1592
-- Name: TABLE "T_REF_EVALUATION_STATUS"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_EVALUATION_STATUS" IS 'The status of an evaluation(product, garden, ...)';


--
-- TOC entry 2120 (class 0 OID 0)
-- Dependencies: 1592
-- Name: COLUMN "T_REF_EVALUATION_STATUS".res_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_EVALUATION_STATUS".res_id IS 'The database id';


--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 1592
-- Name: COLUMN "T_REF_EVALUATION_STATUS".res_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_EVALUATION_STATUS".res_libelle IS 'The evaluation status description';


--
-- TOC entry 1593 (class 1259 OID 122962)
-- Dependencies: 6
-- Name: T_REF_EXCHANGE_TYPE; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_EXCHANGE_TYPE" (
    chg_id integer NOT NULL,
    chg_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_EXCHANGE_TYPE" OWNER TO freeveggie;

--
-- TOC entry 2122 (class 0 OID 0)
-- Dependencies: 1593
-- Name: TABLE "T_REF_EXCHANGE_TYPE"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_EXCHANGE_TYPE" IS 'Choice of exchange type';


--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 1593
-- Name: COLUMN "T_REF_EXCHANGE_TYPE".chg_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_EXCHANGE_TYPE".chg_id IS 'The database id';


--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 1593
-- Name: COLUMN "T_REF_EXCHANGE_TYPE".chg_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_EXCHANGE_TYPE".chg_libelle IS 'The exchange type description';


--
-- TOC entry 1594 (class 1259 OID 122965)
-- Dependencies: 6
-- Name: T_REF_MONTH; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_MONTH" (
    rmn_id integer NOT NULL,
    rmn_name character varying(32)
);


ALTER TABLE public."T_REF_MONTH" OWNER TO freeveggie;

--
-- TOC entry 2125 (class 0 OID 0)
-- Dependencies: 1594
-- Name: TABLE "T_REF_MONTH"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_MONTH" IS 'The reference mont of a year';


--
-- TOC entry 2126 (class 0 OID 0)
-- Dependencies: 1594
-- Name: COLUMN "T_REF_MONTH".rmn_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_MONTH".rmn_id IS 'The month number';


--
-- TOC entry 2127 (class 0 OID 0)
-- Dependencies: 1594
-- Name: COLUMN "T_REF_MONTH".rmn_name; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_MONTH".rmn_name IS 'The month description';


--
-- TOC entry 1606 (class 1259 OID 140002)
-- Dependencies: 6
-- Name: T_REF_PRODUCT; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_PRODUCT" (
    rpc_id integer NOT NULL,
    rpc_carbohydrate double precision,
    rpc_lipid double precision,
    rpc_name character varying(32) NOT NULL,
    rpc_img_filename character varying(128),
    rpc_protein double precision,
    rpc_rpt_id integer NOT NULL
);


ALTER TABLE public."T_REF_PRODUCT" OWNER TO freeveggie;

--
-- TOC entry 2128 (class 0 OID 0)
-- Dependencies: 1606
-- Name: TABLE "T_REF_PRODUCT"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_PRODUCT" IS 'The reference of product';


--
-- TOC entry 2129 (class 0 OID 0)
-- Dependencies: 1606
-- Name: COLUMN "T_REF_PRODUCT".rpc_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT".rpc_id IS 'The database id';


--
-- TOC entry 2130 (class 0 OID 0)
-- Dependencies: 1606
-- Name: COLUMN "T_REF_PRODUCT".rpc_carbohydrate; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT".rpc_carbohydrate IS 'The quantity of carbohydrate in the product';


--
-- TOC entry 2131 (class 0 OID 0)
-- Dependencies: 1606
-- Name: COLUMN "T_REF_PRODUCT".rpc_lipid; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT".rpc_lipid IS 'The quantity of lipid in the product';


--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 1606
-- Name: COLUMN "T_REF_PRODUCT".rpc_name; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT".rpc_name IS 'The name of the product gived by the producer';


--
-- TOC entry 2133 (class 0 OID 0)
-- Dependencies: 1606
-- Name: COLUMN "T_REF_PRODUCT".rpc_img_filename; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT".rpc_img_filename IS 'The file name of the product image';


--
-- TOC entry 2134 (class 0 OID 0)
-- Dependencies: 1606
-- Name: COLUMN "T_REF_PRODUCT".rpc_protein; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT".rpc_protein IS 'The quantity of protein in the product';


--
-- TOC entry 2135 (class 0 OID 0)
-- Dependencies: 1606
-- Name: COLUMN "T_REF_PRODUCT".rpc_rpt_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT".rpc_rpt_id IS 'The product type';


--
-- TOC entry 1595 (class 1259 OID 122970)
-- Dependencies: 6
-- Name: T_REF_PRODUCT_REQUEST_STATUS; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_PRODUCT_REQUEST_STATUS" (
    rpr_id integer NOT NULL,
    rpr_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_PRODUCT_REQUEST_STATUS" OWNER TO freeveggie;

--
-- TOC entry 2136 (class 0 OID 0)
-- Dependencies: 1595
-- Name: TABLE "T_REF_PRODUCT_REQUEST_STATUS"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_PRODUCT_REQUEST_STATUS" IS 'The posible state of a product request';


--
-- TOC entry 2137 (class 0 OID 0)
-- Dependencies: 1595
-- Name: COLUMN "T_REF_PRODUCT_REQUEST_STATUS".rpr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT_REQUEST_STATUS".rpr_id IS 'The database id';


--
-- TOC entry 2138 (class 0 OID 0)
-- Dependencies: 1595
-- Name: COLUMN "T_REF_PRODUCT_REQUEST_STATUS".rpr_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT_REQUEST_STATUS".rpr_libelle IS 'The product request status description';


--
-- TOC entry 1596 (class 1259 OID 122973)
-- Dependencies: 6
-- Name: T_REF_PRODUCT_TYPE; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_PRODUCT_TYPE" (
    rpt_id integer NOT NULL,
    rpt_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_PRODUCT_TYPE" OWNER TO freeveggie;

--
-- TOC entry 2139 (class 0 OID 0)
-- Dependencies: 1596
-- Name: TABLE "T_REF_PRODUCT_TYPE"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_PRODUCT_TYPE" IS 'The reference of product type (fruit or vegetable)';


--
-- TOC entry 2140 (class 0 OID 0)
-- Dependencies: 1596
-- Name: COLUMN "T_REF_PRODUCT_TYPE".rpt_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT_TYPE".rpt_id IS 'The database id';


--
-- TOC entry 2141 (class 0 OID 0)
-- Dependencies: 1596
-- Name: COLUMN "T_REF_PRODUCT_TYPE".rpt_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_PRODUCT_TYPE".rpt_libelle IS 'The product type description';


--
-- TOC entry 1597 (class 1259 OID 122976)
-- Dependencies: 6
-- Name: T_REF_RELATIONS_STATUS; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_RELATIONS_STATUS" (
    rrs_id integer NOT NULL,
    rrs_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_RELATIONS_STATUS" OWNER TO freeveggie;

--
-- TOC entry 2142 (class 0 OID 0)
-- Dependencies: 1597
-- Name: TABLE "T_REF_RELATIONS_STATUS"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_RELATIONS_STATUS" IS 'The posible state of relationship';


--
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 1597
-- Name: COLUMN "T_REF_RELATIONS_STATUS".rrs_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_RELATIONS_STATUS".rrs_id IS 'The database id.';


--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 1597
-- Name: COLUMN "T_REF_RELATIONS_STATUS".rrs_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_RELATIONS_STATUS".rrs_libelle IS 'The relation status description';


--
-- TOC entry 1598 (class 1259 OID 122979)
-- Dependencies: 6
-- Name: T_REF_RELATION_TYPE; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_RELATION_TYPE" (
    rrt_id integer NOT NULL,
    rrt_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_RELATION_TYPE" OWNER TO freeveggie;

--
-- TOC entry 2145 (class 0 OID 0)
-- Dependencies: 1598
-- Name: TABLE "T_REF_RELATION_TYPE"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_RELATION_TYPE" IS 'The possible value of a relation type (friends, neighbors, ...)';


--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 1598
-- Name: COLUMN "T_REF_RELATION_TYPE".rrt_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_RELATION_TYPE".rrt_id IS 'The database id';


--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 1598
-- Name: COLUMN "T_REF_RELATION_TYPE".rrt_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_RELATION_TYPE".rrt_libelle IS 'The relation description';


--
-- TOC entry 1599 (class 1259 OID 122982)
-- Dependencies: 6
-- Name: T_REF_USER_ROLE; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REF_USER_ROLE" (
    rur_id integer NOT NULL,
    rur_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_USER_ROLE" OWNER TO freeveggie;

--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 1599
-- Name: TABLE "T_REF_USER_ROLE"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REF_USER_ROLE" IS 'The posible user role';


--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 1599
-- Name: COLUMN "T_REF_USER_ROLE".rur_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_USER_ROLE".rur_id IS 'The database id';


--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 1599
-- Name: COLUMN "T_REF_USER_ROLE".rur_libelle; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REF_USER_ROLE".rur_libelle IS 'The user role description';


--
-- TOC entry 1600 (class 1259 OID 122985)
-- Dependencies: 6
-- Name: T_REF_USER_STATUS; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "T_REF_USER_STATUS" (
    rus_id integer NOT NULL,
    rus_libelle character varying(32) NOT NULL
);


ALTER TABLE public."T_REF_USER_STATUS" OWNER TO postgres;

--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 1600
-- Name: TABLE "T_REF_USER_STATUS"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "T_REF_USER_STATUS" IS 'The possible state of user (new, blacklisted, ....)';


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 1600
-- Name: COLUMN "T_REF_USER_STATUS".rus_libelle; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN "T_REF_USER_STATUS".rus_libelle IS 'The user status description';


--
-- TOC entry 1601 (class 1259 OID 122988)
-- Dependencies: 6
-- Name: T_RELATIONSHIP; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_RELATIONSHIP" (
    rlt_id bigint NOT NULL,
    rlt_answer character varying(512) NOT NULL,
    rlt_creation_date timestamp without time zone NOT NULL,
    rlt_rrs_id integer NOT NULL,
    rlt_rrt_id integer NOT NULL,
    rlt_usr_id_sender bigint NOT NULL,
    rlt_usr_id_recipient bigint NOT NULL,
    rlt_request character varying(512) NOT NULL
);


ALTER TABLE public."T_RELATIONSHIP" OWNER TO freeveggie;

--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 1601
-- Name: TABLE "T_RELATIONSHIP"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_RELATIONSHIP" IS 'The list of user relationship
';


--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 1601
-- Name: COLUMN "T_RELATIONSHIP".rlt_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_RELATIONSHIP".rlt_id IS 'The database id';


--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 1601
-- Name: COLUMN "T_RELATIONSHIP".rlt_answer; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_RELATIONSHIP".rlt_answer IS 'The answer of the request';


--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 1601
-- Name: COLUMN "T_RELATIONSHIP".rlt_creation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_RELATIONSHIP".rlt_creation_date IS 'The date and time when the request was send';


--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 1601
-- Name: COLUMN "T_RELATIONSHIP".rlt_rrs_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_RELATIONSHIP".rlt_rrs_id IS 'The relation status';


--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 1601
-- Name: COLUMN "T_RELATIONSHIP".rlt_rrt_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_RELATIONSHIP".rlt_rrt_id IS 'The type of relationship';


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 1601
-- Name: COLUMN "T_RELATIONSHIP".rlt_usr_id_sender; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_RELATIONSHIP".rlt_usr_id_sender IS 'The user who send the request';


--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 1601
-- Name: COLUMN "T_RELATIONSHIP".rlt_usr_id_recipient; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_RELATIONSHIP".rlt_usr_id_recipient IS 'The recipient of the request.';


--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 1601
-- Name: COLUMN "T_RELATIONSHIP".rlt_request; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_RELATIONSHIP".rlt_request IS 'The request messages send from the recipient';


--
-- TOC entry 1602 (class 1259 OID 122994)
-- Dependencies: 6
-- Name: T_REQUEST_PRODUCT; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_REQUEST_PRODUCT" (
    rqt_id bigint NOT NULL,
    rqt_answer_msg character varying(512),
    rqt_answer_date timestamp without time zone,
    rqt_creation_date timestamp without time zone NOT NULL,
    rqt_msg character varying(512) NOT NULL,
    rqt_picking_date timestamp without time zone NOT NULL,
    rqt_quantity double precision NOT NULL,
    rqt_rpr_id integer NOT NULL,
    rqt_prd_id bigint NOT NULL,
    "rqt _usr_id" bigint NOT NULL
);


ALTER TABLE public."T_REQUEST_PRODUCT" OWNER TO freeveggie;

--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 1602
-- Name: TABLE "T_REQUEST_PRODUCT"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_REQUEST_PRODUCT" IS 'The product request made by users';


--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_id IS 'The database id';


--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_answer_msg; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_answer_msg IS 'The request answer';


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_answer_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_answer_date IS 'The date the user answer the request';


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_creation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_creation_date IS 'The date of the request have been send';


--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_msg; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_msg IS 'The request message send by the requester';


--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_picking_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_picking_date IS 'The date that the requester wish to pick the product';


--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_quantity; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_quantity IS 'The quantity the request wish to pick';


--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_rpr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_rpr_id IS 'The request status';


--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT".rqt_prd_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT".rqt_prd_id IS 'The product requested';


--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 1602
-- Name: COLUMN "T_REQUEST_PRODUCT"."rqt _usr_id"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_REQUEST_PRODUCT"."rqt _usr_id" IS 'The user who send the request';


--
-- TOC entry 1611 (class 1259 OID 140101)
-- Dependencies: 6
-- Name: T_SUBSCRIPTION; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_SUBSCRIPTION" (
    sbr_id bigint NOT NULL,
    sbr_share_personal boolean NOT NULL,
    sbr_share_garden boolean NOT NULL,
    sbr_newsletter boolean NOT NULL,
    sbr_freeveggie_agreement boolean NOT NULL
);


ALTER TABLE public."T_SUBSCRIPTION" OWNER TO freeveggie;

--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 1611
-- Name: TABLE "T_SUBSCRIPTION"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_SUBSCRIPTION" IS 'The subscription of the user';


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 1611
-- Name: COLUMN "T_SUBSCRIPTION".sbr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_SUBSCRIPTION".sbr_id IS 'The database id';


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 1611
-- Name: COLUMN "T_SUBSCRIPTION".sbr_share_personal; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_SUBSCRIPTION".sbr_share_personal IS 'Does the user agree to share his personal information with free veggie partners';


--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 1611
-- Name: COLUMN "T_SUBSCRIPTION".sbr_share_garden; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_SUBSCRIPTION".sbr_share_garden IS 'Does the user agree to share his garden information to free veggie partners';


--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 1611
-- Name: COLUMN "T_SUBSCRIPTION".sbr_newsletter; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_SUBSCRIPTION".sbr_newsletter IS 'Does the user agree to receive the free veggie newsletter';


--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 1611
-- Name: COLUMN "T_SUBSCRIPTION".sbr_freeveggie_agreement; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_SUBSCRIPTION".sbr_freeveggie_agreement IS 'Does the user sign the freeveggie aggreement';


--
-- TOC entry 1574 (class 1259 OID 109705)
-- Dependencies: 6
-- Name: T_USER; Type: TABLE; Schema: public; Owner: freeveggie; Tablespace: 
--

CREATE TABLE "T_USER" (
    usr_id bigint NOT NULL,
    usr_blacklisted_date timestamp without time zone,
    usr_cancellation_date timestamp without time zone,
    usr_creation_date timestamp without time zone NOT NULL,
    usr_email_address character varying(128) NOT NULL,
    usr_firstname character varying(64) NOT NULL,
    usr_last_connexion timestamp without time zone NOT NULL,
    usr_lastname character varying(64) NOT NULL,
    usr_password character varying(32) NOT NULL,
    usr_rur_id integer NOT NULL,
    usr_rus_id integer NOT NULL,
    usr_temp_password character varying(32),
    usr_username character varying(32) NOT NULL,
    usr_adr_id bigint NOT NULL
);


ALTER TABLE public."T_USER" OWNER TO freeveggie;

--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 1574
-- Name: TABLE "T_USER"; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON TABLE "T_USER" IS 'The website users';


--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_id IS 'The database id';


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_blacklisted_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_blacklisted_date IS 'The date when the user got blacklisted';


--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_cancellation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_cancellation_date IS 'The date when the user cancel his subscription';


--
-- TOC entry 2183 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_creation_date IS 'The date when the account get create';


--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_email_address; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_email_address IS 'The user email address';


--
-- TOC entry 2185 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_firstname; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_firstname IS 'The user firstname';


--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_last_connexion; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_last_connexion IS 'The user last connexion date and time';


--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_lastname; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_lastname IS 'The user last name';


--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_password; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_password IS 'The user password encrypted user MD5';


--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_rur_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_rur_id IS 'The user role ';


--
-- TOC entry 2190 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_rus_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_rus_id IS 'The user status ';


--
-- TOC entry 2191 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_temp_password; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_temp_password IS 'The user temporary password. Create after the user ask for a new one because he lost his password. Encrypted under the MD5 algorithme';


--
-- TOC entry 2192 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_username; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_username IS 'The user login';


--
-- TOC entry 2193 (class 0 OID 0)
-- Dependencies: 1574
-- Name: COLUMN "T_USER".usr_adr_id; Type: COMMENT; Schema: public; Owner: freeveggie
--

COMMENT ON COLUMN "T_USER".usr_adr_id IS 'The user address';


--
-- TOC entry 1612 (class 1259 OID 140115)
-- Dependencies: 1699 6
-- Name: V_USER_PARTIAL; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW "V_USER_PARTIAL" AS
    SELECT "T_USER".usr_id, "T_USER".usr_creation_date, "T_USER".usr_firstname, "T_USER".usr_last_connexion, "T_USER".usr_lastname, "T_USER".usr_password, "T_PROFIL".prf_img_filename FROM "T_PROFIL", "T_USER" WHERE ("T_PROFIL".prf_id = "T_USER".usr_id);


ALTER TABLE public."V_USER_PARTIAL" OWNER TO postgres;

--
-- TOC entry 1576 (class 1259 OID 122881)
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
-- TOC entry 2194 (class 0 OID 0)
-- Dependencies: 1576
-- Name: s_adr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_adr_seq', 1, true);


--
-- TOC entry 1607 (class 1259 OID 140087)
-- Dependencies: 6
-- Name: s_cgr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_cgr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_cgr_seq OWNER TO freeveggie;

--
-- TOC entry 2195 (class 0 OID 0)
-- Dependencies: 1607
-- Name: s_cgr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_cgr_seq', 1, false);


--
-- TOC entry 1608 (class 1259 OID 140090)
-- Dependencies: 6
-- Name: s_cpr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_cpr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_cpr_seq OWNER TO freeveggie;

--
-- TOC entry 2196 (class 0 OID 0)
-- Dependencies: 1608
-- Name: s_cpr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_cpr_seq', 1, false);


--
-- TOC entry 1577 (class 1259 OID 122887)
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
-- TOC entry 2197 (class 0 OID 0)
-- Dependencies: 1577
-- Name: s_grd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_grd_seq', 1, true);


--
-- TOC entry 1609 (class 1259 OID 140092)
-- Dependencies: 6
-- Name: s_lgr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_lgr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_lgr_seq OWNER TO freeveggie;

--
-- TOC entry 2198 (class 0 OID 0)
-- Dependencies: 1609
-- Name: s_lgr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_lgr_seq', 1, false);


--
-- TOC entry 1610 (class 1259 OID 140094)
-- Dependencies: 6
-- Name: s_lpr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggie
--

CREATE SEQUENCE s_lpr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_lpr_seq OWNER TO freeveggie;

--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 1610
-- Name: s_lpr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_lpr_seq', 1, false);


--
-- TOC entry 1578 (class 1259 OID 122899)
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
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 1578
-- Name: s_prd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_prd_seq', 1, true);


--
-- TOC entry 1579 (class 1259 OID 122901)
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
-- TOC entry 2201 (class 0 OID 0)
-- Dependencies: 1579
-- Name: s_prf_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_prf_seq', 1, true);


--
-- TOC entry 1580 (class 1259 OID 122903)
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
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 1580
-- Name: s_rlt_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_rlt_seq', 1, true);


--
-- TOC entry 1581 (class 1259 OID 122905)
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
-- TOC entry 2203 (class 0 OID 0)
-- Dependencies: 1581
-- Name: s_rqt_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_rqt_seq', 1, true);


--
-- TOC entry 1582 (class 1259 OID 122909)
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
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 1582
-- Name: s_usr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_usr_seq', 1, true);


--
-- TOC entry 2006 (class 0 OID 122947)
-- Dependencies: 1588
-- Data for Name: TJ_REAP_CALENDAR; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "TJ_REAP_CALENDAR" (rpc_id, rmn_id) FROM stdin;
\.


--
-- TOC entry 2021 (class 0 OID 123000)
-- Dependencies: 1603
-- Data for Name: TJ_SEED_CALENDAR; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "TJ_SEED_CALENDAR" (rpc_id, rmn_id) FROM stdin;
\.


--
-- TOC entry 2001 (class 0 OID 122911)
-- Dependencies: 1583
-- Data for Name: T_ADDRESS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_ADDRESS" (adr_id, adr_line_1, adr_line_2, adr_city, adr_name, adr_street_number, adr_zip_code, adr_rcn_id) FROM stdin;
\.


--
-- TOC entry 2002 (class 0 OID 122917)
-- Dependencies: 1584
-- Data for Name: T_COMMENT_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_COMMENT_GARDEN" (cgr_id, cgr_comment, cgr_creation_date, cgr_res_id, cgr_grd_id, cgr_usr_id, cgr_ren_id) FROM stdin;
\.


--
-- TOC entry 2003 (class 0 OID 122923)
-- Dependencies: 1585
-- Data for Name: T_COMMENT_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_COMMENT_PRODUCT" (cpr_id, cpr_comment, cpr_creation_date, cpr_res_id, cpr_prd_id, cpr_usr_id, cpr_ren_id) FROM stdin;
\.


--
-- TOC entry 2000 (class 0 OID 109779)
-- Dependencies: 1575
-- Data for Name: T_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_GARDEN" (grd_id, grd_creation_date, grd_description, grd_home_adr, grd_name, grd_img_filename, grd_usr_id, grd_adr_id) FROM stdin;
\.


--
-- TOC entry 2023 (class 0 OID 139806)
-- Dependencies: 1605
-- Data for Name: T_LIKE_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_LIKE_GARDEN" (lgr_id, lgr_creation_date, lgr_res_id, lgr_grd_id, lgr_usr_id) FROM stdin;
\.


--
-- TOC entry 2004 (class 0 OID 122932)
-- Dependencies: 1586
-- Data for Name: T_LIKE_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_LIKE_PRODUCT" (lpr_id, lpr_creation_date, lpr_res_id, lpr_prd_id, lpr_usr_id) FROM stdin;
\.


--
-- TOC entry 1998 (class 0 OID 109681)
-- Dependencies: 1573
-- Data for Name: T_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_PRODUCT" (prd_id, prd_rcm_id, prd_rct_id, prd_description, prd_ret_id, prd_img_filename, prd_quantity, prd_grd_id, prd_rpc_id) FROM stdin;
\.


--
-- TOC entry 2005 (class 0 OID 122941)
-- Dependencies: 1587
-- Data for Name: T_PROFIL; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_PROFIL" (prf_id, prf_experience, prf_interest, prf_other, prf_participation, prf_description, prf_philosophy, prf_meals, prf_img_filename, prf_reason) FROM stdin;
\.


--
-- TOC entry 2022 (class 0 OID 139567)
-- Dependencies: 1604
-- Data for Name: T_REF_COUNTRY; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_COUNTRY" (rcn_id, rcn_code_iso_a2, rcn_code_iso_a3, rcn_code_iso_number, rcn_country_name) FROM stdin;
\.


--
-- TOC entry 2007 (class 0 OID 122950)
-- Dependencies: 1589
-- Data for Name: T_REF_CULTURE_MODE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_CULTURE_MODE" (rcm_id, rcm_libelle) FROM stdin;
1	garden
2	balcony
3	terrace
\.


--
-- TOC entry 2008 (class 0 OID 122953)
-- Dependencies: 1590
-- Data for Name: T_REF_CULTURE_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_CULTURE_TYPE" (rct_id, rct_libelle) FROM stdin;
1	regular
2	bio
3	gmo
\.


--
-- TOC entry 2009 (class 0 OID 122956)
-- Dependencies: 1591
-- Data for Name: T_REF_EVALUATION_NOTE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_EVALUATION_NOTE" (ren_id, ren_libelle) FROM stdin;
1	very bad
2	bad
3	normal
4	good
5	very good
\.


--
-- TOC entry 2010 (class 0 OID 122959)
-- Dependencies: 1592
-- Data for Name: T_REF_EVALUATION_STATUS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_EVALUATION_STATUS" (res_id, res_libelle) FROM stdin;
1	setted
2	removed
3	archived
\.


--
-- TOC entry 2011 (class 0 OID 122962)
-- Dependencies: 1593
-- Data for Name: T_REF_EXCHANGE_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_EXCHANGE_TYPE" (chg_id, chg_libelle) FROM stdin;
1	shares
2	gives
3	sells
\.


--
-- TOC entry 2012 (class 0 OID 122965)
-- Dependencies: 1594
-- Data for Name: T_REF_MONTH; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_MONTH" (rmn_id, rmn_name) FROM stdin;
1	january
2	february
3	march
4	april
5	may
6	june
7	july
8	august
9	september
10	october
11	november
12	december
\.


--
-- TOC entry 2024 (class 0 OID 140002)
-- Dependencies: 1606
-- Data for Name: T_REF_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_PRODUCT" (rpc_id, rpc_carbohydrate, rpc_lipid, rpc_name, rpc_img_filename, rpc_protein, rpc_rpt_id) FROM stdin;
\.


--
-- TOC entry 2013 (class 0 OID 122970)
-- Dependencies: 1595
-- Data for Name: T_REF_PRODUCT_REQUEST_STATUS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_PRODUCT_REQUEST_STATUS" (rpr_id, rpr_libelle) FROM stdin;
1	request
2	pending
3	accepted
4	refused
\.


--
-- TOC entry 2014 (class 0 OID 122973)
-- Dependencies: 1596
-- Data for Name: T_REF_PRODUCT_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_PRODUCT_TYPE" (rpt_id, rpt_libelle) FROM stdin;
1	fruit
2	vegetable
\.


--
-- TOC entry 2015 (class 0 OID 122976)
-- Dependencies: 1597
-- Data for Name: T_REF_RELATIONS_STATUS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_RELATIONS_STATUS" (rrs_id, rrs_libelle) FROM stdin;
1	request
2	pending
3	validated
4	refused
5	cancel
\.


--
-- TOC entry 2016 (class 0 OID 122979)
-- Dependencies: 1598
-- Data for Name: T_REF_RELATION_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_RELATION_TYPE" (rrt_id, rrt_libelle) FROM stdin;
1	freeveggie friend
2	friend
3	neighbors
\.


--
-- TOC entry 2017 (class 0 OID 122982)
-- Dependencies: 1599
-- Data for Name: T_REF_USER_ROLE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_USER_ROLE" (rur_id, rur_libelle) FROM stdin;
1	user
2	manager
3	admin
4	super admin
\.


--
-- TOC entry 2018 (class 0 OID 122985)
-- Dependencies: 1600
-- Data for Name: T_REF_USER_STATUS; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "T_REF_USER_STATUS" (rus_id, rus_libelle) FROM stdin;
1	new
2	valided
3	blacklisted
4	deleted
5	archive
\.


--
-- TOC entry 2019 (class 0 OID 122988)
-- Dependencies: 1601
-- Data for Name: T_RELATIONSHIP; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_RELATIONSHIP" (rlt_id, rlt_answer, rlt_creation_date, rlt_rrs_id, rlt_rrt_id, rlt_usr_id_sender, rlt_usr_id_recipient, rlt_request) FROM stdin;
\.


--
-- TOC entry 2020 (class 0 OID 122994)
-- Dependencies: 1602
-- Data for Name: T_REQUEST_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REQUEST_PRODUCT" (rqt_id, rqt_answer_msg, rqt_answer_date, rqt_creation_date, rqt_msg, rqt_picking_date, rqt_quantity, rqt_rpr_id, rqt_prd_id, "rqt _usr_id") FROM stdin;
\.


--
-- TOC entry 2025 (class 0 OID 140101)
-- Dependencies: 1611
-- Data for Name: T_SUBSCRIPTION; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_SUBSCRIPTION" (sbr_id, sbr_share_personal, sbr_share_garden, sbr_newsletter, sbr_freeveggie_agreement) FROM stdin;
\.


--
-- TOC entry 1999 (class 0 OID 109705)
-- Dependencies: 1574
-- Data for Name: T_USER; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_USER" (usr_id, usr_blacklisted_date, usr_cancellation_date, usr_creation_date, usr_email_address, usr_firstname, usr_last_connexion, usr_lastname, usr_password, usr_rur_id, usr_rus_id, usr_temp_password, usr_username, usr_adr_id) FROM stdin;
\.


--
-- TOC entry 1902 (class 2606 OID 123008)
-- Dependencies: 1583 1583
-- Name: t_address_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_ADDRESS"
    ADD CONSTRAINT t_address_pkey PRIMARY KEY (adr_id);


--
-- TOC entry 1904 (class 2606 OID 123010)
-- Dependencies: 1584 1584
-- Name: t_comment_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_COMMENT_GARDEN"
    ADD CONSTRAINT t_comment_garden_pkey PRIMARY KEY (cgr_id);


--
-- TOC entry 1906 (class 2606 OID 123012)
-- Dependencies: 1585 1585
-- Name: t_comment_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_COMMENT_PRODUCT"
    ADD CONSTRAINT t_comment_product_pkey PRIMARY KEY (cpr_id);


--
-- TOC entry 1900 (class 2606 OID 109786)
-- Dependencies: 1575 1575
-- Name: t_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_GARDEN"
    ADD CONSTRAINT t_garden_pkey PRIMARY KEY (grd_id);


--
-- TOC entry 1954 (class 2606 OID 139827)
-- Dependencies: 1605 1605
-- Name: t_like_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_LIKE_GARDEN"
    ADD CONSTRAINT t_like_garden_pkey PRIMARY KEY (lgr_id);


--
-- TOC entry 1908 (class 2606 OID 123016)
-- Dependencies: 1586 1586
-- Name: t_like_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_LIKE_PRODUCT"
    ADD CONSTRAINT t_like_product_pkey PRIMARY KEY (lpr_id);


--
-- TOC entry 1894 (class 2606 OID 109688)
-- Dependencies: 1573 1573
-- Name: t_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_PRODUCT"
    ADD CONSTRAINT t_product_pkey PRIMARY KEY (prd_id);


--
-- TOC entry 1910 (class 2606 OID 123022)
-- Dependencies: 1587 1587
-- Name: t_profil_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_PROFIL"
    ADD CONSTRAINT t_profil_pkey PRIMARY KEY (prf_id);


--
-- TOC entry 1946 (class 2606 OID 139571)
-- Dependencies: 1604 1604
-- Name: t_ref_country_tmp_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_tmp_pkey PRIMARY KEY (rcn_id);


--
-- TOC entry 1948 (class 2606 OID 139573)
-- Dependencies: 1604 1604
-- Name: t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a2; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a2 UNIQUE (rcn_code_iso_a2);


--
-- TOC entry 1950 (class 2606 OID 139575)
-- Dependencies: 1604 1604
-- Name: t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a3; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a3 UNIQUE (rcn_code_iso_a3);


--
-- TOC entry 1952 (class 2606 OID 139577)
-- Dependencies: 1604 1604
-- Name: t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_number; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_number UNIQUE (rcn_code_iso_number);


--
-- TOC entry 1914 (class 2606 OID 139924)
-- Dependencies: 1589 1589
-- Name: t_ref_culture_mode_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_CULTURE_MODE"
    ADD CONSTRAINT t_ref_culture_mode_pkey PRIMARY KEY (rcm_id);


--
-- TOC entry 1916 (class 2606 OID 139939)
-- Dependencies: 1590 1590
-- Name: t_ref_culture_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_CULTURE_TYPE"
    ADD CONSTRAINT t_ref_culture_type_pkey PRIMARY KEY (rct_id);


--
-- TOC entry 1918 (class 2606 OID 139767)
-- Dependencies: 1591 1591
-- Name: t_ref_evaluation_note_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_EVALUATION_NOTE"
    ADD CONSTRAINT t_ref_evaluation_note_pkey PRIMARY KEY (ren_id);


--
-- TOC entry 1920 (class 2606 OID 139737)
-- Dependencies: 1592 1592
-- Name: t_ref_evaluation_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_EVALUATION_STATUS"
    ADD CONSTRAINT t_ref_evaluation_status_pkey PRIMARY KEY (res_id);


--
-- TOC entry 1922 (class 2606 OID 139882)
-- Dependencies: 1593 1593
-- Name: t_ref_exchange_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_EXCHANGE_TYPE"
    ADD CONSTRAINT t_ref_exchange_type_pkey PRIMARY KEY (chg_id);


--
-- TOC entry 1924 (class 2606 OID 139969)
-- Dependencies: 1594 1594
-- Name: t_ref_month_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_MONTH"
    ADD CONSTRAINT t_ref_month_pkey PRIMARY KEY (rmn_id);


--
-- TOC entry 1956 (class 2606 OID 140006)
-- Dependencies: 1606 1606
-- Name: t_ref_product_pkey01; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_PRODUCT"
    ADD CONSTRAINT t_ref_product_pkey01 PRIMARY KEY (rpc_id);


--
-- TOC entry 1926 (class 2606 OID 139833)
-- Dependencies: 1595 1595
-- Name: t_ref_product_request_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_PRODUCT_REQUEST_STATUS"
    ADD CONSTRAINT t_ref_product_request_status_pkey PRIMARY KEY (rpr_id);


--
-- TOC entry 1928 (class 2606 OID 139954)
-- Dependencies: 1596 1596
-- Name: t_ref_product_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_PRODUCT_TYPE"
    ADD CONSTRAINT t_ref_product_type_pkey PRIMARY KEY (rpt_id);


--
-- TOC entry 1930 (class 2606 OID 139405)
-- Dependencies: 1597 1597
-- Name: t_ref_relation_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_RELATIONS_STATUS"
    ADD CONSTRAINT t_ref_relation_status_pkey PRIMARY KEY (rrs_id);


--
-- TOC entry 1932 (class 2606 OID 139432)
-- Dependencies: 1598 1598
-- Name: t_ref_relation_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_RELATION_TYPE"
    ADD CONSTRAINT t_ref_relation_type_pkey PRIMARY KEY (rrt_id);


--
-- TOC entry 1934 (class 2606 OID 139416)
-- Dependencies: 1599 1599
-- Name: t_ref_user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REF_USER_ROLE"
    ADD CONSTRAINT t_ref_user_role_pkey PRIMARY KEY (rur_id);


--
-- TOC entry 1936 (class 2606 OID 139447)
-- Dependencies: 1600 1600
-- Name: t_ref_user_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "T_REF_USER_STATUS"
    ADD CONSTRAINT t_ref_user_status_pkey PRIMARY KEY (rus_id);


--
-- TOC entry 1938 (class 2606 OID 123050)
-- Dependencies: 1601 1601
-- Name: t_relationship_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_RELATIONSHIP"
    ADD CONSTRAINT t_relationship_pkey PRIMARY KEY (rlt_id);


--
-- TOC entry 1942 (class 2606 OID 123052)
-- Dependencies: 1602 1602
-- Name: t_request_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_REQUEST_PRODUCT"
    ADD CONSTRAINT t_request_product_pkey PRIMARY KEY (rqt_id);


--
-- TOC entry 1958 (class 2606 OID 140105)
-- Dependencies: 1611 1611
-- Name: t_subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_SUBSCRIPTION"
    ADD CONSTRAINT t_subscription_pkey PRIMARY KEY (sbr_id);


--
-- TOC entry 1896 (class 2606 OID 109712)
-- Dependencies: 1574 1574
-- Name: t_user_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_USER"
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (usr_id);


--
-- TOC entry 1912 (class 2606 OID 140039)
-- Dependencies: 1588 1588 1588
-- Name: tj_reap_calendar_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "TJ_REAP_CALENDAR"
    ADD CONSTRAINT tj_reap_calendar_pkey PRIMARY KEY (rpc_id, rmn_id);


--
-- TOC entry 1944 (class 2606 OID 140061)
-- Dependencies: 1603 1603 1603
-- Name: tj_seed_month_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "TJ_SEED_CALENDAR"
    ADD CONSTRAINT tj_seed_month_pkey PRIMARY KEY (rpc_id, rmn_id);


--
-- TOC entry 1940 (class 2606 OID 123058)
-- Dependencies: 1601 1601 1601
-- Name: unq_t_relationship_user_id_one_user_id_two; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_RELATIONSHIP"
    ADD CONSTRAINT unq_t_relationship_user_id_one_user_id_two UNIQUE (rlt_usr_id_sender, rlt_usr_id_recipient);


--
-- TOC entry 1898 (class 2606 OID 139362)
-- Dependencies: 1574 1574
-- Name: unq_t_user_username; Type: CONSTRAINT; Schema: public; Owner: freeveggie; Tablespace: 
--

ALTER TABLE ONLY "T_USER"
    ADD CONSTRAINT unq_t_user_username UNIQUE (usr_username);


--
-- TOC entry 1969 (class 2606 OID 139583)
-- Dependencies: 1583 1945 1604
-- Name: fk_t_address_rcn_d; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_ADDRESS"
    ADD CONSTRAINT fk_t_address_rcn_d FOREIGN KEY (adr_rcn_id) REFERENCES "T_REF_COUNTRY"(rcn_id);


--
-- TOC entry 1970 (class 2606 OID 139716)
-- Dependencies: 1575 1584 1899
-- Name: fk_t_comment_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_COMMENT_GARDEN"
    ADD CONSTRAINT fk_t_comment_grd_id FOREIGN KEY (cgr_grd_id) REFERENCES "T_GARDEN"(grd_id);


--
-- TOC entry 1977 (class 2606 OID 139911)
-- Dependencies: 1585 1917 1591
-- Name: fk_t_comment_product_note_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_COMMENT_PRODUCT"
    ADD CONSTRAINT fk_t_comment_product_note_id FOREIGN KEY (cpr_ren_id) REFERENCES "T_REF_EVALUATION_NOTE"(ren_id);


--
-- TOC entry 1974 (class 2606 OID 123076)
-- Dependencies: 1893 1585 1573
-- Name: fk_t_comment_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_COMMENT_PRODUCT"
    ADD CONSTRAINT fk_t_comment_product_prd_id FOREIGN KEY (cpr_prd_id) REFERENCES "T_PRODUCT"(prd_id);


--
-- TOC entry 1976 (class 2606 OID 139899)
-- Dependencies: 1592 1919 1585
-- Name: fk_t_comment_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_COMMENT_PRODUCT"
    ADD CONSTRAINT fk_t_comment_product_sts_id FOREIGN KEY (cpr_res_id) REFERENCES "T_REF_EVALUATION_STATUS"(res_id);


--
-- TOC entry 1975 (class 2606 OID 123086)
-- Dependencies: 1585 1574 1895
-- Name: fk_t_comment_product_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_COMMENT_PRODUCT"
    ADD CONSTRAINT fk_t_comment_product_user_id FOREIGN KEY (cpr_usr_id) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1973 (class 2606 OID 139773)
-- Dependencies: 1917 1591 1584
-- Name: fk_t_comment_ren_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_COMMENT_GARDEN"
    ADD CONSTRAINT fk_t_comment_ren_id FOREIGN KEY (cgr_ren_id) REFERENCES "T_REF_EVALUATION_NOTE"(ren_id);


--
-- TOC entry 1972 (class 2606 OID 139753)
-- Dependencies: 1919 1592 1584
-- Name: fk_t_comment_res_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_COMMENT_GARDEN"
    ADD CONSTRAINT fk_t_comment_res_id FOREIGN KEY (cgr_res_id) REFERENCES "T_REF_EVALUATION_STATUS"(res_id);


--
-- TOC entry 1971 (class 2606 OID 139731)
-- Dependencies: 1895 1574 1584
-- Name: fk_t_comment_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_COMMENT_GARDEN"
    ADD CONSTRAINT fk_t_comment_usr_id FOREIGN KEY (cgr_usr_id) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1967 (class 2606 OID 123091)
-- Dependencies: 1901 1575 1583
-- Name: fk_t_garden_adr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_GARDEN"
    ADD CONSTRAINT fk_t_garden_adr_id FOREIGN KEY (grd_adr_id) REFERENCES "T_ADDRESS"(adr_id);


--
-- TOC entry 1968 (class 2606 OID 139630)
-- Dependencies: 1575 1574 1895
-- Name: fk_t_garden_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_GARDEN"
    ADD CONSTRAINT fk_t_garden_usr_id FOREIGN KEY (grd_usr_id) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1993 (class 2606 OID 139811)
-- Dependencies: 1899 1575 1605
-- Name: fk_t_like_garden_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_LIKE_GARDEN"
    ADD CONSTRAINT fk_t_like_garden_grd_id FOREIGN KEY (lgr_grd_id) REFERENCES "T_GARDEN"(grd_id);


--
-- TOC entry 1994 (class 2606 OID 139816)
-- Dependencies: 1592 1605 1919
-- Name: fk_t_like_garden_res_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_LIKE_GARDEN"
    ADD CONSTRAINT fk_t_like_garden_res_id FOREIGN KEY (lgr_res_id) REFERENCES "T_REF_EVALUATION_STATUS"(res_id);


--
-- TOC entry 1995 (class 2606 OID 139821)
-- Dependencies: 1605 1895 1574
-- Name: fk_t_like_garden_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_LIKE_GARDEN"
    ADD CONSTRAINT fk_t_like_garden_usr_id FOREIGN KEY (lgr_usr_id) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1978 (class 2606 OID 123116)
-- Dependencies: 1586 1573 1893
-- Name: fk_t_like_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_LIKE_PRODUCT"
    ADD CONSTRAINT fk_t_like_product_prd_id FOREIGN KEY (lpr_prd_id) REFERENCES "T_PRODUCT"(prd_id);


--
-- TOC entry 1980 (class 2606 OID 140071)
-- Dependencies: 1586 1919 1592
-- Name: fk_t_like_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_LIKE_PRODUCT"
    ADD CONSTRAINT fk_t_like_product_sts_id FOREIGN KEY (lpr_res_id) REFERENCES "T_REF_EVALUATION_STATUS"(res_id);


--
-- TOC entry 1979 (class 2606 OID 123126)
-- Dependencies: 1574 1586 1895
-- Name: fk_t_like_product_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_LIKE_PRODUCT"
    ADD CONSTRAINT fk_t_like_product_user_id FOREIGN KEY (lpr_usr_id) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1959 (class 2606 OID 109820)
-- Dependencies: 1573 1575 1899
-- Name: fk_t_product_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_grd_id FOREIGN KEY (prd_grd_id) REFERENCES "T_GARDEN"(grd_id);


--
-- TOC entry 1961 (class 2606 OID 139925)
-- Dependencies: 1573 1913 1589
-- Name: fk_t_product_rcm_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_rcm_id FOREIGN KEY (prd_rcm_id) REFERENCES "T_REF_CULTURE_MODE"(rcm_id);


--
-- TOC entry 1962 (class 2606 OID 139940)
-- Dependencies: 1915 1590 1573
-- Name: fk_t_product_rct_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_rct_id FOREIGN KEY (prd_rct_id) REFERENCES "T_REF_CULTURE_TYPE"(rct_id);


--
-- TOC entry 1960 (class 2606 OID 139883)
-- Dependencies: 1593 1573 1921
-- Name: fk_t_product_ret_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_PRODUCT"
    ADD CONSTRAINT fk_t_product_ret_id FOREIGN KEY (prd_ret_id) REFERENCES "T_REF_EXCHANGE_TYPE"(chg_id);


--
-- TOC entry 1981 (class 2606 OID 140096)
-- Dependencies: 1574 1895 1587
-- Name: fk_t_profil_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_PROFIL"
    ADD CONSTRAINT fk_t_profil_usr_id FOREIGN KEY (prf_id) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1983 (class 2606 OID 140040)
-- Dependencies: 1594 1588 1923
-- Name: fk_t_reap_calendar_mnt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "TJ_REAP_CALENDAR"
    ADD CONSTRAINT fk_t_reap_calendar_mnt_id FOREIGN KEY (rmn_id) REFERENCES "T_REF_MONTH"(rmn_id);


--
-- TOC entry 1982 (class 2606 OID 140029)
-- Dependencies: 1955 1606 1588
-- Name: fk_t_reap_calendar_rpc_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "TJ_REAP_CALENDAR"
    ADD CONSTRAINT fk_t_reap_calendar_rpc_id FOREIGN KEY (rpc_id) REFERENCES "T_REF_PRODUCT"(rpc_id);


--
-- TOC entry 1963 (class 2606 OID 140012)
-- Dependencies: 1573 1955 1606
-- Name: fk_t_ref_product_rpc_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_PRODUCT"
    ADD CONSTRAINT fk_t_ref_product_rpc_id FOREIGN KEY (prd_rpc_id) REFERENCES "T_REF_PRODUCT"(rpc_id);


--
-- TOC entry 1996 (class 2606 OID 140007)
-- Dependencies: 1606 1596 1927
-- Name: fk_t_ref_product_rpt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_REF_PRODUCT"
    ADD CONSTRAINT fk_t_ref_product_rpt_id FOREIGN KEY (rpc_rpt_id) REFERENCES "T_REF_PRODUCT_TYPE"(rpt_id);


--
-- TOC entry 1984 (class 2606 OID 139588)
-- Dependencies: 1929 1601 1597
-- Name: fk_t_relationship_rss_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_RELATIONSHIP"
    ADD CONSTRAINT fk_t_relationship_rss_id FOREIGN KEY (rlt_rrs_id) REFERENCES "T_REF_RELATIONS_STATUS"(rrs_id);


--
-- TOC entry 1985 (class 2606 OID 139593)
-- Dependencies: 1601 1598 1931
-- Name: fk_t_relationship_rtt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_RELATIONSHIP"
    ADD CONSTRAINT fk_t_relationship_rtt_id FOREIGN KEY (rlt_rrt_id) REFERENCES "T_REF_RELATION_TYPE"(rrt_id);


--
-- TOC entry 1987 (class 2606 OID 139603)
-- Dependencies: 1601 1574 1895
-- Name: fk_t_relationship_usr_id_recipient; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_RELATIONSHIP"
    ADD CONSTRAINT fk_t_relationship_usr_id_recipient FOREIGN KEY (rlt_usr_id_recipient) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1986 (class 2606 OID 139598)
-- Dependencies: 1895 1601 1574
-- Name: fk_t_relationship_usr_id_sender; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_RELATIONSHIP"
    ADD CONSTRAINT fk_t_relationship_usr_id_sender FOREIGN KEY (rlt_usr_id_sender) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1990 (class 2606 OID 123231)
-- Dependencies: 1602 1573 1893
-- Name: fk_t_request_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_REQUEST_PRODUCT"
    ADD CONSTRAINT fk_t_request_product_prd_id FOREIGN KEY (rqt_prd_id) REFERENCES "T_PRODUCT"(prd_id);


--
-- TOC entry 1988 (class 2606 OID 139867)
-- Dependencies: 1925 1595 1602
-- Name: fk_t_request_product_rpr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_REQUEST_PRODUCT"
    ADD CONSTRAINT fk_t_request_product_rpr_id FOREIGN KEY (rqt_rpr_id) REFERENCES "T_REF_PRODUCT_REQUEST_STATUS"(rpr_id);


--
-- TOC entry 1989 (class 2606 OID 139872)
-- Dependencies: 1895 1602 1574
-- Name: fk_t_request_product_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_REQUEST_PRODUCT"
    ADD CONSTRAINT fk_t_request_product_usr_id FOREIGN KEY ("rqt _usr_id") REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1992 (class 2606 OID 140062)
-- Dependencies: 1603 1955 1606
-- Name: fk_t_seed_calendar_rpc_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "TJ_SEED_CALENDAR"
    ADD CONSTRAINT fk_t_seed_calendar_rpc_id FOREIGN KEY (rpc_id) REFERENCES "T_REF_PRODUCT"(rpc_id);


--
-- TOC entry 1991 (class 2606 OID 140051)
-- Dependencies: 1594 1603 1923
-- Name: fk_t_seed_month_mnt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "TJ_SEED_CALENDAR"
    ADD CONSTRAINT fk_t_seed_month_mnt_id FOREIGN KEY (rmn_id) REFERENCES "T_REF_MONTH"(rmn_id);


--
-- TOC entry 1997 (class 2606 OID 140106)
-- Dependencies: 1895 1574 1611
-- Name: fk_t_subscription_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_SUBSCRIPTION"
    ADD CONSTRAINT fk_t_subscription_usr_id FOREIGN KEY (sbr_id) REFERENCES "T_USER"(usr_id);


--
-- TOC entry 1964 (class 2606 OID 123256)
-- Dependencies: 1583 1574 1901
-- Name: fk_t_user_adr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_USER"
    ADD CONSTRAINT fk_t_user_adr_id FOREIGN KEY (usr_adr_id) REFERENCES "T_ADDRESS"(adr_id);


--
-- TOC entry 1965 (class 2606 OID 139543)
-- Dependencies: 1574 1599 1933
-- Name: fk_t_user_rur_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_USER"
    ADD CONSTRAINT fk_t_user_rur_id FOREIGN KEY (usr_rur_id) REFERENCES "T_REF_USER_ROLE"(rur_id);


--
-- TOC entry 1966 (class 2606 OID 139548)
-- Dependencies: 1935 1600 1574
-- Name: fk_t_user_rus_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggie
--

ALTER TABLE ONLY "T_USER"
    ADD CONSTRAINT fk_t_user_rus_id FOREIGN KEY (usr_rus_id) REFERENCES "T_REF_USER_STATUS"(rus_id);


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-12-20 22:56:50

--
-- PostgreSQL database dump complete
--

