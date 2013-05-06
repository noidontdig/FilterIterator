/**
 * 
 */
package ps4;

/**
 * AndCriteria implements Criteria. Its constructor takes two Criteria as
 * parameters and creates a composed OR criteria.
 * @author alexandraqin
 *
 */
public class OrCriteria<T> implements Criteria<T> {

  private Criteria<T> first;
  private Criteria<T> second;
  
  public OrCriteria(Criteria<T> first, Criteria<T> second) {
    this.first = first;
    this.second = second;
  }
  
  /**
   * Returns the logical OR of its two criterias' accept methods
   * @param item of type T
   * @return true if item is accepted, false if it is rejected
   */
  public boolean accept(T item) {
    return first.accept(item) || second.accept(item);
  }

  @Override public String toString() {
    return "OrCriteria(" + first.toString() + ", " + second.toString() + ")";
  }
  
  @Override public boolean equals(Object other) {
    if (other instanceof OrCriteria) {
      OrCriteria or = (OrCriteria) other;
      return (first.equals(or.first) && second.equals(or.second))
      || (first.equals(or.second) && second.equals(or.first));
    }
    else {
      return false;
    }
  }
  
  @Override public int hashCode() {
    int result = 19;
    result = 31 * result + first.hashCode();
    result = 31 * result + second.hashCode();
    return result;
  }  
  
}
