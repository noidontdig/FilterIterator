/**
 * 
 */
package ps4;

/**
 * AndCriteria implements Criteria. Its constructor takes two Criteria as
 * parameters and creates a composed AND criteria.
 * @author alexandraqin
 *
 */
public class AndCriteria<T> implements Criteria<T> {

  private Criteria<T> first;
  private Criteria<T> second;
  
  public AndCriteria(Criteria<T> first, Criteria<T> second) {
    this.first = first;
    this.second = second;
  }
  
  /**
   * Returns the logical AND of its two criterias' accept methods
   * @param item of type T
   * @return true if item is accepted, false if it is rejected
   */
  public boolean accept(T item) {
    return first.accept(item) && second.accept(item);
  }
  
  @Override public String toString() {
    return "AndCriteria(" + first.toString() + ", " + second.toString() + ")";
  }
  
  @Override public boolean equals(Object other) {
    if (other instanceof AndCriteria) {
      AndCriteria and = (AndCriteria) other;
      return (first.equals(and.first) && second.equals(and.second))
      || (first.equals(and.second) && second.equals(and.first));
    }
    else {
      return false;
    }
  }
  
  @Override public int hashCode() {
    int result = 18;
    result = 31 * result + first.hashCode();
    result = 31 * result + second.hashCode();
    return result;
  }

}