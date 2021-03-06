PGDMP         %                o         
   freeveggie    9.0.3    9.0.3 p    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     )   SET standard_conforming_strings = 'off';
                       false            L           1259    140119    TJ_REAP_CALENDAR    TABLE     ^   CREATE TABLE "TJ_REAP_CALENDAR" (
    rpc_id integer NOT NULL,
    rmn_id integer NOT NULL
);
 &   DROP TABLE public."TJ_REAP_CALENDAR";
       public      
   freeveggie    false    6            �           0    0    TABLE "TJ_REAP_CALENDAR"    COMMENT     Q   COMMENT ON TABLE "TJ_REAP_CALENDAR" IS 'The reference product reaping calendar';
            public    
   freeveggie    false    1612            �           0    0     COLUMN "TJ_REAP_CALENDAR".rpc_id    COMMENT     ?   COMMENT ON COLUMN "TJ_REAP_CALENDAR".rpc_id IS 'The product ';
            public    
   freeveggie    false    1612            �           0    0     COLUMN "TJ_REAP_CALENDAR".rmn_id    COMMENT     @   COMMENT ON COLUMN "TJ_REAP_CALENDAR".rmn_id IS 'The reap mont';
            public    
   freeveggie    false    1612            B           1259    123000    TJ_SEED_CALENDAR    TABLE     ^   CREATE TABLE "TJ_SEED_CALENDAR" (
    rpc_id integer NOT NULL,
    rmn_id integer NOT NULL
);
 &   DROP TABLE public."TJ_SEED_CALENDAR";
       public      
   freeveggie    false    6            �           0    0    TABLE "TJ_SEED_CALENDAR"    COMMENT     R   COMMENT ON TABLE "TJ_SEED_CALENDAR" IS 'The reference month of seedling product';
            public    
   freeveggie    false    1602            �           0    0     COLUMN "TJ_SEED_CALENDAR".rpc_id    COMMENT     H   COMMENT ON COLUMN "TJ_SEED_CALENDAR".rpc_id IS 'The reference product';
            public    
   freeveggie    false    1602            �           0    0     COLUMN "TJ_SEED_CALENDAR".rmn_id    COMMENT     A   COMMENT ON COLUMN "TJ_SEED_CALENDAR".rmn_id IS 'The seed month';
            public    
   freeveggie    false    1602            C           1259    139567    T_REF_COUNTRY    TABLE     �   CREATE TABLE "T_REF_COUNTRY" (
    rcn_id integer NOT NULL,
    rcn_code_iso_a2 character(2) NOT NULL,
    rcn_code_iso_a3 character(3) NOT NULL,
    rcn_code_iso_number integer NOT NULL,
    rcn_country_name character varying(64) NOT NULL
);
 #   DROP TABLE public."T_REF_COUNTRY";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_COUNTRY"    COMMENT     ;   COMMENT ON TABLE "T_REF_COUNTRY" IS 'The list of country';
            public    
   freeveggie    false    1603            �           0    0 &   COLUMN "T_REF_COUNTRY".rcn_code_iso_a2    COMMENT     N   COMMENT ON COLUMN "T_REF_COUNTRY".rcn_code_iso_a2 IS 'The is 2 letters code';
            public    
   freeveggie    false    1603            �           0    0 &   COLUMN "T_REF_COUNTRY".rcn_code_iso_a3    COMMENT     O   COMMENT ON COLUMN "T_REF_COUNTRY".rcn_code_iso_a3 IS 'The iso 3 letters code';
            public    
   freeveggie    false    1603            �           0    0 *   COLUMN "T_REF_COUNTRY".rcn_code_iso_number    COMMENT     P   COMMENT ON COLUMN "T_REF_COUNTRY".rcn_code_iso_number IS 'The iso code number';
            public    
   freeveggie    false    1603            �           0    0 '   COLUMN "T_REF_COUNTRY".rcn_country_name    COMMENT     J   COMMENT ON COLUMN "T_REF_COUNTRY".rcn_country_name IS 'The country name';
            public    
   freeveggie    false    1603            4           1259    122950    T_REF_CULTURE_MODE    TABLE     s   CREATE TABLE "T_REF_CULTURE_MODE" (
    rcm_id integer NOT NULL,
    rcm_libelle character varying(32) NOT NULL
);
 (   DROP TABLE public."T_REF_CULTURE_MODE";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_CULTURE_MODE"    COMMENT     G   COMMENT ON TABLE "T_REF_CULTURE_MODE" IS 'The choice of culture mode';
            public    
   freeveggie    false    1588            �           0    0 "   COLUMN "T_REF_CULTURE_MODE".rcm_id    COMMENT     D   COMMENT ON COLUMN "T_REF_CULTURE_MODE".rcm_id IS 'The database id';
            public    
   freeveggie    false    1588            �           0    0 '   COLUMN "T_REF_CULTURE_MODE".rcm_libelle    COMMENT     W   COMMENT ON COLUMN "T_REF_CULTURE_MODE".rcm_libelle IS 'The culture mode description ';
            public    
   freeveggie    false    1588            5           1259    122953    T_REF_CULTURE_TYPE    TABLE     s   CREATE TABLE "T_REF_CULTURE_TYPE" (
    rct_id integer NOT NULL,
    rct_libelle character varying(32) NOT NULL
);
 (   DROP TABLE public."T_REF_CULTURE_TYPE";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_CULTURE_TYPE"    COMMENT     G   COMMENT ON TABLE "T_REF_CULTURE_TYPE" IS 'The choice of culture type';
            public    
   freeveggie    false    1589            �           0    0 "   COLUMN "T_REF_CULTURE_TYPE".rct_id    COMMENT     D   COMMENT ON COLUMN "T_REF_CULTURE_TYPE".rct_id IS 'The database id';
            public    
   freeveggie    false    1589            �           0    0 '   COLUMN "T_REF_CULTURE_TYPE".rct_libelle    COMMENT     V   COMMENT ON COLUMN "T_REF_CULTURE_TYPE".rct_libelle IS 'The culture type description';
            public    
   freeveggie    false    1589            6           1259    122956    T_REF_EVALUATION_NOTE    TABLE     v   CREATE TABLE "T_REF_EVALUATION_NOTE" (
    ren_id integer NOT NULL,
    ren_libelle character varying(32) NOT NULL
);
 +   DROP TABLE public."T_REF_EVALUATION_NOTE";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_EVALUATION_NOTE"    COMMENT     _   COMMENT ON TABLE "T_REF_EVALUATION_NOTE" IS 'The value of a note (for a garden or a product)';
            public    
   freeveggie    false    1590            �           0    0 %   COLUMN "T_REF_EVALUATION_NOTE".ren_id    COMMENT     G   COMMENT ON COLUMN "T_REF_EVALUATION_NOTE".ren_id IS 'The database id';
            public    
   freeveggie    false    1590            �           0    0 *   COLUMN "T_REF_EVALUATION_NOTE".ren_libelle    COMMENT     \   COMMENT ON COLUMN "T_REF_EVALUATION_NOTE".ren_libelle IS 'The evaluation note description';
            public    
   freeveggie    false    1590            7           1259    122959    T_REF_EVALUATION_STATUS    TABLE     x   CREATE TABLE "T_REF_EVALUATION_STATUS" (
    res_id integer NOT NULL,
    res_libelle character varying(32) NOT NULL
);
 -   DROP TABLE public."T_REF_EVALUATION_STATUS";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_EVALUATION_STATUS"    COMMENT     c   COMMENT ON TABLE "T_REF_EVALUATION_STATUS" IS 'The status of an evaluation(product, garden, ...)';
            public    
   freeveggie    false    1591            �           0    0 '   COLUMN "T_REF_EVALUATION_STATUS".res_id    COMMENT     I   COMMENT ON COLUMN "T_REF_EVALUATION_STATUS".res_id IS 'The database id';
            public    
   freeveggie    false    1591            �           0    0 ,   COLUMN "T_REF_EVALUATION_STATUS".res_libelle    COMMENT     `   COMMENT ON COLUMN "T_REF_EVALUATION_STATUS".res_libelle IS 'The evaluation status description';
            public    
   freeveggie    false    1591            8           1259    122962    T_REF_EXCHANGE_TYPE    TABLE     t   CREATE TABLE "T_REF_EXCHANGE_TYPE" (
    chg_id integer NOT NULL,
    chg_libelle character varying(32) NOT NULL
);
 )   DROP TABLE public."T_REF_EXCHANGE_TYPE";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_EXCHANGE_TYPE"    COMMENT     E   COMMENT ON TABLE "T_REF_EXCHANGE_TYPE" IS 'Choice of exchange type';
            public    
   freeveggie    false    1592            �           0    0 #   COLUMN "T_REF_EXCHANGE_TYPE".chg_id    COMMENT     E   COMMENT ON COLUMN "T_REF_EXCHANGE_TYPE".chg_id IS 'The database id';
            public    
   freeveggie    false    1592            �           0    0 (   COLUMN "T_REF_EXCHANGE_TYPE".chg_libelle    COMMENT     X   COMMENT ON COLUMN "T_REF_EXCHANGE_TYPE".chg_libelle IS 'The exchange type description';
            public    
   freeveggie    false    1592            9           1259    122965    T_REF_MONTH    TABLE     `   CREATE TABLE "T_REF_MONTH" (
    rmn_id integer NOT NULL,
    rmn_name character varying(32)
);
 !   DROP TABLE public."T_REF_MONTH";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_MONTH"    COMMENT     B   COMMENT ON TABLE "T_REF_MONTH" IS 'The reference mont of a year';
            public    
   freeveggie    false    1593            �           0    0    COLUMN "T_REF_MONTH".rmn_id    COMMENT     >   COMMENT ON COLUMN "T_REF_MONTH".rmn_id IS 'The month number';
            public    
   freeveggie    false    1593            �           0    0    COLUMN "T_REF_MONTH".rmn_name    COMMENT     E   COMMENT ON COLUMN "T_REF_MONTH".rmn_name IS 'The month description';
            public    
   freeveggie    false    1593            E           1259    140002    T_REF_PRODUCT    TABLE     "  CREATE TABLE "T_REF_PRODUCT" (
    rpc_id integer NOT NULL,
    rpc_carbohydrate double precision,
    rpc_lipid double precision,
    rpc_name character varying(32) NOT NULL,
    rpc_img_filename character varying(128),
    rpc_protein double precision,
    rpc_rpt_id integer NOT NULL
);
 #   DROP TABLE public."T_REF_PRODUCT";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_PRODUCT"    COMMENT     @   COMMENT ON TABLE "T_REF_PRODUCT" IS 'The reference of product';
            public    
   freeveggie    false    1605            �           0    0    COLUMN "T_REF_PRODUCT".rpc_id    COMMENT     ?   COMMENT ON COLUMN "T_REF_PRODUCT".rpc_id IS 'The database id';
            public    
   freeveggie    false    1605            �           0    0 '   COLUMN "T_REF_PRODUCT".rpc_carbohydrate    COMMENT     e   COMMENT ON COLUMN "T_REF_PRODUCT".rpc_carbohydrate IS 'The quantity of carbohydrate in the product';
            public    
   freeveggie    false    1605            �           0    0     COLUMN "T_REF_PRODUCT".rpc_lipid    COMMENT     W   COMMENT ON COLUMN "T_REF_PRODUCT".rpc_lipid IS 'The quantity of lipid in the product';
            public    
   freeveggie    false    1605            �           0    0    COLUMN "T_REF_PRODUCT".rpc_name    COMMENT     _   COMMENT ON COLUMN "T_REF_PRODUCT".rpc_name IS 'The name of the product gived by the producer';
            public    
   freeveggie    false    1605            �           0    0 '   COLUMN "T_REF_PRODUCT".rpc_img_filename    COMMENT     \   COMMENT ON COLUMN "T_REF_PRODUCT".rpc_img_filename IS 'The file name of the product image';
            public    
   freeveggie    false    1605            �           0    0 "   COLUMN "T_REF_PRODUCT".rpc_protein    COMMENT     [   COMMENT ON COLUMN "T_REF_PRODUCT".rpc_protein IS 'The quantity of protein in the product';
            public    
   freeveggie    false    1605            �           0    0 !   COLUMN "T_REF_PRODUCT".rpc_rpt_id    COMMENT     D   COMMENT ON COLUMN "T_REF_PRODUCT".rpc_rpt_id IS 'The product type';
            public    
   freeveggie    false    1605            :           1259    122970    T_REF_PRODUCT_REQUEST_STATUS    TABLE     }   CREATE TABLE "T_REF_PRODUCT_REQUEST_STATUS" (
    rpr_id integer NOT NULL,
    rpr_libelle character varying(32) NOT NULL
);
 2   DROP TABLE public."T_REF_PRODUCT_REQUEST_STATUS";
       public      
   freeveggie    false    6            �           0    0 $   TABLE "T_REF_PRODUCT_REQUEST_STATUS"    COMMENT     ]   COMMENT ON TABLE "T_REF_PRODUCT_REQUEST_STATUS" IS 'The posible state of a product request';
            public    
   freeveggie    false    1594            �           0    0 ,   COLUMN "T_REF_PRODUCT_REQUEST_STATUS".rpr_id    COMMENT     N   COMMENT ON COLUMN "T_REF_PRODUCT_REQUEST_STATUS".rpr_id IS 'The database id';
            public    
   freeveggie    false    1594            �           0    0 1   COLUMN "T_REF_PRODUCT_REQUEST_STATUS".rpr_libelle    COMMENT     j   COMMENT ON COLUMN "T_REF_PRODUCT_REQUEST_STATUS".rpr_libelle IS 'The product request status description';
            public    
   freeveggie    false    1594            ;           1259    122973    T_REF_PRODUCT_TYPE    TABLE     s   CREATE TABLE "T_REF_PRODUCT_TYPE" (
    rpt_id integer NOT NULL,
    rpt_libelle character varying(32) NOT NULL
);
 (   DROP TABLE public."T_REF_PRODUCT_TYPE";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_PRODUCT_TYPE"    COMMENT     _   COMMENT ON TABLE "T_REF_PRODUCT_TYPE" IS 'The reference of product type (fruit or vegetable)';
            public    
   freeveggie    false    1595            �           0    0 "   COLUMN "T_REF_PRODUCT_TYPE".rpt_id    COMMENT     D   COMMENT ON COLUMN "T_REF_PRODUCT_TYPE".rpt_id IS 'The database id';
            public    
   freeveggie    false    1595            �           0    0 '   COLUMN "T_REF_PRODUCT_TYPE".rpt_libelle    COMMENT     V   COMMENT ON COLUMN "T_REF_PRODUCT_TYPE".rpt_libelle IS 'The product type description';
            public    
   freeveggie    false    1595            <           1259    122976    T_REF_RELATIONS_STATUS    TABLE     w   CREATE TABLE "T_REF_RELATIONS_STATUS" (
    rrs_id integer NOT NULL,
    rrs_libelle character varying(32) NOT NULL
);
 ,   DROP TABLE public."T_REF_RELATIONS_STATUS";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_RELATIONS_STATUS"    COMMENT     R   COMMENT ON TABLE "T_REF_RELATIONS_STATUS" IS 'The posible state of relationship';
            public    
   freeveggie    false    1596            �           0    0 &   COLUMN "T_REF_RELATIONS_STATUS".rrs_id    COMMENT     I   COMMENT ON COLUMN "T_REF_RELATIONS_STATUS".rrs_id IS 'The database id.';
            public    
   freeveggie    false    1596            �           0    0 +   COLUMN "T_REF_RELATIONS_STATUS".rrs_libelle    COMMENT     ]   COMMENT ON COLUMN "T_REF_RELATIONS_STATUS".rrs_libelle IS 'The relation status description';
            public    
   freeveggie    false    1596            =           1259    122979    T_REF_RELATION_TYPE    TABLE     t   CREATE TABLE "T_REF_RELATION_TYPE" (
    rrt_id integer NOT NULL,
    rrt_libelle character varying(32) NOT NULL
);
 )   DROP TABLE public."T_REF_RELATION_TYPE";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_RELATION_TYPE"    COMMENT     m   COMMENT ON TABLE "T_REF_RELATION_TYPE" IS 'The possible value of a relation type (friends, neighbors, ...)';
            public    
   freeveggie    false    1597            �           0    0 #   COLUMN "T_REF_RELATION_TYPE".rrt_id    COMMENT     E   COMMENT ON COLUMN "T_REF_RELATION_TYPE".rrt_id IS 'The database id';
            public    
   freeveggie    false    1597            �           0    0 (   COLUMN "T_REF_RELATION_TYPE".rrt_libelle    COMMENT     S   COMMENT ON COLUMN "T_REF_RELATION_TYPE".rrt_libelle IS 'The relation description';
            public    
   freeveggie    false    1597            >           1259    122982    T_REF_USER_ROLE    TABLE     p   CREATE TABLE "T_REF_USER_ROLE" (
    rur_id integer NOT NULL,
    rur_libelle character varying(32) NOT NULL
);
 %   DROP TABLE public."T_REF_USER_ROLE";
       public      
   freeveggie    false    6            �           0    0    TABLE "T_REF_USER_ROLE"    COMMENT     ?   COMMENT ON TABLE "T_REF_USER_ROLE" IS 'The posible user role';
            public    
   freeveggie    false    1598            �           0    0    COLUMN "T_REF_USER_ROLE".rur_id    COMMENT     A   COMMENT ON COLUMN "T_REF_USER_ROLE".rur_id IS 'The database id';
            public    
   freeveggie    false    1598            �           0    0 $   COLUMN "T_REF_USER_ROLE".rur_libelle    COMMENT     P   COMMENT ON COLUMN "T_REF_USER_ROLE".rur_libelle IS 'The user role description';
            public    
   freeveggie    false    1598            ?           1259    122985    T_REF_USER_STATUS    TABLE     r   CREATE TABLE "T_REF_USER_STATUS" (
    rus_id integer NOT NULL,
    rus_libelle character varying(32) NOT NULL
);
 '   DROP TABLE public."T_REF_USER_STATUS";
       public         postgres    false    6            �           0    0    TABLE "T_REF_USER_STATUS"    COMMENT     _   COMMENT ON TABLE "T_REF_USER_STATUS" IS 'The possible state of user (new, blacklisted, ....)';
            public       postgres    false    1599            �           0    0 &   COLUMN "T_REF_USER_STATUS".rus_libelle    COMMENT     T   COMMENT ON COLUMN "T_REF_USER_STATUS".rus_libelle IS 'The user status description';
            public       postgres    false    1599            �          0    140119    TJ_REAP_CALENDAR 
   TABLE DATA                     public    
   freeveggie    false    1612   ts       �          0    123000    TJ_SEED_CALENDAR 
   TABLE DATA                     public    
   freeveggie    false    1602   �s       �          0    139567    T_REF_COUNTRY 
   TABLE DATA                     public    
   freeveggie    false    1603   �s       �          0    122950    T_REF_CULTURE_MODE 
   TABLE DATA                     public    
   freeveggie    false    1588   �s       �          0    122953    T_REF_CULTURE_TYPE 
   TABLE DATA                     public    
   freeveggie    false    1589   Ct       �          0    122956    T_REF_EVALUATION_NOTE 
   TABLE DATA                     public    
   freeveggie    false    1590   �t       �          0    122959    T_REF_EVALUATION_STATUS 
   TABLE DATA                     public    
   freeveggie    false    1591   Gu       �          0    122962    T_REF_EXCHANGE_TYPE 
   TABLE DATA                     public    
   freeveggie    false    1592   �u       �          0    122965    T_REF_MONTH 
   TABLE DATA                     public    
   freeveggie    false    1593   @v       �          0    140002    T_REF_PRODUCT 
   TABLE DATA                     public    
   freeveggie    false    1605   w       �          0    122970    T_REF_PRODUCT_REQUEST_STATUS 
   TABLE DATA                     public    
   freeveggie    false    1594   w       �          0    122973    T_REF_PRODUCT_TYPE 
   TABLE DATA                     public    
   freeveggie    false    1595   �w       �          0    122976    T_REF_RELATIONS_STATUS 
   TABLE DATA                     public    
   freeveggie    false    1596   'x       �          0    122979    T_REF_RELATION_TYPE 
   TABLE DATA                     public    
   freeveggie    false    1597   �x       �          0    122982    T_REF_USER_ROLE 
   TABLE DATA                     public    
   freeveggie    false    1598   Hy       �          0    122985    T_REF_USER_STATUS 
   TABLE DATA                     public       postgres    false    1599   �y       ~           2606    139571    t_ref_country_tmp_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_tmp_pkey PRIMARY KEY (rcn_id);
 P   ALTER TABLE ONLY public."T_REF_COUNTRY" DROP CONSTRAINT t_ref_country_tmp_pkey;
       public      
   freeveggie    false    1603    1603            �           2606    139573 3   t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a2 
   CONSTRAINT     �   ALTER TABLE ONLY "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a2 UNIQUE (rcn_code_iso_a2);
 m   ALTER TABLE ONLY public."T_REF_COUNTRY" DROP CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a2;
       public      
   freeveggie    false    1603    1603            �           2606    139575 3   t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a3 
   CONSTRAINT     �   ALTER TABLE ONLY "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a3 UNIQUE (rcn_code_iso_a3);
 m   ALTER TABLE ONLY public."T_REF_COUNTRY" DROP CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_a3;
       public      
   freeveggie    false    1603    1603            �           2606    139577 7   t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_number 
   CONSTRAINT     �   ALTER TABLE ONLY "T_REF_COUNTRY"
    ADD CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_number UNIQUE (rcn_code_iso_number);
 q   ALTER TABLE ONLY public."T_REF_COUNTRY" DROP CONSTRAINT t_ref_country_tmp_unq_t_ref_country_rcn_code_iso_number;
       public      
   freeveggie    false    1603    1603            d           2606    139924    t_ref_culture_mode_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY "T_REF_CULTURE_MODE"
    ADD CONSTRAINT t_ref_culture_mode_pkey PRIMARY KEY (rcm_id);
 V   ALTER TABLE ONLY public."T_REF_CULTURE_MODE" DROP CONSTRAINT t_ref_culture_mode_pkey;
       public      
   freeveggie    false    1588    1588            f           2606    139939    t_ref_culture_type_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY "T_REF_CULTURE_TYPE"
    ADD CONSTRAINT t_ref_culture_type_pkey PRIMARY KEY (rct_id);
 V   ALTER TABLE ONLY public."T_REF_CULTURE_TYPE" DROP CONSTRAINT t_ref_culture_type_pkey;
       public      
   freeveggie    false    1589    1589            h           2606    139767    t_ref_evaluation_note_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY "T_REF_EVALUATION_NOTE"
    ADD CONSTRAINT t_ref_evaluation_note_pkey PRIMARY KEY (ren_id);
 \   ALTER TABLE ONLY public."T_REF_EVALUATION_NOTE" DROP CONSTRAINT t_ref_evaluation_note_pkey;
       public      
   freeveggie    false    1590    1590            j           2606    139737    t_ref_evaluation_status_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY "T_REF_EVALUATION_STATUS"
    ADD CONSTRAINT t_ref_evaluation_status_pkey PRIMARY KEY (res_id);
 `   ALTER TABLE ONLY public."T_REF_EVALUATION_STATUS" DROP CONSTRAINT t_ref_evaluation_status_pkey;
       public      
   freeveggie    false    1591    1591            l           2606    139882    t_ref_exchange_type_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY "T_REF_EXCHANGE_TYPE"
    ADD CONSTRAINT t_ref_exchange_type_pkey PRIMARY KEY (chg_id);
 X   ALTER TABLE ONLY public."T_REF_EXCHANGE_TYPE" DROP CONSTRAINT t_ref_exchange_type_pkey;
       public      
   freeveggie    false    1592    1592            n           2606    139969    t_ref_month_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY "T_REF_MONTH"
    ADD CONSTRAINT t_ref_month_pkey PRIMARY KEY (rmn_id);
 H   ALTER TABLE ONLY public."T_REF_MONTH" DROP CONSTRAINT t_ref_month_pkey;
       public      
   freeveggie    false    1593    1593            �           2606    140006    t_ref_product_pkey01 
   CONSTRAINT     _   ALTER TABLE ONLY "T_REF_PRODUCT"
    ADD CONSTRAINT t_ref_product_pkey01 PRIMARY KEY (rpc_id);
 N   ALTER TABLE ONLY public."T_REF_PRODUCT" DROP CONSTRAINT t_ref_product_pkey01;
       public      
   freeveggie    false    1605    1605            p           2606    139833 !   t_ref_product_request_status_pkey 
   CONSTRAINT     {   ALTER TABLE ONLY "T_REF_PRODUCT_REQUEST_STATUS"
    ADD CONSTRAINT t_ref_product_request_status_pkey PRIMARY KEY (rpr_id);
 j   ALTER TABLE ONLY public."T_REF_PRODUCT_REQUEST_STATUS" DROP CONSTRAINT t_ref_product_request_status_pkey;
       public      
   freeveggie    false    1594    1594            r           2606    139954    t_ref_product_type_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY "T_REF_PRODUCT_TYPE"
    ADD CONSTRAINT t_ref_product_type_pkey PRIMARY KEY (rpt_id);
 V   ALTER TABLE ONLY public."T_REF_PRODUCT_TYPE" DROP CONSTRAINT t_ref_product_type_pkey;
       public      
   freeveggie    false    1595    1595            t           2606    139405    t_ref_relation_status_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY "T_REF_RELATIONS_STATUS"
    ADD CONSTRAINT t_ref_relation_status_pkey PRIMARY KEY (rrs_id);
 ]   ALTER TABLE ONLY public."T_REF_RELATIONS_STATUS" DROP CONSTRAINT t_ref_relation_status_pkey;
       public      
   freeveggie    false    1596    1596            v           2606    139432    t_ref_relation_type_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY "T_REF_RELATION_TYPE"
    ADD CONSTRAINT t_ref_relation_type_pkey PRIMARY KEY (rrt_id);
 X   ALTER TABLE ONLY public."T_REF_RELATION_TYPE" DROP CONSTRAINT t_ref_relation_type_pkey;
       public      
   freeveggie    false    1597    1597            x           2606    139416    t_ref_user_role_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "T_REF_USER_ROLE"
    ADD CONSTRAINT t_ref_user_role_pkey PRIMARY KEY (rur_id);
 P   ALTER TABLE ONLY public."T_REF_USER_ROLE" DROP CONSTRAINT t_ref_user_role_pkey;
       public      
   freeveggie    false    1598    1598            z           2606    139447    t_ref_user_status_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY "T_REF_USER_STATUS"
    ADD CONSTRAINT t_ref_user_status_pkey PRIMARY KEY (rus_id);
 T   ALTER TABLE ONLY public."T_REF_USER_STATUS" DROP CONSTRAINT t_ref_user_status_pkey;
       public         postgres    false    1599    1599            �           2606    140123    tj_reap_calendar_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY "TJ_REAP_CALENDAR"
    ADD CONSTRAINT tj_reap_calendar_pkey PRIMARY KEY (rpc_id, rmn_id);
 R   ALTER TABLE ONLY public."TJ_REAP_CALENDAR" DROP CONSTRAINT tj_reap_calendar_pkey;
       public      
   freeveggie    false    1612    1612    1612            |           2606    140061    tj_seed_month_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY "TJ_SEED_CALENDAR"
    ADD CONSTRAINT tj_seed_month_pkey PRIMARY KEY (rpc_id, rmn_id);
 O   ALTER TABLE ONLY public."TJ_SEED_CALENDAR" DROP CONSTRAINT tj_seed_month_pkey;
       public      
   freeveggie    false    1602    1602    1602            �           2606    140124    fk_t_reap_calendar_mnt_id    FK CONSTRAINT     �   ALTER TABLE ONLY "TJ_REAP_CALENDAR"
    ADD CONSTRAINT fk_t_reap_calendar_mnt_id FOREIGN KEY (rmn_id) REFERENCES "T_REF_MONTH"(rmn_id);
 V   ALTER TABLE ONLY public."TJ_REAP_CALENDAR" DROP CONSTRAINT fk_t_reap_calendar_mnt_id;
       public    
   freeveggie    false    1593    1901    1612            �           2606    140129    fk_t_reap_calendar_rpc_id    FK CONSTRAINT     �   ALTER TABLE ONLY "TJ_REAP_CALENDAR"
    ADD CONSTRAINT fk_t_reap_calendar_rpc_id FOREIGN KEY (rpc_id) REFERENCES "T_REF_PRODUCT"(rpc_id);
 V   ALTER TABLE ONLY public."TJ_REAP_CALENDAR" DROP CONSTRAINT fk_t_reap_calendar_rpc_id;
       public    
   freeveggie    false    1925    1612    1605            �           2606    140007    fk_t_ref_product_rpt_id    FK CONSTRAINT     �   ALTER TABLE ONLY "T_REF_PRODUCT"
    ADD CONSTRAINT fk_t_ref_product_rpt_id FOREIGN KEY (rpc_rpt_id) REFERENCES "T_REF_PRODUCT_TYPE"(rpt_id);
 Q   ALTER TABLE ONLY public."T_REF_PRODUCT" DROP CONSTRAINT fk_t_ref_product_rpt_id;
       public    
   freeveggie    false    1595    1605    1905            �           2606    140062    fk_t_seed_calendar_rpc_id    FK CONSTRAINT     �   ALTER TABLE ONLY "TJ_SEED_CALENDAR"
    ADD CONSTRAINT fk_t_seed_calendar_rpc_id FOREIGN KEY (rpc_id) REFERENCES "T_REF_PRODUCT"(rpc_id);
 V   ALTER TABLE ONLY public."TJ_SEED_CALENDAR" DROP CONSTRAINT fk_t_seed_calendar_rpc_id;
       public    
   freeveggie    false    1605    1925    1602            �           2606    140051    fk_t_seed_month_mnt_id    FK CONSTRAINT     �   ALTER TABLE ONLY "TJ_SEED_CALENDAR"
    ADD CONSTRAINT fk_t_seed_month_mnt_id FOREIGN KEY (rmn_id) REFERENCES "T_REF_MONTH"(rmn_id);
 S   ALTER TABLE ONLY public."TJ_SEED_CALENDAR" DROP CONSTRAINT fk_t_seed_month_mnt_id;
       public    
   freeveggie    false    1593    1602    1901            �   
   x���          �   
   x���          �   
   x���          �   q   x���v
Q���WP
�ru�w�		r���wqUR�(J΍�L�Q �9�I�99��
a�>���
�:
��E)�y��\��f4,)1'9?��
�M+I-*JLN��� �E      �   i   x���v
Q���WP
�ru�w�		r��pUR�(J.��L�Q �9�I�99��
a�>���
�:
�E��9�E��\���f4-)3�
&MJ���� iNB�      �   {   x���v
Q���WP
�ru�ws�	u������qUR�(J͋�L�Q �9�I�99��
 U��
�:
�e�E�
I�)��\��h4�j���/�M̡�y&@�����:SX����� 78u�      �   p   x���v
Q���WP
�ru�ws�	u����q	VR�(J-��L�Q �9�I�99��
 u��
�:
�ũ%%�)��\�T0�hbQjn~��4�X���	5�� �	J�      �   i   x���v
Q���WP
�ru�w�p�p�sw��pUR�H�H��L�Q �9�I�99��
a�>���
�:
���E����\��f4-=��:����� �&C�      �   �   x��ҽ�0����a��89`$QH���U1��BM|{[�2��˹''��0%�$�ix.nIL.6�����}0WP�<�O�{���༩PT~�hEc��+�,d�Ne�Bk-�^6-Z�L�?lM�J04����`o:PO5�h⠉��#�%�h%Xh���nb�)���(f�5���e� �p�      �   
   x���          �   �   x���v
Q���WP
�ru��w	u�C]�C�CCB��4�
��3St@tNfRjNN��B��P������zQjaijq���5�'��5�[������NUs���&&'����P�`p@��C��� �rj�      �   e   x���v
Q���WP
�ru��w	u��pUR�(*(��L�Q �9�I�99��
a�>���
�:
�iE��%��\���e4�,5=�$1)'d ��.�      �   �   x���v
Q���WP
�rub�O����ǐ�`%��������������������a���^�ZX�Z\��i��I��F@R�R2�ҩd�1�ĲĜ̔Ē�*�i�wZi1�L4�������2�� ��{�      �   v   x���v
Q���WP
�rub�O���� W%��������������������a����V��Z�������V������i��I��F`��e�1д������b��\\ G�KK      �   r   x���v
Q���WP
�ru������*)h��g��(��̤Ԝ�TM�0G�P�`C����"uMk.O
�1������N�Q�@�Sr3�(5�hPqiAj��8.. �Yn      �   �   x���v
Q���WP
�ru����8��+)h��g��(��̤Ԝ�TM�0G�P�`C���ruMk.O�2T������B�a�@Òr��s2�K�a�	���ԜT�f
4,�(9#�,d )us�     