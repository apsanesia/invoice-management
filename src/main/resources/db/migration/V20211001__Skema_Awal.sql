CREATE TABLE running_number (
    id character varying(255) NOT NULL,
    prefix character varying(100) NOT NULL,
    last_number bigint NOT NULL
);

CREATE TABLE bank (
    id character varying(255) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    code character varying(100) NOT NULL,
    name character varying(255) NOT NULL,
    central_bank_code character varying(3) NOT NULL
);

CREATE TABLE bank_account (
    id character varying(255) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    bank_id character varying(255) NOT NULL,
    account_number character varying(255) NOT NULL,
    account_name character varying(255) NOT NULL,
    branch_name character varying(255) NOT NULL
);

CREATE TABLE virtual_account_configuration (
    id character varying(255) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    payment_provider_id character varying(255) NOT NULL,
    bank_account_id character varying(255) NOT NULL,
    code character varying(100) NOT NULL,
    name character varying(255) NOT NULL,
    transaction_fee_flat numeric(19,2) NOT NULL,
    transaction_fee_percentage float NOT NULL,
    company_prefix character varying(255) NOT NULL,
    account_number_length int NOT NULL,
    CONSTRAINT transaction_fee_flat_check CHECK ((transaction_fee_flat >= (0)::numeric))
);

CREATE TABLE invoice_type_configuration (
    invoice_type_id character varying(255) NOT NULL,
    virtual_account_configuration_id character varying(255) NOT NULL
);

CREATE TABLE customer (
    id character varying(255) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    code character varying(100) NOT NULL,
    name character varying(255) NOT NULL,
    email character varying(100) NOT NULL,
    mobile_phone character varying(300) NOT NULL
);

CREATE TABLE invoice (
    id character varying(255) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    amount numeric(19,2) NOT NULL,
    description character varying(255) NOT NULL,
    due_date date NOT NULL,
    invoice_number character varying(100) NOT NULL,
    paid boolean NOT NULL,
    total_payment numeric(19,2) NOT NULL,
    payment_status character varying (36) NOT NULL,
    invoice_type_id character varying(255) NOT NULL,
    customer_id character varying(255) NOT NULL,
    CONSTRAINT invoice_amount_check CHECK ((amount >= (0)::numeric))
);

CREATE TABLE invoice_type (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    code character varying(100) NOT NULL,
    name character varying(100) NOT NULL,
    payment_type character varying(255) NOT NULL
);

CREATE TABLE invoice_type_provider (
    invoice_type_id character varying(36) NOT NULL,
    payment_provider_id character varying(36) NOT NULL
);

CREATE TABLE payment (
    id character varying(255) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    provider_reference character varying(255) NOT NULL,
    transaction_time timestamp without time zone NOT NULL,
    virtual_account_id character varying(255) NOT NULL
);

CREATE TABLE payment_provider (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    code character varying(100) NOT NULL,
    logo character varying(255),
    name character varying(100) NOT NULL
);

CREATE TABLE virtual_account (
    id character varying(255) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    account_number character varying(255) NOT NULL,
    invoice_id character varying(255) NOT NULL,
    virtual_account_configuration_id character varying(255) NOT NULL
);

CREATE TABLE activity_log(
    id character varying(255) NOT NULL,
    activity_time timestamp without time zone,
    feature character varying(255) NOT NULL,
    message character varying(255) NOT NULL
);

ALTER TABLE ONLY running_number
    ADD CONSTRAINT running_number_pkey PRIMARY KEY (id);

ALTER TABLE ONLY bank
    ADD CONSTRAINT bank_pkey PRIMARY KEY (id);

ALTER TABLE ONLY bank_account
    ADD CONSTRAINT bank_account_pkey PRIMARY KEY (id);

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_type
    ADD CONSTRAINT invoice_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT invoice_type_provider_pkey PRIMARY KEY (invoice_type_id, payment_provider_id);

ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);

ALTER TABLE ONLY payment_provider
    ADD CONSTRAINT payment_provider_pkey PRIMARY KEY (id);

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT virtual_account_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_type_configuration
    ADD CONSTRAINT invoice_type_configuration_pkey PRIMARY KEY (invoice_type_id, virtual_account_configuration_id);

ALTER TABLE ONLY virtual_account_configuration
    ADD CONSTRAINT virtual_account_configuration_pkey PRIMARY KEY (id);

ALTER TABLE ONLY activity_log
    ADD CONSTRAINT activity_log_pkey PRIMARY KEY (id);

ALTER TABLE ONLY bank_account
    ADD CONSTRAINT fk_bank_account_bank FOREIGN KEY (bank_id) REFERENCES bank(id);

ALTER TABLE ONLY payment
    ADD CONSTRAINT fk8g41e3qt94b8dktnynoto4nde FOREIGN KEY (virtual_account_id) REFERENCES virtual_account(id);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_type_provider_provider FOREIGN KEY (payment_provider_id) REFERENCES payment_provider(id);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_type_provider_type FOREIGN KEY (invoice_type_id) REFERENCES invoice_type(id);

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fkgh9u73nb4nsk7hd2sp8l52xqg FOREIGN KEY (invoice_id) REFERENCES invoice(id);

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fkey_virtual_account_virtual_account_configuration FOREIGN KEY (virtual_account_configuration_id) REFERENCES virtual_account_configuration(id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT fkqss90tikrowtmcc9gw6hegq7d FOREIGN KEY (invoice_type_id) REFERENCES invoice_type(id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT fk_invoice_customer FOREIGN KEY (customer_id) REFERENCES customer(id);

ALTER TABLE ONLY invoice_type_configuration
    ADD CONSTRAINT fk_invoice_type_configuration_invoice_type FOREIGN KEY (invoice_type_id) REFERENCES invoice_type(id);

ALTER TABLE ONLY invoice_type_configuration
    ADD CONSTRAINT fk_invoice_type_configuration_virtual_account_configuration FOREIGN KEY (virtual_account_configuration_id) REFERENCES virtual_account_configuration(id);

ALTER TABLE ONLY virtual_account_configuration
    ADD CONSTRAINT fk_virtual_account_configuration_payment_provider FOREIGN KEY (payment_provider_id) REFERENCES payment_provider(id);

ALTER TABLE ONLY virtual_account_configuration
    ADD CONSTRAINT fk_virtual_account_configuration_bank_account FOREIGN KEY (bank_account_id) REFERENCES bank_account(id);
