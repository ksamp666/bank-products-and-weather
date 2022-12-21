package bankproducts

import bankproducts.enums.Currency
import java.lang.IllegalArgumentException


abstract class BankProduct(
    protected var _name: String,
    protected var _currency: Currency,
    protected var _balance: Double
): IBankProduct {
    override fun toString(): String {
        return "Product '$_name', balance: $_balance $_currency"
    }

    override fun topUp(amount: Double) {
        if (amount <= 0) {
            throw IllegalArgumentException("Can't top up for negative or zero amount. $amount supplied.")
        }
        _balance += amount
    }

    abstract override fun withdraw(amount: Double)

    override fun getBalance(): Double {
        return _balance
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BankProduct) return false

        if (_name != other._name) return false
        if (_currency != other._currency) return false
        if (_balance != other._balance) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _name.hashCode()
        result = 31 * result + _currency.hashCode()
        result = 31 * result + _balance.hashCode()
        return result
    }
}