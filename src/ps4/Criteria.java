/**
 * 
 */
package ps4;

/**
 * Criteria interface can be implemented to create criteria for FilterIterator
 * @author alexandraqin
 *
 */
public interface Criteria<T> {

  /**
   * Defines whether an item is accepted or rejected by the FilterIterator
   * @param item
   * @return true if the item is accepted, false if it is rejected
   */
  public boolean accept(T item);
  
  /**
   * Builder for Criteria
   * @param <T> type of the Criteria
   */
  public static class Builder<T> {
    
    /**
     * First criteria passed as a parameter to the Builder constructor
     */
    Criteria<T> criteria;
     
    public Builder(Criteria<T> criteria) {
      this.criteria = criteria;
    }
    
    /**
     * Factory method to add an AndCriteria to the existing criteria
     * @param second is a Criteria of type T
     * @return the builder
     */
    public Builder and(Criteria<T> second) {
      criteria = new AndCriteria<T>(criteria, second);
      return this;
    }
    
    /**
     * Factory method to add an OrCriteria to the existing criteria
     * @param second is a Criteria of type T
     * @return the builder
     */
    public Builder or(Criteria<T> second) {
      criteria = new OrCriteria<T>(criteria, second);
      return this;
    }
    
    /**
     * Factory method to add a NotCriteria to the existing criteria
     * This negates the existing criteria
     * @return the builder
     */
    public Builder not() {
      criteria = new NotCriteria(criteria);
      return this;
    }
    
    /**
     * Returns the built criteria
     * @return a criteria of type T
     */
    public Criteria<T> build() {
      return criteria;
    }
    
  }
}