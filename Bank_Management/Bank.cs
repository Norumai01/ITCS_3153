class BankAccount
{
    private string fullName = "Bob Denis";
    private string userName = "Denis_69";
    private long routingNum = 3918203192;   // Future implementation, not within scope this project. 
    private string passWord = "LeNY_3821";
   
    public string userChecker
    {
        get { return userName; }
    }
    public string passChecker
    {
        get { return passWord; }
    }
    public string Name
    {
        get { return fullName; }
    }

}

class CheckingAccount : BankAccount 
{
    private long accountNum = 492101;
    public long accountNumChecker
    {
        get { return accountNum; }
    }
    private double accountBalance = 4057.69;

    public double balanceChecker
    {
        set { accountBalance = value; }
        get { return accountBalance; }
    }
}

class SavingAccount : BankAccount
{
    private long accountNum = 237181;
    private double accountBalance = 54652.86;
    private int transactionCount = 2;
    private const int transactionLimit = 6;
    private double interestRate = 0.0385;    // Future implementation, not within scope this project.

    public long accountNumChecker
    {
        get { return accountNum; }
    }
    public double balanceChecker
    {
        set { accountBalance = value; }
        get { return accountBalance; }
    }
    public int transactionChecker 
    {
        set { transactionCount = value; }
        get { return transactionCount; }
    }
    public int limitChecker
    {
        get { return transactionLimit; }
    }
}

class moneyMarketAccount : BankAccount
{
    private long accountNum = 529042;   
    private double accountBalance = 134562.84;
    private int transactionCount = 0;
    private const int transactionLimit = 2;
    private double interestRate = 0.0505;    // Future implementation, not within scope this project.
    
    public long accountNumChecker
    {
        get { return accountNum; }
    }
    public double balanceChecker
    {
        set { accountBalance = value; }
        get { return accountBalance; }
    }
    public int transactionChecker
    {
        set { transactionCount = value; }
        get { return transactionCount; }
    }
    public int limitChecker
    {
        get { return transactionLimit; }
    }
}