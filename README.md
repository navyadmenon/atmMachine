# atmMachine
The project performs basic ATM operation of check balance and withdraw money
User request for balance check. 
        The api url is : /atm/balance
        Request body : 
            {  
              "accountNo": Number,  
               "pin": Number
            }
            
User request a withdrawal. If successful - details of the notes that would be dispensed along with remaining balance. 
       The api url is : /atm/dispense
        Request body : 
            {  
              "accountNo": Number,  
               "pin": Number,
               "amount" : Number
            }
