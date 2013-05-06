/**
 * 
 */
package ps4;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * @author alexandraqin
 *
 */
public class TestFilterIterator {
  
  class NotNullCriteria implements Criteria<Integer> {
    public boolean accept(Integer item) {
      return item != null;
    }
  }
  
  Criteria<Integer> notNull = new NotNullCriteria();
  
  Integer[] array = { null, 1, 2, 3, null, 4 };
  Integer[] nullArray = { null, null };
  
  ArrayList<Integer> empty = new ArrayList<Integer>();
  ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(array));
  ArrayList<Integer> nulls = new ArrayList<Integer>(Arrays.asList(nullArray));
  
  @Test 
  public void testHasNext() {
    Iterator<Integer> itr = new FilterIterator<Integer>(numbers.iterator(), notNull);
    assertTrue(itr.hasNext());
  }
  
  @Test
  public void testHasNextFalse() {
    Iterator<Integer> nullItr = new FilterIterator<Integer>(nulls.iterator(), notNull);
    assertFalse(nullItr.hasNext());
  }
  
  @Test
  public void testHasNextEmpty() {
    Iterator<Integer> emptyItr = new FilterIterator<Integer>(empty.iterator(), notNull);
    assertFalse(emptyItr.hasNext());
  }
  
  @Test
  public void testNext() {
    Iterator<Integer> itr = new FilterIterator<Integer>(numbers.iterator(), notNull);
    assertEquals(itr.next(), (Integer)1);
    assertEquals(itr.next(), (Integer)2);
    assertEquals(itr.next(), (Integer)3);
    assertEquals(itr.next(), (Integer)4);
  }
  
  @Test(expected=NoSuchElementException.class)
  public void testNextFalse() {
    Iterator<Integer> nullItr = new FilterIterator<Integer>(nulls.iterator(), notNull);
    nullItr.next();
    fail("Should have thrown a NoSuchElementException");
  }
  
  @Test(expected=NoSuchElementException.class)
  public void testNextEmpty() {
    Iterator<Integer> emptyItr = new FilterIterator<Integer>(empty.iterator(), notNull); 
    emptyItr.next();
    fail("Should have thrown a NoSuchElementException");
  }
  
  @Test(expected=UnsupportedOperationException.class)
  public void testRemove() {
    Iterator<Integer> itr = new FilterIterator<Integer>(numbers.iterator(), notNull);
    itr.remove();
    fail("Should have thrown an UnsupportedOperationException");
  }
  
  @Test
  public void testHasNextAndNext() {
    Iterator<Integer> itr = new FilterIterator<Integer>(numbers.iterator(), notNull);
    int i = 1;
    int score = 0;
    while(itr.hasNext()) {
      if (itr.next() == i) {
        score++;
      }
      i++;
    }
    assertEquals(score, 4);
  }
  
  @Test
  public void testHasNextAndNextFalse() {
    Iterator<Integer> itr = new FilterIterator<Integer>(nulls.iterator(), notNull);
    int score = 0;
    while(itr.hasNext()) {
      score++;
    }
    assertEquals(score, 0);
  }
  
  @Test
  public void testHasNextAndNextEmpty() {
    Iterator<Integer> itr = new FilterIterator<Integer>(empty.iterator(), notNull);
    int score = 0;
    while(itr.hasNext()) {
      score++;
    }
    assertEquals(score, 0);
  }
  
}
