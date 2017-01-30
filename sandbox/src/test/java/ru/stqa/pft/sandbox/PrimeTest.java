package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by doomin on 02.01.17.
 */
public class PrimeTest {

  @Test
  public void testPrimes() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimes() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }

  @Test (enabled = false)
  public void testPrimesLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
}
