/**
 * 
 */
package ps4;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author alexandraqin
 *
 */
public class TestCriteria {

  class PositiveCriteria implements Criteria<Integer> {
    public boolean accept(Integer item) {
      if (item == null)
        return false;
      else return item > 0;
    }
  }
  
  class NegativeCriteria implements Criteria<Integer> {
    public boolean accept(Integer item) {
      if (item == null)
        return false;
      return item < 0;
    }
  }
  
  class NotNullCriteria implements Criteria<Integer> {
    public boolean accept(Integer item) {
      return item != null;
    }
  }
  
  Criteria<Integer> positive = new PositiveCriteria();
  Criteria<Integer> negative = new NegativeCriteria();
  Criteria<Integer> notNull = new NotNullCriteria();
  
  @Test
  public void testAndCriteria() {
    Criteria<Integer> positiveAndNotNull = new AndCriteria<Integer>(positive, notNull);
    assertTrue(positiveAndNotNull.accept(4));
    assertFalse(positiveAndNotNull.accept(null));
    assertFalse(positiveAndNotNull.accept(-4));
  }
  
  @Test
  public void testOrCriteria() {
    Criteria<Integer> positiveOrNegative = new OrCriteria<Integer>(positive, negative);
    assertTrue(positiveOrNegative.accept(4));
    assertFalse(positiveOrNegative.accept(0));
    assertTrue(positiveOrNegative.accept(-4));
  }
  
  @Test
  public void testNotCriteria() {
    Criteria<Integer> notPositive = new NotCriteria<Integer>(positive);
    assertFalse(notPositive.accept(4));
    assertTrue(notPositive.accept(0));
    assertTrue(notPositive.accept(null));
  }
  
  @Test
  public void testCriteriaBuilder() {
    Criteria<Integer> c = new Criteria.Builder(notNull).and(positive)
        .or(negative).not().build();
    assertFalse(c.accept(4));
    assertTrue(c.accept(null));
    assertFalse(c.accept(-4));
    assertTrue(c.accept(0));
  }
}
