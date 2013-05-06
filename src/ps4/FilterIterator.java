/**
 * 
 */
package ps4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FilterIterator implements Iterator. It is used to iterate over an Iterable
 * according to a given criteria. Its constructor takes an Iterator and a 
 * Criteria.
 * @author alexandraqin
 * @param <E> type of the Iterator and Criteria
 *
 */
public class FilterIterator<E> implements Iterator<E> {

  private Iterator<E> iterator;
  private Criteria<E> criteria;
  private boolean popped;
  private E currentItem;
  
  public FilterIterator(Iterator<E> iterator, Criteria<E> criteria) {  
    this.iterator = iterator;
    this.criteria = criteria;
    this.popped = false;
  }
  
  /**
   * @return true if the Iterable has a next item that passes the criteria
   * test, returns false otherwise
   */
  public boolean hasNext() {
    if (iterator.hasNext()) {
      E next;
      if (popped) {
        next = currentItem;
        popped = false;
      }
      else {
        next = iterator.next();
        currentItem = next;
        popped = true;
      }
      while (iterator.hasNext() && !criteria.accept(next)) {
        next = iterator.next();
        currentItem = next;
      }
      if (criteria.accept(next)) {
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  /**
   * @return the next element in the Iterable that passes the criteria test
   * @throws NoSuchElementException() if there is no such element
   */
  public E next() {
    E next;
    if (popped) {
      next = currentItem;
      popped = false;
    }
    else { 
      next = iterator.next();
    }
    while (iterator.hasNext() && !criteria.accept(next)) {
      next = iterator.next();
    }
    if (criteria.accept(next)) {
      return next;
    }
    else {
      throw new NoSuchElementException();
    }
  }

  /**
   * @throws UnsupportedOpetationException() 
   */
  public void remove() {
    throw new UnsupportedOperationException();
  }
  
  @Override public String toString() {
    return "FilterIterator(" + iterator.toString() + ", " + criteria.toString() + ")";
  }
  
  @Override public boolean equals(Object other) {
    if (other instanceof FilterIterator) {
      FilterIterator fi = (FilterIterator) other;
      return iterator.equals(fi.iterator) && criteria.equals(fi.criteria);
    }
    else {
      return false;
    }
  }
  
  @Override public int hashCode() {
    int result = 16;
    result = 31 * result + iterator.hashCode();
    result = 31 * result + criteria.hashCode();
    return result;
  }

}
