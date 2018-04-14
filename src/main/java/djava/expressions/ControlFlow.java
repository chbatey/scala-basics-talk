package djava.expressions;

public class ControlFlow {

  public static boolean positive(int x) {
    return x > 0;
  }

  public static void ifs() {
    int x = 10;
    {
//      boolean positive;
//      #if-return
//      if (x > 0) {
//        true;
//      } else {
//        false;
//      }
//      #if-return
    }
    {
      //#if-mutable
      boolean positive;
      if (x > 0) {
        positive = true;
      } else {
        positive = false;
      }
      //#if-mutable
    }

    {
      //#if-uninit
      boolean shouldAct;
      if (x > 0) {
        shouldAct = true;
      }
      //#if-uninit
    }

    {
      boolean positive;
      if (x > 0) positive = true;
      else positive = false;
    }

    {
      //#if-ternary
      boolean positive = x > 0 ? true : false;
      //#if-ternary
    }
  }
}
