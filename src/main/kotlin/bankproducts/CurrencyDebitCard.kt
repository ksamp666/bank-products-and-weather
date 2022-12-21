package bankproducts

import bankproducts.enums.Currency
import bankproducts.exceptions.InsufficientFundsException
import java.lang.IllegalArgumentException

open class CurrencyDebitCard(
    name: String,
    currency: Currency,
    balance: Double
) : BankProduct(name, currency, balance), ICard {
    init {
        if (balance < 0) {
            throw IllegalArgumentException("Can't create debit card with negative balance.")
        }
    }

    override fun withdraw(amount: Double) {
        if (amount <= 0) {
            throw IllegalArgumentException("Can't withdraw negative or zero amount. $amount supplied.")
        }
        if (amount > _balance) {
            throw InsufficientFundsException("Failed to withdraw $amount $_currency, the balance is $_balance")
        }
        _balance -= amount
    }

    override fun toString(): String {
        return "Currency debit card '$_name', balance: $_balance $_currency"
    }
}