package bankproducts

import bankproducts.enums.Currency
import java.lang.IllegalArgumentException
import javax.naming.OperationNotSupportedException

class Deposit(
    name: String,
    currency: Currency,
    balance: Double
): BankProduct(name, currency, balance), IDeposit {
    init {
        if (balance < 0) {
            throw IllegalArgumentException("Can't create deposit with negative balance.")
        }
    }

    override fun withdraw(amount: Double) {
        throw OperationNotSupportedException("Withdraw operation is not supported for Deposits")
    }

    override fun close() {
        _balance = 0.0
    }

    override fun toString(): String {
        return "Deposit '$_name', balance: $_balance $_currency"
    }
}