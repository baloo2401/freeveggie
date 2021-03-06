
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
-- Data for Name: T_REF_PRODUCT_TYPE; 
--

INSERT INTO "T_REF_PRODUCT_TYPE" VALUES (1, 'fruit');
INSERT INTO "T_REF_PRODUCT_TYPE" VALUES (2, 'vegetable');

--
-- TOC entry 2014 (class 0 OID 122982)
-- Dependencies: 1596
-- Data for Name: T_REF_USER_ROLE;
--

INSERT INTO "T_REF_USER_ROLE" VALUES (1, 'user');
INSERT INTO "T_REF_USER_ROLE" VALUES (2, 'manager');
INSERT INTO "T_REF_USER_ROLE" VALUES (3, 'admin');
INSERT INTO "T_REF_USER_ROLE" VALUES (4, 'super admin');
INSERT INTO "T_REF_USER_ROLE" VALUES (5, 'super admin');


--
-- TOC entry 2015 (class 0 OID 122985)
-- Dependencies: 1597
-- Data for Name: T_REF_USER_STATUS;
--

INSERT INTO "T_REF_USER_STATUS" VALUES (1, 'new');
INSERT INTO "T_REF_USER_STATUS" VALUES (2, 'valided');
INSERT INTO "T_REF_USER_STATUS" VALUES (3, 'blacklisted');
INSERT INTO "T_REF_USER_STATUS" VALUES (4, 'deleted');
INSERT INTO "T_REF_USER_STATUS" VALUES (5, 'archive');

--
-- TOC entry 2006 (class 0 OID 122956)
-- Dependencies: 1588
-- Data for Name: T_REF_EVALUATION_NOTE;
--

INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (1, 'very bad');
INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (2, 'bad');
INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (3, 'normal');
INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (4, 'good');
INSERT INTO "T_REF_EVALUATION_NOTE" VALUES (5, 'very good');

--
-- TOC entry 2007 (class 0 OID 122959)
-- Dependencies: 1589
-- Data for Name: T_REF_STATUS;
--

INSERT INTO "T_REF_STATUS" VALUES (1, 'created');
INSERT INTO "T_REF_STATUS" VALUES (2, 'deleted');
INSERT INTO "T_REF_STATUS" VALUES (3, 'archived');
INSERT INTO "T_REF_STATUS" VALUES (4, 'blacklisted');


--
-- TOC entry 2007 (class 0 OID 122959)
-- Dependencies: 1589
-- Data for Name: T_REF_EVALUATION_STATUS;
--

INSERT INTO "T_REF_EVALUATION_STATUS" VALUES (1, 'setted');
INSERT INTO "T_REF_EVALUATION_STATUS" VALUES (2, 'removed');
INSERT INTO "T_REF_EVALUATION_STATUS" VALUES (3, 'archived');

--
-- TOC entry 2004 (class 0 OID 122950)
-- Dependencies: 1586
-- Data for Name: T_REF_CULTURE_MODE;
--

INSERT INTO "T_REF_CULTURE_MODE" VALUES (1, 'garden');
INSERT INTO "T_REF_CULTURE_MODE" VALUES (2, 'balcony');
INSERT INTO "T_REF_CULTURE_MODE" VALUES (3, 'terrace');


--
-- TOC entry 2005 (class 0 OID 122953)
-- Dependencies: 1587
-- Data for Name: T_REF_CULTURE_TYPE;
--

INSERT INTO "T_REF_CULTURE_TYPE" VALUES (1, 'regular');
INSERT INTO "T_REF_CULTURE_TYPE" VALUES (2, 'bio');
INSERT INTO "T_REF_CULTURE_TYPE" VALUES (3, 'gmo');


--
-- TOC entry 2008 (class 0 OID 122962)
-- Dependencies: 1590
-- Data for Name: T_REF_EXCHANGE_TYPE;
--

INSERT INTO "T_REF_EXCHANGE_TYPE" VALUES (1, 'shares');
INSERT INTO "T_REF_EXCHANGE_TYPE" VALUES (2, 'gives');
INSERT INTO "T_REF_EXCHANGE_TYPE" VALUES (3, 'sells');

--
-- TOC entry 2010 (class 0 OID 122970)
-- Dependencies: 1592
-- Data for Name: T_REF_PRODUCT_REQUEST_STATUS;
--

INSERT INTO "T_REF_PRODUCT_REQUEST_STATUS" VALUES (1, 'request');
INSERT INTO "T_REF_PRODUCT_REQUEST_STATUS" VALUES (2, 'pending');
INSERT INTO "T_REF_PRODUCT_REQUEST_STATUS" VALUES (3, 'accepted');
INSERT INTO "T_REF_PRODUCT_REQUEST_STATUS" VALUES (4, 'refused');


--
-- TOC entry 2012 (class 0 OID 122976)
-- Dependencies: 1594
-- Data for Name: T_REF_RELATIONS_STATUS;
--

INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (1, 'request');
INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (2, 'pending');
INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (3, 'validated');
INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (4, 'refused');
INSERT INTO "T_REF_RELATIONS_STATUS" VALUES (5, 'cancel');


--
-- TOC entry 2013 (class 0 OID 122979)
-- Dependencies: 1595
-- Data for Name: T_REF_RELATION_TYPE;
--

INSERT INTO "T_REF_RELATION_TYPE" VALUES (1, 'freeveggie friend');
INSERT INTO "T_REF_RELATION_TYPE" VALUES (2, 'friend');
INSERT INTO "T_REF_RELATION_TYPE" VALUES (3, 'neighbors');
