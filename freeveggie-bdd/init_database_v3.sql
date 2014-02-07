--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.3
-- Dumped by pg_dump version 9.0.1
-- Started on 2011-12-20 23:44:57

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 1576
-- Name: s_adr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_adr_seq', 1, true);


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 1606
-- Name: s_cgr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_cgr_seq', 1, false);


--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 1607
-- Name: s_cpr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_cpr_seq', 1, false);


--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 1577
-- Name: s_grd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_grd_seq', 1, true);


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 1608
-- Name: s_lgr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_lgr_seq', 1, false);


--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 1609
-- Name: s_lpr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_lpr_seq', 1, false);


--
-- TOC entry 2034 (class 0 OID 0)
-- Dependencies: 1578
-- Name: s_prd_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_prd_seq', 1, true);


--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 1579
-- Name: s_prf_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_prf_seq', 1, true);


--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 1580
-- Name: s_rlt_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_rlt_seq', 1, true);


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 1581
-- Name: s_rqt_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_rqt_seq', 1, true);


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 1582
-- Name: s_usr_seq; Type: SEQUENCE SET; Schema: public; Owner: freeveggie
--

SELECT pg_catalog.setval('s_usr_seq', 1, true);


--
-- TOC entry 2011 (class 0 OID 122965)
-- Dependencies: 1593
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
-- TOC entry 2013 (class 0 OID 122973)
-- Dependencies: 1595
-- Data for Name: T_REF_PRODUCT_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_PRODUCT_TYPE" (rpt_id, rpt_libelle) FROM stdin;
1	fruit
2	vegetable
\.


--
-- TOC entry 2023 (class 0 OID 140002)
-- Dependencies: 1605 2013
-- Data for Name: T_REF_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_PRODUCT" (rpc_id, rpc_carbohydrate, rpc_lipid, rpc_name, rpc_img_filename, rpc_protein, rpc_rpt_id) FROM stdin;
\.


--
-- TOC entry 2025 (class 0 OID 140119)
-- Dependencies: 1612 2011 2023
-- Data for Name: TJ_REAP_CALENDAR; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "TJ_REAP_CALENDAR" (rpc_id, rmn_id) FROM stdin;
\.


--
-- TOC entry 2020 (class 0 OID 123000)
-- Dependencies: 1602 2011 2023
-- Data for Name: TJ_SEED_CALENDAR; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "TJ_SEED_CALENDAR" (rpc_id, rmn_id) FROM stdin;
\.


--
-- TOC entry 2021 (class 0 OID 139567)
-- Dependencies: 1603
-- Data for Name: T_REF_COUNTRY; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_COUNTRY" (rcn_id, rcn_code_iso_a2, rcn_code_iso_a3, rcn_code_iso_number, rcn_country_name) FROM stdin;
\.


--
-- TOC entry 2001 (class 0 OID 122911)
-- Dependencies: 1583 2021
-- Data for Name: T_ADDRESS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_ADDRESS" (adr_id, adr_line_1, adr_line_2, adr_city, adr_name, adr_street_number, adr_zip_code, adr_rcn_id) FROM stdin;
\.


--
-- TOC entry 2016 (class 0 OID 122982)
-- Dependencies: 1598
-- Data for Name: T_REF_USER_ROLE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_USER_ROLE" (rur_id, rur_libelle) FROM stdin;
1	user
2	manager
3	admin
4	super admin
\.


--
-- TOC entry 2017 (class 0 OID 122985)
-- Dependencies: 1599
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
-- TOC entry 1999 (class 0 OID 109705)
-- Dependencies: 1574 2001 2016 2017
-- Data for Name: T_USER; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_USER" (usr_id, usr_blacklisted_date, usr_cancellation_date, usr_creation_date, usr_email_address, usr_firstname, usr_last_connexion, usr_lastname, usr_password, usr_rur_id, usr_rus_id, usr_temp_password, usr_username, usr_adr_id) FROM stdin;
\.


--
-- TOC entry 2000 (class 0 OID 109779)
-- Dependencies: 1575 2001 1999
-- Data for Name: T_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_GARDEN" (grd_id, grd_creation_date, grd_description, grd_home_adr, grd_name, grd_img_filename, grd_usr_id, grd_adr_id) FROM stdin;
\.


--
-- TOC entry 2008 (class 0 OID 122956)
-- Dependencies: 1590
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
-- TOC entry 2009 (class 0 OID 122959)
-- Dependencies: 1591
-- Data for Name: T_REF_EVALUATION_STATUS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_EVALUATION_STATUS" (res_id, res_libelle) FROM stdin;
1	setted
2	removed
3	archived
\.


--
-- TOC entry 2002 (class 0 OID 122917)
-- Dependencies: 1584 2000 1999 2009 2008
-- Data for Name: T_COMMENT_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_COMMENT_GARDEN" (cgr_id, cgr_comment, cgr_creation_date, cgr_res_id, cgr_grd_id, cgr_usr_id, cgr_ren_id) FROM stdin;
\.


--
-- TOC entry 2006 (class 0 OID 122950)
-- Dependencies: 1588
-- Data for Name: T_REF_CULTURE_MODE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_CULTURE_MODE" (rcm_id, rcm_libelle) FROM stdin;
1	garden
2	balcony
3	terrace
\.


--
-- TOC entry 2007 (class 0 OID 122953)
-- Dependencies: 1589
-- Data for Name: T_REF_CULTURE_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_CULTURE_TYPE" (rct_id, rct_libelle) FROM stdin;
1	regular
2	bio
3	gmo
\.


--
-- TOC entry 2010 (class 0 OID 122962)
-- Dependencies: 1592
-- Data for Name: T_REF_EXCHANGE_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_EXCHANGE_TYPE" (chg_id, chg_libelle) FROM stdin;
1	shares
2	gives
3	sells
\.


--
-- TOC entry 1998 (class 0 OID 109681)
-- Dependencies: 1573 2000 2010 2006 2007 2023
-- Data for Name: T_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_PRODUCT" (prd_id, prd_rcm_id, prd_rct_id, prd_description, prd_ret_id, prd_img_filename, prd_quantity, prd_grd_id, prd_rpc_id) FROM stdin;
\.


--
-- TOC entry 2003 (class 0 OID 122923)
-- Dependencies: 1585 1998 1999 2009 2008
-- Data for Name: T_COMMENT_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_COMMENT_PRODUCT" (cpr_id, cpr_comment, cpr_creation_date, cpr_res_id, cpr_prd_id, cpr_usr_id, cpr_ren_id) FROM stdin;
\.


--
-- TOC entry 2022 (class 0 OID 139806)
-- Dependencies: 1604 2000 2009 1999
-- Data for Name: T_LIKE_GARDEN; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_LIKE_GARDEN" (lgr_id, lgr_creation_date, lgr_res_id, lgr_grd_id, lgr_usr_id) FROM stdin;
\.


--
-- TOC entry 2004 (class 0 OID 122932)
-- Dependencies: 1586 1998 1999 2009
-- Data for Name: T_LIKE_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_LIKE_PRODUCT" (lpr_id, lpr_creation_date, lpr_res_id, lpr_prd_id, lpr_usr_id) FROM stdin;
\.


--
-- TOC entry 2005 (class 0 OID 122941)
-- Dependencies: 1587 1999
-- Data for Name: T_PROFIL; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_PROFIL" (prf_id, prf_experience, prf_interest, prf_other, prf_participation, prf_description, prf_philosophy, prf_meals, prf_img_filename, prf_reason) FROM stdin;
\.


--
-- TOC entry 2012 (class 0 OID 122970)
-- Dependencies: 1594
-- Data for Name: T_REF_PRODUCT_REQUEST_STATUS; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_PRODUCT_REQUEST_STATUS" (rpr_id, rpr_libelle) FROM stdin;
1	request
2	pending
3	accepted
4	refused
\.


--
-- TOC entry 2014 (class 0 OID 122976)
-- Dependencies: 1596
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
-- TOC entry 2015 (class 0 OID 122979)
-- Dependencies: 1597
-- Data for Name: T_REF_RELATION_TYPE; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REF_RELATION_TYPE" (rrt_id, rrt_libelle) FROM stdin;
1	freeveggie friend
2	friend
3	neighbors
\.


--
-- TOC entry 2018 (class 0 OID 122988)
-- Dependencies: 1600 2014 2015 1999 1999
-- Data for Name: T_RELATIONSHIP; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_RELATIONSHIP" (rlt_id, rlt_answer, rlt_creation_date, rlt_rrs_id, rlt_rrt_id, rlt_usr_id_sender, rlt_usr_id_recipient, rlt_request) FROM stdin;
\.


--
-- TOC entry 2019 (class 0 OID 122994)
-- Dependencies: 1601 2012 1999 1998
-- Data for Name: T_REQUEST_PRODUCT; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_REQUEST_PRODUCT" (rqt_id, rqt_answer_msg, rqt_answer_date, rqt_creation_date, rqt_msg, rqt_picking_date, rqt_quantity, rqt_rpr_id, rqt_prd_id, "rqt _usr_id") FROM stdin;
\.


--
-- TOC entry 2024 (class 0 OID 140101)
-- Dependencies: 1610 1999
-- Data for Name: T_SUBSCRIPTION; Type: TABLE DATA; Schema: public; Owner: freeveggie
--

COPY "T_SUBSCRIPTION" (sbr_id, sbr_share_personal, sbr_share_garden, sbr_newsletter, sbr_freeveggie_agreement) FROM stdin;
\.


-- Completed on 2011-12-20 23:44:59

--
-- PostgreSQL database dump complete
--

