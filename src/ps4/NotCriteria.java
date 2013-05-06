/**
 * 
 */
package ps4;

/**
 * NotCriteria implements Criteria. Its constructor takes a Criteria as a
 * parameter and creates a negated criteria.
 * @author alexandraqin
 *
 */
public class NotCriteria<T> implements Criteria<T> {

  private Criteria<T> criteria;
  
  public NotCriteria(Criteria<T> criteria) {
    this.criteria = criteria;
  }
  
  /**
   * Returns the logical NOT of its criteria accept
   * @param item of type T
   * @return true if item is accepted, false if it is rejected
   */
  public boolean accept(T item) {
    return !criteria.accept(item);
  }
  
  @Override public String toString() {
    return "NotCriteria(" + criteria.toString() + ")";
  }
  
  @Override public boolean equals(Object other) {
    if (other instanceof NotCriteria) {
      return criteria.equals(((NotCriteria) other).criteria);
    }
    else {
      return false;
    }
  }
  
  @Override public int hashCode() {
    int result = 17;
    result = 31 * result + criteria.hashCode();
    return result;
  }

}