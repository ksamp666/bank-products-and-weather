package bankproducts

import bankproducts.enums.Currency
import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.min

class CreditCard(
    name: String,
    balance: Double,
    private var _interestRate: Float
) : BankProduct(name, Currency.RUB, balance), ICreditCard {
    override fun withdraw(amount: Double) {
        if (amount <= 0) {
            throw IllegalArgumentException("Can't withdraw negative or zero amount. $amount supplied.")
        }
        _balance -= amount
    }

    override fun getDebt(): Double {
        return abs(min(0.0, _balance))
    }

    override fun toString(): String {
        return "Credit card '$_name', balance: $_balance $_currency, interest rate: $_interestRate"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CreditCard) return false
        if (!super.equals(other)) return false

        if (_interestRate != other._interestRate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + _interestRate.hashCode()
        return result
    }
}