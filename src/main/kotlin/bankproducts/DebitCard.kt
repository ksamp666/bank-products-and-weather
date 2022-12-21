package bankproducts

import bankproducts.enums.Currency


class DebitCard(
    name: String,
    balance: Double
) : CurrencyDebitCard(name, Currency.RUB, balance) {

    override fun toString(): String {
        return "Debit card '$_name', balance: $_balance $_currency"
    }
}