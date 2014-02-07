--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.3
-- Dumped by pg_dump version 9.0.1
-- Started on 2011-12-21 00:01:06

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2026 (class 0 OID 0)
-- Dependencies: 1575
-- Name: s_adr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_adr_seq', 1, true);


--
-- TOC entry 2027 (class 0 OID 0)
-- Dependencies: 1604
-- Name: s_cgr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_cgr_seq', 1, false);


--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 1605
-- Name: s_cpr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_cpr_seq', 1, false);


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 1576
-- Name: s_grd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_grd_seq', 1, true);


--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 1606
-- Name: s_lgr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_lgr_seq', 1, false);


--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 1607
-- Name: s_lpr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_lpr_seq', 1, false);


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 1577
-- Name: s_prd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_prd_seq', 1, true);


--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 1578
-- Name: s_rlt_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_rlt_seq', 1, true);


--
-- TOC entry 2034 (class 0 OID 0)
-- Dependencies: 1579
-- Name: s_rqt_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_rqt_seq', 1, true);


--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 1580
-- Name: s_usr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_usr_seq', 1, true);


--
-- TOC entry 2009 (class 0 OID 122965)
-- Dependencies: 1591
-- Data for Name: T_REF_MONTH; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_MONTH" VALUES (1, 'january');
INSERT INTO "T_REF_MONTH" VALUES (2, 'february');
INSERT INTO "T_REF_MONTH" VALUES (3, 'march');
INSERT INTO "T_REF_MONTH" VALUES (4, 'april');
INSERT INTO "T_REF_MONTH" VALUES (5, 'may');
INSERT INTO "T_REF_MONTH" VALUES (6, 'june');
INSERT INTO "T_REF_MONTH" VALUES (7, 'july');
INSERT INTO "T_REF_MONTH" VALUES (8, 'august');
INSERT INTO "T_REF_MONTH" VALUES (9, 'september');
INSERT INTO "T_REF_MONTH" VALUES (10, 'october');
INSERT INTO "T_REF_MONTH" VALUES (11, 'november');
INSERT INTO "T_REF_MONTH" VALUES (12, 'december');


--
-- TOC entry 2011 (class 0 OID 122973)
-- Dependencies: 1593
-- Data for Name: T_REF_PRODUCT_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_PRODUCT_TYPE" VALUES (1, 'fruit');
INSERT INTO "T_REF_PRODUCT_TYPE" VALUES (2, 'vegetable');


--
-- TOC entry 2021 (class 0 OID 140002)
-- Dependencies: 1603 2011
-- Data for Name: T_REF_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2023 (class 0 OID 140119)
-- Dependencies: 1610 2009 2021
-- Data for Name: TJ_REAP_CALENDAR; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2018 (class 0 OID 123000)
-- Dependencies: 1600 2009 2021
-- Data for Name: TJ_SEED_CALENDAR; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2019 (class 0 OID 139567)
-- Dependencies: 1601
-- Data for Name: T_REF_COUNTRY; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 1999 (class 0 OID 122911)
-- Dependencies: 1581 2019
-- Data for Name: T_ADDRESS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2014 (class 0 OID 122982)
-- Dependencies: 1596
-- Data for Name: T_REF_USER_ROLE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_USER_ROLE" VALUES (1, 'user');
INSERT INTO "T_REF_USER_ROLE" VALUES (2, 'manager');
INSERT INTO "T_REF_USER_ROLE" VALUES (3, 'admin');
INSERT INTO "T_REF_USER_ROLE" VALUES (4, 'super admin');


--
-- TOC entry 2015 (class 0 OID 122985)
-- Dependencies: 1597
-- Data for Name: T_REF_USER_STATUS; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "T_REF_USER_STATUS" VALUES (1, 'new');
INSERT INTO "T_REF_USER_STATUS" VALUES (2, 'valided');
INSERT INTO "T_REF_USER_STATUS" VALUES (3, 'blacklisted');
INSERT INTO "T_REF_USER_STATUS" VALUES (4, 'deleted');
INSERT INTO "T_REF_USER_STATUS" VALUES (5, 'archive');


--
-- TOC entry 1997 (class 0 OID 109705)
-- Dependencies: 1573 1999 2014 2015
-- Data for Name: T_USER; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 1998 (class 0 OID 109779)
-- Dependencies: 1574 1999 1997
-- Data for Name: T_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2006 (class 0 OID 122956)
-- Dependencies: 1588
-- Data for Name: T_REF_EVALUATION_NOTE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (1, 'very bad');
INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (2, 'bad');
INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (3, 'normal');
INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (4, 'good');
INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (5, 'very good');


--
-- TOC entry 2007 (class 0 OID 122959)
-- Dependencies: 1589
-- Data for Name: T_REF_EVALUATION_STATUS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_EVALUATION_STATUS" VALUES (1, 'setted');
INSERT INTO "T_REF_EVALUATION_STATUS" VALUES (2, 'removed');
INSERT INTO "T_REF_EVALUATION_STATUS" VALUES (3, 'archived');


--
-- TOC entry 2000 (class 0 OID 122917)
-- Dependencies: 1582 1998 1997 2007 2006
-- Data for Name: T_COMMENT_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2004 (class 0 OID 122950)
-- Dependencies: 1586
-- Data for Name: T_REF_CULTURE_MODE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_CULTURE_MODE" VALUES (1, 'garden');
INSERT INTO "T_REF_CULTURE_MODE" VALUES (2, 'balcony');
INSERT INTO "T_REF_CULTURE_MODE" VALUES (3, 'terrace');


--
-- TOC entry 2005 (class 0 OID 122953)
-- Dependencies: 1587
-- Data for Name: T_REF_CULTURE_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_CULTURE_TYPE" VALUES (1, 'regular');
INSERT INTO "T_REF_CULTURE_TYPE" VALUES (2, 'bio');
INSERT INTO "T_REF_CULTURE_TYPE" VALUES (3, 'gmo');


--
-- TOC entry 2008 (class 0 OID 122962)
-- Dependencies: 1590
-- Data for Name: T_REF_EXCHANGE_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_EXCHANGE_TYPE" VALUES (1, 'shares');
INSERT INTO "T_REF_EXCHANGE_TYPE" VALUES (2, 'gives');
INSERT INTO "T_REF_EXCHANGE_TYPE" VALUES (3, 'sells');


--
-- TOC entry 1996 (class 0 OID 109681)
-- Dependencies: 1572 1998 2008 2004 2005 2021
-- Data for Name: T_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2001 (class 0 OID 122923)
-- Dependencies: 1583 1996 1997 2007 2006
-- Data for Name: T_COMMENT_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2020 (class 0 OID 139806)
-- Dependencies: 1602 1998 2007 1997
-- Data for Name: T_LIKE_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2002 (class 0 OID 122932)
-- Dependencies: 1584 1996 1997 2007
-- Data for Name: T_LIKE_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2003 (class 0 OID 122941)
-- Dependencies: 1585 1997
-- Data for Name: T_PROFIL; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2010 (class 0 OID 122970)
-- Dependencies: 1592
-- Data for Name: T_REF_PRODUCT_REQUEST_STATUS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_PRODUCT_REQUEST_STATUS" VALUES (1, 'request');
INSERT INTO "T_REF_PRODUCT_REQUEST_STATUS" VALUES (2, 'pending');
INSERT INTO "T_REF_PRODUCT_REQUEST_STATUS" VALUES (3, 'accepted');
INSERT INTO "T_REF_PRODUCT_REQUEST_STATUS" VALUES (4, 'refused');


--
-- TOC entry 2012 (class 0 OID 122976)
-- Dependencies: 1594
-- Data for Name: T_REF_RELATIONS_STATUS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (1, 'request');
INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (2, 'pending');
INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (3, 'validated');
INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (4, 'refused');
INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (5, 'cancel');


--
-- TOC entry 2013 (class 0 OID 122979)
-- Dependencies: 1595
-- Data for Name: T_REF_RELATION_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

INSERT INTO "T_REF_RELATION_TYPE" VALUES (1, 'freeveggie friend');
INSERT INTO "T_REF_RELATION_TYPE" VALUES (2, 'friend');
INSERT INTO "T_REF_RELATION_TYPE" VALUES (3, 'neighbors');


--
-- TOC entry 2016 (class 0 OID 122988)
-- Dependencies: 1598 2012 2013 1997 1997
-- Data for Name: T_RELATIONSHIP; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2017 (class 0 OID 122994)
-- Dependencies: 1599 2010 1997 1996
-- Data for Name: T_REQUEST_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



--
-- TOC entry 2022 (class 0 OID 140101)
-- Dependencies: 1608 1997
-- Data for Name: T_SUBSCRIPTION; Type: TABLE DATA; Schema: public; Owner: freeveggie
--



-- Completed on 2011-12-21 00:01:08

--
-- PostgreSQL database dump complete
--

