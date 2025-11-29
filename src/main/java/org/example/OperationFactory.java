package org.example;

import java.util.HashMap;
import java.util.Map;

public class OperationFactory {

   private static Map<String, Operation> OPERATIONS = Map.of(
           "+", new Add(),
           "-", new Sub(),
           "*", new Mutil(),
           "/", new Dvide()

   );

   public static Operation get(String operation) {
       Operation op =  OPERATIONS.get(operation);

       if( op == null) {
            throw new IllegalArgumentException("Unknown operation: " + operation);
       }

       return op;
   }


}
