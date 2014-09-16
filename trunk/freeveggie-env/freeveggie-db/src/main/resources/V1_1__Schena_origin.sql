--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


SET search_path = public, pg_catalog;

--
-- Name: s_adr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_adr_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_adr_seq OWNER TO freeveggieprod;

--
-- Name: s_cgr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_cgr_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_cgr_seq OWNER TO freeveggieprod;

--
-- Name: s_cpr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_cpr_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_cpr_seq OWNER TO freeveggieprod;

--
-- Name: s_grd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_grd_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_grd_seq OWNER TO freeveggieprod;

--
-- Name: s_lgr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_lgr_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_lgr_seq OWNER TO freeveggieprod;

--
-- Name: s_lpr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_lpr_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_lpr_seq OWNER TO freeveggieprod;

--
-- Name: s_prd_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_prd_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_prd_seq OWNER TO freeveggieprod;

--
-- Name: s_rlt_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_rlt_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_rlt_seq OWNER TO freeveggieprod;

--
-- Name: s_rqt_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_rqt_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_rqt_seq OWNER TO freeveggieprod;

--
-- Name: s_usr_seq; Type: SEQUENCE; Schema: public; Owner: freeveggieprod
--

CREATE SEQUENCE s_usr_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.s_usr_seq OWNER TO freeveggieprod;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: t_address; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_address (
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


ALTER TABLE public.t_address OWNER TO freeveggieprod;

--
-- Name: TABLE t_address; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_address IS 'The address of the user and/or the garden';


--
-- Name: COLUMN t_address.adr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_address.adr_id IS 'The database id';


--
-- Name: COLUMN t_address.adr_name; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_address.adr_name IS 'The address name. This is a user information';


--
-- Name: COLUMN t_address.adr_street_number; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_address.adr_street_number IS 'The street number';


--
-- Name: COLUMN t_address.adr_street_name; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_address.adr_street_name IS 'The address line one';


--
-- Name: COLUMN t_address.adr_rci_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_address.adr_rci_id IS 'The city address';


--
-- Name: t_comment_garden; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_comment_garden (
    cgr_id bigint NOT NULL,
    cgr_comment character varying(512) NOT NULL,
    cgr_creation_date timestamp without time zone NOT NULL,
    cgr_res_id integer NOT NULL,
    cgr_grd_id bigint NOT NULL,
    cgr_usr_id bigint NOT NULL,
    cgr_ren_id integer NOT NULL
);


ALTER TABLE public.t_comment_garden OWNER TO freeveggieprod;

--
-- Name: TABLE t_comment_garden; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_comment_garden IS 'List of the comment made on gardens';


--
-- Name: COLUMN t_comment_garden.cgr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_garden.cgr_id IS 'The database id';


--
-- Name: COLUMN t_comment_garden.cgr_comment; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_garden.cgr_comment IS 'The comment left';


--
-- Name: COLUMN t_comment_garden.cgr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_garden.cgr_creation_date IS 'The creation date of the comment';


--
-- Name: COLUMN t_comment_garden.cgr_res_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_garden.cgr_res_id IS 'The comment status';


--
-- Name: COLUMN t_comment_garden.cgr_grd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_garden.cgr_grd_id IS 'The garden commented';


--
-- Name: COLUMN t_comment_garden.cgr_usr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_garden.cgr_usr_id IS 'The user leaving the comment';


--
-- Name: COLUMN t_comment_garden.cgr_ren_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_garden.cgr_ren_id IS 'The evaluation note';


--
-- Name: t_comment_product; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_comment_product (
    cpr_id bigint NOT NULL,
    cpr_comment character varying(512) NOT NULL,
    cpr_creation_date timestamp without time zone NOT NULL,
    cpr_res_id integer NOT NULL,
    cpr_prd_id bigint NOT NULL,
    cpr_usr_id bigint NOT NULL,
    cpr_ren_id integer NOT NULL
);


ALTER TABLE public.t_comment_product OWNER TO freeveggieprod;

--
-- Name: TABLE t_comment_product; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_comment_product IS 'List of comment made on the product';


--
-- Name: COLUMN t_comment_product.cpr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_product.cpr_id IS 'The database id';


--
-- Name: COLUMN t_comment_product.cpr_comment; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_product.cpr_comment IS 'The comment write';


--
-- Name: COLUMN t_comment_product.cpr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_product.cpr_creation_date IS 'The date of when was write';


--
-- Name: COLUMN t_comment_product.cpr_res_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_product.cpr_res_id IS 'The evaluation status';


--
-- Name: COLUMN t_comment_product.cpr_prd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_product.cpr_prd_id IS 'The product being commented';


--
-- Name: COLUMN t_comment_product.cpr_usr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_product.cpr_usr_id IS 'The user writing the comment';


--
-- Name: COLUMN t_comment_product.cpr_ren_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_comment_product.cpr_ren_id IS 'The value of the note';


--
-- Name: t_garden; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_garden (
    grd_id bigint NOT NULL,
    grd_creation_date timestamp without time zone NOT NULL,
    grd_description character varying(512),
    grd_name character varying(32) NOT NULL,
    grd_img_filename character varying(128),
    grd_usr_id bigint NOT NULL,
    grd_sts_id integer NOT NULL,
    grd_adr_id bigint NOT NULL
);


ALTER TABLE public.t_garden OWNER TO freeveggieprod;

--
-- Name: TABLE t_garden; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_garden IS 'The garden';


--
-- Name: COLUMN t_garden.grd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_garden.grd_id IS 'The database id';


--
-- Name: COLUMN t_garden.grd_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_garden.grd_creation_date IS 'The date the garden was create';


--
-- Name: COLUMN t_garden.grd_description; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_garden.grd_description IS 'The user description of the garden';


--
-- Name: COLUMN t_garden.grd_name; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_garden.grd_name IS 'A user visible address name';


--
-- Name: COLUMN t_garden.grd_img_filename; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_garden.grd_img_filename IS 'The image file name of the garden';


--
-- Name: COLUMN t_garden.grd_usr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_garden.grd_usr_id IS 'The owner';


--
-- Name: COLUMN t_garden.grd_sts_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_garden.grd_sts_id IS 'The status of the garden';


--
-- Name: COLUMN t_garden.grd_adr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_garden.grd_adr_id IS 'The garden address';


--
-- Name: t_like_garden; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_like_garden (
    lgr_id bigint NOT NULL,
    lgr_creation_date timestamp without time zone NOT NULL,
    lgr_res_id integer NOT NULL,
    lgr_grd_id bigint NOT NULL,
    lgr_usr_id bigint NOT NULL
);


ALTER TABLE public.t_like_garden OWNER TO freeveggieprod;

--
-- Name: TABLE t_like_garden; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_like_garden IS 'The list of garden like by user';


--
-- Name: COLUMN t_like_garden.lgr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_garden.lgr_id IS 'The database id';


--
-- Name: COLUMN t_like_garden.lgr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_garden.lgr_creation_date IS 'The creation date of the like';


--
-- Name: COLUMN t_like_garden.lgr_res_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_garden.lgr_res_id IS 'The evaluation status';


--
-- Name: COLUMN t_like_garden.lgr_grd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_garden.lgr_grd_id IS 'The garden evaluated';


--
-- Name: COLUMN t_like_garden.lgr_usr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_garden.lgr_usr_id IS 'The user that like the garden';


--
-- Name: t_like_product; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_like_product (
    lpr_id bigint NOT NULL,
    lpr_creation_date timestamp without time zone NOT NULL,
    lpr_res_id integer NOT NULL,
    lpr_prd_id bigint NOT NULL,
    lpr_usr_id bigint NOT NULL
);


ALTER TABLE public.t_like_product OWNER TO freeveggieprod;

--
-- Name: TABLE t_like_product; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_like_product IS 'The list of product like by user';


--
-- Name: COLUMN t_like_product.lpr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_product.lpr_id IS 'The database id';


--
-- Name: COLUMN t_like_product.lpr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_product.lpr_creation_date IS 'The date when the like have been created';


--
-- Name: COLUMN t_like_product.lpr_res_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_product.lpr_res_id IS 'The evaluation status';


--
-- Name: COLUMN t_like_product.lpr_prd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_product.lpr_prd_id IS 'The product';


--
-- Name: COLUMN t_like_product.lpr_usr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_like_product.lpr_usr_id IS 'The user who like the product';


--
-- Name: t_picture_garden; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_picture_garden (
    grp_id bigint NOT NULL,
    grp_mine_type bigint NOT NULL,
    grp_grd_id bigint,
    picture bit varying NOT NULL,
    grp_creation_date timestamp without time zone NOT NULL
);


ALTER TABLE public.t_picture_garden OWNER TO freeveggieprod;

--
-- Name: COLUMN t_picture_garden.grp_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_garden.grp_id IS 'Database id';


--
-- Name: COLUMN t_picture_garden.grp_mine_type; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_garden.grp_mine_type IS 'Thie picture mine type';


--
-- Name: COLUMN t_picture_garden.grp_grd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_garden.grp_grd_id IS 'Garden Id';


--
-- Name: COLUMN t_picture_garden.picture; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_garden.picture IS 'The picture. In byte.';


--
-- Name: COLUMN t_picture_garden.grp_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_garden.grp_creation_date IS 'The picture creation date.';


--
-- Name: t_picture_product; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_picture_product (
    prp_id bigint NOT NULL,
    prp_mine_type bigint NOT NULL,
    prp_prd_id bigint,
    picture bit varying NOT NULL,
    prp_creation_date timestamp without time zone NOT NULL
);


ALTER TABLE public.t_picture_product OWNER TO freeveggieprod;

--
-- Name: COLUMN t_picture_product.prp_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_product.prp_id IS 'Database id';


--
-- Name: COLUMN t_picture_product.prp_mine_type; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_product.prp_mine_type IS 'The picture mine type';


--
-- Name: COLUMN t_picture_product.prp_prd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_product.prp_prd_id IS 'Garden Id';


--
-- Name: COLUMN t_picture_product.picture; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_product.picture IS 'The picture. In byte.';


--
-- Name: COLUMN t_picture_product.prp_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_picture_product.prp_creation_date IS 'The picture creation date.';


--
-- Name: t_product; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_product (
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


ALTER TABLE public.t_product OWNER TO freeveggieprod;

--
-- Name: TABLE t_product; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_product IS 'The product made by user';


--
-- Name: COLUMN t_product.prd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_id IS 'The database id';


--
-- Name: COLUMN t_product.prd_name; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_name IS 'The product name';


--
-- Name: COLUMN t_product.prd_rcm_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_rcm_id IS 'The culture mode';


--
-- Name: COLUMN t_product.prd_rct_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_rct_id IS 'The exchange type';


--
-- Name: COLUMN t_product.prd_description; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_description IS 'The user description of the product';


--
-- Name: COLUMN t_product.prd_chg_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_chg_id IS 'The type of exchange for this product';


--
-- Name: COLUMN t_product.prd_img_filename; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_img_filename IS 'The file name of the product image';


--
-- Name: COLUMN t_product.prd_quantity; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_quantity IS 'The quantity left available to exchange';


--
-- Name: COLUMN t_product.prd_grd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_grd_id IS 'The garden where the product grow';


--
-- Name: COLUMN t_product.prd_sts_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_sts_id IS 'The product status';


--
-- Name: COLUMN t_product.prd_rpc_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_product.prd_rpc_id IS 'The reference product';


--
-- Name: t_profil; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_profil (
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


ALTER TABLE public.t_profil OWNER TO freeveggieprod;

--
-- Name: TABLE t_profil; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_profil IS 'User profil';


--
-- Name: COLUMN t_profil.prf_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_id IS 'The database id.';


--
-- Name: COLUMN t_profil.prf_experience; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_experience IS 'A user experience of free veggie';


--
-- Name: COLUMN t_profil.prf_interest; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_interest IS 'The interest of the user ';


--
-- Name: COLUMN t_profil.prf_other; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_other IS 'Anything else that the user wanted to say';


--
-- Name: COLUMN t_profil.prf_participation; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_participation IS 'A description why the user subscribe';


--
-- Name: COLUMN t_profil.prf_description; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_description IS 'A description of the user';


--
-- Name: COLUMN t_profil.prf_philosophy; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_philosophy IS 'Life philosophies of the user';


--
-- Name: COLUMN t_profil.prf_meals; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_meals IS 'The prefered meal of the user';


--
-- Name: COLUMN t_profil.prf_img_filename; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_img_filename IS 'The file name of the user picture';


--
-- Name: COLUMN t_profil.prf_reason; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_profil.prf_reason IS 'The reason why the user got to this website';


--
-- Name: t_ref_city; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_city (
    rci_id integer NOT NULL,
    rci_name character varying(64) NOT NULL,
    rci_zip_code character varying(64) NOT NULL,
    rci_rre_id integer NOT NULL
);


ALTER TABLE public.t_ref_city OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_city; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_city IS 'List of the cities';


--
-- Name: t_ref_country; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_country (
    rcn_id integer NOT NULL,
    rcn_code_iso_a2 character(2) NOT NULL,
    rcn_code_iso_a3 character(3) NOT NULL,
    rcn_code_iso_number integer NOT NULL,
    rcn_country_name character varying(64) NOT NULL
);


ALTER TABLE public.t_ref_country OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_country; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_country IS 'The list of country';


--
-- Name: COLUMN t_ref_country.rcn_code_iso_a2; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_country.rcn_code_iso_a2 IS 'The is 2 letters code';


--
-- Name: COLUMN t_ref_country.rcn_code_iso_a3; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_country.rcn_code_iso_a3 IS 'The iso 3 letters code';


--
-- Name: COLUMN t_ref_country.rcn_code_iso_number; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_country.rcn_code_iso_number IS 'The iso code number';


--
-- Name: COLUMN t_ref_country.rcn_country_name; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_country.rcn_country_name IS 'The country name';


--
-- Name: t_ref_culture_mode; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_culture_mode (
    rcm_id integer NOT NULL,
    rcm_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_culture_mode OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_culture_mode; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_culture_mode IS 'The choice of culture mode';


--
-- Name: COLUMN t_ref_culture_mode.rcm_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_culture_mode.rcm_id IS 'The database id';


--
-- Name: COLUMN t_ref_culture_mode.rcm_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_culture_mode.rcm_libelle IS 'The culture mode description ';


--
-- Name: t_ref_culture_type; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_culture_type (
    rct_id integer NOT NULL,
    rct_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_culture_type OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_culture_type; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_culture_type IS 'The choice of culture type';


--
-- Name: COLUMN t_ref_culture_type.rct_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_culture_type.rct_id IS 'The database id';


--
-- Name: COLUMN t_ref_culture_type.rct_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_culture_type.rct_libelle IS 'The culture type description';


--
-- Name: t_ref_evaluation_note; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_evaluation_note (
    ren_id integer NOT NULL,
    ren_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_evaluation_note OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_evaluation_note; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_evaluation_note IS 'The value of a note (for a garden or a product)';


--
-- Name: COLUMN t_ref_evaluation_note.ren_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_evaluation_note.ren_id IS 'The database id';


--
-- Name: COLUMN t_ref_evaluation_note.ren_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_evaluation_note.ren_libelle IS 'The evaluation note description';


--
-- Name: t_ref_evaluation_status; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_evaluation_status (
    res_id integer NOT NULL,
    res_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_evaluation_status OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_evaluation_status; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_evaluation_status IS 'The status of an evaluation(product, garden, ...)';


--
-- Name: COLUMN t_ref_evaluation_status.res_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_evaluation_status.res_id IS 'The database id';


--
-- Name: COLUMN t_ref_evaluation_status.res_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_evaluation_status.res_libelle IS 'The evaluation status description';


--
-- Name: t_ref_exchange_type; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_exchange_type (
    chg_id integer NOT NULL,
    chg_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_exchange_type OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_exchange_type; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_exchange_type IS 'Choice of exchange type';


--
-- Name: COLUMN t_ref_exchange_type.chg_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_exchange_type.chg_id IS 'The database id';


--
-- Name: COLUMN t_ref_exchange_type.chg_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_exchange_type.chg_libelle IS 'The exchange type description';


--
-- Name: t_ref_month; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_month (
    rmn_id integer NOT NULL,
    rmn_name character varying(32)
);


ALTER TABLE public.t_ref_month OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_month; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_month IS 'The reference mont of a year';


--
-- Name: COLUMN t_ref_month.rmn_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_month.rmn_id IS 'The month number';


--
-- Name: COLUMN t_ref_month.rmn_name; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_month.rmn_name IS 'The month description';


--
-- Name: t_ref_product; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_product (
    rpc_id integer NOT NULL,
    rpc_carbohydrate double precision,
    rpc_lipid double precision,
    rpc_name character varying(64) NOT NULL,
    rpc_img_filename character varying(128),
    rpc_protein double precision,
    rpc_rpt_id integer NOT NULL
);


ALTER TABLE public.t_ref_product OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_product; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_product IS 'The reference of product';


--
-- Name: COLUMN t_ref_product.rpc_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product.rpc_id IS 'The database id';


--
-- Name: COLUMN t_ref_product.rpc_carbohydrate; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product.rpc_carbohydrate IS 'The quantity of carbohydrate in the product';


--
-- Name: COLUMN t_ref_product.rpc_lipid; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product.rpc_lipid IS 'The quantity of lipid in the product';


--
-- Name: COLUMN t_ref_product.rpc_name; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product.rpc_name IS 'The name of the product gived by the producer';


--
-- Name: COLUMN t_ref_product.rpc_img_filename; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product.rpc_img_filename IS 'The file name of the product image';


--
-- Name: COLUMN t_ref_product.rpc_protein; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product.rpc_protein IS 'The quantity of protein in the product';


--
-- Name: COLUMN t_ref_product.rpc_rpt_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product.rpc_rpt_id IS 'The product type';


--
-- Name: t_ref_product_request_status; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_product_request_status (
    rpr_id integer NOT NULL,
    rpr_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_product_request_status OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_product_request_status; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_product_request_status IS 'The posible state of a product request';


--
-- Name: COLUMN t_ref_product_request_status.rpr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product_request_status.rpr_id IS 'The database id';


--
-- Name: COLUMN t_ref_product_request_status.rpr_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product_request_status.rpr_libelle IS 'The product request status description';


--
-- Name: t_ref_product_type; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_product_type (
    rpt_id integer NOT NULL,
    rpt_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_product_type OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_product_type; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_product_type IS 'The reference of product type (fruit or vegetable)';


--
-- Name: COLUMN t_ref_product_type.rpt_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product_type.rpt_id IS 'The database id';


--
-- Name: COLUMN t_ref_product_type.rpt_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_product_type.rpt_libelle IS 'The product type description';


--
-- Name: t_ref_region; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_region (
    rre_id integer NOT NULL,
    rre_name character varying(64) NOT NULL,
    rre_rst_id integer NOT NULL
);


ALTER TABLE public.t_ref_region OWNER TO freeveggieprod;

--
-- Name: t_ref_relation_type; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_relation_type (
    rrt_id integer NOT NULL,
    rrt_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_relation_type OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_relation_type; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_relation_type IS 'The possible value of a relation type (friends, neighbors, ...)';


--
-- Name: COLUMN t_ref_relation_type.rrt_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_relation_type.rrt_id IS 'The database id';


--
-- Name: COLUMN t_ref_relation_type.rrt_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_relation_type.rrt_libelle IS 'The relation description';


--
-- Name: t_ref_relations_status; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_relations_status (
    rrs_id integer NOT NULL,
    rrs_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_relations_status OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_relations_status; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_relations_status IS 'The posible state of relationship';


--
-- Name: COLUMN t_ref_relations_status.rrs_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_relations_status.rrs_id IS 'The database id.';


--
-- Name: COLUMN t_ref_relations_status.rrs_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_relations_status.rrs_libelle IS 'The relation status description';


--
-- Name: t_ref_state; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_state (
    rst_id integer NOT NULL,
    rst_name character varying(64) NOT NULL,
    rst_rcn_id integer NOT NULL
);


ALTER TABLE public.t_ref_state OWNER TO freeveggieprod;

--
-- Name: t_ref_status; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_status (
    sts_id integer NOT NULL,
    sts_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_status OWNER TO freeveggieprod;

--
-- Name: t_ref_user_role; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_user_role (
    rur_id integer NOT NULL,
    rur_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_user_role OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_user_role; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_user_role IS 'The posible user role';


--
-- Name: COLUMN t_ref_user_role.rur_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_user_role.rur_id IS 'The database id';


--
-- Name: COLUMN t_ref_user_role.rur_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_user_role.rur_libelle IS 'The user role description';


--
-- Name: t_ref_user_status; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_ref_user_status (
    rus_id integer NOT NULL,
    rus_libelle character varying(32) NOT NULL
);


ALTER TABLE public.t_ref_user_status OWNER TO freeveggieprod;

--
-- Name: TABLE t_ref_user_status; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_ref_user_status IS 'The possible state of user (new, blacklisted, ....)';


--
-- Name: COLUMN t_ref_user_status.rus_libelle; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_ref_user_status.rus_libelle IS 'The user status description';


--
-- Name: t_relationship; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_relationship (
    rlt_id bigint NOT NULL,
    rlt_answer character varying(512),
    rlt_creation_date timestamp without time zone NOT NULL,
    rlt_rrs_id integer NOT NULL,
    rlt_rrt_id integer NOT NULL,
    rlt_usr_id_sender bigint NOT NULL,
    rlt_usr_id_recipient bigint NOT NULL,
    rlt_request character varying(512) NOT NULL
);


ALTER TABLE public.t_relationship OWNER TO freeveggieprod;

--
-- Name: TABLE t_relationship; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_relationship IS 'The list of user relationship';


--
-- Name: COLUMN t_relationship.rlt_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_relationship.rlt_id IS 'The database id';


--
-- Name: COLUMN t_relationship.rlt_answer; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_relationship.rlt_answer IS 'The answer of the request';


--
-- Name: COLUMN t_relationship.rlt_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_relationship.rlt_creation_date IS 'The date and time when the request was send';


--
-- Name: COLUMN t_relationship.rlt_rrs_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_relationship.rlt_rrs_id IS 'The relation status';


--
-- Name: COLUMN t_relationship.rlt_rrt_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_relationship.rlt_rrt_id IS 'The type of relationship';


--
-- Name: COLUMN t_relationship.rlt_usr_id_sender; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_relationship.rlt_usr_id_sender IS 'The user who send the request';


--
-- Name: COLUMN t_relationship.rlt_usr_id_recipient; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_relationship.rlt_usr_id_recipient IS 'The recipient of the request.';


--
-- Name: COLUMN t_relationship.rlt_request; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_relationship.rlt_request IS 'The request messages send from the recipient';


--
-- Name: t_request_product; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_request_product (
    rqt_id bigint NOT NULL,
    rqt_prd_id bigint NOT NULL,
    rqt_usr_id bigint NOT NULL,
    rqt_quantity double precision NOT NULL,
    rqt_msg character varying(512) NOT NULL,
    rqt_answer_msg character varying(512),
    rqt_rpr_id integer NOT NULL,
    rqt_creation_date timestamp without time zone NOT NULL,
    rqt_answer_date timestamp without time zone,
    rqt_picking_date timestamp without time zone NOT NULL
);


ALTER TABLE public.t_request_product OWNER TO freeveggieprod;

--
-- Name: TABLE t_request_product; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_request_product IS 'The product request made by users';


--
-- Name: COLUMN t_request_product.rqt_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_id IS 'The database id';


--
-- Name: COLUMN t_request_product.rqt_prd_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_prd_id IS 'The product requested';


--
-- Name: COLUMN t_request_product.rqt_usr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_usr_id IS 'The user who send the request';


--
-- Name: COLUMN t_request_product.rqt_quantity; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_quantity IS 'The quantity the request wish to pick';


--
-- Name: COLUMN t_request_product.rqt_msg; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_msg IS 'The request message send by the requester';


--
-- Name: COLUMN t_request_product.rqt_answer_msg; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_answer_msg IS 'The request answer';


--
-- Name: COLUMN t_request_product.rqt_rpr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_rpr_id IS 'The request status';


--
-- Name: COLUMN t_request_product.rqt_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_creation_date IS 'The date of the request have been send';


--
-- Name: COLUMN t_request_product.rqt_answer_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_answer_date IS 'The date the user answer the request';


--
-- Name: COLUMN t_request_product.rqt_picking_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_request_product.rqt_picking_date IS 'The date that the requester wish to pick the product';


--
-- Name: t_subscription; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_subscription (
    sbr_id bigint NOT NULL,
    sbr_share_personal boolean NOT NULL,
    sbr_share_garden boolean NOT NULL,
    sbr_newsletter boolean NOT NULL,
    sbr_freeveggie_agreement boolean NOT NULL
);


ALTER TABLE public.t_subscription OWNER TO freeveggieprod;

--
-- Name: TABLE t_subscription; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_subscription IS 'The subscription of the user';


--
-- Name: COLUMN t_subscription.sbr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_subscription.sbr_id IS 'The database id';


--
-- Name: COLUMN t_subscription.sbr_share_personal; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_subscription.sbr_share_personal IS 'Does the user agree to share his personal information with free veggie partners';


--
-- Name: COLUMN t_subscription.sbr_share_garden; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_subscription.sbr_share_garden IS 'Does the user agree to share his garden information to free veggie partners';


--
-- Name: COLUMN t_subscription.sbr_newsletter; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_subscription.sbr_newsletter IS 'Does the user agree to receive the free veggie newsletter';


--
-- Name: COLUMN t_subscription.sbr_freeveggie_agreement; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_subscription.sbr_freeveggie_agreement IS 'Does the user sign the freeveggie aggreement';


--
-- Name: t_user; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE t_user (
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
    usr_adr_id bigint NOT NULL,
    usr_img_filename character varying(64)
);


ALTER TABLE public.t_user OWNER TO freeveggieprod;

--
-- Name: TABLE t_user; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE t_user IS 'The website users';


--
-- Name: COLUMN t_user.usr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_id IS 'The database id';


--
-- Name: COLUMN t_user.usr_blacklisted_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_blacklisted_date IS 'The date when the user got blacklisted';


--
-- Name: COLUMN t_user.usr_cancellation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_cancellation_date IS 'The date when the user cancel his subscription';


--
-- Name: COLUMN t_user.usr_creation_date; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_creation_date IS 'The date when the account get create';


--
-- Name: COLUMN t_user.usr_email_address; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_email_address IS 'The user email address';


--
-- Name: COLUMN t_user.usr_firstname; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_firstname IS 'The user firstname';


--
-- Name: COLUMN t_user.usr_last_connexion; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_last_connexion IS 'The user last connexion date and time';


--
-- Name: COLUMN t_user.usr_lastname; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_lastname IS 'The user last name';


--
-- Name: COLUMN t_user.usr_password; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_password IS 'The user password encrypted user MD5';


--
-- Name: COLUMN t_user.usr_rur_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_rur_id IS 'The user role ';


--
-- Name: COLUMN t_user.usr_rus_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_rus_id IS 'The user status ';


--
-- Name: COLUMN t_user.usr_temp_password; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_temp_password IS 'The user temporary password. Create after the user ask for a new one because he lost his password. Encrypted under the MD5 algorithme';


--
-- Name: COLUMN t_user.usr_username; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_username IS 'The user login';


--
-- Name: COLUMN t_user.usr_adr_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN t_user.usr_adr_id IS 'The user address';


--
-- Name: tj_reap_calendar; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE tj_reap_calendar (
    rpc_id integer NOT NULL,
    rmn_id integer NOT NULL
);


ALTER TABLE public.tj_reap_calendar OWNER TO freeveggieprod;

--
-- Name: TABLE tj_reap_calendar; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE tj_reap_calendar IS 'The reference product reaping calendar';


--
-- Name: COLUMN tj_reap_calendar.rpc_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN tj_reap_calendar.rpc_id IS 'The product ';


--
-- Name: COLUMN tj_reap_calendar.rmn_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN tj_reap_calendar.rmn_id IS 'The reap mont';


--
-- Name: tj_seed_calendar; Type: TABLE; Schema: public; Owner: freeveggieprod; Tablespace: 
--

CREATE TABLE tj_seed_calendar (
    rpc_id integer NOT NULL,
    rmn_id integer NOT NULL
);


ALTER TABLE public.tj_seed_calendar OWNER TO freeveggieprod;

--
-- Name: TABLE tj_seed_calendar; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON TABLE tj_seed_calendar IS 'The reference month of seedling product';


--
-- Name: COLUMN tj_seed_calendar.rpc_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN tj_seed_calendar.rpc_id IS 'The reference product';


--
-- Name: COLUMN tj_seed_calendar.rmn_id; Type: COMMENT; Schema: public; Owner: freeveggieprod
--

COMMENT ON COLUMN tj_seed_calendar.rmn_id IS 'The seed month';



--
-- Name: t_address_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_address
    ADD CONSTRAINT t_address_pkey PRIMARY KEY (adr_id);


--
-- Name: t_comment_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT t_comment_garden_pkey PRIMARY KEY (cgr_id);


--
-- Name: t_comment_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT t_comment_product_pkey PRIMARY KEY (cpr_id);


--
-- Name: t_garden_picuture_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_picture_garden
    ADD CONSTRAINT t_garden_picuture_pkey PRIMARY KEY (grp_id);


--
-- Name: t_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_garden
    ADD CONSTRAINT t_garden_pkey PRIMARY KEY (grd_id);


--
-- Name: t_like_garden_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_like_garden
    ADD CONSTRAINT t_like_garden_pkey PRIMARY KEY (lgr_id);


--
-- Name: t_like_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_like_product
    ADD CONSTRAINT t_like_product_pkey PRIMARY KEY (lpr_id);


--
-- Name: t_product_picuture_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_picture_product
    ADD CONSTRAINT t_product_picuture_pkey PRIMARY KEY (prp_id);


--
-- Name: t_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT t_product_pkey PRIMARY KEY (prd_id);


--
-- Name: t_profil_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_profil
    ADD CONSTRAINT t_profil_pkey PRIMARY KEY (prf_id);


--
-- Name: t_ref_city_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_city
    ADD CONSTRAINT t_ref_city_pkey PRIMARY KEY (rci_id);


--
-- Name: t_ref_country_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_country
    ADD CONSTRAINT t_ref_country_pkey PRIMARY KEY (rcn_id);


--
-- Name: t_ref_culture_mode_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_culture_mode
    ADD CONSTRAINT t_ref_culture_mode_pkey PRIMARY KEY (rcm_id);


--
-- Name: t_ref_culture_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_culture_type
    ADD CONSTRAINT t_ref_culture_type_pkey PRIMARY KEY (rct_id);


--
-- Name: t_ref_evaluation_note_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_evaluation_note
    ADD CONSTRAINT t_ref_evaluation_note_pkey PRIMARY KEY (ren_id);


--
-- Name: t_ref_evaluation_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_evaluation_status
    ADD CONSTRAINT t_ref_evaluation_status_pkey PRIMARY KEY (res_id);


--
-- Name: t_ref_exchange_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_exchange_type
    ADD CONSTRAINT t_ref_exchange_type_pkey PRIMARY KEY (chg_id);


--
-- Name: t_ref_month_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_month
    ADD CONSTRAINT t_ref_month_pkey PRIMARY KEY (rmn_id);


--
-- Name: t_ref_product_pkey01; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_product
    ADD CONSTRAINT t_ref_product_pkey01 PRIMARY KEY (rpc_id);


--
-- Name: t_ref_product_request_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_product_request_status
    ADD CONSTRAINT t_ref_product_request_status_pkey PRIMARY KEY (rpr_id);


--
-- Name: t_ref_product_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_product_type
    ADD CONSTRAINT t_ref_product_type_pkey PRIMARY KEY (rpt_id);


--
-- Name: t_ref_region_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_region
    ADD CONSTRAINT t_ref_region_pkey PRIMARY KEY (rre_id);


--
-- Name: t_ref_relation_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_relations_status
    ADD CONSTRAINT t_ref_relation_status_pkey PRIMARY KEY (rrs_id);


--
-- Name: t_ref_relation_type_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_relation_type
    ADD CONSTRAINT t_ref_relation_type_pkey PRIMARY KEY (rrt_id);


--
-- Name: t_ref_state_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_state
    ADD CONSTRAINT t_ref_state_pkey PRIMARY KEY (rst_id);


--
-- Name: t_ref_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_status
    ADD CONSTRAINT t_ref_status_pkey PRIMARY KEY (sts_id);


--
-- Name: t_ref_user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_user_role
    ADD CONSTRAINT t_ref_user_role_pkey PRIMARY KEY (rur_id);


--
-- Name: t_ref_user_status_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_ref_user_status
    ADD CONSTRAINT t_ref_user_status_pkey PRIMARY KEY (rus_id);


--
-- Name: t_relationship_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT t_relationship_pkey PRIMARY KEY (rlt_id);


--
-- Name: t_request_product_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_request_product
    ADD CONSTRAINT t_request_product_pkey PRIMARY KEY (rqt_id);


--
-- Name: t_subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_subscription
    ADD CONSTRAINT t_subscription_pkey PRIMARY KEY (sbr_id);


--
-- Name: t_user_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (usr_id);


--
-- Name: tj_reap_calendar_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY tj_reap_calendar
    ADD CONSTRAINT tj_reap_calendar_pkey PRIMARY KEY (rpc_id, rmn_id);


--
-- Name: tj_seed_month_pkey; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY tj_seed_calendar
    ADD CONSTRAINT tj_seed_month_pkey PRIMARY KEY (rpc_id, rmn_id);


--
-- Name: unq_t_comment_garden_cgr_usr_id_cgr_grd_id; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT unq_t_comment_garden_cgr_usr_id_cgr_grd_id UNIQUE (cgr_grd_id, cgr_usr_id);


--
-- Name: unq_t_comment_product_cpr_usr_id_cpr_prd_id; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT unq_t_comment_product_cpr_usr_id_cpr_prd_id UNIQUE (cpr_prd_id, cpr_usr_id);


--
-- Name: unq_t_relationship_user_id_one_user_id_two; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT unq_t_relationship_user_id_one_user_id_two UNIQUE (rlt_usr_id_sender, rlt_usr_id_recipient);


--
-- Name: unq_t_user_username; Type: CONSTRAINT; Schema: public; Owner: freeveggieprod; Tablespace: 
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT unq_t_user_username UNIQUE (usr_username);

--
-- Name: fk_t_address_rci_d; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_address
    ADD CONSTRAINT fk_t_address_rci_d FOREIGN KEY (adr_rci_id) REFERENCES t_ref_city(rci_id);


--
-- Name: fk_t_city_rre_d; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_ref_city
    ADD CONSTRAINT fk_t_city_rre_d FOREIGN KEY (rci_rre_id) REFERENCES t_ref_region(rre_id);


--
-- Name: fk_t_comment_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT fk_t_comment_grd_id FOREIGN KEY (cgr_grd_id) REFERENCES t_garden(grd_id);


--
-- Name: fk_t_comment_product_note_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT fk_t_comment_product_note_id FOREIGN KEY (cpr_ren_id) REFERENCES t_ref_evaluation_note(ren_id);


--
-- Name: fk_t_comment_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT fk_t_comment_product_prd_id FOREIGN KEY (cpr_prd_id) REFERENCES t_product(prd_id);


--
-- Name: fk_t_comment_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT fk_t_comment_product_sts_id FOREIGN KEY (cpr_res_id) REFERENCES t_ref_evaluation_status(res_id);


--
-- Name: fk_t_comment_product_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_comment_product
    ADD CONSTRAINT fk_t_comment_product_user_id FOREIGN KEY (cpr_usr_id) REFERENCES t_user(usr_id);


--
-- Name: fk_t_comment_ren_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT fk_t_comment_ren_id FOREIGN KEY (cgr_ren_id) REFERENCES t_ref_evaluation_note(ren_id);


--
-- Name: fk_t_comment_res_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT fk_t_comment_res_id FOREIGN KEY (cgr_res_id) REFERENCES t_ref_evaluation_status(res_id);


--
-- Name: fk_t_comment_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_comment_garden
    ADD CONSTRAINT fk_t_comment_usr_id FOREIGN KEY (cgr_usr_id) REFERENCES t_user(usr_id);


--
-- Name: fk_t_garden_adr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_garden
    ADD CONSTRAINT fk_t_garden_adr_id FOREIGN KEY (grd_adr_id) REFERENCES t_address(adr_id);


--
-- Name: fk_t_garden_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_garden
    ADD CONSTRAINT fk_t_garden_sts_id FOREIGN KEY (grd_sts_id) REFERENCES t_ref_status(sts_id);


--
-- Name: fk_t_garden_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_garden
    ADD CONSTRAINT fk_t_garden_usr_id FOREIGN KEY (grd_usr_id) REFERENCES t_user(usr_id);


--
-- Name: fk_t_like_garden_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_like_garden
    ADD CONSTRAINT fk_t_like_garden_grd_id FOREIGN KEY (lgr_grd_id) REFERENCES t_garden(grd_id);


--
-- Name: fk_t_like_garden_res_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_like_garden
    ADD CONSTRAINT fk_t_like_garden_res_id FOREIGN KEY (lgr_res_id) REFERENCES t_ref_evaluation_status(res_id);


--
-- Name: fk_t_like_garden_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_like_garden
    ADD CONSTRAINT fk_t_like_garden_usr_id FOREIGN KEY (lgr_usr_id) REFERENCES t_user(usr_id);


--
-- Name: fk_t_like_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_like_product
    ADD CONSTRAINT fk_t_like_product_prd_id FOREIGN KEY (lpr_prd_id) REFERENCES t_product(prd_id);


--
-- Name: fk_t_like_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_like_product
    ADD CONSTRAINT fk_t_like_product_sts_id FOREIGN KEY (lpr_res_id) REFERENCES t_ref_evaluation_status(res_id);


--
-- Name: fk_t_like_product_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_like_product
    ADD CONSTRAINT fk_t_like_product_user_id FOREIGN KEY (lpr_usr_id) REFERENCES t_user(usr_id);


--
-- Name: fk_t_picture_garden_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_picture_garden
    ADD CONSTRAINT fk_t_picture_garden_grd_id FOREIGN KEY (grp_grd_id) REFERENCES t_garden(grd_id);


--
-- Name: fk_t_picture_product_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_picture_product
    ADD CONSTRAINT fk_t_picture_product_grd_id FOREIGN KEY (prp_prd_id) REFERENCES t_garden(grd_id);


--
-- Name: fk_t_product_grd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_product_grd_id FOREIGN KEY (prd_grd_id) REFERENCES t_garden(grd_id);


--
-- Name: fk_t_product_rcm_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_product_rcm_id FOREIGN KEY (prd_rcm_id) REFERENCES t_ref_culture_mode(rcm_id);


--
-- Name: fk_t_product_rct_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_product_rct_id FOREIGN KEY (prd_rct_id) REFERENCES t_ref_culture_type(rct_id);


--
-- Name: fk_t_product_ret_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_product_ret_id FOREIGN KEY (prd_chg_id) REFERENCES t_ref_exchange_type(chg_id);


--
-- Name: fk_t_product_sts_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_product_sts_id FOREIGN KEY (prd_sts_id) REFERENCES t_ref_status(sts_id);


--
-- Name: fk_t_profil_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_profil
    ADD CONSTRAINT fk_t_profil_usr_id FOREIGN KEY (prf_id) REFERENCES t_user(usr_id);


--
-- Name: fk_t_reap_calendar_mnt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY tj_reap_calendar
    ADD CONSTRAINT fk_t_reap_calendar_mnt_id FOREIGN KEY (rmn_id) REFERENCES t_ref_month(rmn_id);


--
-- Name: fk_t_reap_calendar_rpc_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY tj_reap_calendar
    ADD CONSTRAINT fk_t_reap_calendar_rpc_id FOREIGN KEY (rpc_id) REFERENCES t_ref_product(rpc_id);


--
-- Name: fk_t_ref_product_rpc_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_product
    ADD CONSTRAINT fk_t_ref_product_rpc_id FOREIGN KEY (prd_rpc_id) REFERENCES t_ref_product(rpc_id);


--
-- Name: fk_t_ref_product_rpt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_ref_product
    ADD CONSTRAINT fk_t_ref_product_rpt_id FOREIGN KEY (rpc_rpt_id) REFERENCES t_ref_product_type(rpt_id);


--
-- Name: fk_t_region_rci_d; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_ref_region
    ADD CONSTRAINT fk_t_region_rci_d FOREIGN KEY (rre_rst_id) REFERENCES t_ref_state(rst_id);


--
-- Name: fk_t_relationship_rss_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT fk_t_relationship_rss_id FOREIGN KEY (rlt_rrs_id) REFERENCES t_ref_relations_status(rrs_id);


--
-- Name: fk_t_relationship_rtt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT fk_t_relationship_rtt_id FOREIGN KEY (rlt_rrt_id) REFERENCES t_ref_relation_type(rrt_id);


--
-- Name: fk_t_relationship_usr_id_recipient; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT fk_t_relationship_usr_id_recipient FOREIGN KEY (rlt_usr_id_recipient) REFERENCES t_user(usr_id);


--
-- Name: fk_t_relationship_usr_id_sender; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_relationship
    ADD CONSTRAINT fk_t_relationship_usr_id_sender FOREIGN KEY (rlt_usr_id_sender) REFERENCES t_user(usr_id);


--
-- Name: fk_t_request_product_prd_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_request_product
    ADD CONSTRAINT fk_t_request_product_prd_id FOREIGN KEY (rqt_prd_id) REFERENCES t_product(prd_id);


--
-- Name: fk_t_request_product_rpr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_request_product
    ADD CONSTRAINT fk_t_request_product_rpr_id FOREIGN KEY (rqt_rpr_id) REFERENCES t_ref_product_request_status(rpr_id);


--
-- Name: fk_t_request_product_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_request_product
    ADD CONSTRAINT fk_t_request_product_usr_id FOREIGN KEY (rqt_usr_id) REFERENCES t_user(usr_id);


--
-- Name: fk_t_seed_calendar_rpc_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY tj_seed_calendar
    ADD CONSTRAINT fk_t_seed_calendar_rpc_id FOREIGN KEY (rpc_id) REFERENCES t_ref_product(rpc_id);


--
-- Name: fk_t_seed_month_mnt_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY tj_seed_calendar
    ADD CONSTRAINT fk_t_seed_month_mnt_id FOREIGN KEY (rmn_id) REFERENCES t_ref_month(rmn_id);


--
-- Name: fk_t_state_rci_d; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_ref_state
    ADD CONSTRAINT fk_t_state_rci_d FOREIGN KEY (rst_rcn_id) REFERENCES t_ref_country(rcn_id);


--
-- Name: fk_t_subscription_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_subscription
    ADD CONSTRAINT fk_t_subscription_usr_id FOREIGN KEY (sbr_id) REFERENCES t_user(usr_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_t_user_adr_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT fk_t_user_adr_id FOREIGN KEY (usr_adr_id) REFERENCES t_address(adr_id);


--
-- Name: fk_t_user_rur_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT fk_t_user_rur_id FOREIGN KEY (usr_rur_id) REFERENCES t_ref_user_role(rur_id);


--
-- Name: fk_t_user_rus_id; Type: FK CONSTRAINT; Schema: public; Owner: freeveggieprod
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT fk_t_user_rus_id FOREIGN KEY (usr_rus_id) REFERENCES t_ref_user_status(rus_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

