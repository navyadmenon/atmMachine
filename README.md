# atmMachine
The project performs basic ATM operation of check balance and withdraw money through API url request. 



User request for balance check. 
        The api url path is : /atm/balance ; method : POST   
        
        Request body : 
            {  
              "accountNo": number,  
               "pin": integer
            }
            
User request a withdrawal. If successful - details of the notes that would be dispensed along with remaining balance. 
       The api url path is : /atm/dispense;  method : POST   
       
                Request body : 
                    {  
                      "accountNo": number,  
                       "pin": integer,
                       "amount" : integer
                    }
