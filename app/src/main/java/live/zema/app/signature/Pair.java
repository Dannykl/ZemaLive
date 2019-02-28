package live.zema.app.signature;

import java.io.Serializable;

public class Pair implements Serializable
{
      private String valOne;
      private long valTwo;


      public Pair(String valOne, long valTwo) {
            this.valOne = valOne;
            this.valTwo = valTwo;
      }

      public String getValOne() {
            return valOne;
      }

      public long getValTwo() {
            return valTwo;
      }
}
