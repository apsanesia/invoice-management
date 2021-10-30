-- bank

insert into bank (id, code, name, central_bank_code, status_record, created, created_by)
    values ('bca', 'BCA', 'Bank BCA', '014', 'ACTIVE', current_timestamp, 'TEST');

insert into bank (id, code, name, central_bank_code, status_record, created, created_by)
    values ('bsi', 'BSI', 'Bank Syariah Indonesia', '451', 'ACTIVE', current_timestamp, 'TEST');

insert into bank (id, code, name, central_bank_code, status_record, created, created_by)
    values ('bni', 'BNI', 'Bank Nasional Indonesia', '009', 'ACTIVE', current_timestamp, 'TEST');

insert into bank (id, code, name, central_bank_code, status_record, created, created_by)
    values ('cimb', 'CIMB', 'CIMB Niaga', '022', 'ACTIVE', current_timestamp, 'TEST');

-- bank account

insert into bank_account (id, bank_id, account_number, account_name, branch_name, status_record, created, created_by)
    values ('bca001', 'bca', '123456789001', 'Test Account BCA', 'Kantor Pusat', 'ACTIVE', current_timestamp, 'TEST');

insert into bank_account (id, bank_id, account_number, account_name, branch_name, status_record, created, created_by)
    values ('bsi001', 'bsi', '123456789002', 'Test Account BSI', 'Kantor Pusat', 'ACTIVE', current_timestamp, 'TEST');

insert into bank_account (id, bank_id, account_number, account_name, branch_name, status_record, created, created_by)
    values ('bni001', 'bni', '123456789003', 'Test Account BNI', 'Kantor Pusat', 'ACTIVE', current_timestamp, 'TEST');

insert into bank_account (id, bank_id, account_number, account_name, branch_name, status_record, created, created_by)
    values ('cimb001', 'cimb', '123456789004', 'Test Account CIMB', 'Kantor Pusat', 'ACTIVE', current_timestamp, 'TEST');

-- payment provider

insert into payment_provider (id, code, name, status_record, created, created_by)
    values ('bankbca', 'BCA', 'Bank BCA', 'ACTIVE', current_timestamp, 'TEST');

insert into payment_provider (id, code, name, status_record, created, created_by)
    values ('bankbni', 'BNI', 'Bank BNI', 'ACTIVE', current_timestamp, 'TEST');

insert into payment_provider (id, code, name, status_record, created, created_by)
    values ('bankbsi', 'BSI', 'Bank BSI', 'ACTIVE', current_timestamp, 'TEST');

insert into payment_provider (id, code, name, status_record, created, created_by)
    values ('bankcimb', 'CIMB', 'Bank CIMB', 'ACTIVE', current_timestamp, 'TEST');

insert into payment_provider (id, code, name, status_record, created, created_by)
    values ('ovo', 'OVO', 'Ovo', 'ACTIVE', current_timestamp, 'TEST');

insert into payment_provider (id, code, name, status_record, created, created_by)
    values ('gopay', 'GOPAY', 'GoPay', 'ACTIVE', current_timestamp, 'TEST');

-- virtual account configuration

insert into virtual_account_configuration (id, code, name, payment_provider_id, bank_account_id, transaction_fee_flat, transaction_fee_percentage, company_prefix, account_number_length, status_record, created, created_by)
    values ('va-bni', 'VA-BNI', 'Virtual Account BNI', 'bankbni', 'bni001', 2000, 0.0, '0123', 12, 'ACTIVE', current_timestamp, 'TEST');

insert into virtual_account_configuration (id, code, name, payment_provider_id, bank_account_id, transaction_fee_flat, transaction_fee_percentage, company_prefix, account_number_length, status_record, created, created_by)
    values ('va-gopay', 'VA-GOPAY', 'Pembayaran GoPay', 'bankbca', 'bca001', 0, 0.025, '0123', 12, 'ACTIVE', current_timestamp, 'TEST');

-- invoice type

insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
    values ('registrasi', 'REG-001', 'Biaya Pendaftaran', 'CLOSED', 'ACTIVE', current_timestamp, 'TEST');

insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
    values ('donasi', 'DONASI-001', 'Sumbangan Sukarela', 'OPEN', 'ACTIVE', current_timestamp, 'TEST');

insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
    values ('uang-muka', 'DP-001', 'Uang Muka', 'INSTALLMENT', 'ACTIVE', current_timestamp, 'TEST');

-- invoice type configuration

insert into invoice_type_configuration (invoice_type_id, virtual_account_configuration_id)
    values ('registrasi', 'va-bni');

insert into invoice_type_configuration (invoice_type_id, virtual_account_configuration_id)
    values ('registrasi', 'va-gopay');

insert into invoice_type_configuration (invoice_type_id, virtual_account_configuration_id)
    values ('donasi', 'va-gopay');

insert into invoice_type_configuration (invoice_type_id, virtual_account_configuration_id)
    values ('uang-muka', 'va-bni');

-- customer

insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by)
    values ('c001', 'CUST-001', 'Customer 001', 'c001@yopmail.com', '081234567890', 'ACTIVE', current_timestamp, 'TEST');
