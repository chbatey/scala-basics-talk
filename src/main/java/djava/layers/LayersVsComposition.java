package djava.layers;

public class LayersVsComposition {

  //#add
  public static int add(int x, int y) {
    return x + y;
  }
  //#add

  //#add10
  public static int add10(int x) {
    return x + 10;
  }
  //#add10

  //#add10-reuse
  public static int add10Take2(int x) {
    return add(x, 10);
  }
  //#add10-reuse

  //#multiply
  public static int multiply(int x, int y) {
    return x * y;
  }
  //#multiply

  //#multiply10
  public static int multiplyBy10(int x) {
    return multiply(x, 10);
  }
  //#multiply10

  //#composition
  public static int addTenThenMultiplyByTen(int x) {
    return multiplyBy10(add10Take2(x));
  }
  //#composition
}
