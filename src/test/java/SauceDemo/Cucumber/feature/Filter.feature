Feature: Menggunakan fitur filter produk

  Scenario: Success menampilkan produk sesuai filter
    Given Halaman produk SauceDemo without filter
    When Click tombol filter
    And Click pilihan dari low to high price
    Then User is on product page with filtered product