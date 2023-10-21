Feature: Melakukan checkout barang

  Scenario: Success melakukan checkout
    Given Halaman awal produk SauceDemo
    When Menambahkan produk ke keranjang
    And Click cart button
    Then User is on cart page
    When Click continue button
    Then User is on order data form page
    When Input first name
    And Input last name
    And Input postal code
    And Click overview button
    Then User is on overview page
    When Click finish button
    Then User is on checkout complete page
