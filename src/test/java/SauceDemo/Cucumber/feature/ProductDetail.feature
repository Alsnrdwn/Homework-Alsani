Feature: Membuka Detail Produk

  Scenario: Success Membuka Detail Produk
    Given Halaman produk SauceDemo
    When Click product name
    Then User is on Product Detail Page