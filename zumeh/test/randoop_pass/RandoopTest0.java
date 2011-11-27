package randoop_pass;

import junit.framework.*;

public class RandoopTest0 extends TestCase {

  public static boolean debug = false;

  public void test1() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test1");


    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      com.es.zumeh.client.model.Password var1 = new com.es.zumeh.client.model.Password("");
      fail("Expected exception of type java.lang.Exception");
    } catch (java.lang.Exception e) {
      // Expected exception.
    }

  }

  public void test2() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test2");


    com.es.zumeh.client.model.Password var1 = new com.es.zumeh.client.model.Password("hi!");
    boolean var3 = var1.checkPasswords("");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var3 == false);

  }

  public void test3() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test3");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("hi!");
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      var0.setPassword("");
      fail("Expected exception of type java.lang.Exception");
    } catch (java.lang.Exception e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);

  }

  public void test4() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test4");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("");
    var0.setPassword("hi!");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);

  }

  public void test5() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test5");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    var0.setPassword("hi!");
    var0.setPassword("hi!");
    java.lang.String var5 = var0.getPassword();
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var5 + "' != '" + "aff97160474a056e838c1f721af01edf"+ "'", var5.equals("aff97160474a056e838c1f721af01edf"));

  }

  public void test6() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test6");


    com.es.zumeh.client.model.Password var1 = new com.es.zumeh.client.model.Password("aff97160474a056e838c1f721af01edf");

  }

  public void test7() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test7");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    java.lang.String var1 = var0.getPassword();
    boolean var3 = var0.checkPasswords("");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var1 + "' != '" + ""+ "'", var1.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var3 == false);

  }

  public void test8() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test8");


    com.es.zumeh.client.model.Password var1 = new com.es.zumeh.client.model.Password("hi!");
    boolean var3 = var1.checkPasswords("hi!");
    boolean var5 = var1.checkPasswords("");
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      var1.setPassword("");
      fail("Expected exception of type java.lang.Exception");
    } catch (java.lang.Exception e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var3 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var5 == false);

  }

  public void test9() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test9");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("hi!");
    boolean var4 = var0.checkPasswords("hi!");
    java.lang.String var5 = var0.getPassword();
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var4 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var5 + "' != '" + ""+ "'", var5.equals(""));

  }

  public void test10() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test10");


    com.es.zumeh.client.model.Password var1 = new com.es.zumeh.client.model.Password("hi!");
    boolean var3 = var1.checkPasswords("hi!");
    boolean var5 = var1.checkPasswords("");
    java.lang.String var6 = var1.getPassword();
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var3 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var5 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var6 + "' != '" + "hi!"+ "'", var6.equals("hi!"));

  }

  public void test11() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test11");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    java.lang.String var1 = var0.getPassword();
    boolean var3 = var0.checkPasswords("aff97160474a056e838c1f721af01edf");
    var0.setPassword("aff97160474a056e838c1f721af01edf");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var1 + "' != '" + ""+ "'", var1.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var3 == false);

  }

  public void test12() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test12");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    java.lang.String var1 = var0.getPassword();
    boolean var3 = var0.checkPasswords("aff97160474a056e838c1f721af01edf");
    java.lang.String var4 = var0.getPassword();
    boolean var6 = var0.checkPasswords("aff97160474a056e838c1f721af01edf");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var1 + "' != '" + ""+ "'", var1.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var3 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var4 + "' != '" + ""+ "'", var4.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var6 == false);

  }

  public void test13() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test13");


    com.es.zumeh.client.model.Password var1 = new com.es.zumeh.client.model.Password("hi!");
    var1.setPassword("hi!");
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      var1.setPassword("");
      fail("Expected exception of type java.lang.Exception");
    } catch (java.lang.Exception e) {
      // Expected exception.
    }

  }

  public void test14() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test14");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    java.lang.String var1 = var0.getPassword();
    var0.setPassword("aff97160474a056e838c1f721af01edf");
    java.lang.String var4 = var0.getPassword();
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var1 + "' != '" + ""+ "'", var1.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var4 + "' != '" + "33efbb43f502e2933fbfc8c508980152"+ "'", var4.equals("33efbb43f502e2933fbfc8c508980152"));

  }

  public void test15() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test15");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    java.lang.String var1 = var0.getPassword();
    boolean var3 = var0.checkPasswords("hi!");
    java.lang.String var4 = var0.getPassword();
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var1 + "' != '" + ""+ "'", var1.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var3 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var4 + "' != '" + ""+ "'", var4.equals(""));

  }

  public void test16() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test16");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    java.lang.String var1 = var0.getPassword();
    java.lang.String var2 = var0.getPassword();
    boolean var4 = var0.checkPasswords("");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var1 + "' != '" + ""+ "'", var1.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var2 + "' != '" + ""+ "'", var2.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var4 == false);

  }

  public void test17() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test17");


    com.es.zumeh.client.model.Password var1 = new com.es.zumeh.client.model.Password("33efbb43f502e2933fbfc8c508980152");

  }

  public void test18() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test18");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("");
    boolean var4 = var0.checkPasswords("");
    boolean var6 = var0.checkPasswords("hi!");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var4 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var6 == false);

  }

  public void test19() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test19");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("");
    boolean var4 = var0.checkPasswords("");
    java.lang.String var5 = var0.getPassword();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      var0.setPassword("");
      fail("Expected exception of type java.lang.Exception");
    } catch (java.lang.Exception e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var4 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var5 + "' != '" + ""+ "'", var5.equals(""));

  }

  public void test20() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test20");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("hi!");
    java.lang.String var3 = var0.getPassword();
    java.lang.String var4 = var0.getPassword();
    var0.setPassword("hi!");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var3 + "' != '" + ""+ "'", var3.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var4 + "' != '" + ""+ "'", var4.equals(""));

  }

  public void test21() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test21");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("hi!");
    var0.setPassword("33efbb43f502e2933fbfc8c508980152");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);

  }

  public void test22() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test22");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("hi!");
    java.lang.String var3 = var0.getPassword();
    boolean var5 = var0.checkPasswords("");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var3 + "' != '" + ""+ "'", var3.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var5 == false);

  }

  public void test23() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test23");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    var0.setPassword("hi!");
    var0.setPassword("hi!");
    var0.setPassword("hi!");
    java.lang.String var7 = var0.getPassword();
    var0.setPassword("aff97160474a056e838c1f721af01edf");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var7 + "' != '" + "aff97160474a056e838c1f721af01edf"+ "'", var7.equals("aff97160474a056e838c1f721af01edf"));

  }

  public void test24() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test24");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    java.lang.String var1 = var0.getPassword();
    // The following exception was thrown during execution.
    // This behavior will recorded for regression testing.
    try {
      var0.setPassword("");
      fail("Expected exception of type java.lang.Exception");
    } catch (java.lang.Exception e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var1 + "' != '" + ""+ "'", var1.equals(""));

  }

  public void test25() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test25");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("hi!");
    java.lang.String var3 = var0.getPassword();
    java.lang.String var4 = var0.getPassword();
    boolean var6 = var0.checkPasswords("33efbb43f502e2933fbfc8c508980152");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var3 + "' != '" + ""+ "'", var3.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue("'" + var4 + "' != '" + ""+ "'", var4.equals(""));
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var6 == false);

  }

  public void test26() throws Throwable {

    if (debug) System.out.printf("%nRandoopTest0.test26");


    com.es.zumeh.client.model.Password var0 = new com.es.zumeh.client.model.Password();
    boolean var2 = var0.checkPasswords("hi!");
    boolean var4 = var0.checkPasswords("hi!");
    boolean var6 = var0.checkPasswords("aff97160474a056e838c1f721af01edf");
    boolean var8 = var0.checkPasswords("hi!");
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var2 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var4 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var6 == false);
    
    // Regression assertion (captures the current behavior of the code)
    assertTrue(var8 == false);

  }

}
