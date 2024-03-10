using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BankProject_Sample
{
    class Program
    {
        public static double GetDoubleFromUser(string prompt)
        {
            // Check the user input to be double.
            double input;
            while (true)
            {
                if (double.TryParse(Console.ReadLine(), out input)) break;
                else Console.WriteLine("Sorry, that is not a valid number. Please try again.");
            }
            return input;
        }
        public static string GetStringFromUser(string input)
        {
            // Check for user input to be those selection of string.
            while (true)
            {
                if ((input == "checking") || (input == "saving") || (input == "money market")) break;
                else
                {
                    Console.WriteLine("Sorry, this is an invalid account.");
                    input = Console.ReadLine();
                }
            }
            return input;
        }

        static void Main(string[] args)
        {
            BankAccount User = new BankAccount();
            CheckingAccount Checking = new CheckingAccount();
            SavingAccount Saving = new SavingAccount();
            moneyMarketAccount moneyMarket = new moneyMarketAccount();
            
            Console.WriteLine("Enter your Username:");
            string userInput = Console.ReadLine();
            Console.WriteLine("Enter your Password:");
            string passInput = Console.ReadLine();
            if ((passInput == User.passChecker) && (userInput == User.userChecker))
            {
                Console.Clear();
                Console.WriteLine("Welcome " + User.Name + "!");
                Console.WriteLine("Checking Account Balance: $" + Checking.balanceChecker);
                Console.WriteLine("Saving Account Balance: $" + Saving.balanceChecker);
                Console.WriteLine("Money Market Account Balance: $" + moneyMarket.balanceChecker);
                Console.WriteLine("What would you like to do: deposit, withdraw, transfer or exit?");
                string input = Console.ReadLine().ToLower();
                while (input != "exit")
                {
                    if (input == "deposit")
                    {
                        Console.WriteLine("How much would you like to deposit? Ex. ##.## or ##");
                        double deposit = GetDoubleFromUser(Console.ReadLine());
                        Console.WriteLine("Which account would you like to deposit from? Checking, Saving, or Money Market");
                        string accountInput = GetStringFromUser(Console.ReadLine());
                        switch (accountInput.ToLower())
                        {
                            case "checking":
                                Checking.balanceChecker += deposit;
                                break;
                            case "saving":
                                Saving.balanceChecker += deposit;
                                break;
                            case "money market":
                                moneyMarket.balanceChecker += deposit;
                                break;
                        }
                        Console.Clear();
                        Console.WriteLine("Checking Account Balance: $" + Checking.balanceChecker);
                        Console.WriteLine("Saving Account Balance: $" + Saving.balanceChecker);
                        Console.WriteLine("Money Market Account Balance: $" + moneyMarket.balanceChecker);
                        Console.WriteLine("Deposit successful!");
                        Console.WriteLine("Would you like to deposit, withdraw, transfer or exit?");
                        input = Console.ReadLine().ToLower();
                    }
                    else if (input == "withdraw") 
                    {
                        Console.WriteLine("How much would you like to withdraw? Ex. ##.## or ##");
                        double withdraw = GetDoubleFromUser(Console.ReadLine());
                        Console.WriteLine("Which account would you like to withdraw from? Checking, Saving, or Money Market");
                        string accountInput = GetStringFromUser(Console.ReadLine());
                        switch (accountInput.ToLower())
                        {
                            case "checking":
                                Checking.balanceChecker -= withdraw;
                                break;
                            case "saving":
                                if (Saving.transactionChecker == Saving.limitChecker)
                                {
                                    Console.WriteLine("You cannot make anymore transactions.");
                                }
                                else 
                                {
                                    Saving.balanceChecker -= withdraw;
                                    Saving.transactionChecker += 1;
                                }
                                break;
                            case "money market":
                                if (moneyMarket.transactionChecker == moneyMarket.limitChecker)
                                {
                                    Console.WriteLine("You cannot make anymore transactions.");
                                }
                                else
                                {
                                    moneyMarket.balanceChecker -= withdraw;
                                    moneyMarket.transactionChecker += 1;
                                }
                                break;
                        }
                        Console.Clear();
                        Console.WriteLine("Checking Account Balance: $" + Checking.balanceChecker);
                        Console.WriteLine("Saving Account Balance: $" + Saving.balanceChecker);
                        Console.WriteLine("Money Market Account Balance: $" + moneyMarket.balanceChecker);
                        Console.WriteLine("Withdraw successful!");
                        Console.WriteLine("Would you like to deposit, withdraw, transfer or exit?");
                        input = Console.ReadLine().ToLower();
                    }
                    else if (input == "transfer")
                    {
                        Console.WriteLine("How much would you like to transfer? Ex. ##.## or ##");
                        double transfer = GetDoubleFromUser(Console.ReadLine());
                        Console.WriteLine("Which account would you like to transfer from? Checking, Saving, or Money Market");
                        string sourceAccount = GetStringFromUser(Console.ReadLine());
                        Console.WriteLine("Which account would you to transfer to? Checking, Saving, or Money Market");
                        string destination = GetStringFromUser(Console.ReadLine());

                        switch (sourceAccount.ToLower())
                        {
                            case "checking":
                                Checking.balanceChecker -= transfer;
                                break;
                            case "saving":
                                Saving.balanceChecker -= transfer;
                                break;
                            case "money market":
                                moneyMarket.balanceChecker -= transfer;
                                break;
                        }

                        switch (destination.ToLower())
                        {
                            case "checking":
                                Checking.balanceChecker += transfer;
                                break;
                            case "saving":
                                Saving.balanceChecker += transfer;
                                break;
                            case "money market":
                                moneyMarket.balanceChecker += transfer;
                                break;
                        }
                        Console.Clear();
                        Console.WriteLine("Checking Account Balance: $" + Checking.balanceChecker);
                        Console.WriteLine("Saving Account Balance: $" + Saving.balanceChecker);
                        Console.WriteLine("Money Market Account Balance: $" + moneyMarket.balanceChecker);
                        Console.WriteLine("Transfer successful!");
                        Console.WriteLine("Would you like to deposit, withdraw, transfer, or exit?");
                        input = Console.ReadLine().ToLower();
                    }
                    else
                    {
                        Console.Clear();
                        Console.WriteLine("Checking Account Balance: $" + Checking.balanceChecker);
                        Console.WriteLine("Saving Account Balance: $" + Saving.balanceChecker);
                        Console.WriteLine("Money Market Account Balance: $" + moneyMarket.balanceChecker);
                        Console.WriteLine("Please write deposit, withdraw, transfer, or exit.");
                        input = Console.ReadLine().ToLower();
                    }
                }
            }
            else
            {
                Console.WriteLine("Wrong username or password, please try again");
            }
            // Program won't exit until a random key is clicked. 
            Console.WriteLine("Click a random key to exit.");
            Console.ReadKey();
        }
    }
}
