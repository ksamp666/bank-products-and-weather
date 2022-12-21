package bankproducts

interface IBankProduct {
    fun getBalance(): Double
    fun topUp(amount: Double)
    fun withdraw(amount: Double)
}