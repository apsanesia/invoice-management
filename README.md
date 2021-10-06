# Aplikasi Invoice Management #

Aplikasi ini dapakai untuk mengelola invoice dan menyambungkan dengan berbagai metode pembayaran.
Diantara metode pembayaran yang akan disupport antara lain

* Virtual Account Bank
    * Bank BNI
    * Bank CIMB
    * Bank BSI

* e-Wallet
    * OVO
    * GoPay

* QR Payment
    * QRIS

* Tipe tagihan yang tersedia:
    * CLOSED: bayar sesuai namonal. Kalau tidak sesuai, ditolak
    * CLOSED: pembayaran berapapun diterima
    * INSTALLMENT: Pembayaran diterima selama total akumulasi lebih kecil atau sama dengan nilai tagihan

# Cara setup database

1. Jalankan postgresql di Docker
    ...
        docker run --rm \
            --name invoice-db \
            -e POSTGRES_DB=invoicedb \
            -e POSTGRES_USER=invoice \
            -e POSTGRES_PASSWORD=invoicepass \
            -e PGDATA=/var/lib/postgresql/data/pgdata \
            -v "$PWD/invoicedb-data;/var/lib/postgresql/data" \
            -p 5432:5432 \
            postgres:13
    ...
